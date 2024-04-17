package com.ktdsuniversity.edu.pms.knowledge.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.FileHandler.StoredFile;
import com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeDao;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
	
	Logger logger = LoggerFactory.getLogger(KnowledgeServiceImpl.class);
	
	@Autowired
	private KnowledgeDao knowledgeDao;
	
	@Autowired
	private FileHandler fileHandler;

	@Override
	public KnowledgeListVO getAllKnowledge() {
		
		int knowledgeCount = this.knowledgeDao.getAllKnowledgeCount();
		List<KnowledgeVO> knowledgeList = this.knowledgeDao.getAllKnowledge();
		
		KnowledgeListVO knowledgeListVO = new KnowledgeListVO();
		knowledgeListVO.setKnowledgeCnt(knowledgeCount);
		knowledgeListVO.setKnowledgeList(knowledgeList);
		
		return knowledgeListVO;
	}

	
	/**
	 * 전달받은 파라미터의 게시글 정보를 조회해 반환한다.
	 * 게시글 조회 시, 게시글 조회 수도 1 증가
	 * @param knowledgeId 사용자가 조회하려는 id
	 * @param isIncrease 의 값이 true일 때, 조회수가 증가한다.
	 * @return 게시글 정보
	 */
	@Override
	public KnowledgeVO getOneKnowledge(String knowledgeId, boolean isIncrease) {	
		
		KnowledgeVO knowledgeVO = this.knowledgeDao.selectOneKnowledge(knowledgeId);
		
		// 게시글 조회한게 결과가 없다면.
		// 예외 발생시키기
		if (knowledgeVO == null) {
//			throw new PageNotFoundException(); TO DO!!
		}
		
		// 조회수 1 증가시키기
		if (isIncrease) {
			int updatedCount = this.knowledgeDao.increaseViewCount(knowledgeId);
		}
		
//		// 추천을 눌렀다면 추천 1 증가
//		if (isRecIncrease) {
//			int updatedCount2 = this.knowledgeDao.increaseViewCount(knowledgeId);
//		}
		
		return knowledgeVO;
	}

	
	@Override
	public boolean createNewKnowledge(KnowledgeVO knowledgeVO, MultipartFile file) {
		
		// 파일 업로드 확인
		if(file != null && !file.isEmpty()) {
			StoredFile storedfile = fileHandler.storeFile(file);
			
			// 정상적으로 업로드 한 경우
			if(storedfile != null) {
				// 난독화 파일
				knowledgeVO.setFileName(storedfile.getRealFileName());
				// 사용자가 업로드한 파일
				knowledgeVO.setOriginFileName(storedfile.getFileName());
			}
		}
		
		int insertedCount = this.knowledgeDao.insertNewKnowledge(knowledgeVO);
		
		return insertedCount > 0;
	}

	// 수정
	@Override
	public boolean updateOneKnowledge(KnowledgeVO knowledgeVO, MultipartFile file) {

		// 파일 업로드 확인
		if(file != null && !file.isEmpty()) {
			// 기존의 게시글 내용을 확인
			// 사용자가 파일을 업로드 한 경우, 기존에 업로드되었던 파일을 삭제하기 위해서!
			// 기존에 첨부된 파일의 존재여부를 확인해야한다.
			KnowledgeVO originalKnowledgeVO = this.knowledgeDao.selectOneKnowledge(knowledgeVO.getKnlId());
			
			// 기존 게시글에 첨부된 파일이 있는지 확인.
			if(originalKnowledgeVO != null) {
				
				// 기존 게시글에 첨부된 파일의 이름을 받아욘다.
				String storedFileName = originalKnowledgeVO.getFileName();
				// 첨부된 파일의 이름이 있는지 확인한다.
				// 만약 첨부된 파일의 이름이 있다면, 이 게시글은 파일이 첨부되었던 게시글이다.
				if (storedFileName != null && storedFileName.length() > 0) {
					// 첨부된 파일을 삭제한다.
					this.fileHandler.deleteFileByFileName(storedFileName);
				}
			}
			
			// 사용자가 업로드한 파일을 서버에 저장한다.
			StoredFile storedFile = this.fileHandler.storeFile(file);
			storedFile.setFileName(storedFile.getFileName());
			storedFile.setRealFileName(storedFile.getRealFileName());
		}
		
		int updatedCount = this.knowledgeDao.updateOneKnowledge(knowledgeVO);
		return updatedCount > 0;
	}

	
	// 삭제
	@Override
	public boolean deleteOneKnowledge(String knlId) {
		
			// 기존의 게시글 내용을 확인
			// 사용자가 파일을 업로드 한 경우, 기존에 업로드되었던 파일을 삭제하기 위해서!
			// 기존에 첨부된 파일의 존재여부를 확인해야한다.
			KnowledgeVO originalKnowledgeVO = this.knowledgeDao.selectOneKnowledge(knlId);
			
			// 기존 게시글에 첨부된 파일이 있는지 확인.
			if(originalKnowledgeVO != null) {
				
				// 기존 게시글에 첨부된 파일의 이름을 받아욘다.
				String storedFileName = originalKnowledgeVO.getFileName();
				// 첨부된 파일의 이름이 있는지 확인한다.
				// 만약 첨부된 파일의 이름이 있다면, 이 게시글은 파일이 첨부되었던 게시글이다.
				if (storedFileName != null && storedFileName.length() > 0) {
					// 첨부된 파일을 삭제한다.
					this.fileHandler.deleteFileByFileName(storedFileName);
				}
			}
		
		
		int deletedCount = this.knowledgeDao.deleteOneKnowledge(knlId);

		return deletedCount > 0;
	}
	
	
	
	





	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
