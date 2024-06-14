package com.ktdsuniversity.edu.pms.survey.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

public interface SurveyQuestionPickDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.survey.dao.SurveyQuestionPickDao";

	public int createNewAnswer(SurveyQuestionPickVO surveyQuestionPickVO);

	public SurveyQuestionPickVO getOneAnswer(String sqpId);

	public int modifyOneAnswer(SurveyQuestionPickVO surveyQuestionPickVO);

	public SurveyQuestionPickVO getOneAnswerSequence(String sqpId);

	public int modifyOneAnswerSequence(SurveyQuestionPickVO surveyQuestionPickVO);

	public List<SurveyQuestionPickVO> getAllPicks(SearchSurveyQuestionPickVO searchSurveyQuestionPickVO);
	
	public List<SurveyQuestionPickVO> getAllPicks();

	public List<SurveyQuestionPickVO> getAllPicks(SurveyQuestionPickVO surveyQuestionPickVO);

	public int deleteOneAnswer(SurveyQuestionPickVO surveyQuestionPickVO);

	public int updateAllSqpCountPlusOneByReply(SurveyReplyVO surveyReplyVO);

}
