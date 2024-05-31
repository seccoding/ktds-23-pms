package com.ktdsuniversity.edu.pms.rentalsupply.vo;

public class RentalSupplyLogVO {

	// 대여품 기록 고유번호
	private String rsplLogId;
	// 대여품 고유번호
	private String rsplId;
	// 대여품 반납자 고유번호
	private String rntnId;
	// 대여품 대여자 고유번호
	private String rntlId;
	// 대여품 승인자 고유번호
	private String rntlApprId;
	// 대여품 대여일
	private String rntlDt;
	// 대여품 반납일
	private String rtrnDt;
	// 대여품 반납예정일
	private String rntnDueDt;
	// 대여품 대여사유
	private String rntlRsn;
	// 대여품 삭제여부
	private String delYn;

	public String getRsplLogId() {
		return rsplLogId;
	}

	public void setRsplLogId(String rsplLogId) {
		this.rsplLogId = rsplLogId;
	}

	public String getRsplId() {
		return rsplId;
	}

	public void setRsplId(String rsplId) {
		this.rsplId = rsplId;
	}

	public String getRntnId() {
		return rntnId;
	}

	public void setRntnId(String rntnId) {
		this.rntnId = rntnId;
	}

	public String getRntlId() {
		return rntlId;
	}

	public void setRntlId(String rntlId) {
		this.rntlId = rntlId;
	}

	public String getRntlApprId() {
		return rntlApprId;
	}

	public void setRntlApprId(String rntlApprId) {
		this.rntlApprId = rntlApprId;
	}

	public String getRntlDt() {
		return rntlDt;
	}

	public void setRntlDt(String rntlDt) {
		this.rntlDt = rntlDt;
	}

	public String getRtrnDt() {
		return rtrnDt;
	}

	public void setRtrnDt(String rtrnDt) {
		this.rtrnDt = rtrnDt;
	}

	public String getRntnDueDt() {
		return rntnDueDt;
	}

	public void setRntnDueDt(String rntnDueDt) {
		this.rntnDueDt = rntnDueDt;
	}

	public String getRntlRsn() {
		return rntlRsn;
	}

	public void setRntlRsn(String rntlRsn) {
		this.rntlRsn = rntlRsn;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

}
