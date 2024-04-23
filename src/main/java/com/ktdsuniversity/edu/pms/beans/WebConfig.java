package com.ktdsuniversity.edu.pms.beans;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ktdsuniversity.edu.pms.login.service.VisitedService;

@Configuration
@Configurable
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private VisitedService visitedService;
	
	@Value("${app.tempsession.enable:false}")
	private boolean enableTempSession;

	@Value("${app.tempsession.empId}")
	private String empId;

	@Value("${app.tempsession.empName}")
	private String empName;

	@Value("${app.authentication.check-url-pattern:/**}")
	private String authCheckUrlPattern;

	@Value("${app.authentication.ignore-url-patterns:}")
	private List<String> authCheckIgnoreUrlPatterns;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**") // /js/로 시작하는 모든 URL
				.addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/css/**") // /css/로 시작하는 모든 URL
				.addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/images/**") // /image/로 시작하는 모든 URL
				.addResourceLocations("classpath:/static/images/");
		registry.addResourceHandler("/html/**") // /image/로 시작하는 모든 URL
				.addResourceLocations("classpath:/static/html/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {


		if (this.enableTempSession) {
			TempSessionInterceptor tempSessionInterceptor = new TempSessionInterceptor();
			tempSessionInterceptor.setEnableTempSession(enableTempSession);
			tempSessionInterceptor.setTempEmpId(empId);
			tempSessionInterceptor.setTempEmpName(empName);

			registry.addInterceptor(tempSessionInterceptor)
					.addPathPatterns("/**");
		}

		RecordScreenAccessAfterLoginInterceptor rsaalException = new RecordScreenAccessAfterLoginInterceptor();
		rsaalException.setVisitedService(visitedService);
		
		//세션에 값이 있으면 해당 방문한 페이지를 DB에 저장시키는 interceptor
		registry.addInterceptor(rsaalException)
				.addPathPatterns(this.authCheckUrlPattern)
				.excludePathPatterns(this.authCheckIgnoreUrlPatterns);
		
		//세션에 값이 없으면 url로 접근을 막는 interceptor
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns(this.authCheckUrlPattern)
				.excludePathPatterns(this.authCheckIgnoreUrlPatterns);
		
		//세션에 값이 있으면 아래 url의 접근을 막는 interceptor
		registry.addInterceptor(new RestrictAccessAfterLoginInterceptor())
				.addPathPatterns("/employee/login", "/ajax/employee/login");

		//경영지원부가 아니라면 아래 url의 접근을 막는 interceptor
		registry.addInterceptor(new RestrictRegistInterceptor())
				.addPathPatterns("/employee/regist", "/ajax/employee/regist");
	}
}