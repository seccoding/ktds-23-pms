package com.ktdsuniversity.edu.pms.borrow.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

public interface BorrowDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.borrow.dao.BorrowDao";

	/**
	 * 사용자의 미반납 상태 비품 개수를 조회한다.
	 * @param employeeVO
	 * @return
	 */
	public int getBorrowCount(EmployeeVO employeeVO);

	public List<BorrowVO> getUserRentalState(EmployeeVO employeeVO);

	public int getProductManageStateAllCount(ProductVO productVO);

	public List<BorrowVO> getProductManageState(ProductVO productVO);

	public int returnOneItem(String brrwHistId);
	
	// PSH0422
	public int getIsNotReturnCount(String dmdId);

    public int returnOneItemByAppr(String apprId);

    public int newBrrwPrdtByAppr(List<BorrowVO> borrowVOList);

	public List<BorrowVO> getUserRentalStateForAppr(EmployeeVO employeeVO);

}
