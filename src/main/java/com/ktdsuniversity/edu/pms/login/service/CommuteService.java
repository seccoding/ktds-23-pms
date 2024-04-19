package com.ktdsuniversity.edu.pms.login.service;

import com.ktdsuniversity.edu.pms.login.vo.CommuteListVO;
import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;

public interface CommuteService {

	CommuteListVO getAllCommuteData();

	CommuteListVO getAllCommuteDataByEmpId();

}
