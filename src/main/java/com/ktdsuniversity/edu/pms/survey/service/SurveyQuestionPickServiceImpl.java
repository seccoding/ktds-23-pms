package com.ktdsuniversity.edu.pms.survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.survey.dao.SurveyQuestionPickDao;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;

@Service
public class SurveyQuestionPickServiceImpl implements SurveyQuestionPickService {

	@Autowired
	private SurveyQuestionPickDao surveyQuestionPickDao;

	@Transactional
	@Override
	public boolean createNewAnswer(SurveyQuestionPickVO surveyQuestionPickVO) {
		return this.surveyQuestionPickDao.createNewAnswer(surveyQuestionPickVO) > 0;
	}

	@Transactional
	@Override
	public boolean modifyOneAnswer(SurveyQuestionPickVO surveyQuestionPickVO) {
//		SurveyQuestionPickVO originalSurveyQuestionPickVO = this.surveyQuestionPickDao
//				.getOneAnswer(surveyQuestionPickVO.getSqpId());

//		if (!originalSurveyQuestionPickVO.getSqpId().equals(surveyQuestionPickVO.getSqpId())) {
//			throw new PageNotFoundException();
//		}
		return this.surveyQuestionPickDao.modifyOneAnswer(surveyQuestionPickVO) > 0;
	}

	@Transactional
	@Override
	public boolean modifyOneAnswerSequence(SurveyQuestionPickVO surveyQuestionPickVO) {
//		SurveyQuestionPickVO originalSurveyQuestionPickVO = this.surveyQuestionPickDao
//				.getOneAnswerSequence(surveyQuestionPickVO.getSqpId());
//
//		if (!originalSurveyQuestionPickVO.getSqpId().equals(surveyQuestionPickVO.getSqpId())) {
//			throw new PageNotFoundException();
//		}
		return this.surveyQuestionPickDao.modifyOneAnswerSequence(surveyQuestionPickVO) > 0;
	}

	@Override
	public List<SurveyQuestionPickVO> getAllPicks(SearchSurveyQuestionPickVO searchSurveyQuestionPickVO) {
		return this.surveyQuestionPickDao.getAllPicks(searchSurveyQuestionPickVO);
	}

	@Override
	public SurveyListVO getAllPicks() {
		List<SurveyQuestionPickVO> pickList = this.surveyQuestionPickDao.getAllPicks();

		SurveyListVO surveyListVO = new SurveyListVO();
		surveyListVO.setPickList(pickList);

		return surveyListVO;
	}

	@Override
	public List<SurveyQuestionPickVO> getAllPicks(SurveyQuestionPickVO surveyQuestionPickVO) {
		return this.surveyQuestionPickDao.getAllPicks(surveyQuestionPickVO);
	}

	@Transactional
	@Override
	public boolean deleteOneSurveyPick(SurveyQuestionPickVO surveyQuestionPickVO) {
//		SurveyQuestionPickVO originalSurveyQuestionPickVO = this.surveyQuestionPickDao
//				.getOneAnswer(surveyQuestionPickVO.getSqpId());

//		if (!originalSurveyQuestionPickVO.getSqpId().equals(surveyQuestionPickVO.getSqpId())) {
//			throw new PageNotFoundException();
//		}
		return this.surveyQuestionPickDao.deleteOneAnswer(surveyQuestionPickVO) > 0;
	}

	@Override
	public SurveyListVO searchAllPicks(SurveyQuestionPickVO surveyQuestionPickVO) {
		List<SurveyQuestionPickVO> pickList = this.surveyQuestionPickDao.getAllPicks(surveyQuestionPickVO);

		SurveyListVO surveyListVO = new SurveyListVO();
		surveyListVO.setPickList(pickList);

		return surveyListVO;
	}

}
