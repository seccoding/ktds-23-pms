package com.ktdsuniversity.edu.pms.rentalsupply.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.approval.dao.ApprovalDao;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.FileHandler.StoredFile;
import com.ktdsuniversity.edu.pms.department.dao.DepartmentDao;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.rentalsupply.dao.RentalSupplyApprovalDao;
import com.ktdsuniversity.edu.pms.rentalsupply.dao.RentalSupplyDao;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyApprovalListVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyApprovalVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyListVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;

@Service
public class RentalSupplyServiceImpl implements RentalSupplyService{
	
	@Autowired
	private RentalSupplyDao rentalSupplyDao;
	
	@Autowired
	private RentalSupplyApprovalDao rentalSupplyApprovalDao;
	
	@Autowired
	private ApprovalDao approvalDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private FileHandler fileHandler;

	@Override
	public RentalSupplyListVO searchAllRentalSupply(SearchRentalSupplyVO searchRentalSupplyVO) {
		int rentalSupplyCount = this.rentalSupplyDao.searchRentalSupplyAllCount(searchRentalSupplyVO);
		searchRentalSupplyVO.setPageCount(rentalSupplyCount);

		List<RentalSupplyVO> rentalSupplyList = this.rentalSupplyDao.searchAllRentalSupply(searchRentalSupplyVO);

		RentalSupplyListVO RentalSupplyListVO = new RentalSupplyListVO();
		RentalSupplyListVO.setRentalSupplyCnt(rentalSupplyCount);
		RentalSupplyListVO.setRentalSupplyList(rentalSupplyList);

		return RentalSupplyListVO;
	}

	@Override
	public RentalSupplyVO getOneRentalSupply(String rsplId) {
		RentalSupplyVO rentalSupplyVO = this.rentalSupplyDao.selectOneRentalSupply(rsplId);
		
		return rentalSupplyVO;
	}

	@Override
	public RentalSupplyListVO getAllRentalSupplyCategory() {
		List<RentalSupplyVO> rentalSupplyCategoryList = this.rentalSupplyDao.selectAllRentalSupplyCategory();

		RentalSupplyListVO RentalSupplyListVO = new RentalSupplyListVO();
		RentalSupplyListVO.setRentalSupplyList(rentalSupplyCategoryList);

		return RentalSupplyListVO;
	}

	@Transactional
	@Override
	public boolean requestRegistrationNewRentalSupply(RentalSupplyApprovalVO rentalSupplyApprovalVO,
			MultipartFile file) {
		// 결재 요청 타입(INSERT, UPDATE, DELETE?)
		rentalSupplyApprovalVO.setRsplApprType("INSERT");
		rentalSupplyApprovalVO.setDelYn("N");
		rentalSupplyApprovalVO.setRsplRqstType("등록");

		if (file != null && !file.isEmpty()) {
			StoredFile storedFile = fileHandler.storeFile(file);

			if (storedFile != null) {
				rentalSupplyApprovalVO.setRsplImg(storedFile.getRealFileName());
			}
		}

		int requestedCount = this.rentalSupplyApprovalDao.insertRentalSupplyApprovalRequest(rentalSupplyApprovalVO);

		// 결재 승인자 리스트
		List<String> approvalList = new ArrayList<>();
		// 결재 요청자 정보
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(rentalSupplyApprovalVO.getRsplApprReqtr());
		// 경영지원부 정보
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		// 경영지원부서장
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		// 대표이사
		String ceoId = this.employeeDao.getAllEmployee().stream().filter(emp -> emp.getPstnId().equals("110"))
				.map(emp -> emp.getEmpId()).findFirst().orElse(mgmtSprtDeptLeadId);

		// 결재라인 순서(경영지원부장 -> 대표이사)
		// 결재라인 조건
		int price = rentalSupplyApprovalVO.getRsplPrice() * rentalSupplyApprovalVO.getInvQty();
		approvalList.add(mgmtSprtDeptLeadId);
		if (price > 10000000) {
//			approvalList.add(ceoId);
			approvalList.add("system01");
		}

		ApprovalVO approvalVO = new ApprovalVO();
		// apprType: 결재 승인 타입(소모품, 비품, 부서, 직원)
		approvalVO.setApprType("RSUPPLY");
		// apprInfo: 결재시 업데이트 해야하는 정보를 담은 FK ID
		approvalVO.setApprInfo(rentalSupplyApprovalVO.getRsplApprId());
		// apprReqtr: 결재 요청자
		approvalVO.setApprReqtr(employeeVO.getEmpId());
		this.approvalDao.insertApproval(approvalList, approvalVO);

		return requestedCount > 0;
	}

