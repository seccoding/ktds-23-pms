package com.ktdsuniversity.edu.pms.changehistory.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;

public interface ChangeHistoryService {

	List<DepartmentHistoryVO> getUserDeptHisory(String empId);

}
