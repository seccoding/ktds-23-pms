package com.ktdsuniversity.edu.pms.output.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.output.vo.OutputSearchVO;
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
	public List<OutputVO> searchAllOutPutList(OutputSearchVO outputSearchVO) {
		return getSqlSession().selectList(NAME_SPACE+".searchAllOutPutList", outputSearchVO);
	}


	@Override
	public int getOutputCnt() {
		return getSqlSession().selectOne(NAME_SPACE+".getOutputCnt");
	}
	
	@Override
	public int searchOutputCnt(OutputSearchVO outputSearchVO) {
		return getSqlSession().selectOne(NAME_SPACE + ".searchOutputCnt",outputSearchVO);
	}


	@Override
	public int insertOneOutput(OutputVO outputVO) {
		return getSqlSession().insert(NAME_SPACE+".insertOneOutput", outputVO);
	}


	@Override
	public OutputVO getOneOutput(String outId) {
		return getSqlSession().selectOne(NAME_SPACE+".getOneOutput", outId);
	}


	@Override
	public int deleteOneOutput(String outId) {
		return getSqlSession().update(NAME_SPACE+".deleteOneOutput", outId);
	}


	@Override
	public int updateOneOutput(OutputVO outputVO) {
		return getSqlSession().update(NAME_SPACE+".updateOneOutput", outputVO);
	}


	@Override
	public OutputVO getOneOutputByPoutId(String outPId) {
		
		return getSqlSession().selectOne(NAME_SPACE+".getOneOutputByPoutId", outPId);
	}





	
}
