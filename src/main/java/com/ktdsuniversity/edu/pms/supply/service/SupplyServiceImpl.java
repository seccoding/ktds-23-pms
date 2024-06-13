package com.ktdsuniversity.edu.pms.supply.service;

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
import com.ktdsuniversity.edu.pms.supply.dao.SupplyApprovalDao;
import com.ktdsuniversity.edu.pms.supply.dao.SupplyDao;
import com.ktdsuniversity.edu.pms.supply.dao.SupplyLogDao;
import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyApprovalListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyApprovalVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyLogListVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyLogVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;

@Service
public class SupplyServiceImpl implements SupplyService {
	
	@Autowired
	private SupplyDao supplyDao;
	
	@Autowired
	private SupplyApprovalDao supplyApprovalDao;
	
	@Autowired
	private ApprovalDao approvalDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private SupplyLogDao supplyLogDao;
	
	@Autowired
	private FileHandler fileHandler;

	@Override
	public SupplyListVO searchAllSupply(SearchSupplyVO searchSupplyVO) {
		int supplyCount = this.supplyDao.searchSupplyAllCount(searchSupplyVO);
		searchSupplyVO.setPageCount(supplyCount);
		
		List<SupplyVO> supplyList = this.supplyDao.searchAllSupply(searchSupplyVO);
		
		SupplyListVO supplyListVO = new SupplyListVO();
		supplyListVO.setSupplyCnt(supplyCount);
		supplyListVO.setSupplyList(supplyList);
		
		return supplyListVO;
	}

	@Override
	public SupplyVO getOneSupply(String splId) {
		SupplyVO supplyVO = this.supplyDao.selectOneSupply(splId);
		
		return supplyVO;
	}
	
	@Override
	public SupplyListVO getAllSupplyCategory() {
		List<SupplyVO> supplyCategoryList = this.supplyDao.selectAllSupplyCategory();
		
		SupplyListVO supplyListVO = new SupplyListVO();
		supplyListVO.setSupplyList(supplyCategoryList);
		
		return supplyListVO;
	}

	@Transactional
	@Override
	public boolean requestRegistrationNewSupply(SupplyApprovalVO supplyApprovalVO, MultipartFile file) {
		// 결재 요청 타입(INSERT, UPDATE, DELETE?)
		supplyApprovalVO.setSplApprType("INSERT");
		supplyApprovalVO.setDelYn("N");
		
		if (file != null && !file.isEmpty()) {
			StoredFile storedFile = fileHandler.storeFile(file);
			
			if (storedFile != null) {
				supplyApprovalVO.setSplImg(storedFile.getRealFileName());
			}			
		}
		
		int requestedCount = this.supplyApprovalDao.insertSupplyApprovalRequest(supplyApprovalVO);
		
		// 결재 승인자 리스트
		List<String> approvalList = new ArrayList<>();
		// 결재 요청자 정보 **(Mapper에 팀장, 부서장 조회하는 코드 추가 필요)
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(supplyApprovalVO.getSplApprReqtr());
		// 결재 요청자가 속한 팀의 장
		String tmLeadId = employeeVO.getTeamVO().getTmLeadId();
		// 결재 요청자가 속한 부서의 장
		String deptLeadId = employeeVO.getDepartmentVO().getDeptLeadId();
		// 경영지원부 정보
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		// 경영지원부서장
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		// 대표이사
		String ceoId = this.employeeDao.getAllEmployee().stream()
														.filter(emp -> emp.getPstnId().equals("110"))
														.map(emp -> emp.getEmpId())
														.findFirst().orElse(mgmtSprtDeptLeadId);
		
		// 결재라인 순서(팀장 -> 부서장 -> 경영지원부장 -> 대표이사)
		// 결재라인 조건
		int price = supplyApprovalVO.getSplPrice() * supplyApprovalVO.getInvQty();		
//		approvalList.add(tmLeadId);		**(현재 팀, 부서가 없는 사원이 존재함으로 주석 처리)
		approvalList.add("1111111");
		if (price > 1000000) {
//			approvalList.add(deptLeadId);
			approvalList.add("2222222");
		}
		if (price > 5000000) {
			approvalList.add(mgmtSprtDeptLeadId);
		}
		if (price > 10000000) {
			approvalList.add(ceoId);
		}

		ApprovalVO approvalVO = new ApprovalVO();
		// apprType: 결재 승인 타입(소모품, 비품, 부서, 직원)
		approvalVO.setApprType("SUPPLY");
		// apprInfo: 결재시 업데이트 해야하는 정보를 담은 FK ID
		approvalVO.setApprInfo(supplyApprovalVO.getSplApprId());
		// apprReqtr: 결재 요청자
		approvalVO.setApprReqtr(employeeVO.getEmpId());
		this.approvalDao.insertApproval(approvalList, approvalVO);
		
		return requestedCount > 0;
	}

