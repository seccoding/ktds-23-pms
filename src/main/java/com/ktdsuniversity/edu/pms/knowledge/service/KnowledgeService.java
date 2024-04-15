package com.ktdsuniversity.edu.pms.knowledge.service;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;

public interface KnowledgeService {

	public KnowledgeListVO getAllKnowledge();

	public KnowledgeVO getOneKnowledge(String knlId, boolean isIncrease);

	public boolean createNewKnowledge(KnowledgeVO knowledgeVO, MultipartFile file);

}
