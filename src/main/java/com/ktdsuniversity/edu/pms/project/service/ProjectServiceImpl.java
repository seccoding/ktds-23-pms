package com.ktdsuniversity.edu.pms.project.service;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.CreationException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.project.dao.ProjectDao;
import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectStatusVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.SearchProjectVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

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
        for(ProjectVO prj:projectList) {
        	ProjectTeammateVO prjTmMate = new ProjectTeammateVO();
        	EmployeeVO empVO = new EmployeeVO();
        	empVO.setEmpName(this.projectDao.findPmNameByPrjId(prj.getPrjId()));
        	prjTmMate.setEmployeeVO(empVO);
        	prj.setPm(prjTmMate);
        	prj.getPm().setEmployeeVO(empVO);;
        }

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

    @Transactional
    @Override
    public boolean deleteManyTeammate(List<String> deleteItems) {
        int deletedCount = projectDao.deleteManyTeammate(deleteItems);

        return deletedCount > 0;
    }

    @Transactional
    @Override
    public boolean deleteOneTeammate(String prjTmId) {
        return projectDao.deleteByTeammateId(prjTmId) > 0;
    }

    @Transactional
    @Override
    public boolean insertOneTeammate(ProjectTeammateVO newProjectTeammate) {
        // project id와 employee id로 한 명의 팀원을 가져온다. 팀원이 삭제된 팀원인지 아닌지를 판단하는 여부를 여기서 확인함
        ProjectTeammateVO originTeammate = projectDao.findTeammateByProjectIdAndEmployeeId(newProjectTeammate);

        int maxProjectLeaderCount = 2;

        // 기존 및 삭제 팀원에 대한 수정 후 등록 로직
        if (originTeammate != null) {
            // PL 추가인지 NONE 추가인지 체크
            if (newProjectTeammate.getRole().equals("PL")) {
                if (maxProjectLeaderCount > projectDao.selectTeammateRolePLCountByProjectId(newProjectTeammate.getPrjId())) {
                    if (originTeammate.getDelYn().equals("Y")) {
                        originTeammate.setRole(newProjectTeammate.getRole());
                        return projectDao.updateTeammateDeleteYnAndRoleByProjectTeammateId(originTeammate) > 0;

                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if (newProjectTeammate.getRole().equals("NONE")) {

                if (originTeammate.getDelYn().equals("Y")) {

                    originTeammate.setRole(newProjectTeammate.getRole());
                    return projectDao.updateTeammateDeleteYnAndRoleByProjectTeammateId(originTeammate) > 0;

                }

            } else {
                throw new CreationException();
            }
        } else {
            // PL 추가인지 NONE 추가인지 체크
            if (newProjectTeammate.getRole().equals("PL")) {
                logger.debug("팀원의 역할 :" + newProjectTeammate.getTmId());
                if (maxProjectLeaderCount > projectDao.selectTeammateRolePLCountByProjectId(newProjectTeammate.getPrjId())) {
                    logger.debug("해당 프로젝트의 현재 PL 인원 :" +  projectDao.selectTeammateRolePLCountByProjectId(newProjectTeammate.getPrjId()));
                    return projectDao.insertNewProjectTeammate(newProjectTeammate) > 0;
                } else {
                    return false;
                }

            } else if (newProjectTeammate.getRole().equals("NONE")) {
                return projectDao.insertNewProjectTeammate(newProjectTeammate) > 0;
            } else {
                throw new CreationException();
            }
        }

        return false;
    }

    @Override
    public List<ProjectTeammateVO> getAllProjectTeammate() {
        return projectDao.findAllProjectTeammate();
    }

    @Override
    public List<ProjectVO> getAllProjectByProjectTeammateId(String tmId) {
        return projectDao.findAllProjectByEmployeeId(tmId);
    }

    @Transactional
    @Override
    public boolean updateReviewStatus(ProjectTeammateVO projectTeammateVO) {
        return projectDao.updateOneTeammateReviewStatusByProjectIdAndEmployeeId(projectTeammateVO) > 0;
    }

    @Transactional
    @Override
    public boolean updateSurveyStatus(ProjectTeammateVO projectTeammateVO) {
        return projectDao.updateOneTeammateSurveyStatusByProjectIdAndEmployeeId(projectTeammateVO) > 0;
    }

	@Override
	public List<ProjectTeammateVO> getAllProjectTeammateByTmId(String empId) {
		return projectDao.getAllProjectTeammateByTmId(empId);
	}

	@Override
	public String getPrjTmId(ProjectTeammateVO projectTeammateVO) {
		return projectDao.getPrjTmId(projectTeammateVO);
	}

	@Override
	public List<ProjectVO> getAllPrjEvent(String empId) {
		return projectDao.getAllPrjEvent(empId);
	}

}
