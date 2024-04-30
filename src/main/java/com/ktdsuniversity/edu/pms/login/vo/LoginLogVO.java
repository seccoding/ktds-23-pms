package com.ktdsuniversity.edu.pms.login.vo;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

public class LoginLogVO {
    private String logId;
    private String empId;
    private String lgnSccDt;
    private String lgtDt;
    private String delYn;

    private String searchKeyword;
    private String searchType;

    private String loginType;

    private EmployeeVO employeeVO;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public EmployeeVO getEmployeeVO() {
        return employeeVO;
    }

    public void setEmployeeVO(EmployeeVO employeeVO) {
        this.employeeVO = employeeVO;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getLgnSccDt() {
        return lgnSccDt;
    }

    public void setLgnSccDt(String lgnSccDt) {
        this.lgnSccDt = lgnSccDt;
    }

    public String getLgtDt() {
        return lgtDt;
    }

    public void setLgtDt(String lgtDt) {
        this.lgtDt = lgtDt;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }
}
