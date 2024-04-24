package com.ktdsuniversity.edu.pms.exceptions;

/**
 * 페이지 접근 권한이 없는 경우 발생하는 예외
 *
 * @author 최형준
 */
public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super("페이지 접근 권한이 없습니다.");
    }

}
