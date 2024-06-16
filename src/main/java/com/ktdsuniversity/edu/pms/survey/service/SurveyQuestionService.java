package com.ktdsuniversity.edu.pms.survey.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.project.vo.ProjectSurveyQuestionVO;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

public interface SurveyQuestionService {
	
	/**
	 * 모든 문항을 가져오는 메서드
	 * @return
	 */
	public SurveyListVO getAllSurvey();
	
	/**
	 * 프로젝트 아이디별로 설문 문항들을 가져오는 메서드
	 * @param prjId
	 * @return
	 */
	public SurveyQuestionVO getOneSurvey(String prjId);
	
	/**
	 * 새로운 설문 문항을 생성하는 메서드
	 * @param surveyQuestionVO
	 * @return
	 */
	public boolean createNewSurveyQuestion(SurveyQuestionVO surveyQuestionVO);

	/**
	 * 
	 * @param surveyQuestionVO
	 * @return
	 */
	public boolean createSurveyBody(SurveyQuestionVO surveyQuestionVO);

	/**
	 * 
	 * @param searchSurveyVO
	 * @return
	 */
	public List<SurveyQuestionVO> getAllSurveys(SearchSurveyVO searchSurveyVO);
	
	/**
	 * 설문 ID로 설문 문항 하나를 수정하는 메서드
	 * @param surveyQuestionVO
	 * @return
	 */
	public boolean modifyOneSurvey(SurveyQuestionVO surveyQuestionVO);

	/**
	 * 설문 ID로 설문 문항의 타입을 바꾸는 메서드(N: 객관식, Y: 서술형)
	 * @param surveyQuestionVO
	 * @return
	 */
	public boolean modifyOneSurveyExceptBody(SurveyQuestionVO surveyQuestionVO);

	/**
	 * 새로운 설문 문항을 새로 생성하는 메서드
	 * @param surveyReplyVO
	 * @return
	 */
	public boolean writeNewSurvey(SurveyReplyVO surveyReplyVO);

	/**
	 * 프로젝트 아이디로 하나의 설문문항을 가져와서 작성하는 메서드??
	 * @param prjId
	 * @return
	 */
	public SurveyQuestionVO getOneSurveyForWrite(String prjId);

	/**
	 * 모든 설문문항을 가져오는 매서드
	 * @return
	 */
	public SurveyListVO getAllQuestions();

	/**
	 * 위의 메서드랑 동일한 메서드인것 같은데 무슨 차이지?
	 * @param surveyQuestionVO
	 * @return
	 */
	public List<SurveyQuestionVO> getAllQuestions(SurveyQuestionVO surveyQuestionVO);

	/**
	 * 
	 * @param searchSurveyVO
	 * @return
	 */
	public SurveyListVO searchProject(SearchSurveyVO searchSurveyVO);

	/**
	 * 하나의 설문문항의 삭제여부를 Y로 바꾸는 메서드
	 * @param surveyQuestionVO
	 * @return
	 */
	public boolean deleteOneSurvey(SurveyQuestionVO surveyQuestionVO);

	/**
	 * 프로젝트 ID로 설문문항을 모두 가져오는 메서드?? 
	 * 동일 메서드가 여러개인것 같은데?
	 * @param prjId
	 * @return
	 */
	public List<SurveyQuestionVO> getAllQuestionsByPrjId(String prjId);

	/**
	 * 모든 설문문항들을 검색하는 메서드??
	 * @param surveyQuestionVO
	 * @return
	 */
	public SurveyListVO searchAllQuestions(SurveyQuestionVO surveyQuestionVO);

	/**
	 * 팀들을 검색하는 메서드??
	 * @param searchSurveyVO
	 * @return
	 */
	public SurveyListVO searchTeammate(SearchSurveyVO searchSurveyVO);

	public SurveyQuestionVO getOneProjectIdBySrvId(String srvId);

	public int getRoleNoneCount(String prjId);

	public int getServeyDoneCount(String prjId);

}
