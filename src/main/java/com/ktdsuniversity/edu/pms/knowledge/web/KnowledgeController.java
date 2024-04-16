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
import com.ktdsuniversity.edu.pms.utils.StringUtil;

@Controller
public class KnowledgeController {
	
	@Autowired
	private KnowledgeService knowledgeService;
	
	// 목록 조회

	@GetMapping("/knowledge/list")
	public String viewKnowledgeListPage(Model model) {
		
		KnowledgeListVO knowledgeList = this.knowledgeService.getAllKnowledge(); 
		model.addAttribute("knowledgeList", knowledgeList);
		return  "/knowledge/knowledgelist";
	}
	
	
	// 게시글별 상세 조회
	@GetMapping("/knowledge/view")
	public String viewDetailKnowledgeListPage(@RequestParam String knlId, Model model) {
		
		System.out.println(knlId);
		
		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId, true, true);
		
		// knowledge/detail 페이지에 데이터를 전송.
		model.addAttribute("knowledgeVO", knowledgeVO);
				
//		return new AjaxResponse().append("oneKnowledge", knowledgeVO);
		return "/knowledge/knowledgeview";
	}
	
	
	// 글 작성 페이지
	@GetMapping("/knowledge/write")
	public String viewKnowledgeWritePage() {
		
		return "/knowledge/knowledgewrite";
	}
	
	
	// 글 작성 
	@PostMapping("/knowledge/write") 
	public String doKnowledgeWrite(KnowledgeVO knowledgeVO, @RequestParam MultipartFile file,
									@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,  Model model) {
		
		// 검사 -> Validator로 추후 수정 가능
		boolean isEmptyTitle = StringUtil.isEmpty(knowledgeVO.getKnlTtl());
		boolean isEmptyContent = StringUtil.isEmpty(knowledgeVO.getKnlCntnt());
		
		if(isEmptyTitle) {
		model.addAttribute("errorMessage", "제목은 필수 입력 값입니다!");
		model.addAttribute("knowledgeVO", knowledgeVO);
		return "knowledge/knowledgemodify";
	}
	
		if(isEmptyContent) {
		model.addAttribute("errorMessage", "내용은 필수 입력 값입니다!");
		model.addAttribute("knowledgeVO", knowledgeVO);
		return "knowledge/knowledgemodify";
	}
		
		boolean isCreateSuccess = this.knowledgeService.createNewKnowledge(knowledgeVO, file);
		
		if(isCreateSuccess) {
			System.out.println("글 등록 성공!");
		} else {
			System.out.println("글 등록 실패!");
		}
		
		String knlId = knowledgeVO.getKnlId();
		return "redirect:/knowledge/list?knowledgeid=" + knlId;
	}

	// 글 수정 페이지
	@GetMapping("/knowledge/modify/{knlId}")
	public String doKnowledgeModify(@PathVariable String knlId, Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId, true, true);
		
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
		
		KnowledgeVO originKnowledgeVO = this.knowledgeService.getOneKnowledge(knlId, true, true);
		
		
		// TO DO !!  1. 유저 검증 코드 부분 
//		if(! originKnowledgeVO.getKnlId().equals(employeeVO.getEmpId()) && ! employeeVO.getAdmnCode().equals(301)) {
//			throw new PageNotFoundException();
//		}
		
		// 수동 검사 -> Validator로 추후 수정 가능
		boolean isEmptyTitle = StringUtil.isEmpty(knowledgeVO.getKnlTtl());
		boolean isEmptyContent = StringUtil.isEmpty(knowledgeVO.getKnlCntnt());
		
		if(isEmptyTitle) {
		model.addAttribute("errorMessage", "제목은 필수 입력 값입니다!");
		model.addAttribute("knowledgeVO", knowledgeVO);
		return "knowledge/knowledgemodify";
	}
	
		if(isEmptyContent) {
		model.addAttribute("errorMessage", "내용은 필수 입력 값입니다!");
		model.addAttribute("knowledgeVO", knowledgeVO);
		return "knowledge/knowledgemodify";
	}
		knowledgeVO.setKnlId(knlId);
		
		boolean isUpdateSuccess = this.knowledgeService.updateOneKnowledge(knowledgeVO, file);
		
		if(isUpdateSuccess) {
			System.out.println("수정 성공!");
		} else {
			System.out.println("수정 실패!");
		}
		
		return "redirect:/knowledge/list";
	}
	
	
	// 글 삭제
	@PostMapping("/knowledge/delete/{knlId}")
	public String doKnowledgeDelete(@PathVariable String knlId, Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		KnowledgeVO originKnowledgeVO = this.knowledgeService.getOneKnowledge(knlId, true, true);
		
		boolean isDeleteSuccess = this.knowledgeService.deleteOneKnowledge(knlId);
		
		if(isDeleteSuccess) {
			System.out.println("삭제 성공!");
		} else {
			System.out.println("삭제 실패!");
		}
		
		
		return "redirect:/knowledge/list";
	}
	
	
	// 파일 업로드
	
	// 파일 다운
	
	
	
	// 엑셀 일괄 등록
//	public AjaxResponse doExcelUpload(@RequestParam MultipartFile multipartFile) {
//		
//		boolean isSuccess = this.knowledgeService.
//		
//		return new AjaxResponse().append("result", isSuccess);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	

