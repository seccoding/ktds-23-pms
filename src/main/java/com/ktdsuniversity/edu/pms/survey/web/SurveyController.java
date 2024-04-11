package com.ktdsuniversity.edu.pms.survey.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionService;

@RestController
public class SurveyController {
	
	@Autowired
	private SurveyQuestionService surveyQuestionService;
	
	@GetMapping("/survey/write")
	public String viewSurveyWritePage() {
		return "survey/surveywrite";
	}
	
//	@PostMapping("/survey/write")
//	public String doSurveyWrite(SurveyQuestionVO serveyQuestionVO, Model model) {
//		
//	}

}
