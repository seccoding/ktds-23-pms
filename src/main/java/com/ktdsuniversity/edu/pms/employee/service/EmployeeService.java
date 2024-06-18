package com.ktdsuniversity.edu.pms.employee.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeChangeHistoryVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeDataVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeInfoVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;

public interface EmployeeService {

	public EmployeeListVO getAllEmployee();

	public EmployeeListVO searchAllEmployee(SearchEmployeeVO searchEmployeeVO);

	public boolean deleteEmployee(String empId);

	public boolean modifyEmployee(EmployeeVO employeeVO);

	public boolean modifyOneEmployee(EmployeeVO employeeVO);
	
	public EmployeeVO getOneEmployee(String empId);

	public boolean createEmployee(EmployeeInfoVO employeeInfoVO);

	public EmployeeListVO searchEmpDeptId(SearchEmployeeVO searchEmployeeVO);

	public List<EmployeeVO> findEmployeesByTeamId(String teamId);

	public int getOneEmpIdIsExist(String empId);

	public boolean deleteTeam(EmployeeVO employeeVO);

	public boolean addTeam(EmployeeVO employeeVO);
	
	public String getDeptIdByEmployeeId(String empId);

	public List<EmployeeVO> findEmployeesByDeptId(String deptId);

	public List<EmployeeVO> getCanBeDeptLead();
	
	public EmployeeVO getOneEmployeeNoTeam(String empId);

	public List<EmployeeVO> getChangeToDeptLead(String departmentId);

	public EmployeeVO getOneEmployeeCheckNull(String empId);

	public EmployeeVO getOneEmployeenullCheck(String empId);

	public List<EmployeeDataVO> getDepartList();

	public List<EmployeeDataVO> getTeamList();

	public List<EmployeeDataVO> getJobList();

	public List<EmployeeDataVO> getEmployeeGradeList();

	public List<EmployeeDataVO> getEmployeeWorkStsList();
	
	public List<EmployeeVO> getEmployeeByDeptId(String deptId);

	public EmployeeInfoVO getEmployeeInfo(String empId);

	public boolean createEmployeeProfile(EmployeeInfoVO employeeInfoVO, MultipartFile file);

	public boolean modifyPwd(EmployeeInfoVO employeeInfoVO);

	public int insertEmployeeChangeHistory(EmployeeChangeHistoryVO changeData);

	public List<EmployeeChangeHistoryVO> getEmployeeChangeHistory(String empId);

	public List<EmployeeInfoVO> getNewEmployeeList();

	public List<EmployeeVO> findEmployeeLoginInfoByDeptId(String deptId);


}
