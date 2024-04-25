package com.ktdsuniversity.edu.pms.qna.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.qna.vo.QnaListVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaRecommendVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaVO;
import com.ktdsuniversity.edu.pms.qna.vo.SearchQnaVO;

public interface QnaService {
	
	public QnaListVO getAllQna();

	public QnaListVO searchAllQna(SearchQnaVO searchQnaVO);

	public QnaVO getOneQna(String qaId, boolean isIncrease);

	public boolean createNewQna(QnaVO qnaVO, MultipartFile file);

//	public int recommendOneQna(String qaId);

	public boolean updateOneQna(QnaVO qnaVO, MultipartFile file);

	public boolean deleteOneQna(String qaId);

	public boolean deleteManyQna(List<String> deleteItems);

	public int getQnaRecommendCount(String qaId);

	public boolean updateRecommend(QnaRecommendVO qnaRecommendVO);
//
//	public boolean cancelRecommend(QnaRecommendVO qnaRecommendVO);

}
