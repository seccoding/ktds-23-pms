package com.ktdsuniversity.edu.pms.survey.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionService;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;

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
	
	@GetMapping("/survey/view")
	public String viewSurveyDetailPage(@RequestParam String prjId, Model model) {
		SurveyQuestionVO surveyQuestionVO = this.surveyQuestionService.getOneSurvey(prjId);
		model.addAttribute("surveyQuestionVO", surveyQuestionVO);
		return "survey/surveyview";
		
	}
	
	@GetMapping("/survey/write")
	public String viewSurveyWritePage(@RequestParam String prjId, Model model) {
		SurveyQuestionVO surveyQuestionVO = this.surveyQuestionService.getOneSurvey(prjId);
		model.addAttribute("surveyQuestionVO", surveyQuestionVO);
		return "survey/surveywrite";
	}
	
//	@PostMapping("/survey/write")
//	public String doSurveyWrite(SurveyQuestionVO serveyQuestionVO, Model model) {
//		
//	}

}
