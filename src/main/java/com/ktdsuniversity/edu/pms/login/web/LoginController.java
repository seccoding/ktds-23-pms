package com.ktdsuniversity.edu.pms.login.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

		if (validator.hasErrors()) {
			Map<String, List<String>> errors = validator.getErrors();
			return new AjaxResponse().append("errors", errors);
		}

		EmployeeVO employee = this.loginLogService.getOneEmployeeByEmpIdAndPwd(employeeVO);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime currentDate = LocalDateTime.now();

		if (employee.getEndDt() != null) {
			LocalDateTime endDate = LocalDateTime.parse(employee.getEndDt(), formatter);

			if (currentDate.isAfter(endDate)) {

				return new AjaxResponse().append("errorEndDt", "퇴사한 사원입니다.");
			}
		}
		if (employee.getRestStDt() != null) {
			LocalDateTime restStDt = LocalDateTime.parse(employee.getRestStDt(), formatter);
			LocalDateTime restEndDt = LocalDateTime.parse(employee.getRestEndDt(), formatter);

			if (currentDate.compareTo(restStDt) >= 0 && currentDate.compareTo(restEndDt) <= 0) {
				return new AjaxResponse().append("errorRestDt", "휴직 중인 사원입니다.");
			}
		}

		// 로그인 할때 lgnYN이 N일 경우 로그인 진행
		if (!SessionUtil.wasLoginEmployee(employee.getEmpId())) {
//		if (employee.getLgnYn().equals("N")) {

			session.setAttribute("_LOGIN_USER_", employee);
			session.setMaxInactiveInterval(20 * 60);
			SessionUtil.addSession(employee.getEmpId(), session);

			// 로그인 성공시 LGNYN을 Y로 변경
			employee = this.loginLogService.getOneEmpIdUseOtherPlace(employeeVO);

			return new AjaxResponse().append("next", nextUrl);
		}
		// 아닐경우 오류 발생
		else {
			return new AjaxResponse().append("errorUseNow", "이미 로그인중인 사원번호입니다.");
		}
	}

	@GetMapping("/employee/logout")
	public String doLogout(HttpSession session, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		this.loginLogService.getOneEmpIdNotUseNow(employeeVO);
		SessionUtil.removeSession(employeeVO.getEmpId());

		session.invalidate();

		return "redirect:/employee/login";
	}

}
