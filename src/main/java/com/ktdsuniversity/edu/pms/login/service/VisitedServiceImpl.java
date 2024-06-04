package com.ktdsuniversity.edu.pms.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.login.dao.VisitedDao;
import com.ktdsuniversity.edu.pms.login.vo.VisitedVO;

@Service
public class VisitedServiceImpl implements VisitedService {

	@Autowired
	private VisitedDao visitedDao;
	
	@Override
	@Transactional
	public boolean insertOneEmpVisitedHistory(VisitedVO visitedVO) {
		return this.visitedDao.insertOneEmpVisitedHistory(visitedVO) > 0;
	}
}
