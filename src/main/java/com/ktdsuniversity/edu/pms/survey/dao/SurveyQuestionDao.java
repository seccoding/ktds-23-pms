package com.ktdsuniversity.edu.pms.survey.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyReplyVO;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

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

	public int insertNewSurvey(SurveyReplyVO surveyReplyVO);

	public SurveyQuestionVO getOneSurveyForWrite(String prjId);

	public List<SurveyQuestionVO> getAllSurveysForWrite(SurveyQuestionVO surveyQuestionVO);

}
