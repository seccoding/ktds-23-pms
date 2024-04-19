package com.ktdsuniversity.edu.pms.login.service;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;

import java.util.List;

public interface LoginLogService {
    /**
     * 로그인 메서드
     * @param employeeVO view 에서 전달 받은 로그인 정보
     * @return
     */
    public EmployeeVO getOneEmployeeByEmpIdAndPwd(EmployeeVO employeeVO);

	public EmployeeVO getOneEmpIdUseOtherPlace(EmployeeVO employeeVO);

	public void getOneEmpIdNotUseNow(EmployeeVO employeeVO);


    /**
     * 로그인 시 로그인 기록 DB 저장
     * @param employee 로그인 되어 있는 사원정보를 갖고 있는 객체
     * @return
     */
    public void updateLoginLog(EmployeeVO employee);

    public EmployeeVO updateEmpLog(EmployeeVO employee);

    public void updateEmpLogout(String logId);

    public void insertCommuteIn(EmployeeVO employee);

    public int getCommuteDt(String empId);

    public void updateComuteFnsh(EmployeeVO employee);

    public int getPwdCndt(String empId);
}
