package com.ktdsuniversity.edu.pms.changehistory.web;

import java.util.List;

import com.ktdsuniversity.edu.pms.changehistory.vo.JobHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.changehistory.service.ChangeHistoryService;
import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.PositionHistoryVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class ChangeHistoryController {
	
	@Autowired
	private ChangeHistoryService changeHistoryService;
	
	@ResponseBody
	@GetMapping("/ajax/change/department")
	public AjaxResponse getDeptChangeHistByEmptId(@RequestParam String empId) {
		List<DepartmentHistoryVO> departmentHistoryVO = this.changeHistoryService.getUserDeptHisory(empId);
		return new AjaxResponse().append("departmentHistoryList", departmentHistoryVO);
	}
	
	@ResponseBody
	@PostMapping("/ajax/change/position")
	public AjaxResponse changePosition(EmployeeVO employeeVO) {
		
		boolean isSuccess = this.changeHistoryService.changePosition(employeeVO);
		return new AjaxResponse().append("isSuccess", isSuccess).append("next", "/employee/view?empId="+employeeVO.getEmpId());
	}

	@ResponseBody
	@PostMapping("/ajax/change/job")
	public AjaxResponse changeJob(EmployeeVO employeeVO) {
		boolean isSuccess = this.changeHistoryService.changeJob(employeeVO);
		return new AjaxResponse().append("isSuccess", isSuccess).append("next", "/employee/view?empId="+employeeVO.getEmpId());
	}



}
