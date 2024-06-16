package com.ktdsuniversity.edu.pms.approval.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.approval.vo.SearchApprovalVO;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;

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
	public Object gellApprovalByApprId(String apprId);

	/**
	 * 승인 혹은 반려를 할 경우 해당내용을 업데이트 한다
	 * @param approvalVO
	 * @return
	 */
	public boolean updateOneApproveal(ApprovalVO approvalVO);
	
	public void test();
//	public ApprovalListVO getAllApprovalByEmpId(String empId);
//
//	public ApprovalVO selectOneApproval(String apprId);
//
//	public String selectNewApprId();
//
//	public boolean createApproval(ApprovalVO approvalVO);
//	
//	public boolean getNewPrdtBorrowForAppr(ApprovalVO approvalVO);
//	
//	public boolean updateUnusablePrdt(ApprovalVO approvalVO);
//
//	public ApprovalListVO searchAllApproval(SearchApprovalVO searchApprovalVO);
//
//	public ApprovalVO selectOneApprovalAll(String apprId);
//
//	public boolean approvalStatusChange(ApprovalVO approvalVO);
//
//	public boolean approvalRntlStatusChange(ApprovalVO approvalVO);
//
//	public boolean deleteOneApproval(String apprId);
//
//	public List<BorrowVO> getAddProductApproval(List<String> addProducts);
//	
//	public boolean getDeptLeader(String empId);




}
