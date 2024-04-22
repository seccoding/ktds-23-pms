package com.ktdsuniversity.edu.pms.issue.vo;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class IssueReplyVO {
	
	private int level;
	private String rplId;
	private String rplPid;
	private String rplCntnt;
	/**
	 * 부모글ID
	 */
	private String pPostId;
	private String pickYn;
	private String crtDt;
	private String crtrId;
	private String mdfDt;
	private String mdfrId;
	private String delYn;
	
	private EmployeeVO employeeVO;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getRplId() {
		return rplId;
	}
	public void setRplId(String rplId) {
		this.rplId = rplId;
	}
	public String getRplPid() {
		return rplPid;
	}
	public void setRplPid(String rplPid) {
		this.rplPid = rplPid;
	}
	public String getRplCntnt() {
		return rplCntnt;
	}
	public void setRplCntnt(String rplCntnt) {
		this.rplCntnt = rplCntnt;
	}
	public String getpPostId() {
		return pPostId;
	}
	public void setpPostId(String pPostId) {
		this.pPostId = pPostId;
	}
	public String getPickYn() {
		return pickYn;
	}
	public void setPickYn(String pickYn) {
		this.pickYn = pickYn;
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
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}
	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}
}
