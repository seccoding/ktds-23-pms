//package com.ktdsuniversity.edu.pms.login.web;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttribute;
//
//import com.ktdsuniversity.edu.pms.approval.service.ApprovalServiceImpl;
//import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
//import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
//import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
//import com.ktdsuniversity.edu.pms.login.service.CommuteService;
//import com.ktdsuniversity.edu.pms.login.service.LoginLogService;
//import com.ktdsuniversity.edu.pms.login.vo.CommuteListVO;
//import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;
//import com.ktdsuniversity.edu.pms.login.vo.LoginLogListVO;
//import com.ktdsuniversity.edu.pms.login.vo.LoginLogVO;
//import com.ktdsuniversity.edu.pms.login.vo.VisitedListVO;
//import com.ktdsuniversity.edu.pms.login.vo.VisitedVO;
//import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
//import com.ktdsuniversity.edu.pms.utils.SessionUtil;
//import com.ktdsuniversity.edu.pms.utils.Validator;
//import com.ktdsuniversity.edu.pms.utils.Validator.Type;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class LoginController {
//
//	private Logger logger = LoggerFactory.getLogger(ApprovalServiceImpl.class);
//
//	@Autowired
//	private LoginLogService loginLogService;
//
//	@Autowired
//	private CommuteService commuteService;
//	
//	@GetMapping("/employee/login")
//	public String viewLoginPage() {
//		return "login/login";
//	}
//
//	@ResponseBody
//	@PostMapping("/ajax/employee/login")
//	public AjaxResponse doLogin(HttpSession session, EmployeeVO employeeVO,
//			@RequestParam(defaultValue = "/") String nextUrl, Model model) {
//
//		Validator<EmployeeVO> validator = new Validator<>(employeeVO);
//
//		validator.add("empId", Type.NOT_EMPTY, "사원번호를 입력해 주세요.").add("empId", Type.EMPID, "사원번호 형식으로 입력해 주세요.")
//				.add("pwd", Type.NOT_EMPTY, "비밀번호를 입력해주세요.").start();
//
//		if (validator.hasErrors()) {
//			Map<String, List<String>> errors = validator.getErrors();
//			return new AjaxResponse().append("errors", errors);
//		}
//
//		EmployeeVO employee = this.loginLogService.getOneEmployeeByEmpIdAndPwd(employeeVO);
//
//		// 퇴사, 휴직 상태에 따른 로그인 제한 기능
//		String loginCheck = employee.getWorkSts();
//
//		if (loginCheck.equals("202")) {
//			return new AjaxResponse().append("errorRestDt","휴직 중인 사원입니다.휴직기간 : " + employee.getRestStDt() + " ~ " + employee.getRestEndDt());
//		} else if (loginCheck.equals("204")) {
//			return new AjaxResponse().append("errorEndDt", "퇴사한 사원입니다.퇴사일 : " + employee.getEndDt());
//		}
//
//		// 사용중 여부 확인
//		if (!SessionUtil.wasLoginEmployee(employee.getEmpId())) {
//
//
//			// 로그인 기록 DB 저장 메서드
//			boolean insertLoginLogSuccess = this.loginLogService.insertLoginLog(employee);
//			if (insertLoginLogSuccess) {
//				logger.debug("로그인 기록 저장을 성공했습니다.");
//			} else {
//				logger.debug("로그인 기록 저장에 실패했습니다.");
//			}
//			// LOGIN_LOG 테이블의 LOG_ID 값을 포함한 employeeVO 객체를 재대입한다.
////            employee = this.loginLogService.updateEmpLog(employee);
//
//			// 로그인 성공시 LGNYN을 Y로 변경
//			boolean updateLGNY = this.loginLogService.updateOneEmpIdUseOtherPlace(employee);
//			if (updateLGNY) {
//				logger.debug("로그인 상태를 'Y'로 변경했습니다.");
//			} else {
//				logger.debug("로그인 상태 변경('Y')에 실패했습니다.");
//			}
//
//
//			CommuteVO todayCommuteVO = this.loginLogService.getCommuteDt(employee.getEmpId());
//			if (todayCommuteVO == null) {
//				logger.debug("오늘 출근 기록이 없습니다. 출근을 기록하겠습니다.");
//				boolean insertCommuteSuccess = this.loginLogService.insertCommuteIn(employee);
//				if (insertCommuteSuccess) {
//					logger.debug("출근 기록에 성공했습니다.");
//					todayCommuteVO = this.loginLogService.getCommuteDt(employee.getEmpId());
//				} else {
//					logger.debug("출근 기록에 실패했습니다.");
//				}
//
//
//			} else {
//				logger.debug("이미 출근을 기록했습니다.");
//			}
//			employee.setCommuteVO(todayCommuteVO);
//			// session.setAttribute("_LOGIN_USER_COMMUTE", todayCommuteVO);
//
//
////            model.addAttribute("todayCommuteVO", todayCommuteVO.getCmmtTime());
//
//			session.setAttribute("_LOGIN_USER_", employee);
//			session.setMaxInactiveInterval(20 * 60);
//			SessionUtil.addSession(employee.getEmpId(), session);
//
////            System.out.println(employee.getCommuteVO().getCmmtTime());
//			System.out.println(todayCommuteVO.getCmmtTime());
//			return new AjaxResponse().append("next", nextUrl);
//		}
//		// 아닐경우 오류 발생
//		else {
//			return new AjaxResponse().append("errorUseNow", "이미 로그인중인 사원번호입니다.");
//		}
//	}
//
//	@GetMapping("/employee/logout")
//	public String doLogout(HttpSession session, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
//
//
//		boolean oneEmpIdNotUseSuccess = this.loginLogService.updateOneEmpIdNotUseNow(employeeVO);
//		if (oneEmpIdNotUseSuccess) {
//			logger.debug("로그인 상태 변경에 성공했습니다.(로그아웃 버튼)");
//		} else {
//			logger.debug("로그인 상태 변경에 실패했습니다.(로그아웃 버튼)");
//		}
//
//		boolean updateEmpLogoutSuccess = this.loginLogService.updateEmpLogout(employeeVO.getLoginLogVO().getLogId());
//		if (updateEmpLogoutSuccess) {
//			logger.debug("로그아웃 기록에 성공했습니다.(로그아웃 버튼)");
//		} else {
//			logger.debug("로그아웃 기록에 실패했습니다.(로그아웃 버튼)");
//		}
//		SessionUtil.removeSession(employeeVO.getEmpId());
//
//		return "redirect:/employee/login";
//	}
//
//	// 출퇴근을 보여주는 페이지
//	@GetMapping("/commute/view")
//	public String doCommuteSearch(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, CommuteVO commuteVO,
//			Model model) {
//		/**
//		 * 현재 로그인한 사원의 CDMN_CODE가 301인지 조회 true: 전체 조회 false: 입력받은 본인의 출퇴근만 조회
//		 */
//		String AdmnCodeIsSystemFormat = "301";
//		if (employeeVO.getAdmnCode().equals(AdmnCodeIsSystemFormat)) {
//			CommuteListVO commuteListVO = commuteService.getAllCommuteData(commuteVO);
//			model.addAttribute("commuteList", commuteListVO);
//			model.addAttribute("commuteVO", commuteVO);
//			return "commute/view";
//		} else {
//			commuteVO.setEmpId(employeeVO.getEmpId());
//			CommuteListVO commuteListVO = commuteService.getAllCommuteDataByEmpId(commuteVO);
//			model.addAttribute("commuteList", commuteListVO);
//			return "commute/view";
//		}
//	}
//
//	@GetMapping("/commute/fnsh")
//	public String doCommuteFinish(@SessionAttribute("_LOGIN_USER_") EmployeeVO employee) {
//		CommuteVO fnshTime = this.loginLogService.getCommuteDt(employee.getEmpId());
//		if (fnshTime.getFnshTime() == null) {
//			boolean updateCommuteFnshSuccess = this.loginLogService.updateCommuteFnsh(employee);
//			if (updateCommuteFnshSuccess) {
//				fnshTime = this.loginLogService.getCommuteDt(employee.getEmpId());
//				logger.debug("퇴근 기록에 성공했습니다.");
//			} else {
//				logger.debug("퇴근 기록에 실패했습니다.");
//			}
//		}
//		employee.setCommuteVO(fnshTime);
//		// fnshTime를 지정해서 set해줘야 할까?
//		// 이따가 디버깅해서 확인해보자
//		return "redirect:/";
//	}
//
//	// 로그인 기록 확인 페이지
//	@GetMapping("/loginlog/view")
//	public String viewLoginLog(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, Model model,
//			LoginLogVO loginLogVO) {
//		String AdmnCodeIsSystemFormat = "301";
//		if (employeeVO.getAdmnCode().equals(AdmnCodeIsSystemFormat)) {
//			LoginLogListVO loginLogListVO = this.loginLogService.getAllLoginLog(loginLogVO);
//			model.addAttribute("loginLogList", loginLogListVO);
//			model.addAttribute("loginLogVO", loginLogVO);
//			return "login/loginlogview";
//		} else {
//			loginLogVO.setEmpId(employeeVO.getEmpId());
//			LoginLogListVO loginLogListVO = this.loginLogService.getOneLoginLog(loginLogVO);
//			model.addAttribute("loginLogList", loginLogListVO);
//			return "login/loginlogview";
//		}
//	}
//
//	// 화면 접근 기록 확인 페이지
//	@GetMapping("/visitedlog/view")
//	public String viewVisitedLog(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, Model model,
//			VisitedVO visitedVO) {
//		String AdmnCodeIsSystemFormat = "301";
//		if (employeeVO.getAdmnCode().equals(AdmnCodeIsSystemFormat)) {
//			VisitedListVO visitedList = this.loginLogService.getAllVisitedLog(visitedVO);
//			model.addAttribute("visitedVO", visitedVO);
//			model.addAttribute("visitedList", visitedList);
//			return "login/visitedlogview";
//		} else {
//			visitedVO.setEmpId(employeeVO.getEmpId());
//			VisitedListVO visitedList = this.loginLogService.getOneVisitedLog(visitedVO);
//			model.addAttribute("visitedList", visitedList);
//			return "login/visitedlogview";
//		}
//	}
//
//	@ResponseBody
//    @PostMapping("/ajax/login/pwdCnDt")
//    public AjaxResponse updatePwdCnDt(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
//
//        boolean updatePwdCnDtSuccess = this.loginLogService.updatePwdDtThirtyDay(employeeVO.getEmpId());
//        if (updatePwdCnDtSuccess) {
//            logger.debug("비밀번호 변경일 변경(SYSDATE - 60) 성공했습니다.");
//        }
//
//        return new AjaxResponse().append("next", "/");
//    }
//}
