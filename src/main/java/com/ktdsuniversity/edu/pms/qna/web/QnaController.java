package com.ktdsuniversity.edu.pms.qna.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.knowledge.web.KnowledgeController;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.qna.service.QnaService;
import com.ktdsuniversity.edu.pms.qna.vo.QnaListVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaVO;
import com.ktdsuniversity.edu.pms.qna.vo.SearchQnaVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;

@Controller
public class QnaController {
	
	Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	@Autowired
	private FileHandler fileHandler;
	
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private RequirementService requirementService;

	@Autowired
	private ProjectService projectService;
	
	
	// 전체 리스트 조회
	@GetMapping("/qna")
	public String viewQnaListPage(Model model, SearchQnaVO searchQnaVO) {
		
		ProjectListVO projectList = this.projectService.getAllProject();
		QnaListVO qnaList = this.qnaService.searchAllQna(searchQnaVO);
		
		model.addAttribute("projectList", projectList);
		model.addAttribute("qnaList", qnaList);
		
		
		return "/qna/qnalist";
	}
	
	// 게시글별 상세 조회
	@GetMapping("/qna/view")
	public String viewDetailQnaListPage(@RequestParam String qaId, Model model) {
		
		QnaVO qnaVO = this.qnaService.getOneQna(qaId, true);
		
		// qna/view 페이지에 데이터를 전송.
		model.addAttribute("qnaVO", qnaVO);

		// return new AjaxResponse().append("oneQna", qnaVO);
		return "/qna/qnaview";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
