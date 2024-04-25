package com.ktdsuniversity.edu.pms.qna.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.qna.vo.QnaReplyVO;

public interface QnaReplyDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.qna.dao.QnaReplyDao";
	
	public List<QnaReplyVO> getAllReplies(QnaReplyVO qnaReplyVO);
	
	public QnaReplyVO getOneReply(String replyId);
	
	public int createNewQnaReply(QnaReplyVO qnaReplyVO);
	
	public int deleteOneQnaReply(String replyId);
	
	public int modifyOneQnaReply(QnaReplyVO qnaReplyVO);
}
