package com.ktdsuniversity.edu.pms.survey.vo;

public class SurveyReplyVO {
	
	private String srvRplId;
	private String srvId;
	private String srvRplCntnt;
	private String crtrId;
	private String crtDt;
	private String mdfrId;
	private String mdfDt;
	private String delYn;
	private String prjId;
	
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
	public String getPrjId() {
		return prjId;
	}
	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
	public SurveyQuestionVO getSurveyQuestionVO() {
		return surveyQuestionVO;
	}
	public void setSurveyQuestionVO(SurveyQuestionVO surveyQuestionVO) {
		this.surveyQuestionVO = surveyQuestionVO;
	}
}
