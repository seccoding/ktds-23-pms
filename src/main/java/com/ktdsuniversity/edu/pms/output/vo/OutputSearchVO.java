package com.ktdsuniversity.edu.pms.output.vo;
import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;


public class OutputSearchVO extends PaginateVO{
	String prjId;
	String outType;
	
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
	
	
}
