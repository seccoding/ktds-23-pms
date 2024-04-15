package com.ktdsuniversity.edu.pms.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	@Override
	public boolean insertNewReviewQuestion(ReviewVO reviewVO) {
		return this.reviewDao.insertNewReviewQuestion(reviewVO) > 0;
	}

	@Override
	public boolean selectOneReview(ReviewVO reviewVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional
	@Override
	public boolean insertNewReview(ReviewVO reviewVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional
	@Override
	public boolean updateOneReview(ReviewVO reviewVO) {
		// TODO Auto-generated method stub
		return false;
	}

	//@Transactional
	@Override
	public boolean deleteOneReview(ReviewVO reviewVO) {

//		ReviewVO reviewVO = this.reviewDao.getAllReview(reviewId);
//
//		// PM이상일 경우에만 삭제할 수 있음
//		if (!email.equals(replyVO.getEmail())) {
//			throw new PageNotFoundException();
//		}
//		return this.reviewDao.deleteOneReview(replyId) > 0;

		return false;
	}



//	@Override
//	public List<ReviewVO> getAllReview() {
//		return reviewDao.selectAllReview();
//	}
	
	
}
