package com.ktdsuniversity.edu.pms.survey.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;

public interface SurveyQuestionDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.survey.dao.SurveyQuestionDao";
	
	public List<SurveyQuestionVO> getAllSurvey();
	
	public int insertNewSurveyQuestion(SurveyQuestionVO surveyQuestionVO);
	
	public SurveyQuestionVO selectOneSurvey(String prjId);
	
	public int updateOneSurvey(SurveyQuestionVO surveyQuestionVO);
	
	public int updateOneSurveyQuestion(SurveyQuestionVO surveyQuestionVO);
	
	public int deleteOneSurvey(String prjId);
	
	public int deleteOneSurveyQuestion(String srvId);

}
