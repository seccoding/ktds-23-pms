package com.ktdsuniversity.edu.pms.issue.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.issue.vo.IssueVO;

@Repository
public class IssueDaoImpl extends SqlSessionDaoSupport implements IssueDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getAllIssueCount() {
		return getSqlSession().selectOne(IssueDao.NAME_SPACE + ".getAllIssueCount");
	}

	@Override
	public List<IssueVO> getAllIssue() {
		return getSqlSession().selectList(IssueDao.NAME_SPACE + ".getAllIssue");
	}
}
