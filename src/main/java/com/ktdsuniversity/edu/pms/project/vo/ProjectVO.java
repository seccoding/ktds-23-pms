package com.ktdsuniversity.edu.pms.project.vo;

import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;

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
    private String reqYn;
    private String isYn;
    private String knlYn;
    private String qaYn;
    private String outYn;
    private String crtrId;
    private String crtDt;
    private String mdfId;
    private String mdfDt;

    private CommonCodeVO prjStsCode;
    private DepartmentVO deptVO;

    private List<ProjectTeammateVO> projectTeammateList;

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

    public String getReqYn() {
        return reqYn;
    }

    public void setReqYn(String reqYn) {
        this.reqYn = reqYn;
    }

    public String getIsYn() {
        return isYn;
    }

    public void setIsYn(String isYn) {
        this.isYn = isYn;
    }

    public String getKnlYn() {
        return knlYn;
    }

    public void setKnlYn(String knlYn) {
        this.knlYn = knlYn;
    }

    public String getQaYn() {
        return qaYn;
    }

    public void setQaYn(String qaYn) {
        this.qaYn = qaYn;
    }

    public String getOutYn() {
        return outYn;
    }

    public void setOutYn(String outYn) {
        this.outYn = outYn;
    }

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
