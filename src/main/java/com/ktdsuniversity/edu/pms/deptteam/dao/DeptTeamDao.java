package com.ktdsuniversity.edu.pms.deptteam.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;

public interface DeptTeamDao {
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.deptteam.dao.DeptTeamDao";

	public int getDepartmentCount();

	public List<DepartmentVO> getAllDepartment();
	
	
}
