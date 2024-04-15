package com.ktdsuniversity.edu.pms.review.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.review.service.ReviewService;
import com.ktdsuniversity.edu.pms.review.vo.ReviewListVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@ResponseBody
	@GetMapping("/review")
	public AjaxResponse vewReviewListPage() {
		ReviewListVO reviewListVO = reviewService.getAllReview();
		
		return new AjaxResponse().append("reviewList", reviewListVO);
	}
}
