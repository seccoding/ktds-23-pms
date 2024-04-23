package com.ktdsuniversity.edu.pms.login.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.vo.LoginLogVO;
import com.ktdsuniversity.edu.pms.login.vo.VisitedVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

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


	public int updateLoginLog(LoginLogVO loginLogVO);

	public EmployeeVO updateEmpLog(EmployeeVO employee);

	public void updateOneEmpLgnTryDt(String empId);

	public int getCountPossibleLogin(String empId);

    public void updateEmpLogout(String logId);

    public void insertCommuteIn(EmployeeVO employee);

    public String selectSalt(String empId);

    public int getCommuteDt(String empId);

    public void updateCommuteFnsh(EmployeeVO employee);

    public int getPwdCndt(String empId);

    public List<LoginLogVO> getAllLoginLog(LoginLogVO loginLogVO);

	List<LoginLogVO> getOneLoginLog(String empId);

	public List<VisitedVO> getAllVisitedLog(VisitedVO visitedVO);

	List<VisitedVO> getOneVisitedLog(String empId);

}
