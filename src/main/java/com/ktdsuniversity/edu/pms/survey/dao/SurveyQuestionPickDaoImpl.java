package com.ktdsuniversity.edu.pms.survey.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionPickVO;

@Repository
public class SurveyQuestionPickDaoImpl extends SqlSessionDaoSupport implements SurveyQuestionPickDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int createNewAnswer(SurveyQuestionPickVO surveyQuestionPickVO) {
		return getSqlSession().insert(SurveyQuestionPickDao.NAME_SPACE + ".createNewAnswer", surveyQuestionPickVO);
	}

	@Override
	public SurveyQuestionPickVO getOneAnswer(String sqpId) {
		return getSqlSession().selectOne(SurveyQuestionPickDao.NAME_SPACE + ".getOneAnswer", sqpId);
	}

	@Override
	public int modifyOneAnswer(SurveyQuestionPickVO surveyQuestionPickVO) {
		return getSqlSession().update(SurveyQuestionPickDao.NAME_SPACE + ".modifyOneAnswer", surveyQuestionPickVO);
	}

//	@Override
//	public SurveyQuestionPickVO getOneAnswerSequence(String sqpId) {
//		return getSqlSession().selectOne(SurveyQuestionPickDao.NAME_SPACE + ".getOneAnswerSequence", sqpId);
//	}
//
//	@Override
//	public int modifyOneAnswerSequence(SurveyQuestionPickVO surveyQuestionPickVO) {
//		return getSqlSession().update(SurveyQuestionPickDao.NAME_SPACE + ".modifyOneAnswerSequence", surveyQuestionPickVO);
//	}

}
