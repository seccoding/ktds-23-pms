package com.ktdsuniversity.edu.pms.survey.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;

public interface SurveyQuestionDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.survey.dao.SurveyQuestionDao";
	
	public List<SurveyQuestionVO> getAllSurvey(SurveyQuestionVO surveyQuestionVO);
	
	public int insertNewSurveyQuestion(SurveyQuestionVO surveyQuestionVO);
	
	public SurveyQuestionVO selectOneSurvey(int prjId);
	
	public int updateOneSurvey(SurveyQuestionVO surveyQuestionVO);
	
	public int updateOneSurveyQuestion(SurveyQuestionVO surveyQuestionVO);
	
	public int deleteOneSurvey(int prjId);
	
	public int deleteOneSurveyQuestion(int srvId);

}
