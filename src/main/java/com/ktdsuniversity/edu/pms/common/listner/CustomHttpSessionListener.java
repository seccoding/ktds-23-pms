//package com.ktdsuniversity.edu.pms.common.listner;
//
//import com.ktdsuniversity.edu.pms.approval.service.ApprovalServiceImpl;
//import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
//import com.ktdsuniversity.edu.pms.login.service.LoginLogService;
//import com.ktdsuniversity.edu.pms.utils.SessionUtil;
//
//import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.HttpSessionEvent;
//import jakarta.servlet.http.HttpSessionListener;
//
//@Component
//public class CustomHttpSessionListener implements HttpSessionListener {
//
//	private Logger logger = LoggerFactory.getLogger(ApprovalServiceImpl.class);
//
//	@Autowired
//	private LoginLogService loginLogService;
//	
//	@Override
//	public void sessionDestroyed(HttpSessionEvent se) {
//		
//		HttpSession session = se.getSession();
//		EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("_LOGIN_USER_");
//		int commuteFnshCheck = this.loginLogService.getCommutFnshCount(employeeVO.getEmpId());
//
//		if (employeeVO != null) {
//			SessionUtil.removeSession(employeeVO.getEmpId(), false);
//			boolean getOneEmpIdNotUseNowSuccess = this.loginLogService.updateOneEmpIdNotUseNow(employeeVO);
//			if (getOneEmpIdNotUseNowSuccess) {
//				logger.debug("로그인 상태 변경('N')에 성공했습니다.");
//			} else {
//				logger.debug("로그인 상태 변경('N')에 실패했습니다.");
//			}
//			boolean updateEmpLogoutSuccess =  this.loginLogService.updateEmpLogout(employeeVO.getLoginLogVO().getLogId());
//			if (updateEmpLogoutSuccess) {
//				logger.debug("로그아웃 기록에 성공했습니다.(세션 만료)");
//			}
//
//			if (commuteFnshCheck > 0) {
//				boolean updateCommuteFnshSuccess = this.loginLogService.updateCommuteFnsh(employeeVO);
//				if (updateCommuteFnshSuccess) {
//					logger.debug("퇴근 기록에 성공했습니다. (18:20 경과)");
//				}
//			}
//		}
//		
//	}
//	
//}
