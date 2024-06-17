package com.ktdsuniversity.edu.pms.login.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.dao.CommuteDao;
import com.ktdsuniversity.edu.pms.login.service.CommuteService;
import com.ktdsuniversity.edu.pms.login.service.LoginLogService;
import com.ktdsuniversity.edu.pms.login.vo.CommuteListVO;
import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.SecurityUtils;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class ApiLoginController {
	
	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private CommuteService commuteService;
	@Autowired
	private CommuteDao commuteDao;
	
	SecurityUtils securityUtils = new SecurityUtils();
	
	@PostMapping("/logout")
	public ApiResponse doLogout(Authentication authentication, @RequestBody Map<String,Boolean> body) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		this.loginLogService.insertLogoutProcess(employeeVO.getEmpId(), body.get("isLeaveWork"));
		
		return ApiResponse.Ok(null);
	}
	/**
	 * 모든 통근 기록을 조회한다<br>
	 * commuteType: {today, oneMonth, twoMonth, thrMonth}<br>
	 * searchType: {cmmtDate, empName, empId}<br>
	 * searchKeyword: {searchType의 내용}<br>
	 * @param authentication 인증정보 <br>
	 * @param commuteVO 검색정보 parsing
	 * @return
	 */
	@GetMapping("/commute")
	public ApiResponse getAllCommuteList(Authentication authentication,  CommuteVO commuteVO) {

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
//		관리자가 아니고, 직급이 부장이하인 경우는 본인의 id 만 조회하게 강제
		
		if(Integer.parseInt(employeeVO.getPstnId())<106) {
			commuteVO.setEmpId(employeeVO.getEmpId());
		}
		
		CommuteListVO commuteListVO  =this.commuteService.getAllCommuteData(commuteVO);
		
		return ApiResponse.Ok(commuteListVO);
	}
	
	@GetMapping("/commute/one")
	public ApiResponse getCommute(Authentication authentication) {
		EmployeeVO employeeVO = securityUtils.getEmpVoByAuthentication(authentication);
		
		CommuteVO commuteVO= this.commuteDao.getOneCommuteDataByEmpIdToday(employeeVO.getEmpId());
		return ApiResponse.Ok(commuteVO);
	}
	
	
//	public ApiResponse searchAllCommuteList(Authentication authentication , @RequestBody )
	/**
	 * 18 시 30 분에 퇴근하지 않은 직원을 일괄 퇴근처리한다 
	 */
//	@Scheduled(cron ="0 30 18 * * *")
//	public void doAllEmployeeLeaveWork() {
//		TODO 금일 출근직원중 퇴근하지 않은 직원을 가져와서 	
//		해당 직원출근기록을 전부 퇴근처리(18:20)서비스를 호출
//		해당 출근인원만큼 퇴근처리 되지 않으면 다시실행 
//		batch로 구현 완료	
//	}
}
