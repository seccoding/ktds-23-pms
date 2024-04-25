package com.ktdsuniversity.edu.pms.qna.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.qna.vo.QnaRecommendVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaVO;
import com.ktdsuniversity.edu.pms.qna.vo.SearchQnaVO;

public interface QnaDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.qna.dao.QnaDao";

	public int getAllQnaCount();

	public List<QnaVO> getAllQna();

	public QnaVO selectOneQna(String qaId);

	public int increaseViewCount(String qaId);

	public int insertNewQna(QnaVO qnaVO);

	public int recommendOneQna(String qaId);

	public int updateOneQna(QnaVO qnaVO);

	public int deleteOneQna(String qaId);

	public List<QnaVO> selectManyQna(List<String> deleteItems);

	public int deleteManyQna(List<String> deleteItems);

	public int searchAllQnaCount(SearchQnaVO searchQnaVO);

	public List<QnaVO> searchAllQna(SearchQnaVO searchQnaVO);

	public int insertOneRecommend(QnaRecommendVO qnaRecommendVO);

	public QnaRecommendVO selectOneRecommend(QnaRecommendVO qnaRecommendVO);

	public int selectOneRecommendCount(String qaId);



	
	

}
