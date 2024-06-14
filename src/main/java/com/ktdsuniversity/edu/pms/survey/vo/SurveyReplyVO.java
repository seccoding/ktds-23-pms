package com.ktdsuniversity.edu.pms.survey.vo;

public class SurveyReplyVO {

	private String srvRplId;
	private String srvId;
	private String sqpId;
	private String srvRplCntnt;
	private String crtrId;
	private String crtDt;
	private String delYn;

	private SurveyQuestionVO surveyQuestionVO;

	public String getSrvRplId() {
		return srvRplId;
	}

	public void setSrvRplId(String srvRplId) {
		this.srvRplId = srvRplId;
	}

	public String getSrvId() {
		return srvId;
	}

	public void setSrvId(String srvId) {
		this.srvId = srvId;
	}

	public String getSqpId() {
		return sqpId;
	}

	public void setSqpId(String sqpId) {
		this.sqpId = sqpId;
	}

	public String getSrvRplCntnt() {
		return srvRplCntnt;
	}

	public void setSrvRplCntnt(String srvRplCntnt) {
		this.srvRplCntnt = srvRplCntnt;
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

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public SurveyQuestionVO getSurveyQuestionVO() {
		return surveyQuestionVO;
	}

	public void setSurveyQuestionVO(SurveyQuestionVO surveyQuestionVO) {
		this.surveyQuestionVO = surveyQuestionVO;
	}
}
