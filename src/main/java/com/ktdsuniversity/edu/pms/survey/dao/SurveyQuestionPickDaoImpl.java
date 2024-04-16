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
		return getSqlSession().insert(SurveyQuestionPickDao.NAME_SPACE + "createNewAnswer", surveyQuestionPickVO);
	}

}
