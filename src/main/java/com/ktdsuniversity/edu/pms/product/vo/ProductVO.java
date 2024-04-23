package com.ktdsuniversity.edu.pms.product.vo;

import java.util.List;

import com.ktdsuniversity.edu.pms.approval.vo.ApprovalDetailVO;
import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;

public class ProductVO extends PaginateVO {
	
	private String prdtId;
	private String prdtName;
	private String prdtCtgr;
	private String onceYn;
	private int curStr;
	private String delYn;
	
	private ProductManagementVO productManagementVO;
	
	private List<ApprovalDetailVO> approvalDetailVOList;
	
	
	public ProductManagementVO getProductManagementVO() {
		return productManagementVO;
	}
	public void setProductManagementVO(ProductManagementVO productManagementVO) {
		this.productManagementVO = productManagementVO;
	}
	public List<ApprovalDetailVO> getApprovalDetailVOList() {
		return approvalDetailVOList;
	}
	public void setApprovalDetailVOList(List<ApprovalDetailVO> approvalDetailVOList) {
		this.approvalDetailVOList = approvalDetailVOList;
	}
	
	
	public String getPrdtId() {
		return prdtId;
	}
	public void setPrdtId(String prdtId) {
		this.prdtId = prdtId;
	}
	public String getPrdtName() {
		return prdtName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}
	public String getPrdtCtgr() {
		return prdtCtgr;
	}
	public void setPrdtCtgr(String prdtCtgr) {
		this.prdtCtgr = prdtCtgr;
	}
	public String getOnceYn() {
		return onceYn;
	}
	public void setOnceYn(String onceYn) {
		this.onceYn = onceYn;
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
	

}
