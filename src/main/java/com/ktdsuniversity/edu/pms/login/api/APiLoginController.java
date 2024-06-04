package com.ktdsuniversity.edu.pms.login.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
	/**
	 * 18 시 30 분에 퇴근하지 않은 직원을 일괄 퇴근처리한다 
	 */
	@Scheduled(cron ="0 30 18 * * *")
	public void doAllEmployeeLeaveWork() {
//		TODO 금일 출근직원중 퇴근하지 않은 직원을 가져와서 	
//		해당 직원출근기록을 전부 퇴근처리(18:20)서비스를 호출
//		해당 출근인원만큼 퇴근처리 되지 않으면 다시실행 
		
	}
}
