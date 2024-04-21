package com.ktdsuniversity.edu.pms.login.dao;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;
import com.ktdsuniversity.edu.pms.login.vo.LoginLogListVO;
import com.ktdsuniversity.edu.pms.login.vo.LoginLogVO;
import com.ktdsuniversity.edu.pms.login.vo.VisitedVO;

import java.util.List;

public interface LoginLogDao {
	public String LOGIN_SPACE = "com.ktdsuniversity.edu.pms.login.dao.LoginLogDao";

	/**
	 * 로그인 메서드
	 * 
	 * @param employeeVO
	 * @return
	 */
	public EmployeeVO getOneEmployeeByEmpIdAndPwd(EmployeeVO employeeVO);

	public EmployeeVO getOneEmpIdUseOtherPlace(EmployeeVO employeeVO);

	public void getOneEmpIdNotUseNow(EmployeeVO employeeVO);

	public void updateOneEmpLgnTryPlusOne(String empId);

	public void updateOneEmpLgnTryZero(String empId);

	public int getOneEmpLgnTryCount(String empId);


	public void updateLoginLog(EmployeeVO employee);

	public EmployeeVO updateEmpLog(EmployeeVO employee);

	public void updateOneEmpLgnTryDt(String empId);

	public int getCountPossibleLogin(String empId);

    public void updateEmpLogout(String logId);

    public void insertCommuteIn(EmployeeVO employee);

    public String selectSalt(String empId);

    public int getCommuteDt(String empId);

    public void updateCommuteFnsh(EmployeeVO employee);

    public int getPwdCndt(String empId);

    public List<LoginLogVO> getAllLoginLog();

	public List<VisitedVO> getAllVisitedLog();
}
