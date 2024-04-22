package com.ktdsuniversity.edu.pms.issue.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.issue.vo.IssueReplyVO;

public interface IssueReplyService {

	public List<IssueReplyVO> getAllReplies(IssueReplyVO issueReplyVO);
	
	public boolean createNewReply(IssueReplyVO issueReplyVO);
	
	public boolean deleteOneReply(String rplId, String empId);
	
	public boolean modifyOneReply(IssueReplyVO issueReplyVO);
}
