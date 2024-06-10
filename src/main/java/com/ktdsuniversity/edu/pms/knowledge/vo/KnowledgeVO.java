package com.ktdsuniversity.edu.pms.knowledge.vo;

import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;

public class KnowledgeVO {
	
	private String knlId;
	private String knlTtl;
	private String knlCntnt;
	private int knlCnt;
	private int knlRecCnt;
	private String crtDt;
	private String mdfDt;
	private String delYn;
	private String crtrId;
	private String mdfrId;
	
	// file - 난독화
	private String fileName;
	// file - 난독화X
	private String originFileName;
	
//	클래스를 선언
	private RequirementVO requirementVO; 
	private ProjectVO  projectVO;
	private EmployeeVO employeeVO;
	private String empId;
	private String empName;
	
	private CommonCodeVO commonCodeVO;
	private KnowledgeRecommendVO knowledgeRecommendVO;
	
	public String getKnlId() {
		return knlId;
	}
	public void setKnlId(String knlId) {
		this.knlId = knlId;
	}
	public String getKnlTtl() {
		return knlTtl;
	}
	public void setKnlTtl(String knlTtl) {
		this.knlTtl = knlTtl;
	}
	public String getKnlCntnt() {
		return knlCntnt;
	}
	public void setKnlCntnt(String knlCntnt) {
		this.knlCntnt = knlCntnt;
	}
	public int getKnlCnt() {
		return knlCnt;
	}
	public void setKnlCnt(int knlCnt) {
		this.knlCnt = knlCnt;
	}
	public int getKnlRecCnt() {
		return knlRecCnt;
	}
	public void setKnlRecCnt(int knlRecCnt) {
		this.knlRecCnt = knlRecCnt;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	public String getMdfDt() {
		return mdfDt;
	}
	public void setMdfDt(String mdfDt) {
		this.mdfDt = mdfDt;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getCrtrId() {
		return crtrId;
	}
	public void setCrtrId(String crtrId) {
		this.crtrId = crtrId;
	}
	public String getMdfrId() {
		return mdfrId;
	}
	public void setMdfrId(String mdfrId) {
		this.mdfrId = mdfrId;
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
	public CommonCodeVO getCommonCodeVO() {
		return commonCodeVO;
	}
	public void setCommonCodeVO(CommonCodeVO commonCodeVO) {
		this.commonCodeVO = commonCodeVO;
	}
	public KnowledgeRecommendVO getKnowledgeRecommendVO() {
		return knowledgeRecommendVO;
	}
	public void setKnowledgeRecommendVO(KnowledgeRecommendVO knowledgeRecommendVO) {
		this.knowledgeRecommendVO = knowledgeRecommendVO;
	}
}
