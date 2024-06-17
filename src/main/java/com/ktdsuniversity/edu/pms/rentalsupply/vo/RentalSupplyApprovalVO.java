package com.ktdsuniversity.edu.pms.rentalsupply.vo;

import java.util.List;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class RentalSupplyApprovalVO extends RentalSupplyVO {
	
	private String rsplApprId;
	private String rsplApprType;
	private String rsplApprReqtr;
	private String rsplApprYn;
	private String rsplRqstType;
	private String rtrnYn;
	private int rsplRqstQty;
	
	private ApprovalVO approvalVO;
	private EmployeeVO employeeVO;
	private List<RentalSupplyApprovalVO> rsupplies;
	
	public String getRsplApprId() {
		return rsplApprId;
	}
	public void setRsplApprId(String rsplApprId) {
		this.rsplApprId = rsplApprId;
	}
	public String getRsplApprType() {
		return rsplApprType;
	}
	public void setRsplApprType(String rsplApprType) {
		this.rsplApprType = rsplApprType;
	}
	public String getRsplApprReqtr() {
		return rsplApprReqtr;
	}
	public void setRsplApprReqtr(String rsplApprReqtr) {
		this.rsplApprReqtr = rsplApprReqtr;
	}
	public String getRsplApprYn() {
		return rsplApprYn;
	}
	public void setRsplApprYn(String rsplApprYn) {
		this.rsplApprYn = rsplApprYn;
	}
	public String getRsplRqstType() {
		return rsplRqstType;
	}
	public void setRsplRqstType(String rsplRqstType) {
		this.rsplRqstType = rsplRqstType;
	}
	public ApprovalVO getApprovalVO() {
		return approvalVO;
	}
	public void setApprovalVO(ApprovalVO approvalVO) {
		this.approvalVO = approvalVO;
	}
	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}
	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}
	public List<RentalSupplyApprovalVO> getRsupplies() {
		return rsupplies;
	}
	public void setRsupplies(List<RentalSupplyApprovalVO> rsupplies) {
		this.rsupplies = rsupplies;
	}
	public String getRtrnYn() {
		return rtrnYn;
	}
	public void setRtrnYn(String rtrnYn) {
		this.rtrnYn = rtrnYn;
	}
	public int getRsplRqstQty() {
		return rsplRqstQty;
	}
	public void setRsplRqstQty(int rsplRqstQty) {
		this.rsplRqstQty = rsplRqstQty;
	}
	
}
