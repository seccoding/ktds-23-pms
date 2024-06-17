package com.ktdsuniversity.edu.pms.knowledge.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.beans.FileHandler.StoredFile;
import com.ktdsuniversity.edu.pms.exceptions.CreationException;
import com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeDao;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeRecommendVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.SearchKnowledgeVO;


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
	
	// 검색
	@Transactional
	@Override
	public KnowledgeListVO searchAllKnowledge(SearchKnowledgeVO searchKnowledgeVO) {
		
		int knowledgeCount = this.knowledgeDao.searchAllKnowledgeCount(searchKnowledgeVO);
		searchKnowledgeVO.setPageCount(knowledgeCount);
		
		List<KnowledgeVO> knowledgeList = this.knowledgeDao.searchAllKnowledge(searchKnowledgeVO);
		
		KnowledgeListVO knowledgeListVO = new KnowledgeListVO();
		knowledgeListVO.setKnowledgeCnt(knowledgeCount);
		knowledgeListVO.setKnowledgeList(knowledgeList);
		
		return knowledgeListVO;
	}

	
	/**
	 * 전달받은 파라미터의 게시글 정보를 조회해 반환
	 * 게시글 조회 시 조회 수도 1 증가
	 * @param knowledgeId 사용자가 조회하려는 knlId
	 * @param isIncrease 의 값이 true일 때, 조회수가 증가한다.
	 * @return 게시글 정보
	 */
	@Transactional
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
		
		return knowledgeVO;
	}
	
	/**
	 * 추천하기
	 */
//	@Override
//	public int recommendOneKnowledge(String knlId) {
//		
//		KnowledgeVO originKnowledge = this.knowledgeDao.selectOneKnowledge(knlId);
//		
//		return this.knowledgeDao.recommendOneKnowledge(knlId) > 0;
//	}
	
	@Transactional
	@Override
	public boolean updateRecommend(KnowledgeRecommendVO knowledgeRecommendVO) {
		// knowledgeRecommendVO를 파라미터로 하나의 추천 정보를 가져온다.
		KnowledgeRecommendVO originKnowledgeRecommendVO = knowledgeDao.selectOneRecommend(knowledgeRecommendVO);
		
		if(originKnowledgeRecommendVO == null) {
//			추천한 기록이 없으면 실행됨
			boolean isInsert = knowledgeDao.insertOneRecommend(knowledgeRecommendVO) > 0;
			
			if (isInsert) {
				boolean isRecommend = knowledgeDao.recommendOneKnowledge(knowledgeRecommendVO.getpPostId()) > 0;
				return isRecommend;
			} else {
				throw new CreationException();
			}
		} else {
//			기존에 추천 기록이 있다.
			return false;
		}
	}

	@Override
	public int getKnowledgeRecommendCount(String knlId) {
		return knowledgeDao.selectOneRecommendCount(knlId);
	}

	@Transactional
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
	@Transactional
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
			knowledgeVO.setOriginFileName(storedFile.getFileName());
			knowledgeVO.setFileName(storedFile.getRealFileName());
			
		}
		
		int updatedCount = this.knowledgeDao.updateOneKnowledge(knowledgeVO);
		return updatedCount > 0;
	}

	
	// 삭제
	@Transactional
	@Override
	public boolean deleteOneKnowledge(String knlId) {
		
			// 기존의 게시글 내용을 확인
			// 사용자가 파일을 업로드 한 경우, 기존에 업로드되었던 파일을 삭제하기 위해서!
			// 기존에 첨부된 파일의 존재여부를 확인해야한다.
			KnowledgeVO originalKnowledgeVO = this.knowledgeDao.selectOneKnowledge(knlId);
			
			// 기존 게시글에 첨부된 파일이 있는지 확인.
			if(originalKnowledgeVO != null) {
				
				// 기존 게시글에 첨부된 파일의 이름을 받아옴.
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
	
	
	// 글 한번에 삭제
	@Transactional
	@Override
	public boolean deleteManyKnowledge(List<String> deleteItems) {
		
		List<KnowledgeVO> originalKnowledgeList = this.knowledgeDao.selectManyKnowledge(deleteItems);
		
		for (KnowledgeVO knowledgeVO : originalKnowledgeList ) {
			if(knowledgeVO != null) {
				String storedFileName = knowledgeVO.getFileName();
				
				if (storedFileName != null && storedFileName.length() > 0) {
					this.fileHandler.deleteFileByFileName(storedFileName);
				}
			}
		}
		
		int deletedCount = this.knowledgeDao.deleteManyKnowledge(deleteItems);
		
		return deletedCount > 0;
	}
	
	

	// 엑셀 일괄 등록
	@Transactional
	@Override
	public boolean createMassiveKnowledge(MultipartFile excelFile) {
		int insertedCount = 0;
		int rowSize = 0;
		
		if(excelFile != null && !excelFile.isEmpty()) {
			StoredFile storedExcel = this.fileHandler.storeFile(excelFile);
			
			if(storedExcel != null) {
				
				// 엑셀파일 읽기
				InputStream excelFileInputStream = null;
				
				try {
					excelFileInputStream = new FileInputStream(storedExcel.getRealFilePath());
				} catch (FileNotFoundException e) {
					logger.error(e.getMessage(), e);
				}
				
				// InputStream의 내용을 엑셀 문서로 읽어옴
				Workbook excelWordbook = null;
				
				if (excelFileInputStream != null) {
					try {
						excelWordbook = new XSSFWorkbook(excelFileInputStream);
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
				}
				
				// 엑셀파일의 특정 Sheet에 있는 모든 데이터를 찾아 List<KnowledgeVO>로 만들어줌
				List<KnowledgeVO> knowledgeListInExcel = new ArrayList<>();
				
				if (excelWordbook != null) {
					// Sheet 추출
					Sheet sheet = excelWordbook.getSheet("Sheet1");
					
					
					rowSize = sheet.getPhysicalNumberOfRows();
					// Row 사이즈만큼 반복
					for (int i = 1; i < rowSize; i++) {
						
						Row row = sheet.getRow(i);
						
						// Row에 있는 cell 정보 가져옴
						String knlId = row.getCell(0).getStringCellValue();
						String knlTtl = row.getCell(1).getStringCellValue();
						String originFileName = row.getCell(2).getStringCellValue();
						
						KnowledgeVO knowledgeVO = new KnowledgeVO();
						knowledgeVO.setKnlId(knlId);
						knowledgeVO.setKnlTtl(knlTtl);
						knowledgeVO.setOriginFileName(originFileName);
						
						knowledgeListInExcel.add(knowledgeVO);
					}
					// List<KnowledgeVO>에 insert
					for (KnowledgeVO knowledgeVO : knowledgeListInExcel) {
						insertedCount += this.knowledgeDao.insertNewKnowledge(knowledgeVO);
						
					}
					
				}
			}
			
		}
		return insertedCount > 0 && insertedCount == rowSize -1;

		
	}

	@Override
	public String findid(String kinId) {
		// TODO Auto-generated method stub
		return this.knowledgeDao.findkindId(kinId);
	}

	








	
	
	
	





	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
