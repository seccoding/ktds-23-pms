package com.ktdsuniversity.edu.pms.project.service;

import com.ktdsuniversity.edu.pms.exceptions.CreationException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.project.dao.ProjectDao;
import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectStatusVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.SearchProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao projectDao;

    @Override
    public ProjectListVO getAllProject() {
        int projectCount = projectDao.selectAllProjectCount();
        List<ProjectVO> projectList = projectDao.selectAllProject();

        ProjectListVO projectListVO = new ProjectListVO();
        projectListVO.setProjectCount(projectCount);
        projectListVO.setProjectList(projectList);

        return projectListVO;
    }

    @Override
    public ProjectListVO searchProject(SearchProjectVO searchProjectVO) {
        int projectCount = projectDao.searchProjectCount(searchProjectVO);
        searchProjectVO.setPageCount(projectCount);

        List<ProjectVO> projectList = projectDao.searchBoard(searchProjectVO);

        ProjectListVO projectListVO = new ProjectListVO();
        projectListVO.setProjectCount(projectCount);
        projectListVO.setProjectList(projectList);

        return projectListVO;
    }

    @Override
    public ProjectVO getOneProject(String projectId) {
        ProjectVO projectVO = projectDao.findById(projectId);

        if (projectVO == null) {
            throw new PageNotFoundException();
        }

        return projectVO;
    }

    @Transactional
    @Override
    public boolean createNewProject(CreateProjectVO projectVO) {
        int insertCount = projectDao.insertNewProject(projectVO);
        boolean isCreateSuccess = insertCount > 0;

        if (isCreateSuccess) {
            ProjectTeammateVO pm = new ProjectTeammateVO();
            pm.setPrjId(projectVO.getPrjId());
            pm.setTmId(projectVO.getPmId());
            pm.setRole("PM");

            projectDao.insertNewPm(pm);
        }

        return isCreateSuccess;
    }

    @Transactional
    @Override
    public boolean deleteOneProject(String projectId) {
        int deleteCount = projectDao.deleteById(projectId);

        return deleteCount > 0;
    }

    @Override
    public int getProjectTeammateCount(String projectId) {
        return projectDao.selectProjectTeammateCount(projectId);
    }

    public List<ProjectTeammateVO> getAllProjectTeammateByProjectId(String projectId) {
        List<ProjectTeammateVO> projectTeammateList = projectDao.findAllProjectTeammateByProjectId(projectId);

        if (projectTeammateList == null || projectTeammateList.isEmpty()) {
            throw new PageNotFoundException();
        }

        return projectTeammateList;
    }

    @Override
    public Map<String, List<ProjectStatusVO>> getProjectStatus(String projectId) {

        List<ProjectStatusVO> projectRequirementStatusList = projectDao.getProjectRequirementStatusList(projectId);
        List<ProjectStatusVO> projectIssueStatusList = projectDao.getProjectIssueStatusList(projectId);

        Map<String, List<ProjectStatusVO>> chartData = new HashMap<>();

        chartData.put("requirement", projectRequirementStatusList);
        chartData.put("issue", projectIssueStatusList);

        return chartData;
    }

    @Transactional
    @Override
    public boolean updateOneProject(CreateProjectVO modifyProjectVO) {
        // 1. 프로젝트를 업데이트 한다.
        boolean isModifySuccess = projectDao.updateOneProject(modifyProjectVO) > 0;

        // 2-1. 성공 시, PM을 가져온다. projectDao.findPmByProjectId(modifyProjectVO.getPrjId()), SELECT WHERE DEL_YN = 'N'
        if (isModifySuccess) {
            ProjectTeammateVO prePm = projectDao.findPmByProjectId(modifyProjectVO.getPrjId());

            // 2-2. 이전의 PM과 변경된 PM의 ID를 비교한다
            if (prePm.getTmId().equals(modifyProjectVO.getPmId())) {
                // 변경점이 없으면 PM은 업데이트하지 않는다.
                return true;
            } else {
                // 3. PM이 변경됐다면, 해당 프로젝트의 PM을 논리적 삭제한다.
                // UPDATE WHERE DEL_YN = 'Y'
                boolean isDeleteSuccess = projectDao.deletePm(prePm.getPrjId()) > 0;

                // 4. 기존 PM 업데이트(논리적 삭제)가 성공되면, 새로운 PM을 INSERT 한다.
                if (isDeleteSuccess) {
                    CreateProjectVO selectedPreDeletedPm = projectDao.selectDeletedPm(modifyProjectVO);
                    if (selectedPreDeletedPm != null) {
                        // 4-1. 만약 새로운 PM이 기존에 있던 PM이라면, DEL_YN만 UPDATE 한다 DEL_YN = 'N'
                        return projectDao.restoreDeletedPm(modifyProjectVO) > 0;
                    } else {
                        // 4-2. 없다면 새로운 PM을 INSERT 한다.
                        ProjectTeammateVO newPm = new ProjectTeammateVO();
                        newPm.setPrjId(modifyProjectVO.getPrjId());
                        newPm.setTmId(modifyProjectVO.getPmId());
                        newPm.setRole("PM");

                        return projectDao.insertNewPm(newPm) > 0;
                    }
                }
            }
        }

        // 5. 종료
        return isModifySuccess;
    }
}
