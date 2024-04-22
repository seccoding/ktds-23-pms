package com.ktdsuniversity.edu.pms.beans;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RestrictAccessAfterLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		EmployeeVO employeeVO = (EmployeeVO)session.getAttribute("_LOGIN_USER_");
		
		if (employeeVO != null) {
			response.sendRedirect("/");
			return false;
		}

		String uri = request.getRequestURI();

		System.out.println("+++++++++++++++++" + uri);
		if (uri.equals("/ajax/employee/regist") || uri.equals("/employee/regist")) {
			if (employeeVO.getDeptId().equals("DEPT_230101_000010")) {
				return true;
			} else {
				response.sendRedirect("/");
				return false;
			}
		}
		return true;
	}
}
