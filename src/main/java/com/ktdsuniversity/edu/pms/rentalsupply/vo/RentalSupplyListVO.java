package com.ktdsuniversity.edu.pms.rentalsupply.vo;

import java.util.List;

public class RentalSupplyListVO {

	private List<RentalSupplyVO> rentalSupplyList;
	private int rentalSupplyCnt;

	public List<RentalSupplyVO> getRentalSupplyList() {
		return rentalSupplyList;
	}

	public void setRentalSupplyList(List<RentalSupplyVO> rentalSupplyList) {
		this.rentalSupplyList = rentalSupplyList;
	}

	public int getRentalSupplyCnt() {
		return rentalSupplyCnt;
	}

	public void setRentalSupplyCnt(int rentalSupplyCnt) {
		this.rentalSupplyCnt = rentalSupplyCnt;
	}

}
