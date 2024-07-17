//package com.ktdsuniversity.edu.pms.requirement.api;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.SessionAttribute;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
//import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
//import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
//import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
//import com.ktdsuniversity.edu.pms.exceptions.AccessDeniedException;
//import com.ktdsuniversity.edu.pms.project.service.ProjectService;
//import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
//import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
//import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
//import com.ktdsuniversity.edu.pms.requirement.vo.RequirementListVO;
//import com.ktdsuniversity.edu.pms.requirement.vo.RequirementSearchVO;
//import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
//import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
//import com.ktdsuniversity.edu.pms.utils.ApiResponse;
//import com.ktdsuniversity.edu.pms.utils.Validator;
//import com.ktdsuniversity.edu.pms.utils.Validator.Type;
//
//@RestController
//@RequestMapping("/api/v1")
//public class ApiRequirementController {
//	
//	@Autowired
//	private ProjectService projectService;
//	
//	@Autowired
//	private RequirementService requirementService;
//	
//	@Autowired
//	private CommonCodeService commonCodeService;
//	
//	@GetMapping("/requirement/search/{prjIdValue}")
//	public ApiResponse getRequirementList(@PathVariable String prjIdValue,
//			RequirementSearchVO requirementSearchVO, Authentication authentication) {
//		
//		// 요구사항 리스트를 불러오는 API
//		
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
//		
//		ProjectListVO projectList = this.projectService.getAllProject();
//		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아니면
//			requirementSearchVO.setEmpId(employeeVO.getEmpId());
//			projectList.setProjectList(this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId()));
//		} else {
//		} // 관리자라면
//
//		projectList.setProjectList(
//				projectList.getProjectList().stream().toList());
//		
//		requirementSearchVO.setPrjId(prjIdValue);
//		RequirementListVO requirementList = requirementService.searchAllRequirement(requirementSearchVO);
//		requirementSearchVO.setPageCount(requirementList.getCount());
//		List<CommonCodeVO> scdSts = this.commonCodeService.getAllCommonCodeListByPId("500");
//		List<CommonCodeVO> rqmSts = this.commonCodeService.getAllCommonCodeListByPId("600");
//		
//		// PM or PL 인지 체크
//		boolean isPmAndPl = false;
//		List<ProjectTeammateVO> tmList = this.projectService.getAllProjectTeammateByProjectId(prjIdValue).stream()
//				.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId()))
//				.filter(tm -> tm.getRole().equals("PM") || tm.getRole().equals("PL")).toList();
//		if (tmList.size() > 0) {
//			isPmAndPl = true;
//		}
//		
//		// 모든 데이터를 하나의 응답으로 합치기
//		Map <String, Object> responseData = new HashMap<>();
//		responseData.put("requirementList", requirementList);
//		responseData.put("isPmAndPl", isPmAndPl);
//		
//		return ApiResponse.Ok(responseData, responseData == null ? 0 : 1);
//		
//		
////		return ApiResponse.Ok(requirementList.getRequirementList(), 
////				requirementList.getCount(),
////				requirementSearchVO.getPageCount(), 
////				requirementSearchVO.getPageNo() < requirementSearchVO.getPageCount() - 1);
//	}
//	
//	
//	@GetMapping("/requirement/view")
//	public ApiResponse getOneRequirement(Authentication authentication, 
//			@RequestParam String prjId, @RequestParam String rqmId) {
//		
//		// 1개의 요구사항에 대한 상세정보를 불러오는 API
//		
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
//		
//		// 본인 프로젝트가 아닐경우, 잘못된 프로젝트 아이디가 입력된경우 에러페이지 & 메시지 전달
//		if (employeeVO.getAdmnCode() != "301") {
//			List<ProjectTeammateVO> tmList = this.projectService.getAllProjectTeammateByProjectId(prjId).stream()
//					.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId())).toList();
//			
//		}
//
//		boolean isPmAndPl = false;
//		List<ProjectTeammateVO> tmList = this.projectService.getAllProjectTeammateByProjectId(prjId).stream()
//				.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId()))
//				.filter(tm -> tm.getRole().equals("PM") || tm.getRole().equals("PL")).toList();
//		if (tmList.size() > 0) {
//			isPmAndPl = true;
//		}
//
//		RequirementVO requirement = this.requirementService.getOneRequirement(rqmId);
//		
//		// 모든 데이터를 하나의 응답으로 합치기
//		Map <String, Object> responseData = new HashMap<>();
//		responseData.put("requirement", requirement);
//		responseData.put("isPmAndPl", isPmAndPl);
//
//		return ApiResponse.Ok(responseData, responseData == null ? 0 : 1);
//
//	}
//	
//	
//	@GetMapping("/requirement/write")
//	public ApiResponse getRequirementWritePage(Authentication authentication) {
//		
//		// 요구사항 작성과 수정에 필요한 데이터들을 불러오는 API
//		
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
//		
//		ProjectListVO projectList = this.projectService.getAllProject();
//		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아니면
//			projectList.setProjectList(this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId()));
//		} else {// 관리자라면
//
//		}
//		projectList.setProjectList(
//				projectList.getProjectList().stream().toList());
//
//		List<CommonCodeVO> scdStsList = this.commonCodeService.getAllCommonCodeListByPId("500");
//		List<CommonCodeVO> rqmStsList = this.commonCodeService.getAllCommonCodeListByPId("600");
//		
//		
//		// 모든 데이터를 하나의 응답으로 합치기
//		Map <String, Object> responseData = new HashMap<>();
//		responseData.put("projectList", projectList.getProjectList());
//		responseData.put("scdSts", scdStsList);
//		responseData.put("rqmSts", rqmStsList);
//
//		return ApiResponse.Ok(responseData, responseData == null ? 0 : 1);
//
//	}
//	
//	
//	@GetMapping("/requirement/teammate/{prjIdValue}")
//	public ApiResponse getProjectTeammate(@PathVariable String prjIdValue, Authentication authentication) {
//		
//		// 프로젝트에 해당하는 Teammate 정보를 불러오는 API
//		
//		List<ProjectTeammateVO> prjTeammateList = this.projectService.getAllProjectTeammateByProjectId(prjIdValue);
//		return ApiResponse.Ok(prjTeammateList);
//	}
//	
//	
//	@PostMapping("/requirement/write")
//	public ApiResponse createRequirement(Authentication authentication, @RequestParam(required = false) MultipartFile file, 
//			RequirementVO requirementVO) throws Exception {
//
//		// 입력한 값들을 전송하는 API
//		
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
//		
//		Map<String, List<String>> error = this.requirementValidator(requirementVO);
//		if (error != null) {
//			return ApiResponse.Ok(error);
//		}
//
//		requirementVO.setCrtrId(employeeVO.getEmpId());
//		boolean isSuccess = this.requirementService.insertOneRequirement(requirementVO, file);
//
//		return ApiResponse.Ok(isSuccess);
//	}
//	
//	
//	@GetMapping("/requirement/modify")
//	public ApiResponse getRequirementModifyPage(Authentication authentication, 
//			@RequestParam String prjId, @RequestParam String rqmId) {
//		
//		// 수정 페이지에서 필요한 값들을 가져오는 API
//		
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
//		
//		if (employeeVO.getAdmnCode() != "301") {
//			List<ProjectTeammateVO> tmList = this.projectService.getAllProjectTeammateByProjectId(prjId).stream()
//					.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId())).toList();
//		}
//
//		ProjectListVO projectList = this.projectService.getAllProject();
//		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아니면
//			projectList.setProjectList(this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId()));
//		} else {// 관리자라면
//		}
////		projectList.setProjectList(
////				projectList.getProjectList().stream().filter((project) -> project.getReqYn().equals("Y")).toList());
//		projectList.setProjectList(
//				projectList.getProjectList().stream().toList());
//		List<ProjectTeammateVO> prjTeammateList = this.projectService.getAllProjectTeammateByProjectId(prjId);
//		RequirementVO requirement = this.requirementService.getOneRequirement(rqmId);
//		List<CommonCodeVO> scdSts = this.commonCodeService.getAllCommonCodeListByPId("500");
//		List<CommonCodeVO> rqmSts = this.commonCodeService.getAllCommonCodeListByPId("600");
//
//		if (throwUnauthorizedUser(employeeVO, requirement.getCrtrId())) {
//			throw new AccessDeniedException();
//		}
//
//		// 모든 데이터를 하나의 응답으로 합치기
//		Map <String, Object> responseData = new HashMap<>();
//		responseData.put("requirement", requirement);
//		responseData.put("projectList", projectList.getProjectList());
//		responseData.put("scdSts", scdSts);
//		responseData.put("rqmSts", rqmSts);
//		responseData.put("prjTeammateList", prjTeammateList);
//
//		return ApiResponse.Ok(responseData, responseData == null ? 0 : 1);
//	}
//	
//	
//	@PutMapping("/requirement/modify/{rqmId}")
//	public ApiResponse modifyRequirement(Authentication authentication, @PathVariable String rqmId,
//			RequirementVO requirementVO, @RequestParam(required = false) MultipartFile file) {
//		
//		// 수정한 값들을 전송하는 API
//		
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
//		
//		if (!employeeVO.getAdmnCode().equals("301")) {
//			List<ProjectTeammateVO> tmList = this.projectService
//					.getAllProjectTeammateByProjectId(requirementVO.getPrjId()).stream()
//					.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId())).toList();
//
//			if (tmList.size() == 0) {
//				throw new AccessDeniedException();
//			}
//		}
//		if (throwUnauthorizedUser(employeeVO, this.requirementService.getOneRequirement(rqmId).getCrtrId())) {
//			throw new AccessDeniedException();
//		}
//
//		Map<String, List<String>> error = this.requirementValidator(requirementVO);
//		if (error != null) {
//			return ApiResponse.Ok(error);
//		}
//
//		requirementVO.setMdfrId(employeeVO.getEmpId());
//
////		isSuccess 의 결과에 따라 값을 다르게 반환
//		boolean isSuccess = this.requirementService.updateRequirement(requirementVO, file);
//
//		return ApiResponse.Ok(isSuccess);
//
//	}
//	
//	
//	@DeleteMapping("/requirement/delete/{rqmId}")
//	public ApiResponse deleteRequirement(Authentication authentication, @PathVariable String rqmId) {
//		// 사용자 체크: 삭제는 본인과 관리자만 가능함
//		
//		// 요구사항 게시물을 삭제하는 API
//		
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
//
//		RequirementVO requirementVO = this.requirementService.getOneRequirement(rqmId);
//		if (throwUnauthorizedUser(employeeVO, requirementVO.getCrtrId())) {
//			return ApiResponse.Ok(false);
//		}
////		TODO isSuccess 의 결과에 따라 값을 다르게 반환
//		boolean isSuccess = this.requirementService.deleteOneRequirement(requirementVO);
//		
//		return ApiResponse.Ok(isSuccess);
//		
//	}
//	
//	
//	@GetMapping("/requirement/downloadFile/{rqmId}")
//	public ResponseEntity<Resource> fileDownload(Authentication authentication, @PathVariable String rqmId) {
//
//		// 파일을 다운로드하는 API
//		
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
//		
//		RequirementVO Requirement = this.requirementService.getOneRequirement(rqmId);
//
//		return this.requirementService.getDownloadFile(Requirement);
//	}
//	
//	
//	@PutMapping("/requirement/delaycall/{rqmId}")
//	public ApiResponse delayRequirement(Authentication authentication,
//			@PathVariable String rqmId) {
//		
//		// 일정상태를 '연기신청'으로 변경하는 API
//
//		RequirementVO thisRequirement = this.requirementService.getOneRequirement(rqmId);
//		
//		// '연기필요' 상태로 수정
//		boolean isSuccess = this.requirementService.delayRequirement(thisRequirement);
//
//		AjaxResponse ajax = new AjaxResponse();
//		return ApiResponse.Ok(isSuccess);
//
//	}
//	
//	
//	@PostMapping("/requirement/delayapprove/{rqmId}")
//	public ApiResponse delayRequirementApprove(Authentication authentication, @PathVariable String rqmId,
//			@RequestBody boolean isApprove) {
//		
//		// 승인 => 일정상태를 진행중으로 바꾸고 기간 7일 연장, 거절 => 일정상태를 진행중으로 바꿈
//
//		boolean isSuccess = this.requirementService.updateDelayRequirement(rqmId, isApprove);
//		
//		return ApiResponse.Ok(isSuccess);
//
//	}
//	
//	
//	@PostMapping("/requirement/testresult/{rqmId}")
//	public ApiResponse requirementTestResult(Authentication authentication, @PathVariable String rqmId,
//			@RequestBody boolean testApprove) {
//		
//		// 테스트결과: 성공 =>  일정상태: 종료, 진행상태: 개발완료, 실패 => 진행상태: 개발중 으로 바꿈
//		
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
//	
//		RequirementVO requirementVO= this.requirementService.getOneRequirement(rqmId);	
//		
//		if(!employeeVO.getAdmnCode().equals("301")) { // 관리자가 아니고
//			if(!requirementVO.getTstr().equals(employeeVO.getEmpId())
//					&&  requirementVO.getRqmSts().equals("604")) { // 테스터가 아니면
//				return ApiResponse.Ok("테스트 결과 입력 권한이 없습니다");
////				return new AjaxResponse().append("error", true).
////						append("errorMassage", "테스트 결과 입력 권한이 없습니다");
//			}
//		}
//		
//		boolean isSuccess = this.requirementService.updateTestResult(requirementVO, testApprove);
//		
////		return new AjaxResponse().append("result", isSuccess);
//		return ApiResponse.Ok(isSuccess);
//	}
//	
//	
//	private Map<String, List<String>> requirementValidator(RequirementVO requirementVO) {
//
//		// 입력값 검사
//		
//		Validator<RequirementVO> validator = new Validator<>(requirementVO);
//		validator.add("rqmTtl", Type.NOT_EMPTY, "제목은 필수 입력값입니다").add("prjId", Type.NOT_EMPTY, "프로젝트는 필수 입력값입니다")
//				 .add("dvlrp", Type.NOT_EMPTY, "담당개발자는 필수 입력값입니다").add("cfrmr", Type.NOT_EMPTY, "확인자는 필수 입력값입니다")
//				 .add("tstr", Type.NOT_EMPTY, "테스터는 필수 입력값입니다").add("strtDt", Type.NOT_EMPTY, "시작일은 필수 입력값입니다")
//				 .add("endDt", Type.NOT_EMPTY, "종료일은 필수 입력값입니다").add("rqmCntnt", Type.NOT_EMPTY, "내용은 필수 입력값입니다")
//				 .add("scdSts", Type.NOT_EMPTY, "일정상태는 필수 입력값입니다").add("rqmSts", Type.NOT_EMPTY, "진행상태 필수 입력값입니다")
//				.start();
//		if (!requirementVO.getStrtDt().isEmpty() && !requirementVO.getEndDt().isEmpty()) {
//			validator.add("endDt", Type.DATE, requirementVO.getStrtDt(), "종료일은 시작일보다 커야합니다").start();
//		}
//
//		if (validator.hasErrors()) {
//			return validator.getErrors();
//
//		} else {
//			return null;
//		}
//	}
//	
//	
//	private boolean throwUnauthorizedUser(EmployeeVO employeeVO, String empId) {
//		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아닌경우
//			if (!employeeVO.getEmpId().equals(empId)) {// 본인이 작성한글이 아니면
//				return true;
//			}
//		}
//		return false;
//	}
//}
