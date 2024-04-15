package com.ktdsuniversity.edu.pms.borrow.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public interface BorrowDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.borrow.dao.BorrowDao";

	/**
	 * 사용자의 미반납 상태 비품 개수를 조회한다.
	 * @param employeeVO
	 * @return
	 */
	public int getBorrowCount(EmployeeVO employeeVO);

	public List<BorrowVO> getUserRentalState(EmployeeVO employeeVO);

	public int getProductManageStateAllCount();

	public List<BorrowVO> getProductManageState();

}
