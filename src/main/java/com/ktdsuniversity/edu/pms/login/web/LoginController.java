package com.ktdsuniversity.edu.pms.login.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.service.LoginLogService;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.SessionUtil;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginLogService loginLogService;

	@GetMapping("/employee/login")
	public String viewLoginPage() {
		return "login/login";
	}

	@GetMapping("/main/mainpage")
	public String viewMainPage() {
		return "main/mainpage";
	}

	@ResponseBody
	@PostMapping("/ajax/employee/login")
	public AjaxResponse doLogin(HttpSession session, EmployeeVO employeeVO,
			@RequestParam(defaultValue = "/main/mainpage") String nextUrl) {
		
		logger.info("NextUrl: " + nextUrl);
		
		Validator<EmployeeVO> validator = new Validator<>(employeeVO);
		
		validator.add("empId", Type.NOT_EMPTY, "사원번호를 입력해 주세요.")
				.add("empId", Type.EMPID, "사원번호 형식으로 입력해 주세요.")
				.add("pwd", Type.NOT_EMPTY, "비밀번호를 입력해주세요.").start();
		
		if(validator.hasErrors()) {
			Map<String, List<String>> errors = validator.getErrors();
			return new AjaxResponse().append("errors", errors);
		}
		
		EmployeeVO employee = this.loginLogService.getOneEmployeeByEmpIdAndPwd(employeeVO);
		//로그인 할때 lgnYN이 N일 경우 로그인 진행
		if (!SessionUtil.wasLoginEmployee(employee.getEmpId())) {
//		if (employee.getLgnYn().equals("N")) {
			
			session.setAttribute("_LOGIN_USER_", employee);
			session.setMaxInactiveInterval(20 * 60);
			SessionUtil.addSession(employee.getEmpId(), session);
			
//			session.setMaxInactiveInterval(20);
			
//			EmployeeVO EmpLog = (EmployeeVO) session.getAttribute("_LOGIN_USER_");
//
////			HttpSession log = (HttpSession) session.getAttribute("_LOGIN_USER_");
////			
//			if (EmpLog == null || EmpLog.equals("")) {
//				System.out.println("로그인로그인로그인로그인로그인로그인로그인로그인로그인로그인로그인로그인로그인로그인로그인로그인로그인로그인");
//				this.loginLogService.getOneEmpIdNotUseNow(employee);
//			}
			//로그인 성공시 LGNYN을 Y로 변경
			employee = this.loginLogService.getOneEmpIdUseOtherPlace(employeeVO);
			
			return new AjaxResponse().append("next", nextUrl);
		}
		// 아닐경우 오류 발생
		else {
			return new AjaxResponse().append("errorUseNow", "이미 로그인중인 사원번호입니다.");
		}
	}
		
	@GetMapping("/employee/logout")
	public String doLogout(HttpSession session, @SessionAttribute("_LOGIN_USER_")EmployeeVO employeeVO) {
		
		this.loginLogService.getOneEmpIdNotUseNow(employeeVO);
		SessionUtil.removeSession(employeeVO.getEmpId());
		
		session.invalidate();
		
		
		return "redirect:/employee/login";
	}
}
