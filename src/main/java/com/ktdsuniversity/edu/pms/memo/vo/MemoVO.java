package com.ktdsuniversity.edu.pms.memo.vo;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class MemoVO {

	private String memoId;
	private String memoPid;
	private String rcvId;
	private String crtrId;
	private String crtDt;
	private String memoCntnt;
	private String readYn;
	private String delYn;
	private String mdfDt;
	private String mdfrId;
	private String saveYn;

	private EmployeeVO employeeVO;
	private String email;
	private String empName;
	private String empId;
	
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}

	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}

	public String getMemoId() {
		return memoId;
	}

	public void setMemoId(String memoId) {
		this.memoId = memoId;
	}

	public String getMemoPid() {
		return memoPid;
	}

	public void setMemoPid(String memoPid) {
		this.memoPid = memoPid;
	}

	public String getRcvId() {
		return rcvId;
	}

	public void setRcvId(String rcvId) {
		this.rcvId = rcvId;
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

	public String getMemoCntnt() {
		return memoCntnt;
	}

	public void setMemoCntnt(String memoCntnt) {
		this.memoCntnt = memoCntnt;
	}

	public String getReadYn() {
		return readYn;
	}

	public void setReadYn(String readYn) {
		this.readYn = readYn;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
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

	public String getSaveYn() {
		return saveYn;
	}

	public void setSaveYn(String saveYn) {
		this.saveYn = saveYn;
	}

}
