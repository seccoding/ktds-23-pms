package com.ktdsuniversity.edu.pms.survey.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;

public interface SurveyQuestionDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.survey.dao.SurveyQuestionDao";
	
	public List<SurveyQuestionVO> getAllSurvey();
	
	public int insertNewSurveyQuestion(SurveyQuestionVO surveyQuestionVO);
	
	public SurveyQuestionVO selectOneSurvey(String prjId);
	
	public SurveyQuestionVO getOneSurvey(String srvId);

	public int insertSurveyBody(SurveyQuestionVO surveyQuestionVO);

	public List<SurveyQuestionVO> getAllSurveys(SearchSurveyVO searchSurveyVO);
	
	public int modifyOneSurvey(SurveyQuestionVO surveyQuestionVO);

	public int modifyOneSurveyExceptBody(SurveyQuestionVO surveyQuestionVO);

}
