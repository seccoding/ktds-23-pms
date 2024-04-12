package com.ktdsuniversity.edu.pms.project.vo;

import java.util.List;

public class ProjectListVO {
    private int projectCount;

    private List<ProjectVO> projectList;

    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    public List<ProjectVO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectVO> projectList) {
        this.projectList = projectList;
    }

}
