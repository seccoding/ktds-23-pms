package com.ktdsuniversity.edu.pms.rentalsupply.vo;

import java.util.List;

public class RentalSupplyLogListVO {
	
	private List<RentalSupplyLogVO> rentalSupplyLogList;
	private int RentalSupplyLogCnt;
	
	public List<RentalSupplyLogVO> getRentalSupplyLogList() {
		return rentalSupplyLogList;
	}
	public void setRentalSupplyLogList(List<RentalSupplyLogVO> rentalSupplyLogList) {
		this.rentalSupplyLogList = rentalSupplyLogList;
	}
	public int getRentalSupplyLogCnt() {
		return RentalSupplyLogCnt;
	}
	public void setRentalSupplyLogCnt(int rentalSupplyLogCnt) {
		RentalSupplyLogCnt = rentalSupplyLogCnt;
	}
	
}
