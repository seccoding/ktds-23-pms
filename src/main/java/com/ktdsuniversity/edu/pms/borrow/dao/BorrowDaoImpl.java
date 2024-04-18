package com.ktdsuniversity.edu.pms.borrow.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
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
	public int getProductManageStateAllCount(ProductVO productVO) {
		return getSqlSession().selectOne(BorrowDao.NAME_SPACE+".getProductManageStateAllCount", productVO);
	}

	@Override
	public List<BorrowVO> getProductManageState(ProductVO productVO) {
		return getSqlSession().selectList(BorrowDao.NAME_SPACE+".getProductManageState", productVO);
	}

	@Override
	public int returnOneItem(String brrwHistId) {
		return getSqlSession().update(BorrowDao.NAME_SPACE+".returnOneItem", brrwHistId);
	}

}
