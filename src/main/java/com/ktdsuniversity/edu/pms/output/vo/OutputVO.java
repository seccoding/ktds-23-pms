package com.ktdsuniversity.edu.pms.output.vo;

public class OutputVO {
	
	private String outId; 	//산출물 아이디
	private String outType; //산출물 종료
	private String outTtl; 	//제목
	private String outVer; 	//버전
	private String outFile; // 산출믈 파일명
	private String crtDt;	//등록일
	private String crtrId;	//등록자 id
	private String mdfDt;	//수정일
	private String mdfrId;	//수정자 아이디
	private String prjId;	//프로젝트 id
	private String delYn;	//삭제여부
	
	public String getOutId() {
		return outId;
	}
	public void setOutId(String outId) {
		this.outId = outId;
	}
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}
	public String getOutTtl() {
		return outTtl;
	}
	public void setOutTtl(String outTtl) {
		this.outTtl = outTtl;
	}
	public String getOutVer() {
		return outVer;
	}
	public void setOutVer(String outVer) {
		this.outVer = outVer;
	}
	public String getOutFile() {
		return outFile;
	}
	public void setOutFile(String outFile) {
		this.outFile = outFile;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	public String getCrtrId() {
		return crtrId;
	}
	public void setCrtrId(String crtrId) {
		this.crtrId = crtrId;
	}
	public String getMdfDt() {
		return mdfDt;
	}
	public void setMdfDt(String mdfDt) {
		this.mdfDt = mdfDt;
	}
	public String getMdfrId() {
		return mdfrId;
	}
	public void setMdfrId(String mdfrId) {
		this.mdfrId = mdfrId;
	}
	public String getPrjId() {
		return prjId;
	}
	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
	
}