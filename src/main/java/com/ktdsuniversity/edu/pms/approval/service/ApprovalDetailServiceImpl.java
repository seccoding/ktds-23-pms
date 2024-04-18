package com.ktdsuniversity.edu.pms.approval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.approval.dao.ApprovalDetailDao;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;


@Service
public class ApprovalDetailServiceImpl implements ApprovalDetailService {
	
	@Autowired
	private ApprovalDetailDao approvaldetaildao;
	
	@Override
	public ApprovalDetailListVO getAllApprovalDetail() {
		// TODO Auto-generated method stub
		ApprovalDetailListVO approvallistvo =new ApprovalDetailListVO();
		
		approvallistvo.setApprovalDetailList(approvaldetaildao.getAllApprovalDetail());
		
		return approvallistvo;
	}
	
	@Override
	public boolean createNewApprovalDetail(ApprovalDetailVO approvaldetailVo) {
		// TODO Auto-generated method stub
		
		int insertCount=this.approvaldetaildao.insertApproval(approvaldetailVo);
		
		return insertCount>0;
	}

	@Override
	public ApprovalDetailListVO getPersonApprovalDetail(String id) {
		// TODO Auto-generated method stub
		ApprovalDetailListVO approvallistvo=new ApprovalDetailListVO();
		
		approvallistvo.setApprovalDetailList(approvaldetaildao.getpersonApproval(id));
		
		
		return approvallistvo;
	}

	

	
		
}
