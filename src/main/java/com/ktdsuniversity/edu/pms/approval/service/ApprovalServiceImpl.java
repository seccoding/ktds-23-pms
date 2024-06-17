package com.ktdsuniversity.edu.pms.approval.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.approval.dao.ApprovalDao;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.department.dao.DepartmentApprovalDao;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentApprovalVO;
import com.ktdsuniversity.edu.pms.rentalsupply.dao.RentalSupplyApprovalDao;
import com.ktdsuniversity.edu.pms.rentalsupply.dao.RentalSupplyDao;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyApprovalVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyVO;
import com.ktdsuniversity.edu.pms.supply.dao.SupplyApprovalDao;
import com.ktdsuniversity.edu.pms.supply.dao.SupplyDao;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyApprovalVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;

@Service
public class ApprovalServiceImpl implements ApprovalService {
	
	Logger logger = LoggerFactory.getLogger(ApprovalDao.class);

	@Autowired
	private ApprovalDao approvalDao;
	
	@Autowired
	private SupplyApprovalDao supplyApprovalDao;
	
	@Autowired 
	private RentalSupplyApprovalDao rentalSupplyApprovalDao;
	
	@Autowired
	private DepartmentApprovalDao departmentApprovalDao;
	
	@Autowired
	private SupplyDao supplyDao;
	
	@Autowired
	private RentalSupplyDao rentalSupplyDao;

	@Override
	public ApprovalListVO getAllApproval() {
		List<ApprovalVO> approvalList = this.approvalDao.getAllApproval();
		
		ApprovalListVO approvalListVO = new ApprovalListVO();
		approvalListVO.setApprList(approvalList);
		
		return approvalListVO;
	}
	
	@Override
	public Object gellApprovalByApprId(String apprId) {
//		1. apprId로 DB에 있는 approvalVO를 가져온다
		ApprovalVO approvalVO= this.approvalDao.getApprovalByApprId(apprId);
		logger.debug("저장된 승인요청 타입: "+approvalVO.getApprType()+" 테이블 pk: "+approvalVO.getApprInfo());
		String apprType = approvalVO.getApprType();
		
//		2.조건문을 통해 필요한 정보를 selecet 한 후 반환
		if(apprType.equals("SUPPLY")) {
			SupplyApprovalVO approvalVO2 = this.supplyApprovalDao.getSupplyApprovalByPK(approvalVO.getApprInfo());
			return approvalVO2;
		}else if(apprType.equals("DEPARTMENT")) {
			DepartmentApprovalVO approvalVO2 = this.departmentApprovalDao.getDepartmentApprovalByPK(approvalVO.getApprInfo());
			return approvalVO2;
		}else if(apprType.equals("RSUPPLY")){
			RentalSupplyVO approvalVO2 = this.rentalSupplyApprovalDao.getRentalSupplyApprovalByPK(approvalVO.getApprInfo());
			return approvalVO2;
		}
		
		return null;
	}

	@Override
	@Transactional
	public void test() {
		ApprovalVO approvalVO = new ApprovalVO();
		approvalVO.setApprType("type");
		approvalVO.setApprInfo("info2");
		approvalVO.setApprReqtr("세영");
		this.approvalDao.insertApproval(List.of("aaa","bbb","ccc","ddd"), approvalVO);

	}

	@Override
	@Transactional
	public boolean updateOneApproveal(ApprovalVO approvalVO) {
		
//		1. 승인 요청을 업데이트 한다 
		this.approvalDao.updateOneApproveal(approvalVO);
		
//		2. 해당 내용의 승인이 전부 완료되었는지 체크 
		approvalVO = this.approvalDao.getApprovalByApprId(approvalVO.getApprId());
		int cnt = this.approvalDao.getNonApprCnt(approvalVO);
		logger.debug(approvalVO.getApprInfo()+"의 미승인 건수: "+cnt);
		
//		3. 완료시 해당 승인내용을 db에 반영한다   
		if(cnt==0) {
			return updateApprovalDetails(approvalVO);
		}
		return false;
		
	}
	
