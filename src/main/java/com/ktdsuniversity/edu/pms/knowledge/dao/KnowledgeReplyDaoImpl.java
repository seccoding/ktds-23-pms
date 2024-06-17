package com.ktdsuniversity.edu.pms.knowledge.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeReplyVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.ReplyRecommandVO;

@Repository
public class KnowledgeReplyDaoImpl extends SqlSessionDaoSupport implements KnowledgeReplyDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<KnowledgeReplyVO> getAllReplies(KnowledgeReplyVO knowledegeReplyVO) {
		return getSqlSession().selectList(KnowledgeReplyDao.NAME_SPACE + ".getAllReplies", knowledegeReplyVO);
	}

	@Override
	public KnowledgeReplyVO getOneReply(String replyId) {
		return getSqlSession().selectOne(KnowledgeReplyDao.NAME_SPACE + ".getOneReply", replyId);
	}

	@Override
	public int createNewKnowledgeReply(KnowledgeReplyVO knowledegeReplyVO) {
		return getSqlSession().insert(KnowledgeReplyDao.NAME_SPACE + ".createNewKnowledgeReply", knowledegeReplyVO);
	}

	@Override
	public int deleteOneKnowledgeReply(String replyId) {
		return getSqlSession().update(KnowledgeReplyDao.NAME_SPACE + ".deleteOneKnowledgeReply", replyId);
	}

	@Override
	public int modifyOneKnowledgeReply(KnowledgeReplyVO knowledegeReplyVO) {
		return getSqlSession().update(KnowledgeReplyDao.NAME_SPACE + ".modifyOneKnowledgeReply", knowledegeReplyVO);
	}

	@Override
	public ReplyRecommandVO selectOneReplyRecommend(ReplyRecommandVO replyRecommandvo) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(KnowledgeReplyDao.NAME_SPACE+".selectOneReplyRecommend", replyRecommandvo);
	}

	@Override
	public int insertOneReplyRecommend(ReplyRecommandVO replyRecommandvo) {
		return getSqlSession().insert(KnowledgeReplyDao.NAME_SPACE+".insertOneReplyRecommend", replyRecommandvo);
	}


	@Override
	public int replyRecommandCount(String replyid) {
		// TODO Auto-generated method stub
		return getSqlSession().update(KnowledgeReplyDao.NAME_SPACE+".replyRecommandCount", replyid);
	}

	@Override
	public List<KnowledgeReplyVO> getAllreReply(KnowledgeReplyVO knowledgereplyvo) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(KnowledgeReplyDao.NAME_SPACE+".getAllreReply", knowledgereplyvo);
	}

	@Override
	public String findEmpid(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(KnowledgeReplyDao.NAME_SPACE+".findEmpid", id);
	}

	@Override
	public int replyCount(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().update(KnowledgeReplyDao.NAME_SPACE+".replyCount", id);
	}

	
}
