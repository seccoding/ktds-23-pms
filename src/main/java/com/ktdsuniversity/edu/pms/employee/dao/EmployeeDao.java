package com.ktdsuniversity.edu.pms.employee.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;

public interface EmployeeDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao";

	public int getAllEmployeeCount();

	public int searchEmployeeAllCount(SearchEmployeeVO searchEmployeeVO);

	public List<EmployeeVO> getAllEmployee();

	public List<EmployeeVO> searchAllEmployee(SearchEmployeeVO searchEmployeeVO);

	public EmployeeVO getOneEmployee(String empId);

	public int deleteEmployeeById(String empId);

	public int modifyEmployee(EmployeeVO employeeVO);

	public EmployeeVO selectOneBoard(int id);

	public int createEmployee(EmployeeVO employeeVO);
	
	public List<EmployeeVO> searchEmpDeptId(SearchEmployeeVO searchEmployeeVO);

	public String saltByEmp(String empId);

	public int getOneEmpIdIsExist(String empId);

}
