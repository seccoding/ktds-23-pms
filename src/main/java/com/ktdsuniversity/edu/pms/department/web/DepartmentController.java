package com.ktdsuniversity.edu.pms.department.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
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
	

	@PostMapping("/department/create")
	public String doCreateNewDepartment(DepartmentVO departmentVO, Model model) {
		boolean isEmptyName = StringUtil.isEmpty(departmentVO.getDeptName());
		boolean isEmptyLeaderId = StringUtil.isEmpty(departmentVO.getDeptLeadId());
		
		if (isEmptyName) {
			model.addAttribute("errorMessage", "부서 이름은 필수 입력 값입니다.");
			model.addAttribute("departmentVO", departmentVO);
			return "/department/departmentcreate";
		}
		if (isEmptyLeaderId) {
			model.addAttribute("errorMessage", "부서장 이름은 필수 입력 값입니다.");
			model.addAttribute("departmentVO", departmentVO);
			return "/department/departmentcreate";
		}
		
		return "redirect:/department/search";
	}

}
