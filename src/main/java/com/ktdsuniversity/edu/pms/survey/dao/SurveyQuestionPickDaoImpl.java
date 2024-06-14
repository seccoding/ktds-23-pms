package com.ktdsuniversity.edu.pms.survey.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

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

	@Override
	public SurveyQuestionPickVO getOneAnswerSequence(String sqpId) {
		return getSqlSession().selectOne(SurveyQuestionPickDao.NAME_SPACE + ".getOneAnswerSequence", sqpId);
	}

	@Override
	public int modifyOneAnswerSequence(SurveyQuestionPickVO surveyQuestionPickVO) {
		return getSqlSession().update(SurveyQuestionPickDao.NAME_SPACE + ".modifyOneAnswerSequence", surveyQuestionPickVO);
	}

	@Override
	public List<SurveyQuestionPickVO> getAllPicks(SearchSurveyQuestionPickVO searchSurveyQuestionPickVO) {
		return getSqlSession().selectList(SurveyQuestionPickDao.NAME_SPACE + ".getAllPicks", searchSurveyQuestionPickVO);
	}

	@Override
	public List<SurveyQuestionPickVO> getAllPicks() {
		return getSqlSession().selectList(SurveyQuestionPickDao.NAME_SPACE + ".getAllPicks");
	}

	@Override
	public List<SurveyQuestionPickVO> getAllPicks(SurveyQuestionPickVO surveyQuestionPickVO) {
		return getSqlSession().selectList(SurveyQuestionPickDao.NAME_SPACE + ".getAllPicks", surveyQuestionPickVO);
	}

	@Override
	public int deleteOneAnswer(SurveyQuestionPickVO surveyQuestionPickVO) {
		return getSqlSession().update(SurveyQuestionPickDao.NAME_SPACE + ".deleteOneAnswer", surveyQuestionPickVO);
	}

	@Override
	public int updateAllSqpCountPlusOneByReply(SurveyReplyVO surveyReplyVO) {
		return getSqlSession().update(SurveyQuestionPickDao.NAME_SPACE + ".updateAllSqpCountPlusOneByReply", surveyReplyVO);
	}

}
