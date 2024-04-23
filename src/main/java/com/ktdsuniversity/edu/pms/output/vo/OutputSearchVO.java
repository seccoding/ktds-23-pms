package com.ktdsuniversity.edu.pms.output.vo;
import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;


public class OutputSearchVO extends PaginateVO{
	String prjId;
	String outType;
	String outVer;
	String empId;
	
	public OutputSearchVO() {
		
	}
	
	public OutputSearchVO(String prjId, String outType,String outVer) {
		this.prjId =prjId;
		this.outType = outType;
		this.outVer = outVer;
	}
	
	public String getPrjId() {
		return prjId;
	}
	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}

	public String getOutVer() {
		return outVer;
	}

	public void setOutVer(String outVer) {
		this.outVer = outVer;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	
	
	
}
