package com.ktdsuniversity.edu.pms.issue.vo;

import java.util.List;

public class IssueListVO {

	/**
	 * DB에서 조회된 이슈의 개수
	 */
	private int issueCnt;
	
	/**
	 * DB에서 조회한 이슈 목록
	 */
	private List<IssueVO> issueList;

	public int getIssueCnt() {
		return issueCnt;
	}

	public void setIssueCnt(int issueCnt) {
		this.issueCnt = issueCnt;
	}

	public List<IssueVO> getIssueList() {
		return issueList;
	}

	public void setIssueList(List<IssueVO> issueList) {
		this.issueList = issueList;
	}
}
