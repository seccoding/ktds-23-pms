package com.ktdsuniversity.edu.pms.survey.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

public interface SurveyReplyService {

	boolean responseSurvey(SurveyReplyVO surveyReplyVO);

	SurveyListVO getAllReplies(SurveyReplyVO surveyReplyVO);

	List<String> getDoneEmpIdList(String prjId);

	List<SurveyReplyVO> getallDescriptiveTypeAnswer(String prjId);

	

}
