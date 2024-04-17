package com.ktdsuniversity.edu.pms.approval.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;


import com.ktdsuniversity.edu.pms.approval.service.ApprovalDetailService;
import com.ktdsuniversity.edu.pms.approval.service.ApprovalService;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class ApprovalController {

	private Logger logger = LoggerFactory.getLogger(ApprovalController.class);

	@Autowired
	private ApprovalService approvalService;

	@Autowired
	private ApprovalDetailService approvaldetailservice;

	@GetMapping("/approval/approvalhome")
	public String doApprovalHomePage(Model model) {
		ApprovalListVO apprList = this.approvalService.getAllApproval();
		model.addAttribute("apprList", apprList);
		return "approval/approvalhome";
	}

	@GetMapping("/approval/approvallist")
	public String doApprovalListByEmpIdPage(Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		ApprovalListVO apprList = this.approvalService.getAllApprovalByEmpId(employeeVO.getEmpId());
		model.addAttribute("apprList", apprList);
		return "approval/approvallist";
	}

	@GetMapping("/approval/view")
	public String doApprovalViewPage(@RequestParam String apprId, Model model) {

		//수정
		ApprovalVO approvalvo = this.approvalService.selectOneApproval(apprId);
		model.addAttribute("approvalvo", approvalvo);

		ApprovalDetailListVO approvalDetailList = this.approvaldetailservice.getAllApprovalDetail();
		model.addAttribute("approvalDetailList", approvalDetailList);
		model.addAttribute("apprid", apprId);

		return "approval/approvalview";
	}

	@GetMapping("/approval/delete/{id}")
	public String doApprovalDeletePage(@PathVariable String apprId) {
		boolean isDeleteSucess = this.approvalService.deleteOneApproval(apprId);

		if (isDeleteSucess) {
			logger.info("결재 삭제 성공");
		} else {
			logger.info("결재 삭제 실패");
		}

		return "redirect:/approval/approvallist";
	}
	
	// 버튼 클릭시 승인 상태로 변화	
	@ResponseBody
	@PostMapping("/approval/approve")
	public AjaxResponse doApproval(@RequestBody String apprId) {
//		apprid=형식으로 들어오기 때문에 replace로 해서 id값  출력
		String apprid = apprId.replace("apprid=", "");
		System.out.println("Received apprid: " + apprid);
		 		 
		boolean updateState=this.approvalService.updatesOneApproval(apprid);;
		
		return new AjaxResponse().append("next","/approval/home");
	}
	
	@GetMapping("/approval/approvalchanage")
	public String doApproval(Model model) {
		ApprovalDetailListVO approvalDetailList = this.approvaldetailservice.getPersonApprovalDetail("APPR_202304_000008");
		model.addAttribute("approvalDetailList", approvalDetailList);
		return "approval/approvalchanage";
	}
	
}
