package com.ktdsuniversity.edu.pms.borrow.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.borrow.vo.SearchBorrowVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.product.dao.ProductDao;
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
	public int searchBorrowAllCount(SearchBorrowVO searchBorrowVO) {
		return getSqlSession().selectOne(BorrowDao.NAME_SPACE+".searchBorrowAllCount", searchBorrowVO);
	}
	
	@Override
	public List<BorrowVO> searchAllUserRentalState(SearchBorrowVO searchBorrowVO) {
		return getSqlSession().selectList(BorrowDao.NAME_SPACE+".searchAllUserRentalState", searchBorrowVO);
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

	// 대여중인 전체 비품 목록
//	@Override
//	public List<BorrowVO> getUserRentalStateForAppr(EmployeeVO employeeVO) {
//		return getSqlSession().selectList(BorrowDao.NAME_SPACE+".getUserRentalStateForAppr", employeeVO.getEmpId());
//	}
	
	// 반납신청 한 비품을 제외한 비품 목록
	@Override
	public List<BorrowVO> getUserRentalNotAppr(EmployeeVO employeeVO) {
		return getSqlSession().selectList(BorrowDao.NAME_SPACE+".getUserRentalNotAppr", employeeVO.getEmpId());
	}

	@Override
	public List<BorrowVO> getBorrowProduct(List<String> addProducts) {
		return getSqlSession().selectList(BorrowDao.NAME_SPACE + ".getBorrowProduct", addProducts);
	}

	// YSH0424
	@Override
	public String selectBrrwHistId() {
		return getSqlSession().selectOne(BorrowDao.NAME_SPACE+".selectBrrwHistId");
	}

	@Override
	public int insertNewBorrowHist(BorrowVO borrowVO) {
		return getSqlSession().insert(BorrowDao.NAME_SPACE+".insertNewBorrowHist", borrowVO);
	}

	@Override
	public List<BorrowVO> searchProductManageStateNotReturn(SearchBorrowVO searchBorrowVO) {
		return getSqlSession().selectList(BorrowDao.NAME_SPACE+".searchProductManageStateNotReturn", searchBorrowVO);
	}

	@Override
	public int getUserListCnt(SearchBorrowVO searchBorrowVO) {
		return getSqlSession().selectOne(BorrowDao.NAME_SPACE+".getUserListCnt", searchBorrowVO);
	}

	@Override
	public int getAllListCnt(SearchBorrowVO searchBorrowVO) {
		return getSqlSession().selectOne(BorrowDao.NAME_SPACE+".getAllListCnt", searchBorrowVO);
	}

	@Override
	public int changeState(String prdtMngId) {
		return getSqlSession().update(BorrowDao.NAME_SPACE+".changeState", prdtMngId);
	}

	@Override
	public List<BorrowVO> getUserRentalStateForMain(String empId) {
		return getSqlSession().selectList(BorrowDao.NAME_SPACE+".getUserRentalStateForMain", empId);
	}

}
