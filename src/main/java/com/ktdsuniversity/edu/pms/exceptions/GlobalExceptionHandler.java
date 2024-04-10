package com.ktdsuniversity.edu.pms.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * "Base Packge (com.ktdsuniversity.edu.pms)" 아래에서 발생하는 처리되지 않은 모든 예외들을
 * ControllerAdvice 가 처리해준다.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// TODO ExceptionHandler 작성해야함.
}