package com.ktdsuniversity.edu.pms.survey.service;

import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

public interface SurveyReplyService {

	boolean responseSurvey(SurveyReplyVO surveyReplyVO);

	public SurveyListVO getAllReplys(String prjId);

}
