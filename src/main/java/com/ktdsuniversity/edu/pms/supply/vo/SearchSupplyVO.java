package com.ktdsuniversity.edu.pms.supply.vo;

import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;

public class SearchSupplyVO extends PaginateVO {
	
	private String searchType;
	private String searchKeyword;
	private String empId;
	
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
}
