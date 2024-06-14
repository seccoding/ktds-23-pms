package com.ktdsuniversity.edu.pms.issue.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.issue.vo.IssueVO;
import com.ktdsuniversity.edu.pms.issue.vo.SearchIssueVO;

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

	@Override
	public List<IssueVO> searchAllIssue(SearchIssueVO searchIssueVO) {
		return getSqlSession().selectList(IssueDao.NAME_SPACE + ".searchAllIssue", searchIssueVO);
	}
	
	@Override
	public int searchIssueAllCount(SearchIssueVO searchIssueVO) {
		return getSqlSession().selectOne(IssueDao.NAME_SPACE + ".searchIssueAllCount", searchIssueVO);
	}
	
	@Override
	public IssueVO selectOneIssue(String isId) {
		return getSqlSession().selectOne(IssueDao.NAME_SPACE + ".selectOneIssue", isId);
	}

	@Override
	public int increaseViewCount(String isId) {
		return getSqlSession().update(IssueDao.NAME_SPACE + ".increaseViewCount", isId);
	}

	@Override
	public int insertNewIssue(IssueVO issueVO) {
		return getSqlSession().insert(IssueDao.NAME_SPACE + ".insertNewIssue", issueVO);
	}

	@Override
	public int updateOneIssue(IssueVO issueVO) {
		return getSqlSession().update(IssueDao.NAME_SPACE + ".updateOneIssue", issueVO);
	}

	@Override
	public int deleteOneIssue(String isId) {
		return getSqlSession().update(IssueDao.NAME_SPACE + ".deleteOneIssue", isId);
	}

	@Override
	public List<IssueVO> selectManyIssue(List<String> deleteItems) {
		return getSqlSession().selectList(IssueDao.NAME_SPACE + ".selectManyIssue", deleteItems);
	}

	@Override
	public int deleteManyIssue(List<String> deleteItems) {
		return getSqlSession().update(IssueDao.NAME_SPACE + ".deleteManyIssue", deleteItems);
	}

	@Override
	public List<IssueVO> searchIssueByPrjId(String prjId) {
		return getSqlSession().selectList(IssueDao.NAME_SPACE+".searchIssueByPrjId", prjId);
	}
}
