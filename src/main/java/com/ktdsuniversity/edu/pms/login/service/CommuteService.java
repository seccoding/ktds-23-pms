package com.ktdsuniversity.edu.pms.login.service;

import com.ktdsuniversity.edu.pms.login.vo.CommuteListVO;

public interface CommuteService {

	CommuteListVO getAllCommuteData();

	CommuteListVO getAllCommuteDataByEmpId(String empId);

}
