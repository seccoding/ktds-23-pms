package com.ktdsuniversity.edu.pms.review.service;

import com.ktdsuniversity.edu.pms.review.vo.ReviewListVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;

public interface ReviewService {

	ReviewListVO getAllReview();
	
	/**
	 * 후기 작성 요청 전송(PM)
	 * @param reviewVO
	 * @return
	 */
	public boolean insertNewReviewQuestion(ReviewVO  reviewVO);
	
	public boolean selectOneReview(ReviewVO reviewVO);
	
	/**
	 * 후기 작성
	 * @param reviewVO
	 * @return
	 */
	public boolean insertNewReview(ReviewVO reviewVO);
	
	/**
	 * 후기 수정
	 * @param reviewVO
	 * @return
	 */
	public boolean updateOneReview(ReviewVO reviewVO);
	
	/**
	 * 후기 삭제(PM이상)
	 * @param reviewVO
	 * @return
	 */
	public boolean deleteOneReview(ReviewVO reviewVO);
	
}
