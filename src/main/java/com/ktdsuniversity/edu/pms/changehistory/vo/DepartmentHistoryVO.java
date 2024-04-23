package com.ktdsuniversity.edu.pms.changehistory.vo;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;

public class DepartmentHistoryVO {
	
	private String deptHistId;
	private String empId;
	private String deptStrtDt;
	private String deptEndDt;
	private String cnNote;
	private String delYn;
	private String pastDeptId;
	
	private DepartmentVO departmentVO;
	
	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}
	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}
	public String getDeptHistId() {
		return deptHistId;
	}
	public void setDeptHistId(String deptHistId) {
		this.deptHistId = deptHistId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getDeptStrtDt() {
		return deptStrtDt;
	}
	public void setDeptStrtDt(String deptStrtDt) {
		this.deptStrtDt = deptStrtDt;
	}
	public String getDeptEndDt() {
		return deptEndDt;
	}
	public void setDeptEndDt(String deptEndDt) {
		this.deptEndDt = deptEndDt;
	}
	public String getCnNote() {
		return cnNote;
	}
	public void setCnNote(String cnNote) {
		this.cnNote = cnNote;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getPastDeptId() {
		return pastDeptId;
	}
	public void setPastDeptId(String pastDeptId) {
		this.pastDeptId = pastDeptId;
	}
	
	

}
