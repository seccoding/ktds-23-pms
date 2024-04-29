package com.ktdsuniversity.edu.pms.department.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.team.service.TeamService;
import com.ktdsuniversity.edu.pms.team.vo.TeamListVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.StringUtil;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/department/search")
	public String viewDepartmentListPage(Model model) {
		DepartmentListVO departmentListVO = this.departmentService.getAllDepartment();
		DepartmentListVO getOnlyDepartmentListVO = this.departmentService.getOnlyDepartment();
		TeamListVO getOnlyTeamListVO = this.teamService.getOnlyTeam();
		List<EmployeeVO> empList = this.employeeService.getCanBeDeptLead();


		
		
		model.addAttribute("departmentList", departmentListVO.getDepartmentList());
		model.addAttribute("onlyDepartmentList", getOnlyDepartmentListVO);
		model.addAttribute("onlyTeamList", getOnlyTeamListVO);
		model.addAttribute("empList", empList);
		return "department/departmentlist";
	}
	@GetMapping("/department/create")
	public String viewCreateNewDepartmentPage() {
		return "department/departmentcreate";
	}
	

	@ResponseBody
	@GetMapping("/ajax/department/search/{deptId}")
	public AjaxResponse getTeamByDeptId(@PathVariable String deptId) {

		
		TeamListVO teamListVO = this.teamService.getAllTeamList(deptId);
		
		return new AjaxResponse().append("teamList", teamListVO.getTeamList());
	}
	
//	@ResponseBody
//	@GetMapping("/ajax/department/search/{teamId}")
//	public AjaxResponse getEmployeeByTeamId(@PathVariable String teamId) {
//
//		
//		EmployeeListVO employeeList = this..getAllEmployee(teamId);
//		
//		return new AjaxResponse().append("teamList", teamListVO.getTeamList());
//	}
//	
	@ResponseBody
	@PostMapping("/ajax/department/create")
	public AjaxResponse doCreateNewDepartment(DepartmentVO departmentVO, Model model, @RequestParam("deptLeadId") String deptLeadId) {
		
		DepartmentListVO departmentListVo=this.departmentService.getDepartMent(departmentVO.getDeptLeadId());
		
		if(departmentListVo.getDepartmentList().size() > 0) {
			return new AjaxResponse().append("message", "이미 사용중인 아이디 입니다");
		}
		
		boolean isSuccess = this.departmentService.createNewDepartment(departmentVO);
		return new AjaxResponse().append("result", isSuccess).append("nextUrl", "/department/search");
	}
	
	
	
	@ResponseBody
	@GetMapping("/ajax/department/show")
	public AjaxResponse selectOptionShowDepartment(@RequestParam String departmentId) {
		DepartmentVO departmentVO = this.departmentService.selectOneDepartment(departmentId);
		List<EmployeeVO> empList = this.employeeService.getChangeToDeptLead(departmentId);
		return new AjaxResponse().append("oneDepartment", departmentVO).append("empList", empList);
	}
	@ResponseBody
	@PostMapping("/ajax/department/modify")
	public AjaxResponse modifyOneDepartment(DepartmentVO departmentVO) {
		this.departmentService.getEmpByDeptId(departmentVO.getDeptLeadId());
		
		
		DepartmentListVO departmentListVo=this.departmentService.getDepartMent(departmentVO.getDeptLeadId());	
		if(departmentListVo.getDepartmentList().size() > 0) {
			return new AjaxResponse().append("message", "이미 사용중인 아이디 입니다");
		}
		
		boolean isModifySuccess = this.departmentService.modifyOneDepartment(departmentVO);
		return new AjaxResponse().append("success", isModifySuccess).append("next", "/department/search");
	}

	@ResponseBody
	@GetMapping("/ajax/department/candelete/{deptId}")
	public AjaxResponse canDeleteOneDepartment(@PathVariable String deptId) {
		boolean isDeletePossible = this.departmentService.isPossibleDelete(deptId);
		return new AjaxResponse().append("possible", isDeletePossible).append("next", "/department/search");

	}
	@ResponseBody
	@GetMapping("/ajax/department/delete/{deptId}")
	public AjaxResponse deleteOneDepartment(@PathVariable String deptId) {
		boolean isSuccessDelete = this.departmentService.deleteOneDepartment(deptId);
		return new AjaxResponse().append("success", isSuccessDelete).append("next", "/department/search"); 
	}

	@ResponseBody
	@GetMapping("/ajax/department/search/findemployee/{teamId}")
	public AjaxResponse findEmployeesByTeamId(@PathVariable String teamId) {
	
		List<EmployeeVO> employeeList = this.employeeService.findEmployeesByTeamId(teamId);
		return new AjaxResponse().append("employeeList", employeeList);
		
	}
	
	@ResponseBody
	@GetMapping("/ajax/department/team/candelete/{teamId}")
	public AjaxResponse canDeleteOneTeam(@PathVariable String teamId) {
		boolean isDeletePossible = this.teamService.isPossibleDelete(teamId);
		return new AjaxResponse().append("possible", isDeletePossible);
				
	}
	
	@ResponseBody
	@GetMapping("/ajax/department/team/delete/{teamId}")
	public AjaxResponse deleteOneTeam(@PathVariable String teamId) {
		boolean isSuccessDelete = this.teamService.deleteOneTeam(teamId);
		return new AjaxResponse().append("success", isSuccessDelete);
	}
	
	@ResponseBody
	@PostMapping("/ajax/department/team/employee/add")
	public AjaxResponse addEmployeeListInTeam(EmployeeListVO employeeListVO) {
		String teamId = employeeListVO.getEmployeeList().get(0).getTeamVO().getTmId();
		List<EmployeeVO> empInTeam = this.employeeService.findEmployeesByTeamId(teamId);
		int successCnt = 0;
		int willAddCnt = 0;
		int isNotDeptPerson = 0;
		List<String> IdList = new ArrayList<>();
		for(EmployeeVO emp:empInTeam) {
			IdList.add(emp.getEmpId());
			
		}
		for(EmployeeVO employeeVO : employeeListVO.getEmployeeList()) {
			if(employeeVO.getDeptId().equals(this.employeeService.getDeptIdByEmployeeId(employeeVO.getEmpId()))) {
				if(!IdList.contains(employeeVO.getEmpId())) {
					willAddCnt++;
					if(this.employeeService.addTeam(employeeVO)) {
						successCnt++;
					};
					
				}
				
				
			}else {
				isNotDeptPerson++;
			}
			
		}
	
			boolean isSuccessMake = willAddCnt == successCnt;
			
			return new AjaxResponse().append("success", isSuccessMake).append("alreadyexist", willAddCnt==0).append("nopartof", isNotDeptPerson);
		
		
	}
	
}
















