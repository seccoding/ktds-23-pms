package com.ktdsuniversity.edu.pms.memo.vo;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

import java.util.List;

public class SendMemoVO {

    private String sendMemoId;
    private String sendId;
    private String memoTtl;
    private String memoCntnt;
    private String fileName;
    private String originFileName;
    private String sendDate;
    private String sendSaveYn;
    private String sendDelYn;
    private String sendStsCode;

    private EmployeeVO employeeVO;
    private DepartmentVO departmentVO;
    private List<ReceiveMemoVO> receiveMemoVOList;
    private List<String> receiveInfoList;

    public String getSendMemoId() {
        return sendMemoId;
    }

    public void setSendMemoId(String sendMemoId) {
        this.sendMemoId = sendMemoId;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getMemoTtl() {
        return memoTtl;
    }

    public void setMemoTtl(String memoTtl) {
        this.memoTtl = memoTtl;
    }

    public String getMemoCntnt() {
        return memoCntnt;
    }

    public void setMemoCntnt(String memoCntnt) {
        this.memoCntnt = memoCntnt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendSaveYn() {
        return sendSaveYn;
    }

    public void setSendSaveYn(String sendSaveYn) {
        this.sendSaveYn = sendSaveYn;
    }

    public String getSendDelYn() {
        return sendDelYn;
    }

    public void setSendDelYn(String sendDelYn) {
        this.sendDelYn = sendDelYn;
    }

    public String getSendStsCode() {
        return sendStsCode;
    }

    public void setSendStsCode(String sendStsCode) {
        this.sendStsCode = sendStsCode;
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

    public List<ReceiveMemoVO> getReceiveMemoVOList() {
        return receiveMemoVOList;
    }

    public void setReceiveMemoVOList(List<ReceiveMemoVO> receiveMemoVOList) {
        this.receiveMemoVOList = receiveMemoVOList;
    }
    
    public List<String> getReceiveInfoList() {
		return receiveInfoList;
	}
    
    public void setReceiveInfoList(List<String> receiveInfoList) {
		this.receiveInfoList = receiveInfoList;
	}

}
