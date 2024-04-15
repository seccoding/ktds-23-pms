package com.ktdsuniversity.edu.pms.knowledge.service;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;

public interface KnowledgeService {

	public KnowledgeListVO getAllKnowledge();

	public KnowledgeVO getOneKnowledge(String knlId, boolean isIncrease, boolean isRecIncrease);

	public boolean createNewKnowledge(KnowledgeVO knowledgeVO, MultipartFile file);

	public boolean updateOneKnowledge(KnowledgeVO knowledgeVO, MultipartFile file);

	public boolean deleteOneKnowledge(String knlId);

}
