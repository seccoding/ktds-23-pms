package com.ktdsuniversity.edu.pms.rentalsupply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyApprovalVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;

@Repository
public class RentalSupplyApprovalDaoImpl extends SqlSessionDaoSupport implements RentalSupplyApprovalDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int searchRentalSupplyAllApprovalLogCount(SearchRentalSupplyVO searchRentalSupplyVO) {
		return getSqlSession().selectOne(RentalSupplyApprovalDao.NAME_SPACE + ".searchRentalSupplyAllApprovalLogCount", searchRentalSupplyVO);
	}

	@Override
	public List<RentalSupplyApprovalVO> searchAllApprovalLog(SearchRentalSupplyVO searchRentalSupplyVO) {
		return getSqlSession().selectList(RentalSupplyApprovalDao.NAME_SPACE + ".searchAllApprovalLog", searchRentalSupplyVO);
	}

	@Override
	public int insertRentalSupplyApprovalRequest(RentalSupplyApprovalVO rentalSupplyApprovalVO) {
		return getSqlSession().insert(RentalSupplyApprovalDao.NAME_SPACE + ".insertRentalSupplyApprovalRequest", rentalSupplyApprovalVO);
	}

	@Override
	public RentalSupplyApprovalVO getRentalSupplyApprovalByPK(String rsplApprId) {
		return getSqlSession().selectOne(RentalSupplyApprovalDao.NAME_SPACE + ".getRentalSupplyApprovalByPK", rsplApprId);
	}

	@Override
	public int updateOneRentalSupplyApprovalYnToYByPK(String rsplApprId) {
		return getSqlSession().update(RentalSupplyApprovalDao.NAME_SPACE + ".updateOneRentalSupplyApprovalYnToYByPK", rsplApprId);
	}

	@Override
	public int updateOneRentalSupplyForReturn(RentalSupplyApprovalVO rentalSupplyApprovalVO) {
		return getSqlSession().update(RentalSupplyApprovalDao.NAME_SPACE + ".updateOneRentalSupplyForReturn", rentalSupplyApprovalVO);
	}

}
