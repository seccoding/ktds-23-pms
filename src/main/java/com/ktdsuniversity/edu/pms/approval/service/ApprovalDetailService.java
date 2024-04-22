package com.ktdsuniversity.edu.pms.approval.service;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailListVO;

public interface ApprovalDetailService {

	public ApprovalDetailListVO getAllApprovalDetail();

	/**
	 * 기존 결재의 번경 사항을 INSERT한다
	 */
//   public boolean createNewApprovalDetail( ApprovalDetailVO approvaldetailVo);

	// PSH0422
	public ApprovalDetailListVO getPersonApprovalDetail(String apprId);

	public boolean updateUnusablePrdt(String apprId);

}
