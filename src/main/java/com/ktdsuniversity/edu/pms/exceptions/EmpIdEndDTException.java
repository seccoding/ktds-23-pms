package com.ktdsuniversity.edu.pms.exceptions;

public class EmpIdEndDTException extends RuntimeException{

    private static final long serialVersionUID = 253954379405285128L;

    public EmpIdEndDTException() {
        super("사원정보가 없습니다.");
    }
}
