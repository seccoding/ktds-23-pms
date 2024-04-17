package com.ktdsuniversity.edu.pms.department.service;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;

public interface DepartmentService {

	public DepartmentListVO getAllDepartment();
	
	public boolean createNewDepartment(DepartmentVO departmentVO);
	
	public boolean deleteOneDepartment(String id);

	public DepartmentListVO getOnlyDepartment();

	public DepartmentVO selectOneDepartment(String departmentId);

	public boolean modifyOneDepartment(DepartmentVO departmentVO);

}
