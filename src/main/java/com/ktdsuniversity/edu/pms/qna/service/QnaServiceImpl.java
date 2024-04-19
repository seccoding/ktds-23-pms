package com.ktdsuniversity.edu.pms.qna.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeServiceImpl;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.qna.dao.QnaDao;
import com.ktdsuniversity.edu.pms.qna.vo.QnaListVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaVO;
import com.ktdsuniversity.edu.pms.qna.vo.SearchQnaVO;

@Service
public class QnaServiceImpl implements QnaService{
	
	Logger logger = LoggerFactory.getLogger(QnaServiceImpl.class);
	
	@Autowired
	private QnaDao qnaDao;
	
	@Autowired
	private FileHandler fileHandler;


	@Override
	public QnaListVO getAllQna() {
		
		int qnaCount = this.qnaDao.getAllQnaCount();
		List<QnaVO> qnaList = this.qnaDao.getAllQna();
		
		QnaListVO qnaListVO = new QnaListVO();
		qnaListVO.setQnaCnt(qnaCount);
		qnaListVO.setQnaList(qnaList);
		
		return qnaListVO;
	}
	
	@Override
	public QnaListVO searchAllQna(SearchQnaVO searchQnaVO) {

		return null;
	}

	@Override
	public QnaVO getOneQna(String qaId, boolean isIncrease) {

		QnaVO qnaVO = this.qnaDao.selectOneQna(qaId);
		
		// 게시글 조회한게 결과가 없다면.예외 발생시키기
		if (qnaVO == null) {
//			throw new PageNotFoundException(); TO DO!!
		}
		
		// 조회수 1증가
		if(isIncrease) {
			int updatedCount = this.qnaDao.increaseViewCount(qaId);
		}
		
		return qnaVO;
	}

	
	
	
	
	
	
	
	
	

}
