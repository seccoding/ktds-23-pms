package com.ktdsuniversity.edu.pms.beans.security.handler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception) 
			throws IOException, ServletException {
		String failureMessage = exception.getMessage();
		
//		실패시 보여줄 url
		String loginPath ="/";
		
		RequestDispatcher rd = request.getRequestDispatcher(loginPath);
		request.setAttribute("message", failureMessage);
		rd.forward(request, response);
		
	}

}
