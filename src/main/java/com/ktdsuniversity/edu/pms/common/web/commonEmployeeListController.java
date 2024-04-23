package com.ktdsuniversity.edu.pms.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class commonEmployeeListController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@ResponseBody
	@GetMapping("/ajax/employeelist/search")
	public AjaxResponse viewEmployeeList(SearchEmployeeVO searchEmployeeVO) {
		EmployeeListVO employeeListVO = this.employeeService.searchAllEmployee(searchEmployeeVO);
	
		return new AjaxResponse().append("result", employeeListVO);
	}
	
	@GetMapping("/employeelist/search")
	public String viewEmployeePage(SearchEmployeeVO searchEmployeeVO, Model model) {
		EmployeeListVO employeeListVO = this.employeeService.searchAllEmployee(searchEmployeeVO);
		model.addAttribute("employeeList", employeeListVO);
		model.addAttribute("searchEmployeeVO", searchEmployeeVO);
		return "/commonemployeelist";
	}
	

}
