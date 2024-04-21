package com.ktdsuniversity.edu.pms.login.web;


import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.pms.login.vo.*;
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
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.SessionUtil;
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

    @ResponseBody
    @PostMapping("/ajax/employee/login")
    public AjaxResponse doLogin(HttpSession session, EmployeeVO employeeVO,
                                @RequestParam(defaultValue = "/") String nextUrl,
                                Model model) {

        Validator<EmployeeVO> validator = new Validator<>(employeeVO);

        validator.add("empId", Type.NOT_EMPTY, "사원번호를 입력해 주세요.")
                .add("empId", Type.EMPID, "사원번호 형식으로 입력해 주세요.")
                .add("pwd", Type.NOT_EMPTY, "비밀번호를 입력해주세요.").start();

        if (validator.hasErrors()) {
            Map<String, List<String>> errors = validator.getErrors();
            return new AjaxResponse().append("errors", errors);
        }

        EmployeeVO employee = this.loginLogService.getOneEmployeeByEmpIdAndPwd(employeeVO);

        // 퇴사, 휴직 상태에 따른 로그인 제한 기능
        String loginCheck = employee.getWorkSts();
        if (loginCheck.equals("202")) {
            return new AjaxResponse().append("errorRestDt", "휴직 중인 사원입니다." + "휴직기간 : " + employee.getRestStDt() + " ~ " + employee.getRestEndDt());
        } else if (loginCheck.equals("204")) {
            return new AjaxResponse().append("errorEndDt", "퇴사한 사원입니다." + "퇴사일 : " + employee.getEndDt());
        }

        // 사용중 여부 확인
        if (!SessionUtil.wasLoginEmployee(employee.getEmpId())) {

            // 로그인 기록 DB 저장 메서드
            this.loginLogService.updateLoginLog(employee);
            // LOGIN_LOG 테이블의 LOG_ID 값을 포함한 employeeVO 객체를 재대입한다.
            employee = this.loginLogService.updateEmpLog(employee);

            //로그인 성공시 LGNYN을 Y로 변경
            this.loginLogService.getOneEmpIdUseOtherPlace(employee);

            int commuteCheck = this.loginLogService.getCommuteDt(employee.getEmpId());

            if (commuteCheck == 0) {
                this.loginLogService.insertCommuteIn(employee);
            }

            session.setAttribute("_LOGIN_USER_", employee);
            session.setMaxInactiveInterval(20 * 60);
            SessionUtil.addSession(employee.getEmpId(), session);


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
        this.loginLogService.updateEmpLogout(employeeVO.getLoginLogVO().getLogId());

        SessionUtil.removeSession(employeeVO.getEmpId());

        return "redirect:/employee/login";
    }
    
	//출퇴근을 보여주는 페이지
	@GetMapping("/commute/view")
	public String doCommuteSearch(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, CommuteVO commuteVO, Model model) {
		/**
		 * 현재 로그인한 사원의 CDMN_CODE가 301인지 조회
		 * true: 전체 조회
		 * false: 입력받은 본인의 출퇴근만 조회
		 */
		String AdmnCodeIsSystemFormat = "301";
		if (employeeVO.getAdmnCode().equals(AdmnCodeIsSystemFormat)) {
			CommuteListVO commuteListVO = commuteService.getAllCommuteData(commuteVO);
			model.addAttribute("commuteList", commuteListVO);
			model.addAttribute("commnuteVO", commuteVO);
			return "commute/view";
		} 
		else{
			CommuteListVO commuteListVO = commuteService.getAllCommuteDataByEmpId(employeeVO.getEmpId());
			model.addAttribute("commuteList", commuteListVO);
			return "commute/view";
		}
	}

    // 로그인 기록 확인 페이지
    @GetMapping("/loginlog/view")
    public String viewLoginLog(Model model) {

        LoginLogListVO loginLogListVO = this.loginLogService.getAllLoginLog();
        model.addAttribute("loginLogList", loginLogListVO);

        return "login/loginlogview";
    }

    // 화면 접근 기록 확인 페이지
    @GetMapping("/visitedlog/view")
    public String viewVisitedLog(Model model) {

        VisitedListVO visitedVO = this.loginLogService.getAllVisitedLog();
        model.addAttribute("visitedList", visitedVO);

        return "login/visitedlogview";
    }
}
