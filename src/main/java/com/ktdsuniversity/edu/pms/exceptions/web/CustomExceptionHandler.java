package com.ktdsuniversity.edu.pms.exceptions.web;

import java.util.Enumeration;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.RequestUtil;

import io.jsonwebtoken.ExpiredJwtException;
@RestControllerAdvice
public class CustomExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Map<String, Object>> handelExpiredJwtException() {
		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(Map.of("tokenExpire", "로그인정보가 만료되었습니다"));

	}
	
	@ExceptionHandler(RuntimeException.class)
	public ApiResponse handleRuntimeException(RuntimeException e) {
		
		String url = RequestUtil.getRequest().getRequestURI();
		Enumeration<String> paramNames = RequestUtil.getRequest().getParameterNames();
		String key = null;
		String value = null;
		while (paramNames.hasMoreElements()) {
			key = paramNames.nextElement();
			value = RequestUtil.getRequest().getParameter(key);
			logger.error("URL: " + url + " / Param Key: " + key + " / Value: " + value);
		}
		
		return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR).setErrors(e.getMessage());
	}
}
	