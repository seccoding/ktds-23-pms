package com.ktdsuniversity.edu.pms.department.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;

public interface DepartmentService {

	public DepartmentListVO getAllDepartment();
	
	public boolean createNewDepartment(DepartmentVO departmentVO);
	
	public boolean isPossibleDelete(String id);

	public DepartmentListVO getOnlyDepartment();

	public DepartmentVO selectOneDepartment(String departmentId);

	public boolean modifyOneDepartment(DepartmentVO departmentVO);

	public boolean deleteOneDepartment(String deptId);

	public String getDepartmentNameById(String deptId);
	

	public int  getDepartMent(String id);

	public String  getOnlypstnid(String pstnid);

	public List<EmployeeVO> getEmpByDeptId(String deptId);

	public boolean getDeptIdByName(DepartmentVO departmentVO);

	



}
