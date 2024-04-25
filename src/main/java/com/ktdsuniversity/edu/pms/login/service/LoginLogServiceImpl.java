package com.ktdsuniversity.edu.pms.login.service;


import com.ktdsuniversity.edu.pms.approval.service.ApprovalServiceImpl;
import com.ktdsuniversity.edu.pms.beans.SHA;
import com.ktdsuniversity.edu.pms.exceptions.EmpIdEndDTException;
import com.ktdsuniversity.edu.pms.login.vo.*;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;

import org.apache.tika.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.beans.SHA;
import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.EmpIdAndPwdIsNotMatchException;
import com.ktdsuniversity.edu.pms.exceptions.LimitLoginException;
import com.ktdsuniversity.edu.pms.login.dao.LoginLogDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService {

	private Logger logger = LoggerFactory.getLogger(ApprovalServiceImpl.class);

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
			int updateOneEmpLgnTryZeroSuccess =  this.loginLogDao.updateOneEmpLgnTryZero(employeeVO.getEmpId());

			if (updateOneEmpLgnTryZeroSuccess > 0) {
				logger.debug("로그인 시도 횟수 초기화에 성공했습니다.(1)");
			} else {
				logger.debug("로그인 시도 횟수 초기화에 실패했습니다.(1)");
			}
		}
		
		//로그인 시도횟수를 가져오고 5번이 되면 오류 발생
		int count = this.loginLogDao.getOneEmpLgnTryCount(employeeVO.getEmpId());
		if (count >= 5) {
			throw new LimitLoginException();
		}
		
		EmployeeVO employee = loginLogDao.getOneEmployeeByEmpIdAndPwd(employeeVO);

		
		//시도 횟수가 5회가 아닐때 로그인 성공, 실패 상관없이 로그인 시도시간을 업데이트한다
		int updateOneEmpLgnTryDtSuccess = this.loginLogDao.updateOneEmpLgnTryDt(employeeVO.getEmpId());
		if (updateOneEmpLgnTryDtSuccess > 0) {
			logger.debug("로그인 시도시간 업데이트에 성공했습니다.");
		} else {
			logger.debug("로그인 시도시간 업데이트에 실패했습니다.");
		}

		// 로그인을 실패하면 실행되는 코드
		if (employee == null) {
			int updateOneEmpLgnTryPlusOneSuccess = this.loginLogDao.updateOneEmpLgnTryPlusOne(employeeVO.getEmpId());
			if (updateOneEmpLgnTryPlusOneSuccess > 0) {
				logger.debug("로그인 실패 - 로그인 시도 횟수 1 증가에 성공했습니다.(2)");
			} else {
				logger.debug("로그인 실패 - 로그인 시도 횟수 1 증가에 실패했습니다.(2)");
			}
			throw new EmpIdAndPwdIsNotMatchException();
		} else {
			// 성공하면 로그인 시도 횟수를 0으로 초기화
			int updateOneEmpLgnTryZeroSuccess =  this.loginLogDao.updateOneEmpLgnTryZero(employeeVO.getEmpId());
			if (updateOneEmpLgnTryZeroSuccess > 0) {
				logger.debug("로그인 시도 횟수 초기화에 성공했습니다.");
			} else {
				logger.debug("로그인 시도 횟수 초기화에 실패했습니다.");
			}
			return employee;
		}


//		employee.setLgnYn("Y");
	}

	@Transactional
	@Override
	public boolean updateOneEmpIdUseOtherPlace(EmployeeVO employeeVO) {
		employeeVO.setLgnYn("Y");
		return this.loginLogDao.updateOneEmpIdUseOtherPlace(employeeVO) > 0;
	}

	@Transactional
	@Override
	public boolean updateOneEmpIdNotUseNow(EmployeeVO employeeVO) {
		int useEmpIdCheck = this.loginLogDao.updateOneEmpIdNotUseNow(employeeVO);

		return useEmpIdCheck > 0;
	}

	@Transactional
	@Override
	public boolean insertLoginLog(EmployeeVO employee) {
		if (employee.getLoginLogVO() == null) {
			employee.setLoginLogVO(new LoginLogVO());
		}
		
		LoginLogVO loginLogVO = employee.getLoginLogVO();
		loginLogVO.setEmpId(employee.getEmpId());
		
		int insertedCount = this.loginLogDao.insertLoginLog(loginLogVO);
		return insertedCount > 0;
	}

	@Transactional
	@Override
	public EmployeeVO updateEmpLog(EmployeeVO employee) {
		TeamListVO teamList = new TeamListVO();
		
		
		return this.loginLogDao.updateEmpLog(employee);
	}

	@Transactional
	@Override
	public boolean updateEmpLogout(String logId) {
		return this.loginLogDao.updateEmpLogout(logId) > 0;
	}

	@Transactional
	@Override
	public boolean insertCommuteIn(EmployeeVO employee) {
		return this.loginLogDao.insertCommuteIn(employee) > 0;
	}

	@Override
	public int getCommuteDt(String empId) {
		return this.loginLogDao.getCommuteDt(empId);
	}

	@Transactional
	@Override
	public boolean updateCommuteFnsh(EmployeeVO employee) {
		return this.loginLogDao.updateCommuteFnsh(employee) > 0;
	}

	@Override
	public int getPwdCndt(String empId) {
		return this.loginLogDao.getPwdCndt(empId);
	}

	@Override
	public LoginLogListVO getAllLoginLog(LoginLogVO loginLogVO) {
		LoginLogListVO loginLogListVO = new LoginLogListVO();
		loginLogListVO.setLoginLogList(this.loginLogDao.getAllLoginLog(loginLogVO));

		return loginLogListVO;
	}

	@Override
	public LoginLogListVO getOneLoginLog(String empId) {
		LoginLogListVO loginLogListVO = new LoginLogListVO();
		loginLogListVO.setLoginLogList(this.loginLogDao.getOneLoginLog(empId));

		return loginLogListVO;
	}

	@Override
	public VisitedListVO getAllVisitedLog(VisitedVO visitedVO) {
		VisitedListVO visitedListVO = new VisitedListVO();
		visitedListVO.setVisitedList(this.loginLogDao.getAllVisitedLog(visitedVO));

		return visitedListVO;
	}

	@Override
	public VisitedListVO getOneVisitedLog(String empId) {
		VisitedListVO visitedListVO = new VisitedListVO();
		visitedListVO.setVisitedList(this.loginLogDao.getOneVisitedLog(empId));
		return visitedListVO;
	}

	@Transactional
	@Override
	public boolean updatePwdDtThirtyDay(String empId) {
		return this.loginLogDao.updatePwdDtThirtyDay(empId) > 0;
	}


}
