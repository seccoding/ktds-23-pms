package com.ktdsuniversity.edu.pms.issue.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.edu.pms.issue.service.IssueService;
import com.ktdsuniversity.edu.pms.issue.vo.IssueListVO;
import com.ktdsuniversity.edu.pms.issue.vo.IssueVO;
import com.ktdsuniversity.edu.pms.issue.vo.SearchIssueVO;

@Controller
public class IssueController {

	@Autowired
	private IssueService issueService;
	
	@GetMapping("/issue/search")
	public String viewListIssuePage(Model model, SearchIssueVO searchIssueVO) {
		
		IssueListVO issueListVO = this.issueService.searchIssue(searchIssueVO);
		model.addAttribute("issueList", issueListVO);
		model.addAttribute("searchIssueVO", searchIssueVO);
		return "issue/issuelist";
	}
	
	@GetMapping("/issue/view")
	public String viewIssueDetailPage(@RequestParam String isId, Model model) {
		IssueVO issueVO = this.issueService.getOneIssue(isId, true);
		model.addAttribute("issueVO", issueVO);
		return "issue/issueview";
		
	}
}
