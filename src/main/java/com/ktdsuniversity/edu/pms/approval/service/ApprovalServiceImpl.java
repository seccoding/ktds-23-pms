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
	
	@Override
	public ApprovalListVO getAllApproval() {
		// TODO Auto-generated method stub
		ApprovalListVO approvalList=new ApprovalListVO();
		
		approvalList.setApprCnt(approvalDao.getAllCount());
		
		approvalList.setApprList(approvalDao.getAllApproval());
				
		return approvalList;
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

}
