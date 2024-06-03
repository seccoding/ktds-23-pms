package com.ktdsuniversity.edu.pms.deptteam.vo;

import java.util.List;

public class DepartmentListVO {

	private int departmentCnt;
	private List<DepartmentVO> departmentList;

	public int getDepartmentCnt() {
		return departmentCnt;
	}

	public void setDepartmentCnt(int departmentCnt) {
		this.departmentCnt = departmentCnt;
	}

	public List<DepartmentVO> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DepartmentVO> departmentList) {
		this.departmentList = departmentList;
	}

}