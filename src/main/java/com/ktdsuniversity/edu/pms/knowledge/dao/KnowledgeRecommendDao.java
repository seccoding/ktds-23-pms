package com.ktdsuniversity.edu.pms.knowledge.dao;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeRecommendVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;

public interface KnowledgeRecommendDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeRecommendDao";

	KnowledgeRecommendVO selectOneRecommend(KnowledgeRecommendVO knowledgeRecommendVO);

	int insertOneRecommend(KnowledgeRecommendVO knowledgeRecommendVO);

	int selectOneRecommendCount(String knlId);

}
