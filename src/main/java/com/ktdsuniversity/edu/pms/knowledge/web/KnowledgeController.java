package com.ktdsuniversity.edu.pms.knowledge.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeService;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.StringUtil;

@Controller
public class KnowledgeController {
	
	Logger logger = LoggerFactory.getLogger(KnowledgeController.class);
	
	@Autowired
	private KnowledgeService knowledgeService;
	
	@Autowired
	private RequirementService requirementService;
	
	@Autowired
	private ProjectService projectService;
	
	
	// 목록 조회
	@GetMapping("/knowledge")
	public String viewKnowledgeListPage(Model model) {
		
		KnowledgeListVO knowledgeList = this.knowledgeService.getAllKnowledge(); 
		model.addAttribute("knowledgeList", knowledgeList);
		return  "/knowledge/knowledgelist";
	}
	
	
	// 게시글별 상세 조회
	@GetMapping("/knowledge/view")
	public String viewDetailKnowledgeListPage(@RequestParam String knlId, Model model) {
		
		logger.info(knlId);
		
		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId, true);
		
		// knowledge/detail 페이지에 데이터를 전송.
		model.addAttribute("knowledgeVO", knowledgeVO);
				
//		return new AjaxResponse().append("oneKnowledge", knowledgeVO);
		return "/knowledge/knowledgeview";
	}
	
	
	
	// 글 작성 페이지
	@GetMapping("/knowledge/write")
	public String viewKnowledgeWritePage(Model model) {
		
		ProjectListVO projectList = projectService.getAllProject();
		List<RequirementVO> requirementList = requirementService.getAllRequirement();
		
		model.addAttribute("requirement", requirementList);
		
		return "/knowledge/knowledgewrite";
	}
	
	
	// 글 작성 // TO DO!! @SessionAttribute 추가 
	@PostMapping("/knowledge/write") 
	public String doKnowledgeWrite(KnowledgeVO knowledgeVO,  @RequestParam MultipartFile file,
										 Model model) {
		
		// 검사 -> Validator로 추후 수정 가능
		boolean isEmptyTitle = StringUtil.isEmpty(knowledgeVO.getKnlTtl());
		boolean isEmptyContent = StringUtil.isEmpty(knowledgeVO.getKnlCntnt());
		
		if(isEmptyTitle) {
		model.addAttribute("errorMessage", "제목은 필수 입력 값입니다!");
		model.addAttribute("knowledgeVO", knowledgeVO);
		return "/knowledge/knowledgewrite";
	}
	
		if(isEmptyContent) {
		model.addAttribute("errorMessage", "내용은 필수 입력 값입니다!");
		model.addAttribute("knowledgeVO", knowledgeVO);
		return "knowledge/knowledgewrite";
	}
		// 세션 검증 시 수정해야 함!!!
		knowledgeVO.setCrtrId("system01");
		
		boolean isCreateSuccess = this.knowledgeService.createNewKnowledge(knowledgeVO, file);
		if(isCreateSuccess) { 
			logger.info("글 등록이 완료되었습니다.");
		} else {
			logger.info("글 등록이 실패되었습니다.");
		}
		
		String knlId = knowledgeVO.getKnlId();
		
		return "redirect:/knowledge?knlId=" + knlId;
		
	}
	
	// 추천하기 // TO DO!!! @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO
	@ResponseBody
	@GetMapping("/ajax/Knowledge/recommend/{knlId}")
	public AjaxResponse doRecommendKnowledge (@PathVariable String knlId) {
		
//		boolean isSuccess = this.knowledgeService.recommendOneKnowledge(knlId);
		
		int recommendCount = this.knowledgeService.recommendOneKnowledge(knlId);
		
//		if(isSuccess) {
//			logger.info("추천!");
//		}
		
		
		return new AjaxResponse().append("result", recommendCount);
	}

	// 글 수정 페이지 // @SessionAttribute 추가 예정
	@GetMapping("/knowledge/modify/{knlId}")
	public String viewKnowledgeModify(@PathVariable String knlId, Model model) {
		
		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knlId, false);
		
//		if(! knowledgeVO.getKnlId().equals(employeeVO.getEmpId()) && ! employeeVO.getAdmnCode().equals(301)) {
//			throw new PageNotFoundException();
//		}
		
		// 게시글의 정보를 화면에 보내준다.
		model.addAttribute("knowledgeVO", knowledgeVO);
		
		return "knowledge/knowledgemodify";
	
	}
	
	
	// 글 수정 작성 페이지
	@PostMapping("/knowledge/modify/{knlId}") // TO DO!!! @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO 
	public String doKnowledgeModify(@PathVariable String knlId, Model model, @RequestParam MultipartFile file,
								KnowledgeVO knowledgeVO) {
		
		KnowledgeVO originKnowledgeVO = this.knowledgeService.getOneKnowledge(knlId, false);
		
		
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
		// Command Object 에는 전달된 knlId가 없으니 @PathVariable로 전달된 knlId를 세팅해준다.
		knowledgeVO.setKnlId(knlId);
		
		boolean isUpdateSuccess = this.knowledgeService.updateOneKnowledge(knowledgeVO, file);
		
		if(isUpdateSuccess) {
			logger.info("수정이 완료되었습니다!");
		} else {
			logger.info("수정이 실패되었습니다!");
		}
		
		return "redirect:/knowledge/view?knlId=" + knlId;
	}
	
	
	// 글 삭제
	@GetMapping("/knowledge/delete/{knlId}") // TO DO!!! @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO
	public String doKnowledgeDelete(@PathVariable String knlId, Model model) {
		
		boolean isDeleteSuccess = this.knowledgeService.deleteOneKnowledge(knlId);
		
//		KnowledgeVO originKnowledgeVO = this.knowledgeService.getOneKnowledge(knlId, false);
		
		// TO DO !!  1. 유저 검증 코드 부분 
//		if(! originKnowledgeVO.getKnlId().equals(employeeVO.getEmpId()) && ! employeeVO.getAdmnCode().equals(301)) {
//			throw new PageNotFoundException();
//		}
		
		if(isDeleteSuccess) {
			logger.info("삭제가 성공되었습니다!");
		} else {
			logger.info("삭제가 실패되었습니다!");
		}
		
		return "redirect:/knowledge";
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
	

