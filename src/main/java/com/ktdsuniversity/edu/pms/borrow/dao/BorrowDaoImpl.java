package com.ktdsuniversity.edu.pms.borrow.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.borrow.vo.SearchBorrowVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

@Repository
public class BorrowDaoImpl extends SqlSessionDaoSupport implements BorrowDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getBorrowCount(EmployeeVO employeeVO) {
		return getSqlSession().selectOne(BorrowDao.NAME_SPACE+".getBorrowCount", employeeVO.getEmpId());
	}

	@Override
	public List<BorrowVO> getUserRentalState(EmployeeVO employeeVO) {
		return getSqlSession().selectList(BorrowDao.NAME_SPACE+".getUserRentalState", employeeVO.getEmpId());
	}
	
	@Override
	public int searchBorrowAllCount(SearchBorrowVO searchBorrowVO) {
		return getSqlSession().selectOne(BorrowDao.NAME_SPACE+".getBorrowCount", searchBorrowVO.getEmployeeVO().getEmpId());
	}
	
	@Override
	public List<BorrowVO> searchAllUserRentalState(SearchBorrowVO searchBorrowVO) {
		return getSqlSession().selectList(BorrowDao.NAME_SPACE+".getUserRentalState", searchBorrowVO.getEmployeeVO().getEmpId());
	}

	@Override
	public int getProductManageStateAllCount() {
		return getSqlSession().selectOne(BorrowDao.NAME_SPACE+".getProductManageStateAllCount");
	}

	@Override
	public List<BorrowVO> getProductManageState() {
		return getSqlSession().selectList(BorrowDao.NAME_SPACE+".getProductManageState");
	}
	
	@Override
	public int searchProductManagementStateAllCount(SearchBorrowVO searchBorrowVO) {
		return getSqlSession().selectOne(BorrowDao.NAME_SPACE+".searchProductManagementStateAllCount", searchBorrowVO);
	}
	
	@Override
	public List<BorrowVO> searchAllProductManagementState(SearchBorrowVO searchBorrowVO) {
		return getSqlSession().selectList(BorrowDao.NAME_SPACE+".searchAllProductManagementState", searchBorrowVO);
	}

	@Override
	public int returnOneItem(String brrwHistId) {
		return getSqlSession().update(BorrowDao.NAME_SPACE+".returnOneItem", brrwHistId);
	}
	
	// PSH0422
	@Override
	public int getIsNotReturnCount(String dmdId) {
		return getSqlSession().selectOne(BorrowDao.NAME_SPACE + ".getIsNotReturnCount", dmdId);
	}

	@Override
	public int returnOneItemByAppr(String apprId) {
		return getSqlSession().update(BorrowDao.NAME_SPACE + ".returnOneItemByAppr", apprId);
	}

	@Override
	public int newBrrwPrdtByAppr(List<BorrowVO> borrowVOList) {
		return getSqlSession().update(BorrowDao.NAME_SPACE + ".newBrrwPrdtByAppr", borrowVOList);
	}

	@Override
	public List<BorrowVO> getUserRentalStateForAppr(EmployeeVO employeeVO) {
		System.out.println("getUserRentalStateForAppr >>>>>>>>>>>>>> HRER!!!!!!");
		return getSqlSession().selectList(BorrowDao.NAME_SPACE+".getUserRentalStateForAppr", employeeVO.getEmpId());
	}

}
