package com.ktdsuniversity.edu.pms.survey.vo;

import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class SearchSurveyVO extends PaginateVO {
	
	private String searchType;
	private String searchKeyword;
	private String prjId;
	private EmployeeVO employeeVO;
	
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
	public String getPrjId() {
		return prjId;
	}
	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}
	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}

}
