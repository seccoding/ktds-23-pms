package com.ktdsuniversity.edu.pms.knowledge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeDao;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
	
	@Autowired
	private KnowledgeDao knowledgeDao;

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
		
		// 추천을 눌렀다면 추천 1 증가?
		
		
		return knowledgeVO;
	}





	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
