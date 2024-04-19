package com.ktdsuniversity.edu.pms.qna.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QnaDaoImpl extends SqlSessionDaoSupport implements QnaDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate (SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	

}
