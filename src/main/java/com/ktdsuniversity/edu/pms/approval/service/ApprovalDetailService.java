package com.ktdsuniversity.edu.pms.approval.service;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;

public interface ApprovalDetailService {
	public ApprovalDetailListVO getAllApprovalDetail();
	
	/**
	 * 기존 결재의 번경 사항을 INSERT한다 
	*/
   public boolean createNewApprovalDetail( ApprovalDetailVO approvaldetailVo);
   
   public ApprovalDetailListVO getPersonApprovalDetail(String id);
}
