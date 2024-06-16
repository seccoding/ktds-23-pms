package com.ktdsuniversity.edu.pms.supply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.supply.vo.SearchSupplyVO;
import com.ktdsuniversity.edu.pms.supply.vo.SupplyApprovalVO;

@Repository
public class SupplyApprovalDaoImpl extends SqlSessionDaoSupport implements SupplyApprovalDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int searchSupplyAllApprovalLogCount(SearchSupplyVO searchSupplyVO) {
		return getSqlSession().selectOne(SupplyApprovalDao.NAME_SPACE + ".searchSupplyAllApprovalLogCount", searchSupplyVO);
	}
	
	@Override
	public List<SupplyApprovalVO> searchAllApprovalLog(SearchSupplyVO searchSupplyVO) {
		return getSqlSession().selectList(SupplyApprovalDao.NAME_SPACE + ".searchAllApprovalLog", searchSupplyVO);
	}
	
	@Override
	public int insertSupplyApprovalRequest(SupplyApprovalVO supplyApprovalVO) {
		return getSqlSession().insert(SupplyApprovalDao.NAME_SPACE + ".insertSupplyApprovalRequest", supplyApprovalVO);
	}

	@Override
	public SupplyApprovalVO getSupplyApprovalByPK(String splApprId) {
		return getSqlSession().selectOne(SupplyApprovalDao.NAME_SPACE + ".getSupplyApprovalByPK", splApprId);
	}

	@Override
	public int updateOneSupplyApprovalYnToYByPK(String splApprId) {
		return getSqlSession().update(SupplyApprovalDao.NAME_SPACE + ".updateOneSupplyApprovalYnToYByPK", splApprId);
	}

}
