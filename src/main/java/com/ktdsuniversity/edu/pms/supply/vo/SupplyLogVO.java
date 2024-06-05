package com.ktdsuniversity.edu.pms.supply.vo;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class SupplyLogVO {
	
	private String splLogId;
	private String splId;
	private String reqrId;
	private int reqCnt;
	private String reqDt;
	private String reqRsn;
	private String delYn;
	private String empNameWithId;
	
	private SupplyVO supplyVO;
	private EmployeeVO employeeVO;
	
	public String getSplLogId() {
		return splLogId;
	}
	public void setSplLogId(String splLogId) {
		this.splLogId = splLogId;
	}
	public String getSplId() {
		return splId;
	}
	public void setSplId(String splId) {
		this.splId = splId;
	}
	public String getReqrId() {
		return reqrId;
	}
	public void setReqrId(String reqrId) {
		this.reqrId = reqrId;
	}
	public int getReqCnt() {
		return reqCnt;
	}
	public void setReqCnt(int reqCnt) {
		this.reqCnt = reqCnt;
	}
	public String getReqDt() {
		return reqDt;
	}
	public void setReqDt(String reqDt) {
		this.reqDt = reqDt;
	}
	public String getReqRsn() {
		return reqRsn;
	}
	public void setReqRsn(String reqRsn) {
		this.reqRsn = reqRsn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getEmpNameWithId() {
		return empNameWithId;
	}
	public void setEmpNameWithId(String empNameWithId) {
		this.empNameWithId = empNameWithId;
	}
	public SupplyVO getSupplyVO() {
		return supplyVO;
	}
	public void setSupplyVO(SupplyVO supplyVO) {
		this.supplyVO = supplyVO;
	}
	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}
	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}
}
