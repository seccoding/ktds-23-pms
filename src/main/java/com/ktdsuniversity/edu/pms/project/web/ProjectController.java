package com.ktdsuniversity.edu.pms.project.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.exceptions.CreationException;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.SearchProjectVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.StringUtil;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private CommonCodeService commonCodeService;

	@GetMapping("/project")
	public String redirectToProjectSearchPage() {
//        ProjectListVO projectListVO = projectService.getAllProject();
//
//        model.addAttribute("projectList", projectListVO);

		return "redirect:/project/search";
	}

	@GetMapping("/project/search")
	public String viewSearchProjectListPage(Model model,
			SearchProjectVO searchProjectVO) {
//        ProjectListVO projectListVO = projectService.getAllProject();

		ProjectListVO projectListVO = projectService
				.searchProject(searchProjectVO);
		List<CommonCodeVO> projectCommonCodeList = commonCodeService
				.getAllCommonCodeListByPId("400");

		model.addAttribute("commonCodeList", projectCommonCodeList);
		model.addAttribute("projectList", projectListVO);
		model.addAttribute("searchProjectVO", searchProjectVO);

		return "project/projectlist";
	}

	/**
	 * /project/view?projectId=PRJ_240409_000012
	 *
	 * @param projectId
	 * @return
	 */
	@ResponseBody
	@GetMapping("/project/view")
	public AjaxResponse viewProjectDetailPage(@RequestParam String projectId) {
		ProjectVO projectVO = projectService.getOneProject(projectId);

		// 사원 검증 로직, 관리자인지, 프로젝트의 팀에 해당되는 사람인지 확인해야한다. 권한 없으므로 예외
		// boolean isTeammate = projectVO.getProjectTeammateList().stream()
		// .anyMatch(teammate -> teammate.getTmId().equals(세션에 있는 사원 아이디));

		return new AjaxResponse().append("project", projectVO);
	}

	@GetMapping("/project/write")
	public String viewProjectWritePage() {
		return "project/projectwrite";
	}

	// 작성자 추가를 위해 SessionAttribute 추가 필요, @SessionAttribute("_LOGIN_USER_")
	// MemberVO
	// memberVO
	// form action 추가 필요
	@PostMapping("/project/write")
	public String writeProject(CreateProjectVO createProjectVO, Model model) {
		// 0. session memberVO가 admin 이 아닌 경우, return list page or return 400
		// page(잘못된
		// 접근)

		// 1. 데이터 검증, 검증에 맞춰 errorMessage 추가 후 write page return
		// 1-1. 프로젝트명, 고객사명
		// 1-3. 수행부서가 있는 부서인지 확인하는 로직, 수행부서 get logic 이 필요
		// 1-4. 총책임자에 해당하는 pmId로, 사원 get logic 이 필요
		boolean isEmptyPrjName = StringUtil
				.isEmpty(createProjectVO.getPrjName());
		boolean isEmptyClntInfo = StringUtil
				.isEmpty(createProjectVO.getClntInfo());
		boolean isEmptyDeptId = StringUtil.isEmpty(createProjectVO.getDeptId());
		boolean isEmptyStrtDt = StringUtil.isEmpty(createProjectVO.getStrtDt());
		boolean isEmptyEndDt = StringUtil.isEmpty(createProjectVO.getEndDt());

		if (isEmptyPrjName) {
			model.addAttribute("errorMessage", "프로젝트명을 입력해주세요.");
			model.addAttribute("project", createProjectVO);
			return "project/projectwrite";
		}

		if (isEmptyClntInfo) {
			model.addAttribute("errorMessage", "고객사를 입력해주세요.");
			model.addAttribute("project", createProjectVO);
			return "project/projectwrite";
		}

		if (isEmptyDeptId) {
			model.addAttribute("errorMessage", "부서를 선택해주세요.");
			model.addAttribute("project", createProjectVO);
			return "project/projectwrite";
		}

		if (isEmptyStrtDt || isEmptyEndDt) {
			model.addAttribute("errorMessage", "시작일, 종료일을 입력해주세요.");
			model.addAttribute("project", createProjectVO);
			return "project/projectwrite";
		}

		LocalDate strtDt = LocalDate.parse(createProjectVO.getStrtDt(),
				DateTimeFormatter.ISO_DATE);
		LocalDate endDt = LocalDate.parse(createProjectVO.getEndDt(),
				DateTimeFormatter.ISO_DATE);
		if (strtDt.isEqual(endDt) || endDt.isBefore(strtDt)) {
			model.addAttribute("errorMessage",
					"종료일은 시작일보다 이후여야 합니다. 날짜를 다시 설정해주세요.");
			model.addAttribute("project", createProjectVO);
			return "project/projectwrite";
		}

		// 범위 검사를 위한 날짜
		LocalDate startOfThisYear = LocalDate.of(strtDt.getYear(), 1, 1);
		LocalDate endOfFiveYears = LocalDate.now().plusYears(5).withMonth(12)
				.withDayOfMonth(31);

		// 시작일이 올해의 1월 1일 이후인지, 종료일이 5년 후의 12월 31일 이전 또는 그 날짜인지 확인
		if (strtDt.isBefore(startOfThisYear) || endDt.isAfter(endOfFiveYears)) {
			model.addAttribute("errorMessage",
					"시작일은 올해의 1월 1일 이후여야 하며, 종료일은 5년 후의 12월 31일 이전 또는 그 날짜여야 합니다.");
			model.addAttribute("project", createProjectVO);
			return "project/projectwrite";
		}

		if (createProjectVO.getReqYn() == null) {
			if (createProjectVO.getIsYn() != null
					|| createProjectVO.getKnlYn() != null
					|| createProjectVO.getQaYn() != null) {
				model.addAttribute("errorMessage", "비정상적인 요청입니다.");
				model.addAttribute("project", createProjectVO);
				return "project/projectwrite";
			}
		}

		// 2. 검증 로직에 잘 맞춰서 작성한 경우, 데이터 저장
		// 2-1. 세션에서 작성자 id 추출, projectVO.setCrtrId();
		// 현재는 정적 데이터로 해결함.
		createProjectVO.setCrtrId("system01");

		boolean isCreateSuccess = projectService
				.createNewProject(createProjectVO);

		if (!isCreateSuccess) {
			throw new CreationException();
		}

		String prjId = createProjectVO.getPrjId();
		return "redirect:/project/view?projectId=" + prjId;
	}

	/**
	 * TODO
	 */
	@GetMapping("/project/modify/{projectId}")
	public String viewProjectModifyPage(@PathVariable String projectId,
			Model model) {

		ProjectVO projectVO = projectService.getOneProject(projectId);

		// 작성자 또는 PM인지를 검증하는 로직 작성 필요

		model.addAttribute("projectVO", projectVO);

		return "project/projectmodify";
	}

	// 수정자 추가를 위해 SessionAttribute 추가 필요
	@PostMapping("/project/modify/{projectId}")
	public String modifyProject(@PathVariable String projectId) {
		// 1. 프로젝트를 가져와서 있는지 확인
		ProjectVO originalProjectVO = projectService.getOneProject(projectId);

		// 2. 세션으로 관리자 판별 (originalProjectVO와 유저를 판별 및 유저 권한으로 판별), 실패 시 throw
		// new
		// RuntimeException

		// 3. 데이터 수정 여부 확인

		return "redirect:/project/view?projectId=" + projectId;
	}

	// 수정자 추가를 위해 SessionAttribute 추가 필요,
	// 수정자 추가 시, Mapper 에도 컬럼 추가 필요 Parameter 도 Id에서 VO로 변경 필요
	@GetMapping("/project/delete/{projectId}")
	public String deleteProject(@PathVariable String projectId) {
		// 1. 프로젝트를 가져와서 있는지 확인
		ProjectVO originalProjectVO = projectService.getOneProject(projectId);

		// 2. 검증 로직 (originalProjectVO와 유저를 판별 및 유저 권한으로 판별), 실패 시 throw new
		// 작성자 또는 관리자
		// RuntimeException

		// 3. 데이터 삭제 여부 확인
		boolean isDeleteSuccess = projectService.deleteOneProject(projectId);

//        if (isDeleteSuccess) {
//            성공로그
//        } else {
//            실패로그
//        }

		return "redirect:/project";
	}
}
