package com.ktdsuniversity.edu.pms.survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.survey.dao.SurveyQuestionPickDao;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionPickVO;

@Service
public class SurveyQuestionPickServiceImpl implements SurveyQuestionPickService {
	
	@Autowired
	private SurveyQuestionPickDao surveyQuestionPickDao;

	@Transactional
	@Override
	public boolean createNewAnswer(SurveyQuestionPickVO surveyQuestionPickVO) {
		return this.surveyQuestionPickDao.createNewAnswer(surveyQuestionPickVO) > 0;
	}

}
