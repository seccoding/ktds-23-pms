package com.ktdsuniversity.edu.pms.knowledge.service;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeRecommendVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;

public interface KnowledgeRecommendService {

//	int getKnowledgerecommendCount(String knlId);

//	boolean hasEmployeeRecommended(String crtrId);

//	void saveRecommendation(KnowledgeRecommendVO knowledgeRecommendVO);

	boolean updateRecommend(KnowledgeRecommendVO knowledgeRecommendVO);

	int getKnowledgeRecommendCount(String knlId);


}
