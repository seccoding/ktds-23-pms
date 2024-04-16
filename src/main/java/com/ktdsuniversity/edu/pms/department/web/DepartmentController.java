package com.ktdsuniversity.edu.pms.department.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentListVO;

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

}
