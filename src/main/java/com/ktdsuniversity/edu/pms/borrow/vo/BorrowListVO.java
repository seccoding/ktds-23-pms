package com.ktdsuniversity.edu.pms.borrow.vo;

import java.util.List;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class BorrowListVO {
	
	private int borrowCnt;
	
	private List<BorrowVO> borrowList;
	
	private EmployeeVO employeeVO;
	

	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}

	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}

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
