package com.ktdsuniversity.edu.pms.approval.vo;

import java.util.List;

public class ApprovalListVO {
	
	/**
	 * DB에서 조회한 결재 개수
	 */
	private int apprCnt;
	/**
	 * DB에서 조회한 결재정보의 목록
	 */
	private List<ApprovalVO> apprList;
	public int getApprCnt() {
		return apprCnt;
	}
	public void setApprCnt(int apprCnt) {
		this.apprCnt = apprCnt;
	}
	public List<ApprovalVO> getApprList() {
		return apprList;
	}
	public void setApprList(List<ApprovalVO> apprList) {
		this.apprList = apprList;
	}
	

}
