package com.ktdsuniversity.edu.pms.department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.department.dao.DepartmentDao;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.team.dao.TeamDao;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;


@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private TeamDao teamDao;
	
	@Override
	public DepartmentListVO getAllDepartment() {
		
		int departmentCount = this.departmentDao.getDepartmentCount();
		List<DepartmentVO> departmentList = this.departmentDao.getAllDepartment();
		
		DepartmentListVO departmentListVO = new DepartmentListVO();
		departmentListVO.setDepartmentCnt(departmentCount);
		departmentListVO.setDepartmentList(departmentList);
		
		return departmentListVO;
	}

	@Transactional
	@Override
	public boolean createNewDepartment(DepartmentVO departmentVO) {
		
		int insertedCount = this.departmentDao.createNewDepartment(departmentVO);
		return insertedCount > 0;
	}

	@Override
	public boolean isPossibleDelete(String id) {
		boolean isPossibleDelete = this.teamDao.countTeamInDepartement(id) == 0;
		
		return isPossibleDelete;
	}

	@Override
	public DepartmentListVO getOnlyDepartment() {
		
		List<DepartmentVO> onlyDepartmentListVO = this.departmentDao.getOnlyDepartment();
		DepartmentListVO departmentListVO = new DepartmentListVO();
		departmentListVO.setDepartmentList(onlyDepartmentListVO);
		return departmentListVO;
	}

	@Override
	public DepartmentVO selectOneDepartment(String departmentId) {
		
		DepartmentVO departmentVO = this.departmentDao.getOneDepartment(departmentId);
		
		return departmentVO;
	}

	@Transactional
	@Override
	public boolean modifyOneDepartment(DepartmentVO departmentVO) {
		return departmentDao.updateOneDepartment(departmentVO) > 0;
	}

	@Transactional
	@Override
	public boolean deleteOneDepartment(String deptId) {
		return departmentDao.deleteOneDepartment(deptId) > 0;
	}

	@Override
	public String getDepartmentNameById(String deptId) {
		return departmentDao.getDepartmentNameById(deptId);
	}
	
	@Override
	public String getOnlypstnid(String pstnid) {
		// TODO Auto-generated method stub
		return this.departmentDao.getOnlypstnid(pstnid);
	}

	@Override
	public List<EmployeeVO> getEmpByDeptId(String deptId) {
		List<EmployeeVO> empList =  this.departmentDao.getEmpByDeptId(deptId);
		return empList;
	}

	

	
	
	

	
}
