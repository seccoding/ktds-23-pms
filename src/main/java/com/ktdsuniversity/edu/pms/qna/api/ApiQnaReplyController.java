package com.ktdsuniversity.edu.pms.qna.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.qna.service.QnaReplyService;
import com.ktdsuniversity.edu.pms.qna.vo.QnaReplyVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

@RestController
@RequestMapping("/api/v1")
public class ApiQnaReplyController {
	
	@Autowired
	public QnaReplyService qnaReplyService;
	
	@GetMapping("/qna/reply/{pPostId}")
	public ApiResponse getAllReplies(@PathVariable String pPostId, QnaReplyVO qnaReplyVO) {
		List<QnaReplyVO> qnaReplyList = this.qnaReplyService.getAllReplies(qnaReplyVO);
		return ApiResponse.Ok(qnaReplyList);
	}
	
	@PostMapping("/qna/reply/{pPostId}")
	public ApiResponse createNewReplies(@PathVariable String pPostId, QnaReplyVO qnaReplyVO, 
										Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		qnaReplyVO.setCrtrId(employeeVO.getEmpId());
		
		boolean isSuccess = this.qnaReplyService.createNewReply(qnaReplyVO);
		return ApiResponse.Ok(isSuccess);
	}
	
	@GetMapping("/qna/reply/delete/{rplId}") 
	public ApiResponse deleteReplies(@PathVariable String rplId, Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		boolean isSuccess = this.qnaReplyService.deleteOneReply(rplId, employeeVO.getEmpId());
		return ApiResponse.Ok(isSuccess);
	}
	
	@PostMapping("/qna/reply/modify/{rplId}")
	public ApiResponse modifyReplies(@PathVariable String rplId, QnaReplyVO qnaReplyVO, 
									 Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser)userDetails).getEmployeeVO();
		
		qnaReplyVO.setRplId(rplId);
		qnaReplyVO.setCrtrId(employeeVO.getEmpId());
		
		boolean isSuccess = this.qnaReplyService.modifyOneReply(qnaReplyVO);
		
		Map <String, Object> responseData = new HashMap<>();
		responseData.put("result", isSuccess);
		responseData.put("pPostId", qnaReplyVO.getpPostId());
		
		return ApiResponse.Ok(responseData, responseData == null ? 0 : 1);
	}
	

}
