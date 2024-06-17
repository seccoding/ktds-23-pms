package com.ktdsuniversity.edu.pms.knowledge.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeReplyVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.ReplyRecommandVO;

public interface KnowledgeReplyService {

	public List<KnowledgeReplyVO> getAllReplies(KnowledgeReplyVO knowledgeReplyVO);
	
	public boolean createNewReply(KnowledgeReplyVO knowledgeReplyVO);
	
	public boolean deleteOneReply(String rplId, String empId);
	
	public boolean modifyOneReply(KnowledgeReplyVO knowledgeReplyVO);
	
	public boolean updateReplyRecommend(ReplyRecommandVO replyRecommandvo);
	
	//재댓글	
	public List<KnowledgeReplyVO> getAllreReplies(KnowledgeReplyVO knowledgeReplyVO);
	
	public String findReplyId(String id);
	
}
