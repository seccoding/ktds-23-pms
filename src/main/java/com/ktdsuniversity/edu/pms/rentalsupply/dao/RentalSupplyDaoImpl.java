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

	/**
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

	/**
	 * 대여품 한 개 상세 조회
	 */
	@Override
	public RentalSupplyVO selectOneRentalSupply(String rsplId) {
		return getSqlSession().selectOne(RentalSupplyDao.NAME_SPACE+ ".selectOneRentalSupply", rsplId);
	}
	
	@Override
	public List<RentalSupplyVO> selectAllRentalSupplyCategory() {
		return getSqlSession().selectList(RentalSupplyDao.NAME_SPACE + ".selectAllRentalSupplyCategory");
	}

	/**
	 * 대여품 등록
	 */
	@Override
	public int registerNewRentalSupply(RentalSupplyVO rentalSupplyVO) {
		return getSqlSession().insert(RentalSupplyDao.NAME_SPACE + ".registerNewRentalSupply", rentalSupplyVO);
	}

	/**
	 * 대여품 수정
	 */
	@Override
	public int updateOneRentalSupply(RentalSupplyVO rentalSupplyVO) {
		return getSqlSession().update(RentalSupplyDao.NAME_SPACE + ".updateOneRentalSupply", rentalSupplyVO);
	}

	/**
	 * 대여품 재고 수정
	 */
	@Override
	public int updateOneRentalSupplyStock(RentalSupplyVO rentalSupplyVO) {
		return getSqlSession().update(RentalSupplyDao.NAME_SPACE + ".updateOneRentalSupplyStock", rentalSupplyVO);
	}

	/**
	 * 대여품 삭제
	 */
	@Override
	public int deleteOneRentalSupply(RentalSupplyVO rentalSupplyVO) {
		return getSqlSession().update(RentalSupplyDao.NAME_SPACE + ".deleteOneRentalSupply", rentalSupplyVO);
	}
	
}
