package com.ktdsuniversity.edu.pms.survey.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.project.vo.ProjectSurveyQuestionVO;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

public interface SurveyQuestionService {
	
	public SurveyListVO getAllSurvey();
	
	public SurveyQuestionVO getOneSurvey(String prjId);
	
	public boolean createNewSurveyQuestion(SurveyQuestionVO surveyQuestionVO);

	public boolean createSurveyBody(SurveyQuestionVO surveyQuestionVO);

	public List<SurveyQuestionVO> getAllSurveys(SearchSurveyVO searchSurveyVO);
	
	public boolean modifyOneSurvey(SurveyQuestionVO surveyQuestionVO);

	public boolean modifyOneSurveyExceptBody(SurveyQuestionVO surveyQuestionVO);

	public boolean writeNewSurvey(SurveyReplyVO surveyReplyVO);

	public SurveyQuestionVO getOneSurveyForWrite(String prjId);

	public SurveyListVO getAllQuestions();

	public List<SurveyQuestionVO> getAllQuestions(SurveyQuestionVO surveyQuestionVO);

	public SurveyListVO searchProject(SearchSurveyVO searchSurveyVO);

	public boolean deleteOneSurvey(SurveyQuestionVO surveyQuestionVO);

	public List<SurveyQuestionVO> getAllQuestionsByPrjId(String prjId);

	public SurveyListVO searchAllQuestions(SurveyQuestionVO surveyQuestionVO);

	public SurveyListVO searchTeammate(SearchSurveyVO searchSurveyVO);

}
