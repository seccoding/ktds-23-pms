package com.ktdsuniversity.edu.pms.knowledge.service;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;

public interface KnowledgeService {

	public KnowledgeListVO getAllKnowledge();

	public KnowledgeVO getOneKnowledge(String knowledgeId, boolean isIncrease);

}
