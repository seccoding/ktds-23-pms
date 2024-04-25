package com.ktdsuniversity.edu.pms.qna.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.qna.vo.QnaReplyVO;

public interface QnaReplyService {

	public List<QnaReplyVO> getAllReplies(QnaReplyVO qnaReplyVO);
	
	public boolean createNewReply(QnaReplyVO qnaReplyVO);
	
	public boolean deleteOneReply(String rplId, String empId);
	
	public boolean modifyOneReply(QnaReplyVO qnaReplyVO);
}
