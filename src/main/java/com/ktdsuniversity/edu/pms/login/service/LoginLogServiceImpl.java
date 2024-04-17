package com.ktdsuniversity.edu.pms.login.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.beans.SHA;
import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.EmpIdAndPwdIsNotMatchException;
import com.ktdsuniversity.edu.pms.exceptions.LimitLoginException;
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
	private EmployeeDao employeeDao;

	@Autowired
	private SHA sha;

	@Override
	public EmployeeVO getOneEmployeeByEmpIdAndPwd(EmployeeVO employeeVO) {
//		이 주석 지우면 저 죽어요
//		String saltOfEmp = this.employeeDao.saltByEmp(employeeVO.getEmpId());
//		if (StringUtil.isEmpty(saltOfEmp)) {
//			this.loginLogDao.updateOneEmpLgnTryPlusOne(employeeVO.getEmpId());
//			throw new EmpIdAndPwdIsNotMatchException();
//		}
		/**
		 * 로그인 시도횟수를 가져오고 5번 초과될 경우
		 * 로그인 시도시간을 현재시간 + 60분 으로 업데이트하고
		 * 로그인 시도시간이 현재시간보다 크다면 오류를 발생시키고
		 * 로그인 시도시간이 현재시간보다 작다면 로그인 시도횟수를 0으로 초기화
		 * 매퍼 한번에 가져오는지, 따로가져오는지
		 */
		//로그인 시도횟수를 가져오고 5번 초과일경우 오류 발생
		//수정중
//		int possiableLoginCount = this.loginLogDao.getCountPossibleLogin(employeeVO.getEmpId());
//		if (possiableLoginCount > 0) {
//			this.loginLogDao.updateOneEmpLgnTryZero(employeeVO.getEmpId());
//		}
		
		
		int count = this.loginLogDao.getOneEmpLgnTryCount(employeeVO.getEmpId());
		if (count >= 5) {


		employeeVO.setPwd(employeeVO.getPwd());


		EmployeeVO employee = loginLogDao.getOneEmployeeByEmpIdAndPwd(employeeVO);



			throw new LimitLoginException();
		}
		
		String pwd = employeeVO.getPwd();
//		pwd = this.sha.getEncrypt(pwd, saltOfEmp);
		employeeVO.setPwd(pwd);

		EmployeeVO employee = loginLogDao.getOneEmployeeByEmpIdAndPwd(employeeVO);

		

		if (employee == null) {
			// exception 패키지에서 exception 처리 해야 한다.
			this.loginLogDao.updateOneEmpLgnTryPlusOne(employeeVO.getEmpId());
			
			throw new EmpIdAndPwdIsNotMatchException();
		} else {
			// 성공하면 로그인 시도 횟수를 0으로 초기화
			this.loginLogDao.updateOneEmpLgnTryZero(employeeVO.getEmpId());
			return employee;
		}


//		employee.setLgnYn("Y");
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
	public void updateEmpLogout(EmployeeVO employee) {
		this.loginLogDao.updateEmpLogout(employee);
	}


}
