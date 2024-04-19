package com.ktdsuniversity.edu.pms.approval.dao;

import java.util.List;


import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;


public interface ApprovalDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.approval.dao.ApprovalDao";

	/**
	 * 전체 결재 건수를 조회한다.(ADMIN)
	 * @return 결재 건수
	 */
	public int getAllCount();
	
	/**
	 * 모든 결재 정보를 조회한다.(ADMIN)
	 * @return 결재 정보 목록
	 */
	public List<ApprovalVO> getAllApproval();

	/**
	 * 사원이 신청한 결재 건수를 조회한다.
	 * @param empId 사원ID
	 * @return 사원이 신청한 결재 건수
	 */
	public int getAllCountByEmpId(String empId);
	
	/**
	 * 사원이 신청한 결재 목록
	 * @param empId 사원ID
	 * @return 사원이 신청한 결재 목록
	 */
	public List<ApprovalVO> getAllApprovalByEmpId(String empId);

	public ApprovalVO selectOneApproval(String apprId);

	/**
     * 파라미터로 전달받은 게시글 ID의 게시글 정보를 조회한다.
     * @param apprId 게시글 ID (번호)
     * @return
     */
    public ApprovalVO selectOneApprovalAll(String apprId);

	public int updateApprovalStatus(ApprovalVO approvalVO);
    
    /**
     * 파라미터로 전달받은 게시글 ID의 게시글을 삭제한다.
     * @param id 게시글 ID (번호)
     * @return DB에 Delete한 게시글의 수
     */

    public int deleteApproval(String apprId);

    /**
     * 버튼 클릭시 승인 상태로 바뀐다
     */
    public int updateApproval(String id);
    
    
    
    
    /**
     * 승인되지 않은 결재 내역 개수
     */
    public int getAllApproveCount();
    
    /**
     * 일주일이상 지연된 결재 개수
     */
    public int  getAllOneWeekApprovalCount();
    
    /**
     * 한 달 이내 결재내역
     */
    public int  getAllMonthApprovalCount();
    
    /**
     * 페이지 개수 구하는 쿼리
     */
	public int searchBoardAllCount(ApprovalVO approvaVo);
    
	/**
     * 전체리스트 구하는 공식
     */
	public List<ApprovalVO> searchAllBoard(ApprovalVO approvaVo);
    

}
