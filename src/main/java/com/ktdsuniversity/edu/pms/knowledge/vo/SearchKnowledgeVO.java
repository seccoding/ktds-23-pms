package com.ktdsuniversity.edu.pms.knowledge.vo;

import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;

public class SearchKnowledgeVO extends PaginateVO {
	
	private String searchType;
	
	private String searchKeyword;

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
