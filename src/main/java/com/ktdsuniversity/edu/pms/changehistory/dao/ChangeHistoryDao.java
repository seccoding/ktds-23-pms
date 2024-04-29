package com.ktdsuniversity.edu.pms.changehistory.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.JobHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.PositionHistoryVO;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.job.vo.JobVO;

public interface ChangeHistoryDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.changehistory.dao.ChangeHistoryDao";

	public int insertOneChangeDeptHistory(EmployeeVO employeeVO);

	public String getRecentDeptHist(String empId);

	public List<DepartmentHistoryVO> getAllDeptHist(String empId);

	public List<JobHistoryVO> getAllJobHist(String empId);

	public List<PositionHistoryVO> getUserPositionHistory(String empId);
	
	public int insertOneChangeJobHistory(EmployeeVO employeeVO);
	
	public String getRecentJobHist(String empId);
	
	public int insertOneChangePositionHistory(EmployeeVO employeeVO);
	
	public String getRecentPositionHist(String empId);

	public List<CommonCodeVO> getAllPosition();

	public List<JobHistoryVO> getUserJobHistory(String empId);

	public int insertPositionHist(EmployeeVO employeeVO);

	public List<JobVO> getAllJob();

	




}
