package com.ktdsuniversity.edu.employee.service;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.employee.vo.SearchEmployeeVO;

public interface EmployeeService {

	public EmployeeListVO getAllEmployee();
	
	public EmployeeListVO searchAllEmployee(SearchEmployeeVO searchEmployeeVO);
	
	public EmployeeVO getOneEmployee(int id);
	
	//사원 추가 ?
	public boolean createNewEmployee(EmployeeVO employeeVO, MultipartFile file); 
	
	
}
