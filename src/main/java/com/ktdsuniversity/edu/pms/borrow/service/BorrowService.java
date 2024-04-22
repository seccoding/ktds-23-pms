package com.ktdsuniversity.edu.pms.borrow.service;

import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

public interface BorrowService {

	public BorrowListVO getUserRentalState(EmployeeVO employeeVO);

	public BorrowListVO getProductManageState(ProductVO productVO);

	public boolean returnOneItem(BorrowVO borrowVO);
	
	// PSH0422
	public boolean getIsNotReturnCount(String dmdId);

	public BorrowListVO getUserRentalStateForAppr(EmployeeVO employeeVO);

}
