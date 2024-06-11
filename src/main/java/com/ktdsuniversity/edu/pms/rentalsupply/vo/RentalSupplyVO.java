package com.ktdsuniversity.edu.pms.rentalsupply.vo;

public class RentalSupplyVO {

	// 대여품 고유번호
	private String rsplId;
	// 대여품 명
	private String rsplName;
	// 대여품 카테고리
	private String rsplCtgr;
	// 대여품 가격
	private int rsplPrice;
	// 대여품 재고개수
	private int invQty;
	// 대여품 삭제여부
	private String delYn;
	// 대여품 이미지 파일 명
	private String rsplImg;
	// 대여품 상세 정보
	private String rsplDtl;
	// 대여품 등록자 아이디
	private String rsplRegtId;
	// 대여품 수정자 아이디
	private String rsplMdfrId;
	// 대여품 등록일
	private String rsplRegtDt;
	// 대여품 수정일
	private String rsplMdfDt;

	public String getRsplId() {
		return rsplId;
	}

	public void setRsplId(String rsplId) {
		this.rsplId = rsplId;
	}

	public String getRsplName() {
		return rsplName;
	}

	public void setRsplName(String rsplName) {
		this.rsplName = rsplName;
	}

	public String getRsplCtgr() {
		return rsplCtgr;
	}

	public void setRsplCtgr(String rsplCtgr) {
		this.rsplCtgr = rsplCtgr;
	}

	public int getRsplPrice() {
		return rsplPrice;
	}

	public void setRsplPrice(int rsplPrice) {
		this.rsplPrice = rsplPrice;
	}

	public int getInvQty() {
		return invQty;
	}

	public void setInvQty(int invQty) {
		this.invQty = invQty;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getRsplImg() {
		return rsplImg;
	}

	public void setRsplImg(String rsplImg) {
		this.rsplImg = rsplImg;
	}

	public String getRsplDtl() {
		return rsplDtl;
	}

	public void setRsplDtl(String rsplDtl) {
		this.rsplDtl = rsplDtl;
	}

	public String getRsplRegtId() {
		return rsplRegtId;
	}

	public void setRsplRegtId(String rsplRegtId) {
		this.rsplRegtId = rsplRegtId;
	}

	public String getRsplMdfrId() {
		return rsplMdfrId;
	}

	public void setRsplMdfrId(String rsplMdfrId) {
		this.rsplMdfrId = rsplMdfrId;
	}

	public String getRsplRegtDt() {
		return rsplRegtDt;
	}

	public void setRsplRegtDt(String rsplRegtDt) {
		this.rsplRegtDt = rsplRegtDt;
	}

	public String getRsplMdfDt() {
		return rsplMdfDt;
	}

	public void setRsplMdfDt(String rsplMdfDt) {
		this.rsplMdfDt = rsplMdfDt;
	}

}
