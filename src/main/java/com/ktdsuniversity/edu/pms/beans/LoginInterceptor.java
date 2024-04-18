//package com.ktdsuniversity.edu.pms.beans;
//
//import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
//import com.ktdsuniversity.edu.pms.utils.SessionUtil;
//import com.ktdsuniversity.edu.pms.utils.StringUtil;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.apache.tika.utils.StringUtils;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//public class LoginInterceptor implements HandlerInterceptor {
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        HttpSession session = request.getSession();
//        EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("_LOGIN_USER");
//        boolean sessionCheck = SessionUtil.wasLoginEmployee(employeeVO.getEmpId());
//
//        if (employeeVO == null) {
//            String httpMethod = request.getMethod().toLowerCase();
//            String uri = request.getRequestURI();
//            String queryString = request.getQueryString();
//
//            if (httpMethod.equals("get")) {
//                String nextUrl = uri;
//
//                if (!StringUtils.isEmpty(queryString)) {
//                    nextUrl += "?" + queryString;
//                }
//
//                request.setAttribute("nextUrl", nextUrl);
//            }
//
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
//            requestDispatcher.forward(request, response);
//
//            return false;
//        }
//        return true;
//    }
//
//}
