package com.ktdsuniversity.edu.pms.beans.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.ktdsuniversity.edu.pms.member.dao.MemberDao;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MemberDao memberDao;

    @Bean
    UserDetailsService userDetailsService() {
        return new SecurityUserDetailsService(memberDao);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new SecuritySHA();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(AntPathRequestMatcher.antMatcher("/auth/token")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/member")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/login")).permitAll() // 로그인 페이지는 인증 없이 접근 가능
                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/login")).permitAll() // 로그인 API는 인증 없이 접근 가능
                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/phoneplan")).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll()
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(
                    AntPathRequestMatcher.antMatcher("/auth/token"),
                    AntPathRequestMatcher.antMatcher("/api/**")
                )
            )
            .cors(cors -> {
                CorsConfigurationSource source = request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("*"));
                    config.setAllowedMethods(List.of("*"));
                    config.setAllowedHeaders(List.of("*"));
                    return config;
                };
                cors.configurationSource(source);
            })
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            );

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
