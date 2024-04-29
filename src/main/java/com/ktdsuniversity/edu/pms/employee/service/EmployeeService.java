package com.ktdsuniversity.edu.pms.employee.service;

import java.util.List;

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

	public boolean createEmployee(EmployeeVO employeeVO, MultipartFile file);

	public EmployeeListVO searchEmpDeptId(SearchEmployeeVO searchEmployeeVO);

	public List<EmployeeVO> findEmployeesByTeamId(String teamId);

	public int getOneEmpIdIsExist(String empId);

	public boolean modifyOneEmployee(EmployeeVO employeeVO);

	public boolean deleteTeam(EmployeeVO employeeVO);

	public boolean addTeam(EmployeeVO employeeVO);
	
	public String getDeptIdByEmployeeId(String empId);

	public List<EmployeeVO> findEmployeesByDeptId(String deptId);

	public List<EmployeeVO> getCanBeDeptLead();
	
	public EmployeeVO getOneEmployeeNoTeam(String empId);

	public List<EmployeeVO> getChangeToDeptLead(String departmentId);

	public EmployeeVO getOneEmployeeCheckNull(String empId);

	public EmployeeVO getOneEmployeenullCheck(String empId);



}
