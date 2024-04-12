package com.ktdsuniversity.edu.pms.project.web;

import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping("/project")
	public String viewProjectListPage(Model model) {
		ProjectListVO projectListVO = projectService.getAllProject();

		model.addAttribute("projectList", projectListVO);

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

		return new AjaxResponse().append("oneProject", projectVO);
	}

	@GetMapping("/project/write")
	public String viewProjectWritePage() {
		return "project/projectwrite";
	}

	// 작성자 추가를 위해 SessionAttribute 추가 필요, @SessionAttribute("_LOGIN_USER_") MemberVO
	// memberVO
	// form action 추가 필요
	@PostMapping("/project/write")
	public String writeProject(CreateProjectVO registProjectVO) {
		// 0. session memberVO가 admin 이 아닌 경우, return list page or return 400 page(잘못된
		// 접근)

		// 1. 데이터 검증, 검증에 맞춰 errorMessage 추가 후 write page return
		// 1-1. 프로젝트명, 고객사명
		// 1-2. 요구사항 관리 필요여부 없이 보낼 시, 다른 게시판 서비스 3개를 null 로 처리

		// 1-3. 수행부서가 있는 부서인지 확인하는 로직, 수행부서 get logic 이 필요
		// 1-4. 총책임자에 해당하는 pmId로, 사원 get logic 이 필요

		// 2. 검증 로직에 잘 맞춰서 작성한 경우, 데이터 저장
		// 2-1. 세션에서 작성자 id 추출, projectVO.setCrtrId();

		boolean isCreateSuccess = projectService.createNewProject(registProjectVO);

		return "redirect:/project/view";
	}

	/**
	 * TODO
	 */
	// 수정자 추가를 위해 SessionAttribute 추가 필요
	@PostMapping("/project/modify/{projectId}")
	public String modifyProject(@PathVariable String projectId) {
		// 1. 프로젝트를 가져와서 있는지 확인
		ProjectVO originalProjectVO = projectService.getOneProject(projectId);

		// 2. 세션으로 관리자 판별 (originalProjectVO와 유저를 판별 및 유저 권한으로 판별), 실패 시 throw new
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
