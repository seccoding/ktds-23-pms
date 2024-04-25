package com.ktdsuniversity.edu.pms.project.vo;

public class ProjectSurveyQuestionVO extends ProjectVO {

	private ProjectTeammateVO projectTeammateVO;
	
	private String srvSts;
	
	private String srvYn;

	public String getSrvYn() {
		return srvYn;
	}

	public void setSrvYn(String srvYn) {
		this.srvYn = srvYn;
	}

	public String getSrvSts() {
		return srvSts;
	}

	public void setSrvSts(String srvSts) {
		this.srvSts = srvSts;
	}
	
	public ProjectTeammateVO getProjectTeammateVO() {
		return projectTeammateVO;
	}
	
	public void setProjectTeammateVO(ProjectTeammateVO projectTeammateVO) {
		this.projectTeammateVO = projectTeammateVO;
	}
}
