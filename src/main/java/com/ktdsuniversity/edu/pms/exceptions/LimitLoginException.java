package com.ktdsuniversity.edu.pms.exceptions;

public class LimitLoginException extends RuntimeException{

	private static final long serialVersionUID = -3529948638245316071L;
	/**
	 * 로그인 횟수가 5번이 초과될경우 사용하는 예외처리
	 */
	public LimitLoginException() {
		super("로그인 횟수가 5번을 초과하였습니다. 60분뒤에 시도해 주세요.");
	}
}
