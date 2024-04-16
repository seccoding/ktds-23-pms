package com.ktdsuniversity.edu.pms.employee.vo;

import java.util.List;

public class EmployeeListVO {
	
	private int employeeCnt ;
	
	private List<EmployeeVO> employeeList;
	
	
	public int getEmployeeCnt() {
		return employeeCnt;
	}

	public void setEmployeeCnt(int employeeCnt) {
		this.employeeCnt = employeeCnt;
	}

	public List<EmployeeVO> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeVO> employeeList) {
		this.employeeList = employeeList;
	}


	

}
