package com.ktdsuniversity.edu.pms.approval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.approval.dao.ApprovalDao;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;

@Service
public class ApprovalServiceImpl implements ApprovalService {
	
	@Autowired
	private ApprovalDao approvalDao;

	@Override
	public ApprovalListVO searchAllApproval() {
		
		int approvalCount = this.approvalDao.getAllCount();
		List<ApprovalVO> approvalList = this.approvalDao.getAllApproval();
		
		ApprovalListVO approvalListVO = new ApprovalListVO();
		approvalListVO.setApprCnt(approvalCount);
		approvalListVO.setApprList(approvalList);
		
		return approvalListVO;
	}

//	@Override
//	public ApprovalListVO searchAllApprovalByEmpId(String empId) {
//		// 중복 제거 필요
//		int approvalCount = this.approvalDao.getAllCountByEmpId(empId);
//		List<ApprovalVO> approvalList = this.approvalDao.getAllApprovalByEmpId(empId);
//		
//		ApprovalListVO approvalListVO = new ApprovalListVO();
//		approvalListVO.setApprCnt(approvalCount);
//		approvalListVO.setApprList(approvalList);
//
//		return approvalListVO;
//	}

}
