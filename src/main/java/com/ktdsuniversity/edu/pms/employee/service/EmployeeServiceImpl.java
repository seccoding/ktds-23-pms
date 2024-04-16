package com.ktdsuniversity.edu.pms.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.SHA;
import com.ktdsuniversity.edu.pms.department.dao.DepartmentDao;
import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private SHA sha;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private FileHandler fileHandler;

	
	@Autowired
	private DepartmentDao departmentDao;


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


//	@Override (로그인에서 사용)
//	public boolean createNewEmployee(EmployeeVO employeeVO, MultipartFile picture) {
//		
//			String pwd = employeeVO.getPwd();
//			String salt = this.sha.generateSalt();
//			pwd = this.sha.getEncrypt(pwd, salt);
//			
//			employeeVO.setPwd(pwd);
//			employeeVO.setSalt(salt);
//			
//			if(picture != null && !picture.isEmpty()) {
//				StoredFile storedFile = fileHandler.storeFile(picture);
//				if(storedFile != null) {
//					employeeVO.setPrfl(storedFile.getFileName());
//					employeeVO.setPrfl(storedFile.getRealFileName());
//				}
//			}
//			
//			int insertedCount = this.employeeDao.insertNewEmployee(employeeVO);
//			return insertedCount > 0;
//	}
	
	@Override
	public boolean deleteEmployee(String empId) {
		
		return this.employeeDao.deleteEmployeeById(empId) > 0;
	}
	
	@Override
	public boolean modifyEmployee(EmployeeVO employeeVO) {
		
		
		return this.employeeDao.modifyEmployee(employeeVO) > 0;
	}

	@Override
	public EmployeeVO getOneEmployee(String empId) {
		
		return this.employeeDao.getOneEmployee(empId);
	}



	@Override
	public EmployeeVO getOneEmployee(int id) {
		EmployeeVO employeeVO = this.employeeDao.selectOneBoard(id);
		
		return employeeVO;
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
