package com.ktdsuniversity.edu.pms.login.service;

import com.ktdsuniversity.edu.pms.beans.SHA;
import com.ktdsuniversity.edu.pms.exceptions.EmpIdEndDTException;
import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;
import org.apache.tika.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.EmpIdAndPwdIsNotMatchException;
import com.ktdsuniversity.edu.pms.login.dao.LoginLogDao;

import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService {

	/**
	 * 맴버변수 loginLogDao 에 Bean Container 에 적재된 Dao 의존성 주입(할당)
	 */
	@Autowired
	private LoginLogDao loginLogDao;

	@Autowired
	private SHA sha;

	@Transactional
	@Override
	public EmployeeVO getOneEmployeeByEmpIdAndPwd(EmployeeVO employeeVO) {

		String storedSalt = this.loginLogDao.selectSalt(employeeVO.getEmpId());

		// 만약, salt 값이 null 이라면, 회원정보가 없는 것이므로 사용자에게 예외를 전달한다.
		if (StringUtils.isEmpty(storedSalt)) {
			throw new EmpIdEndDTException();
		}

		// 2. salt 값이 있을 경우, salt를 이용해 sha 암호화 한다.
		employeeVO.setPwd(this.sha.getEncrypt(employeeVO.getPwd(), storedSalt));

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

	@Override
	public void updateLoginLog(EmployeeVO employee) {
		this.loginLogDao.updateLoginLog(employee);
	}

	@Override
	public EmployeeVO updateEmpLog(EmployeeVO employee) {

		return this.loginLogDao.updateEmpLog(employee);
	}

	@Override
	public void updateEmpLogout(String logId) {
		this.loginLogDao.updateEmpLogout(logId);
	}

	@Override
	public void insertCommuteIn(EmployeeVO employee) {
		this.loginLogDao.insertCommuteIn(employee);
	}

	@Override
	public int getCommuteDt(String empId) {
		return this.loginLogDao.getCommuteDt(empId);
	}

	@Override
	public void updateComuteFnsh(EmployeeVO employee) {
		this.loginLogDao.updateCommuteFnsh(employee);
	}

	@Override
	public int getPwdCndt(String empId) {
		return this.loginLogDao.getPwdCndt(empId);
	}


}
