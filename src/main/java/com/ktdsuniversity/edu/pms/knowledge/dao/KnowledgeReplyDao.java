package com.ktdsuniversity.edu.pms.knowledge.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeReplyVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.ReplyRecommandVO;

public interface KnowledgeReplyDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeReplyDao";
	
	public List<KnowledgeReplyVO> getAllReplies(KnowledgeReplyVO knowledegeReplyVO);
	
	public KnowledgeReplyVO getOneReply(String replyId);
	
	public int createNewKnowledgeReply(KnowledgeReplyVO knowledegeReplyVO);
	
	public int deleteOneKnowledgeReply(String replyId);
	
	public int modifyOneKnowledgeReply(KnowledgeReplyVO knowledegeReplyVO);
	
	//	댓글 추천
	public ReplyRecommandVO  selectOneReplyRecommend(ReplyRecommandVO replyRecommandvo);
	
	public int insertOneReplyRecommend(ReplyRecommandVO replyRecommandvo);
	
	public int replyRecommandCount(String replyid);
	
	// 답글 
	public List<KnowledgeReplyVO> getAllreReply(KnowledgeReplyVO knowledgereplyvo);
	
	// 댓글 id 권한 설정
	public String findEmpid(String id);
	
	public int replyCount(String id);
	
}