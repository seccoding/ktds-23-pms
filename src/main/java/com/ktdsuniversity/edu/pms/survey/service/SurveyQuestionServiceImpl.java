package com.ktdsuniversity.edu.pms.survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.project.dao.ProjectDao;
import com.ktdsuniversity.edu.pms.project.vo.ProjectSurveyQuestionVO;
import com.ktdsuniversity.edu.pms.survey.dao.SurveyQuestionDao;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService {

	@Autowired
	private SurveyQuestionDao surveyQuestionDao;
	@Autowired
	private ProjectDao projectDao;

	@Override
	public SurveyListVO getAllSurvey() {
		List<SurveyQuestionVO> surveyList = this.surveyQuestionDao.getAllSurvey();

		SurveyListVO surveyListVO = new SurveyListVO();
		surveyListVO.setSurveyList(surveyList);

		return surveyListVO;
	}

	@Override
	public SurveyQuestionVO getOneSurvey(String prjId) {
		SurveyQuestionVO surveyQuestionVO = this.surveyQuestionDao.selectOneSurvey(prjId);
//		if (surveyQuestionVO == null) {
//			throw new PageNotFoundException();
//		}
		return surveyQuestionVO;
	}

	@Transactional
	@Override
	public boolean createNewSurveyQuestion(SurveyQuestionVO surveyQuestionVO) {
		int insertedCount = this.surveyQuestionDao.insertNewSurveyQuestion(surveyQuestionVO);
		insertedCount = this.projectDao.updateOneProjectSurveySts(surveyQuestionVO.getPrjId());
		return insertedCount > 0;
	}

	@Transactional
	@Override
	public boolean createSurveyBody(SurveyQuestionVO surveyQuestionVO) {
		SurveyQuestionVO originalSurveyQuestionVO = this.surveyQuestionDao.getOneSurvey(surveyQuestionVO.getSrvId());

		if (!originalSurveyQuestionVO.getPrjId().equals(surveyQuestionVO.getPrjId())) {
			throw new PageNotFoundException();
		}
		return this.surveyQuestionDao.insertSurveyBody(surveyQuestionVO) > 0;
	}

	@Override
	public List<SurveyQuestionVO> getAllSurveys(SearchSurveyVO searchSurveyVO) {
		return this.surveyQuestionDao.getAllSurveys(searchSurveyVO);
	}

	@Transactional
	@Override
	public boolean modifyOneSurvey(SurveyQuestionVO surveyQuestionVO) {
		SurveyQuestionVO originalSurveyQuestionVO = this.surveyQuestionDao.getOneSurvey(surveyQuestionVO.getSrvId());

		if (!originalSurveyQuestionVO.getSrvId().equals(surveyQuestionVO.getSrvId())) {
			throw new PageNotFoundException();
		}
		return this.surveyQuestionDao.modifyOneSurvey(surveyQuestionVO) > 0;
	}

	@Transactional
	@Override
	public boolean modifyOneSurveyExceptBody(SurveyQuestionVO surveyQuestionVO) {
		SurveyQuestionVO originalSurveyQuestionVO = this.surveyQuestionDao.getOneSurvey(surveyQuestionVO.getSrvId());

		if (!originalSurveyQuestionVO.getSrvId().equals(surveyQuestionVO.getSrvId())) {
			throw new PageNotFoundException();
		}
		return this.surveyQuestionDao.modifyOneSurveyExceptBody(surveyQuestionVO) > 0;
	}

	@Transactional
	@Override
	public boolean writeNewSurvey(SurveyReplyVO surveyReplyVO) {
		int insertedCount = this.surveyQuestionDao.insertNewSurvey(surveyReplyVO);

		return insertedCount > 0;
	}

	@Transactional
	@Override
	public SurveyQuestionVO getOneSurveyForWrite(String prjId) {
		SurveyQuestionVO surveyQuestionVO = this.surveyQuestionDao.getOneSurveyForWrite(prjId);
		if (surveyQuestionVO == null) {
			throw new PageNotFoundException();
		}
		return surveyQuestionVO;

	}

	@Override
	public SurveyListVO getAllQuestions() {
		List<SurveyQuestionVO> questionList = this.surveyQuestionDao.getAllQuestions();

		SurveyListVO surveyListVO = new SurveyListVO();
		surveyListVO.setQuestionList(questionList);

		return surveyListVO;
	}

	@Override
	public List<SurveyQuestionVO> getAllQuestions(SurveyQuestionVO surveyQuestionVO) {
		return this.surveyQuestionDao.getAllQuestions(surveyQuestionVO);
	}

	@Override
	public SurveyListVO searchProject(SearchSurveyVO searchSurveyVO) {
		int projectCount = this.surveyQuestionDao.searchProjectCount(searchSurveyVO);
		searchSurveyVO.setPageCount(projectCount);

		List<ProjectSurveyQuestionVO> projectList = this.surveyQuestionDao.searchBoard(searchSurveyVO);

		SurveyListVO surveyListVO = new SurveyListVO();
		surveyListVO.setProjectCount(projectCount);
		surveyListVO.setProjectList(projectList);

		return surveyListVO;
	}

	@Transactional
	@Override
	public boolean deleteOneSurvey(SurveyQuestionVO surveyQuestionVO) {
		SurveyQuestionVO originalSurveyQuestionVO = this.surveyQuestionDao.getOneSurvey(surveyQuestionVO.getSrvId());

		if (!originalSurveyQuestionVO.getSrvId().equals(surveyQuestionVO.getSrvId())) {
			throw new PageNotFoundException();
		}
		return this.surveyQuestionDao.deleteOneSurvey(surveyQuestionVO) > 0;
	}

	@Override
	public List<SurveyQuestionVO> getAllQuestionsByPrjId(String prjId) {
		return this.surveyQuestionDao.getAllQuestionsByPrjId(prjId);
	}

	@Override
	public SurveyListVO searchAllQuestions(SurveyQuestionVO surveyQuestionVO) {
		List<SurveyQuestionVO> questionList = this.surveyQuestionDao.getAllQuestions(surveyQuestionVO);

		SurveyListVO surveyListVO = new SurveyListVO();
		surveyListVO.setQuestionList(questionList);

		return surveyListVO;
	}

	@Override
	public SurveyListVO searchTeammate(SearchSurveyVO searchSurveyVO) {
		List<ProjectSurveyQuestionVO> teammateList = this.surveyQuestionDao.searchTeammate(searchSurveyVO);

		SurveyListVO surveyListVO = new SurveyListVO();
		surveyListVO.setProjectTeammateList(teammateList);
		return surveyListVO;
	}

	@Override
	public SurveyQuestionVO getOneProjectIdBySrvId(String srvId) {
		return this.surveyQuestionDao.getOneSrvQuestionVOBySrvId(srvId);
	}

	@Override
	public int getRoleNoneCount(String prjId) {
		return this.surveyQuestionDao.getRoleNoneCount(prjId);
	}

	@Override
	public int getServeyDoneCount(String prjId) {
		// TODO Auto-generated method stub
		return this.surveyQuestionDao.surveyQuestionDao(prjId);
	}

}
