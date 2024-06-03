package com.ktdsuniversity.edu.pms.deptteam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.deptteam.dao.DeptTeamDao;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;

@Service
public class DeptTeamServiceImpl implements DeptTeamService{
	
	@Autowired
	private DeptTeamDao deptTeamDao;

	@Override
	public DepartmentListVO getAllDepartment() {
		int departmentCount = this.deptTeamDao.getDepartmentCount();
		List<DepartmentVO> departmentList = this.deptTeamDao.getAllDepartment();
		
		DepartmentListVO departmentListVO = new DepartmentListVO();
		departmentListVO.setDepartmentCnt(departmentCount);
		departmentListVO.setDepartmentList(departmentList);
		
		return departmentListVO;
	}


	@Override
	public DepartmentListVO getOnlyDepartment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamListVO getOnlyTeam() {
		// TODO Auto-generated method stub
		return null;
	}
}
