package com.ktdsuniversity.edu.pms.review.vo;
import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;

public class SearchReviewVO extends PaginateVO {


	private String prjId;
    private String searchType;
    private String searchKeyword;

    
    public String getSearchType() {
        return searchType;
    }

    public String getPrjId() {
		return prjId;
	}

	public void setPrjId(String prjId) {
		this.prjId = prjId;
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
