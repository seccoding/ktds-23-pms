package com.ktdsuniversity.edu.pms.utils;

import java.util.HashMap;
import java.util.Map;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

import jakarta.servlet.http.HttpSession;

public abstract class SessionUtil {

	/**
	 * 세션을 확인하는 유틸
	 * key: 로그인하는 사원번호
	 * value: 로그인한 사원번호의 세션
	 */
	private static Map<String, HttpSession> sessionMap;

	
	static {
		sessionMap = new HashMap<>();
	}
	
	private SessionUtil() {
	}

	public static Map<String, HttpSession> getSessionMap() {
		return sessionMap;
	}

	/**
	 * sessionMap에 입력받은 세션이 있는지 확인하는 메소드
	 * @param employeeId 입력받은 사원번호
	 * @return true: 로그인중인 사원번호 ,false: 로그인되어있지 않은 사원번호
	 */
	public static boolean wasLoginEmployee(String employeeId) {
		return sessionMap.containsKey(employeeId);
	}
	
	/**
	 * 로그인 되어있지 않은 사원번호라면 sessionMap에 사원번호와 세션을 추가한다
	 * @param employeeId 입력받은 사원번호
	 * @param session 사원번호에 해당하는 세션
	 */
	public static void addSession(String employeeId, HttpSession session) {
		if (!wasLoginEmployee(employeeId)) {
			sessionMap.put(employeeId, session);
		}
	}
	
	/**
	 * 해당하는 사원번호의 세션을 제거한다 로그아웃 버튼을 눌렀을 때 실행되는 메서드이다.
	 * 값이 true여야 톰캣을 거치지 않고 세션을 제거한다.
	 * @param employeeId 입력받은 사원번호
	 */
	public static void removeSession(String employeeId) {
		removeSession(employeeId, true);
	}
	
	/**
	 * 톰캣에서 세션을 제거해야 하기 때문에 false 를 전달한다.
	 * (로그아웃을 누르지 않고 세션이 만료되었을 때 실행되는 메서드)
	 * map에서 로그인되어 있는 사원의 정보를 제거한다.
	 * @param employeeId 로그인 되어 있던 사원의 정보
	 * @param immediatleyLogout false 이미 세션이 제거된 상태이고, map 에서 사원정보를 제거한다.
	 */
	public static void removeSession(String employeeId, boolean immediatleyLogout) {
		if (wasLoginEmployee(employeeId)) {
			if (immediatleyLogout) {
				// 세션 제거전 로그아웃 기록 해야함.
				sessionMap.get(employeeId).invalidate();
			}
			sessionMap.remove(employeeId);
		}
	}
	
	/**
	 * 세션에 있는 사원정보가 map 에도 저장되어 있다면 세션에 저장되어 있는 정보들을 VO 객체로 가져온다.
	 * @param employeeId 세션이 저장되어 있는 사원정보
	 * @return 사원정보에 저장되어 있는 VO객체 또는 null
	 */
	public static EmployeeVO getEmployeeVO(String employeeId) {
		if (wasLoginEmployee(employeeId)) {
			return (EmployeeVO) sessionMap.get(employeeId).getAttribute("_LOGIN_USER_");
		}
		
		return null;
	}

}