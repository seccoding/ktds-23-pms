package com.ktdsuniversity.edu.pms.issue.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.issue.vo.IssueReplyVO;

public interface IssueReplyDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.issue.dao.IssueReplyDao";
	
	public List<IssueReplyVO> getAllReplies(IssueReplyVO issueReplyVO);
	
	public IssueReplyVO getOneReply(String replyId);
	
	public int createNewIssueReply(IssueReplyVO issueReplyVO);
	
	public int deleteOneIssueReply(String replyId);
	
	public int modifyOneIssueReply(IssueReplyVO issueReplyVO);
}
