package com.ktdsuniversity.edu.pms.requirement.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.AccessDeniedException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementListVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementSearchVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

@Controller
public class RequirementController {

	@Autowired
	private RequirementService requirementService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CommonCodeService commonCodeService;

//	@Autowired
//	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/requirement")
	public String viewAllRequirement() {
		return "redirect:/requirement/search?prjId=";
	}

	@GetMapping("/requirement/search")
	public String viewSearchAllRequirement(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			@RequestParam String prjId, Model model, RequirementSearchVO requirementSearchVO) {
		ProjectListVO projectList = this.projectService.getAllProject();
		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아니면
			requirementSearchVO.setEmpId(employeeVO.getEmpId());
			projectList.setProjectList(this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId()));
		} else {
		} // 관리자라면

//		projectList.setProjectList(
//				projectList.getProjectList().stream().filter((project) -> project.getReqYn().equals("Y")).toList());
		projectList.setProjectList(
				projectList.getProjectList().stream().toList());

		RequirementListVO requirementList = requirementService.searchAllRequirement(requirementSearchVO);
		requirementSearchVO.setPageCount(requirementList.getCount());
		List<CommonCodeVO> scdSts = this.commonCodeService.getAllCommonCodeListByPId("500");
		List<CommonCodeVO> rqmSts = this.commonCodeService.getAllCommonCodeListByPId("600");

		model.addAttribute("resultList", requirementList).addAttribute("requirementSearch", requirementSearchVO)
				.addAttribute("projectList", projectList).addAttribute("prjId", prjId).addAttribute("scdSts", scdSts)
				.addAttribute("rqmSts", rqmSts).addAttribute("searchOption", requirementSearchVO);

		return "requirement/requirementlist";
	}

	@GetMapping("/requirement/view")
	public String viewOneRequirement(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			@RequestParam("prjId") String prjId, @RequestParam("rqmId") String rqmId, Model model) {
		// 본인 프로젝트가 아닐경우, 잘못된 프로젝트 아이디가 입력된경우 에러페이지 & 메시지 전달
		if (employeeVO.getAdmnCode() != "301") {
			List<ProjectTeammateVO> tmList = this.projectService.getAllProjectTeammateByProjectId(prjId).stream()
					.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId())).toList();

//			if(tmList.size() == 0  ) {
//				throw new PageNotFoundException();
//			}
		}

		boolean isPmAndPl = false;
		List<ProjectTeammateVO> tmList = this.projectService.getAllProjectTeammateByProjectId(prjId).stream()
				.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId()))
				.filter(tm -> tm.getRole().equals("PM") || tm.getRole().equals("PL")).toList();
		if (tmList.size() > 0) {
			isPmAndPl = true;
		}

		RequirementVO requirement = this.requirementService.getOneRequirement(rqmId);
		model.addAttribute("requirement", requirement).addAttribute("employeeVO", employeeVO).addAttribute("isPmAndPl",
				isPmAndPl);

		return "requirement/requirementview";

	}

	@GetMapping("/requirement/write")
	public String viewwritePage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, Model model) {
		ProjectListVO projectList = this.projectService.getAllProject();
		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아니면
			projectList.setProjectList(this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId()));
		} else {// 관리자라면

		}

//		projectList.setProjectList(
//				projectList.getProjectList().stream().filter((project) -> project.getReqYn().equals("Y")).toList());
		projectList.setProjectList(
				projectList.getProjectList().stream().toList());

		List<CommonCodeVO> scdStsList = this.commonCodeService.getAllCommonCodeListByPId("500");
		List<CommonCodeVO> rqmStsList = this.commonCodeService.getAllCommonCodeListByPId("600");
