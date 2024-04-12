package com.ktdsuniversity.edu.pms.project.service;

import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;

public interface ProjectService {
    ProjectListVO getAllProject();

    ProjectVO getOneProject(String projectId);

    boolean createNewProject(CreateProjectVO projectVO);

    boolean deleteOneProject(String projectId);
}
