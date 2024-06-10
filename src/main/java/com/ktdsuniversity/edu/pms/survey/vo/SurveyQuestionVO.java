package com.ktdsuniversity.edu.pms.survey.vo;

import java.util.List;

import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;

public class SurveyQuestionVO {

	private String srvId;
	private String prjId;
	private String srvQst;
	private String crtrId;
	private String mdfrId;
	private String mdfDt;
	private String delYn;
	private int seq;
	private String typeYn;

	private ProjectVO projectVO;
	private DepartmentVO departmentVO;
	private ProjectTeammateVO projectTeammateVO;
	private EmployeeVO employeeVO;
	private CommonCodeVO commonCodeVO;
	private List<SurveyQuestionPickVO> pickList;

	public String getSrvId() {
		return srvId;
	}

	public void setSrvId(String srvId) {
		this.srvId = srvId;
	}

	public String getPrjId() {
		return prjId;
	}

	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}

	public String getSrvQst() {
		return srvQst;
	}

	public void setSrvQst(String srvQst) {
		this.srvQst = srvQst;
	}

	public String getCrtrId() {
		return crtrId;
	}

	public void setCrtrId(String crtrId) {
		this.crtrId = crtrId;
	}

	public String getMdfrId() {
		return mdfrId;
	}

	public void setMdfrId(String mdfrId) {
		this.mdfrId = mdfrId;
	}

	public String getMdfDt() {
		return mdfDt;
	}

	public void setMdfDt(String mdfDt) {
		this.mdfDt = mdfDt;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTypeYn() {
		return typeYn;
	}

	public void setTypeYn(String typeYn) {
		this.typeYn = typeYn;
	}

	public ProjectVO getProjectVO() {
		return projectVO;
	}

	public void setProjectVO(ProjectVO projectVO) {
		this.projectVO = projectVO;
	}

	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}

	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}

	public ProjectTeammateVO getProjectTeammateVO() {
		return projectTeammateVO;
	}

	public void setProjectTeammateVO(ProjectTeammateVO projectTeammateVO) {
		this.projectTeammateVO = projectTeammateVO;
	}

	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}

	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}

	public CommonCodeVO getCommonCodeVO() {
		return commonCodeVO;
	}

	public void setCommonCodeVO(CommonCodeVO commonCodeVO) {
		this.commonCodeVO = commonCodeVO;
	}

	public List<SurveyQuestionPickVO> getPickList() {
		return pickList;
	}

	public void setPickList(List<SurveyQuestionPickVO> pickList) {
		this.pickList = pickList;
	}
}