//		List<ProjectTeammateVO> prjTeammateList = this.projectService
//				.getAllProjectTeammateByProjectId("PRJ_240409_000012");

		model.addAttribute("projectList", projectList).addAttribute("scdSts", scdStsList)
				.addAttribute("rqmSts", rqmStsList)
				;

		return "requirement/requirementwrite";

	}

	@ResponseBody
	@PostMapping("/ajax/requirement/write")
	public AjaxResponse createRequirement(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, MultipartFile file,
			RequirementVO requirementVO, Model model) throws Exception {

		Map<String, List<String>> error = this.requirementValidator(requirementVO);
		if (error != null) {
			return new AjaxResponse().append("error", error);
		}

		requirementVO.setCrtrId(employeeVO.getEmpId());
		boolean isSuccess = this.requirementService.insertOneRequirement(requirementVO, file);

		return new AjaxResponse().append("result", isSuccess);
//				"redirect:/requirement/search?prjId=" + requirementVO.getPrjId();
	}

	@GetMapping("/requirement/downloadFile/{rqmId}")
	public ResponseEntity<Resource> fileDownload(@PathVariable String rqmId) {

		RequirementVO Requirement = this.requirementService.getOneRequirement(rqmId);

		return this.requirementService.getDownloadFile(Requirement);

	}

	@ResponseBody
	@GetMapping("/requirement/teammate/{prjId}")
	public AjaxResponse postProjectTeammate(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			@PathVariable String prjId) {
		List<ProjectTeammateVO> prjTeammateList = this.projectService.getAllProjectTeammateByProjectId(prjId);

		return new AjaxResponse().append("prjTeammateList", prjTeammateList);
	}

	@GetMapping("/project/requirement/modify")
	public String viewModifyPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, @RequestParam String prjId,
			@RequestParam String rqmId, Model model) {
		if (employeeVO.getAdmnCode() != "301") {
			List<ProjectTeammateVO> tmList = this.projectService.getAllProjectTeammateByProjectId(prjId).stream()
					.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId())).toList();
		}

		ProjectListVO projectList = this.projectService.getAllProject();
		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아니면
			projectList.setProjectList(this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId()));
		} else {// 관리자라면
		}
//		projectList.setProjectList(
//				projectList.getProjectList().stream().filter((project) -> project.getReqYn().equals("Y")).toList());
		projectList.setProjectList(
				projectList.getProjectList().stream().toList());
		List<ProjectTeammateVO> prjTeammateList = this.projectService.getAllProjectTeammateByProjectId(prjId);
		RequirementVO requirement = this.requirementService.getOneRequirement(rqmId);
		List<CommonCodeVO> scdSts = this.commonCodeService.getAllCommonCodeListByPId("500");
		List<CommonCodeVO> rqmSts = this.commonCodeService.getAllCommonCodeListByPId("600");

		if (throwUnauthorizedUser(employeeVO, requirement.getCrtrId())) {
			throw new AccessDeniedException();
		}

		model.addAttribute("requirement", requirement).addAttribute("projectList", projectList)
				.addAttribute("scdSts", scdSts).addAttribute("rqmSts", rqmSts)
				.addAttribute("prjTeammateList", prjTeammateList);

		return "requirement/requirementmodify";
	}

	@ResponseBody
	@PostMapping("/ajax/requirement/modify")
	public AjaxResponse modifyRequirement(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, String rqmId,
			RequirementVO requirementVO, MultipartFile file) {
		if (!employeeVO.getAdmnCode().equals("301")) {
			List<ProjectTeammateVO> tmList = this.projectService
					.getAllProjectTeammateByProjectId(requirementVO.getPrjId()).stream()
					.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId())).toList();

			if (tmList.size() == 0) {
				throw new AccessDeniedException();
			}
		}
		if (throwUnauthorizedUser(employeeVO, this.requirementService.getOneRequirement(rqmId).getCrtrId())) {
			throw new AccessDeniedException();
		}

		Map<String, List<String>> error = this.requirementValidator(requirementVO);
		if (error != null) {
			return new AjaxResponse().append("error", error);
		}

		requirementVO.setMdfrId(employeeVO.getEmpId());

//		isSuccess 의 결과에 따라 값을 다르게 반환
		boolean isSuccess = this.requirementService.updateRequirement(requirementVO, file);

		return new AjaxResponse().append("result", isSuccess);
