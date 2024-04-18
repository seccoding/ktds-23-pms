package com.ktdsuniversity.edu.pms.approval.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.approval.dao.ApprovalDao;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;

@Service
public class ApprovalServiceImpl implements ApprovalService {
	
	Logger logger = LoggerFactory.getLogger(ApprovalDao.class);

	@Autowired
	private ApprovalDao approvalDao;

	@Override
	public ApprovalListVO getAllApproval() {
		int approvalCount = this.approvalDao.getAllCount();
		List<ApprovalVO> approvalList = this.approvalDao.getAllApproval();
		
		ApprovalListVO approvalListVO = new ApprovalListVO();
		approvalListVO.setApprCnt(approvalCount);
		approvalListVO.setApprList(approvalList);
		
		return approvalListVO;
	}
	
	@Override
	public ApprovalListVO getAllApprovalByEmpId(String empId) {
		int approvalCount = this.approvalDao.getAllCountByEmpId(empId);
		List<ApprovalVO> approvalList = this.approvalDao.getAllApprovalByEmpId(empId);
		
		ApprovalListVO approvalListVO = new ApprovalListVO();
		approvalListVO.setApprCnt(approvalCount);
		approvalListVO.setApprList(approvalList);
		
		return approvalListVO;
	}

	@Override
	public ApprovalVO selectOneApproval(String apprId) {
		return this.approvalDao.selectOneApproval(apprId);
	}

	@Override
	public ApprovalVO selectOneApprovalAll(String apprId) {
		ApprovalVO approvalVO=this.approvalDao.selectOneApprovalAll(apprId);
		if(approvalVO == null) {
			throw new PageNotFoundException(); // 적당한 error를 찾아보자.
		}
		return approvalVO;
	}


	@Override
	public boolean approvalStatusChange(ApprovalVO approvalVO) {
		int updateCount = this.approvalDao.updateApprovalStatus(approvalVO);
		return updateCount > 0;
	}

	@Override
	public boolean deleteOneApproval(String apprId) {
		int deleteCount=this.approvalDao.deleteApproval(apprId);
		return deleteCount>0;
	}
}
