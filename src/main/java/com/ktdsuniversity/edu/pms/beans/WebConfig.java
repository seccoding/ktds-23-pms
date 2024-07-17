package com.ktdsuniversity.edu.pms.beans;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Configurable
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.tempsession.enable:false}")
    private boolean enableTempSession;

    @Value("${app.tempsession.memId:defaultMemId}")
    private String tempMemId;

    @Value("${app.tempsession.Name:defaultName}")
    private String tempName;

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
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/html/**")
                .addResourceLocations("classpath:/static/html/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (this.enableTempSession) {
            TempSessionInterceptor tempSessionInterceptor = new TempSessionInterceptor();
            tempSessionInterceptor.setEnableTempSession(enableTempSession);
            tempSessionInterceptor.setTempMemId(tempMemId);
            tempSessionInterceptor.setTempName(tempName);

            registry.addInterceptor(tempSessionInterceptor)
                    .addPathPatterns("/**");
        }

        RecordScreenAccessAfterLoginInterceptor rsaalInterceptor = new RecordScreenAccessAfterLoginInterceptor();

        // 세션에 값이 있으면 해당 페이지에 접근을 허용하는 인터셉터
        registry.addInterceptor(rsaalInterceptor)
                .addPathPatterns(this.authCheckUrlPattern)
                .excludePathPatterns(this.authCheckIgnoreUrlPatterns)
                .excludePathPatterns("/login")
                .excludePathPatterns("/api/v1/login")
                .excludePathPatterns("/api/v1/member");

        // 세션에 값이 없으면 URL로 접근을 막는 인터셉터
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(this.authCheckUrlPattern)
                .excludePathPatterns(this.authCheckIgnoreUrlPatterns)
                .excludePathPatterns("/login")
                .excludePathPatterns("/api/v1/login")
                .excludePathPatterns("/api/v1/member");
    }
}
