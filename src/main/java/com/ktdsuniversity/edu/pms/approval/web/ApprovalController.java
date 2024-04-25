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

import com.ktdsuniversity.edu.pms.approval.service.ApprovalDetailService;
import com.ktdsuniversity.edu.pms.approval.service.ApprovalService;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.approval.vo.SearchApprovalVO;
import com.ktdsuniversity.edu.pms.borrow.service.BorrowService;
import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementListVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class ApprovalController {

	private Logger logger = LoggerFactory.getLogger(ApprovalController.class);

	@Autowired
	private ApprovalService approvalService;
	@Autowired
	private ApprovalDetailService approvalDetailService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private BorrowService borrowService;

	// PSH0422
	// 조건에 따른 count 어떻게 뿌려줄지 생각해보기
	@GetMapping("/approval/home")
	public String viewApprovalHomePage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
									   Model model, SearchApprovalVO searchApprovalVO) {

		commonSearchApproval(employeeVO, model, searchApprovalVO);
		return "approval/approvalhome";
	}
 

	@GetMapping("/approval/progresslist")
	public String viewApprProgressListPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
										   Model model, SearchApprovalVO searchApprovalVO) {

		searchApprovalVO.setSearchStatus("801"); // CommonCode...?									 
		commonSearchApproval(employeeVO, model, searchApprovalVO);
		return "approval/approvallist";					 
	}
 

	@GetMapping("/approval/completelist")
	public String viewApprCompleteListPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
									   Model model, SearchApprovalVO searchApprovalVO) {
		searchApprovalVO.setSearchStatus("800");
		commonSearchApproval(employeeVO, model, searchApprovalVO);

		return "approval/approvallist";
	}

	
	@GetMapping("/approval/view")
	public String doApprovalViewPage(@RequestParam String apprId, Model model) {

		ApprovalVO approvalVO = this.approvalService.selectOneApprovalAll(apprId);
		model.addAttribute("approvalVO", approvalVO);
		ApprovalDetailListVO approvalListVO = this.approvalDetailService.getPersonApprovalDetail(apprId);
		model.addAttribute("approvalList", approvalListVO);

		if (approvalVO == null || approvalVO.getApprovalDetailVOList() == null) {
			throw new PageNotFoundException();
		}
		return "approval/approvalview";
	}


	@GetMapping("/approval/write")
	public String viewApprovalWritePage(Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		EmployeeVO dmdEmployeeVO = employeeService.getOneEmployee(employeeVO.getEmpId());
		BorrowListVO borrowListVO = borrowService.getUserRentalStateForAppr(dmdEmployeeVO);

		if (borrowListVO == null) {
			throw new PageNotFoundException();
		}

		model.addAttribute("employee", dmdEmployeeVO);
		model.addAttribute("borrowList", borrowListVO);
		return "approval/approvalwrite";
	}

	@ResponseBody
	@PostMapping("/ajax/approval/write")
	public AjaxResponse doApprovalWrite(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			ApprovalVO newApprovalVO) {

		if (!employeeVO.getEmpId().equals(newApprovalVO.getDmdId()) && !newApprovalVO.getApprCtgr().equals("902")) {
			throw new PageNotFoundException();
		}
		// validator로 수정
		if (newApprovalVO.getApprTtl() == null) {
			return new AjaxResponse().append("errorMessage", "기안서 제목은 필수 입력 항목입니다.");
		}
		String apprId = approvalService.selectNewApprId();
		newApprovalVO.setApprId(apprId);
		boolean isSuccessCreate = this.approvalService.createApproval(newApprovalVO);

		return new AjaxResponse().append("result", isSuccessCreate)
								 .append("next", "approval/approvallist");
	}
	
//	@ResponseBody
//	@PostMapping("/ajax/approval/product")
//	public AjaxResponse addProductForNewApproval(ProductManagementListVO prdtMngListVO, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
//		boolean isSuccessSelected = this.approvalService.getPrdtForNewAppr(prdtMngListVO);
//		return new AjaxResponse().append("result", isSuccessSelected);
//	}
	
	// 결재승인,반려
	@ResponseBody
	@PostMapping("/ajax/approval/statuschange/{apprId}")
	public AjaxResponse doApprovalStatusChange(@PathVariable String apprId, ApprovalVO approvalVO,
												@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		approvalVO.setApprId(apprId);
		boolean isSuccessChanged = this.approvalService.approvalStatusChange(approvalVO);

		return new AjaxResponse().append("result", isSuccessChanged);
	}
	
    // 기대여비품 반납, 사용불가
    @ResponseBody
    @PostMapping("/ajax/approval/unusablePrdt")
    public AjaxResponse doUnusableProduct(String apprId, ApprovalVO approvalVO, 
    										@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
    	approvalVO.setApprId(apprId);
        boolean isSuccessChanged = this.approvalService.updateUnusablePrdt(approvalVO);
        return new AjaxResponse().append("result", isSuccessChanged)
                                 .append("next", "/approval/view?apprId=" + apprId);
    }

	// 결재 승인 후 신규비품 대여
	@ResponseBody
	@PostMapping("/ajax/product/newprdtborrow")
	public AjaxResponse doNewPrdtForAppr(String apprId, ApprovalVO approvalVO) {
		approvalVO.setApprId(apprId);
		boolean isSuccessBorrow = this.approvalService.getNewPrdtBorrowForAppr(approvalVO);
		return new AjaxResponse().append("result", isSuccessBorrow);
	}

	@ResponseBody
	@GetMapping("/ajax/approval/delete/{apprId}")
	public AjaxResponse doApprovalDelete(@PathVariable String apprId, Model model) {

		ApprovalVO approvalVO = this.approvalService.selectOneApproval(apprId);
		// 퇴사: true
		boolean isLeaveEmployee = this.employeeService.getOneEmployee(approvalVO.getDmdId()).getWorkSts().equals("204");
		// 대여중인 물품이 없음: true
		boolean isNoReturnProduct = this.borrowService.getIsNotReturnCount(approvalVO.getDmdId());

		if (!isLeaveEmployee) {
			return new AjaxResponse().append("errorMessage", "퇴직한 사원의 결재 내역만 삭제할 수 있습니다.");
		}
		if (!isNoReturnProduct) {
			return new AjaxResponse().append("errorMessage", "대여중인 비품이 포함된 결재 내역은 삭제할 수 없습니다.");
		}

		boolean isDeleteSuccess = this.approvalService.deleteOneApproval(apprId);
		return new AjaxResponse().append("result", isDeleteSuccess)
								 .append("next", "approval/list");
	}
	
	// searchAllApproval 공통 메서드
	private void commonSearchApproval(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO
									, Model model, SearchApprovalVO searchApprovalVO) {
		
		// 관리자로 로그인하면 결재 페이지 오류 남(teamList == null)
//		EmployeeVO employee = this.employeeService.getOneEmployeeNoTeam(employeeVO.getEmpId());

		searchApprovalVO.setEmployeeVO(employeeVO);
		ApprovalListVO apprListVO = this.approvalService.searchAllApproval(searchApprovalVO);

//		model.addAttribute("employee", employee);
		model.addAttribute("apprList", apprListVO);
		model.addAttribute("searchApproval", searchApprovalVO);
	}

}
