package com.ktdsuniversity.edu.pms.team.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.team.vo.TeamVO;

public interface TeamDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.team.dao.TeamDao";

	public int createNewTeam(TeamVO teamVO);

	public TeamVO getOneTeam(String teamId);

	public int updateOneTeam(TeamVO teamVO);

	public List<TeamVO> getOnlyTeam();

}
