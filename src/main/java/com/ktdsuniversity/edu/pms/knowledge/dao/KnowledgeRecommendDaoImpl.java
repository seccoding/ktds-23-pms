package com.ktdsuniversity.edu.pms.knowledge.dao;

import org.mybatis.spring.SqlSessionTemplate;


import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeRecommendVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;

@Repository
public class KnowledgeRecommendDaoImpl  extends SqlSessionDaoSupport implements KnowledgeRecommendDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public KnowledgeRecommendVO selectOneRecommend(KnowledgeRecommendVO knowledgeRecommendVO) {
		return getSqlSession().selectOne(KnowledgeRecommendDao.NAME_SPACE + ".selectOneRecommend", knowledgeRecommendVO);
	}

	@Override
	public int insertOneRecommend(KnowledgeRecommendVO knowledgeRecommendVO) {
		return getSqlSession().insert(KnowledgeRecommendDao.NAME_SPACE + ".insertOneRecommend", knowledgeRecommendVO);
	}

	@Override
	public int selectOneRecommendCount(String knlId) {
		return getSqlSession().selectOne(KnowledgeRecommendDao.NAME_SPACE + ".selectOneRecommendCount", knlId);
	}



}
