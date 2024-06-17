package com.ktdsuniversity.edu.pms.approval.vo;

import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class ApprovalVO extends PaginateVO {

	/**
	 * 결재 요청에 대한 고유번호
	 */
	private String apprId;
	private String pApprId; // 부모 결제요청 ID
	private String apprType; // 결제 승인 타입(소모품, 비품, 부서, 직원)
	private String apprInfo; // 결제시 업데이트 해야하는 정보를 담은 FK ID
	private String apprReqtr; // 결제 요청자
	private String apprDate; // 결제 요청일
	private String apprEndDate; // 결제 종료일
	private String approver; // 결제 승인자
	private String apprYn; // 결제승인여부
	private String apprRsn; // 결제에 대한 사유(거절시 작성)
	private String delYn; // 삭제여부
	private String searchYn; // 검색가능여부(승인페이지 노출)

	private EmployeeVO reqtrVO;

	private EmployeeVO approverVO;

	public ApprovalVO() {
	}

	public ApprovalVO(ApprovalVO approvalVO) {
		this.apprId = approvalVO.apprId;
		this.pApprId = approvalVO.pApprId;
		this.apprType = approvalVO.apprType;
		this.apprInfo = approvalVO.apprInfo;
		this.apprReqtr = approvalVO.apprReqtr;
		this.apprDate = approvalVO.apprDate;
		this.apprEndDate = approvalVO.apprEndDate;
		this.approver = approvalVO.approver;
		this.apprYn = approvalVO.apprYn;
		this.apprRsn = approvalVO.apprRsn;
		this.delYn = approvalVO.delYn;
		this.searchYn = approvalVO.searchYn;
	}

	public String getApprId() {
		return apprId;
	}

	public void setApprId(String apprid) {
		this.apprId = apprid;
	}

	public String getpApprId() {
		return pApprId;
	}

	public void setpApprId(String pApprId) {
		this.pApprId = pApprId;
	}

	public String getApprType() {
		return apprType;
	}

	public void setApprType(String apprType) {
		this.apprType = apprType;
	}

	public String getApprInfo() {
		return apprInfo;
	}

	public void setApprInfo(String apprInfo) {
		this.apprInfo = apprInfo;
	}

	public String getApprReqtr() {
		return apprReqtr;
	}

	public void setApprReqtr(String apprReqtr) {
		this.apprReqtr = apprReqtr;
	}

	public String getApprDate() {
		return apprDate;
	}

	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}

	public String getApprEndDate() {
		return apprEndDate;
	}

	public void setApprEndDate(String apprEndDate) {
		this.apprEndDate = apprEndDate;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApprYn() {
		return apprYn;
	}

	public void setApprYn(String apprYn) {
		this.apprYn = apprYn;
	}

	public String getApprRsn() {
		return apprRsn;
	}

	public void setApprRsn(String apprRsn) {
		this.apprRsn = apprRsn;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getSearchYn() {
		return searchYn;
	}

	public void setSearchYn(String searchYn) {
		this.searchYn = searchYn;
	}

	public EmployeeVO getReqtrVO() {
		return reqtrVO;
	}

	public void setReqtrVO(EmployeeVO reqtrVO) {
		this.reqtrVO = reqtrVO;
	}

	public EmployeeVO getApproverVO() {
		return approverVO;
	}

	public void setApproverVO(EmployeeVO approverVO) {
		this.approverVO = approverVO;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
