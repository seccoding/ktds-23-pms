package com.ktdsuniversity.edu.pms.employee.vo;

public class EmployeeChangeHistoryVO {

	private int historyId;
	
	private String empId;
	
	private String type;
	
	private String curValue;
	
	private String preValue;
	
	private String updDate;

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurValue() {
		return curValue;
	}

	public void setCurValue(String curValue) {
		this.curValue = curValue;
	}

	public String getPreValue() {
		return preValue;
	}

	public void setPreValue(String prevValue) {
		this.preValue = prevValue;
	}

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	
	
}
