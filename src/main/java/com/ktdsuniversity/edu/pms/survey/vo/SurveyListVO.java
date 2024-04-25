package com.ktdsuniversity.edu.pms.survey.vo;

import java.util.List;

import com.ktdsuniversity.edu.pms.project.vo.ProjectSurveyQuestionVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;

public class SurveyListVO {
	
	private int projectCount;
	
	private List<SurveyQuestionVO> surveyList;
	private List<SurveyQuestionVO> questionList;
	private List<SurveyQuestionPickVO> pickList;
	private List<SurveyReplyVO> replyList;
	
	private List<ProjectSurveyQuestionVO> projectList;
	private List<ProjectSurveyQuestionVO> projectTeammateList;

	public int getProjectCount() {
		return projectCount;
	}
	public void setProjectCount(int projectCount) {
		this.projectCount = projectCount;
	}
	public List<SurveyQuestionVO> getSurveyList() {
		return surveyList;
	}
	public void setSurveyList(List<SurveyQuestionVO> surveyList) {
		this.surveyList = surveyList;
	}
	public List<SurveyQuestionVO> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<SurveyQuestionVO> questionList) {
		this.questionList = questionList;
	}
	public List<SurveyQuestionPickVO> getPickList() {
		return pickList;
	}
	public void setPickList(List<SurveyQuestionPickVO> pickList) {
		this.pickList = pickList;
	}
	public List<ProjectSurveyQuestionVO> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<ProjectSurveyQuestionVO> projectList) {
		this.projectList = projectList;
	}
	public List<ProjectSurveyQuestionVO> getProjectTeammateList() {
		return projectTeammateList;
	}
	public void setProjectTeammateList(List<ProjectSurveyQuestionVO> projectTeammateList) {
		this.projectTeammateList = projectTeammateList;
	}
	public List<SurveyReplyVO> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<SurveyReplyVO> replyList) {
		this.replyList = replyList;
	}

}
