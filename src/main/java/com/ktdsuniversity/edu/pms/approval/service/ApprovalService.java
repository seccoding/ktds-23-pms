package com.ktdsuniversity.edu.pms.approval.service;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.approval.vo.SearchApprovalVO;

public interface ApprovalService {

	/**
	 * 모든 결재목록과 건수를 조회한다.
	 * 
	 * @return
	 */
	public ApprovalListVO getAllApproval();

	/**
	 * 사용자가 작성한 전체 결재 목록과 건수를 조회한다.
	 * 
	 * @return
	 */
	public ApprovalListVO getAllApprovalByEmpId(String empId);

	public ApprovalVO selectOneApproval(String apprId);
	
	// PSH0422
	public String selectNewApprId();

	public boolean createApproval(ApprovalVO approvalVO);
	
	public boolean getNewPrdtBorrowForAppr(String apprId);

	/**
	 * 승인되지 않은 결재 내역 개수
	 */
	public ApprovalListVO getAllApprove();

	/**
	 * 일주일이상 지연된 결재
	 */
	public ApprovalListVO getAllOneWeekApproval();

	/**
	 * 한 달 이내 결재내역
	 */
	public ApprovalListVO getAllMonthApproval();

	public ApprovalVO selectOneApprovalAll(String apprId);

	public boolean approvalStatusChange(ApprovalVO approvalVO);

	public boolean deleteOneApproval(String apprId);

	/**
	 * 페이지 개수와 리스트를 구한다
	 */
	public ApprovalListVO searchAllBoard(ApprovalVO approvaVo);

	/**
	 * view 페이지 에 보여질 페이지 개수와 리스트를 출력 한다
	 */
	public ApprovalListVO searchApprovalView(SearchApprovalVO searchapprovalvo, String id);

}
