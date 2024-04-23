package com.ktdsuniversity.edu.pms.requirement.vo;

import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;

/**
 * 
 */
public class RequirementSearchVO  extends PaginateVO {
	
	private String prjId;
	private String scdSts;
	private String rqmSts;
	private String searchType ;
	private String searchKeyoword;
	private String empId;
	
	public String getPrjId() {
		return prjId;
	}
	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
	public String getScdSts() {
		return scdSts;
	}
	public void setScdSts(String scdSts) {
		this.scdSts = scdSts;
	}
	public String getRqmSts() {
		return rqmSts;
	}
	public void setRqmSts(String rqmSts) {
		this.rqmSts = rqmSts;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchKeyoword() {
		return searchKeyoword;
	}
	public void setSearchKeyoword(String searchKeyoword) {
		this.searchKeyoword = searchKeyoword;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	
	


}
