package com.ktdsuniversity.edu.pms.login.service;

import java.util.List;

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
	public CommuteListVO getAllCommuteData() {

		List<CommuteVO> commuteList = this.commuteDao.getAllCommuteData();
		CommuteListVO commuteListVO = new CommuteListVO();
		commuteListVO.setCommuteList(commuteList);
		return commuteListVO;
	}

	@Override
	public CommuteListVO getAllCommuteDataByEmpId() {
		
		List<CommuteVO> commuteList = this.commuteDao.getAllCommuteDataByEmpId();
		CommuteListVO commuteListVO = new CommuteListVO();
		commuteListVO.setCommuteList(commuteList);
		return commuteListVO;
	}

}
