package com.ktdsuniversity.edu.pms.login.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.service.LoginLogService;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

@RestController
@RequestMapping("/api")
public class APiLoginController {
	
	@Autowired
	private LoginLogService loginLogService;
	
	
	@PostMapping("/logout")
	public ApiResponse doLogout(Authentication authentication, @RequestBody Map<String,Boolean> body) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		this.loginLogService.insertLogoutProcess(employeeVO.getEmpId(), body.get("isLeaveWork"));
		
		return ApiResponse.Ok(null);
	}
}
