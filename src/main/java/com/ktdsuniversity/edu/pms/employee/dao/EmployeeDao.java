package com.ktdsuniversity.edu.pms.employee.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;


public interface EmployeeDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.employee.dao.EmployeeDao";

	
	
	public int getAllEmployeeCount ();
	
	public int searchEmployeeAllCount (SearchEmployeeVO searchEmployeeVO);
	
	public List<EmployeeVO> getAllEmployee();
	
	public List<EmployeeVO> searchAllEmployee(SearchEmployeeVO searchEmployeeVO);
	
	public EmployeeVO getOneEmployee(String empId); 
	
	public int insertNewEmployee(EmployeeVO employeeVO);
	
	public int deleteEmployeeById(int empId);
	
	public int modifyEmployee(EmployeeVO employeeVO);

}

