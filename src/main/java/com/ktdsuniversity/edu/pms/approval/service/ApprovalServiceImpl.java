package com.ktdsuniversity.edu.pms.approval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.approval.dao.ApprovalDao;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;

@Service
public class ApprovalServiceImpl implements ApprovalService {
	
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
	public ApprovalVO selectOneApproval(String id) {
		// TODO Auto-generated method stub
		ApprovalVO approvalvo=this.approvalDao.getOneApproval(id);
			
		return approvalvo;
	}

	@Override
	public boolean deleteOneApproval(String id) {
		// TODO Auto-generated method stub
		int deleteCount=this.approvalDao.deleteApproval(id);
		
		return deleteCount>0;
	}
	
	@Override
	public boolean updatesOneApproval(String id) {
		// TODO Auto-generated method stub
		
		int updateCount=this.approvalDao.updateApproval(id);
	
		return updateCount>0;
	}

	

	

}
