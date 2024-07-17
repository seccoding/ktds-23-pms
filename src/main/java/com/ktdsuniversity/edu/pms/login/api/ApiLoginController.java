package com.ktdsuniversity.edu.pms.login.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.member.vo.MemberVO;
import com.ktdsuniversity.edu.pms.login.service.LoginLogService;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1")
public class ApiLoginController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/login")
    public ApiResponse doLogin(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String memId = body.get("memId");
        String pwd = body.get("pwd");

        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(memId, pwd);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 사용자 정보를 세션에 저장
            HttpSession session = request.getSession(true);
            session.setAttribute("_LOGIN_USER_", authentication.getPrincipal());

            return ApiResponse.Ok(authentication.getPrincipal());
        } catch (BadCredentialsException e) {
            return ApiResponse.Error("Invalid username or password");
        } catch (Exception e) {
            return ApiResponse.Error("An error occurred during login");
        }
    }

    @PostMapping("/logout")
    public ApiResponse doLogout(Authentication authentication, @RequestBody Map<String, Boolean> body) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberVO memberVO = ((SecurityUser) userDetails).getMemberVO();
        
        this.loginLogService.insertLogoutProcess(memberVO.getMemId(), body.get("isLeaveWork"));
        
        return ApiResponse.Ok(null);
    }
}
