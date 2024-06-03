package com.ktdsuniversity.edu.pms.deptteam.service;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;

public interface DeptTeamService {

	DepartmentListVO getAllDepartment();

	DepartmentListVO getOnlyDepartment();

	TeamListVO getOnlyTeam();

}
