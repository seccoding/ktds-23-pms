package com.ktdsuniversity.edu.pms.approval.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.approval.service.ApprovalService;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.SecurityUtils;
import com.ktdsuniversity.edu.pms.utils.Validator;

@RestController()
@RequestMapping("/api")
public class ApiApprovalController {
	
	@Autowired
	private ApprovalService approvalService;
	
	
	private SecurityUtils securityUtils = new SecurityUtils();
	
	/**
	 * 본인이 요청한 승인 리스트를 보내준다
	 * @param authentication
	 * @return
	 */
	@GetMapping("/approval/requester")
	public ApiResponse getApprovalListByRequester(Authentication authentication) {
		EmployeeVO employeeVO= this.securityUtils.getEmpVoByAuthentication(authentication);
		
		ApprovalListVO approvalListVO= this.approvalService.getAllApproval();
		approvalListVO.setApprList(
		approvalListVO.getApprList()
		.stream()
		.filter((appr)->appr.getApprReqtr().equals(employeeVO.getEmpId()))
		.filter((appr)->appr.getSearchYn().equals("Y"))
		.toList());
		approvalListVO.setApprCnt(approvalListVO.getApprList().size());
		
		return ApiResponse.Ok(approvalListVO);
	}
	/**
	 * 본인이 승인해야되는 승인 리스트를 보내준다
	 * @param authentication
	 * @return
	 */
	@GetMapping("/approval/approver")
	public ApiResponse getApprovalListByApprover(Authentication authentication) {
		EmployeeVO employeeVO= this.securityUtils.getEmpVoByAuthentication(authentication);
		
		ApprovalListVO approvalListVO= this.approvalService.getAllApproval();
		approvalListVO.setApprList(
		approvalListVO.getApprList()
		.stream()
		.filter((appr)->appr.getApprover().equals(employeeVO.getEmpId()))
		.filter((appr)->appr.getSearchYn().equals("Y"))
		.toList());
		approvalListVO.setApprCnt(approvalListVO.getApprList().size());

		
		return ApiResponse.Ok(approvalListVO);
	}
	/**
	 * 본인이 승인해야되는 승인 정보를 보내준다 
	 * @param authentication
	 * @param apprId
	 * @return
	 */
	@GetMapping("/approval/{apprId}")
	public ApiResponse gellApprovalInfoByApprId (Authentication authentication , @PathVariable String apprId) {
		Object approvalInfo = this.approvalService.gellApprovalByApprId(apprId);
		ApprovalVO approvalVO =this.approvalService.getAllApproval().getApprList().stream().filter(appr->appr.getApprId().equals(apprId)).toList().get(0);
		return ApiResponse.Ok(Map.of("approvalInfo",approvalInfo,"approvalVO",approvalVO));
		
	}
	/**
	 * 한개의 승인이 승인 혹은 거절 될 경우 해당 내용을 DB 에 업데이트 한다 
	 * @param authentication
	 * @return
	 */
	@PutMapping("/approval")
	public ApiResponse updateOneApproveal(Authentication authentication, @RequestBody ApprovalVO approvalVO) {
		
		if(approvalVO.getApprYn().equals("N") && approvalVO.getApprRsn()==null) {
			return ApiResponse.BAD_REQUEST(List.of("사유를 적어주시기 바랍니다"));
		}		
		
		boolean isUpdated =false;
		isUpdated=this.approvalService.updateOneApproveal(approvalVO);
		
		return isUpdated?ApiResponse.Ok("요청 완료", 1):ApiResponse.Ok("요청 실패", 0);

	}
	@PostMapping("/approval")
	public ApiResponse insertTest (Authentication authentication) {
		
		this.approvalService.test();
		return null;
	}
	
	@DeleteMapping("/approval")
	public ApiResponse deleteOneApproveal(Authentication authentication) {
		return null;

	}
	
}
