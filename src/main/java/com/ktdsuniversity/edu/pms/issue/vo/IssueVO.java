package com.ktdsuniversity.edu.pms.issue.vo;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;

public class IssueVO {

	private String isId;
	private String rqmId;
	private String isTtl;
	private int isCnt;
	private String isLv;
	private String isFile;
	private String isCntnt;
	private String isMngr;
	private String isSts;
	private String crtDt;
	private String crtrId;
	private String mdfDt;
	private String mdfrId;
	private String delYn;
	private String fileName;
	private String originFileName;
	
	private RequirementVO requirementVO; 
	
	private ProjectVO  projectVO;
	
	private EmployeeVO employeeVO;
	private String empId;
	private String empName;
	
	public String getIsId() {
		return isId;
	}
	public void setIsId(String isId) {
		this.isId = isId;
	}
	public String getRqmId() {
		return rqmId;
	}
	public void setRqmId(String rqmId) {
		this.rqmId = rqmId;
	}
	public String getIsTtl() {
		return isTtl;
	}
	public void setIsTtl(String isTtl) {
		this.isTtl = isTtl;
	}
	public int getIsCnt() {
		return isCnt;
	}
	public void setIsCnt(int isCnt) {
		this.isCnt = isCnt;
	}
	public String getIsLv() {
		return isLv;
	}
	public void setIsLv(String isLv) {
		this.isLv = isLv;
	}
	public String getIsFile() {
		return isFile;
	}
	public void setIsFile(String isFile) {
		this.isFile = isFile;
	}
	public String getIsCntnt() {
		return isCntnt;
	}
	public void setIsCntnt(String isCntnt) {
		this.isCntnt = isCntnt;
	}
	public String getIsMngr() {
		return isMngr;
	}
	public void setIsMngr(String isMngr) {
		this.isMngr = isMngr;
	}
	public String getIsSts() {
		return isSts;
	}
	public void setIsSts(String isSts) {
		this.isSts = isSts;
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
	public RequirementVO getRequirementVO() {
		return requirementVO;
	}
	public void setRequirementVO(RequirementVO requirementVO) {
		this.requirementVO = requirementVO;
	}
	public ProjectVO getProjectVO() {
		return projectVO;
	}
	public void setProjectVO(ProjectVO projectVO) {
		this.projectVO = projectVO;
	}
	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}
	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
}
