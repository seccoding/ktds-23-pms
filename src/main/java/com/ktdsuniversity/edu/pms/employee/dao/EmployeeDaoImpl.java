package com.ktdsuniversity.edu.pms.employee.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;

@Repository
public class EmployeeDaoImpl extends SqlSessionDaoSupport implements EmployeeDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getAllEmployeeCount() {
	
		return getSqlSession().selectOne(EmployeeDao.NAME_SPACE + ".getEmployeeAllCount");
	}

	@Override
	public int searchEmployeeAllCount(SearchEmployeeVO searchEmployeeVO) {
			
		return getSqlSession().selectOne(EmployeeDao.NAME_SPACE + ".searchEmployeeAllCount", searchEmployeeVO)  ;
	}

	@Override
	public List<EmployeeVO> getAllEmployee() {
		
		return getSqlSession().selectList(EmployeeDao.NAME_SPACE + ".getAllEmployee");
	}

	@Override
	public List<EmployeeVO> searchAllEmployee(SearchEmployeeVO searchEmployeeVO) {
		
		return getSqlSession().selectList(EmployeeDao.NAME_SPACE + ".searchAllEmployee", searchEmployeeVO);
	}

	@Override
	public EmployeeVO selectOneBoard(int id) {
		
		return getSqlSession().selectOne(EmployeeDao.NAME_SPACE + ".selectOneBoard", id);
	}

	@Override
	public int createEmployee(EmployeeVO employeeVO) {
		return getSqlSession().insert(EmployeeDao.NAME_SPACE + ".createEmployee", employeeVO);
	}

	
	
	

}
