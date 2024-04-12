package com.ktdsuniversity.edu.pms.requirement.service;

import java.util.List;

import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;

public interface RequirementService {

	public List<RequirementVO> getAllRequirement(String prjId);

	public RequirementVO getOneRequirement(String rqmId);

	public boolean updateRequirement(RequirementVO requirementVO);

	public boolean updateDelayRequirement(String rqmId);

	public boolean checkEssentialElement(RequirementVO requirementVO);

}
