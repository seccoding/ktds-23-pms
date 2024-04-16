package com.ktdsuniversity.edu.pms.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.review.dao.ReviewDao;
import com.ktdsuniversity.edu.pms.review.vo.ReviewListVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService {

	/*
	*Bean Container에 등록된 reviewDao Bean을 가져와 주입시킨다
	*/
	@Autowired
	private ReviewDao reviewDao;

	@Override
	public ReviewListVO getAllReview() {
		List<ReviewVO> reviewList = reviewDao.selectAllReview();

        ReviewListVO reviewListVO = new ReviewListVO();
        reviewListVO.setReviewList(reviewList);

        return reviewListVO;
	}
	

	@Override
	public ReviewListVO viewReviewCntnt() {
		List<ReviewVO> reviewList = reviewDao.viewReviewCntnt();

        ReviewListVO reviewListVO = new ReviewListVO();
        reviewListVO.setReviewList(reviewList);

        return reviewListVO;
	}


	
//	@Override
//	public ReviewListVO viewReviewCntnt() {
//		List<ReviewVO> reviewList = reviewDao.selectAllReview();
//
//		ReviewListVO reviewListVO = new ReviewListVO();
//        reviewListVO.setReviewList(reviewList);
//
//        return reviewListVO;
//	}
	
	
	@Transactional
	@Override
	public boolean insertNewReviewQuestion(ReviewVO reviewVO) {
		return this.reviewDao.insertNewReviewQuestion(reviewVO) > 0;
	}

	@Transactional
	@Override
	public boolean insertNewReview(ReviewVO reviewVO) {
		return this.reviewDao.insertNewReview(reviewVO) > 0;
	}

	@Transactional
	@Override
	public boolean updateOneReview(ReviewVO reviewVO) {
		return this.reviewDao.insertNewReviewQuestion(reviewVO) > 0;
	}

	//@Transactional
	@Override
	public boolean deleteOneReview(String rvId, String email) {

		ReviewVO reviewVO = this.reviewDao.getOneReview(rvId);
		
		if(!email.equals(reviewVO.getEmployeeVO())) {
			throw new PageNotFoundException();
		}
		return this.reviewDao.deleteOneReview(rvId) > 0;
	}


	@Override
	public ReviewVO getOneReview(String rvId, boolean isdeleted) {
		ReviewVO reviewVO = this.reviewDao.getOneReview(rvId);
		return reviewVO;
	}

	


	

	
	
	
//		int reviewVO = this.reviewDao.getOneReview(reviewId);
		
//		ReviewVO reviewVO = this.reviewDao.getAllReview(reviewId);
//
//		// PM이상일 경우에만 삭제할 수 있음
//		if (!email.equals(replyVO.getEmail())) {
//			throw new PageNotFoundException();
//		}
//		return this.reviewDao.deleteOneReview(replyId) > 0;


//	@Override
//	public List<ReviewVO> getAllReview() {
//		return reviewDao.selectAllReview();
//	}
	
	
}
