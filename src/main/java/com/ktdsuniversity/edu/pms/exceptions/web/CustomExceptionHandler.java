package com.ktdsuniversity.edu.pms.exceptions.web;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Map<String, Object>> handelExpiredJwtException() {
		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(Map.of("message", "로그인정보가 만료되었습니다"));

	}
}
