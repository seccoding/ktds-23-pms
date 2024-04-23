package com.ktdsuniversity.edu.pms.changehistory.vo;

import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;

public class PositionHistoryVO {
	
	private String pstnId;
	private String empId;
	private String pastPstnId;
	private String pstnStrtDt;
	private String pstnEndDt;
	private String cnNote;
	private String delYn;
	
	private CommonCodeVO commonVO;
	private CommonCodeVO pastCommonVO;
	
	public CommonCodeVO getPastCommonVO() {
		return pastCommonVO;
	}
	public void setPastCommonVO(CommonCodeVO pastCommonVO) {
		this.pastCommonVO = pastCommonVO;
	}
	public CommonCodeVO getCommonVO() {
		return commonVO;
	}
	public void setCommonVO(CommonCodeVO commonVO) {
		this.commonVO = commonVO;
	}
	public String getPstnId() {
		return pstnId;
	}
	public void setPstnId(String pstnId) {
		this.pstnId = pstnId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getPastPstnId() {
		return pastPstnId;
	}
	public void setPastPstnId(String pastPstnId) {
		this.pastPstnId = pastPstnId;
	}
	public String getPstnStrtDt() {
		return pstnStrtDt;
	}
	public void setPstnStrtDt(String pstnStrtDt) {
		this.pstnStrtDt = pstnStrtDt;
	}
	public String getPstnEndDt() {
		return pstnEndDt;
	}
	public void setPstnEndDt(String pstnEndDt) {
		this.pstnEndDt = pstnEndDt;
	}
	public String getCnNote() {
		return cnNote;
	}
	public void setCnNote(String cnNote) {
		this.cnNote = cnNote;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
	

}
