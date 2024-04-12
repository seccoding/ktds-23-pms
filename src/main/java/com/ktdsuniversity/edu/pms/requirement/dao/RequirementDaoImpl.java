package com.ktdsuniversity.edu.pms.requirement.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;

@Repository
public class RequirementDaoImpl extends SqlSessionDaoSupport implements RequirementDao{
	@Autowired
	@Override
	public void setSqlSessionTemplate
	(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

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
	public int insertOneRequirement(RequirementVO requirementVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOneRequirement(String rqmId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDelayOneRequirement(RequirementVO requirementVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
