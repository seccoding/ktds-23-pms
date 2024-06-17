package com.ktdsuniversity.edu.pms.knowledge.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeRecommendVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.SearchKnowledgeVO;

public interface KnowledgeService {

	public KnowledgeListVO getAllKnowledge();
	
	public KnowledgeListVO searchAllKnowledge(SearchKnowledgeVO searchKnowledgeVO);

	public KnowledgeVO getOneKnowledge(String knlId, boolean isIncrease);
	
	// 추천
//	public int recommendOneKnowledge(String knlId);
	
	boolean updateRecommend(KnowledgeRecommendVO knowledgeRecommendVO);

	public int getKnowledgeRecommendCount(String knlId);

	public boolean createNewKnowledge(KnowledgeVO knowledgeVO, MultipartFile file);

	public boolean updateOneKnowledge(KnowledgeVO knowledgeVO, MultipartFile file);

	public boolean deleteOneKnowledge(String knlId);

	public boolean createMassiveKnowledge(MultipartFile excelFile);
	
	public boolean deleteManyKnowledge(List<String> deleteItems);

	public String findid(String kinId);

}
