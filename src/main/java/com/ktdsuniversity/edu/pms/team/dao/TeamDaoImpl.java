package com.ktdsuniversity.edu.pms.team.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

@Repository
public class TeamDaoImpl extends SqlSessionDaoSupport implements TeamDao{

	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int createNewTeam(TeamVO teamVO) {
		return getSqlSession().insert(TeamDao.NAME_SPACE + ".createNewTeam", teamVO);
	}

}
