package com.ktdsuniversity.edu.pms.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.project.dao.ClientDao;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDao clientDao;
}
