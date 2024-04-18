package com.ktdsuniversity.edu.pms.employee.vo;

import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;

public class SearchEmployeeVO extends PaginateVO{

	private String searchType;
	private String searchKeyWord;
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchKeyWord() {
		return searchKeyWord;
	}
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}


	
}
