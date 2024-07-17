package com.ktdsuniversity.edu.pms.beans;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.pms.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TempSessionInterceptor implements HandlerInterceptor {

    private boolean enableTempSession;
    private String tempMemId;
    private String tempName;

    public void setEnableTempSession(boolean enableTempSession) {
        this.enableTempSession = enableTempSession;
    }

    public void setTempMemId(String tempMemId) {
        this.tempMemId = tempMemId;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (enableTempSession) {
            HttpSession session = request.getSession();
            MemberVO tempUser = new MemberVO();
            tempUser.setMemId(tempMemId);
            tempUser.setName(tempName);
            session.setAttribute("_LOGIN_USER_", tempUser);
        }
        return true;
    }
}