	@Transactional
	@Override
	public boolean requestModificationRentalSupply(RentalSupplyApprovalVO rentalSupplyApprovalVO, MultipartFile file) {
		// 결재 요청 타입(INSERT, UPDATE, DELETE?)
		rentalSupplyApprovalVO.setRsplApprType("UPDATE");
		rentalSupplyApprovalVO.setDelYn("N");
		rentalSupplyApprovalVO.setRsplRqstType("수정");

		RentalSupplyVO originalRentalSupplyVO = this.rentalSupplyDao.selectOneRentalSupply(rentalSupplyApprovalVO.getRsplId());
		if (file != null && !file.isEmpty()) {

			if (originalRentalSupplyVO != null) {
				String storedImage = originalRentalSupplyVO.getRsplImg();

				if (storedImage != null && storedImage.length() > 0) {
					this.fileHandler.deleteFileByFileName(storedImage);
				}
			}
			StoredFile storedFile = fileHandler.storeFile(file);
			rentalSupplyApprovalVO.setRsplImg(storedFile.getRealFileName());
		}
		int updatedCount = this.rentalSupplyApprovalDao.insertRentalSupplyApprovalRequest(rentalSupplyApprovalVO);

		// 결재 승인자 리스트
		List<String> approvalList = new ArrayList<>();
		// 결재 요청자 정보 **(Mapper에 팀장, 부서장 조회하는 코드 추가 필요)
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(rentalSupplyApprovalVO.getRsplApprReqtr());
		// 경영지원부 정보
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		// 경영지원부서장
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		// 대표이사
		String ceoId = this.employeeDao.getAllEmployee().stream().filter(emp -> emp.getPstnId().equals("110"))
				.map(emp -> emp.getEmpId()).findFirst().orElse(mgmtSprtDeptLeadId);

		// 결재라인 순서(팀장 -> 부서장 -> 경영지원부장 -> 대표이사)
		// 결재라인 조건
		/*
		 * 공금에 영향을 줄 수 있는 경우 1. 제품가격 변경 -> 가격 변경만으로는 영향을 주지 않음 2. 재고 변경
		 */
		int price;

		approvalList.add(mgmtSprtDeptLeadId);

		if (rentalSupplyApprovalVO.getInvQty() != originalRentalSupplyVO.getInvQty()) {
			price = Math.abs(
					rentalSupplyApprovalVO.getRsplPrice() * (rentalSupplyApprovalVO.getInvQty() - originalRentalSupplyVO.getInvQty()));
			if (price > 10000000) {
//				approvalList.add(ceoId);
				approvalList.add("system01");
			}
		}

		ApprovalVO approvalVO = new ApprovalVO();
		// apprType: 결재 승인 타입(소모품, 비품, 부서, 직원)
		approvalVO.setApprType("RSUPPLY");
		// apprInfo: 결재시 업데이트 해야하는 정보를 담은 FK ID
		approvalVO.setApprInfo(rentalSupplyApprovalVO.getRsplApprId());
		// apprReqtr: 결재 요청자
		approvalVO.setApprReqtr(employeeVO.getEmpId());
		this.approvalDao.insertApproval(approvalList, approvalVO);

		return updatedCount > 0;
	}

