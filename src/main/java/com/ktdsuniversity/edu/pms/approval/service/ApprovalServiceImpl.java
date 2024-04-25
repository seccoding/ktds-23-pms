package com.ktdsuniversity.edu.pms.approval.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.approval.dao.ApprovalDao;
import com.ktdsuniversity.edu.pms.approval.dao.ApprovalDetailDao;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.approval.vo.SearchApprovalVO;
import com.ktdsuniversity.edu.pms.borrow.dao.BorrowDao;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
import com.ktdsuniversity.edu.pms.exceptions.CreationException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.product.dao.ProductDao;
import com.ktdsuniversity.edu.pms.product.dao.ProductManagementDao;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementListVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

@Service
public class ApprovalServiceImpl implements ApprovalService {
	
	Logger logger = LoggerFactory.getLogger(ApprovalDao.class);

	@Autowired
	private ApprovalDao approvalDao;
	@Autowired
	private ApprovalDetailDao approvalDetailDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductManagementDao productManagementDao;
	@Autowired
	private BorrowDao borrowDao;

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
	
	// PSH0422
	@Override
	public ApprovalVO selectOneApprovalAll(String apprId) {
		ApprovalVO approvalVO = this.approvalDao.selectOneApprovalAll(apprId);
		if(approvalVO == null || approvalDetailDao.getPersonApproval(apprId) == null) {
			throw new PageNotFoundException();
		}
		return approvalVO;
	}
	
	@Override
	public String selectNewApprId() {
		return this.approvalDao.selectOneApprId();
	}

	@Transactional
	@Override
	public boolean createApproval(ApprovalVO approvalVO) {
		// Approval 등록
		int insertCount = this.approvalDao.insertApproval(approvalVO);
		if(! (insertCount > 0)) {
			throw new CreationException();
		}

		// Approval Detail 등록
		List<ApprovalDetailVO> newApprovalDeatilVOList = new ArrayList<>();
		for (ProductVO productVO : approvalVO.getProductListVO()) {
			ApprovalDetailVO approvalDetailVO = new ApprovalDetailVO();
			approvalDetailVO.setApprId(approvalVO.getApprId());
			approvalDetailVO.setPrdtId(productVO.getPrdtId());
			newApprovalDeatilVOList.add(approvalDetailVO);
		}
		int insertApprDtlCount = approvalDetailDao.insertApprovalDetailList(newApprovalDeatilVOList);
		boolean isInsertApprDtl = (insertApprDtlCount == approvalVO.getProductListVO().size());
		return isInsertApprDtl;
	}

	@Transactional
	@Override
	public boolean approvalStatusChange(ApprovalVO approvalVO) {
		int updateApprStsCount =  this.approvalDao.updateApprovalStatus(approvalVO);
		return updateApprStsCount > 0;
	}
	
	@Transactional
	@Override
	public boolean approvalRntlStatusChange(ApprovalVO approvalVO) {
		int updateRntlStsCount =  this.approvalDao.updateRentalStatus(approvalVO);
		return updateRntlStsCount > 0;
	}
	

//	@Override
//	public boolean getPrdtForNewAppr(ProductManagementListVO prdtMngListVO) {
//		for(ProductManagementVO prdtMngVO : prdtMngListVO.getProductManagementList()) {
//			ProductManagementVO prdtMng =  this.productManagementDao.selectPrdtForNewAppr(prdtMngListVO.getProductManagementList().get(0).getPrdtMngId());
//		}
//		return false;
//	}

	@Transactional
	@Override
	public boolean updateUnusablePrdt(ApprovalVO approvalVO) {
		// 1.비품 반납
		int returnPrdtCount = this.borrowDao.returnOneItemByAppr(approvalVO.getApprId());
		// 2.반납비품 사용불가
		int unusablePrdtCount = this.productManagementDao.unusablePrdtByAppr(approvalVO.getApprId());
		// 3.대여상태 -> 반납완료
		int updateRntlCount =  this.approvalDao.updateRentalStatus(approvalVO);

		return ((returnPrdtCount > 0) && (unusablePrdtCount > 0) && (updateRntlCount > 0));
	}
	
