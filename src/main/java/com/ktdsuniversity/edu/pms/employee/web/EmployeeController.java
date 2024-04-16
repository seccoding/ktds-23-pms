package com.ktdsuniversity.edu.pms.employee.web;

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
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private FileHandler fileHandler;
	
//	@GetMapping("/employee/search")
//	public String viewEmployeeListPage(Model model, SearchEmployeeVO searchEmployeeVO) {
//		EmployeeListVO employeeListVO = this.employeeService.searchAllEmployee(searchEmployeeVO);
//		model.addAttribute("employeeList", employeeListVO);
//		model.addAttribute("searchEmployeeVO", searchEmployeeVO);
//		return "employee/employeelist"; //employeelist JSP에 보낸다.
//	}
	
	@GetMapping("/employee/search")
	public String viewEmployeeListPage(Model model) {
		EmployeeListVO employeeListVO = this.employeeService.getAllEmployee();
		
		model.addAttribute("employeeList", employeeListVO);
		
		return "employee/employeelist";
	}
	
	@GetMapping("/employee/search/{employeeId}")
	public String viewOneEmployeePage(@RequestParam String empId, Model model) {
		EmployeeVO employeeVO = this.employeeService.getOneEmployee(empId);
		model.addAttribute("employeeVO", employeeVO);
		return "employee/employeeview";
	}
	
//	@GetMapping("/employee/regist") 
//	public String viewRegistEmployeePage() {
//		return "employee/employeeregist";
//	}
//	
	
//	@PostMapping("/employee/regist")
//	public String createNewEmployee(@SessionAttribute("_EMPLOYEE") EmployeeVO employeeVO, 
//									Model model,@RequestParam MultipartFile picture){
//			
//			Validator<EmployeeVO> validator = new Validator<>(employeeVO);
//			validator.add("empName", Type.NOT_EMPTY, "이름을 입력해주세요.")
//					 .add("empId", Type.NOT_EMPTY, "사원번호를 입력해주세요.")
//					 .add("jobId", Type.NOT_EMPTY, "직무를 입력해주세요.")
//					 .add("deptId", Type.NOT_EMPTY, "부서를 입력해주세요.")
//					 .add("pstnId", Type.NOT_EMPTY, "직급을 입력해주세요.")
//					 .add("workSts", Type.NOT_EMPTY, "재직상태를 입력해주세요.")
//					 .add("salYear", Type.NOT_EMPTY, "호봉을 입력해주세요.")
//					 .add("hireDt", Type.NOT_EMPTY, "입사일을 설정해주세요.")
//					 .add("hireYear", Type.NOT_EMPTY, "연차를 설정해주세요.")
//					 .add("addr", Type.NOT_EMPTY, "주소를 입력해주세요.")
//					 .add("brth", Type.NOT_EMPTY, "생일을 입력해주세요.")
//					 .add("email", Type.NOT_EMPTY, "이메일을 입력해주세요.")
//					 .add("email", Type.EMAIL, "이메일 형식으로 입력해주세요.")
//					 .add("pwd", Type.NOT_EMPTY, "비밀번호를 입력해주세요.")
//					 .add("mngrYn", Type.NOT_EMPTY, "임원여부를 설정해주세요.").start();	
//			
//			boolean isSuccess = this.employeeService.createNewEmployee(employeeVO, picture);
//			if(isSuccess) {
//				return "redirect:/employee/regist";
//		}
//			model.addAttribute("EmployeeVO", employeeVO);
//			return "employee/employeesearch";
//	}
	
	@ResponseBody
	@GetMapping("ajax/employee/delete")
	public AjaxResponse deleteEmp(HttpSession session, @SessionAttribute("_EMPLOYEE_") 
									EmployeeVO employeeVO) {
		boolean isSuccess = this.employeeService.deleteEmployee(employeeVO.getEmpId());
		if(isSuccess) {
			session.invalidate();
		}
		return new AjaxResponse().append("next", isSuccess ? "/employee/success-delete-emp"
											: "/employee/failed-delete-emp");
	}

	@GetMapping("/employee/modify/{empId}")
	public String viewEmpModifyPage(@PathVariable String empId, Model model, 
									@SessionAttribute("_Employee_") EmployeeVO employeeVO) {
		EmployeeVO employee = this.employeeService.getOneEmployee(empId);
		model.addAttribute("employeeVO", employee);
		return "employee/employeemodify";
	}
		
	
	@PostMapping("/employee/modify/{empId}")
	public AjaxResponse modifyEmp(@PathVariable String empId, Model model,
			@SessionAttribute("_EMPLOYEE_") EmployeeVO employeeVO,
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
			 return new AjaxResponse().append("result", isUpdateSuccess);
			
		}
	
									
}
	
	


