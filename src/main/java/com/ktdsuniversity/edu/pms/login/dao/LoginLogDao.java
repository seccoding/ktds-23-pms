package com.ktdsuniversity.edu.pms.login.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;
import com.ktdsuniversity.edu.pms.login.vo.LoginLogVO;
import com.ktdsuniversity.edu.pms.login.vo.VisitedVO;

public interface LoginLogDao {
	public String LOGIN_SPACE = "com.ktdsuniversity.edu.pms.login.dao.LoginLogDao";

	/**
	 * 로그인 메서드
	 * 
	 * @param employeeVO
	 * @return
	 */
	public EmployeeVO getOneEmployeeByEmpIdAndPwd(EmployeeVO employeeVO);

	public int updateOneEmpIdUseOtherPlace(EmployeeVO employeeVO);

	public int updateOneEmpIdNotUseNow(EmployeeVO employeeVO);

	public int updateOneEmpLgnTryPlusOne(String empId);

	public int updateOneEmpLgnTryZero(String empId);

	public int getOneEmpLgnTryCount(String empId);

	public int insertLoginLog(LoginLogVO loginLogVO);

	public EmployeeVO updateEmpLog(EmployeeVO employee);

	public int updateOneEmpLgnTryDt(String empId);

	public int getCountPossibleLogin(String empId);

    public int updateEmpLogout(String logId);

    public int insertCommuteIn(EmployeeVO employee);

    public String selectSalt(String empId);

    public CommuteVO getCommuteDt(String empId);

    public int updateCommuteFnsh(EmployeeVO employee);

    public int getPwdCndt(String empId);

    public List<LoginLogVO> getAllLoginLog(LoginLogVO loginLogVO);

	public List<LoginLogVO> getOneLoginLog(LoginLogVO loginLogVO);

	public List<VisitedVO> getAllVisitedLog(VisitedVO visitedVO);

	public List<VisitedVO> getOneVisitedLog(VisitedVO visitedVO);

	public int updatePwdDtThirtyDay(String empId);

	public int getCommutFnshCount(String empId);
}
