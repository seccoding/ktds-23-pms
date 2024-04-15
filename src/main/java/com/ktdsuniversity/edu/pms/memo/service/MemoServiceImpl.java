package com.ktdsuniversity.edu.pms.memo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.memo.dao.MemoDao;

@Service
public class MemoServiceImpl implements MemoService{
	
	@Autowired
	private MemoDao memoDao;
	
	
}
