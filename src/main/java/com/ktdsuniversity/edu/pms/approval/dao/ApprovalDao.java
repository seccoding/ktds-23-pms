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

	
}
