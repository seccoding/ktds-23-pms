package com.ktdsuniversity.edu.pms.changehistory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.changehistory.dao.ChangeHistoryDao;
import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.JobHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.PositionHistoryVO;

@Service
public class ChangeHistoryServiceImpl implements ChangeHistoryService{
	
	@Autowired
	private ChangeHistoryDao changeHistoryDao;

	@Override
	public List<DepartmentHistoryVO> getUserDeptHisory(String empId) {
		List<DepartmentHistoryVO> departmentHistoryVO = this.changeHistoryDao.getAllDeptHist(empId);
		return departmentHistoryVO;
	}

	@Override
	public List<JobHistoryVO> getUserJobHistory(String empId) {
		List<JobHistoryVO> jobHistoryVO = this.changeHistoryDao.getAllJobHist(empId);
		return jobHistoryVO;
	}

	@Override
	public List<PositionHistoryVO> getUserPositionHistory(String empId) {
		List<PositionHistoryVO> PositionHistoryVO = this.changeHistoryDao.getUserPositionHistory(empId);
		return PositionHistoryVO;
	}

}
