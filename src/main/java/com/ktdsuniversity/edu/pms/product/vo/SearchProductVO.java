package com.ktdsuniversity.edu.pms.product.vo;

import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;

public class SearchProductVO extends PaginateVO {
	
	private String searchType; // option 선택
	private String searchKeyword; // 검색어
	
	private Boolean isCheck;
	
	
	public Boolean getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
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
