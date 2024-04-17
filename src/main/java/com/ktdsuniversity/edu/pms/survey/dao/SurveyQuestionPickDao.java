package com.ktdsuniversity.edu.pms.survey.dao;

import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionPickVO;

public interface SurveyQuestionPickDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.survey.dao.SurveyQuestionPickDao";

	public int createNewAnswer(SurveyQuestionPickVO surveyQuestionPickVO);

	public SurveyQuestionPickVO getOneAnswer(String sqpId);

	public int modifyOneAnswer(SurveyQuestionPickVO surveyQuestionPickVO);

}
