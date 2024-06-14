package com.ktdsuniversity.edu.pms.team.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.team.vo.TeamApprovalVO;

@Repository
public class TeamApprovalDaoImpl extends SqlSessionDaoSupport implements TeamApprovalDao{
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertTeamApprovalRequest(TeamApprovalVO teamApprovalVO) {
		return getSqlSession().insert(TeamApprovalDao.NAME_SPACE + ".insertTeamApprovalRequest",teamApprovalVO);
	}
	
}
