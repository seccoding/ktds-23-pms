package com.ktdsuniversity.edu.pms.borrow.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.borrow.vo.SearchBorrowVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

public interface BorrowService {



	public BorrowListVO searchUserRentalState(SearchBorrowVO searchBorrowVO);
	


	public BorrowListVO searchProductManageState(SearchBorrowVO searchBorrowVO);
	
	public boolean returnOneItem(BorrowVO borrowVO);
	
	// PSH0422
	public boolean getIsNotReturnCount(String dmdId);

	public BorrowListVO getUserRentalStateForAppr(EmployeeVO employeeVO);


	public BorrowListVO searchProductManageStateNotReturn(SearchBorrowVO searchBorrowVO);


	public int getNotNullCnt(SearchBorrowVO searchBorrowVO);



	public int getAllCnt(SearchBorrowVO searchBorrowVO);


	public List<BorrowVO> getUserRentalState(String empId);



}
