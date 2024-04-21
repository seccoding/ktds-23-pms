package com.ktdsuniversity.edu.pms.qna.vo;

import java.util.List;

import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;

public class QnaListVO {
	
	/*
	 * DB에서 조회한 게시글의 개수
	 */
	private int qnaCnt;
	
	/*
	 * DB에서 조회한 게시글 정보의 목록
	 */
	private List<QnaVO> qnaList;
	

	public int getQnaCnt() {
		return qnaCnt;
	}

	public void setQnaCnt(int qnaCnt) {
		this.qnaCnt = qnaCnt;
	}

	public List<QnaVO> getQnaList() {
		return qnaList;
	}

	public void setQnaList(List<QnaVO> qnaList) {
		this.qnaList = qnaList;
	}








	

}
