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

}
