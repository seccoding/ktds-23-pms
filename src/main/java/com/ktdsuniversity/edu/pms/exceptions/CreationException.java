package com.ktdsuniversity.edu.pms.exceptions;

/**
 * 페이지 생성 또는 글 작성 시 발생 예외
 *
 * @author 최형준
 */
public class CreationException extends RuntimeException{

    public CreationException() {
        super("생성 또는 작성에 실패했습니다.");
    }

}
