package com.ktdsuniversity.edu.pms.approval.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.approval.dao.ApprovalDetailDao;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;
import com.ktdsuniversity.edu.pms.borrow.dao.BorrowDao;
import com.ktdsuniversity.edu.pms.product.dao.ProductManagementDao;

@Service
public class ApprovalDetailServiceImpl implements ApprovalDetailService {

	private Logger logger = LoggerFactory.getLogger(ApprovalServiceImpl.class);

	@Autowired
	private ApprovalDetailDao approvalDetailDao;
	@Autowired
	private ProductManagementDao productManagementDao;
	@Autowired
	private BorrowDao borrowDao;
	
	@Override
	public ApprovalDetailListVO getAllApprovalDetail() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ApprovalDetailListVO getAllApprovalDetail() {
//		// TODO Auto-generated method stub
//		ApprovalDetailListVO approvallistvo = new ApprovalDetailListVO();
//		approvallistvo.setApprovalDetailList(approvaldetaildao.getAllApprovalDetail());
//		return approvallistvo;
//	}
//
//	@Override
//	public boolean createNewApprovalDetail(ApprovalDetailVO approvaldetailVo) {
//		// TODO Auto-generated method stub
//		int insertCount = this.approvaldetaildao.insertApproval(approvaldetailVo);
//		return false;
//	}

	// PSH0422
//	@Override
//	public ApprovalDetailListVO getPersonApprovalDetail(String apprId) {
//		ApprovalDetailListVO approvallistVO = new ApprovalDetailListVO();
//		approvallistVO.setApprovalDetailList(approvalDetailDao.getPersonApproval(apprId));
//		return approvallistVO;
//	}
//
//	@Transactional
//	@Override
//	public boolean updateUnusablePrdt(String apprId) {
//
//		int returnPrdtCount = this.borrowDao.returnOneItemByAppr(apprId);
//		int unusablePrdtCount = this.productManagementDao.unusablePrdtByAppr(apprId);
//
//		return ((returnPrdtCount > 0) && (unusablePrdtCount > 0));
//	}

}
