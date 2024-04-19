package com.ktdsuniversity.edu.pms.requirement.web;

import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.login.web.LoginController;
import com.ktdsuniversity.edu.pms.output.vo.OutputVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementListVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementSearchVO;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.team.service.TeamService;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class RequirementController {

	@Autowired
	private RequirementService requirementService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private TeamService teamService ;

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/requirement")
	public String viewAllRequirement() {
		return "redirect:/requirement/search?prjId=ALL";
	}
	
	@GetMapping("/requirement/search")
	public String viewSearchAllRequirement(
			/* @SessionAttribute , */
			@RequestParam String prjId,Model model, 
			RequirementSearchVO requirementSearchVO) {
		// TODO 본인 프로젝트가 아닐경우, 잘못된 프로젝트 아이디가 입력된경우 에러페이지 & 메시지 전달
		RequirementListVO requirementList = requirementService.searchAllRequirement(requirementSearchVO);
        requirementSearchVO.setPageNo(11);//페이지 1로 강제설정 나중에 삭제예정임
        requirementSearchVO.setPageCount(requirementList.getCount()); 
		List<CommonCodeVO> scdSts = this.commonCodeService.getAllCommonCodeListByPId("500");
		List<CommonCodeVO> rqmSts = this.commonCodeService.getAllCommonCodeListByPId("600");
		ProjectListVO projectList = this.projectService.getAllProject();
		projectList.setProjectList(
				projectList.getProjectList().stream()
				.filter((project)->project.getReqYn().equals("Y")).toList());
		
		model.addAttribute("resultList", requirementList)
        .addAttribute("requirementSearch", requirementSearchVO)
		.addAttribute("projectList", projectList)
		.addAttribute("prjId", prjId)
		.addAttribute("scdSts", scdSts)
		.addAttribute("rqmSts", rqmSts)
		.addAttribute("searchOption", requirementSearchVO);
		
		return "requirement/requirementlist";
	}

	@GetMapping("/requirement/view")
	public String viewOneRequirement(
			/* @SessionAttribute , */
			@RequestParam("prjId") String prjId, @RequestParam("rqmId") String rqmId, Model model) {
		// TODO 본인 프로젝트가 아닐경우, 잘못된 프로젝트 아이디가 입력된경우 에러페이지 & 메시지 전달
		RequirementVO requirement = this.requirementService.getOneRequirement(rqmId);

		model.addAttribute("requirement", requirement);

		return "requirement/requirementview";

	}

	@GetMapping("/requirement/write")
	public String viewwritePage(Model model
	/* @SessionAttribute , */) {
		// TODO 사원리스트도 보내줘야 담당자, 테스터, 확인자 체크가능 ->현재는 임의의 사원번호를 넣는중
		ProjectListVO projectList = this.projectService.getAllProject();
		projectList.setProjectList(
				projectList.getProjectList().stream()
				.filter((project)->project.getReqYn().equals("Y")).toList());
		List<CommonCodeVO> scdStsList = this.commonCodeService.getAllCommonCodeListByPId("500");
		List<CommonCodeVO> rqmStsList = this.commonCodeService.getAllCommonCodeListByPId("600");

		model.addAttribute("projectList", projectList).addAttribute("scdSts", scdStsList).addAttribute("rqmSts",
				rqmStsList);

		return "requirement/requirementwrite";

	}

	@PostMapping("/requirement/write")
	public String createRequirement(@RequestParam MultipartFile file /* @SessionAttribute , */
			,RequirementVO requirementVO, Model model) {
		boolean isSuccess = this.requirementService.insertOneRequirement(requirementVO, file);

		return "redirect:/requirement/search?prjId=" + requirementVO.getPrjId();
	}
	@GetMapping("/requirement/downloadFile/{rqmId}")
	public ResponseEntity<Resource> fileDownload(@PathVariable String rqmId) {
		
		RequirementVO Requirement= this.requirementService.getOneRequirement(rqmId);

		return this.requirementService.getDownloadFile(Requirement);
		
	}
	

	@GetMapping("/project/requirement/modify")
	public String viewModifyPage(/* @SessionAttribute , */
			@RequestParam("prjId") String prjId, @RequestParam("rqmId") String rqmId, Model model) {
//		TODO 사원리스트도 보내줘야 담당자, 테스터, 확인자 체크가능 ->현재는 임의의 사원번호를 넣는중
//		TODO 사용자 체크: 수정은 본인과 관리자만 가능함

		RequirementVO requirement = this.requirementService.getOneRequirement(rqmId);
		ProjectListVO projectList = this.projectService.getAllProject();
		projectList.setProjectList(
				projectList.getProjectList().stream()
				.filter((project)->project.getReqYn().equals("Y")).toList());
		List<CommonCodeVO> scdSts = this.commonCodeService.getAllCommonCodeListByPId("500");
		List<CommonCodeVO> rqmSts = this.commonCodeService.getAllCommonCodeListByPId("600");

		model.addAttribute("requirement", requirement).addAttribute("projectList", projectList)
				.addAttribute("scdSts", scdSts).addAttribute("rqmSts", rqmSts);

		return "requirement/requirementmodify";
	}

	@PostMapping("/requirement/modify")
	public String modifyRequirement(@RequestParam String rqmId,
			/* @SessionAttribute , */
			RequirementVO requirementVO, @RequestParam MultipartFile file) {
//		TODO 입력된 정보가 올바른지 확인 필요

//		TODO isSuccess 의 결과에 따라 값을 다르게 반환
		boolean isSuccess = this.requirementService.updateRequirement(requirementVO, file);

		return "redirect:/requirement";

	}

	@GetMapping("/project/requirement/delete")
	public String deleteRequirement(
			/* @SessionAttribute , */
			@RequestParam String rqmId) {
		// TODO 사용자 체크: 삭제는 본인과 관리자만 가능함
		RequirementVO requirementVO = this.requirementService.getOneRequirement(rqmId);

//		TODO isSuccess 의 결과에 따라 값을 다르게 반환
		boolean isSuccess = this.requirementService.deleteOneRequirement(requirementVO);

		return "redirect:/project/requirement?prjId=" + requirementVO.getPrjId();
	}
	@ResponseBody
	@GetMapping("/project/requirement/delaycall")
	public AjaxResponse delayRequirement(/* @SessionAttribute , */
			@RequestParam String rqmId) {
		
		RequirementVO thisRequirement = this.requirementService.getOneRequirement(rqmId);
		boolean isSuccess = this.requirementService.delayRequirement(thisRequirement);
		
		 AjaxResponse ajax = new AjaxResponse();
		return ajax.append("result", isSuccess);

	}
	@ResponseBody
	@GetMapping("/project/requirement/delayaccess")
	public AjaxResponse accessDelay(
			/* @SessionAttribute , */
			@RequestParam String rqmId, @RequestParam boolean dalayApprove) {

		boolean isSuccess = this.requirementService.updateDelayRequirement(rqmId, dalayApprove);
		
		AjaxResponse ajax= new AjaxResponse();
		return ajax.append("result", isSuccess).append("dalayApprove", dalayApprove);
		
	}

}
