package com.ktdsuniversity.edu.pms.project.service;

import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.SearchProjectVO;

public interface ProjectService {
    ProjectListVO getAllProject();

    ProjectListVO searchProject(SearchProjectVO searchProjectVO);

    ProjectVO getOneProject(String projectId);

    boolean createNewProject(CreateProjectVO projectVO);

    boolean deleteOneProject(String projectId);
}
