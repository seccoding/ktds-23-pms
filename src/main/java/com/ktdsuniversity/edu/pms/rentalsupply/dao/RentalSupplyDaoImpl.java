package com.ktdsuniversity.edu.pms.rentalsupply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.rentalsupply.vo.RentalSupplyVO;
import com.ktdsuniversity.edu.pms.rentalsupply.vo.SearchRentalSupplyVO;

@Repository
public class RentalSupplyDaoImpl extends SqlSessionDaoSupport implements RentalSupplyDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	/*
	 * 대여품 전체 갯수
	 */
	@Override
	public int searchRentalSupplyAllCount(SearchRentalSupplyVO searchRentalSupplyVO) {
		return getSqlSession().selectOne(RentalSupplyDao.NAME_SPACE + ".searchRentalSupplyAllCount" ,searchRentalSupplyVO);
	}

	/**
	 * 대여품 전체 조회
	 */
	@Override
	public List<RentalSupplyVO> searchAllRentalSupply(SearchRentalSupplyVO searchRentalSupplyVO) {
		return getSqlSession().selectList(RentalSupplyDao.NAME_SPACE + ".searchAllRentalSupply" , searchRentalSupplyVO);
	}
	
	
}