//		return "redirect:/requirement";

	}

	@ResponseBody
	@PostMapping("/project/requirement/delete")
	public AjaxResponse deleteRequirement(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, String rqmId) {
		// 사용자 체크: 삭제는 본인과 관리자만 가능함

		RequirementVO requirementVO = this.requirementService.getOneRequirement(rqmId);
		if (throwUnauthorizedUser(employeeVO, requirementVO.getCrtrId())) {
			return new AjaxResponse().append("result", false);
		}
//		TODO isSuccess 의 결과에 따라 값을 다르게 반환
		boolean isSuccess = this.requirementService.deleteOneRequirement(requirementVO);
		return new AjaxResponse().append("result", isSuccess).append("url",
				"/requirement/search?prjId=" + requirementVO.getPrjId());

//		return "redirect:/project/requirement?prjId=" + requirementVO.getPrjId();
	}

	@ResponseBody
	@PostMapping("/project/requirement/delaycall")
	public AjaxResponse delayRequirement(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			@RequestParam String rqmId) {

		RequirementVO thisRequirement = this.requirementService.getOneRequirement(rqmId);
		boolean isSuccess = this.requirementService.delayRequirement(thisRequirement);

		AjaxResponse ajax = new AjaxResponse();
		return ajax.append("result", isSuccess);

	}

	@ResponseBody
	@PostMapping("/project/requirement/delayaccess")
	public AjaxResponse accessDelay(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, @RequestParam String rqmId,
			@RequestParam boolean dalayApprove) {

		boolean isSuccess = this.requirementService.updateDelayRequirement(rqmId, dalayApprove);

		AjaxResponse ajax = new AjaxResponse();
		return ajax.append("result", isSuccess).append("dalayApprove", dalayApprove);

	}
	@ResponseBody
	@PostMapping("/ajax/project/requirement/testresult")
	public AjaxResponse testResult(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, @RequestParam String rqmId,
			boolean testApprove) {
	
	RequirementVO requirementVO= this.requirementService.getOneRequirement(rqmId);	
	if(!employeeVO.getAdmnCode().equals("301")) {//관리자가 아니고
		if(!requirementVO.getTstr().equals(employeeVO.getEmpId())
				&&  requirementVO.getRqmSts().equals("604")) {
			return new AjaxResponse().append("error", true).
					append("errorMassage", "테스트 결과 입력 권한이 없습니다");
		}
	}
	
	boolean isSuccess = this.requirementService.updateTestResult(requirementVO, testApprove);
		
		return new AjaxResponse().append("result", isSuccess);
	}

	private Map<String, List<String>> requirementValidator(RequirementVO requirementVO) {

		Validator<RequirementVO> validator = new Validator<>(requirementVO);
		validator.add("rqmTtl", Type.NOT_EMPTY, "제목은 필수 입력값입니다").add("rqmCntnt", Type.NOT_EMPTY, "내용은 필수 입력값입니다")
				.add("strtDt", Type.NOT_EMPTY, "시작일은 필수 입력값입니다").add("endDt", Type.NOT_EMPTY, "종료일은 필수 입력값입니다")
				.add("dvlrp", Type.NOT_EMPTY, "담당개발자는 필수 입력값입니다").add("cfrmr", Type.NOT_EMPTY, "확인자는 필수 입력값입니다")
				.add("tstr", Type.NOT_EMPTY, "테스터는 필수 입력값입니다").add("scdSts", Type.NOT_EMPTY, "일정상태는 필수 입력값입니다")
				.add("rqmSts", Type.NOT_EMPTY, "요구사항은 필수 입력값입니다").add("prjId", Type.NOT_EMPTY, "프로젝트는 필수 입력값입니다")
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

	private boolean throwUnauthorizedUser(EmployeeVO employeeVO, String empId) {
		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아닌경우
			if (!employeeVO.getEmpId().equals(empId)) {// 본인이 작성한글이 아니면
				return true;
			}
		}
		return false;
	}

}
