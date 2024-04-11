package com.ktdsuniversity.edu.pms.issue.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.issue.vo.IssueVO;

public interface IssueDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.issue.dao.IssueDao";
	
	/**
	 * 모든 이슈 개수 조회
	 * @return
	 */
	public int getAllIssueCount();
	
	/**
	 * 모든 이슈를 목록 조회
	 * @return
	 */
	public List<IssueVO> getAllIssue();
	
	
}
