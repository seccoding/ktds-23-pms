package com.ktdsuniversity.edu.pms.login.web;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.service.LoginLogService;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

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

    public AjaxResponse doLogin(HttpSession session, EmployeeVO employeeVO, @RequestParam(defaultValue = "/main/mainpage") String nextUrl) {

        EmployeeVO employee = this.loginLogService.getOneEmployeeByEmpIdAndPwd(employeeVO);

        session.setAttribute("_LOGIN_USER_", employee);

        return new AjaxResponse().append("next", nextUrl);
    }
}
