package com.ktdsuniversity.edu.pms.supply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.supply.dao.SupplyDao;

@Service
public class SupplyServiceImpl implements SupplyService {
	
	@Autowired
	private SupplyDao supplyDao;

}
