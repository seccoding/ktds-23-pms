package com.ktdsuniversity.edu.pms.login.vo;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class VisitedVO {

    private String accsId;
    private String empId;
    private String accsUrl;
    private String accsDt;
    private String delYn;

    private EmployeeVO employeeVO;

    private String searchType;
    private String searchKeyword;
    
    private String visitedType;

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

    public EmployeeVO getEmployeeVO() {
        return employeeVO;
    }

    public void setEmployeeVO(EmployeeVO employeeVO) {
        this.employeeVO = employeeVO;
    }

    public String getAccsId() {
        return accsId;
    }

    public void setAccsId(String accsId) {
        this.accsId = accsId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getAccsUrl() {
        return accsUrl;
    }

    public void setAccsUrl(String accsUrl) {
        this.accsUrl = accsUrl;
    }

    public String getAccsDt() {
        return accsDt;
    }

    public void setAccsDt(String accsDt) {
        this.accsDt = accsDt;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

	public String getVisitedType() {
		return visitedType;
	}

	public void setVisitedType(String visitedType) {
		this.visitedType = visitedType;
	}
    
    
}
