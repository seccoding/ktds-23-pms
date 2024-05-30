//package com.ktdsuniversity.edu.pms.beans;
//
//import org.apache.tika.utils.StringUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//public class LoginInterceptor implements HandlerInterceptor {
//
//	/**
//	 * Controller 실행 전에 Interceptor가 개입
//	 * 
//	 * @param request  브라우저가 서버에게 요청한 정보
//	 * @param response 서버가 브라우저에게 응답할 정보
//	 * @param handler  실행할 Controller
//	 * @return controller 실행을 계속할것인지 여부 (false 일 경우, 컨트롤러 실행을 하지 않고 즉시 응답해버린다.)
//	 */
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//
//		HttpSession session = request.getSession();
//		EmployeeVO employee = (EmployeeVO) session.getAttribute("_LOGIN_USER_");
////        boolean sessionCheck = SessionUtil.wasLoginEmployee(employeeVO.getEmpId());
//
//		if (employee == null) {
//
//			// 원래 가려했던 URL 정보. -> 현재 요청중인 URL을 확인.
//			// GET, POST, PUT, DELETE, FETCH 등등...
//			String httpMethod = request.getMethod().toLowerCase();
//			String uri = request.getRequestURI();
//			String queryString = request.getQueryString();
//
//			if (httpMethod.equals("get")) {
//				String nextUrl = uri;
//				if (!StringUtils.isEmpty(queryString)) {
//					nextUrl += "?" + queryString;
//				}
//				request.setAttribute("nextUrl", nextUrl);
//			}
//
////			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
////			requestDispatcher.forward(request, response);
//			response.sendRedirect("/employee/login");
//
//			// Controller 실행 x
//			return false;
//		}
//		// Controller 실행
//		return true;
//	}
//
//
//}
