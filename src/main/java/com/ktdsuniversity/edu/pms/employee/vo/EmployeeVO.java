package com.ktdsuniversity.edu.pms.employee.vo;

import java.util.List;

import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.JobHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.PositionHistoryVO;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.job.vo.JobVO;
import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;
import com.ktdsuniversity.edu.pms.login.vo.LoginLogVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

public class EmployeeVO {



    private String empId;
    private String empName;
    private String workSts;
    private int salYear;
    private String prfl;
    private String hireDt;
    private String hireYear;
    private String endDt;
    private String restStDt;
    private String restEndDt;
    private String cntct;
    private String addr;
    private String brth;
    private String email;
    private String mngrYn;
    private String admnCode;
    private String deptId;
    private String jobId;
    private String pstnId;
    private String pwd;
	private String confirmPwd;
    private String salt;
    private int lgnTry;
    private String pwdCnDt;
    private String lgnTryDt;
    private String lgnYn;
    private String fileName;
    private DepartmentVO departmentVO;
    private CommonCodeVO commonCodeVO;
    private DepartmentHistoryVO departmentHistoryVO;
    private String originPrflFileName; 
    private JobHistoryVO jobHistoryVO;
	private String newPwd;
	private String oldPwd;
    private PositionHistoryVO positionHistoryVO;
    private String confirmPwd;
	private String newPwd;

    public PositionHistoryVO getPositionHistoryVO() {
		return positionHistoryVO;
	}

	public void setPositionHistoryVO(PositionHistoryVO positionHistoryVO) {
		this.positionHistoryVO = positionHistoryVO;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public JobHistoryVO getJobHistoryVO() {
		return jobHistoryVO;
	}

	public void setJobHistoryVO(JobHistoryVO jobHistoryVO) {
		this.jobHistoryVO = jobHistoryVO;
	}

	public DepartmentHistoryVO getDepartmentHistoryVO() {
		return departmentHistoryVO;
	}

	public void setDepartmentHistoryVO(DepartmentHistoryVO departmentHistoryVO) {
		this.departmentHistoryVO = departmentHistoryVO;
	}

	private LoginLogVO loginLogVO;

	private CommuteVO commuteVO;

	public CommuteVO getCommuteVO() {
		return commuteVO;
	}

	public void setCommuteVO(CommuteVO commuteVO) {
		this.commuteVO = commuteVO;
	}

	public LoginLogVO getLoginLogVO() {
		return loginLogVO;
	}

	public void setLoginLogVO(LoginLogVO loginLogVO) {
		this.loginLogVO = loginLogVO;
	}

	private JobVO jobVO;
	private TeamVO teamVO;
	private List<TeamVO> teamList;

	public List<TeamVO> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<TeamVO> teamList) {
		this.teamList = teamList;
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

	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}

	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getWorkSts() {
		return workSts;
	}

	public void setWorkSts(String workSts) {
		this.workSts = workSts;
	}

	public int getSalYear() {
		return salYear;
	}

	public void setSalYear(int salYear) {
		this.salYear = salYear;
	}

	public String getPrfl() {
		return prfl;
	}

	public void setPrfl(String prfl) {
		this.prfl = prfl;
	}

	public String getHireDt() {
		return hireDt;
	}

	public void setHireDt(String hireDt) {
		this.hireDt = hireDt;
	}

	public String getHireYear() {
		return hireYear;
	}

	public void setHireYear(String hireYear) {
		this.hireYear = hireYear;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getRestStDt() {
		return restStDt;
	}

	public void setRestStDt(String restStDt) {
		this.restStDt = restStDt;
	}

	public String getRestEndDt() {
		return restEndDt;
	}

	public void setRestEndDt(String restEndDt) {
		this.restEndDt = restEndDt;
	}

	public String getCntct() {
		return cntct;
	}

	public void setCntct(String cntct) {
		this.cntct = cntct;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getBrth() {
		return brth;
	}

	public void setBrth(String brth) {
		this.brth = brth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMngrYn() {
		return mngrYn;
	}

	public void setMngrYn(String mngrYn) {
		this.mngrYn = mngrYn;
	}

	public String getAdmnCode() {
		return admnCode;
	}

	public void setAdmnCode(String admnCode) {
		this.admnCode = admnCode;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getPstnId() {
		return pstnId;
	}

	public void setPstnId(String pstnId) {
		this.pstnId = pstnId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getLgnTry() {
		return lgnTry;
	}

	public void setLgnTry(int lgnTry) {
		this.lgnTry = lgnTry;
	}

	public String getPwdCnDt() {
		return pwdCnDt;
	}

	public void setPwdCnDt(String pwdCnDt) {
		this.pwdCnDt = pwdCnDt;
	}

	public String getLgnTryDt() {
		return lgnTryDt;
	}

	public void setLgnTryDt(String lgnTryDt) {
		this.lgnTryDt = lgnTryDt;
	}

	public String getLgnYn() {
		return lgnYn;
	}

	public void setLgnYn(String lgnYn) {
		this.lgnYn = lgnYn;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public CommonCodeVO getCommonCodeVO() {
		return commonCodeVO;
	}

	public void setCommonCodeVO(CommonCodeVO commonCodeVO) {
		this.commonCodeVO = commonCodeVO;
	}

	public String getOriginPrflFileName() {
		return originPrflFileName;
	}

	public void setOriginPrflFileName(String originPrflFileName) {
		this.originPrflFileName = originPrflFileName;
	}


	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
}