	private boolean updateApprovalDetails (ApprovalVO approvalVO) {
		logger.debug("다른테이블 업데이트 필요함 ");
		approvalVO= this.approvalDao.getApprovalByApprId(approvalVO.getApprId());
		if(approvalVO.getApprType().equals("SUPPLY")) {//소모품 테이블 업테이트
			
			this.supplyApprovalDao.updateOneSupplyApprovalYnToYByPK(approvalVO.getApprInfo());
			SupplyApprovalVO supplyApprovalVO = this.supplyApprovalDao.getSupplyApprovalByPK(approvalVO.getApprInfo());
			
			SupplyVO supplyVO = new SupplyVO();
			supplyVO.setSplName(supplyApprovalVO.getSplName());
			supplyVO.setSplCtgr(supplyApprovalVO.getSplCtgr());
			supplyVO.setSplPrice(supplyApprovalVO.getSplPrice());
			supplyVO.setInvQty(supplyApprovalVO.getInvQty());
			supplyVO.setSplImg(supplyApprovalVO.getSplImg());
			supplyVO.setSplDtl(supplyApprovalVO.getSplDtl());				
			
			if(supplyApprovalVO.getSplApprType().equals("INSERT")){
				supplyVO.setSplRegtId(supplyApprovalVO.getSplApprReqtr());
				this.supplyDao.registerNewSupply(supplyVO);		
				
				return true;
			}else if(supplyApprovalVO.getSplApprType().equals("UPDATE")){
				supplyVO.setSplId(supplyApprovalVO.getSplId());
				if(supplyApprovalVO.getSplRqstType().equals("수정")) {
					supplyVO.setSplMdfrId(supplyApprovalVO.getSplApprReqtr());						
				}
				this.supplyDao.updateOneSupply(supplyVO);
				
				return true;
			}
		}else if(approvalVO.getApprType().equals("RSUPPLY")) {//대여품 테이블 업데이트
			this.rentalSupplyApprovalDao.updateOneRentalSupplyApprovalYnToYByPK(approvalVO.getApprInfo());
			RentalSupplyApprovalVO rentalSupplyApprovalVO = this.rentalSupplyApprovalDao.getRentalSupplyApprovalByPK(approvalVO.getApprInfo());

			RentalSupplyVO rentalSupplyVO = new RentalSupplyVO();
			rentalSupplyVO.setRsplName(rentalSupplyApprovalVO.getRsplName());
			rentalSupplyVO.setRsplCtgr(rentalSupplyApprovalVO.getRsplCtgr());
			rentalSupplyVO.setRsplPrice(rentalSupplyApprovalVO.getRsplPrice());
			rentalSupplyVO.setInvQty(rentalSupplyApprovalVO.getInvQty());
			rentalSupplyVO.setRsplImg(rentalSupplyApprovalVO.getRsplImg());				
			rentalSupplyVO.setRsplDtl(rentalSupplyApprovalVO.getRsplDtl());				
			
			if(rentalSupplyApprovalVO.getRsplApprType().equals("INSERT")) {
				rentalSupplyVO.setRsplRegtId(rentalSupplyApprovalVO.getRsplApprReqtr());				
				this.rentalSupplyDao.registerNewRentalSupply(rentalSupplyVO);
				
				return true;
			}
			else if(rentalSupplyApprovalVO.getRsplApprType().equals("UPDATE")) {
				rentalSupplyVO.setRsplId(rentalSupplyApprovalVO.getRsplId());
				if(rentalSupplyApprovalVO.getRsplRqstType().equals("수정")) {
					rentalSupplyVO.setRsplMdfrId(rentalSupplyApprovalVO.getRsplApprReqtr());				
				}
				this.rentalSupplyDao.updateOneRentalSupply(rentalSupplyVO);
				
				return true;
			}else if(approvalVO.getApprType().equals("DEPARTMENT")){
				
			}
			
		}
		return false;
	}

	
	
//	@Override
//	public ApprovalListVO getAllApprovalByEmpId(String empId) {
//		int approvalCount = this.approvalDao.getAllCountByEmpId(empId);
//		List<ApprovalVO> approvalList = this.approvalDao.getAllApprovalByEmpId(empId);
//		
//		ApprovalListVO approvalListVO = new ApprovalListVO();
//		approvalListVO.setApprCnt(approvalCount);
//		approvalListVO.setApprList(approvalList);
//		
//		return approvalListVO;
//	}
//
//	@Override
//	public ApprovalVO selectOneApproval(String apprId) {
//		return this.approvalDao.selectOneApproval(apprId);
//	}
//	
//	// PSH0422
//	@Override
//	public ApprovalVO selectOneApprovalAll(String apprId) {
//		ApprovalVO approvalVO = this.approvalDao.selectOneApprovalAll(apprId);
//		if(approvalVO == null || approvalDetailDao.getPersonApproval(apprId) == null) {
//			throw new PageNotFoundException();
//		}
//		return approvalVO;
//	}
//	
//	@Override
//	public String selectNewApprId() {
//		return this.approvalDao.selectOneApprId();
//	}
//
//	@Transactional
//	@Override
//	public boolean createApproval(ApprovalVO approvalVO) {
//		String apprMngId = this.departmentDao.getOneDepartment("DEPT_230101_000010").getDeptLeadId();
//		approvalVO.setApprMngId(apprMngId);
//		
//		// Approval 등록
//		int insertCount = this.approvalDao.insertApproval(approvalVO);
//		if(! (insertCount > 0)) {
//			throw new CreationException();
//		}
//
//		// Approval Detail 등록
//		List<ApprovalDetailVO> newApprovalDeatilVOList = new ArrayList<>();
//		for (ProductVO productVO : approvalVO.getProductListVO()) {
//			ApprovalDetailVO approvalDetailVO = new ApprovalDetailVO();
//			approvalDetailVO.setApprId(approvalVO.getApprId());
//			approvalDetailVO.setPrdtId(productVO.getPrdtId());
//			newApprovalDeatilVOList.add(approvalDetailVO);
//		}
//		int insertApprDtlCount = approvalDetailDao.insertApprovalDetailList(newApprovalDeatilVOList);
//		boolean isInsertApprDtl = (insertApprDtlCount == approvalVO.getProductListVO().size());
//		return isInsertApprDtl;
//	}
//
//	@Transactional
//	@Override
//	public boolean approvalStatusChange(ApprovalVO approvalVO) {
//		int updateApprStsCount =  this.approvalDao.updateApprovalStatus(approvalVO);
//		return updateApprStsCount > 0;
//	}
//	
//	@Transactional
//	@Override
//	public boolean approvalRntlStatusChange(ApprovalVO approvalVO) {
//		int updateRntlStsCount =  this.approvalDao.updateRentalStatus(approvalVO);
//		return updateRntlStsCount > 0;
//	}
//
//	@Transactional
//	@Override
//	public boolean updateUnusablePrdt(ApprovalVO approvalVO) {
//		// 1.비품 반납
//		int returnPrdtCount = this.borrowDao.returnOneItemByAppr(approvalVO.getApprId());
//		// 2.반납비품 사용불가
//		int unusablePrdtCount = this.productManagementDao.unusablePrdtByAppr(approvalVO.getApprId());
//		// 3.대여상태 -> 반납완료
//		int updateRntlCount =  this.approvalDao.updateRentalStatus(approvalVO);
//
//		return ((returnPrdtCount > 0) && (unusablePrdtCount > 0) && (updateRntlCount > 0));
//	}
//	
//	@Transactional
//	@Override
//	public boolean getNewPrdtBorrowForAppr(ApprovalVO approvalVO) {
//		// 1. 기대여 비품 선택
//		List<String> prdtNameList = this.approvalDetailDao.getPrdtNameByApprId(approvalVO.getApprId());
//		// 2. 신규 대여 비품 선택
//		List<BorrowVO> borrowVOList = new ArrayList<>();
//		int isSuccessprdtMng = 0;
//		for (int i = 0 ; i < prdtNameList.size(); i++ ) {
//			String newBrrwPrdtId = this.productManagementDao.getNewPrdtMngIdForBorrow(prdtNameList.get(i));
//			isSuccessprdtMng += this.productManagementDao.changeItemBrrwStateY(newBrrwPrdtId);
//			// 신규 대여이력정보
//			BorrowVO borrowVO = new BorrowVO();
//			borrowVO.setBrrwId(this.approvalDao.selectOneApproval(approvalVO.getApprId()).getDmdId());
//			borrowVO.setPrdtMngId(newBrrwPrdtId);
//			borrowVOList.add(i, borrowVO);
//		}
//		// 3. 선택비품 대여처리
//		int isSuccessBrrwHt = this.borrowDao.newBrrwPrdtByAppr(borrowVOList);
//		if( (isSuccessprdtMng != isSuccessBrrwHt) && (isSuccessBrrwHt != borrowVOList.size())) {
//			throw new PageNotFoundException();
//		}
//
//		// 4. 재고 수량 감소
//		int isSuccessChange = 0;
//		for (String prdtName : prdtNameList) {
//			isSuccessChange += this.productDao.changeOnePrdtStored(prdtName);
//		}
//		if(isSuccessChange != prdtNameList.size()) {
//			throw new PageNotFoundException();
//		}
//		// 5. 대여상태 -> 신규비품제공
//		int updateRntlCount =  this.approvalDao.updateRentalStatus(approvalVO);
//
//		boolean isProcessSuccess = (isSuccessprdtMng > 0) && (isSuccessBrrwHt > 0)
//									&& (isSuccessChange > 0) && (updateRntlCount > 0);
//		return isProcessSuccess;
//	}
//	
//	@Transactional
//	@Override
//	public boolean deleteOneApproval(String apprId) {
//		// 결재상세내역 삭제
//		int deleteApprDtlCount = this.approvalDetailDao.deleteApprovalDetail(apprId);
//		if(! (this.approvalDetailDao.getPrdtCountByApprId(apprId) == deleteApprDtlCount)) {
//			throw new PageNotFoundException(); // 오류 수정
//		}
//		// 결재 내역 삭제
//		int deleteCount = this.approvalDao.deleteApproval(apprId);
// 
//		return deleteCount > 0;
//	}
//
//	@Override
//	public List<BorrowVO> getAddProductApproval(List<String> addProducts) {
//		List<BorrowVO> borrowList = this.borrowDao.getBorrowProduct(addProducts);
//		return borrowList;
//	}
//
//	@Override
//	public ApprovalListVO searchAllApproval(SearchApprovalVO searchApprovalVO) {
//		
//		System.out.println("???????????????" + searchApprovalVO.getSearchDate());
//		
//		int approvalCount = this.approvalDao.searchAllApprovalCount(searchApprovalVO);
//		searchApprovalVO.setPageCount(approvalCount);
//
//		List<ApprovalVO> approvalList = this.approvalDao.searchAllApproval(searchApprovalVO);
//
//		ApprovalListVO approvalListVO = new ApprovalListVO();
//		approvalListVO.setApprCnt(approvalCount);
//		approvalListVO.setApprList(approvalList);
//
//		return approvalListVO;
//	}
//
//	@Override
//	public boolean getDeptLeader(String empId) {
//		String authId = this.departmentDao.getOneDepartment("DEPT_230101_000010").getDeptLeadId();
//		if(empId.equals(authId)) {
//			return true;
//		}
//		return false;
//	}
//  
}
