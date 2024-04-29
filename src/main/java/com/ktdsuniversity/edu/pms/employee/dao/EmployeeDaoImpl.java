package com.ktdsuniversity.edu.pms.employee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

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

		return getSqlSession().update(EmployeeDao.NAME_SPACE + ".deleteEmployeeById", empId);
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
	public String saltByEmp(String empId) {
		return getSqlSession().selectOne(EmployeeDao.NAME_SPACE + ".saltByEmp", empId);
	}

	@Override
	public List<EmployeeVO> searchEmpDeptId(SearchEmployeeVO searchEmployeeVO) {
		
		return getSqlSession().selectList(EmployeeDao.NAME_SPACE + ".searchEmpDeptId"
				, searchEmployeeVO);
	}

	@Override
	public List<EmployeeVO> findEmployeesByTeamId(String teamId) {

		return getSqlSession().selectList(EmployeeDao.NAME_SPACE + ".findEmployeesByTeamId", teamId);
	}
	
	@Override
	public int getOneEmpIdIsExist(String empId) {
		return getSqlSession().selectOne(EmployeeDao.NAME_SPACE + ".getOneEmpIdIsExist", empId);
	}

	@Override
	public int modifyOneEmployee(EmployeeVO employeeVO) {
		return getSqlSession().update(EmployeeDao.NAME_SPACE + ".modifyOneEmployee", employeeVO);
	}

	@Override
	public List<TeamVO> getEmployeeAllTeam(String empId) {
		return getSqlSession().selectList(EmployeeDao.NAME_SPACE+".getEmployeeAllTeam", empId);
	}

	@Override
	public int deleteTeam(EmployeeVO employeeVO) {
		return getSqlSession().update(EmployeeDao.NAME_SPACE+".deleteTeam", employeeVO);
	}

	@Override
	public int addTeam(EmployeeVO employeeVO) {
		return getSqlSession().insert(EmployeeDao.NAME_SPACE+".addTeam", employeeVO);
	}

	@Override
	public String getDeptIdByEmployeeId(String empId) {
		return getSqlSession().selectOne(EmployeeDao.NAME_SPACE+".getDeptIdByEmployeeId", empId);
	}

	@Override
	public List<EmployeeVO> findEmployeesByDeptID(String deptId) {
		return getSqlSession().selectList(EmployeeDao.NAME_SPACE+".findEmployeesByDeptID", deptId);
	}

	@Override
	public List<EmployeeVO> getCanBeDeptLead() {
		return getSqlSession().selectList(EmployeeDao.NAME_SPACE+".getCanBeDeptLead");
	}

	@Override
	public List<EmployeeVO> getChangeToDeptLead(String departmentId) {
		return getSqlSession().selectList(EmployeeDao.NAME_SPACE+".getChangeToDeptLead", departmentId);
	}

	@Override
	public int modifyEmployeeJob(EmployeeVO employeeVO) {
		return getSqlSession().update(EmployeeDao.NAME_SPACE + ".modifyEmployeeJob", employeeVO);
	}

	@Override
	public int modifyEmployeePosition(EmployeeVO employeeVO) {
		return getSqlSession().update(EmployeeDao.NAME_SPACE+".modifyEmployeePosition", employeeVO);

	}

	@Override
	public int modifyEmployeeDept(EmployeeVO employeeVO) {
		return getSqlSession().update(EmployeeDao.NAME_SPACE+".modifyEmployeeDept", employeeVO);
	}

	

}
