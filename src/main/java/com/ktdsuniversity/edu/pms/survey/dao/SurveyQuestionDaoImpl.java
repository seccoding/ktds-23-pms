package com.ktdsuniversity.edu.pms.survey.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;

@Repository
public class SurveyQuestionDaoImpl extends SqlSessionDaoSupport implements SurveyQuestionDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<SurveyQuestionVO> getAllSurvey(SurveyQuestionVO surveyQuestionVO) {
		return getSqlSession().selectList(SurveyQuestionDao.NAME_SPACE + ".getAllSurvey", surveyQuestionVO);
	}

	@Override
	public int insertNewSurveyQuestion(SurveyQuestionVO surveyQuestionVO) {
		return getSqlSession().insert(SurveyQuestionDao.NAME_SPACE + ".insertNewSurveyQuestion", surveyQuestionVO);
	}

	@Override
	public SurveyQuestionVO selectOneSurvey(int prjId) {
		return getSqlSession().selectOne(SurveyQuestionDao.NAME_SPACE + ".selectOneSurvey", prjId);
	}

	@Override
	public int updateOneSurvey(SurveyQuestionVO surveyQuestionVO) {
		return getSqlSession().update(SurveyQuestionDao.NAME_SPACE + ".updateOneSurvey", surveyQuestionVO);
	}

	@Override
	public int updateOneSurveyQuestion(SurveyQuestionVO surveyQuestionVO) {
		return getSqlSession().update(SurveyQuestionDao.NAME_SPACE + ".updateOneSurveyQuestion", surveyQuestionVO);
	}

	@Override
	public int deleteOneSurvey(int prjId) {
		return getSqlSession().update(SurveyQuestionDao.NAME_SPACE + ".deleteOneSurvey", prjId);
	}

	@Override
	public int deleteOneSurveyQuestion(int srvId) {
		return getSqlSession().update(SurveyQuestionDao.NAME_SPACE + ".deleteOneSurveyQuestion", srvId);
	}

}
