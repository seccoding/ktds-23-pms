package com.ktdsuniversity.edu.pms.output.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.output.vo.OutputListVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputVO;


@Repository
public class OutputDaoImpl extends SqlSessionDaoSupport implements OutputDao{
	
	@Autowired
	@Override
	public void setSqlSessionTemplate
	(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}


	@Override
	public List<OutputVO> getAllOutputList() {
		return getSqlSession().selectList(NAME_SPACE+".getAllOutputList");
	}


	@Override
	public int getOutputCnt() {
		return getSqlSession().selectOne(NAME_SPACE+".getOutputCnt");
	}


	@Override
	public int updateOneOutput(OutputVO outputVO) {
		return getSqlSession().insert(NAME_SPACE+".updateOneOutput", outputVO);
	}
}
