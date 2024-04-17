package com.ktdsuniversity.edu.pms.survey.vo;

public class SurveyQuestionPickVO {
	
	private String sqpId;
	private String srvId;
	private String sqpCntnt;
	private String nextId;
	private String crtrId;
	private String crtDt;
	private String mdfrId;
	private String mdfDt;
	private String delYn;
	private int seq;
	
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
	public String getCrtrId() {
		return crtrId;
	}
	public void setCrtrId(String crtrId) {
		this.crtrId = crtrId;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	public String getMdfrId() {
		return mdfrId;
	}
	public void setMdfrId(String mdfrId) {
		this.mdfrId = mdfrId;
	}
	public String getMdfDt() {
		return mdfDt;
	}
	public void setMdfDt(String mdfDt) {
		this.mdfDt = mdfDt;
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
	public SurveyQuestionVO getSurveyQuestionVO() {
		return surveyQuestionVO;
	}
	public void setSurveyQuestionVO(SurveyQuestionVO surveyQuestionVO) {
		this.surveyQuestionVO = surveyQuestionVO;
	}
}
