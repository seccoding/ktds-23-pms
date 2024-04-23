package com.ktdsuniversity.edu.pms.beans;

import com.ktdsuniversity.edu.pms.approval.service.ApprovalServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.service.VisitedService;
import com.ktdsuniversity.edu.pms.login.vo.VisitedVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RecordScreenAccessAfterLoginInterceptor implements HandlerInterceptor {

	private VisitedService visitedService;

	private Logger logger = LoggerFactory.getLogger(ApprovalServiceImpl.class);

	public void setVisitedService(VisitedService visitedService) {
		this.visitedService = visitedService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getSession().getAttribute("_LOGIN_USER_") != null) {
			VisitedVO visitedVO = new VisitedVO();

			HttpSession session = request.getSession();
			EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("_LOGIN_USER_");

			StringBuffer uri = request.getRequestURL();
			StringBuffer nextUrl = uri;
			
			visitedVO.setEmpId(employeeVO.getEmpId());
			visitedVO.setAccsUrl(nextUrl.toString());

			boolean insertOneEmpVisitedHistory = visitedService.insertOneEmpVisitedHistory(visitedVO);
			if (insertOneEmpVisitedHistory) {
				logger.debug("화면 접근 기록에 성공했습니다.");
			} else {
				logger.debug("화면 접근 기록에 실패했습니다.");
			}
		}
		return true;
	}
}
