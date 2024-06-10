package com.ktdsuniversity.edu.pms.knowledge.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeReplyService;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeService;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeReplyVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeReplyVO;

@RestController
@RequestMapping("/api/v1")
public class ApiKnowledgeReply {
	
	@Autowired
	public KnowledgeReplyService knowledgeReplyService;
	
	// 댓글 리스트(list)	
	@GetMapping("/reply/{pPostId}")
	public ApiResponse getAllknowledge(@PathVariable String pPostId, 
			KnowledgeReplyVO knowledgeReplyVO) {
		knowledgeReplyVO.setpPostId(pPostId);
		List<KnowledgeReplyVO> knowledgeReplyList = this.knowledgeReplyService.getAllReplies(knowledgeReplyVO);
		
		
		return ApiResponse.Ok( knowledgeReplyList);
	}
	
	// 지식관리 댓글 추가(insert)
	@PostMapping("/knowledge/reply/{pPostId}")
	public ApiResponse doCreateNewReplies(@PathVariable String pPostId,
			KnowledgeReplyVO knowledgeReplyVO,
			Authentication authentication,
			KnowledgeReplyVO knowledgereplyvo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String employeeVO = ((SecurityUser) userDetails).getEmployeeVO().getEmpId();
		
		knowledgereplyvo.setCrtrId(employeeVO);
		
		boolean isSuccess = this.knowledgeReplyService.createNewReply(knowledgeReplyVO);
		return ApiResponse.Ok(isSuccess);
		
	}
	
	// 지식관리 댓글 삭제
	@GetMapping("/knowledge/reply/delete/{rplId}")
	public ApiResponse doDeleteReplies(@PathVariable String rplId, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String employeeVO = ((SecurityUser) userDetails).getEmployeeVO().getEmpId();
		
		boolean isSuccess = this.knowledgeReplyService.deleteOneReply(rplId, employeeVO);

		List<String> errorMessage = new ArrayList<>();
		if (isSuccess) {
			System.out.println("삭제에 성공하였습니다");
		}
		else {
			 errorMessage.add("삭제에 실패하였습니다");  // More specific error message
		     return ApiResponse.BAD_REQUEST(errorMessage);
		}

		return ApiResponse.Ok(isSuccess);
	}
	
	// 지식관리 댓글 수정
	@PostMapping("/knowledge/reply/modify/{rplId}")
	public ApiResponse doModifyReplies(@PathVariable String rplId, 
			KnowledgeReplyVO knowledgeReplyVO,
			Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String employeeVO = ((SecurityUser) userDetails).getEmployeeVO().getEmpId();
		
		knowledgeReplyVO.setRplId(rplId);
		knowledgeReplyVO.setCrtrId(employeeVO);
			
		boolean isSuccess = this.knowledgeReplyService.modifyOneReply(knowledgeReplyVO);
		
		return ApiResponse.Ok(isSuccess);
		
	}
	
}
