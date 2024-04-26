package com.ktdsuniversity.edu.pms.review.vo;

import java.util.List;

public class ReviewListVO {
	
	private int reviewCnt;
	
	private List<ReviewVO> reviewList;
	
	public List<ReviewVO> getReviewList() {
		return reviewList;
	}
	public void setReviewList(List<ReviewVO> reviewList) {
		this.reviewList = reviewList;
	}
	public int getReviewCnt() {
		return reviewCnt;
	}
	public void setReviewCnt(int reviewCnt) {
		this.reviewCnt = reviewCnt;
	}
}



