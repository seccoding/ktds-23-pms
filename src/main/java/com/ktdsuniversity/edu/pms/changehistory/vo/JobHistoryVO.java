package com.ktdsuniversity.edu.pms.changehistory.vo;

import com.ktdsuniversity.edu.pms.job.vo.JobVO;

public class JobHistoryVO {


    private String jobHistId;
    private String empId;
    private String pastJobId;
    private String jobStrtDt;
    private String jobEndDt;
    private String cnNote;
    private String delYn;
    
    private JobVO jobVO;
    
    
	public JobVO getJobVO() {
		return jobVO;
	}
	public void setJobVO(JobVO jobVO) {
		this.jobVO = jobVO;
	}
	public String getJobHistId() {
		return jobHistId;
	}
	public void setJobHistId(String jobHistId) {
		this.jobHistId = jobHistId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getPastJobId() {
		return pastJobId;
	}
	public void setPastJobId(String pastJobId) {
		this.pastJobId = pastJobId;
	}
	public String getJobStrtDt() {
		return jobStrtDt;
	}
	public void setJobStrtDt(String jobStrtDt) {
		this.jobStrtDt = jobStrtDt;
	}
	public String getJobEndDt() {
		return jobEndDt;
	}
	public void setJobEndDt(String jobEndDt) {
		this.jobEndDt = jobEndDt;
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
