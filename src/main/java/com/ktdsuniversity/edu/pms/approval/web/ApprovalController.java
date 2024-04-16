package com.ktdsuniversity.edu.pms.approval.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.approval.service.ApprovalDetailService;
import com.ktdsuniversity.edu.pms.approval.service.ApprovalService;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

@Controller
public class ApprovalController {
	
	private Logger logger = LoggerFactory.getLogger(ApprovalController.class);
	
	@Autowired
	private ApprovalService approvalService;
	
	@Autowired
	private ApprovalDetailService approvaldetailservice;

	
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
	
	@GetMapping("/approval/approvalhome")
	public String doApprovalHome(Model model) {
		ApprovalListVO apprList = this.approvalService.getAllApproval();		
		model.addAttribute("apprList", apprList);
				
		return "approval/approvalhome";
	}
	
	@GetMapping("/approval/view")
	public String doApprovalView(@RequestParam String apprid, Model model) {
		System.out.println("apprid;"+apprid);
		
		ApprovalVO approvalvo=this.approvalService.selectOneApproval(apprid);	
		model.addAttribute("approvalvo", approvalvo);
		
		ApprovalDetailListVO approvalDetailList= this.approvaldetailservice.getAllApprovalDetail();
		model.addAttribute("approvalDetailList", approvalDetailList);
		model.addAttribute("apprid",apprid);
		
		return "approval/approvalView";
	}
	
	@GetMapping("/approval/delete/{id}")
	public String doApprovalDelete(@PathVariable String apprid) {
		System.out.println("apprid:"+apprid);
		
		boolean isDeleteSucess =this.approvalService.deleteOneApproval(apprid);
		
		if(isDeleteSucess) {
			System.out.println("삭제 성공");
		}
		else {
			System.out.println("삭제 실패");
		}
		
		return "redirect:/approval/approvalhome";
	}
}
