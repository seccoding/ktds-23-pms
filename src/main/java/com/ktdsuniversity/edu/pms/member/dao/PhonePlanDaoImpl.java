package com.ktdsuniversity.edu.pms.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.member.vo.PhonePlanVO;

@Repository
public class PhonePlanDaoImpl extends SqlSessionDaoSupport implements PhonePlanDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<PhonePlanVO> selectAllPhonePlans() {
		return getSqlSession().selectList(PhonePlanDao.NAME_SPACE + ".selectAllPhonePlans");
	}
}
