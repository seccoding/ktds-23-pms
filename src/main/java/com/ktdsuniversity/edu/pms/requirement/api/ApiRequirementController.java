package com.ktdsuniversity.edu.pms.requirement.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementListVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementSearchVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

@RestController
@RequestMapping("/api/v1")
public class ApiRequirementController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private RequirementService requirementService;
	
	@Autowired
	private CommonCodeService commonCodeService;
	
	@GetMapping("/requirement/search")
	public ApiResponse getRequirementList( 
			RequirementSearchVO requirementSearchVO, Authentication authentication) {
		
		// 요구사항 리스트를 불러오는 API
		
		System.out.println("@@@@@@@@@@@@@@" + authentication + "@@@@@@@@@@@@@");
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		ProjectListVO projectList = this.projectService.getAllProject();
		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아니면
			requirementSearchVO.setEmpId(employeeVO.getEmpId());
			projectList.setProjectList(this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId()));
		} else {
		} // 관리자라면

		projectList.setProjectList(
				projectList.getProjectList().stream().toList());

		RequirementListVO requirementList = requirementService.searchAllRequirement(requirementSearchVO);
		requirementSearchVO.setPageCount(requirementList.getCount());
		List<CommonCodeVO> scdSts = this.commonCodeService.getAllCommonCodeListByPId("500");
		List<CommonCodeVO> rqmSts = this.commonCodeService.getAllCommonCodeListByPId("600");
		
		
		return ApiResponse.Ok(requirementList.getRequirementList(), 
				requirementList.getCount(),
				requirementSearchVO.getPageCount(), 
				requirementSearchVO.getPageNo() < requirementSearchVO.getPageCount() - 1);
	}
	
	
	@GetMapping("/requirement/view")
	public ApiResponse getOneRequirement(Authentication authentication, 
			@RequestParam("prjId") String prjId, @RequestParam("rqmId") String rqmId) {
		
		// 1개의 요구사항에 대한 상세정보를 불러오는 API
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		// 본인 프로젝트가 아닐경우, 잘못된 프로젝트 아이디가 입력된경우 에러페이지 & 메시지 전달
		if (employeeVO.getAdmnCode() != "301") {
			List<ProjectTeammateVO> tmList = this.projectService.getAllProjectTeammateByProjectId(prjId).stream()
					.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId())).toList();
			
		}

		boolean isPmAndPl = false;
		List<ProjectTeammateVO> tmList = this.projectService.getAllProjectTeammateByProjectId(prjId).stream()
				.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId()))
				.filter(tm -> tm.getRole().equals("PM") || tm.getRole().equals("PL")).toList();
		if (tmList.size() > 0) {
			isPmAndPl = true;
		}

		RequirementVO requirement = this.requirementService.getOneRequirement(rqmId);
		

		return ApiResponse.Ok(requirement, requirement == null ? 0 : 1);

	}
	
	
	@GetMapping("/requirement/write")
	public ApiResponse getRequirementWritePage(Authentication authentication) {
		
		// 요구사항 작성 페이지를 불러오는 API
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		ProjectListVO projectList = this.projectService.getAllProject();
		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아니면
			projectList.setProjectList(this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId()));
		} else {// 관리자라면

		}
		projectList.setProjectList(
				projectList.getProjectList().stream().toList());

		List<CommonCodeVO> scdStsList = this.commonCodeService.getAllCommonCodeListByPId("500");
		List<CommonCodeVO> rqmStsList = this.commonCodeService.getAllCommonCodeListByPId("600");
		
		
		// 모든 데이터를 하나의 응답으로 합치기
		Map <String, Object> responseData = new HashMap<>();
		responseData.put("projectList", projectList.getProjectList());
		responseData.put("scdSts", scdStsList);
		responseData.put("rqmSts", rqmStsList);
		
//		System.out.println("@@@@@@@@@@@@@@" + responseData.get("projectList"));
//		System.out.println("##############" + responseData.get("scdSts"));
//		System.out.println("$$$$$$$$$$$$$$" + responseData.get("rqmSts"));

		return ApiResponse.Ok(responseData, responseData == null ? 0 : 1);

	}
	
	
	@GetMapping("/requirement/write/{prjId}")
	public ApiResponse getProjectTeammate(@PathVariable String prjId, Authentication authentication) {
		
		// 프로젝트를 선택할 시에 Teammate 정보를 불러오는 API
		
		List<ProjectTeammateVO> prjTeammateList = this.projectService.getAllProjectTeammateByProjectId(prjId);
		System.out.println("@@@@@@@@@@@prjTeammateList: " + prjTeammateList);
//		return new AjaxResponse().append("prjTeammateList", prjTeammateList);
		return ApiResponse.Ok(prjTeammateList);
	}
	
	
	@PostMapping("/requirement/write")
	public ApiResponse createRequirement(Authentication authentication, MultipartFile file, 
			RequirementVO requirementVO) throws Exception {

		// 입력한 값들을 전송하는 API
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		Map<String, List<String>> error = this.requirementValidator(requirementVO);
		if (error != null) {
			return ApiResponse.Ok(error);
		}

		requirementVO.setCrtrId(employeeVO.getEmpId());
		boolean isSuccess = this.requirementService.insertOneRequirement(requirementVO, file);

		return ApiResponse.Ok(isSuccess);
	}
	
	
	private Map<String, List<String>> requirementValidator(RequirementVO requirementVO) {

		// 입력값 검사
		
		Validator<RequirementVO> validator = new Validator<>(requirementVO);
		validator.add("rqmTtl", Type.NOT_EMPTY, "제목은 필수 입력값입니다").add("prjId", Type.NOT_EMPTY, "프로젝트는 필수 입력값입니다")
				 .add("dvlrp", Type.NOT_EMPTY, "담당개발자는 필수 입력값입니다").add("cfrmr", Type.NOT_EMPTY, "확인자는 필수 입력값입니다")
				 .add("tstr", Type.NOT_EMPTY, "테스터는 필수 입력값입니다").add("strtDt", Type.NOT_EMPTY, "시작일은 필수 입력값입니다")
				 .add("endDt", Type.NOT_EMPTY, "종료일은 필수 입력값입니다").add("rqmCntnt", Type.NOT_EMPTY, "내용은 필수 입력값입니다")
				 .add("scdSts", Type.NOT_EMPTY, "일정상태는 필수 입력값입니다").add("rqmSts", Type.NOT_EMPTY, "요구사항은 필수 입력값입니다")
				.start();
		if (!requirementVO.getStrtDt().isEmpty() && !requirementVO.getEndDt().isEmpty()) {
			validator.add("endDt", Type.DATE, requirementVO.getStrtDt(), "종료일은 시작일보다 커야합니다").start();
		}

		if (validator.hasErrors()) {
			return validator.getErrors();

		} else {
			return null;
		}
	}
}
