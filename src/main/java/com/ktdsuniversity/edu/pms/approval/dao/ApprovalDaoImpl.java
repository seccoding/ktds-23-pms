package com.ktdsuniversity.edu.pms.approval.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;

@Repository
public class ApprovalDaoImpl extends SqlSessionDaoSupport implements ApprovalDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getAllCount() {
		return getSqlSession().selectOne(ApprovalDao.NAME_SPACE + ".getAllCount");
	}

	@Override
	public List<ApprovalVO> getAllApproval() {
		return getSqlSession().selectList(ApprovalDao.NAME_SPACE + ".getAllApproval");
	}

	@Override
	public ApprovalVO getOneApproval(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(ApprovalDao.NAME_SPACE +".getOneApproval", id);
	}

	@Override
	public int deleteApproval(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().update(ApprovalDao.NAME_SPACE +".deleteApproval", id);
	}

	
	
}
