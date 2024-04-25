package com.ktdsuniversity.edu.pms.employee.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.changehistory.service.ChangeHistoryService;
import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.JobHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.PositionHistoryVO;
import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;
import com.ktdsuniversity.edu.pms.team.service.TeamService;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;


@Controller
public class EmployeeController {

	private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private ChangeHistoryService changeHistoryService;

	@Autowired
	private FileHandler fileHandler;

//	@GetMapping("/employee/search")
//	public String viewEmployeeListPage(Model model, SearchEmployeeVO searchEmployeeVO) {
//	EmployeeListVO employeeListVO = this.employeeService.searchAllEmployee(searchEmployeeVO);
//	model.addAttribute("employeeList", employeeListVO);
//	model.addAttribute("searchEmployeeVO", searchEmployeeVO);
//	return "employee/employeelist";
//	}
	
//	//조회
//	@GetMapping("/employee/search")
//	public String viewEmployeeListPage(Model model) {
//		EmployeeListVO employeeListVO = this.employeeService.getAllEmployee();
//		
//		model.addAttribute("employeeList", employeeListVO);
//
//		
//		return "employee/employeelist";
//	}
	
	//검색결과 
	@GetMapping("/employee/search")
	public String viewEmployeePage(SearchEmployeeVO searchEmployeeVO, Model model) {
		EmployeeListVO employeeListVO = this.employeeService.searchAllEmployee(searchEmployeeVO);
		model.addAttribute("employeeList", employeeListVO);
		model.addAttribute("searchEmployeeVO", searchEmployeeVO);
		return "employee/employeelist";
	}
	
	//상제정보 
	@GetMapping("/employee/view")
	public String viewEmployeeDetail(@RequestParam String empId, Model model) {
		
		EmployeeVO employeeVO = this.employeeService.getOneEmployee(empId);
		List<DepartmentHistoryVO> departmentHistList = this.changeHistoryService.getUserDeptHisory(empId);
		List<JobHistoryVO> jobHistList = this.changeHistoryService.getUserJobHistory(empId);
		List<PositionHistoryVO> positionHistList = this.changeHistoryService.getUserPositionHistory(empId);
		model.addAttribute("employeeVO", employeeVO);
		model.addAttribute("departmentHistList", departmentHistList);
		model.addAttribute("jobHistList", jobHistList);
		model.addAttribute("positionHistList", positionHistList);
		return "employee/employeeview";
	}
	
	//팀 삭제
	@ResponseBody
	@GetMapping("/ajax/employee/delete/team")
	public AjaxResponse deleteTeam(EmployeeVO employeeVO) {
		boolean isSuccessDelete = this.employeeService.deleteTeam(employeeVO);
		return new AjaxResponse().append("result", isSuccessDelete).append("next", "/employee/modify/"+employeeVO.getEmpId());
	}
	
	
	//삭제 
	@ResponseBody
	@GetMapping("ajax/employee/delete")
	public AjaxResponse deleteEmp(EmployeeVO employeeVO) {
		boolean isSuccess = this.employeeService.deleteEmployee(employeeVO.getEmpId());
//		if(isSuccess) {
//			session.invalidate();
//		}
		return new AjaxResponse().append("next", isSuccess ? "/employee/success-delete-emp"
											: "/employee/failed-delete-emp");
	}
	
	

	//수정페이지
	@GetMapping("/employee/modify/{empId}")
	public String viewEmpModifyPage(@PathVariable String empId, Model model, 
									 EmployeeVO employeeVO) {
		EmployeeVO employee = this.employeeService.getOneEmployee(empId);
		model.addAttribute("employeeVO", employee);

		
		DepartmentListVO departmentList = this.departmentService.getAllDepartment();
		 model.addAttribute("departmentlist", departmentList);
		
//		TeamListVO teamList = this.teamService.getAllTeamList(employee.getDeptId());
//		model.addAttribute("teamListinDept", teamList.getTeamList());
		
		return "employee/employeemodify";
	}
	
	@ResponseBody
	@GetMapping("/ajax/employee/modify")
	public AjaxResponse getEmployeeInput(@RequestParam String empId,  String deptId) {
		EmployeeVO employee = this.employeeService.getOneEmployee(empId);
		TeamListVO teamList = this.teamService.getAllTeamList(deptId);
		
		return new AjaxResponse().append("employeeDept", employee.getDeptId()).append("teamList", teamList.getTeamList()).append("empTeamList", employee.getTeamList());
	}
	
	@ResponseBody
	@PostMapping("/ajax/employee/modify")
	public AjaxResponse modifyEmployee(EmployeeVO employeeVO) {
		
		boolean isSuccess = this.employeeService.modifyOneEmployee(employeeVO);
		return new AjaxResponse().append("isSuccess", isSuccess).append("next", "/employee/view?empId="+employeeVO.getEmpId());
	}
		
