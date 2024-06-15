package com.ktdsuniversity.edu.pms.approval.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.approval.vo.SearchApprovalVO;

public interface ApprovalDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.approval.dao.ApprovalDao";


	/**
	 * 전체 결재 건수를 조회한다.(ADMIN)
	 * 
	 * @return 결재 건수
	 */
	public int getAllCount();

	/**
	 * 모든 결재 정보를 조회한다.(ADMIN)
	 * 
	 * @return 결재 정보 목록
	 */
	public List<ApprovalVO> getAllApproval();
	
	/**
	 * 승인요청 정보를 받아서 승인요청 정보를 업데이트 한다
	 * @param approvalList 승인이 필요한 승인자들의 리스트
	 * @param approvalVO  insert Info(필수 정보: apprType, apprInfo, apprReqtr)
	 * @return approvalList.size == insertCnt
	 */
	public boolean insertApproval(List<String> approverList, ApprovalVO approvalVO );

	/**
	 * 승인 혹은 반려를 할 경우 해당내용을 업데이트 한다
	 * @param approvalVO
	 * @return
	 */
	public int updateOneApproveal(ApprovalVO approvalVO);
	/**
	 * apprId 를 통해 ApprovalVO 를 반환한다 
	 * @param apprId
	 * @return
	 */
	public ApprovalVO getApprovalByApprId(String apprId);
	/**
	 * apprInfo 를 통해서 승인되지 않은 건수가 몇개인지 조회한다
	 * @param approvalVO
	 * @return
	 */
	public int getNonApprCnt(ApprovalVO approvalVO);



}
