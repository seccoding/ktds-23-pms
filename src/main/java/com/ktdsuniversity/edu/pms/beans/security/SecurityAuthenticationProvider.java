package com.ktdsuniversity.edu.pms.beans.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String empId = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(empId);
		
		String salt = ((SecurityUser)userDetails).getEmployeeVO().getSalt();
		
		((SecuritySHA)passwordEncoder).setSalt(salt);
		
		boolean isMatchedPassword = this.passwordEncoder.matches(password, userDetails.getPassword());
	
		if(!isMatchedPassword) {
			throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
		
		return new UsernamePasswordAuthenticationToken(userDetails, password,userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
