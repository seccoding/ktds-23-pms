package com.ktdsuniversity.edu.pms.knowledge.vo;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class ReplyRecommandVO {
 	private String  reprecid;
 	private String reprecyn;
 	private String reprplid;
 	private String repcrtrid;
 	
 	private EmployeeVO employeeVO;
 	
	public String getReprecid() {
		return reprecid;
	}
	public void setReprecid(String reprecid) {
		this.reprecid = reprecid;
	}
	public String getReprecyn() {
		return reprecyn;
	}
	public void setReprecyn(String reprecyn) {
		this.reprecyn = reprecyn;
	}
	public String getReprplid() {
		return reprplid;
	}
	public void setReprplid(String reprplid) {
		this.reprplid = reprplid;
	}
	public String getRepcrtrid() {
		return repcrtrid;
	}
	public void setRepcrtrid(String repcrtrid) {
		this.repcrtrid = repcrtrid;
	}
 	
 	
}
