package com.ktdsuniversity.edu.pms.issue.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.issue.service.IssueService;
import com.ktdsuniversity.edu.pms.issue.vo.IssueListVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class IssueController {

	@Autowired
	private IssueService issueService;
	
//	@ResponseBody
//	@GetMapping("/issue")
//	public AjaxResponse viewIssueListPage() {
//		IssueListVO issueListVO = issueService.getAllIssue();
//		
//		return AjaxResponse().append("issue", issueListVO);
//		
//	}

}
