package com.ktdsuniversity.edu.pms.qna.vo;

import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;

public class QnaVO {
	
	private String qaId;
	private String prjId;
	private String qaTtl;
	private String qaCntnt;
	private int qaCnt;
	private int qaRecCnt;
	private String crtDt;
	private String mdfDt;
	private String delYn;
	private String crtrId;
	private String mdfrId;
	
	// file - 난독화
	private String fileName;
	// file - 난독화X
	private String originFileName;
	private ProjectVO projectVO;
	private EmployeeVO employeeVO;
	private String empId;
	private String empName;
	private CommonCodeVO commonCodeVO;
	private QnaRecommendVO qnaRecommendVO;

	
	public String getQaId() {
		return qaId;
	}
	public void setQaId(String qaId) {
		this.qaId = qaId;
	}

	
	public String getQaTtl() {
		return qaTtl;
	}
	public void setQaTtl(String qaTtl) {
		this.qaTtl = qaTtl;
	}
	public String getQaCntnt() {
		return qaCntnt;
	}
	public void setQaCntnt(String qaCntnt) {
		this.qaCntnt = qaCntnt;
	}
	public int getQaCnt() {
		return qaCnt;
	}
	public void setQaCnt(int qaCnt) {
		this.qaCnt = qaCnt;
	}
	public int getQaRecCnt() {
		return qaRecCnt;
	}
	public void setQaRecCnt(int qaRecCnt) {
		this.qaRecCnt = qaRecCnt;
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
	public QnaRecommendVO getQnaRecommendVO() {
		return qnaRecommendVO;
	}
	public void setQnaRecommendVO(QnaRecommendVO qnaRecommendVO) {
		this.qnaRecommendVO = qnaRecommendVO;
	}
	public CommonCodeVO getCommonCodeVO() {
		return commonCodeVO;
	}
	public void setCommonCodeVO(CommonCodeVO commonCodeVO) {
		this.commonCodeVO = commonCodeVO;
	}
	public String getPrjId() {
		return prjId;
	}
	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
	
	
	

}
