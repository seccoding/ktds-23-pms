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
	 * 사원이 신청한 결재 건수를 조회한다.
	 * 
	 * @param empId 사원ID
	 * @return 사원이 신청한 결재 건수
	 */
	public int getAllCountByEmpId(String empId);

	/**
	 * 사원이 신청한 결재 목록
	 * 
	 * @param empId 사원ID
	 * @return 사원이 신청한 결재 목록
	 */
	public List<ApprovalVO> getAllApprovalByEmpId(String empId);

	public ApprovalVO selectOneApproval(String apprId);

	/**
	 * 파라미터로 전달받은 게시글 ID의 게시글 정보를 조회한다.
	 * 
	 * @param apprId 게시글 ID (번호)
	 * @return
	 */
	public ApprovalVO selectOneApprovalAll(String apprId);

	public int updateApprovalStatus(ApprovalVO approvalVO);
	
	public int updateRentalStatus(ApprovalVO approvalVO);

	// PSH - 0422
	public String selectOneApprId();

	public int insertApproval(ApprovalVO approvalVO);

	public int deleteApproval(String apprId);
	
	// PSH - search
	public int searchAllApprovalCount(SearchApprovalVO searchApprovalVO);

	public List<ApprovalVO> searchAllApproval(SearchApprovalVO searchApprovalVO);													

}
