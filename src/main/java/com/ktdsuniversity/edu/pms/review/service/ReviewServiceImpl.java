package com.ktdsuniversity.edu.pms.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//	@Override
//	public List<ReviewVO> getAllReview() {
//		return reviewDao.selectAllReview();
//	}
	
	
}
