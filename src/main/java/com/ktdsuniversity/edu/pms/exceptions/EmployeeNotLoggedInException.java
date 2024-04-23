package com.ktdsuniversity.edu.pms.exceptions;

public class EmployeeNotLoggedInException extends RuntimeException {
	
	private static final long serialVersionUID = -957464259398158944L;

	public EmployeeNotLoggedInException() {
        super("로그인 후 이용해주세요!");
    }

}
