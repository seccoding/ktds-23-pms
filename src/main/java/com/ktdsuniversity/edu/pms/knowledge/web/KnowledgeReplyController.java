package com.ktdsuniversity.edu.pms.knowledge.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeReplyService;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeReplyVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@RestController
public class KnowledgeReplyController {

	@Autowired
	public KnowledgeReplyService knowledgeReplyService;
	
	@GetMapping("/ajax/knowledge/reply/{pPostId}")
	public AjaxResponse getAllReplies(@PathVariable String pPostId, KnowledgeReplyVO knowledgeReplyVO) {
		List<KnowledgeReplyVO> knowledgeReplyList = this.knowledgeReplyService.getAllReplies(knowledgeReplyVO);
		
		return new AjaxResponse().append("knowledgeReplies", knowledgeReplyList);
	}
	
	@PostMapping("/ajax/knowledge/reply/{pPostId}")
	public AjaxResponse doCreateNewReplies(@PathVariable String pPostId,
			KnowledgeReplyVO knowledgeReplyVO,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		knowledgeReplyVO.setCrtrId(employeeVO.getEmpId());

		boolean isSuccess = this.knowledgeReplyService.createNewReply(knowledgeReplyVO);
		return new AjaxResponse().append("result", isSuccess);
	}
	
	@GetMapping("/ajax/knowledge/reply/delete/{rplId}")
	public AjaxResponse doDeleteReplies(@PathVariable String rplId,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		boolean isSuccess = this.knowledgeReplyService.deleteOneReply(rplId, employeeVO.getEmpId());

		return new AjaxResponse().append("result", isSuccess);
	}
	
	@PostMapping("/ajax/knowledge/reply/modify/{rplId}")
	public AjaxResponse doModifyReplies(@PathVariable String rplId,
			KnowledgeReplyVO knowledgeReplyVO,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		knowledgeReplyVO.setRplId(rplId);
		knowledgeReplyVO.setCrtrId(employeeVO.getEmpId());

		boolean isSuccess = this.knowledgeReplyService.modifyOneReply(knowledgeReplyVO);
		return new AjaxResponse().append("result", isSuccess).append("pPostId", knowledgeReplyVO.getpPostId());
	}
}