	@Transactional
	@Override
	public boolean getNewPrdtBorrowForAppr(ApprovalVO approvalVO) {
		// 1. 기대여 비품 선택
		List<String> prdtNameList = this.approvalDetailDao.getPrdtNameByApprId(approvalVO.getApprId());
		// 2. 신규 대여 비품 선택
		List<BorrowVO> borrowVOList = new ArrayList<>();
		int isSuccessprdtMng = 0;
		for (int i = 0 ; i < prdtNameList.size(); i++ ) {
			String newBrrwPrdtId = this.productManagementDao.getNewPrdtMngIdForBorrow(prdtNameList.get(i));
			isSuccessprdtMng += this.productManagementDao.changeItemBrrwStateY(newBrrwPrdtId);
			// 신규 대여이력정보
			BorrowVO borrowVO = new BorrowVO();
			borrowVO.setBrrwId(this.approvalDao.selectOneApproval(approvalVO.getApprId()).getDmdId());
			borrowVO.setPrdtMngId(newBrrwPrdtId);
			borrowVOList.add(i, borrowVO);
		}
		// 3. 선택비품 대여처리
		int isSuccessBrrwHt = this.borrowDao.newBrrwPrdtByAppr(borrowVOList);
		if( (isSuccessprdtMng != isSuccessBrrwHt) && (isSuccessBrrwHt != borrowVOList.size())) {
			throw new PageNotFoundException();
		}

		// 4. 재고 수량 감소
		int isSuccessChange = 0;
		for (String prdtName : prdtNameList) {
			isSuccessChange += this.productDao.changeOnePrdtStored(prdtName);
		}
		if(isSuccessChange != prdtNameList.size()) {
			throw new PageNotFoundException();
		}
		// 5. 대여상태 -> 신규비품제공
		int updateRntlCount =  this.approvalDao.updateRentalStatus(approvalVO);

		boolean isProcessSuccess = (isSuccessprdtMng > 0) && (isSuccessBrrwHt > 0)
									&& (isSuccessChange > 0) && (updateRntlCount > 0);
		return isProcessSuccess;
	}
	
	@Transactional
	@Override
	public boolean deleteOneApproval(String apprId) {
		// 결재상세내역 삭제
		int deleteApprDtlCount = this.approvalDetailDao.deleteApprovalDetail(apprId);
		if(! (this.approvalDetailDao.getPrdtCountByApprId(apprId) == deleteApprDtlCount)) {
			throw new PageNotFoundException(); // 오류 수정
		}
		// 결재 내역 삭제
		int deleteCount = this.approvalDao.deleteApproval(apprId);
 
		return deleteCount > 0;
	}

	// PSH - search
	@Override
	public ApprovalListVO searchAllApproval(SearchApprovalVO searchApprovalVO) {
		int approvalCount = this.approvalDao.searchApprAllCount(searchApprovalVO);
		searchApprovalVO.setPageCount(approvalCount);

		List<ApprovalVO> approvalList = this.approvalDao.searchAllApproval(searchApprovalVO);

		ApprovalListVO approvalListVO = new ApprovalListVO();
		approvalListVO.setApprCnt(approvalCount);
		approvalListVO.setApprList(approvalList);

		return approvalListVO;
	}

	// PHS End ----------
	
	@Override
	public ApprovalListVO getAllApprove() {
		// TODO Auto-generated method stub
		int approveCount = this.approvalDao.getAllApproveCount();
		
		ApprovalListVO approvalListVO = new ApprovalListVO();
		approvalListVO.setApprCnt(approveCount);
		
		return approvalListVO;
	}

	@Override
	public ApprovalListVO getAllOneWeekApproval() {
		// TODO Auto-generated method stub
		int approvalweeKCount = this.approvalDao.getAllOneWeekApprovalCount();
		
		ApprovalListVO approvalListVO = new ApprovalListVO();
		approvalListVO.setApprCnt(approvalweeKCount);
		
		return approvalListVO;
	}
	
	@Override
	public ApprovalListVO getAllMonthApproval() {
		// TODO Auto-generated method stub
		int approvalMonthCount = this.approvalDao.getAllMonthApprovalCount();
		
		ApprovalListVO approvalListVO = new ApprovalListVO();
		approvalListVO.setApprCnt(approvalMonthCount);
		
		return approvalListVO;
	}
	

	@Override
	public ApprovalListVO searchAllBoard(ApprovalVO approvaVo) {
		// TODO Auto-generated method stub
		int boardcount=this.approvalDao.searchBoardAllCount(approvaVo);
		approvaVo.setPageCount(boardcount);
		
		List<ApprovalVO> boardList=this.approvalDao.searchAllBoard(approvaVo);
		
		ApprovalListVO boardListVO=new ApprovalListVO();
		boardListVO.setApprCnt(boardcount);
		boardListVO.setApprList(boardList);
		
		return boardListVO;
	}

	@Transactional
	@Override
	public ApprovalListVO searchApprovalView(SearchApprovalVO searchapprovalvo, String id) {
		// TODO Auto-generated method stub
		int ApprovalCount=this.approvalDao.selectAllApprovalCount(id);
		searchapprovalvo.setPageCount(ApprovalCount);
		
		SearchApprovalVO searchapprova=new SearchApprovalVO();
		searchapprova.setEmpId(id);
		
		List<ApprovalVO> approval=approvalDao.searchBoard(searchapprovalvo);
		
		ApprovalListVO approvallistvo=new ApprovalListVO();
		approvallistvo.setApprCnt(ApprovalCount);
		approvallistvo.setApprList(approval);
		
		return approvallistvo;
	}
}
