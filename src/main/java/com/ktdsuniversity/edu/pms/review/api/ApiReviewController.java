package com.ktdsuniversity.edu.pms.review.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.review.service.ReviewService;
import com.ktdsuniversity.edu.pms.review.vo.ReviewListVO;
import com.ktdsuniversity.edu.pms.review.vo.ReviewVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

@RestController
@RequestMapping("/api/review/")
public class ApiReviewController {

	private Logger logger = LoggerFactory.getLogger(ApiReviewController.class);

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ProjectService projectService;
	
	
	@GetMapping("writes/{prjId}")
	public ApiResponse viewWriteReviewPage(@PathVariable String prjId ,Authentication authentication) {
		
		
		return new ApiResponse().Ok(true);
	}
	
	// 리뷰 작성
	@PostMapping("writes")
	public ApiResponse doWriteReview(ReviewVO reviewVO, Authentication authentication, ProjectTeammateVO projectTeammateVO) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		reviewVO.setCrtrId(employeeVO.getEmpId());
		projectTeammateVO.setTmId(employeeVO.getEmpId());
		projectTeammateVO.setPrjId(reviewVO.getPrjId());
		
		Validator<ReviewVO> validator = new Validator<>(reviewVO);
		
		validator.add("rvCntnt", Type.NOT_EMPTY, "후기 내용을 작성해주세요.")
				 .start();
		
		if (validator.hasErrors()) {
			Map<String, List<String>> errors = validator.getErrors();
			String errorMessage = errors.values().toString();
			return ApiResponse.Ok(errorMessage);
		}
		
		boolean isCreateSuccess = this.reviewService.insertNewReviewAndUpdateStatus(reviewVO, projectTeammateVO);
		
		if (!isCreateSuccess) {
			logger.debug("후기 작성 저장에 실패했습니다.");
		} else {
			logger.debug("후기 작성에 성공했습니다.");
		}
		
		return ApiResponse.Ok(isCreateSuccess);
	}
	
	
	// 관리자 리뷰 삭제
	@PutMapping("writes/{rvId}")
	public ApiResponse deleteReview(@PathVariable String rvId, @RequestBody String prjId, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		if (employeeVO.getAdmnCode().equals("N")) {
			return ApiResponse.FORBIDDEN("권한이 없습니다.");
		} 
		
		// 후기 삭제 메서드
		boolean isDeleteSuccess = this.reviewService.reviewViewResultDelete(rvId);
		if (isDeleteSuccess) {
			logger.debug("후기 삭제에 성공했습니다.");
		} else {
			logger.debug("후기 삭제에 실패했습니다.");
		}
		
		// 후기 수정 메서드 (삭제시 삭제일, 삭제한 관리자 ID 저장
		Map<String, Object> modifyParam = new HashMap<>();
		Map<String, Object> modifyRvYnParam = new HashMap<>();
		modifyParam.put("empId", employeeVO.getEmpId());
		modifyParam.put("reviewId", rvId);
		modifyRvYnParam.put("prjId", prjId);
		modifyRvYnParam.put("empId", employeeVO.getEmpId());
		
		System.out.println(modifyParam);
		boolean isModifySuccess = this.reviewService.reviewResultModify(modifyParam, modifyRvYnParam);
		
		if (isModifySuccess) {
			logger.debug("후기 수정에 성공했습니다.");
		} else {
			logger.debug("후기 수정에 실패했습니다.");
		}
		
		
		return ApiResponse.Ok(true);
	}
	
	/**
	 * 후기 작성 가능 여부를 반환하는 메서드
	 * @param prjIdList 회원이 속해 있는 프로젝트의 PK (1~N)              
	 * @param authentication
	 * @return
	 */
	@PostMapping("writes/reviewyn")
	public ApiResponse getReviewYN(@RequestBody List<String> prjIdList, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		Map<String, Boolean> result = new HashMap<>();
		IntStream.range(0, prjIdList.size()).forEach(i -> {
			System.out.println("프로젝트 리스트 : " + prjIdList.get(i));
		});
		
		
		IntStream.range(0, prjIdList.size())
				 .forEach(i -> {
					 Map<String, String> param = new HashMap<>();
					 param.put("empId", employeeVO.getEmpId());
					 param.put("prjId", prjIdList.get(i));
					 result.put("result" + i, this.reviewService.getReviewYnByEmpIdAndPrjId(param));
				 });

		return ApiResponse.Ok(result);
	}
	
	// 사원이 속한 프로젝트 리스트 가져오기
	@GetMapping("writes/prjList")
	public ApiResponse getEmpPrjList(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		List<ProjectTeammateVO> projectTeammateVOList = this.reviewService.getEmpPrjList(employeeVO.getEmpId());
		
//		projectTeammateVOList.stream().forEach(i -> {
//			System.out.println(projectTeammateVOList.);
//		});

		return ApiResponse.Ok(projectTeammateVOList);
		
	}
	

	/**
	 * 관리자 또는 PM 일 경우 
	 * 결과 보기 또는 후기 관리를 클릭할 때 작성된 모든 후기를 가져오는 메서드
	 * @param authentication 
	 * @param prjId
	 * @return List<ProjectVO> 
	 */
	@GetMapping("writes/reviewResult/{prjId}")
	public ApiResponse getReviewResult(Authentication authentication, @PathVariable String prjId) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		ReviewListVO reviewListVO = this.reviewService.getReviewResult(prjId);
		
		return ApiResponse.Ok(reviewListVO);
	}
}
