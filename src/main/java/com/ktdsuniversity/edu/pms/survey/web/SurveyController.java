package com.ktdsuniversity.edu.pms.survey.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionService;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;

@Controller
public class SurveyController {
	
	@Autowired
	private SurveyQuestionService surveyQuestionService;
	
	@GetMapping("/survey/list")
	public String viewSurveyListPage(Model model) {
		SurveyListVO surveyListVO = this.surveyQuestionService.getAllSurvey();
		model.addAttribute("surveyList", surveyListVO);
		return "survey/surveylist";
	}
	
	
	@GetMapping("/survey/write")
	public String viewSurveyWritePage() {
		return "survey/surveywrite";
	}
	
//	@PostMapping("/survey/write")
//	public String doSurveyWrite(SurveyQuestionVO serveyQuestionVO, Model model) {
//		
//	}

}
