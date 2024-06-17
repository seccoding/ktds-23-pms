package com.ktdsuniversity.edu.pms.supply.vo;

import java.util.List;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class SupplyApprovalVO extends SupplyVO {
	
	private String splApprId;
	private String splApprType;
	private String splApprReqtr;
	private String splApprYn;
	private String splRqstType; // 소모품 결재 신청 유형 (등록, 수정, 신청)
	private int splRqstQty;
	
	private ApprovalVO approvalVO;
	private EmployeeVO employeeVO;
	private List<SupplyApprovalVO> supplies;
	
	public String getSplApprId() {
		return splApprId;
	}
	public void setSplApprId(String splApprId) {
		this.splApprId = splApprId;
	}
	public String getSplApprType() {
		return splApprType;
	}
	public void setSplApprType(String splApprType) {
		this.splApprType = splApprType;
	}
	public String getSplApprReqtr() {
		return splApprReqtr;
	}
	public void setSplApprReqtr(String splApprReqtr) {
		this.splApprReqtr = splApprReqtr;
	}
	public String getSplApprYn() {
		return splApprYn;
	}
	public void setSplApprYn(String splApprYn) {
		this.splApprYn = splApprYn;
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
	public List<SupplyApprovalVO> getSupplies() {
		return supplies;
	}
	public void setSupplies(List<SupplyApprovalVO> supplies) {
		this.supplies = supplies;
	}
	public String getSplRqstType() {
		return splRqstType;
	}
	public void setSplRqstType(String splRqstType) {
		this.splRqstType = splRqstType;
	}
	public int getSplRqstQty() {
		return splRqstQty;
	}
	public void setSplRqstQty(int splRqstQty) {
		this.splRqstQty = splRqstQty;
	}
	
}
