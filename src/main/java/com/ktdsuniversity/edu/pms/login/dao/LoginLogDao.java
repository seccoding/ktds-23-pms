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

	/**
	 * empId 로 조회된 정보에 로그인 시도횟수가 5보다 작으면, <br>
	 * 로그인 시도회수를 1증가한 후에 로그인 시도시간을 기록한다
	 * @param empId 조회하는 empId
	 * @return update 성공횟수 (1이면 성공 0이면 실패)
	 */
	public int updateOneEmpLgnTryPlusOne(String empId);

	/**
	 * empId 로 조회된 정보에 로그인 시도회수를 0으로 초기화 한 후,<br>
	 * 로그인 시도시간을 기록한다
	 * @param empId 조회하는 empId
	 * @return update 성공횟수 (1이면 성공 0이면 실패)
	 */
	public int updateOneEmpLgnTryZero(String empId);

	public int getOneEmpLgnTryCount(String empId);
	/**
	 * LoginLogVO 값을 받아와서 로그인 로그를 DB에 저장한다
	 * @param loginLogVO
	 * @return LoginLog가 성공한 갯수
	 */
	public int insertLoginLog(LoginLogVO loginLogVO);

	public EmployeeVO updateEmpLog(EmployeeVO employee);

	public int updateOneEmpLgnTryDt(String empId);

	/**
	 * empId 를 받아서 해당 유져의 최종 로그인 시간과 현재시간의 차이가 1시간 이내인 경우 1을 반환한다, 아니면 0
	 * @param empId
	 * @return
	 */
	public int getCountPossibleLogin(String empId);

    public int updateEmpLogout(String empId);

    public int insertCommuteIn(EmployeeVO employee);

    public String selectSalt(String empId);

    public CommuteVO getCommuteDt(String empId);

    public int updateCommuteFnsh(EmployeeVO employee);
/**
 * empId 로 조회된 비밀번호 변경일이 <br>
 * 현재 날짜보다 90일 이전이면 1을 반환
 * @param empId 사원번호
 * @return 1=비밀번호 만료 , 0 비밀번호 사용가능
 */
    public int getPwdCndt(String empId);

    public List<LoginLogVO> getAllLoginLog(LoginLogVO loginLogVO);

	public List<LoginLogVO> getOneLoginLog(LoginLogVO loginLogVO);

	public List<VisitedVO> getAllVisitedLog(VisitedVO visitedVO);

	public List<VisitedVO> getOneVisitedLog(VisitedVO visitedVO);

	public int updatePwdDtThirtyDay(String empId);

	public int getCommutFnshCount(String empId);

	
}
