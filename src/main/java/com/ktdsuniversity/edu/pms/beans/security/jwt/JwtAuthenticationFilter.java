//package com.ktdsuniversity.edu.pms.beans.security.jwt;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
//import com.ktdsuniversity.edu.pms.member.vo.MemberVO;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    @Autowired
//    private JsonWebTokenProvider jsonWebTokenProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String servletPath = request.getServletPath();
//
//        if (servletPath.startsWith("/api") && !servletPath.equals("/api/v1/member")) {
//            String jwt = request.getHeader("Authorization");
//            MemberVO memberVO = null;
//            if (jwt != null && !jwt.isEmpty()) {
//            	memberVO = this.jsonWebTokenProvider.getUserFormToken(jwt);
//            }
//
//            if (memberVO != null) {
//                SecurityUser user = new SecurityUser(memberVO);
//                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
