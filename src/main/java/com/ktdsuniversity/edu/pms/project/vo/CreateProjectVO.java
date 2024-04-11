package com.ktdsuniversity.edu.pms.project.vo;

public class CreateProjectVO extends ProjectVO {
	// insert 시, teammate table 에 pm 추가
	private String pmId;

	public String getPmId() {
		return pmId;
	}

	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
}
