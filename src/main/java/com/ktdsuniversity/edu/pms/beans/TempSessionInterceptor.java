package com.ktdsuniversity.edu.pms.beans;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TempSessionInterceptor implements HandlerInterceptor {

	private boolean enableTempSession;
	private String tempEmpId;
	private String tempEmpName;

	public boolean isEnableTempSession() {
		return enableTempSession;
	}

	public void setEnableTempSession(boolean enableTempSession) {
		this.enableTempSession = enableTempSession;
	}

	public String getTempEmpId() {
		return tempEmpId;
	}

	public void setTempEmpId(String tempEmpId) {
		this.tempEmpId = tempEmpId;
	}

	public String getTempEmpName() {
		return tempEmpName;
	}

	public void setTempEmpName(String tempEmpName) {
		this.tempEmpName = tempEmpName;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (this.enableTempSession
				&& request.getSession().getAttribute("_LOGIN_USER_") == null) {
			EmployeeVO employeeVO = new EmployeeVO();
			employeeVO.setEmpId(tempEmpId);
			employeeVO.setEmpName(tempEmpName);

			request.getSession().setAttribute("_LOGIN_USER_", employeeVO);
		}
		return true;
	}

}
