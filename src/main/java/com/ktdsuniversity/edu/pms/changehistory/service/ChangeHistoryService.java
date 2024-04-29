package com.ktdsuniversity.edu.pms.changehistory.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.JobHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.PositionHistoryVO;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.job.vo.JobVO;

public interface ChangeHistoryService {

	List<DepartmentHistoryVO> getUserDeptHisory(String empId);

	List<JobHistoryVO> getUserJobHistory(String empId);

	List<PositionHistoryVO> getUserPositionHistory(String empId);

	List<CommonCodeVO> getAllPosition();

	boolean changePosition(EmployeeVO employeeVO);

	boolean changeJob(EmployeeVO employeeVO);

	List<JobVO> getAllJob();

	
	

}
