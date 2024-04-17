package com.ktdsuniversity.edu.pms.department.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.StringUtil;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/department/search")
	public String viewDepartmentListPage(Model model) {
		DepartmentListVO departmentListVO = this.departmentService.getAllDepartment();
		
		model.addAttribute("departmentList", departmentListVO);
		
		return "department/departmentlist";
	}
	@GetMapping("/department/create")
	public String viewCreateNewDepartmentPage() {
		return "department/departmentcreate";
	}
	

	@ResponseBody
	@PostMapping("/ajax/department/create")
	public AjaxResponse doCreateNewDepartment(DepartmentVO departmentVO, Model model) {
		boolean isEmptyName = StringUtil.isEmpty(departmentVO.getDeptName());
		boolean isEmptyLeaderId = StringUtil.isEmpty(departmentVO.getDeptLeadId());
		
		System.out.println(departmentVO.getDeptName() + departmentVO.getDeptLeadId()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
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

}
