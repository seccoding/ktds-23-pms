package com.ktdsuniversity.edu.pms.approval.dao;

import java.util.List;


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
	
	/**
     * 파라미터로 전달받은 게시글 ID의 게시글 정보를 조회한다.
     * @param id 게시글 ID (번호)
     * @return
     */
    public ApprovalVO getOneApproval(String id);
    
    /**
     * 파라미터로 전달받은 게시글 ID의 게시글을 삭제한다.
     * @param id 게시글 ID (번호)
     * @return DB에 Delete한 게시글의 수
     */
    public int deleteApproval(String id);

}
