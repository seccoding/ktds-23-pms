package com.ktdsuniversity.edu.pms.rentalsupply.vo;

import java.util.List;

public class RentalSupplyApprovalListVO {
	
	private List<RentalSupplyApprovalVO> rentalSupplyApprovalList;
	private int rentalSupplyApprovalCnt;
	
	public List<RentalSupplyApprovalVO> getRentalSupplyApprovalList() {
		return rentalSupplyApprovalList;
	}
	public void setRentalSupplyApprovalList(List<RentalSupplyApprovalVO> rentalSupplyApprovalList) {
		this.rentalSupplyApprovalList = rentalSupplyApprovalList;
	}
	public int getRentalSupplyApprovalCnt() {
		return rentalSupplyApprovalCnt;
	}
	public void setRentalSupplyApprovalCnt(int rentalSupplyApprovalCnt) {
		this.rentalSupplyApprovalCnt = rentalSupplyApprovalCnt;
	}

}
