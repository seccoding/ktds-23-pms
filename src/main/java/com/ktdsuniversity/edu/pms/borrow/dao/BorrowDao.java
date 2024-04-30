package com.ktdsuniversity.edu.pms.borrow.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.borrow.vo.SearchBorrowVO;
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

	public int searchBorrowAllCount(SearchBorrowVO searchBorrowVO);

	public List<BorrowVO> searchAllUserRentalState(SearchBorrowVO searchBorrowVO);
	
	
	public int searchProductManagementStateAllCount(SearchBorrowVO searchBorrowVO);

	public List<BorrowVO> searchAllProductManagementState(SearchBorrowVO searchBorrowVO);
	

	public int returnOneItem(String brrwHistId);
	
	// PSH0422
	public int getIsNotReturnCount(String dmdId);

    public int returnOneItemByAppr(String apprId);

    public int newBrrwPrdtByAppr(List<BorrowVO> borrowVOList);

//	public List<BorrowVO> getUserRentalStateForAppr(EmployeeVO employeeVO);
	
	public List<BorrowVO> getUserRentalNotAppr(EmployeeVO employeeVO);
	
	public List<BorrowVO> getBorrowProduct(List<String> addProducts);

	
	// YSH0424
	
	/**
	 * 대여이력 ID의 시퀀스 값을 가져온다.
	 * @return
	 */
	public String selectBrrwHistId();

	/**
	 * 대여이력에 
	 * @param borrowVO
	 * @return
	 */
	public int insertNewBorrowHist(BorrowVO borrowVO);

	public List<BorrowVO> searchProductManageStateNotReturn(SearchBorrowVO searchBorrowVO);

	public int getUserListCnt(SearchBorrowVO searchBorrowVO);

	public int getAllListCnt(SearchBorrowVO searchBorrowVO);

	public int changeState(String prdtMngId);

	public List<BorrowVO> getUserRentalStateForMain(String empId);


	

}
