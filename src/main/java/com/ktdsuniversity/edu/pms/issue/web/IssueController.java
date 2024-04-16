package com.ktdsuniversity.edu.pms.issue.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.edu.pms.issue.service.IssueService;
import com.ktdsuniversity.edu.pms.issue.vo.IssueListVO;
import com.ktdsuniversity.edu.pms.issue.vo.IssueVO;

@Controller
public class IssueController {
	
	private Logger logger = LoggerFactory.getLogger(IssueController.class);

	@Autowired
	private IssueService issueService;
	
	@GetMapping("/issue")
	public String viewIssueListPage(Model model) {
		
		IssueListVO issueListVO = this.issueService.getAllIssue();
		model.addAttribute("issueList", issueListVO);
		return "/issue/issuelist";
	}
	
//	@GetMapping("/issue/search")
//	public String viewListIssuePage(Model model, SearchIssueVO searchIssueVO) {
//		
//		IssueListVO issueListVO = this.issueService.searchIssue(searchIssueVO);
//		model.addAttribute("issueList", issueListVO);
//		model.addAttribute("searchIssueVO", searchIssueVO);
//		return "issue/issuelist";
//	}
	
	@GetMapping("/issue/view")
	public String viewIssueDetailPage(@RequestParam String isId, Model model) {
		IssueVO issueVO = this.issueService.getOneIssue(isId, true);
		model.addAttribute("issueVO", issueVO);
		return "issue/issueview";
	}
	
	@GetMapping("/issue/write")
	public String viewIssueWritePage(Model model) {
		return "issue/issuewrite";
	}
	
	@PostMapping("/issue/write")
	public String doWriteIssue(IssueVO issueVO, Model model) {
		boolean isSuccess = this.issueService.createNewIssue(issueVO);
		if (isSuccess) {
			logger.info("글 등록 성공!");
		}
		else {
			logger.info("글 등록 실패!");
		}
		return "redirect:/issue";
	}
}