	//수정
	@PostMapping("/employee/modify/{empId}")
	public String modifyEmp(@PathVariable String empId, Model model,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			@RequestParam MultipartFile picture) {
			Validator<EmployeeVO> validator = new Validator<>(employeeVO);
			validator.add("empName", Type.NOT_EMPTY, "이름을 입력해주세요.")
			 .add("empId", Type.NOT_EMPTY, "사원번호를 입력해주세요.")
			 .add("jobId", Type.NOT_EMPTY, "직무를 입력해주세요.")
			 .add("deptId", Type.NOT_EMPTY, "부서를 입력해주세요.")
			 .add("pstnId", Type.NOT_EMPTY, "직급을 입력해주세요.")
			 .add("workSts", Type.NOT_EMPTY, "재직상태를 입력해주세요.")
			 .add("salYear", Type.NOT_EMPTY, "호봉을 입력해주세요.")
			 .add("hireDt", Type.NOT_EMPTY, "입사일을 설정해주세요.")
			 .add("hireYear", Type.NOT_EMPTY, "연차를 설정해주세요.")
			 .add("addr", Type.NOT_EMPTY, "주소를 입력해주세요.")
			 .add("brth", Type.NOT_EMPTY, "생일을 입력해주세요.")
			 .add("email", Type.NOT_EMPTY, "이메일을 입력해주세요.")
			 .add("email", Type.EMAIL, "이메일 형식으로 입력해주세요.")
			 .add("pwd", Type.NOT_EMPTY, "비밀번호를 입력해주세요.")

			 .add("mngrYn", Type.NOT_EMPTY, "임원여부를 설정해주세요.").start();	
			 
			 employeeVO.setEmpId(empId);
			 boolean isUpdateSuccess = this.employeeService.modifyEmployee(employeeVO);
			 if(isUpdateSuccess) {
				 logger.info("등록 성공");
			 }
			 else {
				 logger.info("등록 실패");
			 }
			 model.addAttribute("employeeVO", employeeVO);
			 
			 System.out.println(employeeVO.getDepartmentVO().getDeptName());
			 System.out.println(employeeVO.getEmail());
			 
			 
			 return "redirect:/employee/view?empId=" + empId;
			
		}
	


	/**
	 * 회원가입 페이지
	 */
	@GetMapping("/employee/regist")
	public String viewRegistPage() {
		return "employee/regist";
	}

	@ResponseBody
	@PostMapping("/ajax/employee/regist")
	public AjaxResponse doRegist(EmployeeVO employeeVO, @RequestParam(defaultValue = "/employee/search") String nextUrl, 
			@RequestParam String empId, @RequestParam(required = false) MultipartFile file) {
		/**
		 * 사원번호가 있는지 확인하고
		 * 1: 존재하는 사원번호, 0: 없는 사원번호
		 * 1이라면 "이미 사용중인 사원번호입니다."오류 발생
		 */
		int isEmpIdUseCount = this.employeeService.getOneEmpIdIsExist(empId);
		if(isEmpIdUseCount == 1) {
			return new AjaxResponse().append("errorMessage", "이미 사용중인 사원번호 입니다.");
		}
		
		Validator<EmployeeVO> validator = new Validator<>(employeeVO);
		
		validator.add("empId", Type.NOT_EMPTY, "사원번호를 입력해 주세요.")
				.add("empId", Type.EMPID, "사원번호 형식으로 입력해 주세요")
				.add("pwd", Type.NOT_EMPTY, "비밀번호를 입력해 주세요")
				.add("pwd", Type.PASSWORD, "비밀번호 형식으로 입력해 주세요")
				.add("empName", Type.NOT_EMPTY, "사원이름을 입력해 주세요")
				.add("hireDt", Type.NOT_EMPTY, "입사일을 지정해 주세요")
				.add("addr", Type.NOT_EMPTY, "주소를 입력해 주세요")
				.add("brth", Type.NOT_EMPTY, "생일을 지정해 주세요")
				.add("email", Type.NOT_EMPTY, "이메일을 입력해 주세요")
				.add("email", Type.EMAIL, "이메일 형식으로 입력해 주세요")
				.add("deptId", Type.NOT_EMPTY, "부서ID를 입력해 주세요")
				.add("deptId", Type.DEPTID, "부서ID형식으로 입력해 주세요")
				.add("jobId", Type.NOT_EMPTY, "직무ID를 입력해 주세요")
				.add("jobId", Type.JOBID, "직무ID형식으로 입력해주세요")
				.add("pstnId", Type.NOT_EMPTY, "직급ID를 입력해 주세요")
				.add("pstnId", Type.PSTNID, "직급ID형식으로 입력해 주세요").start();
		
		if (validator.hasErrors()) {
			Map<String, List<String>> errors = validator.getErrors();
			return new AjaxResponse().append("errors", errors);
		}
		
		boolean createEmpSuccess = this.employeeService.createEmployee(employeeVO, file);
		
		// 사원 회원가입에 성공했다면
		if (createEmpSuccess) {
			// 임시로 메인페이지로 이동
			return new AjaxResponse().append("next", nextUrl);
		}
		return new AjaxResponse().append("errorMessage", "실패사유");
	}


}
