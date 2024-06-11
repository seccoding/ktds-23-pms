package com.ktdsuniversity.edu.pms.login.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;

public interface CommuteDao {

	public String COMMUTE_SPACE = "com.ktdsuniversity.edu.pms.login.dao.CommuteDao";

	public List<CommuteVO> getAllCommuteData(CommuteVO commuteVO);

	public List<CommuteVO> getAllCommuteDataByEmpId(CommuteVO commuteVO);
	/**
	 * 파라미터로 받은 empID 로 조회시 출근기록을 조회
	 * @param empId
	 * @return CommuteVO 출근기록이 잇는 VO 정보
	 */
	public CommuteVO getOneCommuteDataByEmpIdToday(String empId);
	/**
	 * 출근한 직원의 출근기록을 넣는 쿼리 실행
	 * @param empId 출근직원의 id
	 * @return 
	 */
    public int insertCommuteIn(String empId);
    /**
     * 파라미터로 받아온 직원의 퇴근기록을 현재시간으로 update 한다
     * @param empId
     * @return
     */
    public int updateCommuteLeaveWork(String empId);

}
