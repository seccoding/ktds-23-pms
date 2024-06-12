package com.ktdsuniversity.edu.pms.department.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

@Repository
public class DepartmentDaoImpl extends SqlSessionDaoSupport implements DepartmentDao{


	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getDepartmentCount() {
		return getSqlSession().selectOne(DepartmentDao.NAME_SPACE + ".getDepartmentCount");
	}
	
	@Override
	public List<DepartmentVO> getAllDepartment() {
		return getSqlSession().selectList(DepartmentDao.NAME_SPACE + ".getAllDepartment");
	}

	@Override
	public int createNewDepartment(DepartmentVO departmentVO) {
		return getSqlSession().insert(DepartmentDao.NAME_SPACE + ".createNewDepartment", departmentVO);
	}

	@Override
	public int deleteOneDepartment(String id) {
		return getSqlSession().update(DepartmentDao.NAME_SPACE + ".deleteOneDepartment", id);
	}

	@Override
	public int updateOneDepartment(DepartmentVO departmentVO) {
		return getSqlSession().update(DepartmentDao.NAME_SPACE + ".updateOneDepartment", departmentVO);
	}

	@Override
	public List<DepartmentVO> getOnlyDepartment(String deptId) {
		return getSqlSession().selectList(DepartmentDao.NAME_SPACE + ".getOnlyDepartment", deptId);
	}

	@Override
	public DepartmentVO getOneDepartment(String departmentId) {
		return getSqlSession().selectOne(DepartmentDao.NAME_SPACE + ".getOneDepartment", departmentId);
	}

	@Override
	public String getDepartmentNameById(String deptId) {
		return getSqlSession().selectOne(DepartmentDao.NAME_SPACE + ".getDepartmentNameById", deptId);
	}
	

	@Override
	public int getDepartment(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(DepartmentDao.NAME_SPACE+".getDepartment", id);
	}

	@Override
	public String getOnlypstnid(String pstnid) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(DepartmentDao.NAME_SPACE+".getOnlypstnid", pstnid);
	}


	@Override
	public List<EmployeeVO> getEmpByDeptId(String deptId) {
		return getSqlSession().selectList(DepartmentDao.NAME_SPACE+".getEmpByDeptId", deptId);
	}

	@Override
	public String getDeptIdByName(String deptName) {
		return getSqlSession().selectOne(DepartmentDao.NAME_SPACE+".getDeptIdByName", deptName);
	}



}
