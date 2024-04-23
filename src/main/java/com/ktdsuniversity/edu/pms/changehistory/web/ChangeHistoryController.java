package com.ktdsuniversity.edu.pms.changehistory.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.changehistory.service.ChangeHistoryService;
import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
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

}
