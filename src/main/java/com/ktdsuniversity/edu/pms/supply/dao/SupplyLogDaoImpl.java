package com.ktdsuniversity.edu.pms.supply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyLogVO;

@Repository
public class SupplyLogDaoImpl extends SqlSessionDaoSupport implements SupplyLogDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int searchSupplyLogAllCount(SearchSupplyVO searchSupplyVO) {
		return getSqlSession().selectOne(SupplyLogDao.NAME_SPACE + ".searchSupplyLogAllCount", searchSupplyVO);
	}

	@Override
	public List<SupplyLogVO> searchAllSupplyLog(SearchSupplyVO searchSupplyVO) {
		return getSqlSession().selectList(SupplyLogDao.NAME_SPACE + ".searchAllSupplyLog", searchSupplyVO);
	}

}
