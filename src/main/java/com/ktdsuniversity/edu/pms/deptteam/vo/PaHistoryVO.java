package com.ktdsuniversity.edu.pms.deptteam.vo;

public class PaHistoryVO {

	// 인사 변경 이력 고유번호
	private String paHistId;
	// 변경 사원 고유번호
	private String empId;
	// 변경 사유
	private String paChgNote;
	// 변경이력 삭제여부
	private String delYn;
	// 인사 구분자 직급 부서 팀 직무 등
	private String paFlag;
	// 인사 변경 날짜
	private String paChgDt;
	// 이전 부서 고유번호
	private String preDeptId;
	// 변경 부서 고유번호
	private String paDeptId;
	// 이전 팀 고유번호
	private String preTmId;
	// 변경 팀 고유번호
	private String paTmId;
	// 이전 직급 고유번호
	private String prePstnId;
	// 변경 직급 고유번호
	private String paPstnId;
	// 이전 직무 고유번호
	private String preJobId;
	// 변경 직무 고유번호
	private String paJobId;

	public String getPaHistId() {
		return paHistId;
	}

	public void setPaHistId(String paHistId) {
		this.paHistId = paHistId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getPaChgNote() {
		return paChgNote;
	}

	public void setPaChgNote(String paChgNote) {
		this.paChgNote = paChgNote;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getPaFlag() {
		return paFlag;
	}

	public void setPaFlag(String paFlag) {
		this.paFlag = paFlag;
	}

	public String getPaChgDt() {
		return paChgDt;
	}

	public void setPaChgDt(String paChgDt) {
		this.paChgDt = paChgDt;
	}

	public String getPreDeptId() {
		return preDeptId;
	}

	public void setPreDeptId(String preDeptId) {
		this.preDeptId = preDeptId;
	}

	public String getPaDeptId() {
		return paDeptId;
	}

	public void setPaDeptId(String paDeptId) {
		this.paDeptId = paDeptId;
	}

	public String getPreTmId() {
		return preTmId;
	}

	public void setPreTmId(String preTmId) {
		this.preTmId = preTmId;
	}

	public String getPaTmId() {
		return paTmId;
	}

	public void setPaTmId(String paTmId) {
		this.paTmId = paTmId;
	}

	public String getPrePstnId() {
		return prePstnId;
	}

	public void setPrePstnId(String prePstnId) {
		this.prePstnId = prePstnId;
	}

	public String getPaPstnId() {
		return paPstnId;
	}

	public void setPaPstnId(String paPstnId) {
		this.paPstnId = paPstnId;
	}

	public String getPreJobId() {
		return preJobId;
	}

	public void setPreJobId(String preJobId) {
		this.preJobId = preJobId;
	}

	public String getPaJobId() {
		return paJobId;
	}

	public void setPaJobId(String paJobId) {
		this.paJobId = paJobId;
	}

}