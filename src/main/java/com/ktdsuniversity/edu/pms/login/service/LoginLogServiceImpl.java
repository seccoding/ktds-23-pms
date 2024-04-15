package com.ktdsuniversity.edu.pms.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.EmpIdAndPwdIsNotMatchException;
import com.ktdsuniversity.edu.pms.login.dao.LoginLogDao;

@Service
public class LoginLogServiceImpl implements LoginLogService {

	/**
	 * 맴버변수 loginLogDao 에 Bean Container 에 적재된 Dao 의존성 주입(할당)
	 */
	@Autowired
	private LoginLogDao loginLogDao;

	@Transactional
	@Override
	public EmployeeVO getOneEmployeeByEmpIdAndPwd(EmployeeVO employeeVO) {

		employeeVO.setPwd(employeeVO.getPwd());
		
		
		EmployeeVO employee = loginLogDao.getOneEmployeeByEmpIdAndPwd(employeeVO);
		
		
		if (employee == null) {
			// exception 패키지에서 exception 처리 해야 한다.
			throw new EmpIdAndPwdIsNotMatchException();
		}
		
		
//		employee.setLgnYn("Y");
		return employee;
	}

	@Override
	public EmployeeVO getOneEmpIdUseOtherPlace(EmployeeVO employeeVO) {
		employeeVO.setLgnYn("Y");
		this.loginLogDao.getOneEmpIdUseOtherPlace(employeeVO);
		
		return null;
	}

	@Override
	public void getOneEmpIdNotUseNow(EmployeeVO employeeVO) {
		employeeVO.setLgnYn("N");
		this.loginLogDao.getOneEmpIdNotUseNow(employeeVO);
	}

	

}
