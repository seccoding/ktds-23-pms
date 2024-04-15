package com.ktdsuniversity.edu.pms.requirement.dao;

import java.util.List;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.requirement.vo.DelayAcessVO;

public interface RequirementDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.requirement.dao.RequirementDao";

	public List<RequirementVO> getAllRequirement();

	public RequirementVO getOneRequirement(String rqmId);
	

	public int insertOneRequirement(RequirementVO requirementVO);
	

	public int updateOneRequirement(RequirementVO requirementVO);


	public int deleteReRequirement(RequirementVO requirementVO);

	public int delayRequirement(RequirementVO requirementVO);
	
	public int updateDelayOneRequirement(DelayAcessVO delayAcessVO);


}
