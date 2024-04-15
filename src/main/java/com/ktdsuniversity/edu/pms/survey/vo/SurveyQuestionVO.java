package com.ktdsuniversity.edu.pms.survey.vo;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;

public class SurveyQuestionVO {
	
	private String srvId;
	private String prjId;
	private String srvQst;
	private String crtrId;
	private String crtDt;
	private String mdfrId;
	private String mdfDt;
	private String delYn;
	private int seq;
	private String typeYn;
	private String srvSts;
	
	private ProjectVO projectVO;
	private DepartmentVO departmentVO;
	
	public String getSrvId() {
		return srvId;
	}
	public void setSrvId(String srvId) {
		this.srvId = srvId;
	}
	public String getPrjId() {
		return prjId;
	}
	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
	public String getSrvQst() {
		return srvQst;
	}
	public void setSrvQst(String srvQst) {
		this.srvQst = srvQst;
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
	public String getTypeYn() {
		return typeYn;
	}
	public void setTypeYn(String typeYn) {
		this.typeYn = typeYn;
	}
	public String getSrvSts() {
		return srvSts;
	}
	public void setSrvSts(String srvSts) {
		this.srvSts = srvSts;
	}
	public ProjectVO getProjectVO() {
		return projectVO;
	}
	public void setProjectVO(ProjectVO projectVO) {
		this.projectVO = projectVO;
	}
	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}
	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}

}
