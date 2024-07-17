package com.ktdsuniversity.edu.pms.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.pms.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RecordScreenAccessAfterLoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(RecordScreenAccessAfterLoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();

        // 로그인 페이지와 로그인 API에 대한 요청은 인증되지 않아도 접근 허용
        if (uri.equals("/login") || uri.equals("/api/v1/login") || uri.equals("/api/v1/member")) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("_LOGIN_USER_") == null) {
            logger.info("Unauthorized access attempt to {}", uri);
            response.sendRedirect("/login");
            return false;
        }

        MemberVO memberVO = (MemberVO) session.getAttribute("_LOGIN_USER_");
        if (memberVO == null) {
            logger.info("Unauthorized access attempt to {}", uri);
            response.sendRedirect("/login");
            return false;
        }

        logger.info("User {} accessed {}", memberVO.getMemId(), uri);
        return true;
    }
}
