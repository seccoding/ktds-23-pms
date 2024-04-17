package com.ktdsuniversity.edu.pms.borrow.service;

import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public interface BorrowService {

	public BorrowListVO getUserRentalState(EmployeeVO employeeVO);

	public BorrowListVO getProductManageState();

	public boolean returnOneItem(String brrwHistId);
	
	

}
