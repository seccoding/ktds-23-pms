package com.ktdsuniversity.edu.pms.review.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.review.service.ReviewService;
import com.ktdsuniversity.edu.pms.review.vo.ReviewListVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;

@Controller
public class ReviewController {

	private Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ProjectService projectService;
	
	/*
	 * [프로젝트명, 고객사, 수행부소, 상태, 시작일, 종료일, (후기결과보기)] 표로 보여주는 페이지
	 * - 프로젝트명 선택시 -> 후기작성 페이지로 이동(/review/write)
	 * - 후기결과보기 선택시 -> 해당 프로젝트의 후기결과 목록으로 이동(/review/viewwresult)
	 */
	@GetMapping("/review")
	public String viewReviewListPage(Model model) {
		ReviewListVO reviewListVO = reviewService.getAllReview();
		model.addAttribute("reviewlist", reviewListVO);
		return "review/reviewlist"; // reviewList.jsp 파일 이름
	}
	
	/*
	 * 후기작성을 사용자에게 보여주는 페이지
	 * - id값으로 해당 후기의 흐로젝트명을 가져와서 후기작성페이지에 바로 노출되도록 하기위함
	 */
	@GetMapping("/review/prjId/{id}/write")
	public String viewReviewWritePage(@PathVariable String id, Model model) {
		ProjectVO projectVO = projectService.getOneProject(id);
		model.addAttribute("project", projectVO);
		return "review/reviewwrite"; // reviewList.jsp 파일 이름
	}
	
	/*
	 * 사용자가 후기를 작성하는 페이지
	 * - 후기 작성 후 '저장' 버튼을 누르면 db로 전송되고, 
	 *   '저장'버튼을 누른 이후 후기는 수정할 수 없음
	 */
	@PostMapping("/review/write")
	public String ReviewWritePage(Model model, ReviewVO reviewVO) {
		this.reviewService.insertNewReview(reviewVO);
//		logger.debug(">>>>>>>>> {}", reviewVO);
		return "redirect:/review"; 
	}
		
	
	@GetMapping("/review/viewresult")
	public String viewReviewCntnt(Model model) {
		ReviewListVO reviewListVO = this.reviewService.viewReviewCntnt();
		model.addAttribute("reviewList", reviewListVO);
		
		return "review/reviewresult"; // reviewList.jsp 파일 이름
	}
//	@GetMapping("/review/modify")
//	public String viewModifyPage() {
//		return "review/reviewwrite";   // reviewList.jsp 파일 이름
//	}
//	@PostMapping("/review/modify")
//	public String ReviewModifyPage(Model model, ReviewVO reviewVO) {
//		
//		this.reviewService.updateOneReview(reviewVO);
//		model.addAttribute("reviewVO", reviewVO);
//		
//		return "redirect: /review"; 
//	}
	
	
	/*
	 * @GetMapping("/board/delete/{id}") public String deleteOneReview(@PathVariable
	 * String rvId, String email) {
	 * 
	 * ReviewVO originalBoardVO = this.reviewService.getOneReview(rvId, false); //삭제
	 * 요청 boolean isDeletedSuccess = this.reviewService.deleteOneReview(rvId,
	 * email); //게시글 목록으로 이동 return "redirect:/review/viewresult";
	 * 
	 * }
	 */
	
//	@GetMapping("/review/delete")
//	public String viewDeletePage() {
//		return "review/rev iewlist";   // reviewList.jsp 파일 이름
//	}
//	@PostMapping("/review/delete")
//	public String reviewDeletePage(Model model, ReviewVO reviewVO, String rvId, String email) {
//		
//		this.reviewService.deleteOneReview(rvId, email);
//		model.addAttribute("reviewVO", reviewVO);
//		
//		return "review/reviewresult"; 
//	}
	
	

	
	
//	@PostMapping("ajax/review/modify/{reviewId}")
//	public AjaxResponse doModifyReview(@PathVariable String rvId, ReviewVO reviewVO) {
//		
//		reviewVO.setRvId(rvId);
//
//		boolean isSuccess = this.reviewService.updateOneReview(reviewVO);
//		return new AjaxResponse().append("review/reviewmodify", isSuccess);
//	}
//	
//	@PostMapping("ajax/review/delete/{reviewId}")
//	public AjaxResponse doDeleteReview(@PathVariable String rvId, ReviewVO reviewVO) {		
//		
//		boolean isSuccess = this.reviewService.deleteOneReview(rvId);
//		return new AjaxResponse().append("review/reviewdelete", isSuccess);
//	}
	
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

//	@GetMapping("/timer")
//	public String timer() {
//		return "review/timer";
//	}
	
	
}

