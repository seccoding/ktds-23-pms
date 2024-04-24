package com.ktdsuniversity.edu.pms.knowledge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeReplyDao;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeReplyVO;

@Service
public class KnowledgeReplyServiceImpl implements KnowledgeReplyService{

	@Autowired
	private KnowledgeReplyDao knowledgeReplyDao;

	@Override
	public List<KnowledgeReplyVO> getAllReplies(KnowledgeReplyVO knowledgeReplyVO) {
		return this.knowledgeReplyDao.getAllReplies(knowledgeReplyVO);
	}

	@Transactional
	@Override
	public boolean createNewReply(KnowledgeReplyVO knowledgeReplyVO) {
		return this.knowledgeReplyDao.createNewKnowledgeReply(knowledgeReplyVO) > 0;
	}

	@Transactional
	@Override
	public boolean deleteOneReply(String rplId, String empId) {
		KnowledgeReplyVO knowledgeReplyVO = this.knowledgeReplyDao.getOneReply(rplId);
		if (!empId.equals(knowledgeReplyVO.getCrtrId())) {
			throw new PageNotFoundException();
		}
		return this.knowledgeReplyDao.deleteOneKnowledgeReply(rplId) > 0;
	}

	@Transactional
	@Override
	public boolean modifyOneReply(KnowledgeReplyVO knowledgeReplyVO) {
		KnowledgeReplyVO originalknowledgeReplyVO = this.knowledgeReplyDao.getOneReply(knowledgeReplyVO.getRplId());
		if (!originalknowledgeReplyVO.getCrtrId().equals(knowledgeReplyVO.getCrtrId())) {
			throw new PageNotFoundException();
		}
		return this.knowledgeReplyDao.modifyOneKnowledgeReply(knowledgeReplyVO) > 0;
	}
}
