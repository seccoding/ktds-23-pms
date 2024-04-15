package com.ktdsuniversity.edu.pms.employee.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeListVO;
import com.ktdsuniversity.edu.pms.employee.vo.SearchEmployeeVO;
import com.ktdsuniversity.edu.pms.beans.FileHandler;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private FileHandler fileHandler;
	
	@GetMapping("/employee/search")
	public String viewEmployeeListPage(Model model, SearchEmployeeVO searchEmployeeVO) {
		EmployeeListVO employeeListVO = this.employeeService.searchAllEmployee(searchEmployeeVO);
		model.addAttribute("employeeList", employeeListVO);
		model.addAttribute("searchEmployeeVO", searchEmployeeVO);
		return "employee/employeelist"; //employeelist JSP에 보낸다.
	}
	
	
	

}
