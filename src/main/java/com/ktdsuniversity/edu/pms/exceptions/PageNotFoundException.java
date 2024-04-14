package com.ktdsuniversity.edu.pms.exceptions;

public class PageNotFoundException extends RuntimeException {

    public PageNotFoundException() {
        super("페이지를 찾을 수 없습니다.");
    }

}
