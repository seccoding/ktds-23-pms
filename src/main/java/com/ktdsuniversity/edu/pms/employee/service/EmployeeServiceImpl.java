package com.ktdsuniversity.edu.pms.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.beans.SHA;
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
	public boolean deleteEmployee(String empId) {
		
		return this.employeeDao.deleteEmployeeById(empId) > 0;
	}
	
	@Override
	public boolean modifyEmployee(EmployeeVO employeeVO) {
		
		int updatedCount = this.employeeDao.modifyEmployee(employeeVO);
		return updatedCount > 0;
	}

	
	@Override
	public EmployeeVO getOneEmployee(String empId) {
		
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(empId);
		return employeeVO;
		
	}

	public boolean createEmployee(EmployeeVO employeeVO) {
		String pwd = employeeVO.getPwd();
		String salt = this.sha.generateSalt();
		pwd = this.sha.getEncrypt(pwd, salt);

		employeeVO.setPwd(pwd);
		employeeVO.setSalt(salt);
		
		int createSuccessCount = employeeDao.createEmployee(employeeVO);
		
		return createSuccessCount > 0;
	}

	@Override
	public EmployeeListVO searchEmpDeptId(SearchEmployeeVO searchEmployeeVO) {
		
		List<EmployeeVO> employeeList = this.employeeDao.searchEmpDeptId(searchEmployeeVO);
		
		EmployeeListVO employeeListVO = new EmployeeListVO();
		employeeListVO.setEmployeeList(employeeList);
		
		return employeeListVO;
	}

	@Override
	public int getOneEmpIdIsExist(String empId) {
		return this.employeeDao.getOneEmpIdIsExist(empId);
	}

}
