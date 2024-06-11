package com.ktdsuniversity.edu.pms.approval.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.approval.service.ApprovalService;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalListVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;

@RestController()
@RequestMapping("/api")
public class ApiApprovalController {
	
	@Autowired
	private ApprovalService approvalService;
	
	/**
	 * 본인이 승인해야되는 승인 리스트를 보내준다
	 * 
	 * @param authentication
	 * @return
	 */
	@GetMapping("/approval")
	public ApiResponse getAllApprovalList(Authentication authentication) {
		
		ApprovalListVO approvalListVO= this.approvalService.getAllApproval();
		
		return ApiResponse.Ok(approvalListVO);
	}
	/**
	 * 한개의 승인이 승인 혹은 거절 될 경우 해당 내용을 DB 에 업데이트 한다 
	 * @param authentication
	 * @return
	 */
	@PutMapping("/approval")
	public ApiResponse updateOneApproveal(Authentication authentication, @RequestBody ApprovalVO approvalVO) {
		
		if(approvalVO.getApprYn().equals("N") && approvalVO.getApprRsn().isEmpty()) {
			return ApiResponse.BAD_REQUEST(List.of("사유를 적어주시기 바랍니다"));
		}
		Validator<ApprovalVO> validator = new Validator<ApprovalVO>(approvalVO);
		
		
		boolean isUpdated =this.approvalService.updateOneApproveal(approvalVO);
		
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
