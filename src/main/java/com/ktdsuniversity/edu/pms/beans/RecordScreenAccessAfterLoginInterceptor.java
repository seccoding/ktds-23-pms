package com.ktdsuniversity.edu.pms.beans;

import com.ktdsuniversity.edu.pms.approval.service.ApprovalServiceImpl;
import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.beans.security.jwt.JsonWebTokenProvider;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.pms.login.service.VisitedService;
import com.ktdsuniversity.edu.pms.login.vo.VisitedVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * /auth/token 을 제외한 모든 요청시 사용되는 Interceptor
 * request header 에 있는 token 값을 사용해서 EmployeeVO 를 가져온다음 
 * 접속기록을 DB에 insert 하고 Log 를 남긴다
 * 
 */
public class RecordScreenAccessAfterLoginInterceptor implements HandlerInterceptor {

	private VisitedService visitedService;
	private JsonWebTokenProvider jsonWebTokenProvider;

	private Logger logger = LoggerFactory.getLogger(RecordScreenAccessAfterLoginInterceptor.class);

//	Interceptor 에서 의존성 주입을 하려면 Autowired 가 안됨 webConfig 참고
	public void setVisitedService(VisitedService visitedService) {
		this.visitedService = visitedService;
	}
//	Interceptor 에서 의존성 주입을 하려면 Autowired 가 안됨 webConfig 참고
	public void setJsonWebTokenProvider(JsonWebTokenProvider jsonWebTokenProvider) {
		this.jsonWebTokenProvider = jsonWebTokenProvider;
	}

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getHeader("Authorization") ==null ||request.getHeader("Authorization").isEmpty()) {
			return false;
		}
//		request header 에 있는 token 값을 사용해서 EmployeeVO 를 가져온다
		String token =request.getHeader("Authorization") ;
		
		EmployeeVO employeeVO = this.jsonWebTokenProvider.getUserFormToken(token);
		
		if (employeeVO == null) {//유져 정보 없으면 다시 kick
			return false;
		}else {//유져의 정보가 있으면 접근기록을 DB 에  insert
			VisitedVO visitedVO = new VisitedVO();
			StringBuffer uri = request.getRequestURL();
			StringBuffer nextUrl = uri;
			visitedVO.setEmpId(employeeVO.getEmpId());
			visitedVO.setAccsUrl(nextUrl.toString());
			boolean insertOneEmpVisitedHistory = visitedService.insertOneEmpVisitedHistory(visitedVO);
			
//			Insert 성공, 실패 결과와 insert 정보를 log 에 남김
			if (insertOneEmpVisitedHistory) {
				logger.debug("화면 접근 기록에 성공했습니다.");
				logger.debug("유져 Id: "+visitedVO.getEmpId());
				logger.debug("URL :"+visitedVO.getAccsUrl());
				
			} else {
				logger.debug("화면 접근 기록에 실패했습니다.");
				logger.debug("유져 Id: "+visitedVO.getEmpId());
				logger.debug("URL :"+visitedVO.getAccsUrl());
			}
		}
		return true;
	}
}
