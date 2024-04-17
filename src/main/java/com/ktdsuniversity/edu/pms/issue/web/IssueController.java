package com.ktdsuniversity.edu.pms.issue.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.issue.service.IssueService;
import com.ktdsuniversity.edu.pms.issue.vo.IssueListVO;
import com.ktdsuniversity.edu.pms.issue.vo.IssueVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.StringUtil;

@Controller
public class IssueController {
	
	private Logger logger = LoggerFactory.getLogger(IssueController.class);

	@Autowired
	private IssueService issueService;
	
	@Autowired
	private RequirementService requirementService;
	
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
		
		List<RequirementVO> requirementList = requirementService.getAllRequirement();
		model.addAttribute("requirement", requirementList);
		return "/issue/issuewrite";
	}
	
	@PostMapping("/issue/write")
	public String doWriteIssue(IssueVO issueVO, Model model, @RequestParam MultipartFile file) {
		
		boolean isEmptyTitle = StringUtil.isEmpty(issueVO.getIsTtl());
		boolean isEmptyContent = StringUtil.isEmpty(issueVO.getIsCntnt());
		
		if (isEmptyTitle) {
			model.addAttribute("errorMessage", "제목은 필수 입력 값입니다!");
			model.addAttribute("issueVO", issueVO);
			return "/issue/issuewrite";
		}
		
		if (isEmptyContent) {
			model.addAttribute("errorMessage", "내용은 필수 입력 값입니다!");
			model.addAttribute("issueVO", issueVO);
			return "/issue/issuewrite";
		}
		// 세션 추가 해야함
		issueVO.setCrtrId("system01");
		issueVO.setIsMngr("system01");
		
		boolean isSuccess = this.issueService.createNewIssue(issueVO, file);
		if (isSuccess) {
			logger.info("글 등록 성공!");
		}
		else {
			logger.info("글 등록 실패!");
		}
		
		String isId = issueVO.getIsId();
		
		return "redirect:/issue?isId=" + isId;
	}
	
	@GetMapping("/issue/modify/{isId}")
	public String viewModifyIssuePage(@PathVariable String isId, Model model) {
		
		IssueVO issueVO = this.issueService.getOneIssue(isId, false);
		
		model.addAttribute("issueVO", issueVO);
		return "issue/issuemodify";
	}
	
	@PostMapping("/issue/modify/{isId}")
	public String doModifyIssue(@PathVariable String isId, Model model, @RequestParam MultipartFile file, IssueVO issueVO) {

		boolean isEmptyTitle = StringUtil.isEmpty(issueVO.getIsTtl());
		boolean isEmptyContent = StringUtil.isEmpty(issueVO.getIsCntnt());
		
		if (isEmptyTitle) {
			model.addAttribute("errorMessage", "제목은 필수 입력 값입니다.");
			model.addAttribute("issueVO", issueVO);
			return "issue/issuemofidy";
		}
		
		if (isEmptyContent) {
			model.addAttribute("errorMessage", "내용은 필수 입력 값입니다.");
			model.addAttribute("issueVO", issueVO);
			return "board/boardmodify";
		}
		
		issueVO.setIsId(isId);
		issueVO.setCrtrId("system01");
		issueVO.setMdfrId("system01");
		
		boolean isUpdateSuccess = this.issueService.updateOneIssue(issueVO, file);
		
		if (isUpdateSuccess) {
			logger.info("수정 성공했습니다!");
		}
		else {
			logger.info("수정 실패했습니다!");
		}
		return "redirect:/issue/view?isId=" + isId;
	}
}
