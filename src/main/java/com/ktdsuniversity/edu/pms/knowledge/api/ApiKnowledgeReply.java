package com.ktdsuniversity.edu.pms.knowledge.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeReplyDao;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeReplyService;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeService;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeReplyVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.ReplyRecommandVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeReplyVO;
import org.springframework.web.bind.annotation.RequestBody;


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
		EmployeeVO employee = ((SecurityUser) userDetails).getEmployeeVO();
		
		List<String> errorMessage = new ArrayList<>();
		String str=this.knowledgeReplyService.findReplyId(rplId);
		if(!employee.getEmpId().equals(str)) {
			errorMessage.add("글 작성자만 수정 가능합니다");  
		    return ApiResponse.BAD_REQUEST(errorMessage);
		}

		
		boolean isSuccess = this.knowledgeReplyService.deleteOneReply(rplId, employee.getEmpId());
		
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
		EmployeeVO employee = ((SecurityUser) userDetails).getEmployeeVO();
		
		List<String> errorMessage = new ArrayList<>();
		String str=this.knowledgeReplyService.findReplyId(rplId);
		if(!employee.getEmpId().equals(str)) {
			errorMessage.add("글 작성자만 수정 가능합니다");  
		    return ApiResponse.BAD_REQUEST(errorMessage);
		}
		
		knowledgeReplyVO.setRplId(rplId);
		knowledgeReplyVO.setCrtrId(employee.getEmpId());
			
		boolean isSuccess = this.knowledgeReplyService.modifyOneReply(knowledgeReplyVO);
		
		//추천 중복체크		
		if (!isSuccess) {
			errorMessage.add("추천은 한번만 가능 합니다");  // More specific error message
		     return ApiResponse.BAD_REQUEST(errorMessage);
		}
		
		return ApiResponse.Ok(isSuccess);
		
	}
	
	//  댓글 추천	
	@PostMapping("/knowledge/reply/recommand/{rplId}")
	public ApiResponse replyRecommand(@PathVariable String rplId, 
			Authentication authentication, 
			ReplyRecommandVO replyRecommandvo) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String employeeVO = ((SecurityUser) userDetails).getEmployeeVO().getEmpId();
		
		replyRecommandvo.setReprplid(rplId);
		replyRecommandvo.setRepcrtrid(employeeVO);
		
		boolean  isRecommend= knowledgeReplyService.updateReplyRecommend(replyRecommandvo);
		
		return ApiResponse.Ok(isRecommend);
	}
	
	// 답변	
	//재댓글 list
	@GetMapping("/reReply/{knlId}")
	public ApiResponse getAllreReply(@PathVariable String knlId, 
			KnowledgeReplyVO knowledgeReplyVO) {
		System.out.println("knlId"+knlId);
		knowledgeReplyVO.setRplPid(knlId);
		List<KnowledgeReplyVO> knowledgeReplyList = this.knowledgeReplyService.getAllreReplies(knowledgeReplyVO);
		
		return ApiResponse.Ok( knowledgeReplyList);
	}
	
	//재댓글	insert
	@PostMapping("/knowledge/rereply/{pPostId}")
	public ApiResponse postMethodName(@RequestParam(required = false) String pPostId,
			@RequestParam(required = false) String rplId,
			KnowledgeReplyVO knowledgeReplyVO,
			Authentication authentication,
			KnowledgeReplyVO knowledgereplyvo) {
			System.out.println("pPostId:"+pPostId);
			System.out.println("rplId:"+pPostId);
			
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String employeeVO = ((SecurityUser) userDetails).getEmployeeVO().getEmpId();
			
			knowledgereplyvo.setRplPid(rplId);
			knowledgereplyvo.setCrtrId(employeeVO);
			knowledgeReplyVO.setRplId(pPostId);
			
			boolean isSuccess = this.knowledgeReplyService.createNewReply(knowledgeReplyVO);
		
			return ApiResponse.Ok(isSuccess);
	}

	//재댓글 삭제
	@GetMapping("/knowledge/rereply/delete/{rplId}")
	public ApiResponse doDeletetrReplies(@PathVariable String rplId, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employee = ((SecurityUser) userDetails).getEmployeeVO();
		
		List<String> errorMessage = new ArrayList<>();
		String str=this.knowledgeReplyService.findReplyId(rplId);
		if(!employee.getEmpId().equals(str)) {
			errorMessage.add("글 작성자만 수정 가능합니다");  
		    return ApiResponse.BAD_REQUEST(errorMessage);
		}
		
		
		boolean isSuccess = this.knowledgeReplyService.deleteOneReply(rplId, employee.getEmpId());
		
		if (isSuccess) {
			System.out.println("삭제에 성공하였습니다");
		}
		else {
			 errorMessage.add("삭제에 실패하였습니다");  // More specific error message
		     return ApiResponse.BAD_REQUEST(errorMessage);
		}
			
		return ApiResponse.Ok(isSuccess);
	}
	
	
}
