package com.ktdsuniversity.edu.pms.output.vo;

import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;

public class OutputVO {
	private int level;
	
	private String outId; 	//산출물 아이디
	private String outType; //산출물 종류
	private String outTtl; 	//제목
	private String outVer; 	//버전
	private String outFile; // 산출믈  원본 파일명
	private String crtDt;	//등록일
	private String crtrId;	//등록자 id
	private String mdfDt;	//수정일
	private String mdfrId;	//수정자 아이디
	private String prjId;	//프로젝트 id
	private String delYn;	//삭제여부
	private String outEncodeFile;//산출물 인코딩 파일명
	private String outVerNum;//버젼별 넘버
	
	private CommonCodeVO outVerSts;
	
	private CommonCodeVO outTypeVO;
	
	private ProjectVO project;
	
	private EmployeeVO crtrIdVO;
	
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
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
	public CommonCodeVO getOutTypeVO() {
		return outTypeVO;
	}
	public void setOutTypeVO(CommonCodeVO outTypeVO) {
		this.outTypeVO = outTypeVO;
	}
	public ProjectVO getProject() {
		return project;
	}
	public void setProject(ProjectVO project) {
		this.project = project;
	}
	public String getOutEncodeFile() {
		return outEncodeFile;
	}
	public void setOutEncodeFile(String outEncodeFile) {
		this.outEncodeFile = outEncodeFile;
	}
	
	public String getOutVerNum() {
		return outVerNum;
	}
	public void setOutVerNum(String outVerNum) {
		this.outVerNum = outVerNum;
	}
	public CommonCodeVO getOutVerSts() {
		return outVerSts;
	}
	public void setOutVerSts(CommonCodeVO outVerSts) {
		this.outVerSts = outVerSts;
	}
	public EmployeeVO getCrtrIdVO() {
		return crtrIdVO;
	}
	public void setCrtrIdVO(EmployeeVO crtrIdVO) {
		this.crtrIdVO = crtrIdVO;
	}
	
	
	
	
}