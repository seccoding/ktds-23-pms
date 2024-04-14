package com.ktdsuniversity.edu.pms.approval.service;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;

public interface ApprovalService {

	/**
	 * 전체 결재 목록과 건수를 조회한다.(ADMIN)
	 * @return
	 */
	public ApprovalListVO searchAllApproval();

	/**
	 * 사용자가 작성한 전체 결재 목록과 건수를 조회한다.
	 * @return
	 */
//	public ApprovalListVO searchAllApprovalByEmpId(String empId);

}
