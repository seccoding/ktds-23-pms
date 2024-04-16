package com.ktdsuniversity.edu.pms.project.vo;

import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;

public class SearchProjectVO extends PaginateVO {

    private String searchStatus;
    private String searchType;
    private String searchKeyword;

    public String getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(String searchStatus) {
        this.searchStatus = searchStatus;
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
