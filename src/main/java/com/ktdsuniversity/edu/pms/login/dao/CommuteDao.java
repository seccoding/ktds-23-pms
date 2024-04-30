package com.ktdsuniversity.edu.pms.login.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;

public interface CommuteDao {

	public String COMMUTE_SPACE = "com.ktdsuniversity.edu.pms.login.dao.CommuteDao";

	public List<CommuteVO> getAllCommuteData(CommuteVO commuteVO);

	public List<CommuteVO> getAllCommuteDataByEmpId(CommuteVO commuteVO);

}
