package com.ktdsuniversity.edu.pms.output.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class OutputDaoImpl extends SqlSessionDaoSupport implements OutputDao{
	
	@Autowired
	@Override
	public void setSqlSessionTemplate
	(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
}
