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

//	@Transactional
//	@Override
//	public boolean registerNewSupply(SupplyVO supplyVO, MultipartFile file) {
//		if (file != null && !file.isEmpty()) {
//			StoredFile storedFile = fileHandler.storeFile(file);
//			
//			if (storedFile != null) {
//				supplyVO.setSplImg(storedFile.getRealFileName());
//			}
//		}
//		
//		int registeredCount = this.supplyDao.registerNewSupply(supplyVO);
//		
//		return registeredCount > 0;
//	}
	
	@Transactional
	@Override
	public boolean requestRegistrationNewSupply(SupplyApprovalVO supplyApprovalVO, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			StoredFile storedFile = fileHandler.storeFile(file);
			
			if (storedFile != null) {
				supplyApprovalVO.setSplImg(storedFile.getRealFileName());
			}			
		}
		
		int requestedCount = this.supplyApprovalDao.insertSupplyRegistrationRequest(supplyApprovalVO);
		
		List<String> approvalList = new ArrayList<>();
		
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(supplyApprovalVO.getSplRegtId());
		String tmLeadId = employeeVO.getTeamVO().getTmLeadId();
		String deptLeadId = employeeVO.getDepartmentVO().getDeptLeadId();
		
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		
		String ceoId = this.employeeDao.getAllEmployee().stream().filter(emp -> emp.getPstnId().equals("110")).toString();
		
		int price = supplyApprovalVO.getSplPrice() * supplyApprovalVO.getInvQty();
		
		approvalList.add(tmLeadId);
		
		if (price > 1000000) {
			approvalList.add(deptLeadId);
		}
		if (price > 5000000) {
			approvalList.add(mgmtSprtDeptLeadId);
		}
		if (price > 10000000) {
			approvalList.add(ceoId);
		}
		
		ApprovalVO approvalVO = new ApprovalVO();
		approvalVO.setApprType("INSERT");
		approvalVO.setApprReqtr(supplyApprovalVO.getSplRegtId());
		
		this.approvalDao.insertApproval(approvalList, approvalVO);
		
		return requestedCount > 0;
	}

	@Transactional
	@Override
	public boolean updateOneSupply(SupplyVO supplyVO) {
		int updatedCount = this.supplyDao.updateOneSupply(supplyVO);
		
		return updatedCount > 0;
	}
	
	@Transactional
	@Override
	public boolean updateOneSupplyStock(SupplyVO supplyVO) {
		int updatedCount = this.supplyDao.updateOneSupplyStock(supplyVO);
		
		return updatedCount > 0;
	}

	@Transactional
	@Override
	public boolean deleteOneSupply(SupplyVO supplyVO) {
		SupplyVO originalSupplyVO = this.supplyDao.selectOneSupply(supplyVO.getSplId());
		
		if (originalSupplyVO != null) {
			String storedImage = originalSupplyVO.getSplImg();
			
			if (storedImage != null && storedImage.length() > 0) {
				this.fileHandler.deleteFileByFileName(storedImage);
			}
		}
		
		int deletedCount = this.supplyDao.deleteOneSupply(supplyVO);
		
		return deletedCount > 0;
	}

	@Override
	public SupplyLogListVO searchAllSupplyLog(SearchSupplyVO searchSupplyVO) {
		int supplyLogCount = this.supplyLogDao.searchSupplyLogAllCount(searchSupplyVO);
		searchSupplyVO.setPageCount(supplyLogCount);
		
		List<SupplyLogVO> supplyLogList = this.supplyLogDao.searchAllSupplyLog(searchSupplyVO);
		
		SupplyLogListVO supplyLogListVO = new SupplyLogListVO();
		supplyLogListVO.setSupplyLogCnt(supplyLogCount);
		supplyLogListVO.setSupplyLogList(supplyLogList);
		
		return supplyLogListVO;
	}

}
