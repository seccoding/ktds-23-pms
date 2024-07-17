package com.ktdsuniversity.edu.pms.utils;

import java.util.HashMap;
import java.util.Map;

import com.ktdsuniversity.edu.pms.member.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

public abstract class SessionUtil {

	/**
	 * 세션을 확인하는 유틸
	 * key: 로그인하는 회원ID
	 * value: 로그인한 회원의 세션
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
	 * @param memId 입력받은 회원ID
	 * @return true: 로그인중인 회원ID ,false: 로그인되어있지 않은 회원ID
	 */
	public static boolean wasLoginMember(String memId) {
		return sessionMap.containsKey(memId);
	}
	
	/**
	 * 로그인 되어있지 않은 회원이라면 sessionMap에 회원ID와 세션을 추가한다
	 * @param memId 입력받은 회원ID
	 * @param session 회원ID에 해당하는 세션
	 */
	public static void addSession(String memId, HttpSession session) {
		if (!wasLoginMember(memId)) {
			sessionMap.put(memId, session);
		}
	}
	
	/**
	 * 해당하는 회원ID의 세션을 제거한다 로그아웃 버튼을 눌렀을 때 실행되는 메서드이다.
	 * 값이 true여야 톰캣을 거치지 않고 세션을 제거한다.
	 * @param memId 입력받은 회원ID
	 */
	public static void removeSession(String memId) {
		removeSession(memId, true);
	}
	
	/**
	 * 톰캣에서 세션을 제거해야 하기 때문에 false 를 전달한다.
	 * (로그아웃을 누르지 않고 세션이 만료되었을 때 실행되는 메서드)
	 * map에서 로그인되어 있는 회원의 정보를 제거한다.
	 * @param memId 로그인 되어 있던 회원의 정보
	 * @param immediatleyLogout false 이미 세션이 제거된 상태이고, map 에서 사원정보를 제거한다.
	 */
	public static void removeSession(String memId, boolean immediatleyLogout) {
		if (wasLoginMember(memId)) {
			if (immediatleyLogout) {
				// 세션 제거전 로그아웃 기록 해야함.
				sessionMap.get(memId).invalidate();
			}
			sessionMap.remove(memId);
		}
	}
	
	/**
	 * 세션에 있는 회원정보가 map 에도 저장되어 있다면 세션에 저장되어 있는 정보들을 VO 객체로 가져온다.
	 * @param memId 세션이 저장되어 있는 회원정보
	 * @return 회원정보에 저장되어 있는 VO객체 또는 null
	 */
	public static MemberVO getEmployeeVO(String memId) {
		if (wasLoginMember(memId)) {
			return (MemberVO) sessionMap.get(memId).getAttribute("_LOGIN_USER_");
		}
		
		return null;
	}

}