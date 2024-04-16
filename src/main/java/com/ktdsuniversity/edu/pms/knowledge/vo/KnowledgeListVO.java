package com.ktdsuniversity.edu.pms.knowledge.vo;

import java.util.List;

public class KnowledgeListVO {
	
	/*
	 * DB에서 조회한 게시글의 개수
	 */
	private int knowledgeCnt;
	
	
	/*
	 * DB에서 조회한 게시글 정보의 목록
	 */
	private List<KnowledgeVO> knowledgeList;


	public int getKnowledgeCnt() {
		return knowledgeCnt;
	}


	public void setKnowledgeCnt(int knowledgeCnt) {
		this.knowledgeCnt = knowledgeCnt;
	}


	public List<KnowledgeVO> getKnowledgeList() {
		return knowledgeList;
	}


	public void setKnowledgeList(List<KnowledgeVO> knowledgeList) {
		this.knowledgeList = knowledgeList;
	}
	
	

}
