package com.ktdsuniversity.edu.pms.approval.vo;

import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class SearchApprovalVO extends PaginateVO {

    private String searchType;
    private String searchKeyword;
    private String searchStatus; // 결재상태
    private boolean searchAuth; // 경영관리부장 여부
    private String searchDate; // 날짜검색용
    private EmployeeVO employeeVO;

    // 기본 생성자
    public SearchApprovalVO() {
    }

    // employeeVO, searchStatus, searchAuth 생성자
    public SearchApprovalVO(String searchStatus, boolean searchAuth, EmployeeVO employeeVO) {
        this(searchStatus, searchAuth, "", employeeVO);
    }

    // employeeVO, searchStatus, searchAuth, searchDate 생성자
    public SearchApprovalVO(String searchStatus, boolean searchAuth, String searchDate, EmployeeVO employeeVO) {
        this.searchStatus = searchStatus;
        this.searchAuth = searchAuth;
        this.searchDate = searchDate;
        this.employeeVO = employeeVO;
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

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}

	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}

    public boolean getSearchAuth() {
        return searchAuth;
    }

    public void setSearchAuth(boolean searchAuth) {
        this.searchAuth = searchAuth;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

}
