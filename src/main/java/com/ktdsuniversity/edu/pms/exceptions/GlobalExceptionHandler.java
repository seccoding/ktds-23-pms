package com.ktdsuniversity.edu.pms.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * "Base Packge (com.ktdsuniversity.edu.pms)" 아래에서 발생하는 처리되지 않은 모든 예외들을
 * ControllerAdvice 가 처리해준다.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// TODO ExceptionHandler 작성해야함.
	// TODO ExceptionHandler 에 RuntimeException 추가해야함. - 현재 개발 단계이므로 에러 파악을 위해 미추가

	@ExceptionHandler(PageNotFoundException.class)
	public Object viewPageNotFoundPage(PageNotFoundException pnfe, Model model) {

		logger.error(pnfe.getMessage(), pnfe);

		model.addAttribute("message", pnfe.getMessage());

		return "error/404";

	}

	@ExceptionHandler(CreationException.class)
	public Object viewErrorPage(RuntimeException re, Model model) {

		logger.error(re.getMessage(), re);

		model.addAttribute("message", re.getMessage());

		return "error/500";

	}
}