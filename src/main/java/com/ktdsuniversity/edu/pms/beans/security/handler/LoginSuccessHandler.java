package com.ktdsuniversity.edu.pms.beans.security.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.member.vo.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	private final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		logger.info("Spring Security 인증 성공");
		
		Object user = authentication.getPrincipal();
		
		if(user instanceof UserDetails) {
			MemberVO memberVO = ((SecurityUser)user).getMemberVO();
			logger.info("memId :"+memberVO.getMemId());
			logger.info("password :"+memberVO.getPwd());
		}else {
			logger.info("memId :"+user);
		}
		
		response.sendRedirect("/requirement/search");
		
		
	}

}
