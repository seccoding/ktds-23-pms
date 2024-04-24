package com.ktdsuniversity.edu.pms.issue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.issue.dao.IssueReplyDao;
import com.ktdsuniversity.edu.pms.issue.vo.IssueReplyVO;

@Service
public class IssueReplyServiceImpl implements IssueReplyService {

	@Autowired
	private IssueReplyDao issueReplyDao;

	@Override
	public List<IssueReplyVO> getAllReplies(IssueReplyVO issueReplyVO) {
		return this.issueReplyDao.getAllReplies(issueReplyVO);
	}

	@Transactional
	@Override
	public boolean createNewReply(IssueReplyVO issueReplyVO) {
		return this.issueReplyDao.createNewIssueReply(issueReplyVO) > 0;
	}
	
	@Transactional
	@Override
	public boolean deleteOneReply(String rplId, String empId) {
		
		IssueReplyVO issueReplyVO = this.issueReplyDao.getOneReply(rplId);
		if (!empId.equals(issueReplyVO.getCrtrId())) {
			throw new PageNotFoundException();
		}
		return this.issueReplyDao.deleteOneIssueReply(rplId) > 0;
	}

	@Transactional
	@Override
	public boolean modifyOneReply(IssueReplyVO issueReplyVO) {
		IssueReplyVO originalIssueReplyVO = this.issueReplyDao.getOneReply(issueReplyVO.getRplId());
		if (!originalIssueReplyVO.getCrtrId().equals(issueReplyVO.getCrtrId())) {
			throw new PageNotFoundException();
		}
		return this.issueReplyDao.modifyOneIssueReply(issueReplyVO) > 0;
	}
}
