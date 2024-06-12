package com.ktdsuniversity.edu.pms.supply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyVO;

@Repository
public class SupplyDaoImpl extends SqlSessionDaoSupport implements SupplyDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int searchSupplyAllCount(SearchSupplyVO searchSupplyVO) {
		return getSqlSession().selectOne(SupplyDao.NAME_SPACE + ".searchSupplyAllCount", searchSupplyVO);
	}

	@Override
	public List<SupplyVO> searchAllSupply(SearchSupplyVO searchSupplyVO) {
		return getSqlSession().selectList(SupplyDao.NAME_SPACE + ".searchAllSupply", searchSupplyVO);
	}

	@Override
	public SupplyVO selectOneSupply(String splId) {
		return getSqlSession().selectOne(SupplyDao.NAME_SPACE + ".selectOneSupply", splId);
	}
	
	@Override
	public List<SupplyVO> selectAllSupplyCategory() {
		return getSqlSession().selectList(SupplyDao.NAME_SPACE + ".selectAllSupplyCategory");
	}

	@Override
	public int registerNewSupply(SupplyVO supplyVO) {
		return getSqlSession().insert(SupplyDao.NAME_SPACE + ".registerNewSupply", supplyVO);
	}

	@Override
	public int updateOneSupply(SupplyVO supplyVO) {
		return getSqlSession().update(SupplyDao.NAME_SPACE + ".updateOneSupply", supplyVO);
	}
	
	@Override
	public int updateOneSupplyStock(SupplyVO supplyVO) {
		return getSqlSession().update(SupplyDao.NAME_SPACE + ".updateOneSupplyStock", supplyVO);
	}

	@Override
	public int deleteOneSupply(SupplyVO supplyVO) {		
		return getSqlSession().update(SupplyDao.NAME_SPACE + ".deleteOneSupply", supplyVO);
	}

}
