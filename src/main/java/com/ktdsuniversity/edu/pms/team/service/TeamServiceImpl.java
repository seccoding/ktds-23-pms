package com.ktdsuniversity.edu.pms.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.team.dao.TeamDao;
import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

@Service
public class TeamServiceImpl implements TeamService{

	@Autowired
	private TeamDao teamDao;
	
	@Override
	public boolean createNewTeam(TeamVO teamVO) {
		
		int insertedCount = this.teamDao.createNewTeam(teamVO);
		return insertedCount > 0;
	}

}
