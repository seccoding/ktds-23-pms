package com.ktdsuniversity.edu.pms.survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.survey.dao.SurveyQuestionDao;

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService {
	
	@Autowired
	private SurveyQuestionDao surveyQuestionDao;

}
