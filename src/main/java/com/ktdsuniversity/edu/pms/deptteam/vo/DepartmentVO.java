package com.ktdsuniversity.edu.pms.deptteam.vo;

import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.job.vo.JobVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

public class DepartmentVO {
	// 부서 고유번호
	private String deptId;
	// 부서 명
	private String deptName;
	// 부서 생성일
	private String deptCrDt;
	// 부서장 고유번호
	private String deptLeadId;
	// 부서 삭제여부
	private String delYn;

	private EmployeeVO employeeVO;
	private TeamVO teamVO;
	private JobVO jobVO;
	private CommonCodeVO commonCodeVO;
	
	
	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}

	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}

	public TeamVO getTeamVO() {
		return teamVO;
	}

	public void setTeamVO(TeamVO teamVO) {
		this.teamVO = teamVO;
	}

	public JobVO getJobVO() {
		return jobVO;
	}

	public void setJobVO(JobVO jobVO) {
		this.jobVO = jobVO;
	}

	public CommonCodeVO getCommonCodeVO() {
		return commonCodeVO;
	}

	public void setCommonCodeVO(CommonCodeVO commonCodeVO) {
		this.commonCodeVO = commonCodeVO;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCrDt() {
		return deptCrDt;
	}

	public void setDeptCrDt(String deptCrDt) {
		this.deptCrDt = deptCrDt;
	}

	public String getDeptLeadId() {
		return deptLeadId;
	}

	public void setDeptLeadId(String deptLeadId) {
		this.deptLeadId = deptLeadId;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

}