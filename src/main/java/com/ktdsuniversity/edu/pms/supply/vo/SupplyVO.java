package com.ktdsuniversity.edu.pms.supply.vo;

public class SupplyVO {
	
	private String splId;
	private String splName;
	private String splCtgr;
	private int splPrice;
	private int invQty;
	private String delYn;
	
	public String getSplId() {
		return splId;
	}
	public void setSplId(String splId) {
		this.splId = splId;
	}
	public String getSplName() {
		return splName;
	}
	public void setSplName(String splName) {
		this.splName = splName;
	}
	public String getSplCtgr() {
		return splCtgr;
	}
	public void setSplCtgr(String splCtgr) {
		this.splCtgr = splCtgr;
	}
	public int getSplPrice() {
		return splPrice;
	}
	public void setSplPrice(int splPrice) {
		this.splPrice = splPrice;
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
