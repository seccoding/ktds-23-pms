package com.ktdsuniversity.edu.pms.issue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.issue.dao.IssueDao;
import com.ktdsuniversity.edu.pms.issue.vo.IssueListVO;
import com.ktdsuniversity.edu.pms.issue.vo.IssueVO;

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueDao issueDao;

	@Override
	public IssueListVO getAllIssue() {
		int issueCount = this.issueDao.getAllIssueCount();
		List<IssueVO> issueList = this.issueDao.getAllIssue();
		
		IssueListVO issueListVO = new IssueListVO();
		issueListVO.setIssueCnt(issueCount);
		issueListVO.setIssueList(issueList);
		
		return issueListVO;
	}

	@Transactional
	@Override
	public IssueVO getOneIssue(int isId, boolean isIncrease) {
		IssueVO issueVO = this.issueDao.selectOneIssue(isId);
		
		if (issueVO == null) {
			return null;
			// TODO PageNotFoundException 작성해야함.
		}
		if (isIncrease) {
			this.issueDao.increaseViewCount(isId);
		}
		return issueVO;
	}
	
	@Transactional
	@Override
	public boolean createNewIssue(IssueVO issueVO) {
		int insertedCount = this.issueDao.insertNewIssue(issueVO);
		
		return insertedCount > 0;
	}

	@Transactional
	@Override
	public boolean updateOneIssue(IssueVO issueVO) {
		int updatedCount = this.issueDao.updateOneIssue(issueVO);
		
		return updatedCount > 0;
	}

	@Transactional
	@Override
	public boolean deleteOneIssue(int isId) {
		int deletedCount = this.issueDao.deleteOneIssue(isId);
		
		return deletedCount > 0;
	}
}
