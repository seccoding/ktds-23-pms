package com.ktdsuniversity.edu.pms.common.listner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.service.LoginLogService;
import com.ktdsuniversity.edu.pms.utils.SessionUtil;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@Component
public class CustomHttpSessionListener implements HttpSessionListener {

	@Autowired
	private LoginLogService loginLogService;
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
		HttpSession session = se.getSession();
		EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("_LOGIN_USER_");
		if (employeeVO != null) {
			SessionUtil.removeSession(employeeVO.getEmpId(), false);
			this.loginLogService.getOneEmpIdNotUseNow(employeeVO);
		}
		
	}
	
}
