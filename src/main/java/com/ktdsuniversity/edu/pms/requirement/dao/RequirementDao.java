package com.ktdsuniversity.edu.pms.requirement.dao;

import java.util.List;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.requirement.vo.DelayAcessVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementSearchVO;

public interface RequirementDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.requirement.dao.RequirementDao";
	
	public int getAllCount(RequirementSearchVO requirementSearchVO);

	public List<RequirementVO> getAllRequirement();
	
	public List<RequirementVO> getAllRequirement(RequirementSearchVO requirementSearchVO);

	public RequirementVO getOneRequirement(String rqmId);
	

	public int insertOneRequirement(RequirementVO requirementVO);
	

	public int updateOneRequirement(RequirementVO requirementVO);


	public int deleteReRequirement(RequirementVO requirementVO);

	public int delayRequirement(RequirementVO requirementVO);
	
	public int updateDelayOneRequirement(DelayAcessVO delayAcessVO);

	public List<RequirementVO> getAllRequirementByprjId(String prjId);

	public List<RequirementVO> searchAllRequirement(RequirementSearchVO requirementSearchVO);
	
	public List<RequirementVO> getAllRequirementByTeammateId (String empId);

	public int updateTestResult(RequirementVO requirementVO);


}
