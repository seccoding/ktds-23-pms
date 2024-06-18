//package com.ktdsuniversity.edu.pms.beans;
//
//import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//public class RestrictRegistInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        HttpSession httpSession = request.getSession();
//        EmployeeVO employeeVO = (EmployeeVO) httpSession.getAttribute("_LOGIN_USER_");
//
//        String uri = request.getRequestURI();
//
//        if (uri.equals("/ajax/employee/regist") || uri.equals("/employee/regist")) {
//            if (employeeVO.getDeptId().equals("DEPT_230101_000010") || employeeVO.getAdmnCode().equals("301")) {
//                return true;
//            } else {
//                response.sendRedirect("/");
//                return false;
//            }
//        }
//        return true;
//    }
//}
