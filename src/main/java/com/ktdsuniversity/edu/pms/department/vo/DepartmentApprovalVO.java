package com.ktdsuniversity.edu.pms.department.vo;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class DepartmentApprovalVO extends DepartmentVO {

	private String deptApprId;
	private String deptApprType;
	private String delYn;
	private String deptApprYn;
	private ApprovalVO approvalVO;
	private String deptApprReqtr;
	private String deptApprReason;

	private String eaEmpName; // 결제 요청자 정보 ea
	private String eaEmpId;
	private String eaEmail;
	private String edlEmpName; // 부서장 정보 edl
	private String edlEmpId;
	private String edlEmail;

	public String getEaEmpName() {
		return eaEmpName;
	}

	public void setEaEmpName(String eaEmpName) {
		this.eaEmpName = eaEmpName;
	}

	public String getEaEmpId() {
		return eaEmpId;
	}

	public void setEaEmpId(String eaEmpId) {
		this.eaEmpId = eaEmpId;
	}

	public String getEaEmail() {
		return eaEmail;
	}

	public void setEaEmail(String eaEmail) {
		this.eaEmail = eaEmail;
	}

	public String getEdlEmpName() {
		return edlEmpName;
	}

	public void setEdlEmpName(String edlEmpName) {
		this.edlEmpName = edlEmpName;
	}

	public String getEdlEmpId() {
		return edlEmpId;
	}

	public void setEdlEmpId(String edlEmpId) {
		this.edlEmpId = edlEmpId;
	}

	public String getEdlEmail() {
		return edlEmail;
	}

	public void setEdlEmail(String edlEmail) {
		this.edlEmail = edlEmail;
	}

	public String getDeptApprReason() {
		return deptApprReason;
	}

	public void setDeptApprReason(String deptApprReason) {
		this.deptApprReason = deptApprReason;
	}

	public String getDeptApprReqtr() {
		return deptApprReqtr;
	}

	public void setDeptApprReqtr(String deptApprReqtr) {
		this.deptApprReqtr = deptApprReqtr;
	}

	public String getDeptApprId() {
		return deptApprId;
	}

	public void setDeptApprId(String deptApprId) {
		this.deptApprId = deptApprId;
	}

	public String getDeptApprType() {
		return deptApprType;
	}

	public void setDeptApprType(String deptApprType) {
		this.deptApprType = deptApprType;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getDeptApprYn() {
		return deptApprYn;
	}

	public void setDeptApprYn(String deptApprYn) {
		this.deptApprYn = deptApprYn;
	}

	public ApprovalVO getApprovalVO() {
		return approvalVO;
	}

	public void setApprovalVO(ApprovalVO approvalVO) {
		this.approvalVO = approvalVO;
	}

}
