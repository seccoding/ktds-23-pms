package com.ktdsuniversity.edu.pms.approval.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;

public interface ApprovalDetailDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.approval.dao.ApprovalDetailDao";
	
	/**
     * DB에 저장된 모든 게시글의 목록을 조회
     * @return
     */
    public List<ApprovalDetailVO> getAllApprovalDetail();
}
