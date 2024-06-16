package com.ktdsuniversity.edu.pms.team.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamApprovalVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

public interface TeamService {

	public boolean createNewTeam(TeamApprovalVO teamApprovalVO);

	public TeamVO selectOneTeam(String teamId);

	public boolean modifyOneTeam(TeamApprovalVO teamApprovalVO);
	
	public TeamListVO getOnlyTeam();

	public TeamListVO getAllTeamList(String deptId);

	public boolean isPossibleDelete(String teamId);

	public boolean deleteOneTeam(TeamApprovalVO teamApprovalVO);
	
	public List<EmployeeVO> getAllEmployeeInTeam(String teamId);

	public boolean createNewTeamMember(EmployeeVO employeeVO);

}
