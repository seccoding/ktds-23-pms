package com.ktdsuniversity.edu.pms.team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.team.dao.TeamDao;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

@Service
public class TeamServiceImpl implements TeamService{

	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional
	@Override
	public boolean createNewTeam(TeamVO teamVO) {
		int insertedCount = this.teamDao.createNewTeam(teamVO);
		return insertedCount > 0;
	}

	@Override
	public TeamVO selectOneTeam(String teamId) {
		TeamVO teamVO = this.teamDao.getOneTeam(teamId);
		return teamVO;
	}

	@Transactional
	@Override
	public boolean modifyOneTeam(TeamVO teamVO) {
		return teamDao.updateOneTeam(teamVO) > 0;
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
	public boolean deleteOneTeam(String teamId) {
		return teamDao.deleteOneTeam(teamId) > 0;
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
