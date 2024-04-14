package com.ktdsuniversity.edu.pms.survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.survey.dao.SurveyQuestionDao;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService {
	
	@Autowired
	private SurveyQuestionDao surveyQuestionDao;

	@Override
	public SurveyListVO getAllSurvey() {
		List<SurveyQuestionVO> surveyList = this.surveyQuestionDao.getAllSurvey();
		
		SurveyListVO surveyListVO = new SurveyListVO();
		surveyListVO.setSurveyList(surveyList);
		
		return surveyListVO;
	}

	@Transactional
	@Override
	public boolean createNewSurveyQuestion(SurveyQuestionVO surveyQuestionVO) {
		int insertedCount = this.surveyQuestionDao.insertNewSurveyQuestion(surveyQuestionVO);
		
		return insertedCount > 0;
	}
	


}
