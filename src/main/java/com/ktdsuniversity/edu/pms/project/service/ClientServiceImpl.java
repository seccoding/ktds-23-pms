package com.ktdsuniversity.edu.pms.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.project.dao.ClientDao;
import com.ktdsuniversity.edu.pms.project.vo.ClientVO;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDao clientDao;

	@Transactional
	@Override
	public boolean createClient(ClientVO clientVO) {
		return this.clientDao.createClient(clientVO) > 0;
	}
}
