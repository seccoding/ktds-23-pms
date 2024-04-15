package com.ktdsuniversity.edu.pms.knowledge.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeService;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class KnowledgeController {
	
	@Autowired
	private KnowledgeService knowledgeService;
	
	// 목록 조회
	@ResponseBody
	@GetMapping("/ajax/knowledge/list")
	public AjaxResponse viewKnowledgeListPage() {
		
		KnowledgeListVO knowledgeListVO = this.knowledgeService.getAllKnowledge();
		
		return new AjaxResponse().append("knowledge", knowledgeListVO);
	}
	
	
	// 게시글별 상세 조회
	@ResponseBody
	@GetMapping("/ajax/knowledge/detail")
	public AjaxResponse viewDetailKnowledgeListPage(@RequestParam String knlId, Model model) {
		
		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId, true);
		
		// knowledge/detail 페이지에 데이터를 전송.
		model.addAttribute("knowledgeVO", knowledgeVO);
				
		return new AjaxResponse().append("oneKnowledge", knowledgeVO);
	}
	
	
	// 글 작성 페이지
	@GetMapping("/knowledge/write")
	public String viewKnowledgeWritePage() {
		
		return "redirect:/knowledge/write";
	}
	
	
	// 글 작성 유효성
	@PostMapping("/knowledge/write") 
	public String doKnowledgeWrite(KnowledgeVO knowledgeVO, @RequestParam MultipartFile file,
									@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,  Model model) {
		
		// 검사
//		boolean isNotEmptyTitle = ValidationUtils.notEmpty(KnowledgeVO.getKnlTtl());
//		boolean isNotEmptyContent = ValidationUtils.notEmpty(KnowledgeVO.getKnlCntnt());
		
//		if(!isNotEmptyTitle) {
//			model.addAttribute("errorMessage", "제목은 필수 입력 값입니다!");
//			model.addAttribute("knowledgeVO", knowledgeVO);
//			return "knowledge/knowledgewrite";
//		}
//		
//		if(!isNotEmptyContent) {
//			model.addAttribute("errorMessage", "내용은 필수 입력 값입니다!");
//			model.addAttribute("knowledgeVO", knowledgeVO);
//			return "knowledge/knowledgewrite";
//		}
		
		boolean isCreateSuccess = this.knowledgeService.createNewKnowledge(knowledgeVO, file);
		
		if(isCreateSuccess) {
			System.out.println("글 등록 성공!");
		} else {
			System.out.println("글 등록 실패!");
		}
		
		return "redirect:/knowledge/list";
	}

	// 글 수정 페이지
	@GetMapping("/knowledge/modify/{knlId}")
	public String doKnowledgeModify(@PathVariable String knlId, Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId, true);
		
		if(! knowledgeVO.getKnlId().equals(employeeVO.getEmpId()) && ! employeeVO.getAdmnCode().equals(301)) {
//			throw new PageNotFoundException();
		}
		
		// 게시글의 정보를 화면에 보내준다.
		model.addAttribute("knowledgeVO", knowledgeVO);
		
		return "knowledge/knowledgemodify";
	
	}
	
	
	// 글 수정 작성 페이지
	@PostMapping("/knowledge/modify/{knlId}")
	public String viewKnowledgeModify(@PathVariable String knlId, Model model, @RequestParam MultipartFile file,
								KnowledgeVO knowledgeVO, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		KnowledgeVO originKnowledgeVO = this.knowledgeService.getOneKnowledge(knlId, true);
		
		if(! originKnowledgeVO.getKnlId().equals(employeeVO.getEmpId()) && ! employeeVO.getAdmnCode().equals(301)) {
//			throw new PageNotFoundException();
		}
		
		// 수동 검사
//		boolean isNotEmptyTitle = ValidationUtils.notEmpty(KnowledgeVO.getKnlTtl());
//		boolean isNotEmptyContent = ValidationUtils.notEmpty(KnowledgeVO.getKnlCntnt());
		
//		if(!isNotEmptyTitle) {
//		model.addAttribute("errorMessage", "제목은 필수 입력 값입니다!");
//		model.addAttribute("knowledgeVO", knowledgeVO);
//		return "knowledge/knowledgemodify";
//	}
//	
//		if(!isNotEmptyContent) {
//		model.addAttribute("errorMessage", "내용은 필수 입력 값입니다!");
//		model.addAttribute("knowledgeVO", knowledgeVO);
//		return "knowledge/knowledgemodify";
//	}
		
		return null;    // TO DO!@!@
	}
	
	
	// 글 삭제
	@PostMapping("/knowledge/delete/{knlId}")
	public String doKnowledgeDelete(@PathVariable String knlId, Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
