package com.ktdsuniversity.edu.pms.review.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.pms.review.service.ReviewService;
import com.ktdsuniversity.edu.pms.review.vo.ReviewListVO;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
//	@ResponseBody	
//	@GetMapping("/review")
//	public AjaxResponse vewReviewListPage() {
//		ReviewListVO reviewListVO = reviewService.getAllReview();
//		
//		return new AjaxResponse().append("reviewList", reviewListVO);
//		
//	}
	
		
		
		
	@GetMapping("/review")
	public String viewReviewListPage(Model model) {
		ReviewListVO reviewListVO = reviewService.getAllReview();
		model.addAttribute("reviewlist", reviewListVO);
		return "review/reviewlist"; // reviewList.jsp 파일 이름
	}
	
		
	
	@PostMapping("/review/write")
	public String viewReviewWritePage() {
		return "review/reviewwrite";
	}
	

	@GetMapping("/timer")
	public String timer() {
		return "review/timer";
	}
	
	
}

