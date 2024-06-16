package com.ktdsuniversity.edu.pms.survey.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

public interface SurveyReplyDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.survey.dao.SurveyReplyDao";

	public int insertSurveyAnswer(SurveyReplyVO surveyReplyVO);

	public List<SurveyReplyVO> getAllReplies(SurveyReplyVO surveyReplyVO);

	public List<String> getDoneEmpIdList(String prjId);

	public List<SurveyReplyVO> getallDescriptiveTypeAnswer(String prjId);

}
