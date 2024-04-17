package com.ktdsuniversity.edu.pms.survey.service;

import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionPickVO;

public interface SurveyQuestionPickService {

	public boolean createNewAnswer(SurveyQuestionPickVO surveyQuestionPickVO);

	public boolean modifyOneAnswer(SurveyQuestionPickVO surveyQuestionPickVO);

	public boolean modifyOneAnswerSequence(SurveyQuestionPickVO surveyQuestionPickVO);

}
