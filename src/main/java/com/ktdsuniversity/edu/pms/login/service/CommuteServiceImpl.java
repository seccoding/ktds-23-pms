package com.ktdsuniversity.edu.pms.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.login.dao.CommuteDao;
import com.ktdsuniversity.edu.pms.login.vo.CommuteListVO;
import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;

@Service
public class CommuteServiceImpl implements CommuteService {

	@Autowired
	private CommuteDao commuteDao;

	@Override
	public CommuteListVO getAllCommuteData(CommuteVO commuteVO) {

		CommuteListVO commuteListVO = new CommuteListVO();
		commuteListVO.setCommuteList(commuteDao.getAllCommuteData(commuteVO));
		return commuteListVO;
	}

	@Override
	public CommuteListVO getAllCommuteDataByEmpId(CommuteVO commuteVO) {
		CommuteListVO commuteListVO = new CommuteListVO();
		commuteListVO.setCommuteList(commuteDao.getAllCommuteDataByEmpId(commuteVO));
		return commuteListVO;
	}

}
