package com.ktdsuniversity.edu.pms.login.service;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;
import com.ktdsuniversity.edu.pms.login.vo.LoginLogListVO;
import com.ktdsuniversity.edu.pms.login.vo.LoginLogVO;
import com.ktdsuniversity.edu.pms.login.vo.VisitedListVO;
import com.ktdsuniversity.edu.pms.login.vo.VisitedVO;

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

    public CommuteVO getCommuteDt(String empId);

    public boolean updateCommuteFnsh(EmployeeVO employee);

    public int getPwdCndt(String empId);

    public LoginLogListVO getAllLoginLog(LoginLogVO loginLogVO);

    public LoginLogListVO getOneLoginLog(LoginLogVO loginLogVO);

    public VisitedListVO getAllVisitedLog(VisitedVO visitedVO);

    public VisitedListVO getOneVisitedLog(VisitedVO visitedVO);

    public boolean updatePwdDtThirtyDay(String empId);

    public int getCommutFnshCount(String empId);
    /**
	 * 사용자의 계정이 잠김처리 되었는지 확인후 반환 <br>
	 * 로그인 시도 실패횟수 5회이상, 마지막 실패후 1시간이내시 잠금처리
	 * @param employeeVO 확인할 계정 정보
	 * @return
	 */
    public boolean isAccountLocked(EmployeeVO employeeVO);
}
