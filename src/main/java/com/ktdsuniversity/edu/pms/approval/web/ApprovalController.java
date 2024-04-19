package com.ktdsuniversity.edu.pms.approval.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;


import com.ktdsuniversity.edu.pms.approval.service.ApprovalService;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.borrow.service.BorrowService;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class ApprovalController {

	private Logger logger = LoggerFactory.getLogger(ApprovalController.class);

	@Autowired
	private ApprovalService approvalService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private BorrowService borrowService;

	
	@GetMapping("/approval/approvalhome")
	public String doApprovalHomePage(Model model, ApprovalVO approvaVo) {
//		ApprovalListVO apprList = this.approvalService.getAllApproval();
//        model.addAttribute("apprList", apprList);
        
        ApprovalListVO apprList=this.approvalService.searchAllBoard(approvaVo);
        model.addAttribute("apprList", apprList);
        model.addAttribute("searchBoardVO", approvaVo);
        
        ApprovalListVO approveList=this.approvalService.getAllApprove();
        model.addAttribute("approveList", approveList);
        
        ApprovalListVO OneWeekApprovalList =this.approvalService.getAllOneWeekApproval();
        model.addAttribute("OneWeekApprovalList", OneWeekApprovalList);
        
        ApprovalListVO monthApprovalList=this.approvalService.getAllMonthApproval();
        model.addAttribute("monthApprovalList",monthApprovalList);
        
		return "approval/approvalhome";
	}

	@GetMapping("/approval/approvallist")
	public String doApprovalListByEmpIdPage(Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		ApprovalListVO apprList = this.approvalService.getAllApprovalByEmpId(employeeVO.getEmpId());
		model.addAttribute("apprList", apprList);
		return "approval/approvallist";
	}

	@GetMapping("/approval/approvalview")
	public String doApprovalViewPage(@RequestParam String apprId, Model model) {

		ApprovalVO approvalVO = this.approvalService.selectOneApprovalAll(apprId);
		model.addAttribute("approvalVO", approvalVO);

		if(approvalVO == null || approvalVO.getApprovalDetailVOList() == null) {
			throw new PageNotFoundException();
		}
		return "/approval/approvalview";
	}
	
	// 비품대여현황 jsp에서 전달받을 파라미터: @RequestParam String empId
	@GetMapping("/approval/approvalwrite")
	public String viewApprovalWritePage(Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		// test code
		EmployeeVO dmdEmployeeVO = employeeService.getOneEmployee(employeeVO.getEmpId());
		BorrowListVO borrowListVO = borrowService.getUserRentalState(dmdEmployeeVO);
		
//		if(dmdEmployeeVO == null || ! dmdEmployeeVO.getEmpId().equals(employeeVO.getEmpId())) {
//			throw new PageNotFoundException();
//		}
		
		if(borrowListVO == null) {
			throw new PageNotFoundException();
		}

		model.addAttribute("employee", dmdEmployeeVO);
		model.addAttribute("borrowList", borrowListVO);
		return "/approval/approvalwrite";
	}

	@ResponseBody
	@PostMapping("/ajax/approval/statuschange/{apprId}")
	public AjaxResponse doApprovalStatusChange(@PathVariable String apprId, @RequestParam String apprSts,
											   @SessionAttribute("_LOGIN_USER_")EmployeeVO employeeVO) {

		ApprovalVO approvalVO = this.approvalService.selectOneApproval(apprId);
		if(! employeeVO.getEmpId().equals(approvalVO.getDmdId()) || apprSts == null) {
			throw new PageNotFoundException();
		}

		if(apprSts.equalsIgnoreCase("ok")) {
			approvalVO.setApprSts("802");
		}
		else if (apprSts.equalsIgnoreCase("no")) {
			approvalVO.setApprSts("803");
		}
		boolean isSuccessChanged = this.approvalService.approvalStatusChange(approvalVO);

		return new AjaxResponse().append("result", isSuccessChanged)
								 .append("next", "/approval/approvalview?apprId=" + apprId);
	}

	@GetMapping("/approval/delete/{id}")
	public String doApprovalDelete(@PathVariable String apprId) {
		boolean isDeleteSuccess = this.approvalService.deleteOneApproval(apprId);
		return "redirect:/approval/approvallist";
	}

}
