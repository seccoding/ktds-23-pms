package com.ktdsuniversity.edu.pms.exceptions;

public class EmpIdAndPwdIsNotMatchException extends RuntimeException{
	
	private static final long serialVersionUID = -3727452697276531038L;
	/**
	 * 사원아이디 또는 비밀번호가 일치하지 않을경우 사용하는 예외처리
	 */
	public EmpIdAndPwdIsNotMatchException() {
		super("사원번호 또는 비밀번호가 일치하지 않습니다.");
	}


}
