package com.ktdsuniversity.edu.pms.review.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.review.service.ReviewService;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

@RestController
@RequestMapping("/api/review/")
public class ApiReviewController {

	private Logger logger = LoggerFactory.getLogger(ApiReviewController.class);

	@Autowired
	private ReviewService reviewService;
	
	
	@GetMapping("writes")
	public ApiResponse viewWriteReviewPage(Authentication authentication) {
		
		
		return new ApiResponse().Ok(true);
	}
	
	// 리뷰 작성
	@PostMapping("writes/{id}")
	public ApiResponse doWriteReview(@PathVariable String id   ,ReviewVO reviewVO, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		reviewVO.setEmpId(employeeVO.getEmpId());
		
		Validator<ReviewVO> validator = new Validator<>(reviewVO);
		List<String> errorMessgae = null;
		
		validator.add("rvCntnt", Type.NOT_EMPTY, "후기 내용을 작성해주세요.")
				 .start();
		
		if (validator.hasErrors()) {
			
		}
		
		boolean isCreateSuccess = reviewService.updateOneReview(reviewVO);
		
		if (!isCreateSuccess) {
			logger.debug("후기 작성 저장에 실패했습니다.");
		} else {
			logger.debug("후기 작성에 성공했습니다.");
		}
		
		return ApiResponse.Ok(isCreateSuccess);
	}
	
	
	// 관리자 리뷰 삭제
	@PutMapping("writes")
	public ApiResponse deleteReview(@PathVariable int prjId, Authentication authentication) {
		
		
		
		
		return ApiResponse.Ok(true);
	}
	
	
	@GetMapping("test")
	public Map<String, Object> test(){
		Map<String, Object> map = new HashMap<>();
		map.put("TEST1", "TEST1");
		System.out.println("TEST1111......");
		return map;
	}
	
	
	@GetMapping("/ajax/review/viewresult/{id}/delete")
	public AjaxResponse reviewViewResultDelete(@PathVariable String id) {
		logger.debug("ID={}", id);
		return new AjaxResponse().append("result", reviewService.reviewViewResultDelete(id));
	}

}
