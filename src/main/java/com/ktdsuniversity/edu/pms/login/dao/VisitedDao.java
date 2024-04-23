package com.ktdsuniversity.edu.pms.login.dao;

import com.ktdsuniversity.edu.pms.login.vo.VisitedVO;

public interface VisitedDao {
	public String VISITED_SPACE = "com.ktdsuniversity.edu.pms.login.dao.VisitedDao";
	
	public int insertOneEmpVisitedHistory(VisitedVO visitedVO);
}
