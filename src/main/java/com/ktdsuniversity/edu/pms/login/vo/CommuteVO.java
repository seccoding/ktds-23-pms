package com.ktdsuniversity.edu.pms.login.vo;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class CommuteVO {
    private String cmmtId;
    private String cmmtDate;
    private String cmmtTime;
    private String fnshTime;
    private String empId;
    private String delYn;
    
    private EmployeeVO employeeVO;
    private String empName;

    public String getCmmtId() {
        return cmmtId;
    }

    public void setCmmtId(String cmmtId) {
        this.cmmtId = cmmtId;
    }

    public String getCmmtDate() {
        return cmmtDate;
    }

    public void setCmmtDate(String cmmtDate) {
        this.cmmtDate = cmmtDate;
    }

    public String getCmmtTime() {
        return cmmtTime;
    }

    public void setCmmtTime(String cmmtTime) {
        this.cmmtTime = cmmtTime;
    }

    public String getFnshTime() {
        return fnshTime;
    }

    public void setFnshTime(String fnshTime) {
        this.fnshTime = fnshTime;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}

	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
    
    
}
