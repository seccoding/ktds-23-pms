package com.ktdsuniversity.edu.pms.knowledge.vo;

public class KnowledgeVO {
	
	private String knlId;
	private String rqmId;
	private String knlTtl;
	private String knlCntnt;
	private int knlCnt;
	private int knlRecCnt;
	private String crtDt;
	private String mdfDt;
	private String delYn;
	private String crtrId;
	private String mdfrId;
	
	// file - 난독화
	private String fileName;
	// file - 난독화X
	private String originalFileName;
	
	
	public String getKnlId() {
		return knlId;
	}
	public void setKnlId(String knlId) {
		this.knlId = knlId;
	}
	public String getRqmId() {
		return rqmId;
	}
	public void setRqmId(String rqmId) {
		this.rqmId = rqmId;
	}
	public String getKnlTtl() {
		return knlTtl;
	}
	public void setKnlTtl(String knlTtl) {
		this.knlTtl = knlTtl;
	}
	public String getKnlCntnt() {
		return knlCntnt;
	}
	public void setKnlCntnt(String knlCntnt) {
		this.knlCntnt = knlCntnt;
	}
	public int getKnlCnt() {
		return knlCnt;
	}
	public void setKnlCnt(int knlCnt) {
		this.knlCnt = knlCnt;
	}
	public int getKnlRecCnt() {
		return knlRecCnt;
	}
	public void setKnlRecCnt(int knlRecCnt) {
		this.knlRecCnt = knlRecCnt;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
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
	public String getCrtrId() {
		return crtrId;
	}
	public void setCrtrId(String crtrId) {
		this.crtrId = crtrId;
	}
	public String getMdfrId() {
		return mdfrId;
	}
	public void setMdfrId(String mdfrId) {
		this.mdfrId = mdfrId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	
}