	@Transactional
	@Override
	public boolean requestModificationSupply(SupplyApprovalVO supplyApprovalVO, MultipartFile file) {
		// 결재 요청 타입(INSERT, UPDATE, DELETE?)
		supplyApprovalVO.setSplApprType("UPDATE");
		supplyApprovalVO.setDelYn("N");
		
		SupplyVO originalSupplyVO = this.supplyDao.selectOneSupply(supplyApprovalVO.getSplId());
		if (file != null && !file.isEmpty()) {
			
			if (originalSupplyVO != null) {
				String storedImage = originalSupplyVO.getSplImg();
				
				if (storedImage != null && storedImage.length() > 0) {
					this.fileHandler.deleteFileByFileName(storedImage);
				}
			}
			StoredFile storedFile = fileHandler.storeFile(file);
			supplyApprovalVO.setSplImg(storedFile.getRealFileName());
		}
		int updatedCount = this.supplyApprovalDao.insertSupplyApprovalRequest(supplyApprovalVO);
		
		// 결재 승인자 리스트
		List<String> approvalList = new ArrayList<>();
		// 결재 요청자 정보 **(Mapper에 팀장, 부서장 조회하는 코드 추가 필요)
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(supplyApprovalVO.getSplApprReqtr());
		// 결재 요청자가 속한 팀의 장
		String tmLeadId = employeeVO.getTeamVO().getTmLeadId();
		// 결재 요청자가 속한 부서의 장
		String deptLeadId = employeeVO.getDepartmentVO().getDeptLeadId();
		// 경영지원부 정보
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		// 경영지원부서장
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		// 대표이사
		String ceoId = this.employeeDao.getAllEmployee().stream()
														.filter(emp -> emp.getPstnId().equals("110"))
														.map(emp -> emp.getEmpId())
														.findFirst().orElse(mgmtSprtDeptLeadId);
		
		// 결재라인 순서(팀장 -> 부서장 -> 경영지원부장 -> 대표이사)
		// 결재라인 조건		
		/*
		 * 공금에 영향을 줄 수 있는 경우
		 * 1. 제품가격 변경 -> 가격 변경만으로는 영향을 주지 않음
		 * 2. 재고 변경
		 */
		int price;
		
//		approvalList.add(tmLeadId);		**(현재 팀, 부서가 없는 사원이 존재함으로 주석 처리)
		approvalList.add("1111111");
		
		if (supplyApprovalVO.getInvQty() != originalSupplyVO.getInvQty()) {
			price = Math.abs(supplyApprovalVO.getSplPrice() * (supplyApprovalVO.getInvQty() - originalSupplyVO.getInvQty()));
			
			if (price > 1000000) {
//				approvalList.add(deptLeadId);
				approvalList.add("2222222");
			}
			if (price > 5000000) {
				approvalList.add(mgmtSprtDeptLeadId);
			}
			if (price > 10000000) {
				approvalList.add(ceoId);
			}
		}
		
		ApprovalVO approvalVO = new ApprovalVO();
		// apprType: 결재 승인 타입(소모품, 비품, 부서, 직원)
		approvalVO.setApprType("SUPPLY");
		// apprInfo: 결재시 업데이트 해야하는 정보를 담은 FK ID
		approvalVO.setApprInfo(supplyApprovalVO.getSplApprId());
		// apprReqtr: 결재 요청자
		approvalVO.setApprReqtr(employeeVO.getEmpId());
		this.approvalDao.insertApproval(approvalList, approvalVO);
		
		return updatedCount > 0;
	}
	
//	@Transactional
//	@Override
//	public boolean updateOneSupply(SupplyVO supplyVO) {
//		int updatedCount = this.supplyDao.updateOneSupply(supplyVO);
//		
//		return updatedCount > 0;
//	}
	
