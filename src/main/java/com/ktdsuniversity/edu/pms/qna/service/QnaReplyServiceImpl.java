package com.ktdsuniversity.edu.pms.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.qna.dao.QnaReplyDao;
import com.ktdsuniversity.edu.pms.qna.vo.QnaReplyVO;

@Service
public class QnaReplyServiceImpl implements QnaReplyService {

	@Autowired
	private QnaReplyDao qnaReplyDao;
	
	@Override
	public List<QnaReplyVO> getAllReplies(QnaReplyVO qnaReplyVO) {
		return this.qnaReplyDao.getAllReplies(qnaReplyVO);
	}
	
	@Transactional
	@Override
	public boolean createNewReply(QnaReplyVO qnaReplyVO) {
		return this.qnaReplyDao.createNewQnaReply(qnaReplyVO) > 0;
	}

	@Transactional
	@Override
	public boolean deleteOneReply(String rplId, String empId) {
		QnaReplyVO qnaReplyVO = this.qnaReplyDao.getOneReply(rplId);
		if (!empId.equals(qnaReplyVO.getCrtrId())) {
			throw new PageNotFoundException();
		}
		return this.qnaReplyDao.deleteOneQnaReply(rplId) > 0;
	}

	@Transactional
	@Override
	public boolean modifyOneReply(QnaReplyVO qnaReplyVO) {
		QnaReplyVO originalQnaReplyVO = this.qnaReplyDao.getOneReply(qnaReplyVO.getRplId());
		if (!originalQnaReplyVO.getCrtrId().equals(qnaReplyVO.getCrtrId())) {
			throw new PageNotFoundException();
		}
		return this.qnaReplyDao.modifyOneQnaReply(originalQnaReplyVO) > 0;
	}
}
