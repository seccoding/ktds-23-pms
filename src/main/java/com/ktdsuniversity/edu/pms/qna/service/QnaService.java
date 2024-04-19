package com.ktdsuniversity.edu.pms.qna.service;

import com.ktdsuniversity.edu.pms.qna.vo.QnaListVO;
import com.ktdsuniversity.edu.pms.qna.vo.QnaVO;
import com.ktdsuniversity.edu.pms.qna.vo.SearchQnaVO;

public interface QnaService {
	
	public QnaListVO getAllQna();

	public QnaListVO searchAllQna(SearchQnaVO searchQnaVO);

	public QnaVO getOneQna(String qaId, boolean isIncrease);

}
