package com.ktdsuniversity.edu.pms.survey.vo;

public class SurveyResultVO extends SurveyReplyVO {
	
	private String srvId;
	private String srvQst;
	private String prjId;
	
	public String getSrvId() {
		return srvId;
	}
	public void setSrvId(String srvId) {
		this.srvId = srvId;
	}
	public String getSrvQst() {
		return srvQst;
	}
	public void setSrvQst(String srvQst) {
		this.srvQst = srvQst;
	}
	public String getPrjId() {
		return prjId;
	}
	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}

}
