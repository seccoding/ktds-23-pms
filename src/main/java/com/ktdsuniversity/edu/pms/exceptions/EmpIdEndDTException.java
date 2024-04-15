package com.ktdsuniversity.edu.pms.exceptions;

import java.io.Serial;

public class EmpIdEndDTException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 253954379405285128L;

    public EmpIdEndDTException() {
        super("퇴사한 사원입니다.");
    }
}
