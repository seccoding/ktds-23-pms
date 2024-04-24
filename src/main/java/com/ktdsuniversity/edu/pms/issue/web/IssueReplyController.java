package com.ktdsuniversity.edu.pms.issue.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.issue.service.IssueReplyService;
import com.ktdsuniversity.edu.pms.issue.vo.IssueReplyVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@RestController
public class IssueReplyController {

	@Autowired
	public IssueReplyService issueReplyService;
	
	@GetMapping("/ajax/issue/reply/{pPostId}")
	public AjaxResponse getAllReplies(@PathVariable String pPostId, IssueReplyVO issueReplyVO) {
		List<IssueReplyVO> issueReplyList = this.issueReplyService.getAllReplies(issueReplyVO);
		
		return new AjaxResponse().append("issueReplies", issueReplyList);
	}
	
	@PostMapping("/ajax/issue/reply/{pPostId}")
	public AjaxResponse doCreateNewReplies(@PathVariable String pPostId,
			IssueReplyVO issueReplyVO,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		issueReplyVO.setCrtrId(employeeVO.getEmpId());

		boolean isSuccess = this.issueReplyService.createNewReply(issueReplyVO);
		return new AjaxResponse().append("result", isSuccess);
	}
	
	@GetMapping("/ajax/issue/reply/delete/{rplId}")
	public AjaxResponse doDeleteReplies(@PathVariable String rplId,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		boolean isSuccess = this.issueReplyService.deleteOneReply(rplId, employeeVO.getEmpId());

		return new AjaxResponse().append("result", isSuccess);
	}
	
	@PostMapping("/ajax/issue/reply/modify/{rplId}")
	public AjaxResponse doModifyReplies(@PathVariable String rplId,
			IssueReplyVO issueReplyVO,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		issueReplyVO.setRplId(rplId);
		issueReplyVO.setCrtrId(employeeVO.getEmpId());

		boolean isSuccess = this.issueReplyService.modifyOneReply(issueReplyVO);
		return new AjaxResponse().append("result", isSuccess).append("pPostId", issueReplyVO.getpPostId());
	}
}