	@Transactional
	@Override
	public boolean updateOneSupplyStock(SupplyVO supplyVO) {
		int updatedCount = this.supplyDao.updateOneSupplyStock(supplyVO);
		
		return updatedCount > 0;
	}

	@Transactional
	@Override
	public boolean requestDeleteSupply(SupplyApprovalVO supplyApprovalVO) {
		supplyApprovalVO.setSplApprType("UPDATE");
		supplyApprovalVO.setDelYn("Y");
		
		SupplyVO originalSupplyVO = this.supplyDao.selectOneSupply(supplyApprovalVO.getSplId());
		
		if (originalSupplyVO != null) {
			String storedImage = originalSupplyVO.getSplImg();
			
			if (storedImage != null && storedImage.length() > 0) {
				this.fileHandler.deleteFileByFileName(storedImage);
			}
		}
		
		int deletedCount = this.supplyApprovalDao.insertSupplyApprovalRequest(supplyApprovalVO);
		
		List<String> approvalList = new ArrayList<>();
		
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(supplyApprovalVO.getSplApprReqtr());
		String tmLeadId = employeeVO.getTeamVO().getTmLeadId();
		String deptLeadId = employeeVO.getDepartmentVO().getDeptLeadId();
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		String ceoId = this.employeeDao.getAllEmployee().stream()
														.filter(emp -> emp.getPstnId().equals("110"))
														.map(emp -> emp.getEmpId())
														.findFirst().orElse(mgmtSprtDeptLeadId);
		
		int price = supplyApprovalVO.getSplPrice() * supplyApprovalVO.getInvQty();		
//		approvalList.add(tmLeadId);
		approvalList.add("1111111");
		if (price > 1000000) {
//			approvalList.add(deptLeadId);
			approvalList.add("2222222");
		}
		if (price > 5000000) {
			approvalList.add(mgmtSprtDeptLeadId);
		}
		if (price > 10000000) {
			approvalList.add(ceoId);
		}
		
		ApprovalVO approvalVO = new ApprovalVO();
		approvalVO.setApprType("SUPPLY");
		// apprInfo: 결재시 업데이트 해야하는 정보를 담은 FK ID
		approvalVO.setApprInfo(supplyApprovalVO.getSplApprId());
		// apprReqtr: 결재 요청자
		approvalVO.setApprReqtr(employeeVO.getEmpId());
		this.approvalDao.insertApproval(approvalList, approvalVO);
		
		return deletedCount > 0;
	}

//	@Override
//	public SupplyLogListVO searchAllSupplyLog(SearchSupplyVO searchSupplyVO) {
//		int supplyLogCount = this.supplyLogDao.searchSupplyLogAllCount(searchSupplyVO);
//		searchSupplyVO.setPageCount(supplyLogCount);
//		
//		List<SupplyLogVO> supplyLogList = this.supplyLogDao.searchAllSupplyLog(searchSupplyVO);
//		
//		SupplyLogListVO supplyLogListVO = new SupplyLogListVO();
//		supplyLogListVO.setSupplyLogCnt(supplyLogCount);
//		supplyLogListVO.setSupplyLogList(supplyLogList);
//		
//		return supplyLogListVO;
//	}

	@Override
	public SupplyApprovalListVO searchAllSupplyApprovalLog(SearchSupplyVO searchSupplyVO) {
		int supplyApprovalListCount = this.supplyApprovalDao.searchSupplyAllApprovalLogCount(searchSupplyVO);
		searchSupplyVO.setPageCount(supplyApprovalListCount);
		
		List<SupplyApprovalVO> supplyApprovalList = this.supplyApprovalDao.searchAllApprovalLog(searchSupplyVO);
		
		SupplyApprovalListVO supplyApprovalListVO = new SupplyApprovalListVO();
		supplyApprovalListVO.setSupplyApprovalCnt(supplyApprovalListCount);
		supplyApprovalListVO.setSupplyApprovalList(supplyApprovalList);
		
		return supplyApprovalListVO;
	}

}
