package com.ktdsuniversity.edu.pms.survey.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.pms.survey.dao.SurveyReplyDao;

public class SurveyReplyServiceImpl implements SurveyReplyService {
	
	@Autowired
	private SurveyReplyDao surveyReplyDao;

}
