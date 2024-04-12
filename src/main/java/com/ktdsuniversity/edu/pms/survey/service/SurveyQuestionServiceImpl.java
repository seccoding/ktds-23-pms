package com.ktdsuniversity.edu.pms.survey.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		surveyList = surveyList.stream().collect(Collectors.toMap(SurveyQuestionVO::getPrjId, Function.identity(),
				(existing, replacement) -> existing)).values().stream().collect(Collectors.toList());
		
		SurveyListVO surveyListVO = new SurveyListVO();
		surveyListVO.setSurveyList(surveyList);
		
		return surveyListVO;
	}

}
