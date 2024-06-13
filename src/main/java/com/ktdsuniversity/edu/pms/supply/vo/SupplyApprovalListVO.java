package com.ktdsuniversity.edu.pms.supply.vo;

import java.util.List;

public class SupplyApprovalListVO {
	
	private List<SupplyApprovalVO> supplyApprovalList;
	private int supplyApprovalCnt;
	
	public List<SupplyApprovalVO> getSupplyApprovalList() {
		return supplyApprovalList;
	}
	public void setSupplyApprovalList(List<SupplyApprovalVO> supplyApprovalList) {
		this.supplyApprovalList = supplyApprovalList;
	}
	public int getSupplyApprovalCnt() {
		return supplyApprovalCnt;
	}
	public void setSupplyApprovalCnt(int supplyApprovalCnt) {
		this.supplyApprovalCnt = supplyApprovalCnt;
	}

}
