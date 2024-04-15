package com.ktdsuniversity.edu.pms.exceptions;

public class PageNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -957464259398158944L;

	public PageNotFoundException() {
        super("페이지를 찾을 수 없습니다.");
    }

}
