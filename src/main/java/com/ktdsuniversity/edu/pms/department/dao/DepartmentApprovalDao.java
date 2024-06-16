package com.ktdsuniversity.edu.pms.department.dao;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentApprovalVO;

public interface DepartmentApprovalDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.department.dao.DepartmentApprovalDao";

	public int insertDepartmentApprovalRequest(DepartmentApprovalVO departmentApprovalVO);
	
	public DepartmentApprovalVO getDepartmentApprovalByPK(String deptApprId); 
}
