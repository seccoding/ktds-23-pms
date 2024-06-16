package com.ktdsuniversity.edu.pms.department.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentApprovalVO;

@Repository
public class DepartmentApprovalDaoImpl extends SqlSessionDaoSupport implements DepartmentApprovalDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	// 매퍼 만들어야됨 인서트
	@Override
	public int insertDepartmentApprovalRequest(DepartmentApprovalVO departmentApprovalVO) {
		return getSqlSession().insert(DepartmentApprovalDao.NAME_SPACE + ".insertDepartmentApprovalRequest", departmentApprovalVO);
	}

	// 한가지만 가져오는 것
	@Override
	public DepartmentApprovalVO getDepartmentApprovalByPK(String deptApprId) {
		return getSqlSession().selectOne(DepartmentApprovalDao.NAME_SPACE + ".getDepartmentApprovalByPK", deptApprId);
	}
}
