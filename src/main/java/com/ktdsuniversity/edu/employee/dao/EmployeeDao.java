package com.ktdsuniversity.edu.employee.dao;

import java.util.List;

import com.ktdsuniversity.edu.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.employee.vo.SearchEmployeeVO;


public interface EmployeeDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.employee.dao.EmployeeDao";

	public int getAllEmployeeCount ();
	
	public int searchEmployeeAllCount (SearchEmployeeVO searchEmployeeVO);
	
	public List<EmployeeVO> getAllEmployee();
	
	public List<EmployeeVO> searchAllEmployee(SearchEmployeeVO searchEmployeeVO);
	
	public EmployeeVO selectOneBoard(int id);

}
