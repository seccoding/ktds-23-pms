package com.ktdsuniversity.edu.pms.knowledge.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeReplyVO;

public interface KnowledgeReplyDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeReplyDao";
	
	public List<KnowledgeReplyVO> getAllReplies(KnowledgeReplyVO knowledegeReplyVO);
	
	public KnowledgeReplyVO getOneReply(String replyId);
	
	public int createNewKnowledgeReply(KnowledgeReplyVO knowledegeReplyVO);
	
	public int deleteOneKnowledgeReply(String replyId);
	
	public int modifyOneKnowledgeReply(KnowledgeReplyVO knowledegeReplyVO);
}
