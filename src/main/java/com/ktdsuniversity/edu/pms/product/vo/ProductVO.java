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
	
	private List<ApprovalDetailVO> approvalDetailVOList;
	
	public List<ApprovalDetailVO> getApprovalDetailVOList() {
		return approvalDetailVOList;
	}
	public void setApprovalDetailVOList(List<ApprovalDetailVO> approvalDetailVOList) {
		this.approvalDetailVOList = approvalDetailVOList;
	}
	private String searchType; // option 선택
	private String searchKeyword; // 검색어
	
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
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

}
