package com.ktdsuniversity.edu.pms.knowledge.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeRecommendVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.SearchKnowledgeVO;


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
	public KnowledgeVO selectOneKnowledge(String knlId) {
		return getSqlSession().selectOne(KnowledgeDao.NAME_SPACE + ".selectOneKnowledge", knlId);
	}


	@Override
	public int increaseViewCount(String knlId) {
		return getSqlSession().update(KnowledgeDao.NAME_SPACE + ".increaseViewCount", knlId);
	}


	@Override
	public int insertNewKnowledge(KnowledgeVO knowledgeVO) {
		return getSqlSession().insert(KnowledgeDao.NAME_SPACE + ".insertNewKnowledge", knowledgeVO);
	}


	@Override
	public int updateOneKnowledge(KnowledgeVO knowledgeVO) {
		return getSqlSession().update(KnowledgeDao.NAME_SPACE + ".updateOneKnowledge", knowledgeVO);
	}


	@Override
	public int deleteOneKnowledge(String knlId) {
		return getSqlSession().update(KnowledgeDao.NAME_SPACE + ".deleteOneKnowledge", knlId);
	}


	@Override
	public int recommendOneKnowledge(String knlId) {
		return getSqlSession().update(KnowledgeDao.NAME_SPACE + ".recommendOneKnowledge", knlId);
	}
	
	// 추천
	@Override
	public KnowledgeRecommendVO selectOneRecommend(KnowledgeRecommendVO knowledgeRecommendVO) {
		return getSqlSession().selectOne(KnowledgeDao.NAME_SPACE + ".selectOneRecommend", knowledgeRecommendVO);
	}
	
	// 추천
	@Override
	public int insertOneRecommend(KnowledgeRecommendVO knowledgeRecommendVO) {
		return getSqlSession().insert(KnowledgeDao.NAME_SPACE + ".insertOneRecommend", knowledgeRecommendVO);
	}

	// 추천
	@Override
	public int selectOneRecommendCount(String knlId) {
		return getSqlSession().selectOne(KnowledgeDao.NAME_SPACE + ".selectOneRecommendCount", knlId);
	}


	@Override
	public int searchAllKnowledgeCount(SearchKnowledgeVO searchKnowledgeVO) {
		return getSqlSession().selectOne(KnowledgeDao.NAME_SPACE + ".searchAllKnowledgeCount", searchKnowledgeVO);
	}


	@Override
	public List<KnowledgeVO> searchAllKnowledge(SearchKnowledgeVO searchKnowledgeVO) {
		return getSqlSession().selectList(KnowledgeDao.NAME_SPACE + ".searchAllKnowledge", searchKnowledgeVO);
	}


	@Override
	public List<KnowledgeVO> selectManyKnowledge(List<String> deleteItems) {
		return getSqlSession().selectList(KnowledgeDao.NAME_SPACE + ".selectManyKnowledge", deleteItems);
	}


	@Override
	public int deleteManyKnowledge(List<String> deleteItems) {

		return getSqlSession().update(KnowledgeDao.NAME_SPACE + ".deleteManyKnowledge", deleteItems);
	}


	@Override
	public String findkindId(String kinId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(KnowledgeDao.NAME_SPACE+".findkindId", kinId);
	}





	
	
	
}
