package com.ktdsuniversity.edu.pms.department.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentApprovalVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public interface DepartmentService {

	public DepartmentListVO getAllDepartment();
	
	public boolean createNewDepartment(DepartmentApprovalVO departmentApprovalVO);
	
	public boolean isPossibleDelete(String id);

	public DepartmentListVO getOnlyDepartment(String deptId);

	public DepartmentVO selectOneDepartment(String departmentId);

	public boolean modifyOneDepartment(DepartmentApprovalVO departmentApprovalVO);

	public boolean deleteOneDepartment(DepartmentApprovalVO departmentApprovalVO);

	public String getDepartmentNameById(String deptId);
	

	public int  getDepartMent(String id);

	public String  getOnlypstnid(String pstnid);

	public List<EmployeeVO> getEmpByDeptId(String deptId);

	public boolean getDeptIdByName(DepartmentVO departmentVO);

	



}
