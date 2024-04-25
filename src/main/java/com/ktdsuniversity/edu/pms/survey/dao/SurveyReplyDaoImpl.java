package com.ktdsuniversity.edu.pms.survey.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

@Repository
public class SurveyReplyDaoImpl extends SqlSessionDaoSupport implements SurveyReplyDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertSurveyAnswer(SurveyReplyVO surveyReplyVO) {
		return getSqlSession().insert(SurveyReplyDao.NAME_SPACE + ".insertSurveyAnswer", surveyReplyVO);
	}

	@Override
	public List<SurveyReplyVO> getAllReplys(String prjId) {
		return getSqlSession().selectList(SurveyReplyDao.NAME_SPACE + ".getAllReplys", prjId);
	}
	
}
