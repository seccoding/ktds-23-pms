//package com.ktdsuniversity.edu.pms.approval.web;
//
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttribute;
//
//import com.ktdsuniversity.edu.pms.approval.service.ApprovalDetailService;
//import com.ktdsuniversity.edu.pms.approval.service.ApprovalService;
//import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailListVO;
//import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
//import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
//import com.ktdsuniversity.edu.pms.approval.vo.SearchApprovalVO;
//import com.ktdsuniversity.edu.pms.borrow.service.BorrowService;
//import com.ktdsuniversity.edu.pms.borrow.vo.BorrowListVO;
//import com.ktdsuniversity.edu.pms.borrow.vo.BorrowVO;
//import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
//import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
//import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
//import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
//import com.ktdsuniversity.edu.pms.utils.Validator;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@Controller
//public class ApprovalController {
//
//	private Logger logger = LoggerFactory.getLogger(ApprovalController.class);
//
//	@Autowired
//	private ApprovalService approvalService;
//	@Autowired
//	private ApprovalDetailService approvalDetailService;
//	@Autowired
//	private EmployeeService employeeService;
//	@Autowired
//	private BorrowService borrowService;
//
//	@GetMapping(value ={"/approval/home", "/approval/home/waiting", "/approval/home/delay", "/approval/home/oneMonth"})
//	public String viewApprovalHomePage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
//									   Model model, SearchApprovalVO searchApprovalVO, HttpServletRequest request) {
//
//		String url = request.getRequestURI();
//		String uri = url.substring(url.lastIndexOf('/') + 1);
//		boolean searchAuth = this.approvalService.getDeptLeader(employeeVO.getEmpId());
//		
//		// 완료되지 않은 결재
//		SearchApprovalVO searchWatingApprovalVO = new SearchApprovalVO("waiting", searchAuth, "", employeeVO);
//		ApprovalListVO apprWatingListVO = this.approvalService.searchAllApproval(searchWatingApprovalVO);
//		model.addAttribute("apprWaitingList", apprWatingListVO);
//
//		// 일주일 이상 지연된 결재
//		SearchApprovalVO searchDelayApprovalVO = new SearchApprovalVO("waiting", searchAuth, "delay", employeeVO);
//		ApprovalListVO approvalDelayListVO =this.approvalService.searchAllApproval(searchDelayApprovalVO);
//		model.addAttribute("approvalDelayList", approvalDelayListVO);
//
//		// 한달 이내 결재
//		SearchApprovalVO searchOneMonthApprovalVO = new SearchApprovalVO("complete", searchAuth, "oneMonth", employeeVO);
//		ApprovalListVO approvalOneMonthListVO = this.approvalService.searchAllApproval(searchOneMonthApprovalVO);
//		model.addAttribute("approvalOneMonthList", approvalOneMonthListVO);
//		
//		// 전체 목록
//		if(uri.equals("waiting")) {
//			commonSearchApproval(employeeVO, model, searchWatingApprovalVO);
//		} else if(uri.equals("delay")) {
//			commonSearchApproval(employeeVO, model, searchDelayApprovalVO);
//		} else if(uri.equals("oneMonth")) {
//			commonSearchApproval(employeeVO, model, searchOneMonthApprovalVO);
//		} else {
//			commonSearchApproval(employeeVO, model, searchApprovalVO);
//		}
//		return "approval/approvalhome";
//	}
//
//	@GetMapping(value = {"/approval/progresslist", "/approval/completelist"})
//	public String viewApprovalListPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
//										   Model model, SearchApprovalVO searchApprovalVO, HttpServletRequest request) {
//
//		String url = request.getRequestURI();
//		String uri = url.substring(url.lastIndexOf('/') + 1);
//		searchApprovalVO.setSearchStatus(uri);
//
//		commonSearchApproval(employeeVO, model, searchApprovalVO);
//		return "approval/approvallist";					 
//	}
//
//	@GetMapping("/approval/view")
//	public String doApprovalViewPage(@RequestParam String apprId, Model model, SearchApprovalVO searchApprovalVO,
//									 @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
//
//		ApprovalVO approvalVO = this.approvalService.selectOneApprovalAll(apprId);
//		model.addAttribute("approvalVO", approvalVO);
//		ApprovalDetailListVO approvalListVO = this.approvalDetailService.getPersonApprovalDetail(apprId);
//		model.addAttribute("approvalList", approvalListVO);
//		searchApprovalVO.setSearchAuth(this.approvalService.getDeptLeader(employeeVO.getEmpId()));
//		model.addAttribute("searchApproval", searchApprovalVO);
//
//		if (approvalVO == null || approvalVO.getApprovalDetailVOList() == null) {
//			throw new PageNotFoundException();
//		}
//		return "approval/approvalview";
//	}
//
//	@GetMapping("/approval/write")
//	public String viewApprovalWritePage(Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, 
//			 								HttpServletRequest request) {
//
//		EmployeeVO dmdEmployeeVO = this.employeeService.getOneEmployeeCheckNull(employeeVO.getEmpId());
//		BorrowListVO borrowListVO = this.borrowService.getUserRentalStateForAppr(dmdEmployeeVO);
//
//		if (borrowListVO.getBorrowCnt() == 0 && borrowListVO.getBorrowList().size() == 0) {
//			model.addAttribute("errorMessage", "대여중인 비품이 없으므로 비품변경신청을 할 수 없습니다.");
//			return "approval/approvalhome";
//		}
//
//		model.addAttribute("employee", dmdEmployeeVO);
//		model.addAttribute("borrowList", borrowListVO);
//		return "approval/approvalwrite";
//	}
//
//	@ResponseBody
//	@PostMapping("/ajax/approval/write")
//	public AjaxResponse doApprovalWrite(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
//			ApprovalVO newApprovalVO) {
//
//		if (!employeeVO.getEmpId().equals(newApprovalVO.getDmdId()) && !newApprovalVO.getApprCtgr().equals("902")) {
//			throw new PageNotFoundException();
//		}
//
//		Validator<ApprovalVO> validator = new Validator<>(newApprovalVO);
//		validator.add("apprTtl", Validator.Type.NOT_EMPTY, "기안서 제목을 입력해주세요.")
//				 .add("apprCntnt", Validator.Type.NOT_EMPTY, "기안서 내용을 입력해주세요.")
//				 .add("productListVO", Validator.Type.NOT_EMPTY, "변경할 비품을 선택해주세요.")
//				 .start();
//		if(validator.hasErrors()) {
//			Map<String,List<String>> errors = validator.getErrors();
//			return new AjaxResponse().append("errors", errors);
//		}
//
//		String apprId = approvalService.selectNewApprId();
//		newApprovalVO.setApprId(apprId);
//		boolean isSuccessCreate = this.approvalService.createApproval(newApprovalVO);
//
//		return new AjaxResponse().append("result", isSuccessCreate)
//								 .append("next", "/approval/progresslist");
//	}
//
//	@ResponseBody
//	@PostMapping("/ajax/approval/addProduct")
//	public AjaxResponse addProductForNewApproval(@RequestParam("addProducts[]") List<String> addProducts) {
//
//		List<BorrowVO> borrowList = this.approvalService.getAddProductApproval(addProducts);
//		return new AjaxResponse().append("borrowList", borrowList);
//	}
//
//	// 결재승인,반려
//	@ResponseBody
//	@PostMapping("/ajax/approval/statuschange/{apprId}")
//	public AjaxResponse doApprovalStatusChange(@PathVariable String apprId, ApprovalVO approvalVO) {
//
//		approvalVO.setApprId(apprId);
//		boolean isSuccessChanged = this.approvalService.approvalStatusChange(approvalVO);
//
//		return new AjaxResponse().append("result", isSuccessChanged);
//	}
//	
//    // 기대여비품 반납, 사용불가
//    @ResponseBody
//    @PostMapping("/ajax/approval/unusablePrdt")
//    public AjaxResponse doUnusableProduct(String apprId, ApprovalVO approvalVO) {
//    	approvalVO.setApprId(apprId);
//        boolean isSuccessChanged = this.approvalService.updateUnusablePrdt(approvalVO);
//        return new AjaxResponse().append("result", isSuccessChanged)
//                                 .append("next", "/approval/view?apprId=" + apprId);
//    }
//
//	// 결재 승인 후 신규비품 대여
//	@ResponseBody
//	@PostMapping("/ajax/product/newprdtborrow")
//	public AjaxResponse doNewPrdtForAppr(String apprId, ApprovalVO approvalVO) {
//		approvalVO.setApprId(apprId);
//		boolean isSuccessBorrow = this.approvalService.getNewPrdtBorrowForAppr(approvalVO);
//		return new AjaxResponse().append("result", isSuccessBorrow);
//	}
//
//	@ResponseBody
//	@GetMapping("/ajax/approval/delete/{apprId}")
//	public AjaxResponse doApprovalDelete(@PathVariable String apprId, Model model) {
//
//		ApprovalVO approvalVO = this.approvalService.selectOneApproval(apprId);
//		// 퇴사: true
//		boolean isLeaveEmployee = this.employeeService.getOneEmployeeCheckNull(approvalVO.getDmdId()).getWorkSts().equals("204");
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
//		return new AjaxResponse().append("result", isDeleteSuccess)
//								 .append("next", "/approval/completelist");
//	}
//	
//	// searchAllApproval 공통
//	private void commonSearchApproval(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO
//									, Model model, SearchApprovalVO searchApprovalVO) {
//
//		searchApprovalVO.setEmployeeVO(employeeVO);
//		searchApprovalVO.setSearchAuth(this.approvalService.getDeptLeader(employeeVO.getEmpId()));
//		EmployeeVO employee = this.employeeService.getOneEmployeeCheckNull(employeeVO.getEmpId());
//		ApprovalListVO apprListVO = this.approvalService.searchAllApproval(searchApprovalVO);
//		model.addAttribute("employee", employee);
//		model.addAttribute("apprList", apprListVO);
//		model.addAttribute("searchApproval", searchApprovalVO);
//	}
//
//}
