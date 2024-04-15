package com.ktdsuniversity.edu.pms.review.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.pms.review.service.ReviewService;
import com.ktdsuniversity.edu.pms.review.vo.ReviewListVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/review")
	public String viewReviewListPage(Model model) {
		ReviewListVO reviewListVO = reviewService.getAllReview();
		model.addAttribute("reviewlist", reviewListVO);
		return "review/reviewlist"; // reviewList.jsp 파일 이름
	}
	
//	@ResponseBody	
//	@GetMapping("/review")
//	public AjaxResponse vewReviewListPage() {
//		ReviewListVO reviewListVO = reviewService.getAllReview();
//		
//		return new AjaxResponse().append("reviewList", reviewListVO);
//		
//	}
	
	
//	@PostMapping("ajax/review/write/{reviewId}")
//	public AjaxResponse doCreateNewReview(@PathVariable String rvId, ReviewVO reviewVO) {
//		
//		reviewVO.setRvId(rvId);
//		
//		boolean isSuccess = this.reviewService.insertNewReview(reviewVO);
//		return new AjaxResponse().append("review/reviewwrite", isSuccess);
//	}
	
	@GetMapping("/review/write")
	public String viewReviewWritePage() {
		return "review/reviewwrite"; // reviewList.jsp 파일 이름
	}
	
	@PostMapping("/review/write")
	public String viewReviewWritePage(Model model, ReviewVO reviewVO) {
		
		this.reviewService.insertNewReview(reviewVO);
		model.addAttribute("reviewVO", reviewVO);
		
		return "redirect:/review"; // reviewList.jsp 파일 이름
	}
	
	
	
	
//	
//	@PostMapping("ajax/review/modify/{reviewId}")
//	public AjaxResponse doModifyReview(@PathVariable String rvId, ReviewVO reviewVO) {
//		
//		reviewVO.setRvId(rvId);
//
//		boolean isSuccess = this.reviewService.updateOneReview(reviewVO);
//		return new AjaxResponse().append("review/reviewmodify", isSuccess);
//	}
//	
//	
//	
//	
//	@PostMapping("ajax/review/delete/{reviewId}")
//	public AjaxResponse doDeleteReview(@PathVariable String rvId, ReviewVO reviewVO) {		
//		
//		boolean isSuccess = this.reviewService.deleteOneReview(rvId);
//		return new AjaxResponse().append("review/reviewdelete", isSuccess);
//	}

//	@GetMapping("/timer")
//	public String timer() {
//		return "review/timer";
//	}
	
	
}

