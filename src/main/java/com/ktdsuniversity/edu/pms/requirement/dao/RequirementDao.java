package com.ktdsuniversity.edu.pms.requirement.dao;

import java.util.List;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;

public interface RequirementDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.requirement.dao.RequirementDao";

	public List<RequirementVO> getAllRequirement(String prjId);

	public RequirementVO getOneRequirement(String rqmId);

	public int insertOneRequirement(RequirementVO requirementVO);

	public int updateOneRequirement(String rqmId);

	public int updateDelayOneRequirement(RequirementVO requirementVO);

}
