package com.ktdsuniversity.edu.pms.issue.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.issue.vo.IssueReplyVO;
import com.ktdsuniversity.edu.pms.issue.vo.SearchIssueReplyVO;

@Repository
public class IssueReplyDaoImpl extends SqlSessionDaoSupport implements IssueReplyDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<IssueReplyVO> getAllReplies(SearchIssueReplyVO searchIssueReplyVO) {
		return getSqlSession().selectList(IssueReplyDao.NAME_SPACE + ".getAllReplies", searchIssueReplyVO);
	}

	@Override
	public IssueReplyVO getOneReply(String replyId) {
		return getSqlSession().selectOne(IssueReplyDao.NAME_SPACE + ".getOneReply", replyId);
	}

	@Override
	public int createNewIssueReply(String replyId) {
		return getSqlSession().insert(IssueReplyDao.NAME_SPACE + ".createNewIssueReply", replyId);
	}

	@Override
	public int deleteOneIssueReply(String replyId) {
		return getSqlSession().update(IssueReplyDao.NAME_SPACE + ".deleteOneIssueReply", replyId);
	}

	@Override
	public int modifyOneIssueReply(IssueReplyVO issueReplyVO) {
		return getSqlSession().update(IssueReplyDao.NAME_SPACE + ".modifyOneIssueReply", issueReplyVO);
	}

	@Override
	public int recommendOneIssueReply(String replyId) {
		return getSqlSession().update(IssueReplyDao.NAME_SPACE + ".recommendOneIssueReply", replyId);
	}
}