	@Transactional
	@Override
	public boolean requestGetRentalSupply(RentalSupplyApprovalVO rentalSupplyApprovalVO) {
		rentalSupplyApprovalVO.setRsplRqstType("신청");
		rentalSupplyApprovalVO.setRtrnYn("N");
		
	    EmployeeVO employeeVO = this.employeeDao.getOneEmployee(rentalSupplyApprovalVO.getRsplApprReqtr());

	    // 원래 소모품 정보를 가져옴
	    RentalSupplyVO originalRentalSupplyVO = this.rentalSupplyDao.selectOneRentalSupply(rentalSupplyApprovalVO.getRsplId());
	    if (originalRentalSupplyVO == null) {
	        return false;
	    }

	    int requestedQty = rentalSupplyApprovalVO.getRsplRqstQty();
	    int newQty = originalRentalSupplyVO.getInvQty() - requestedQty;
	    if (newQty < 0) {
	        return false; // 재고가 부족한 경우
	    }

	    // 결재 요청 타입(UPDATE)
	    rentalSupplyApprovalVO.setRsplApprType("UPDATE");
	    rentalSupplyApprovalVO.setDelYn("N");
	    
	    rentalSupplyApprovalVO.setRsplName(originalRentalSupplyVO.getRsplName());
	    rentalSupplyApprovalVO.setRsplCtgr(originalRentalSupplyVO.getRsplCtgr());
	    rentalSupplyApprovalVO.setRsplImg(originalRentalSupplyVO.getRsplImg());
	    rentalSupplyApprovalVO.setRsplDtl(originalRentalSupplyVO.getRsplDtl());
	    rentalSupplyApprovalVO.setRsplPrice(originalRentalSupplyVO.getRsplPrice());
	    rentalSupplyApprovalVO.setInvQty(newQty);

	    int requestedCount = this.rentalSupplyApprovalDao.insertRentalSupplyApprovalRequest(rentalSupplyApprovalVO);

	    // 결재 승인자 리스트
	    List<String> approvalList = new ArrayList<>();
	    String tmLeadId = employeeVO.getTeamVO().getTmLeadId();
	    String deptLeadId = employeeVO.getDepartmentVO().getDeptLeadId();
	    DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
	    String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
	    String ceoId = this.employeeDao.getAllEmployee().stream()
	                              .filter(emp -> emp.getPstnId().equals("110"))
	                              .map(emp -> emp.getEmpId())
	                              .findFirst()
	                              .orElse(mgmtSprtDeptLeadId);

	    int price = originalRentalSupplyVO.getRsplPrice() * requestedQty;
	    approvalList.add(tmLeadId);
	    if (price > 1000000) {
	        approvalList.add(deptLeadId);
	    }
	    if (price > 5000000) {
	        approvalList.add(mgmtSprtDeptLeadId);
	    }
	    if (price > 10000000) {
//	        approvalList.add(ceoId);
	    	approvalList.add("system01");
	    }

	    ApprovalVO approvalVO = new ApprovalVO();
	    approvalVO.setApprType("RSUPPLY");
	    approvalVO.setApprInfo(rentalSupplyApprovalVO.getRsplApprId());
	    approvalVO.setApprReqtr(employeeVO.getEmpId());
	    this.approvalDao.insertApproval(approvalList, approvalVO);

	    return requestedCount > 0;
	}

	@Transactional
	@Override
	public boolean updateOneRentalSupplyStock(RentalSupplyVO rentalSupplyVO) {
		int updatedCount = this.rentalSupplyDao.updateOneRentalSupplyStock(rentalSupplyVO);
		
		return updatedCount > 0;
	}

