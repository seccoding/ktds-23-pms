package com.ktdsuniversity.edu.pms.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.review.dao.ReviewDao;
import com.ktdsuniversity.edu.pms.review.vo.ReviewListVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;

	@Override
	public ReviewListVO getAllReview() {
		List<ReviewVO> reviewList = reviewDao.selectAllReview();

        ReviewListVO reviewListVO = new ReviewListVO();
        reviewListVO.setReviewList(reviewList);

        return reviewListVO;
	}
}
