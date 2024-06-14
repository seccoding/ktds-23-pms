package com.ktdsuniversity.edu.pms.survey.vo;

public class SurveyQuestionPickVO {

	private String sqpId;
	private String srvId;
	private String sqpCntnt;
	private String nextId;
	private String delYn;
	private int seq;
	private int sqpCount;

	private SurveyQuestionVO surveyQuestionVO;

	public String getSqpId() {
		return sqpId;
	}

	public void setSqpId(String sqpId) {
		this.sqpId = sqpId;
	}

	public String getSrvId() {
		return srvId;
	}

	public void setSrvId(String srvId) {
		this.srvId = srvId;
	}

	public String getSqpCntnt() {
		return sqpCntnt;
	}

	public void setSqpCntnt(String sqpCntnt) {
		this.sqpCntnt = sqpCntnt;
	}

	public String getNextId() {
		return nextId;
	}

	public void setNextId(String nextId) {
		this.nextId = nextId;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getSqpCount() {
		return sqpCount;
	}

	public void setSqpCount(int sqpCount) {
		this.sqpCount = sqpCount;
	}

	public SurveyQuestionVO getSurveyQuestionVO() {
		return surveyQuestionVO;
	}

	public void setSurveyQuestionVO(SurveyQuestionVO surveyQuestionVO) {
		this.surveyQuestionVO = surveyQuestionVO;
	}
}
