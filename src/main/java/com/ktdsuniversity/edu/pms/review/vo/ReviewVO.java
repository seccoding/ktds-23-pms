package com.ktdsuniversity.edu.pms.review.vo;

import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;

public class ReviewVO extends PaginateVO {

	private String rvId;
	private String rvCntnt;
	private String prjId;
	private String crtDt;
	private String crtrId;
	private String mdfDt;
	private String mdfrId;
	private String delYn;
	private String empId;
	private String mngrYn;
	private String starRating;
		
	private ProjectVO projectVO;
	private DepartmentVO departmentVO;
	private String deptName;
	private ProjectTeammateVO projectTeammateVO;
	private EmployeeVO employeeVO;
	private String rvYn;
	
	public String getMngrYn() {
		return mngrYn;
	}
	public void setMngrYn(String mngrYn) {
		this.mngrYn = mngrYn;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getRvYn() {
		return rvYn;
	}
	public void setRvYn(String rvYn) {
		this.rvYn = rvYn;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}
	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}
	public ProjectTeammateVO getProjectTeammateVO() {
		return projectTeammateVO;
	}
	public void setProjectTeammateVO(ProjectTeammateVO projectTeammateVO) {
		this.projectTeammateVO = projectTeammateVO;
	}
	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}
	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}
	public ProjectVO getProjectVO() {
		return projectVO;
	}
	public void setProjectVO(ProjectVO projectVO) {
		this.projectVO = projectVO;
	}
	public String getRvId() {
		return rvId;
	}
	public void setRvId(String rvId) {
		this.rvId = rvId;
	}
	public String getRvCntnt() {
		return rvCntnt;
	}
	public void setRvCntnt(String rvCntnt) {
		this.rvCntnt = rvCntnt;
	}
	public String getPrjId() {
		return prjId;
	}
	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	public String getCrtrId() {
		return crtrId;
	}
	public void setCrtrId(String crtrId) {
		this.crtrId = crtrId;
	}
	public String getMdfDt() {
		return mdfDt;
	}
	public void setMdfDt(String mdfDt) {
		this.mdfDt = mdfDt;
	}
	public String getMdfrId() {
		return mdfrId;
	}
	public void setMdfrId(String mdfrId) {
		this.mdfrId = mdfrId;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getStarRating() {
		return starRating;
	}

	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}
}
