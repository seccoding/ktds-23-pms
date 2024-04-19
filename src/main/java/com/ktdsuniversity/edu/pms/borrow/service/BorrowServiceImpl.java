package com.ktdsuniversity.edu.pms.borrow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.borrow.dao.BorrowDao;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.product.dao.ProductDao;
import com.ktdsuniversity.edu.pms.product.dao.ProductManagementDao;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

@Service
public class BorrowServiceImpl implements BorrowService{

	@Autowired
	private BorrowDao borrowDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductManagementDao productManagementDao;
	
	@Override
	public BorrowListVO getUserRentalState(EmployeeVO employeeVO) {
		int borrowCount = this.borrowDao.getBorrowCount(employeeVO);
		List<BorrowVO> borrowList = this.borrowDao.getUserRentalState(employeeVO);
		
		BorrowListVO borrowListVO = new BorrowListVO();
		borrowListVO.setBorrowCnt(borrowCount);
		borrowListVO.setBorrowList(borrowList);
		
		return borrowListVO;
	}

	@Override
	public BorrowListVO getProductManageState(ProductVO productVO) {
		int borrowCount = this.borrowDao.getProductManageStateAllCount(productVO);
		
		productVO.setPageCount(borrowCount);
		
		List<BorrowVO> borrowList = this.borrowDao.getProductManageState(productVO);
		
		BorrowListVO borrowListVO = new BorrowListVO();
		borrowListVO.setBorrowCnt(borrowCount);
		borrowListVO.setBorrowList(borrowList);
		
		return borrowListVO;
	}

	@Transactional
	@Override
	public boolean returnOneItem(BorrowVO borrowVO) {
		int returnStateChangeCnt = this.borrowDao.returnOneItem(borrowVO.getBrrwHistId()) ;
		int changeStateCnt = this.productManagementDao.changeOneItemBrrwState(borrowVO.getPrdtMngId());
		String prdtId = this.productManagementDao.getProductId(borrowVO.getPrdtMngId());
		int changeProductCnt =  this.productDao.changeOneProductCnt(prdtId);
		return  returnStateChangeCnt> 0 && changeStateCnt > 0 && changeProductCnt > 0  ;
	}

}
