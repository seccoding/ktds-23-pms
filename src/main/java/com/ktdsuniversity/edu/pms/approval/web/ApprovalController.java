package com.ktdsuniversity.edu.pms.approval.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.approval.service.ApprovalService;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

@Controller
public class ApprovalController {
	
	private Logger logger = LoggerFactory.getLogger(ApprovalController.class);
	
	@Autowired
	private ApprovalService approvalService;
	
	@GetMapping("/approval/search")
	public String viewApprovalListPage(Model model, @SessionAttribute("") EmployeeVO employeeVO) {
		ApprovalListVO approvalListVO = null;
		// 경지부장 로그인(하드코딩 수정)
		if(employeeVO.getDeptId() == "DEPT_230101_000010" && employeeVO.getAdmnCode().equals("301")) {
			approvalListVO = this.approvalService.searchAllApproval();
			model.addAttribute(approvalListVO);
			return "approval/admin/approvallist";
		}
		// 사용자 로그인
//		approvalListVO = this.approvalService.searchAllApprovalByEmpId(employeeVO.getEmpId());
		model.addAttribute(approvalListVO);
		return "approval/approvallist";
	}
}
