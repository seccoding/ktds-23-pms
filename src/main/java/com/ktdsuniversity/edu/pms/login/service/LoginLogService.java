package com.ktdsuniversity.edu.pms.login.service;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.vo.*;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;

import java.util.List;

public interface LoginLogService {
    /**
     * 로그인 메서드
     * @param employeeVO view 에서 전달 받은 로그인 정보
     * @return
     */
    public EmployeeVO getOneEmployeeByEmpIdAndPwd(EmployeeVO employeeVO);

	public boolean updateOneEmpIdUseOtherPlace(EmployeeVO employeeVO);

	public boolean updateOneEmpIdNotUseNow(EmployeeVO employeeVO);


    /**
     * 로그인 시 로그인 기록 DB 저장
     * @param employee 로그인 되어 있는 사원정보를 갖고 있는 객체
     * @return
     */
    public boolean insertLoginLog(EmployeeVO employee);

    public EmployeeVO updateEmpLog(EmployeeVO employee);

    public boolean updateEmpLogout(String logId);

    public boolean insertCommuteIn(EmployeeVO employee);

    public int getCommuteDt(String empId);

    public boolean updateCommuteFnsh(EmployeeVO employee);

    public int getPwdCndt(String empId);

    public LoginLogListVO getAllLoginLog(LoginLogVO loginLogVO);

    public LoginLogListVO getOneLoginLog(String empId);

    public VisitedListVO getAllVisitedLog(VisitedVO visitedVO);

    public VisitedListVO getOneVisitedLog(String empId);

    public boolean updatePwdDtThirtyDay(String empId);

}
