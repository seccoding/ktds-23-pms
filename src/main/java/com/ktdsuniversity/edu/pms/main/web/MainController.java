package com.ktdsuniversity.edu.pms.main.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.department.service.DepartmentService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.service.LoginLogService;
import com.ktdsuniversity.edu.pms.memo.service.MemoService;
import com.ktdsuniversity.edu.pms.memo.vo.MemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;

@Controller
public class MainController {

	@Autowired
	private LoginLogService loginLogService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired MemoService memoService;

	@GetMapping("/")
	public String viewMainLayoutPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, Model model) {
		if (this.loginLogService.getPwdCndt(employeeVO.getEmpId()) > 0) {
			model.addAttribute("pwdMessage", "비밀번호를 변경한지 90일지 지났습니다. 비밀번호를 변경해주세요.");
		}
		return "layout/main_layout";
	}
	
	@GetMapping("/main/dashboard") 
	public String viewMainDashboardPage(@SessionAttribute("_LOGIN_USER_")EmployeeVO employeeVO,SearchMemoVO searchMemoVO, Model model) {
		String deptName = this.departmentService.getDepartmentNameById((employeeVO.getDeptId()));
		model.addAttribute("deptname", deptName);
		
		searchMemoVO.setEmpId(employeeVO.getEmpId());
		MemoListVO memoListVO =this.memoService.getReceiveMemoAllsearch(searchMemoVO);
		
		model.addAttribute("memoList", memoListVO );
		model.addAttribute("searchMemoVO", searchMemoVO);
		
		return "main/dashboard";
	}
	
}
