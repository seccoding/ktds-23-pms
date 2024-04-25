package com.ktdsuniversity.edu.pms.approval.vo;

import java.util.List;

import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.product.vo.ProductVO;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

public class ApprovalVO extends PaginateVO {

	private String apprId;
	private String dmdDt;
	private String apprCtgr;
	private String apprSts;
	private String arrpRjct;
	private String lastCnDt;
	private String delYn;
	private String dmdId;
	private String apprMngId;
	private String apprFnshDt;
	private String apprTtl;
	private String apprCntnt;
	private String rntlSts;

	private EmployeeVO employeeVO;
	private EmployeeVO employeeManagerVO;
	private CommonCodeVO commonCodeVO;
	private DepartmentVO departmentVO;
	private List<TeamVO> teamList;
	private List<ApprovalDetailVO> approvalDetailVOList;
	private List<ProductVO> productListVO;

	public String getApprId() {
		return apprId;
	}

	public void setApprId(String apprId) {
		this.apprId = apprId;
	}

	public String getDmdDt() {
		return dmdDt;
	}

	public void setDmdDt(String dmdDt) {
		this.dmdDt = dmdDt;
	}

	public String getApprCtgr() {
		return apprCtgr;
	}

	public void setApprCtgr(String apprCtgr) {
		this.apprCtgr = apprCtgr;
	}

	public String getApprSts() {
		return apprSts;
	}

	public void setApprSts(String apprSts) {
		this.apprSts = apprSts;
	}

	public String getArrpRjct() {
		return arrpRjct;
	}

	public void setArrpRjct(String arrpRjct) {
		this.arrpRjct = arrpRjct;
	}

	public String getLastCnDt() {
		return lastCnDt;
	}

	public void setLastCnDt(String lastCnDt) {
		this.lastCnDt = lastCnDt;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getDmdId() {
		return dmdId;
	}

	public void setDmdId(String dmdId) {
		this.dmdId = dmdId;
	}

	public String getApprMngId() {
		return apprMngId;
	}

	public void setApprMngId(String apprMngId) {
		this.apprMngId = apprMngId;
	}

	public void setApprFnshDt(String apprFnshDt) {
		this.apprFnshDt = apprFnshDt;
	}

	public String getApprFnshDt() {
		return apprFnshDt;
	}

	public void setApprTtl(String apprTtl) {
		this.apprTtl = apprTtl;
	}

	public String getApprTtl() {
		return apprTtl;
	}

	public void setApprCntnt(String apprCntnt) {
		this.apprCntnt = apprCntnt;
	}

	public String getApprCntnt() {
		return apprCntnt;
	}
	
	public String getRntlSts() {
		return rntlSts;
	}

	public void setRntlSts(String rntlSts) {
		this.rntlSts = rntlSts;
	}

	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}

	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}

	public CommonCodeVO getCommonCodeVO() {
		return commonCodeVO;
	}

	public void setCommonCodeVO(CommonCodeVO commonCodeVO) {
		this.commonCodeVO = commonCodeVO;
	}
	

	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}

	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}

	public EmployeeVO getEmployeeManagerVO() {
		return employeeManagerVO;
	}

	public void setEmployeeManagerVO(EmployeeVO employeeManagerVO) {
		this.employeeManagerVO = employeeManagerVO;
	}

	public List<ApprovalDetailVO> getApprovalDetailVOList() {
		return approvalDetailVOList;
	}

	public void setApprovalDetailVOList(List<ApprovalDetailVO> approvalDetailVOList) {
		this.approvalDetailVOList = approvalDetailVOList;
	}

	public List<ProductVO> getProductListVO() {
		return productListVO;
	}

	public void setProductListVO(List<ProductVO> productListVO) {
		this.productListVO = productListVO;
	}

	public List<TeamVO> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<TeamVO> teamList) {
		this.teamList = teamList;
	}
	
}
