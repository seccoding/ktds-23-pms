package com.ktdsuniversity.edu.pms.knowledge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.exceptions.CreationException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeReplyDao;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeReplyVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.ReplyRecommandVO;

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
	
	// 댓글 추천	
	@Override
	public boolean updateReplyRecommend(ReplyRecommandVO replyRecommandvo) {

		ReplyRecommandVO  originKnowledgeReplyRecommendVO=knowledgeReplyDao.selectOneReplyRecommend(replyRecommandvo);
	
		if(originKnowledgeReplyRecommendVO==null) {
			boolean isInsert=knowledgeReplyDao.insertOneReplyRecommend(replyRecommandvo)>0;
			if(isInsert) {
				boolean isRecommend = knowledgeReplyDao.replyRecommandCount(replyRecommandvo.getReprplid())>0;
				// 댓글에 횟수 증가 				
				boolean str= this.knowledgeReplyDao.replyCount(replyRecommandvo.getReprplid())>0;
				return isRecommend;
			
			}
			else {
				throw new CreationException();
			}
			
		}
		else {
			return false;
		}
		
		
		
	}
	// 재댓글
	@Override
	public List<KnowledgeReplyVO> getAllreReplies(KnowledgeReplyVO knowledgeReplyVO) {
		// TODO Auto-generated method stub
		return this.knowledgeReplyDao.getAllreReply(knowledgeReplyVO);
	}

	@Override
	public String findReplyId(String id) {
		// TODO Auto-generated method stub
		return this.knowledgeReplyDao.findEmpid(id);
	}
	
		
	

	

	

	
	
}
