package com.ktdsuniversity.edu.pms.survey.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionPickService;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class SurveyAnswerController {
	
	@Autowired
	private SurveyQuestionPickService surveyQuestionPickService;
	
	

}