	@Transactional
	@Override
	public boolean requestDeleteRentalSupply(RentalSupplyApprovalVO rentalSupplyApprovalVO) {
		rentalSupplyApprovalVO.setRsplApprType("UPDATE");
		rentalSupplyApprovalVO.setDelYn("Y");

		RentalSupplyVO originalRentalSupplyVO = this.rentalSupplyDao.selectOneRentalSupply(rentalSupplyApprovalVO.getRsplId());

		if (originalRentalSupplyVO != null) {
			String storedImage = originalRentalSupplyVO.getRsplImg();

			if (storedImage != null && storedImage.length() > 0) {
				this.fileHandler.deleteFileByFileName(storedImage);
			}
		}

		int deletedCount = this.rentalSupplyApprovalDao.insertRentalSupplyApprovalRequest(rentalSupplyApprovalVO);

		List<String> approvalList = new ArrayList<>();

		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(rentalSupplyApprovalVO.getRsplApprReqtr());
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		String ceoId = this.employeeDao.getAllEmployee().stream().filter(emp -> emp.getPstnId().equals("110"))
				.map(emp -> emp.getEmpId()).findFirst().orElse(mgmtSprtDeptLeadId);

		int price = rentalSupplyApprovalVO.getRsplPrice() * rentalSupplyApprovalVO.getInvQty();
		approvalList.add(mgmtSprtDeptLeadId);
		if (price > 10000000) {
//			approvalList.add(ceoId);
			approvalList.add("system01");
		}

		ApprovalVO approvalVO = new ApprovalVO();
		approvalVO.setApprType("RSUPPLY");
		// apprInfo: 결재시 업데이트 해야하는 정보를 담은 FK ID
		approvalVO.setApprInfo(rentalSupplyApprovalVO.getRsplApprId());
		// apprReqtr: 결재 요청자
		approvalVO.setApprReqtr(employeeVO.getEmpId());
		this.approvalDao.insertApproval(approvalList, approvalVO);

		return deletedCount > 0;
	}

	@Override
	public RentalSupplyApprovalListVO searchAllRentalSupplyApprovalLog(SearchRentalSupplyVO searchRentalSupplyVO) {
		int rentalSupplyApprovalListCount = this.rentalSupplyApprovalDao.searchRentalSupplyAllApprovalLogCount(searchRentalSupplyVO);
		searchRentalSupplyVO.setPageCount(rentalSupplyApprovalListCount);

		List<RentalSupplyApprovalVO> rentalSupplyApprovalList = this.rentalSupplyApprovalDao.searchAllApprovalLog(searchRentalSupplyVO);

		RentalSupplyApprovalListVO rentalSupplyApprovalListVO = new RentalSupplyApprovalListVO();
		rentalSupplyApprovalListVO.setRentalSupplyApprovalCnt(rentalSupplyApprovalListCount);
		rentalSupplyApprovalListVO.setRentalSupplyApprovalList(rentalSupplyApprovalList);

		return rentalSupplyApprovalListVO;
	}

	@Transactional
	@Override
	public boolean requestReturnRentalSupply(RentalSupplyApprovalVO rentalSupplyApprovalVO) {
		RentalSupplyApprovalVO rentalSupplyApprovalData = this.rentalSupplyApprovalDao.getRentalSupplyApprovalByPK(rentalSupplyApprovalVO.getRsplApprId());
	    EmployeeVO employeeVO = this.employeeDao.getOneEmployee(rentalSupplyApprovalData.getRsplApprReqtr());

	    // 원래 소모품 정보를 가져옴
	    RentalSupplyVO originalRentalSupplyVO = this.rentalSupplyDao.selectOneRentalSupply(rentalSupplyApprovalData.getRsplId());
	    if (originalRentalSupplyVO == null) {
	        return false;
	    }
	    
	    int requestedQty = rentalSupplyApprovalVO.getRsplRqstQty();
	    int newQty = originalRentalSupplyVO.getInvQty() + requestedQty;
	    
	    originalRentalSupplyVO.setInvQty(newQty);
	    
	    rentalSupplyApprovalVO.setInvQty(newQty);
	    
	    int requestedCount = this.rentalSupplyApprovalDao.updateOneRentalSupplyForReturn(rentalSupplyApprovalVO);
	    this.rentalSupplyDao.updateOneRentalSupply(originalRentalSupplyVO);
	    
//	    List<String> approvalList = new ArrayList<>();
//	    DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
//	    String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
//	    
//	    approvalList.add(mgmtSprtDeptLeadId);
//	    
//	    ApprovalVO approvalVO = new ApprovalVO();
//	    approvalVO.setApprType("RSUPPLY");
//	    approvalVO.setApprInfo(rentalSupplyApprovalVO.getRsplApprId());
//	    approvalVO.setApprReqtr(employeeVO.getEmpId());
//	    this.approvalDao.insertApproval(approvalList, approvalVO);

	    return requestedCount > 0;

	}

	
}
