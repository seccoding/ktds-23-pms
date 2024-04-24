package com.ktdsuniversity.edu.pms.changehistory.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.JobHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.PositionHistoryVO;

public interface ChangeHistoryService {

	List<DepartmentHistoryVO> getUserDeptHisory(String empId);

	List<JobHistoryVO> getUserJobHistory(String empId);

	List<PositionHistoryVO> getUserPositionHistory(String empId);
	
	

}
