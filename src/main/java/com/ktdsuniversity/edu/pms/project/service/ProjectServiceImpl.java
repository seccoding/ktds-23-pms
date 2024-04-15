package com.ktdsuniversity.edu.pms.project.service;

import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.project.dao.ProjectDao;
import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.SearchProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
