package com.ktdsuniversity.edu.pms.commoncode.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;

@Repository
public class CommonCodeDaoImpl extends SqlSessionDaoSupport
		implements CommonCodeDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<CommonCodeVO> selectAllCommonCodeList() {
		return getSqlSession()
				.selectList(NAME_SPACE + ".selectAllCommonCodeList");
	}

	@Override
	public int insertNewCommonCode(CommonCodeVO commonCodeVO) {
		return getSqlSession().insert(NAME_SPACE + ".insertNewCommonCode",
				commonCodeVO);
	}

	@Override
	public int updateCommonCode(CommonCodeVO commonCodeVO) {
		return getSqlSession().update(NAME_SPACE + ".updateCommonCode",
				commonCodeVO);
	}

	@Override
	public int deleteCommonCode(CommonCodeVO commonCodeVO) {
		return getSqlSession().update(NAME_SPACE + ".deleteCommonCode",
				commonCodeVO);
	}

}
