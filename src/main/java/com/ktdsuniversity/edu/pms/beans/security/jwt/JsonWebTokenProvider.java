package com.ktdsuniversity.edu.pms.beans.security.jwt;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.EmployeeNotLoggedInException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JsonWebTokenProvider {
	@Value("${app.jwt.issuer:undifined-issuer}")
	private String issuer;
	
	@Value("${app.jwt.secret-key:spring-security-key-random-token-key}")
	private String secretKey;
	
	/**
	 * 토큰 생성 함수
	 * @param expireAt 유효기간
	 * @param employee 토큰에 첨부할 사용자의 정보
	 * @return
	 */
	public String generateToken(Duration expireAt, EmployeeVO employee) {
//		Token 생성
		SecretKey key = Keys.hmacShaKeyFor(//hmacSha 알고리즘으로 구현된 인코드 키
				secretKey.getBytes(StandardCharsets.UTF_8)); //여기 에러나면 hello spring 참고
//		현재 시간과 만료시간 선언
		Date now = new Date();
		Date expiry = new Date(now.getTime()+ expireAt.toMillis());
		
		return Jwts.builder()
				.header().add("typ","JWT").and()
				.issuer(issuer)
				.issuedAt(now)
				.expiration(expiry)
				.subject(employee.getEmpId())
				.claims(Map.of("user",employee))
				.signWith(key)
				.compact();
	}
	/**
	 * 토큰을 이용하여 유져 정보를 전달
	 * @param token 전달된 토큰 정보
	 * @return 회원정보
	 * @throws JsonProcessingException
	 */
	public EmployeeVO getUserFormToken(String token) throws JsonProcessingException {
		SecretKey key = Keys.hmacShaKeyFor(//hmacSha 알고리즘으로 구현된 인코드 키
				secretKey.getBytes(StandardCharsets.UTF_8));
		 Jws<Claims> jws = Jwts.parser()
//					.requireIssuer(this.issuer)
					.verifyWith(key)
					.build()
					.parseSignedClaims(token);
		 Claims claims = jws.getPayload();
	
		
		ObjectMapper om = new ObjectMapper();
		
		Object user = claims.get("user");
		String json = om.writeValueAsString(user);
		
		EmployeeVO employeeVO = om.readValue(json, EmployeeVO.class);
		
		return employeeVO;
		
	}

}
