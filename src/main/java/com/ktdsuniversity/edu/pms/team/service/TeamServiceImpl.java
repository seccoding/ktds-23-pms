package com.ktdsuniversity.edu.pms.team.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.approval.dao.ApprovalDao;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.department.dao.DepartmentDao;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.team.dao.TeamApprovalDao;
import com.ktdsuniversity.edu.pms.team.dao.TeamDao;
import com.ktdsuniversity.edu.pms.team.vo.TeamApprovalVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

@Service
public class TeamServiceImpl implements TeamService{

	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private TeamApprovalDao teamApprovalDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private ApprovalDao approvalDao;
	
	@Transactional
	@Override
	public boolean createNewTeam(TeamApprovalVO teamApprovalVO) {
		teamApprovalVO.setTmApprType("INSERT");
		teamApprovalVO.setDelYn("N");
		
		int requestedCount = this.teamApprovalDao.insertTeamApprovalRequest(teamApprovalVO);

		// 결재 승인자 리스트
		List<String> approvalList = new ArrayList<>();
		// 결재 요청자 정보 **(Mapper에 팀장, 부서장 조회하는 코드 추가 필요)
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(teamApprovalVO.getTmApprReqtr());		// 결재 요청자가 속한 팀의 장
		String tmLeadId = employeeVO.getTeamVO().getTmLeadId();				
		// 경영지원부 정보
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		// 경영지원부서장
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		// 대표이사
		String ceoId = this.employeeDao.getAllEmployee().stream()
																.filter(emp -> emp.getPstnId().equals("110"))
																.map(emp -> emp.getEmpId())		
																.findFirst().orElse(mgmtSprtDeptLeadId);
		approvalList.add(tmLeadId);
		approvalList.add(mgmtSprtDeptLeadId);
		approvalList.add(ceoId);
		
		ApprovalVO approvalVO = new ApprovalVO();
		// apprType: 결재 승인 타입(소모품, 비품, 부서, 직원)
		approvalVO.setApprType("TEAM");
		// apprInfo: 결재시 업데이트 해야하는 정보를 담은 FK ID
		approvalVO.setApprInfo(teamApprovalVO.getTmApprId());
		// apprReqtr: 결재 요청자
		approvalVO.setApprReqtr(employeeVO.getEmpId());
		this.approvalDao.insertApproval(approvalList, approvalVO);
		
		return requestedCount > 0;
//		세영이형 가져와
//		int insertedCount = this.teamDao.createNewTeam(teamVO);
//		return insertedCount > 0;
	}

	@Override
	public TeamVO selectOneTeam(String teamId) {
		TeamVO teamVO = this.teamDao.getOneTeam(teamId);
		return teamVO;
	}

	@Transactional
	@Override
	public boolean modifyOneTeam(TeamApprovalVO teamApprovalVO) {
		teamApprovalVO.setTmApprType("UPDATE");
		teamApprovalVO.setDelYn("N");
		
		int requestedCount = this.teamApprovalDao.insertTeamApprovalRequest(teamApprovalVO);

		// 결재 승인자 리스트
		List<String> approvalList = new ArrayList<>();
		// 결재 요청자 정보 **(Mapper에 팀장, 부서장 조회하는 코드 추가 필요)
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(teamApprovalVO.getTmApprReqtr());		// 결재 요청자가 속한 팀의 장
		String tmLeadId = employeeVO.getTeamVO().getTmLeadId();				
		// 경영지원부 정보
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		// 경영지원부서장
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		// 대표이사
		String ceoId = this.employeeDao.getAllEmployee().stream()
																.filter(emp -> emp.getPstnId().equals("110"))
																.map(emp -> emp.getEmpId())		
																.findFirst().orElse(mgmtSprtDeptLeadId);
		approvalList.add(tmLeadId);
		approvalList.add(mgmtSprtDeptLeadId);
		approvalList.add(ceoId);
		
		ApprovalVO approvalVO = new ApprovalVO();
		// apprType: 결재 승인 타입(소모품, 비품, 부서, 직원)
		approvalVO.setApprType("TEAM");
		// apprInfo: 결재시 업데이트 해야하는 정보를 담은 FK ID
		approvalVO.setApprInfo(teamApprovalVO.getTmApprId());
		// apprReqtr: 결재 요청자
		approvalVO.setApprReqtr(employeeVO.getEmpId());
		this.approvalDao.insertApproval(approvalList, approvalVO);
		
		return requestedCount > 0;
		// return teamDao.updateOneTeam(teamVO) > 0;
	}

