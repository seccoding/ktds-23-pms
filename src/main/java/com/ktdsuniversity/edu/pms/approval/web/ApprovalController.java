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
import com.ktdsuniversity.edu.pms.approval.vo.SearchApprovalVO;
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

	@GetMapping("/approval/home")
	public String doApprovalHomePage(Model model, ApprovalVO approvaVo) {
//		ApprovalListVO apprList = this.approvalService.getAllApproval();
//        model.addAttribute("apprList", apprList);

		ApprovalListVO apprList = this.approvalService.searchAllBoard(approvaVo);
		model.addAttribute("apprList", apprList);
		model.addAttribute("searchBoardVO", approvaVo);

		ApprovalListVO approveList = this.approvalService.getAllApprove();
		model.addAttribute("approveList", approveList);

		ApprovalListVO OneWeekApprovalList = this.approvalService.getAllOneWeekApproval();
		model.addAttribute("OneWeekApprovalList", OneWeekApprovalList);

		ApprovalListVO monthApprovalList = this.approvalService.getAllMonthApproval();
		model.addAttribute("monthApprovalList", monthApprovalList);

		return "approval/approvalhome";
	}

	@GetMapping("/approval/list")
	public String doApprovalListByEmpIdPage(Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			SearchApprovalVO searchapprovalvo) {
//		ApprovalListVO apprList = this.approvalService.getAllApprovalByEmpId(employeeVO.getEmpId());
//		model.addAttribute("apprList", apprList);
		System.out.println(employeeVO.getEmpId());

		searchapprovalvo.setEmpId(employeeVO.getEmpId());

		ApprovalListVO apprList = this.approvalService.searchApprovalView(searchapprovalvo, employeeVO.getEmpId());
		model.addAttribute("searchapprovalvo", searchapprovalvo);
		model.addAttribute("apprList", apprList);

		return "/approval/approvallist";
	}

	// 검색
	@ResponseBody
	@PostMapping("/approval/list")
	public AjaxResponse doPsostApprovalListByEmpIdPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			@RequestParam("searchKeyword") String searchKeyword, SearchApprovalVO searchapprovalvo, Model model,
			String searchType) {

		searchapprovalvo.setEmpId(employeeVO.getEmpId());
		searchapprovalvo.setSearchType(searchType);
		searchapprovalvo.setSearchKeyword(searchKeyword);

		ApprovalListVO apprList = this.approvalService.searchApprovalView(searchapprovalvo, employeeVO.getEmpId());

		return new AjaxResponse().append("success", "success")
								 .append("apprList", apprList).append("searchapprovalvo", searchapprovalvo);
	}

	@GetMapping("/approval/view")
	public String doApprovalViewPage(@RequestParam String apprId, Model model) {

		ApprovalVO approvalVO = this.approvalService.selectOneApprovalAll(apprId);
		model.addAttribute("approvalVO", approvalVO);

		if (approvalVO == null || approvalVO.getApprovalDetailVOList() == null) {
			throw new PageNotFoundException();
		}
		return "/approval/approvalview";
	}

	// PSH0422

	// 비품대여현황 jsp에서 전달받을 파라미터: @RequestParam String empId
	@GetMapping("/approval/write")
	public String viewApprovalWritePage(Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		// test code
		EmployeeVO dmdEmployeeVO = employeeService.getOneEmployee(employeeVO.getEmpId());
		BorrowListVO borrowListVO = borrowService.getUserRentalStateForAppr(dmdEmployeeVO);

//		if(dmdEmployeeVO == null || ! dmdEmployeeVO.getEmpId().equals(employeeVO.getEmpId())) {
//			throw new PageNotFoundException();
//		}

		if (borrowListVO == null) {
			throw new PageNotFoundException();
		}

		model.addAttribute("employee", dmdEmployeeVO);
		model.addAttribute("borrowList", borrowListVO);
		return "/approval/approvalwrite";
	}

	@ResponseBody
	@PostMapping("/ajax/approval/write")
	public AjaxResponse doApprovalWrite(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			ApprovalVO newApprovalVO) {

		if (!employeeVO.getEmpId().equals(newApprovalVO.getDmdId()) && !newApprovalVO.getApprCtgr().equals("902")) {
			throw new PageNotFoundException();
		}

		if (newApprovalVO.getApprTtl() == null) {
			return new AjaxResponse().append("errorMessage", "기안서 제목은 필수 입력 항목입니다.");
		}
		String apprId = approvalService.selectNewApprId();
		newApprovalVO.setApprId(apprId);
		boolean isSuccessCreate = this.approvalService.createApproval(newApprovalVO);

		return new AjaxResponse().append("result", isSuccessCreate)
								 .append("next", "/approval/view?apprId=" + apprId);
	}

	// 수정필요
	@ResponseBody
	@PostMapping("/ajax/approval/statuschange/{apprId}")
	public AjaxResponse doApprovalStatusChange(@PathVariable String apprId, @RequestParam String apprSts,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		ApprovalVO approvalVO = this.approvalService.selectOneApproval(apprId);
		if (!employeeVO.getEmpId().equals(approvalVO.getDmdId()) || apprSts == null) {
			throw new PageNotFoundException();
		}

		if (apprSts.equalsIgnoreCase("ok")) {
			approvalVO.setApprSts("802");
		} else if (apprSts.equalsIgnoreCase("no")) {
			approvalVO.setApprSts("803");
		}
		boolean isSuccessChanged = this.approvalService.approvalStatusChange(approvalVO);

		return new AjaxResponse().append("result", isSuccessChanged)
								 .append("next", "/approval/view?apprId=" + apprId);
	}

	// 결재 승인 후 신규비품 대여
//	@ResponseBody
//	@PostMapping("/ajax/product/newPrdtBorrow")
//	public AjaxResponse doNewPrdtForAppr(String apprId) {
//		boolean isSuccessBorrow = this.approvalService.getNewPrdtBorrowForAppr(apprId);
//		return new AjaxResponse().append("result", isSuccessBorrow).append("next", "/approval/list");
//	}

//	@ResponseBody
//	@GetMapping("/ajax/approval/delete/{apprId}")
//	public AjaxResponse doApprovalDelete(@PathVariable String apprId, Model model) {
//
//		ApprovalVO approvalVO = this.approvalService.selectOneApproval(apprId);
//		// 퇴사: true
//		boolean isLeaveEmployee = this.employeeService.getOneEmployee(approvalVO.getDmdId()).getWorkSts().equals("204");
//		// 대여중인 물품이 없음: true
//		boolean isNoReturnProduct = this.borrowService.getIsNotReturnCount(approvalVO.getDmdId());
//
//		if (!isLeaveEmployee) {
//			return new AjaxResponse().append("errorMessage", "퇴직한 사원의 결재 내역만 삭제할 수 있습니다.");
//		}
//		if (!isNoReturnProduct) {
//			return new AjaxResponse().append("errorMessage", "대여중인 비품이 포함된 결재 내역은 삭제할 수 없습니다.");
//		}
//
//		boolean isDeleteSuccess = this.approvalService.deleteOneApproval(apprId);
//		return new AjaxResponse().append("result", isDeleteSuccess).append("next", "/approval/list");
//	}

}
