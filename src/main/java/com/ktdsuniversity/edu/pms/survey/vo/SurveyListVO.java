package com.ktdsuniversity.edu.pms.survey.vo;

import java.util.List;

import com.ktdsuniversity.edu.pms.project.vo.ProjectSurveyQuestionVO;

public class SurveyListVO {
	
	private int projectCount;
	
	private List<SurveyQuestionVO> surveyList;
	private List<SurveyQuestionVO> questionList;
	private List<SurveyQuestionPickVO> pickList;
	
	private List<ProjectSurveyQuestionVO> projectList;

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

}
