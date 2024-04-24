package com.ktdsuniversity.edu.pms.approval.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.approval.dao.ApprovalDetailDao;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailListVO;
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
	

	// PSH0422
	@Override
	public ApprovalDetailListVO getPersonApprovalDetail(String apprId) {
		ApprovalDetailListVO approvalListVO = new ApprovalDetailListVO();
		approvalListVO.setApprovalDetailList(approvalDetailDao.getPersonApproval(apprId));
		return approvalListVO;
	}

//	@Transactional
//	@Override
//	public boolean updateUnusablePrdt(String apprId) {
//
//		int returnPrdtCount = this.borrowDao.returnOneItemByAppr(apprId);
//		int unusablePrdtCount = this.productManagementDao.unusablePrdtByAppr(apprId);
//		
//
//		return ((returnPrdtCount > 0) && (unusablePrdtCount > 0));
//	}

}
