package com.ktdsuniversity.edu.pms.changehistory.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.JobHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.PositionHistoryVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public interface ChangeHistoryDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.changehistory.dao.ChangeHistoryDao";

	int insertOneChangeDeptHistory(EmployeeVO employeeVO);

	String getRecentDeptHist(String empId);

	List<DepartmentHistoryVO> getAllDeptHist(String empId);

	List<JobHistoryVO> getAllJobHist(String empId);

	List<PositionHistoryVO> getUserPositionHistory(String empId);
	
	int insertOneChangeJobHistory(EmployeeVO employeeVO);
	
	String getRecentJobHist(String empId);
	
	int insertOneChangePositionHistory(EmployeeVO employeeVO);
	
	String getRecentPositionHist(String empId);

	List<CommonCodeVO> getAllPosition();

	List<JobHistoryVO> getUserJobHistory(String empId);

	int insertPositionHist(EmployeeVO employeeVO);

	List<CommonCodeVO> getAllJob();

	




}
