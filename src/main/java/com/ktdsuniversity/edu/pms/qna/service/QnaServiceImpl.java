package com.ktdsuniversity.edu.pms.qna.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.FileHandler.StoredFile;
import com.ktdsuniversity.edu.pms.exceptions.CreationException;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeServiceImpl;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeRecommendVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;
import com.ktdsuniversity.edu.pms.qna.dao.QnaDao;
import com.ktdsuniversity.edu.pms.qna.vo.QnaListVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaRecommendVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaVO;
import com.ktdsuniversity.edu.pms.qna.vo.SearchQnaVO;

@Service
public class QnaServiceImpl implements QnaService{
	
	Logger logger = LoggerFactory.getLogger(QnaServiceImpl.class);
	
	@Autowired
	private QnaDao qnaDao;
	
	@Autowired
	private FileHandler fileHandler;

	@Transactional
	@Override
	public QnaListVO getAllQna() {
		
		int qnaCount = this.qnaDao.getAllQnaCount();
		List<QnaVO> qnaList = this.qnaDao.getAllQna();
		
		QnaListVO qnaListVO = new QnaListVO();
		qnaListVO.setQnaCnt(qnaCount);
		qnaListVO.setQnaList(qnaList);
		
		return qnaListVO;
	}
	
	// 검색
	@Transactional
	@Override
	public QnaListVO searchAllQna(SearchQnaVO searchQnaVO) {
		
		int qnaCount = this.qnaDao.searchAllQnaCount(searchQnaVO);
		searchQnaVO.setPageCount(qnaCount);
		
		List<QnaVO> qnaList = this.qnaDao.searchAllQna(searchQnaVO);
		
		QnaListVO qnaListVO = new QnaListVO();
		qnaListVO.setQnaCnt(qnaCount);
		qnaListVO.setQnaList(qnaList);

		return qnaListVO;
	}

	@Transactional
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

	@Transactional
	@Override
	public boolean createNewQna(QnaVO qnaVO, MultipartFile file) {
		
		// 파일 업로드 확인
		if(file != null && !file.isEmpty()) {
			StoredFile storedfile = fileHandler.storeFile(file);
			// 정상적으로 업로드 한 경우
			if(storedfile != null) {
				// 난독화 파일
				qnaVO.setFileName(storedfile.getRealFileName());
				// 사용자가 업로드한 파일
				qnaVO.setOriginFileName(storedfile.getFileName());
			}
		}
		
		int insertedCount = this.qnaDao.insertNewQna(qnaVO);
		
		return insertedCount > 0;
	}
	
	@Transactional
	@Override
	public boolean updateOneQna(QnaVO qnaVO, MultipartFile file) {
		
		// 파일 업로드 확인
		if(file != null && !file.isEmpty()) {
			// 기존의 게시글 내용을 확인
			// 사용자가 파일을 업로드 한 경우, 기존에 업로드되었던 파일을 삭제하기 위해서!
			// 기존에 첨부된 파일의 존재여부를 확인
			QnaVO originalQnaVO = this.qnaDao.selectOneQna(qnaVO.getQaTtl());
			
			// 기존 게시글에 첨부된 파일이 있는지 확인.
			if(originalQnaVO != null) {
				
				// 기존 게시글에 첨부된 파일의 이름을 받아욘다.
				String storedFileName = originalQnaVO.getFileName();
				// 첨부된 파일의 이름이 있는지 확인한다.
				// 만약 첨부된 파일의 이름이 있다면, 이 게시글은 파일이 첨부되었던 게시글이다.
				if (storedFileName != null && storedFileName.length() > 0) {
					// 첨부된 파일을 삭제한다.
					this.fileHandler.deleteFileByFileName(storedFileName);
				}
			}
			
			// 사용자가 업로드한 파일을 서버에 저장한다.
			StoredFile storedFile = this.fileHandler.storeFile(file);
			qnaVO.setOriginFileName(storedFile.getFileName());
			qnaVO.setFileName(storedFile.getRealFileName());
			
		}
		
		int updatedCount = this.qnaDao.updateOneQna(qnaVO);
		return updatedCount > 0;
	}
		
		
	@Transactional
	@Override
	public boolean deleteOneQna(String qaId) {
		
		QnaVO originalQnaVO = this.qnaDao.selectOneQna(qaId);
		
		if(originalQnaVO != null) {
			
			// 기존 게시글에 첨부된 파일의 이름을 받아옴.
			String storedFileName = originalQnaVO.getFileName();
			// 첨부된 파일의 이름이 있는지 확인한다.
			// 만약 첨부된 파일의 이름이 있다면, 이 게시글은 파일이 첨부되었던 게시글이다.
			if (storedFileName != null && storedFileName.length() > 0) {
				// 첨부된 파일을 삭제
				this.fileHandler.deleteFileByFileName(storedFileName);
			}
		}
	
		int deletedCount = this.qnaDao.deleteOneQna(qaId);
	
		return deletedCount > 0;
	}
	
	@Transactional
	@Override
	public boolean deleteManyQna(List<String> deleteItems) {
		
		List<QnaVO> originalQnaList = this.qnaDao.selectManyQna(deleteItems);
		
		for (QnaVO qnaVO : originalQnaList ) {
			if(qnaVO != null) {
				String storedFileName = qnaVO.getFileName();
				
				if (storedFileName != null && storedFileName.length() > 0) {
					this.fileHandler.deleteFileByFileName(storedFileName);
				}
			}
		}
		
		int deletedCount = this.qnaDao.deleteManyQna(deleteItems);
		
		return deletedCount > 0;
	}
	
//	@Transactional
//	@Override
//	public int recommendOneQna(String qaId) {
//		
//		QnaVO originQna = this.qnaDao.selectOneQna(qaId);
//
//		return this.qnaDao.recommendOneQna(qaId);
//	}
	
	@Transactional
	@Override
	public int getQnaRecommendCount(String qaId) {
		return this.qnaDao.selectOneRecommendCount(qaId);
	}

	@Transactional
	@Override
	public boolean updateRecommend(QnaRecommendVO qnaRecommendVO) {
		// knowledgeRecommendVO를 파라미터로 하나의 추천 정보를 가져온다.
		QnaRecommendVO originQnaRecommendVO = qnaDao.selectOneRecommend(qnaRecommendVO);
		
		if(originQnaRecommendVO == null) {
//			추천한 기록이 없으면 실행됨
			boolean isInsert = this.qnaDao.insertOneRecommend(qnaRecommendVO) > 0;
			
			if (isInsert) {
				boolean isRecommend = this.qnaDao.recommendOneQna(qnaRecommendVO.getpPostId()) > 0;
				return isRecommend;
			} else {
				throw new CreationException();
			}
		} else {
//			기존에 추천 기록이 있다.
			return false;
		}
	}

//	@Transactional
//	@Override
//	public boolean cancelRecommend(QnaRecommendVO qnaRecommendVO) {
//		
//		QnaRecommendVO originQnaRecommendVO = qnaDao.selectOneRecommend(qnaRecommendVO);
//		
//		// 추천한 기록이 있으면 실행
//		if(originQnaRecommendVO != null) {
//			boolean isDelete = this.qnaDao.deleteOneRecommend(qnaRecommendVO) > 0;
//			
//			if(isDelete) {
//				boolean isCancelRecommend = this.qnaDao.cancelRecommendOneQna(qnaRecommendVO.getpPostId()) > 0;
//				return isCancelRecommend;
//			} else {
//				throw new CreationException();
//			}
//		}
//		
//		
//		return false;
//	}
	
	
	
	
	
}
