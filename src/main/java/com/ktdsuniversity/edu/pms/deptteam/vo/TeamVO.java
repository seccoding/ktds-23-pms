package com.ktdsuniversity.edu.pms.deptteam.vo;

public class TeamVO {
	
	// 팀 고유번호
	private String tmId;
	// 팀 생성일
	private String tmCrDt;
	// 팀 명
	private String tmName;
	// 부서명
	private String deptId;
	// 팀 삭제 여부
	private String delYn;
	// 팀장 고유번호
	private String tmLeadId;

	public String getTmId() {
		return tmId;
	}

	public void setTmId(String tmId) {
		this.tmId = tmId;
	}

	public String getTmCrDt() {
		return tmCrDt;
	}

	public void setTmCrDt(String tmCrDt) {
		this.tmCrDt = tmCrDt;
	}

	public String getTmName() {
		return tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getTmLeadId() {
		return tmLeadId;
	}

	public void setTmLeadId(String tmLeadId) {
		this.tmLeadId = tmLeadId;
	}

}
