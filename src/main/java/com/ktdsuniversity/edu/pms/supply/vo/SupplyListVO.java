package com.ktdsuniversity.edu.pms.supply.vo;

import java.util.List;

public class SupplyListVO {
	
	private List<SupplyVO> supplyList;
	private int supplyCnt;
	
	public List<SupplyVO> getSupplyList() {
		return supplyList;
	}
	public void setSupplyList(List<SupplyVO> supplyList) {
		this.supplyList = supplyList;
	}
	public int getSupplyCnt() {
		return supplyCnt;
	}
	public void setSupplyCnt(int supplyCnt) {
		this.supplyCnt = supplyCnt;
	}
}
