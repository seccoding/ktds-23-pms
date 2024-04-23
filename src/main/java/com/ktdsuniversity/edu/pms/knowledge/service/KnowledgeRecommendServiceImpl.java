package com.ktdsuniversity.edu.pms.knowledge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.exceptions.CreationException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeDao;
import com.ktdsuniversity.edu.pms.knowledge.dao.KnowledgeRecommendDao;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeRecommendVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;

@Service
public class KnowledgeRecommendServiceImpl implements KnowledgeRecommendService{

	@Autowired
	private KnowledgeRecommendDao knowledgeRecommendDao;
	
//	@Autowired
//	private KnowledgeDao knowledgeDao;

	@Transactional
	@Override
	public boolean updateRecommend(KnowledgeRecommendVO knowledgeRecommendVO) {
		// knowledgeRecommendVO를 파라미터로 하나의 추천 정보를 가져온다.
		KnowledgeRecommendVO originKnowledgeRecommendVO = knowledgeRecommendDao.selectOneRecommend(knowledgeRecommendVO);
		
		if(originKnowledgeRecommendVO == null) {
//			추천한 기록이 없으면 실행됨
			boolean isInsert = knowledgeRecommendDao.insertOneRecommend(knowledgeRecommendVO) > 0;
			if (isInsert) {
				return isInsert;
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
		return knowledgeRecommendDao.selectOneRecommendCount(knlId);
	}

	
	
	
//	@Override
//	public boolean hasEmployeeRecommended(String crtrId) {
//		// 이미 추천한 직원인지 확인
//		
//		return  knowledgeRecommendDao.existsByCrtrId(crtrId);
//	}


//	@Override
//	public void saveRecommendation(KnowledgeRecommendVO knowledgeRecommendVO) {
//		// 추천을 저장
//		// 지식 추천 정보를 데이터베이스에 저장
//        knowledgeRecommendDao.save(knowledgeRecommendVO);
//        
//        // 해당 지식의 추천 횟수를 업데이트
//        KnowledgeVO knowledge = knowledgeDao.findById(knowledgeRecommendVO.getpPostId());
//        if (knowledge != null) {
//        	// 지식이 존재하면 추천 횟수를 증가시키고 저장
//            knowledge.setKnlRecCnt(knowledge.getKnlRecCnt() + 1);
//            knowledgeDao.save(knowledge);
//        }
//		
//	}


}
