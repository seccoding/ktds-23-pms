package com.ktdsuniversity.edu.pms.qna.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.qna.vo.QnaVO;

public interface QnaDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.qna.dao.QnaDao";

	public int getAllQnaCount();

	public List<QnaVO> getAllQna();

	public QnaVO selectOneQna(String qaId);

	public int increaseViewCount(String qaId);
	
	

}
