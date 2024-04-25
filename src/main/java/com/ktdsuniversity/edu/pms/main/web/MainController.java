package com.ktdsuniversity.edu.pms.main.web;


import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MainController {

	@Autowired
	private LoginLogService loginLogService;

	@GetMapping("/")
	public String viewMainLayoutPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, Model model) {
		if (this.loginLogService.getPwdCndt(employeeVO.getEmpId()) > 0) {
			model.addAttribute("pwdMessage", "비밀번호를 변경한지 90일지 지났습니다. 비밀번호를 변경해주세요.");
		}
		return "layout/main_layout";
	}
	
	@GetMapping("/main/dashboard") 
	public String viewMainDashboardPage(@SessionAttribute("_LOGIN_USER_")EmployeeVO employeeVO) {
		return "main/dashboard";
	}
}
