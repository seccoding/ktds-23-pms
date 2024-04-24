package com.ktdsuniversity.edu.pms.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.beans.SHA;
import com.ktdsuniversity.edu.pms.changehistory.dao.ChangeHistoryDao;
import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private SHA sha;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ChangeHistoryDao changeHistoryDao;
	
	@Override
	public EmployeeListVO getAllEmployee() {
		
		int employeeCount = this.employeeDao.getAllEmployeeCount();
		List<EmployeeVO> employeeList = this.employeeDao.getAllEmployee();
		
		EmployeeListVO employeeListVO = new EmployeeListVO();
		employeeListVO.setEmployeeCnt(employeeCount);
		employeeListVO.setEmployeeList(employeeList);
		
		return employeeListVO;
	}

	@Override
	public EmployeeListVO searchAllEmployee(SearchEmployeeVO searchEmployeeVO) {
		
		int employeeCount = this.employeeDao.searchEmployeeAllCount(searchEmployeeVO);
		searchEmployeeVO.setPageCount(employeeCount);
		List<EmployeeVO> employeeList = this.employeeDao.searchAllEmployee(searchEmployeeVO);
		
		EmployeeListVO employeeListVO = new EmployeeListVO();
		employeeListVO.setEmployeeCnt(employeeCount);
		employeeListVO.setEmployeeList(employeeList);
		return employeeListVO;
	}
	
	

	@Override
	public boolean deleteEmployee(String empId) {
		
		return this.employeeDao.deleteEmployeeById(empId) > 0;
	}
	
	@Override
	public boolean modifyEmployee(EmployeeVO employeeVO) {
		
		int updatedCount = this.employeeDao.modifyEmployee(employeeVO);
		return updatedCount > 0;
	}

	
	@Override
	public EmployeeVO getOneEmployee(String empId) {
		
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(empId);
		List<TeamVO> teamList = this.employeeDao.getEmployeeAllTeam(empId);
		
	
		employeeVO.setTeamList(teamList);
			
		return employeeVO;
		
	}

	public boolean createEmployee(EmployeeVO employeeVO) {
		String pwd = employeeVO.getPwd();
		String salt = this.sha.generateSalt();
		pwd = this.sha.getEncrypt(pwd, salt);

		employeeVO.setPwd(pwd);
		employeeVO.setSalt(salt);
		
		int createSuccessCount = employeeDao.createEmployee(employeeVO);
		
		return createSuccessCount > 0;
	}

	@Override
	public EmployeeListVO searchEmpDeptId(SearchEmployeeVO searchEmployeeVO) {
		
		List<EmployeeVO> employeeList = this.employeeDao.searchEmpDeptId(searchEmployeeVO);
		
		EmployeeListVO employeeListVO = new EmployeeListVO();
		employeeListVO.setEmployeeList(employeeList);
		
		return employeeListVO;
	}

	@Override
	public List<EmployeeVO> findEmployeesByTeamId(String teamId) {
		return this.employeeDao.findEmployeesByTeamId(teamId);
	}
	
	@Override
	public int getOneEmpIdIsExist(String empId) {
		return this.employeeDao.getOneEmpIdIsExist(empId);
	}

	@Transactional
	@Override
	public boolean modifyOneEmployee(EmployeeVO employeeVO) {
		EmployeeVO originEmployee = this.employeeDao.getOneEmployee(employeeVO.getEmpId());
		
		int updatedCount = this.employeeDao.modifyOneEmployee(employeeVO);
		
		// 부서가 변경된 경우 부서 변경 이력 추가
		if(!originEmployee.getDeptId().equals( employeeVO.getDeptId())) {
			
			List<DepartmentHistoryVO> deptHistList = this.changeHistoryDao.getAllDeptHist(employeeVO.getEmpId());
			// 기존 이력 존재할 경우 최근 이력의 end날짜를 시작 날짜로 설정
			if(deptHistList.size() > 0) {
				String provDate = this.changeHistoryDao.getRecentDeptHist(employeeVO.getEmpId());
				
				employeeVO.setHireDt(provDate);		
			}
			
			employeeVO.setDeptId(originEmployee.getDeptId());
			int insertCnt = 0; 
			insertCnt =	this.changeHistoryDao.insertOneChangeDeptHistory(employeeVO);
			
			if(insertCnt == 0) {
				return false;
			}
			
		}
		
		// 팀리스트가 추가되었을 경우
		if(employeeVO.getTeamList()!=null) {
			// 기존의 팀 리스트를 originEmployee에 할당
			originEmployee.setTeamList(this.employeeDao.getEmployeeAllTeam(employeeVO.getEmpId()));
			int willAddTeam = 0;
			int addTeamCount = 0;
			for (TeamVO changeTeam: employeeVO.getTeamList()) {
				// 기존리스트 없으면 다 추가
				if(originEmployee.getTeamList() == null) {
					employeeVO.setTeamVO(changeTeam);
					willAddTeam++;
					addTeamCount = this.employeeDao.addTeam(employeeVO);
				}
				// 기존 리스트 있으면 기존 팀에 없는 팀만 추가
				else if (!originEmployee.getTeamList().contains(changeTeam)) {
					employeeVO.setTeamVO(changeTeam);
					willAddTeam++;
					addTeamCount = this.employeeDao.addTeam(employeeVO);
				}
				
			}
			if(willAddTeam!=addTeamCount) {
				return false;
			}
			
		} 
		
		
		
		return updatedCount > 0;
	}

	@Transactional
	@Override
	public boolean deleteTeam(EmployeeVO employeeVO) {
		return this.employeeDao.deleteTeam(employeeVO) > 0;
	}

	

}
