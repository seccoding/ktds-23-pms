package com.ktdsuniversity.edu.pms.survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.survey.dao.SurveyQuestionPickDao;

@Service
public class SurveyQuestionPickServiceImpl implements SurveyQuestionPickService {
	
	@Autowired
	private SurveyQuestionPickDao surveyQuestionPickDao;

}
