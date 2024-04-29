package com.ktdsuniversity.edu.pms.employee.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

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

	public List<EmployeeVO> findEmployeesByTeamId(String teamId);

	public int getOneEmpIdIsExist(String empId);

	public int modifyOneEmployee(EmployeeVO employeeVO);

	public List<TeamVO> getEmployeeAllTeam(String empId);

	public int deleteTeam(EmployeeVO employeeVO);

	public int addTeam(EmployeeVO employeeVO);
	
	public String getDeptIdByEmployeeId(String empId);

	public List<EmployeeVO> findEmployeesByDeptID(String deptId);

	public List<EmployeeVO> getCanBeDeptLead();

	public List<EmployeeVO> getChangeToDeptLead(String departmentId);

	public int modifyEmployeeJob(EmployeeVO employeeVO);

	public int modifyEmployeePosition(EmployeeVO employeeVO);

	public int modifyEmployeeDept(EmployeeVO employeeVO);

	


}
