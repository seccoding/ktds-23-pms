package com.ktdsuniversity.edu.pms.supply.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.supply.vo.SupplyApprovalVO;

@Repository
public class SupplyApprovalDaoImpl extends SqlSessionDaoSupport implements SupplyApprovalDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertSupplyRegistrationRequest(SupplyApprovalVO supplyApprovalVO) {
		return getSqlSession().insert(SupplyApprovalDao.NAME_SPACE + ".insertSupplyRegistrationRequest", supplyApprovalVO);
	}

}
