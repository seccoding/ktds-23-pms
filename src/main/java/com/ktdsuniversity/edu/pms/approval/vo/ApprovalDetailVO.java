package com.ktdsuniversity.edu.pms.approval.vo;

import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

public class ApprovalDetailVO {
	
	private String apprDtlId;
	private String apprId;
	private String prdtId;
	private int curStr;
	private String delYn;
	private ApprovalVO approvalVO;
	private ProductVO productVO;
	
	public String getApprDtlId() {
		return apprDtlId;
	}
	public void setApprDtlId(String apprDtlId) {
		this.apprDtlId = apprDtlId;
	}
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getPrdtId() {
		return prdtId;
	}
	public void setPrdtId(String prdtId) {
		this.prdtId = prdtId;
	}
	public int getCurStr() {
		return curStr;
	}
	public void setCurStr(int curStr) {
		this.curStr = curStr;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public ApprovalVO getApprovalVO() {
		return approvalVO;
	}
	public void setApprovalVO(ApprovalVO approvalVO) {
		this.approvalVO = approvalVO;
	}
	public ProductVO getProductVO() {
		return productVO;
	}
	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	
	
	
}
