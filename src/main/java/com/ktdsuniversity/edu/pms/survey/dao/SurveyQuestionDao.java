package com.ktdsuniversity.edu.pms.survey.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.project.vo.ProjectSurveyQuestionVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
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

	public List<SurveyQuestionVO> getAllQuestions();

	public List<SurveyQuestionVO> getAllQuestions(SurveyQuestionVO surveyQuestionVO);

	public int searchProjectCount(SearchSurveyVO searchSurveyVO);

	public List<ProjectSurveyQuestionVO> searchBoard(SearchSurveyVO searchSurveyVO);

	public int deleteOneSurvey(SurveyQuestionVO surveyQuestionVO);

	public List<SurveyQuestionVO> getAllQuestionsByPrjId(String prjId);

	public List<ProjectSurveyQuestionVO> searchTeammate(SearchSurveyVO searchSurveyVO);

	public SurveyQuestionVO getOneSrvQuestionVOBySrvId(String srvId);

	public int getRoleNoneCount(String prjId);

	public int surveyQuestionDao(String prjId);

	

}
