package com.ktdsuniversity.edu.pms.beans.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JsonWebTokenProvider jsonWebTokenProvider;
	
	@Override
	protected void doFilterInternal
			(HttpServletRequest request
			, HttpServletResponse response
			, FilterChain filterChain)
			throws ServletException, IOException {
		
		String servletPath = request.getServletPath();
		
		if (CorsUtils.isPreFlightRequest(request)) {
			filterChain.doFilter(request, response);
		}
		else if(servletPath.startsWith("/api")) {
			String jwt = request.getHeader("Authorization");
		
			EmployeeVO employeeVO = this.jsonWebTokenProvider.getUserFormToken(jwt);
			SecurityUser user = new SecurityUser(employeeVO);
			
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		
		filterChain.doFilter(request, response);
	}

}

