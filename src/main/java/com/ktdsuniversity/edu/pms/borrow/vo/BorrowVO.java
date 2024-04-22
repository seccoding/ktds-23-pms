package com.ktdsuniversity.edu.pms.borrow.vo;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;
import com.ktdsuniversity.edu.pms.approval.vo.ApprovalVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductManagementVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;

public class BorrowVO {
	
	private String brrwHistId;
	private String brrwId;
	private String prdtMngId;
	private String brrwDt;
	private String rtnDt;
	private String delYn;
	
	private ProductManagementVO productManagementVO;
	
	private ProductVO productVO;
	
	// PSH0422
	private ApprovalVO approvalVO;
	private ApprovalDetailVO approvalDetailVO;
	
	public ProductVO getProductVO() {
		return productVO;
	}
	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	public ProductManagementVO getProductManagementVO() {
		return productManagementVO;
	}
	public void setProductManagementVO(ProductManagementVO productManagementVO) {
		this.productManagementVO = productManagementVO;
	}
	public String getBrrwHistId() {
		return brrwHistId;
	}
	public void setBrrwHistId(String brrwHistId) {
		this.brrwHistId = brrwHistId;
	}
	public String getBrrwId() {
		return brrwId;
	}
	public void setBrrwId(String brrwId) {
		this.brrwId = brrwId;
	}
	public String getPrdtMngId() {
		return prdtMngId;
	}
	public void setPrdtMngId(String prdtMngId) {
		this.prdtMngId = prdtMngId;
	}
	public String getBrrwDt() {
		return brrwDt;
	}
	public void setBrrwDt(String brrwDt) {
		this.brrwDt = brrwDt;
	}
	public String getRtnDt() {
		return rtnDt;
	}
	public void setRtnDt(String rtnDt) {
		this.rtnDt = rtnDt;
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
	public ApprovalDetailVO getApprovalDetailVO() {
		return approvalDetailVO;
	}
	public void setApprovalDetailVO(ApprovalDetailVO approvalDetailVO) {
		this.approvalDetailVO = approvalDetailVO;
	}
	
	

}
