package com.ktdsuniversity.edu.pms.beans.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ktdsuniversity.edu.pms.beans.security.handler.LoginSuccessHandler;
import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Bean
	UserDetailsService userDetailsService() {
		return new SecurityUserDetailsService(employeeDao);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new SecuritySHA() ;
	}
	
	
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/WEB-INF/views/**"))
//				CSRF적용을 위해 Spring Security 설정 필요
//				.requestMatchers(AntPathRequestMatcher.antMatcher("/member/login"))
//				.requestMatchers(AntPathRequestMatcher.antMatcher("/member/regist/**"))
				.requestMatchers(AntPathRequestMatcher.antMatcher("/error/**"))
				.requestMatchers(AntPathRequestMatcher.antMatcher("/favicon.ico"))
				.requestMatchers(AntPathRequestMatcher.antMatcher("/member/**-delete-me"))
				.requestMatchers(AntPathRequestMatcher.antMatcher("/js/**"))
				.requestMatchers(AntPathRequestMatcher.antMatcher("/css/**"));
	}
	
	@Bean
	SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(httpRequest ->
				httpRequest
				.requestMatchers(AntPathRequestMatcher.antMatcher("/employee/login")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/ajax/employee/login")).permitAll()
				.anyRequest().permitAll());
		
		http.formLogin( formLogin -> 
						formLogin.loginPage("/employee/login")
								 .usernameParameter("empId")
								 .passwordParameter("pwd")
								 .successHandler(new LoginSuccessHandler())
				);
		
		http.csrf(csrf-> csrf.disable()) 
			.cors(cors ->cors.disable());
		return http.build();
	}
	
}
