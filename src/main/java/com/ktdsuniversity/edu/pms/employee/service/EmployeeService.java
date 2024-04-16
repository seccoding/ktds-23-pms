package com.ktdsuniversity.edu.pms.employee.service;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;

public interface EmployeeService {

	public EmployeeListVO getAllEmployee();
	
	public EmployeeListVO searchAllEmployee(SearchEmployeeVO searchEmployeeVO);

	public boolean deleteEmployee(String empId);
	
	public boolean modifyEmployee(EmployeeVO employeeVO);
	
	public EmployeeVO getOneEmployee(String empId);
 
	
	
}

