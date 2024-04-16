package com.ktdsuniversity.edu.pms.department.vo;

<<<<<<< Updated upstream
public class DepartmentVO {
	private String deptId;
	private String deptName;
	private String deptCrDt;
	private String delYn;
	private String deptLeadId;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCrDt() {
		return deptCrDt;
	}

	public void setDeptCrDt(String deptCrDt) {
		this.deptCrDt = deptCrDt;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getDeptLeadId() {
		return deptLeadId;
	}

	public void setDeptLeadId(String deptLeadId) {
		this.deptLeadId = deptLeadId;
	}
=======
import java.util.List;
>>>>>>> Stashed changes

public class DepartmentVO {
	private String deptId;
	private String deptName;
	private String deptCrDt;
	private String delYn;
	private String deptLeadId;
	private List<DepartmentVO> departmentList;
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCrDt() {
		return deptCrDt;
	}
	public void setDeptCrDt(String deptCrDt) {
		this.deptCrDt = deptCrDt;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getDeptLeadId() {
		return deptLeadId;
	}
	public void setDeptLeadId(String deptLeadId) {
		this.deptLeadId = deptLeadId;
	}
	public List<DepartmentVO> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List<DepartmentVO> departmentList) {
		this.departmentList = departmentList;
	}
}
