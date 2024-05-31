package com.ktdsuniversity.edu.pms.employee.vo;

public class PaHistoryVO {

	// 변경 사원의 고유번호
	private String empId;
	// 인사변경 이력 고유번호
	private String paHistId;
	// 1: 직급 2: 부서 3: 직무 (공통코드로 관리)
	private String paFlag;
	// 변경 사유
	private String paChgNote;
	// 변경일
	private String paChgDt;
	// 변경 이력 삭제여부
	private String delYn;
	
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getPaHistId() {
		return paHistId;
	}
	public void setPaHistId(String paHistId) {
		this.paHistId = paHistId;
	}
	public String getPaFlag() {
		return paFlag;
	}
	public void setPaFlag(String paFlag) {
		this.paFlag = paFlag;
	}
	public String getPaChgNote() {
		return paChgNote;
	}
	public void setPaChgNote(String paChgNote) {
		this.paChgNote = paChgNote;
	}
	public String getPaChgDt() {
		return paChgDt;
	}
	public void setPaChgDt(String paChgDt) {
		this.paChgDt = paChgDt;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
	
	
}
