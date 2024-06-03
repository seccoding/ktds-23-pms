package com.ktdsuniversity.edu.pms.deptteam.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;

@Repository
public class DeptTeamDaoImpl extends SqlSessionDaoSupport implements DeptTeamDao{
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getDepartmentCount() {
		return getSqlSession().selectOne(DeptTeamDao.NAME_SPACE + ".getDepartmentCount");
	}

	@Override
	public List<DepartmentVO> getAllDepartment() {
		return getSqlSession().selectList(DeptTeamDao.NAME_SPACE + ".getAllDepartment");
	}
	
	
}
