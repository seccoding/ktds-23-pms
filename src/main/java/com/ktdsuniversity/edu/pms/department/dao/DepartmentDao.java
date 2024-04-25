package com.ktdsuniversity.edu.pms.department.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;

public interface DepartmentDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.department.dao.DepartmentDao";
	
	public int getDepartmentCount();
	
	public List<DepartmentVO> getAllDepartment();
	
	public int createNewDepartment(DepartmentVO deparmentVO);
	
	public int deleteOneDepartment(String id);
	
	public int updateOneDepartment(DepartmentVO departmentVO);

	public List<DepartmentVO> getOnlyDepartment();

	public DepartmentVO getOneDepartment(String departmentId);

	public String getDepartmentNameById(String deptId);
	
	public  int getDepartment(String id);
	
	public String  getOnlypstnid(String pstnid); 
}
