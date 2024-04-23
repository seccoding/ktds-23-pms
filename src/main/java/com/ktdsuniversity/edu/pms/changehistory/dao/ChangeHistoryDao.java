package com.ktdsuniversity.edu.pms.changehistory.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public interface ChangeHistoryDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.changehistory.dao.ChangeHistoryDao";

	int insertOneChangeDeptHistory(EmployeeVO employeeVO);

	String getRecentDeptHist(String empId);

	List<DepartmentHistoryVO> getAllDeptHist(String empId);



}
