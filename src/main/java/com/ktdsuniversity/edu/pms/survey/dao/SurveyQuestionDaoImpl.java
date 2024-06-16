package com.ktdsuniversity.edu.pms.survey.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.project.vo.ProjectSurveyQuestionVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

@Repository
public class SurveyQuestionDaoImpl extends SqlSessionDaoSupport implements SurveyQuestionDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<SurveyQuestionVO> getAllSurvey() {
		return getSqlSession().selectList(SurveyQuestionDao.NAME_SPACE + ".getAllSurvey");
	}

	@Override
	public int insertNewSurveyQuestion(SurveyQuestionVO surveyQuestionVO) {
		return getSqlSession().insert(SurveyQuestionDao.NAME_SPACE + ".insertNewSurveyQuestion", surveyQuestionVO);
	}

	@Override
	public SurveyQuestionVO selectOneSurvey(String prjId) {
		return getSqlSession().selectOne(SurveyQuestionDao.NAME_SPACE + ".selectOneSurvey", prjId);
	}

	@Override
	public SurveyQuestionVO getOneSurvey(String srvId) {
		return getSqlSession().selectOne(SurveyQuestionDao.NAME_SPACE + ".getOneSurvey", srvId);
	}

	@Override
	public int insertSurveyBody(SurveyQuestionVO surveyQuestionVO) {
		return getSqlSession().update(SurveyQuestionDao.NAME_SPACE + ".insertSurveyBody", surveyQuestionVO);
	}

	@Override
	public List<SurveyQuestionVO> getAllSurveys(SearchSurveyVO searchSurveyVO) {
		return getSqlSession().selectList(SurveyQuestionDao.NAME_SPACE + ".getAllSurveys", searchSurveyVO);
	}
	
	@Override
	public int modifyOneSurvey(SurveyQuestionVO surveyQuestionVO) {
		return getSqlSession().update(SurveyQuestionDao.NAME_SPACE + ".modifyOneSurvey", surveyQuestionVO);
	}

	@Override
	public int modifyOneSurveyExceptBody(SurveyQuestionVO surveyQuestionVO) {
		return getSqlSession().update(SurveyQuestionDao.NAME_SPACE + ".modifyOneSurveyExceptBody", surveyQuestionVO);
	}

	@Override
	public int insertNewSurvey(SurveyReplyVO surveyReplyVO) {
		return getSqlSession().insert(SurveyQuestionDao.NAME_SPACE + ".insertNewSurvey", surveyReplyVO);
	}

	@Override
	public SurveyQuestionVO getOneSurveyForWrite(String prjId) {
		return getSqlSession().selectOne(SurveyQuestionDao.NAME_SPACE + ".getOneSurveyForWrite", prjId);
	}

	@Override
	public List<SurveyQuestionVO> getAllQuestions() {
		return getSqlSession().selectList(SurveyQuestionDao.NAME_SPACE + ".getAllQuestions");
	}

	@Override
	public List<SurveyQuestionVO> getAllQuestions(SurveyQuestionVO surveyQuestionVO) {
		return getSqlSession().selectList(SurveyQuestionDao.NAME_SPACE + ".getAllQuestions", surveyQuestionVO);
	}

	@Override
	public int searchProjectCount(SearchSurveyVO searchSurveyVO) {
		return getSqlSession().selectOne(SurveyQuestionDao.NAME_SPACE + ".searchProjectCount", searchSurveyVO);
	}

	@Override
	public List<ProjectSurveyQuestionVO> searchBoard(SearchSurveyVO searchSurveyVO) {
		return getSqlSession().selectList(SurveyQuestionDao.NAME_SPACE + ".searchBoard", searchSurveyVO);
	}

	@Override
	public int deleteOneSurvey(SurveyQuestionVO surveyQuestionVO) {
		return getSqlSession().update(SurveyQuestionDao.NAME_SPACE + ".deleteOneSurvey", surveyQuestionVO);
	}

	@Override
	public List<SurveyQuestionVO> getAllQuestionsByPrjId(String prjId) {
		return getSqlSession().selectList(SurveyQuestionDao.NAME_SPACE + ".getAllQuestionsByPrjId", prjId);
	}

	@Override
	public List<ProjectSurveyQuestionVO> searchTeammate(SearchSurveyVO searchSurveyVO) {
		return getSqlSession().selectList(SurveyQuestionDao.NAME_SPACE + ".searchTeammate", searchSurveyVO);
	}

	@Override
	public SurveyQuestionVO getOneSrvQuestionVOBySrvId(String srvId) {
		return getSqlSession().selectOne(SurveyQuestionDao.NAME_SPACE + ".getOneSrvQuestionVOBySrvId", srvId);
	}

	@Override
	public int getRoleNoneCount(String prjId) {
		return getSqlSession().selectOne(SurveyQuestionDao.NAME_SPACE + ".getRoleNoneCount", prjId);
	}

	@Override
	public int surveyQuestionDao(String prjId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(SurveyQuestionDao.NAME_SPACE + ".surveyQuestionDao", prjId);
	}

}
