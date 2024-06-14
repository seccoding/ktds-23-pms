package com.ktdsuniversity.edu.pms.project.vo;

import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;

import java.util.List;

public class ProjectVO {

    private String prjId;
    private String prjName;
    private String clntInfo;
    private String deptId;
    private String prjSts;
    private String strtDt;
    private String endDt;
    private String delYn;
//    private String reqYn;
//    private String isYn;
//    private String knlYn;
//    private String qaYn;
//    private String outYn;
    private String crtrId;
    private String crtDt;
    private String mdfId;
    private String mdfDt;
    private String srvSts;
    private String srvCrDate;
    private String srvEndDate;
    private String prjMemo;

    private CommonCodeVO prjStsCode;
    private DepartmentVO deptVO;
    private ClientVO clientVO;

    private ProjectTeammateVO pm;
    private int totalRequireCnt;
    private int requireCnt;

	private List<ProjectTeammateVO> projectTeammateList;
    private List<RequirementVO> projectRequirementsList;
    
    // [totalReqCnt, DoneReqCnt, DoneServCnt, DoneReviewCnt, totalIssueCnt, doneIssueCnt]
    private List<Integer> chartData;
    
    
    public List<Integer> getChartData() {
		return chartData;
	}

	public void setChartData(List<Integer> chartData) {
		this.chartData = chartData;
	}

	public ClientVO getClientVO() {
		return clientVO;
	}

	public void setClientVO(ClientVO clientVO) {
		this.clientVO = clientVO;
	}

	public int getTotalRequireCnt() {
		return totalRequireCnt;
	}

	public void setTotalRequireCnt(int totalRequireCnt) {
		this.totalRequireCnt = totalRequireCnt;
	}

	public int getRequireCnt() {
		return requireCnt;
	}

	public void setRequireCnt(int requireCnt) {
		this.requireCnt = requireCnt;
	}
    
    public ProjectTeammateVO getPm() {
    	return pm;
    }
    
    public void setPm(ProjectTeammateVO pm) {
    	this.pm = pm;
    }
    public String getPrjMemo() {
		return prjMemo;
	}

	public void setPrjMemo(String prjMemo) {
		this.prjMemo = prjMemo;
	}

	public String getSrvSts() {
		return srvSts;
	}

	public void setSrvSts(String srvSts) {
		this.srvSts = srvSts;
	}

	public String getSrvCrDate() {
		return srvCrDate;
	}

	public void setSrvCrDate(String srvCrDate) {
		this.srvCrDate = srvCrDate;
	}

	public String getSrvEndDate() {
		return srvEndDate;
	}

	public void setSrvEndDate(String srvEndDate) {
		this.srvEndDate = srvEndDate;
	}

	

    public List<RequirementVO> getProjectRequirementsList() {
		return projectRequirementsList;
	}

	public void setProjectRequirementsList(List<RequirementVO> projectRequirementsList) {
		this.projectRequirementsList = projectRequirementsList;
	}

	public String getPrjId() {
        return prjId;
    }

    public void setPrjId(String prjId) {
        this.prjId = prjId;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getClntInfo() {
        return clntInfo;
    }

    public void setClntInfo(String clntInfo) {
        this.clntInfo = clntInfo;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPrjSts() {
        return prjSts;
    }

    public void setPrjSts(String prjSts) {
        this.prjSts = prjSts;
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

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

//    public String getReqYn() {
//        return reqYn;
//    }
//
//    public void setReqYn(String reqYn) {
//        this.reqYn = reqYn;
//    }
//
//    public String getIsYn() {
//        return isYn;
//    }
//
//    public void setIsYn(String isYn) {
//        this.isYn = isYn;
//    }
//
//    public String getKnlYn() {
//        return knlYn;
//    }
//
//    public void setKnlYn(String knlYn) {
//        this.knlYn = knlYn;
//    }
//
//    public String getQaYn() {
//        return qaYn;
//    }
//
//    public void setQaYn(String qaYn) {
//        this.qaYn = qaYn;
//    }
//
//    public String getOutYn() {
//        return outYn;
//    }
//
//    public void setOutYn(String outYn) {
//        this.outYn = outYn;
//    }

    public String getCrtrId() {
        return crtrId;
    }

    public void setCrtrId(String crtrId) {
        this.crtrId = crtrId;
    }

    public String getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(String crtDt) {
        this.crtDt = crtDt;
    }

    public String getMdfId() {
        return mdfId;
    }

    public void setMdfId(String mdfId) {
        this.mdfId = mdfId;
    }

    public String getMdfDt() {
        return mdfDt;
    }

    public void setMdfDt(String mdfDt) {
        this.mdfDt = mdfDt;
    }

    public CommonCodeVO getPrjStsCode() {
        return prjStsCode;
    }

    public void setPrjStsCode(CommonCodeVO prjStsCode) {
        this.prjStsCode = prjStsCode;
    }

    public DepartmentVO getDeptVO() {
        return deptVO;
    }

    public void setDeptVO(DepartmentVO deptVO) {
        this.deptVO = deptVO;
    }

    public List<ProjectTeammateVO> getProjectTeammateList() {
        return projectTeammateList;
    }

    public void setProjectTeammateList(List<ProjectTeammateVO> projectTeammateList) {
        this.projectTeammateList = projectTeammateList;
    }
}
