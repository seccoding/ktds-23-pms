package com.ktdsuniversity.edu.pms.borrow.vo;

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
	
	

}
