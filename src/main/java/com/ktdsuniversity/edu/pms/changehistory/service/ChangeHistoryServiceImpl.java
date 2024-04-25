package com.ktdsuniversity.edu.pms.changehistory.service;

import java.util.ArrayList;
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

	@Override
	public List<CommonCodeVO> getAllPosition() {
		List<CommonCodeVO> positionList = this.changeHistoryDao.getAllPosition();
		return positionList;
	}

	@Transactional
	@Override
	public boolean changePosition(EmployeeVO employeeVO) {
		EmployeeVO originEmployee = this.employeeDao.getOneEmployee(employeeVO.getEmpId());
		
		List<PositionHistoryVO> posiHistList = this.changeHistoryDao.getUserPositionHistory(employeeVO.getEmpId());
		
		int updatedCount = this.employeeDao.modifyEmployeePosition(employeeVO);
		
		if(posiHistList.size() > 0) {
			String prevDate = this.changeHistoryDao.getRecentPositionHist(employeeVO.getEmpId());
			
			employeeVO.setHireDt(prevDate);			
		}else {
			employeeVO.setHireDt(originEmployee.getHireDt());	
		}
		
		int insertHistCnt = this.changeHistoryDao.insertOneChangePositionHistory(employeeVO);
		return insertHistCnt > 0 && updatedCount == insertHistCnt;
	}

	@Override
	public boolean changeJob(EmployeeVO employeeVO) {
		EmployeeVO originEmployee = this.employeeDao.getOneEmployee(employeeVO.getEmpId());

		List<JobHistoryVO> jobHistList = this.changeHistoryDao.getUserJobHistory(employeeVO.getEmpId());

		int updatedCount = this.employeeDao.modifyEmployeeJob(employeeVO);
		if(jobHistList.size() > 0) {
			String prevDate = this.changeHistoryDao.getRecentJobHist(employeeVO.getEmpId());
			employeeVO.setHireDt(prevDate);
		}else {
			employeeVO.setHireDt(originEmployee.getHireDt());
		}
		int insertHistCnt = this.changeHistoryDao.insertOneChangeJobHistory(employeeVO);
		return insertHistCnt > 0 && updatedCount == insertHistCnt;

	}

	@Override
	public List<CommonCodeVO> getAllJob() {
		List<CommonCodeVO> jobList =this.changeHistoryDao.getAllJob();
		return jobList;
	}


}
