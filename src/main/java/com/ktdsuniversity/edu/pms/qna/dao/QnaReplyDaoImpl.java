package com.ktdsuniversity.edu.pms.qna.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.qna.vo.QnaReplyVO;

@Repository
public class QnaReplyDaoImpl extends SqlSessionDaoSupport implements QnaReplyDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<QnaReplyVO> getAllReplies(QnaReplyVO qnaReplyVO) {
		return getSqlSession().selectList(QnaReplyDao.NAME_SPACE + ".getAllReplies", qnaReplyVO);
	}

	@Override
	public QnaReplyVO getOneReply(String replyId) {
		return getSqlSession().selectOne(QnaReplyDao.NAME_SPACE + ".getOneReply", replyId);
	}

	@Override
	public int createNewQnaReply(QnaReplyVO qnaReplyVO) {
		return getSqlSession().insert(QnaReplyDao.NAME_SPACE + ".createNewQnaReply", qnaReplyVO);
	}

	@Override
	public int deleteOneQnaReply(String replyId) {
		return getSqlSession().update(QnaReplyDao.NAME_SPACE + ".deleteOneQnaReply", replyId);
	}

	@Override
	public int modifyOneQnaReply(QnaReplyVO qnaReplyVO) {
		return getSqlSession().update(QnaReplyDao.NAME_SPACE + ".modifyOneQnaReply", qnaReplyVO);
	}

}
