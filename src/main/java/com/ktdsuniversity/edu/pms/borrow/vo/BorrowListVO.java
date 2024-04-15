package com.ktdsuniversity.edu.pms.borrow.vo;

import java.util.List;

public class BorrowListVO {
	
	private int borrowCnt;
	
	private List<BorrowVO> borrowList;

	public int getBorrowCnt() {
		return borrowCnt;
	}

	public void setBorrowCnt(int borrowCnt) {
		this.borrowCnt = borrowCnt;
	}

	public List<BorrowVO> getBorrowList() {
		return borrowList;
	}

	public void setBorrowList(List<BorrowVO> borrowList) {
		this.borrowList = borrowList;
	}
	
	

}
