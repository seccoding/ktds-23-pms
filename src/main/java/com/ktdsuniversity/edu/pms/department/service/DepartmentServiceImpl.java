package com.ktdsuniversity.edu.pms.department.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.approval.dao.ApprovalDao;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.changehistory.dao.ChangeHistoryDao;
import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.department.dao.DepartmentApprovalDao;
import com.ktdsuniversity.edu.pms.department.dao.DepartmentDao;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentApprovalVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.team.dao.TeamDao;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;


@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private ChangeHistoryDao changeHistoryDao;
	
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentApprovalDao departmentApprovalDao;
	
	@Autowired
	private ApprovalDao approvalDao;
	
	@Override
	public DepartmentListVO getAllDepartment() {
		
		int departmentCount = this.departmentDao.getDepartmentCount();
		List<DepartmentVO> departmentList = this.departmentDao.getAllDepartment();
		
		DepartmentListVO departmentListVO = new DepartmentListVO();
		departmentListVO.setDepartmentCnt(departmentCount);
		departmentListVO.setDepartmentList(departmentList);
		
		return departmentListVO;
	}

	@Transactional
	@Override
	public boolean createNewDepartment(DepartmentApprovalVO departmentApprovalVO) {
		
		departmentApprovalVO.setDeptApprType("INSERT");
		departmentApprovalVO.setDelYn("N");
		
		int requestedCount = this.departmentApprovalDao.insertDepartmentApprovalRequest(departmentApprovalVO);
		
		// 결재 승인자 리스트
		List<String> approvalList = new ArrayList<>();
		// 결재 요청자 정보 **(Mapper에 팀장, 부서장 조회하는 코드 추가 필요)
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(departmentApprovalVO.getDeptApprReqtr());
				
		// 경영지원부 정보
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		// 경영지원부서장
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		// 대표이사
		String ceoId = this.employeeDao.getAllEmployee().stream()
																.filter(emp -> emp.getPstnId().equals("110"))
																.map(emp -> emp.getEmpId())		
																.findFirst().orElse(mgmtSprtDeptLeadId);
		approvalList.add(mgmtSprtDeptLeadId);
		approvalList.add(ceoId);
		

		ApprovalVO approvalVO = new ApprovalVO();
		// apprType: 결재 승인 타입(소모품, 비품, 부서, 직원)
		approvalVO.setApprType("DEPARTMENT");
		// apprInfo: 결재시 업데이트 해야하는 정보를 담은 FK ID
		approvalVO.setApprInfo(departmentApprovalVO.getDeptApprId());
		// apprReqtr: 결재 요청자
		approvalVO.setApprReqtr(employeeVO.getEmpId());
		this.approvalDao.insertApproval(approvalList, approvalVO);
		
		return requestedCount > 0;
		
//		세영이형.. 쓰세요..
//		// 새로운 부서 생성
//				int insertedCount = this.departmentDao.createNewDepartment(departmentApprovalVO);
////				this.approvalDao.insertApproval(결제승인라인, ApprovalVO)
//				
////				EmployeeVO changeDeptEmpl = this.employeeDao.getOneEmployee(departmentVO.getDeptLeadId());
//				
//				return insertedCount > 0 ;
	}

	@Override
	public boolean isPossibleDelete(String id) {
		boolean isPossibleDelete = this.teamDao.countTeamInDepartement(id) == 0;
		
		return isPossibleDelete;
	}

	@Override
	public DepartmentListVO getOnlyDepartment(String deptId) {
		
		List<DepartmentVO> onlyDepartmentListVO = this.departmentDao.getOnlyDepartment(deptId);
		DepartmentListVO departmentListVO = new DepartmentListVO();
		departmentListVO.setDepartmentList(onlyDepartmentListVO);
		return departmentListVO;
	}

	@Override
	public DepartmentVO selectOneDepartment(String departmentId) {
		
		DepartmentVO departmentVO = this.departmentDao.getOneDepartment(departmentId);
		
		return departmentVO;
	}

	@Transactional
	@Override
	public boolean modifyOneDepartment(DepartmentApprovalVO departmentApprovalVO) {

		departmentApprovalVO.setDeptApprType("UPDATE");
		departmentApprovalVO.setDelYn("N");
		
		int requestedCount = this.departmentApprovalDao.insertDepartmentApprovalRequest(departmentApprovalVO);
		
		// 결재 승인자 리스트
		List<String> approvalList = new ArrayList<>();
		// 결재 요청자 정보 **(Mapper에 팀장, 부서장 조회하는 코드 추가 필요)
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(departmentApprovalVO.getDeptApprReqtr());
				
		// 경영지원부 정보
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		// 경영지원부서장
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		// 대표이사
		String ceoId = this.employeeDao.getAllEmployee().stream()
																.filter(emp -> emp.getPstnId().equals("110"))
																.map(emp -> emp.getEmpId())		
																.findFirst().orElse(mgmtSprtDeptLeadId);
		approvalList.add(mgmtSprtDeptLeadId);
		approvalList.add(ceoId);

		ApprovalVO approvalVO = new ApprovalVO();
		// apprType: 결재 승인 타입(소모품, 비품, 부서, 직원)
		approvalVO.setApprType("DEPARTMENT");
		// apprInfo: 결재시 업데이트 해야하는 정보를 담은 FK ID
		approvalVO.setApprInfo(departmentApprovalVO.getDeptApprId());
		// apprReqtr: 결재 요청자
		approvalVO.setApprReqtr(employeeVO.getEmpId());
		this.approvalDao.insertApproval(approvalList, approvalVO);
		
		return requestedCount > 0;

		// return departmentDao.updateOneDepartment(departmentVO) > 0;
	}

	@Transactional
	@Override
	public boolean deleteOneDepartment(DepartmentApprovalVO departmentApprovalVO) {
		
		departmentApprovalVO.setDeptApprType("DELETE");
		departmentApprovalVO.setDelYn("N");

		int requestedCount = this.departmentApprovalDao.insertDepartmentApprovalRequest(departmentApprovalVO);
		// 결재 승인자 리스트
		List<String> approvalList = new ArrayList<>();
		// 결재 요청자 정보 **(Mapper에 팀장, 부서장 조회하는 코드 추가 필요)
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(departmentApprovalVO.getDeptApprReqtr());
				
		// 경영지원부 정보
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		// 경영지원부서장
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		// 대표이사
		String ceoId = this.employeeDao.getAllEmployee().stream()
														.filter(emp -> emp.getPstnId().equals("110"))
														.map(emp -> emp.getEmpId())		
														.findFirst().orElse(mgmtSprtDeptLeadId);
		approvalList.add(mgmtSprtDeptLeadId);
		approvalList.add(ceoId);

		ApprovalVO approvalVO = new ApprovalVO();
		// apprType: 결재 승인 타입(소모품, 비품, 부서, 직원)
		approvalVO.setApprType("DEPARTMENT");
		// apprInfo: 결재시 업데이트 해야하는 정보를 담은 FK ID
		approvalVO.setApprInfo(departmentApprovalVO.getDeptApprId());
		// apprReqtr: 결재 요청자
		approvalVO.setApprReqtr(employeeVO.getEmpId());
		this.approvalDao.insertApproval(approvalList, approvalVO);

		return requestedCount > 0;
		// return departmentDao.deleteOneDepartment(deptId) > 0;
	}

	@Override
	public String getDepartmentNameById(String deptId) {
		return departmentDao.getDepartmentNameById(deptId);
	}

	@Override
	public int getDepartMent(String id) {
		// TODO Auto-generated method stub
		return departmentDao.getDepartment(id);
	}

	@Override
	public String getOnlypstnid(String pstnid) {
		// TODO Auto-generated method stub
		return this.departmentDao.getOnlypstnid(pstnid);
	}


	@Override
	public List<EmployeeVO> getEmpByDeptId(String deptId) {
		List<EmployeeVO> empList =  this.departmentDao.getEmpByDeptId(deptId);
		return empList;
	}

	@Override
	public boolean getDeptIdByName(DepartmentVO departmentVO) {
		return this.departmentDao.getDeptIdByName(departmentVO.getDeptName()) == null;
	}


	
}
