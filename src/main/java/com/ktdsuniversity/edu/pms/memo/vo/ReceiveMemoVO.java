package com.ktdsuniversity.edu.pms.memo.vo;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class ReceiveMemoVO {

    private String rcvMemoId;
    private String sendMemoId;
    private String rcvId;
    private String rcvCode;
    private String rcvDate;
    private String rcvSaveYn;
    private String rcvDelYn;

    private EmployeeVO employeeVO;
    private DepartmentVO departmentVO;
    private SendMemoVO sendMemoVO;

    public String getRcvMemoId() {
        return rcvMemoId;
    }

    public void setRcvMemoId(String rcvMemoId) {
        this.rcvMemoId = rcvMemoId;
    }

    public String getSendMemoId() {
        return sendMemoId;
    }

    public void setSendMemoId(String sendMemoId) {
        this.sendMemoId = sendMemoId;
    }

    public String getRcvId() {
        return rcvId;
    }

    public void setRcvId(String rcvId) {
        this.rcvId = rcvId;
    }

    public String getRcvCode() {
        return rcvCode;
    }

    public void setRcvCode(String rcvCode) {
        this.rcvCode = rcvCode;
    }

    public String getRcvDate() {
        return rcvDate;
    }

    public void setRcvDate(String rcvDate) {
        this.rcvDate = rcvDate;
    }

    public String getRcvSaveYn() {
        return rcvSaveYn;
    }

    public void setRcvSaveYn(String rcvSaveYn) {
        this.rcvSaveYn = rcvSaveYn;
    }

    public String getRcvDelYn() {
        return rcvDelYn;
    }

    public void setRcvDelYn(String rcvDelYn) {
        this.rcvDelYn = rcvDelYn;
    }

    public EmployeeVO getEmployeeVO() {
        return employeeVO;
    }

    public void setEmployeeVO(EmployeeVO employeeVO) {
        this.employeeVO = employeeVO;
    }

	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}

	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}

	public SendMemoVO getSendMemoVO() {
		return sendMemoVO;
	}

	public void setSendMemoVO(SendMemoVO sendMemoVO) {
		this.sendMemoVO = sendMemoVO;
	}
    
}
