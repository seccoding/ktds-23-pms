package com.ktdsuniversity.edu.pms.department.vo;

import java.util.List;

import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.job.vo.JobVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

public class DepartmentVO {
	private String deptId;
	private String deptName;
	private String deptCrDt;
	private String delYn;
	private String deptLeadId;
	private String empName;
	private String email;
	private EmployeeVO employeeVO;
	private TeamVO teamVO;
	private JobVO jobVO;
	private CommonCodeVO commonCodeVO;
//	private List<TeamVO> teamListVO;
//	
//	public List<TeamVO> getTeamListVO() {
//		return teamListVO;
//	}
//	public void setTeamListVO(List<TeamVO> teamListVO) {
//		this.teamListVO = teamListVO;
//	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
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

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getDeptLeadId() {
		return deptLeadId;
	}

	public void setDeptLeadId(String deptLeadId) {
		this.deptLeadId = deptLeadId;
	}

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

}
