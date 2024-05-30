package com.ktdsuniversity.edu.pms.beans.security;

import java.util.List;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.ktdsuniversity.edu.pms.beans.security.handler.LoginFailureHandler;
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
				.requestMatchers(AntPathRequestMatcher.antMatcher("/auth/token")).permitAll()
				.anyRequest().authenticated());
		
		http.formLogin( formLogin -> 
						formLogin.loginPage("/employee/login")
								 .loginProcessingUrl("/member/login-proc")
								 .usernameParameter("empId")
								 .passwordParameter("pwd")
								 .successHandler(new LoginSuccessHandler())
								 .failureHandler(new LoginFailureHandler())	
				);	
		
//		
		http.csrf(csrf-> csrf
				.ignoringRequestMatchers(
				AntPathRequestMatcher.antMatcher("/auth/token"),
				AntPathRequestMatcher.antMatcher("/api/**"))); 
		
		http.cors(cors ->{
			CorsConfigurationSource sourse =request->{
				CorsConfiguration config = new CorsConfiguration();
				
				config.setAllowedOrigins(List.of("http://localhost:3000"));
				config.setAllowedHeaders(List.of("*"));
				config.setAllowedMethods(List.of("GET","PUT","POST","DELETE"));
				return config;
			};
			cors.configurationSource(sourse);
		});
		
		
		return http.build();
	}
	
}
