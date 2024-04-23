package com.ktdsuniversity.edu.pms.changehistory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.changehistory.dao.ChangeHistoryDao;
import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;

@Service
public class ChangeHistoryServiceImpl implements ChangeHistoryService{
	
	@Autowired
	private ChangeHistoryDao changeHistoryDao;

	@Override
	public List<DepartmentHistoryVO> getUserDeptHisory(String empId) {
		List<DepartmentHistoryVO> departmentHistoryVO = this.changeHistoryDao.getAllDeptHist(empId);
		return departmentHistoryVO;
	}

}
