package com.ktdsuniversity.edu.pms.exceptions;

public class OtherPlaceAlreadyUseEmpIdException extends RuntimeException{

	private static final long serialVersionUID = 7111418624098140883L;
	
	public OtherPlaceAlreadyUseEmpIdException() {
		super("이미 다른곳에서 로그인중인 아이디입니다.");
	}

}
