package com.ktdsuniversity.edu.pms.issue.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.issue.service.IssueReplyService;
import com.ktdsuniversity.edu.pms.issue.vo.IssueReplyVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

@RestController
public class ApiIssueReplyController {
	
	@Autowired
	public IssueReplyService issueReplyService;
	
	@GetMapping("/issue/reply/{pPostId}")
	public ApiResponse getAllReplies(@PathVariable String pPostId, IssueReplyVO issueReplyVO) {
		List<IssueReplyVO> issueReplyList = this.issueReplyService.getAllReplies(issueReplyVO);
		
		return ApiResponse.Ok(issueReplyList);
	}
	
	@PostMapping("/issue/reply/{pPostId}")
	public ApiResponse createNewReplies(@PathVariable String pPostId, IssueReplyVO issueReplyVO,
										Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		issueReplyVO.setCrtrId(employeeVO.getEmpId());
		boolean isSuccess = this.issueReplyService.createNewReply(issueReplyVO);
		return ApiResponse.Ok(isSuccess);
	}
	
	@GetMapping("/issue/reply/delete/{rplId}")
	public ApiResponse deleteReplies(@PathVariable String rplId, 
									Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		boolean isSuccess = this.issueReplyService.deleteOneReply(rplId, employeeVO.getEmpId());
		return ApiResponse.Ok(isSuccess);
	}
	
	@PostMapping("/issue/reply/modify/{rplId}")
	public ApiResponse modifyReplies(@PathVariable String rplId, IssueReplyVO issueReplyVO,
									Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		issueReplyVO.setRplId(rplId);
		issueReplyVO.setCrtrId(employeeVO.getEmpId());
		
		boolean isSuccess = this.issueReplyService.modifyOneReply(issueReplyVO);
		return ApiResponse.Ok(isSuccess);
						  
	}
}
