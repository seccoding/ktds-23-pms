package com.ktdsuniversity.edu.pms.requirement.vo;

import com.ktdsuniversity.edu.pms.common.vo.PaginateVO;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;

public class RequirementVO {

	private String rqmId; // 요구사항 아이디
	private String rqmTtl; // 요구사항 제목
	private String rqmCntnt;// 요구사항 내용
	private String strtDt; // 시작일
	private String endDt; // 종료예정일
	private String rqmFile; // 실제 파일명
	private String dvlrp; // 담당개발자
	private String cfrmr; // 확인자
	private String tstr; // 테스터
	private String testRslt;// 테스트결과
	private String scdSts; // 일정상태
	private String rqmSts; // 요구사항 진행상태
	private String crtDt; // 등록일
	private String crtrId; // 등록자
	private String mdfDt; // 수정일
	private String mdfrId; // 수정자
	private String prjId; // 프로젝트 아이디
	private String delYn; // 삭제여부
	private String rqmEncodeFile;// 인코딩된 파일명

	private CommonCodeVO scdStsVO;
	private CommonCodeVO rqmStsVO;
	private ProjectVO projectVO;
	private EmployeeVO dvlrpVO; 	//담당개발자 vo
	private EmployeeVO cfrmrVO; 	//확인자 vo
	private EmployeeVO tstrVO; 		//테스터 vo
	private EmployeeVO crtrIdVO; 	//등록자 vo
	private EmployeeVO mdfrVO; 		//수정자 vo
	
	

	public String getRqmId() {
		return rqmId;
	}

	public void setRqmId(String rqmId) {
		this.rqmId = rqmId;
	}

	public String getRqmTtl() {
		return rqmTtl;
	}

	public void setRqmTtl(String rqmTtl) {
		this.rqmTtl = rqmTtl;
	}

	public String getRqmCntnt() {
		return rqmCntnt;
	}

	public void setRqmCntnt(String rqmCntnt) {
		this.rqmCntnt = rqmCntnt;
	}

	public String getStrtDt() {
		return strtDt;
	}

	public void setStrtDt(String strtDt) {
		this.strtDt = strtDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getRqmFile() {
		return rqmFile;
	}

	public void setRqmFile(String rqmFile) {
		this.rqmFile = rqmFile;
	}

	public String getDvlrp() {
		return dvlrp;
	}

	public void setDvlrp(String dvlrp) {
		this.dvlrp = dvlrp;
	}

	public String getCfrmr() {
		return cfrmr;
	}

	public void setCfrmr(String cfrmr) {
		this.cfrmr = cfrmr;
	}

	public String getTstr() {
		return tstr;
	}

	public void setTstr(String tstr) {
		this.tstr = tstr;
	}

	public String getTestRslt() {
		return testRslt;
	}

	public void setTestRslt(String testRslt) {
		this.testRslt = testRslt;
	}

	public String getScdSts() {
		return scdSts;
	}

	public void setScdSts(String scdSts) {
		this.scdSts = scdSts;
	}

	public String getRqmSts() {
		return rqmSts;
	}

	public void setRqmSts(String rqmSts) {
		this.rqmSts = rqmSts;
	}

	public String getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtrId() {
		return crtrId;
	}

	public void setCrtrId(String crtrId) {
		this.crtrId = crtrId;
	}

	public String getMdfDt() {
		return mdfDt;
	}

	public void setMdfDt(String mdfDt) {
		this.mdfDt = mdfDt;
	}

	public String getMdfrId() {
		return mdfrId;
	}

	public void setMdfrId(String mdfrId) {
		this.mdfrId = mdfrId;
	}

	public String getPrjId() {
		return prjId;
	}

	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	
	public String getRqmEncodeFile() {
		return rqmEncodeFile;
	}

	public void setRqmEncodeFile(String rqmEncodeFile) {
		this.rqmEncodeFile = rqmEncodeFile;
	}

	public CommonCodeVO getScdStsVO() {
		return scdStsVO;
	}

	public void setScdStsVO(CommonCodeVO scdStsVO) {
		this.scdStsVO = scdStsVO;
	}

	public CommonCodeVO getRqmStsVO() {
		return rqmStsVO;
	}

	public void setRqmStsVO(CommonCodeVO rqmStsVO) {
		this.rqmStsVO = rqmStsVO;
	}

	public ProjectVO getProjectVO() {
		return projectVO;
	}

	public void setProjectVO(ProjectVO projectVO) {
		this.projectVO = projectVO;
	}

	public EmployeeVO getDvlrpVO() {
		return dvlrpVO;
	}

	public void setDvlrpVO(EmployeeVO dvlrpVO) {
		this.dvlrpVO = dvlrpVO;
	}

	public EmployeeVO getCfrmrVO() {
		return cfrmrVO;
	}

	public void setCfrmrVO(EmployeeVO cfrmrVO) {
		this.cfrmrVO = cfrmrVO;
	}

	public EmployeeVO getTstrVO() {
		return tstrVO;
	}

	public void setTstrVO(EmployeeVO tstrVO) {
		this.tstrVO = tstrVO;
	}

	public EmployeeVO getCrtrIdVO() {
		return crtrIdVO;
	}

	public void setCrtrIdVO(EmployeeVO crtrIdVO) {
		this.crtrIdVO = crtrIdVO;
	}

	public EmployeeVO getMdfrVO() {
		return mdfrVO;
	}

	public void setMdfrVO(EmployeeVO mdfrVO) {
		this.mdfrVO = mdfrVO;
	}
	

}