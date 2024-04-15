package com.ktdsuniversity.edu.pms.survey.service;

import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;

public interface SurveyQuestionService {
	
	public SurveyListVO getAllSurvey();
	
	public boolean createNewSurveyQuestion(SurveyQuestionVO surveyQuestionVO);

}
