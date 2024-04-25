package com.ktdsuniversity.edu.pms.team.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

public interface TeamService {

	public boolean createNewTeam(TeamVO teamVO);

	public TeamVO selectOneTeam(String teamId);

	public boolean modifyOneTeam(TeamVO teamVO);
	
	public TeamListVO getOnlyTeam();

	public TeamListVO getAllTeamList(String deptId);

	public boolean isPossibleDelete(String teamId);

	public boolean deleteOneTeam(String teamId);
	
	public List<EmployeeVO> getAllEmployeeInTeam(String teamId);

}
