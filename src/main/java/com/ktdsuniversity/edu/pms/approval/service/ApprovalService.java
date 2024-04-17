package com.ktdsuniversity.edu.pms.approval.service;


import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;

public interface ApprovalService {

	/**
	 * 모든 결재목록과 건수를 조회한다.
	 * @return
	 */
	public ApprovalListVO getAllApproval();
	
	/**
	 * 사용자가 작성한 전체 결재 목록과 건수를 조회한다.
	 * @return
	 */
	public ApprovalListVO getAllApprovalByEmpId(String empId);

	public ApprovalVO selectOneApproval(String apprId);

    public ApprovalVO selectOneApprovalAll(String apprId);

	public boolean approvalStatusChange(ApprovalVO approvalVO);

    public boolean deleteOneApproval(String apprId);

}
