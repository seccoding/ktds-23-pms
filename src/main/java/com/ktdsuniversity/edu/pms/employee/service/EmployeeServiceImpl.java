package com.ktdsuniversity.edu.pms.employee.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;
import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.FileHandler.StoredFile;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private FileHandler fileHandler;

	@Override
	public EmployeeListVO getAllEmployee() {
		
		int employeeCount = this.employeeDao.getAllEmployeeCount();
		List<EmployeeVO> employeeList = this.employeeDao.getAllEmployee();
		
		EmployeeListVO employeeListVO = new EmployeeListVO();
		employeeListVO.setEmployeeCnt(employeeCount);
		employeeListVO.setEmployeeList(employeeList);
		
		return employeeListVO;
	}

	@Override
	public EmployeeListVO searchAllEmployee(SearchEmployeeVO searchEmployeeVO) {
		
		int employeeCount = this.employeeDao.searchEmployeeAllCount(searchEmployeeVO);
		searchEmployeeVO.setPageCount(employeeCount);
		List<EmployeeVO> employeeList = this.employeeDao.searchAllEmployee(searchEmployeeVO);
		
		EmployeeListVO employeeListVO = new EmployeeListVO();
		employeeListVO.setEmployeeCnt(employeeCount);
		employeeListVO.setEmployeeList(employeeList);
		return employeeListVO;
	}

	@Override
	public EmployeeVO getOneEmployee(int id) {
		EmployeeVO employeeVO = this.employeeDao.selectOneBoard(id);
		
		return employeeVO;
	}

	@Override
	public boolean createNewEmployee(EmployeeVO employeeVO, MultipartFile file) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public boolean createNewEmployee(EmployeeVO employeeVO, MultipartFile file) {
//		if(file != null && !file.isEmpty()) {
//			if(StoredFile != null) {
//				employeeVO.setFileName(StoredFile.getRealFileName());
//				employeeVO.setOriginFileName(StoredFile.getFileName());
//			}
//			
//		}
//		int insertedCount = this.employeeDao.insertNewEmployee(employeeVO);
//		
//		return insertedCount > 0;
//	}

}
