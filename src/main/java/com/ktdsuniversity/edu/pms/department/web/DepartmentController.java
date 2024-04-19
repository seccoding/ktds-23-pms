package com.ktdsuniversity.edu.pms.department.web;

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
	
	@GetMapping("/department/search")
	public String viewDepartmentListPage(Model model) {
		DepartmentListVO departmentListVO = this.departmentService.getAllDepartment();
		DepartmentListVO getOnlyDepartmentListVO = this.departmentService.getOnlyDepartment();
		TeamListVO getOnlyTeamListVO = this.teamService.getOnlyTeam();

		
		
		model.addAttribute("departmentList", departmentListVO);
		model.addAttribute("onlyDepartmentList", getOnlyDepartmentListVO);
		model.addAttribute("onlyTeamList", getOnlyTeamListVO);
		
		return "department/departmentlist";
	}
	@GetMapping("/department/create")
	public String viewCreateNewDepartmentPage() {
		return "department/departmentcreate";
	}
	

	@ResponseBody
	@GetMapping("/ajax/department/search/{deptId}")
	public AjaxResponse getDepartmentBydeptId(@PathVariable String deptId) {
		TeamListVO teamListVO = this.teamService.getAllTeamList(deptId);
		return new AjaxResponse().append("teamList", teamListVO);
	}
	
	@ResponseBody
	@PostMapping("/ajax/department/create")
	public AjaxResponse doCreateNewDepartment(DepartmentVO departmentVO, Model model) {
		boolean isEmptyName = StringUtil.isEmpty(departmentVO.getDeptName());
		boolean isEmptyLeaderId = StringUtil.isEmpty(departmentVO.getDeptLeadId());
		
		
		
		if (isEmptyName) {
			model.addAttribute("errorMessage", "부서 이름은 필수 입력 값입니다.");
			model.addAttribute("departmentVO", departmentVO);
			return new AjaxResponse().append("errormessage", model);
		}
		if (isEmptyLeaderId) {
			model.addAttribute("errorMessage", "부서장 아이디는 필수 입력 값입니다.");
			model.addAttribute("departmentVO", departmentVO);
			return new AjaxResponse().append("errormessage", model);
		}
		
		boolean isSuccess = this.departmentService.createNewDepartment(departmentVO);
		return new AjaxResponse().append("result", isSuccess).append("nextUrl", "/department/search");
	}
	
	@ResponseBody
	@GetMapping("/ajax/department/show")
	public AjaxResponse selectOptionShowDepartment(@RequestParam String departmentId) {
		DepartmentVO departmentVO = this.departmentService.selectOneDepartment(departmentId);
		return new AjaxResponse().append("oneDepartment", departmentVO);
	}
	@ResponseBody
	@PostMapping("/ajax/department/modify")
	public AjaxResponse modifyOneDepartment(DepartmentVO departmentVO) {
		boolean isModifySuccess = this.departmentService.modifyOneDepartment(departmentVO);
		System.out.println(departmentVO.getDeptId());
		System.out.println(departmentVO.getDeptName());
		System.out.println(departmentVO.getDeptLeadId());
		return new AjaxResponse().append("success", isModifySuccess).append("next", "/department/search");
	}

	
}
