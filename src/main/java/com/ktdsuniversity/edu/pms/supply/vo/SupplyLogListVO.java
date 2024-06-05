package com.ktdsuniversity.edu.pms.supply.vo;

import java.util.List;

public class SupplyLogListVO {
	
	private List<SupplyLogVO> supplyLogList;
	private int supplyLogCnt;
	
	public List<SupplyLogVO> getSupplyLogList() {
		return supplyLogList;
	}
	public void setSupplyLogList(List<SupplyLogVO> supplyLogList) {
		this.supplyLogList = supplyLogList;
	}
	public int getSupplyLogCnt() {
		return supplyLogCnt;
	}
	public void setSupplyLogCnt(int supplyLogCnt) {
		this.supplyLogCnt = supplyLogCnt;
	}
}
