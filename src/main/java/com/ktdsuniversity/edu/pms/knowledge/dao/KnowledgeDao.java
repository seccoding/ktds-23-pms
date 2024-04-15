package com.ktdsuniversity.edu.pms.knowledge.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;

public interface KnowledgeDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeDao";

	public int getAllKnowledgeCount();

	public List<KnowledgeVO> getAllKnowledge();

	public KnowledgeVO selectOneKnowledge(String knlId);

	public int increaseViewCount(String knlId);

	public int insertNewKnowledge(KnowledgeVO knowledgeVO);

	public int updateOneKnowledge(KnowledgeVO knowledgeVO);

	public int deleteOneKnowledge(String knlId);



}
