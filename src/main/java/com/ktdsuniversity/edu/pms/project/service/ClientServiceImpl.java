package com.ktdsuniversity.edu.pms.project.service;

import java.util.List;

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

	@Override
	public List<ClientVO> getAllClient() {
		List<ClientVO> clientList = this.clientDao.getAllClient();
		return clientList;
	}

	@Override
	public ClientVO getClientOfProject(String clntInfo) {
		return this.clientDao.getClientOfProject(clntInfo);
	}

	@Override
	public boolean modifyClient(ClientVO clientVO) {
		return this.clientDao.modifyClient(clientVO) > 0;
	}
}
