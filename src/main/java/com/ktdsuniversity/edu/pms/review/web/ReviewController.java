package com.ktdsuniversity.edu.pms.review.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;
import com.ktdsuniversity.edu.pms.project.dao.ProjectDao;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.review.service.ReviewService;
import com.ktdsuniversity.edu.pms.review.vo.ReviewListVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;
import com.ktdsuniversity.edu.pms.review.vo.SearchReviewVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

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
	public String viewReviewListPage(SearchReviewVO searchReviewVO, Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		List<ProjectTeammateVO> tmList =this.projectService.getAllProjectTeammate().stream()
		.filter(tm->tm.getTmId().equals(employeeVO.getEmpId()))
		.filter(tm->tm.getRole().equals("PM"))
		.toList();
		boolean isPM = false;
		if (tmList.size()>0) { // PM인 경우
		    isPM = true;
		}

		searchReviewVO.setEmployeeVO(employeeVO);
		
		ReviewListVO reviewListVO = reviewService.getAllReview(searchReviewVO);
		model.addAttribute("reviewlist", reviewListVO);
		model.addAttribute("SearchReviewVO", searchReviewVO);
		model.addAttribute("isPM", isPM);
//		.addAttribute("isRVY", isRVY);

		return "review/reviewlist"; // reviewList.jsp 파일 이름
	}
	
	/*
	 * 후기작성을 사용자에게 보여주는 페이지
	 * - id값으로 해당 후기의 흐로젝트명을 가져와서 후기작성페이지에 바로 노출되도록 하기위함
	 */
	@GetMapping("/review/prjId/{id}/write")
	public String viewReviewWritePage(@PathVariable String id, Model model, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		if (employeeVO.getAdmnCode().equals("301") || employeeVO.getMngrYn().equals("Y")) {
			return "redirect:/review"; 
		}
		
		List<ProjectTeammateVO> tmList =this.projectService.getAllProjectTeammateByProjectId(id).stream()
		.filter(tm->tm.getTmId().equals(employeeVO.getEmpId()))
		.filter(tm->tm.getRvYn().equals("Y"))
		.toList();
		boolean isRVY = false;
		
		if (tmList.size()>0) { // rvYn = Y 일 경우
			isRVY = true;
		}
		
		if( employeeVO.getMngrYn().equals("N")) {
			ProjectVO projectVO = projectService.getOneProject(id);
			model.addAttribute("project", projectVO);
		}
		model.addAttribute("crtrId", employeeVO.getEmpId());
		model.addAttribute("employeeVO", employeeVO);
		model.addAttribute("isRVY", isRVY);
		
		
		return "review/reviewwrite"; // reviewList.jsp 파일 이름
	}
	
	
	
	
	/*
	 * 사용자가 후기를 작성하는 페이지
	 * - 후기 작성 후 '저장' 버튼을 누르면 db로 전송되고, 
	 *   '저장'버튼을 누른 이후 후기는 수정할 수 없음
	 */
	@PostMapping("/review/write")
	public String ReviewWritePage(Model model, ReviewVO reviewVO, ProjectTeammateVO projectTeammateVO,
								@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, ProjectVO projectVO) {
		
		
		projectTeammateVO.setTmId(employeeVO.getEmpId());
		
		
		Validator<ReviewVO> validator = new Validator<>(reviewVO);
		validator
		.add("rvCntnt", Type.NOT_EMPTY, "내용은 필수 입력값입니다.")
		.start();
		
		
		if(validator.hasErrors()) {
			
			Map<String, List<String>> errors = validator.getErrors();
			model.addAttribute("errorMessage",errors);
			model.addAttribute("reviewVO",reviewVO);
			model.addAttribute("project", projectVO);			
			return "review/reviewwrite";
		}
		
		/*
		 * this.reviewService.insertNewReview(reviewVO);
		 * this.reviewService.updateReviewStatus(projectTeammateVO);
		 */
		boolean success = reviewService.insertNewReviewAndUpdateStatus(reviewVO, projectTeammateVO);
			if (!success) {
		        return "후기전송에 실패했습니다. 다시 시도해주세요.";
		    }
		
		model.addAttribute("projectTeammateVO", projectTeammateVO);
		model.addAttribute("employeeVO", employeeVO);
		model.addAttribute("reviewVO", reviewVO);

//		this.reviewService.insertReviewRvYn(projectTeammateVO);
//		logger.debug(">>>>>>>>> {}", reviewVO);
		return "redirect:/review"; 
	}
		
	 @GetMapping("/review/viewresult")
	   public String viewReviewCntnt(@RequestParam("prjId") String id, SearchReviewVO searchReviewVO, Model model) {
		ReviewListVO reviewListVO = this.reviewService.getAllReviewResult(searchReviewVO);
	
			/*
			 * @GetMapping("/review/viewresult/prjId/{id}") public String
			 * viewReviewCntnt(@RequestParam("id") String id, SearchReviewVO searchReviewVO,
			 * Model model) {
			 */
		
		int projectVO = projectService.getProjectTeammateCount(id);
		
		model.addAttribute("teammateCount", projectVO);
		
		model.addAttribute("reviewList", reviewListVO);
		model.addAttribute("SearchReviewVO", searchReviewVO);
		return "review/reviewresult"; // reviewList.jsp 파일 이름
	
	}
	
	
	@ResponseBody
	@PostMapping("/ajax/review/delete/massive")
	public AjaxResponse doDeleteMassive(@RequestParam("reviewIds[]") List<String> reviewIds
									  , @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
					
		boolean deleteResult = this.reviewService.deleteManyReview(reviewIds);
		
		return new AjaxResponse().append("result", deleteResult);
		
		
	}
	

	
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
	
	
//	@PostMapping("ajax/review/write/{reviewId}")
//	public AjaxResponse doCreateNewReview(@PathVariable String rvId, ReviewVO reviewVO) {
//		
//		reviewVO.setRvId(rvId);
//		
//		boolean isSuccess = this.reviewService.insertNewReview(reviewVO);
//		return new AjaxResponse().append("review/reviewwrite", isSuccess);
//	}


	
	
}

