package com.ktdsuniversity.edu.pms.knowledge.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;


@Repository
public class KnowledgeDaoImpl extends SqlSessionDaoSupport implements KnowledgeDao {
	
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	
	@Override
	public int getAllKnowledgeCount() {
		return getSqlSession().selectOne(KnowledgeDao.NAME_SPACE + ".getAllKnowledgeCount");
	}

	@Override
	public List<KnowledgeVO> getAllKnowledge() {
		return getSqlSession().selectList(KnowledgeDao.NAME_SPACE + ".getAllKnowledge");
	}


	@Override
	public KnowledgeVO selectOneKnowledge(String knowledgeId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int increaseViewCount(String knowledgeId) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	

}
