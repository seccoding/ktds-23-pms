package com.ktdsuniversity.edu.pms.requirement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.requirement.dao.RequirementDao;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;

@Service
public class RequirementServiceImpl implements RequirementService{
	
	private RequirementDao requirementDao;

	@Override
	public List<RequirementVO> getAllRequirement(String prjId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequirementVO getOneRequirement(String rqmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateRequirement(RequirementVO requirementVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDelayRequirement(String rqmId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkEssentialElement(RequirementVO requirementVO) {
		// TODO Auto-generated method stub
		return false;
	}

}
