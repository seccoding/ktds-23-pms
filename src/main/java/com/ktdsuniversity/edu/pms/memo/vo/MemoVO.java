package com.ktdsuniversity.edu.pms.memo.vo;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class MemoVO {

	private String memoId;
	private String rcvId;
	private String crtrId;
	private String crtDt;
	private String readYn;
	private String delYn;
	private String saveYn;
	private String memoCntnt;
	private String memoTtl;
	private String rcvSave;
	private String crtrSave;

	private EmployeeVO employeeVO;
	private String email;
	private String empName;
	private String empId;

	public String getMemoId() {
		return memoId;
	}

	public void setMemoId(String memoId) {
		this.memoId = memoId;
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

	public String getSaveYn() {
		return saveYn;
	}

	public void setSaveYn(String saveYn) {
		this.saveYn = saveYn;
	}

	public String getMemoCntnt() {
		return memoCntnt;
	}

	public void setMemoCntnt(String memoCntnt) {
		this.memoCntnt = memoCntnt;
	}

	public String getMemoTtl() {
		return memoTtl;
	}

	public void setMemoTtl(String memoTtl) {
		this.memoTtl = memoTtl;
	}

	public String getRcvSave() {
		return rcvSave;
	}

	public void setRcvSave(String rcvSave) {
		this.rcvSave = rcvSave;
	}

	public String getCrtrSave() {
		return crtrSave;
	}

	public void setCrtrSave(String crtrSave) {
		this.crtrSave = crtrSave;
	}

	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}

	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

}
