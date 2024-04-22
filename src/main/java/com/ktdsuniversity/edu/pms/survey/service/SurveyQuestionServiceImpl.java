package com.ktdsuniversity.edu.pms.survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.survey.dao.SurveyQuestionDao;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyVO;
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
	public SurveyQuestionVO getOneSurvey(String prjId) {
		SurveyQuestionVO surveyQuestionVO = this.surveyQuestionDao.selectOneSurvey(prjId);
		if (surveyQuestionVO == null) {
			throw new PageNotFoundException();
		}
		return surveyQuestionVO;
	}
	
	@Transactional
	@Override
	public boolean createNewSurveyQuestion(SurveyQuestionVO surveyQuestionVO) {
		int insertedCount = this.surveyQuestionDao.insertNewSurveyQuestion(surveyQuestionVO);
		
		return insertedCount > 0;
	}

	@Transactional
	@Override
	public boolean createSurveyBody(SurveyQuestionVO surveyQuestionVO) {
		SurveyQuestionVO originalSurveyQuestionVO = this.surveyQuestionDao
							.getOneSurvey(surveyQuestionVO.getSrvId());
		
		if (!originalSurveyQuestionVO.getPrjId().equals(surveyQuestionVO.getPrjId())) {
			throw new PageNotFoundException();
		}
		return this.surveyQuestionDao.insertSurveyBody(surveyQuestionVO) > 0;
	}

	@Transactional
	@Override
	public List<SurveyQuestionVO> getAllSurveys(SearchSurveyVO searchSurveyVO) {
		return this.surveyQuestionDao.getAllSurveys(searchSurveyVO);
	}
	
	@Transactional
	@Override
	public boolean modifyOneSurvey(SurveyQuestionVO surveyQuestionVO) {
		SurveyQuestionVO originalSurveyQuestionVO = this.surveyQuestionDao
							.getOneSurvey(surveyQuestionVO.getSrvId());
		
		if (!originalSurveyQuestionVO.getSrvId().equals(surveyQuestionVO.getSrvId())) {
			throw new PageNotFoundException();
		}
		return this.surveyQuestionDao.modifyOneSurvey(surveyQuestionVO) > 0;
	}

	@Transactional
	@Override
	public boolean modifyOneSurveyExceptBody(SurveyQuestionVO surveyQuestionVO) {
		SurveyQuestionVO originalSurveyQuestionVO = this.surveyQuestionDao
							.getOneSurvey(surveyQuestionVO.getSrvId());
			
		if (!originalSurveyQuestionVO.getSrvId().equals(surveyQuestionVO.getSrvId())) {
		throw new PageNotFoundException();
		}
		return this.surveyQuestionDao.modifyOneSurveyExceptBody(surveyQuestionVO) > 0;
	}

}
