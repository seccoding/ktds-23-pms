package com.ktdsuniversity.edu.pms.deptteam.vo;

import java.util.List;

public class TeamListVO {

	private int teamCnt;
	private List<TeamVO> teamList;

	public int getTeamCnt() {
		return teamCnt;
	}

	public void setTeamCnt(int teamCnt) {
		this.teamCnt = teamCnt;
	}

	public List<TeamVO> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<TeamVO> teamList) {
		this.teamList = teamList;
	}

}