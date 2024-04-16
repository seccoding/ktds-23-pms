package com.ktdsuniversity.edu.pms.output.vo;

import java.util.List;

public class OutputListVO {
	
	int listCnt ; 				//산출물 리스트 안의 산출물 갯수
	List<OutputVO> outputList; 	//산출물 리스트
	
	public int getListCnt() {
		return listCnt;
	}
	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
	public List<OutputVO> getOutputList() {
		return outputList;
	}
	public void setOutputList(List<OutputVO> outputList) {
		this.outputList = outputList;
	}
}
