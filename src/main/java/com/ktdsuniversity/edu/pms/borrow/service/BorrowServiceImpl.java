package com.ktdsuniversity.edu.pms.borrow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.borrow.dao.BorrowDao;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

@Service
public class BorrowServiceImpl implements BorrowService{

	@Autowired
	private BorrowDao borrowDao;
	
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
	public BorrowListVO getProductManageState() {
		int borrowCount = this.borrowDao.getProductManageStateAllCount();
		List<BorrowVO> borrowList = this.borrowDao.getProductManageState();
		
		BorrowListVO borrowListVO = new BorrowListVO();
		borrowListVO.setBorrowCnt(borrowCount);
		borrowListVO.setBorrowList(borrowList);
		
		return borrowListVO;
	}

	@Transactional
	@Override
	public boolean returnOneItem(String brrwHistId) {
		return this.borrowDao.returnOneItem(brrwHistId) > 0;
	}

}