	@Override
	public TeamListVO getOnlyTeam() {
		
		List<TeamVO> onlyTeamListVO = this.teamDao.getOnlyTeam();
		TeamListVO teamListVO = new TeamListVO();
		teamListVO.setTeamList(onlyTeamListVO);
		return teamListVO;
	}

	@Override
	public TeamListVO getAllTeamList(String deptId) {
		List<TeamVO> getAllTeamListVO = this.teamDao.getAllTeamList(deptId);
		TeamListVO teamListVO = new TeamListVO();
		teamListVO.setTeamList(getAllTeamListVO);
		return teamListVO;
	}

	@Override
	public boolean isPossibleDelete(String teamId) {
		return teamDao.isPossibleDelete(teamId) == 0;
	}

	@Transactional
	@Override
	public boolean deleteOneTeam(TeamApprovalVO teamApprovalVO) {
		
		teamApprovalVO.setTmApprType("DELETE");
		teamApprovalVO.setDelYn("N");
		
		int requestedCount = this.teamApprovalDao.insertTeamApprovalRequest(teamApprovalVO);

		// 결재 승인자 리스트
		List<String> approvalList = new ArrayList<>();
		// 결재 요청자 정보 **(Mapper에 팀장, 부서장 조회하는 코드 추가 필요)
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(teamApprovalVO.getTmApprReqtr());		// 결재 요청자가 속한 팀의 장
		String tmLeadId = employeeVO.getTeamVO().getTmLeadId();				
		// 경영지원부 정보
		DepartmentVO mgmtSprtDeptVO = this.departmentDao.getOneDepartment("DEPT_230101_000010");
		// 경영지원부서장
		String mgmtSprtDeptLeadId = mgmtSprtDeptVO.getDeptLeadId();
		// 대표이사
		String ceoId = this.employeeDao.getAllEmployee().stream()
																.filter(emp -> emp.getPstnId().equals("110"))
																.map(emp -> emp.getEmpId())		
																.findFirst().orElse(mgmtSprtDeptLeadId);
		approvalList.add(tmLeadId);
		approvalList.add(mgmtSprtDeptLeadId);
		approvalList.add(ceoId);
		
		ApprovalVO approvalVO = new ApprovalVO();
		// apprType: 결재 승인 타입(소모품, 비품, 부서, 직원)
		approvalVO.setApprType("TEAM");
		// apprInfo: 결재시 업데이트 해야하는 정보를 담은 FK ID
		approvalVO.setApprInfo(teamApprovalVO.getTmApprId());
		// apprReqtr: 결재 요청자
		approvalVO.setApprReqtr(employeeVO.getEmpId());
		this.approvalDao.insertApproval(approvalList, approvalVO);
		
		return requestedCount > 0;

		// return teamDao.deleteOneTeam(teamId) > 0;
	}

	@Override
	public List<EmployeeVO> getAllEmployeeInTeam(String teamId) {
		List<EmployeeVO> empListInTeam = this.teamDao.getAllEmployeeInTeam(teamId);
		return empListInTeam;
	}

	/**
	 * 팀에 멤버 추가 ==> employee 테이블 수정을 통해 사원의 소속된 팀을 변경하는 걸로 추가
	 */
	@Transactional
	@Override
	public boolean createNewTeamMember(EmployeeVO employeeVO) {
		int issuccess =this.employeeDao.modifyEmployeeTeam(employeeVO);
		return issuccess > 0;
	}

}
