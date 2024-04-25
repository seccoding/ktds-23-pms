package com.ktdsuniversity.edu.pms.changehistory.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.JobHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.PositionHistoryVO;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public interface ChangeHistoryService {

	// PSH - public 추가
	public List<DepartmentHistoryVO> getUserDeptHisory(String empId);

	public List<JobHistoryVO> getUserJobHistory(String empId);

	public List<PositionHistoryVO> getUserPositionHistory(String empId);

	public List<CommonCodeVO> getAllPosition();

	public boolean changePosition(EmployeeVO employeeVO);

	public boolean changeJob(EmployeeVO employeeVO);

	// PSH - 충돌 메서드 추가
	public List<CommonCodeVO> getAllJob();

}
