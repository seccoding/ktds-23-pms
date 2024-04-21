package com.ktdsuniversity.edu.pms.login.service;


import com.ktdsuniversity.edu.pms.beans.SHA;
import com.ktdsuniversity.edu.pms.exceptions.EmpIdEndDTException;
import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;
import org.apache.tika.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		 * 로그인을 할때마다 현재시간을 시도시간에 기입
		 * 
		 * 로그인을 할때 시도횟수가 5번일 경우 오류발생
		 * 
		 */


		String storedSalt = this.loginLogDao.selectSalt(employeeVO.getEmpId());

		// 만약, salt 값이 null 이라면, 회원정보가 없는 것이므로 사용자에게 예외를 전달한다.
		if (StringUtils.isEmpty(storedSalt)) {
			throw new EmpIdEndDTException();
		}

		employeeVO.setPwd(this.sha.getEncrypt(employeeVO.getPwd(), storedSalt));

		//현재시간이 로그인 시도시간 + 1/24보다 큰 경우 1을 반환하고 아니라면 0을 반환하는 쿼리를 실행
		//실행된 값이 0보다 클경우 로그인 시도횟수를 0으로 초기화
		int possiableLoginCount = this.loginLogDao.getCountPossibleLogin(employeeVO.getEmpId());
		if (possiableLoginCount > 0) {
			this.loginLogDao.updateOneEmpLgnTryZero(employeeVO.getEmpId());
		}
		
		//로그인 시도횟수를 가져오고 5번이 되면 오류 발생
		int count = this.loginLogDao.getOneEmpLgnTryCount(employeeVO.getEmpId());
		if (count >= 5) {
			throw new LimitLoginException();
		}







		EmployeeVO employee = loginLogDao.getOneEmployeeByEmpIdAndPwd(employeeVO);

		//시도 횟수가 5회가 아닐때 로그인 성공, 실패 상관없이 로그인 시도시간을 업데이트한다
		this.loginLogDao.updateOneEmpLgnTryDt(employeeVO.getEmpId());

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
