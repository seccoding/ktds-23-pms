package com.ktdsuniversity.edu.pms.qna.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.qna.service.QnaReplyService;
import com.ktdsuniversity.edu.pms.qna.vo.QnaReplyVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@RestController
public class QnaReplyController {

	@Autowired
	public QnaReplyService qnaReplyService;
	
	@GetMapping("/ajax/qna/reply/{pPostId}")
	public AjaxResponse getAllReplies(@PathVariable String pPostId,
									QnaReplyVO qnaReplyVO) {
		List<QnaReplyVO> qnaReplyList = this.qnaReplyService.getAllReplies(qnaReplyVO);
		return new AjaxResponse().append("qnaReplies", qnaReplyList);
	}
	
	@PostMapping("/ajax/qna/reply/{pPostId}")
	public AjaxResponse doCreateNewReplies(@PathVariable String pPostId,
			QnaReplyVO qnaReplyVO,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		qnaReplyVO.setCrtrId(employeeVO.getEmpId());
		
		boolean isSuccess = this.qnaReplyService.createNewReply(qnaReplyVO);
		return new AjaxResponse().append("result", isSuccess);
	}
	
	@GetMapping("/ajax/qna/reply/delete/{rplId}")
	public AjaxResponse doDeleteReplies(@PathVariable String rplId,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		boolean isSuccess = this.qnaReplyService.deleteOneReply(rplId, employeeVO.getEmpId());

		return new AjaxResponse().append("result", isSuccess);
	}
	
	@PostMapping("/ajax/qna/reply/modify/{rplId}")
	public AjaxResponse doModifyReplies(@PathVariable String rplId,
			QnaReplyVO qnaReplyVO,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {

		qnaReplyVO.setRplId(rplId);
		qnaReplyVO.setCrtrId(employeeVO.getEmpId());

		boolean isSuccess = this.qnaReplyService.modifyOneReply(qnaReplyVO);
		return new AjaxResponse().append("result", isSuccess).append("pPostId", qnaReplyVO.getpPostId());
	}
}
