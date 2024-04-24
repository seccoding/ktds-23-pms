package com.ktdsuniversity.edu.pms.approval.service;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailListVO;

public interface ApprovalDetailService {

	// PSH0422
	public ApprovalDetailListVO getPersonApprovalDetail(String apprId);

//	public boolean updateUnusablePrdt(String apprId);

}
