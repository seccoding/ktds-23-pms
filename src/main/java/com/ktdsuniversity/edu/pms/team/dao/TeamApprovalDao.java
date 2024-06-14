package com.ktdsuniversity.edu.pms.team.dao;

import com.ktdsuniversity.edu.pms.team.vo.TeamApprovalVO;

public interface TeamApprovalDao {
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.team.dao.TeamApprovalDao";

	public int insertTeamApprovalRequest(TeamApprovalVO teamApprovalVO);
}
