package com.ktdsuniversity.edu.pms.employee.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;

@Repository
public class EmployeeDaoImpl extends SqlSessionDaoSupport implements EmployeeDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getAllEmployeeCount() {

		return getSqlSession().selectOne(EmployeeDao.NAME_SPACE + ".getAllEmployeeCount");
	}

	@Override
	public int searchEmployeeAllCount(SearchEmployeeVO searchEmployeeVO) {

		return getSqlSession().selectOne(EmployeeDao.NAME_SPACE + ".searchEmployeeAllCount", searchEmployeeVO);
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
	public int deleteEmployeeById(String empId) {

		return getSqlSession().update(EmployeeDao.NAME_SPACE + ".deleteEmployeeByName", empId);
	}

	@Override
	public EmployeeVO getOneEmployee(String empId) {

		return getSqlSession().selectOne(EmployeeDao.NAME_SPACE + ".getOneEmployee", empId);
	}

//	@Override
//	public int insertNewEmployee(EmployeeVO employeeVO) {
//		
//		return getSqlSession().insert(EmployeeDao.NAME_SPACE + ".createNewEmployee", employeeVO);
//	}

	@Override
	public int modifyEmployee(EmployeeVO employeeVO) {

		return getSqlSession().update(EmployeeDao.NAME_SPACE + ".modifyEmployee", employeeVO);
	}

	public int createEmployee(EmployeeVO employeeVO) {
		return getSqlSession().insert(EmployeeDao.NAME_SPACE + ".createEmployee", employeeVO);
	}

	@Override
	public EmployeeVO selectOneBoard(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeVO> searchEmpDeptId(SearchEmployeeVO searchEmployeeVO) {
		
		return getSqlSession().selectList(EmployeeDao.NAME_SPACE + ".searchEmpDeptId"
				, searchEmployeeVO);
	}

}
