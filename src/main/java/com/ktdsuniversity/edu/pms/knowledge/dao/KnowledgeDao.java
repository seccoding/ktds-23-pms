package com.ktdsuniversity.edu.pms.knowledge.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.SearchKnowledgeVO;

public interface KnowledgeDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeDao";

	public int getAllKnowledgeCount();

	public List<KnowledgeVO> getAllKnowledge();

	public KnowledgeVO selectOneKnowledge(String knlId);

	public int increaseViewCount(String knlId);

	public int insertNewKnowledge(KnowledgeVO knowledgeVO);

	public int updateOneKnowledge(KnowledgeVO knowledgeVO);

	public int deleteOneKnowledge(String knlId);

	public int recommendOneKnowledge(String knlId);

	public int searchAllKnowledgeCount(SearchKnowledgeVO searchKnowledgeVO);

	public List<KnowledgeVO> searchAllKnowledge(SearchKnowledgeVO searchKnowledgeVO);

	public List<KnowledgeVO> selectManyKnowledge(List<Integer> deleteItems);

	public int deleteManyBoard(List<Integer> deleteItems);



}
