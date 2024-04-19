package com.ktdsuniversity.edu.pms.login.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.service.CommuteService;
import com.ktdsuniversity.edu.pms.login.service.LoginLogService;
import com.ktdsuniversity.edu.pms.login.vo.CommuteListVO;
import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.SessionUtil;
import com.ktdsuniversity.edu.pms.utils.StringUtil;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private LoginLogService loginLogService;
	
	
	@Autowired
	private CommuteService commuteService;

	@GetMapping("/employee/login")
	public String viewLoginPage() {
		return "login/login";
	}

	@GetMapping("/main/mainpage")
	public String viewMainPage(HttpSession session, Model model) {

		EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("_LOGIN_USER_");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime pwdCnDt = LocalDateTime.parse(employeeVO.getPwdCnDt(), formatter);

		if (currentDate.compareTo(pwdCnDt.plusDays(90)) >= 0) {
			model.addAttribute("pwdMessage", "비밀번호를 변경한지 90일이 지났습니다. 비밀번호를 변경해주세요.");
			System.out.println("------------------------" + pwdCnDt);
		}
		return "main/mainpage";
	}

	@ResponseBody
	@PostMapping("/ajax/employee/login")
	public AjaxResponse doLogin(HttpSession session, EmployeeVO employeeVO,
			@RequestParam(defaultValue = "/") String nextUrl, Model model) {

		Validator<EmployeeVO> validator = new Validator<>(employeeVO);

		validator.add("empId", Type.NOT_EMPTY, "사원번호를 입력해 주세요.").add("empId", Type.EMPID, "사원번호 형식으로 입력해 주세요.")
				.add("pwd", Type.NOT_EMPTY, "비밀번호를 입력해주세요.").start();

		if (validator.hasErrors()) {
			Map<String, List<String>> errors = validator.getErrors();
			return new AjaxResponse().append("errors", errors);
		}

		EmployeeVO employee = this.loginLogService.getOneEmployeeByEmpIdAndPwd(employeeVO);

		// 퇴사, 휴직 상태에 따른 로그인 제한 기능
		String loginCheck = employee.getWorkSts();
		if (loginCheck.equals("202")) {
			return new AjaxResponse().append("errorRestDt",
					"휴직 중인 사원입니다." + "휴직기간 : " + employee.getRestStDt() + " ~ " + employee.getRestEndDt());
		} else if (loginCheck.equals("204")) {
			return new AjaxResponse().append("errorEndDt", "퇴사한 사원입니다." + "퇴사일 : " + employee.getEndDt());
		}

		// 사용중 여부 확인
		if (!SessionUtil.wasLoginEmployee(employee.getEmpId())) {
			// 로그인 기록 DB 저장 메서드
			this.loginLogService.updateLoginLog(employee);
			// LOGIN_LOG 테이블의 LOG_ID 값을 포함한 employeeVO 객체를 재대입한다.
			employee = this.loginLogService.updateEmpLog(employee);

			session.setAttribute("_LOGIN_USER_", employee);
			session.setMaxInactiveInterval(20 * 60);
			SessionUtil.addSession(employee.getEmpId(), session);

			// 로그인 성공시 LGNYN을 Y로 변경
			employee = this.loginLogService.getOneEmpIdUseOtherPlace(employeeVO);
			return new AjaxResponse().append("next", nextUrl);
		} else {
			return new AjaxResponse().append("errorUseNow", "이미 로그인중인 사원번호입니다.");
		}
	}

	@GetMapping("/employee/logout")
	public String doLogout(HttpSession session, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		this.loginLogService.getOneEmpIdNotUseNow(employeeVO);
		this.loginLogService.updateEmpLogout(employeeVO);
		SessionUtil.removeSession(employeeVO.getEmpId());

//		session.invalidate();

		return "redirect:/employee/login";
	}
	
	
//	//출퇴근을 보여주는 페이지
//	@GetMapping("/commute/view")
//	public String viewCommutePage() {
//		return "commute/view";
//	}
//	
//	
//	@ResponseBody
//	@PostMapping("/commute/view")
//	public AjaxResponse doCommuteSearch(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, CommuteVO commuteVO) {
//		
//		String empIdIsSystemFormat = ".*system.*";
//		/**
//		 * EMP_ID가 시스템 계정인지 검사하고
//		 * 맞다면 전체 조회
//		 * 아니라면 입력받은 본인의 출퇴근을 조회
//		 */
//		if (employeeVO.getEmpId().matches(empIdIsSystemFormat)) {
//			CommuteListVO commuteListVO = commuteService.getAllCommuteData();
//			return new AjaxResponse().append("commuteData", commuteListVO);
//		} else{
//			CommuteListVO commuteListVO = commuteService.getAllCommuteDataByEmpId();
//			return new AjaxResponse().append("commuteData", commuteListVO);
//		}
//	}
	

}
