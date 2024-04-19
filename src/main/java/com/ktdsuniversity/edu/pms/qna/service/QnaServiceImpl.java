package com.ktdsuniversity.edu.pms.qna.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeServiceImpl;
import com.ktdsuniversity.edu.pms.qna.dao.QnaDao;

@Service
public class QnaServiceImpl implements QnaService{
	
	Logger logger = LoggerFactory.getLogger(QnaServiceImpl.class);
	
	@Autowired
	private QnaDao qnaDao;
	
	@Autowired
	private FileHandler fileHandler;

}
