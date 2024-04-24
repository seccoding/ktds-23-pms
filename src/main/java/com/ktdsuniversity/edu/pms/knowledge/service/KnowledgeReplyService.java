package com.ktdsuniversity.edu.pms.knowledge.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeReplyVO;

public interface KnowledgeReplyService {

	public List<KnowledgeReplyVO> getAllReplies(KnowledgeReplyVO knowledgeReplyVO);
	
	public boolean createNewReply(KnowledgeReplyVO knowledgeReplyVO);
	
	public boolean deleteOneReply(String rplId, String empId);
	
	public boolean modifyOneReply(KnowledgeReplyVO knowledgeReplyVO);
}
