package com.ktdsuniversity.edu.pms.department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.department.dao.DepartmentDao;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;


@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public DepartmentListVO getAllDepartment() {
		
		int departmentCount = this.departmentDao.getDepartmentCount();
		List<DepartmentVO> departmentList = this.departmentDao.getAllDepartment();
		
		DepartmentListVO departmentListVO = new DepartmentListVO();
		departmentListVO.setDepartmentCnt(departmentCount);
		departmentListVO.setDepartmentList(departmentList);
		
		return departmentListVO;
	}

	@Override
	public boolean createNewDepartment(DepartmentVO departmentVO) {
		
		int insertedCount = this.departmentDao.createNewDepartment(departmentVO);
		return insertedCount > 0;
	}

	@Override
	public boolean deleteOneDepartment(String id) {
		
		
		return this.departmentDao.deleteOneDepartment(id) > 0;
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

	
}
