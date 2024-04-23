package com.ktdsuniversity.edu.pms.project.vo;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class ProjectTeammateVO {

	private String prjTmId;
	private String prjId;
	private String tmId;
	private String role;
	private String delYn;
	private String rvYn;
	private String srvYn;

	private EmployeeVO employeeVO;

	public String getPrjTmId() {
		return prjTmId;
	}

	public void setPrjTmId(String prjTmId) {
		this.prjTmId = prjTmId;
	}

	public String getPrjId() {
		return prjId;
	}

	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}

	public String getTmId() {
		return tmId;
	}

	public void setTmId(String tmId) {
		this.tmId = tmId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}

	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}

	public String getRvYn() {
		return rvYn;
	}

	public void setRvYn(String rvYn) {
		this.rvYn = rvYn;
	}

	public String getSrvYn() {
		return srvYn;
	}

	public void setSrvYn(String srvYn) {
		this.srvYn = srvYn;
	}
}
