package com.ktdsuniversity.edu.pms.requirement.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class RequirementController {

	@Autowired
	private RequirementService requirementService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CommonCodeService commonCodeService ;

	
	/**
	 * 전체 리스트를 받아온다. 계정에 정보에따라 노출되는 값이 다름
	 * 
	 * @param prjId 계정정보
	 * @param model
	 * @return
	 */
	@GetMapping("/project/requirement")
	public String viewAllRequirement(
			/* @SessionAttribute , */
			@RequestParam("prjId") String prjId, Model model) {
		// TODO
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지
		
//		2. select and filter
		List<RequirementVO> requirementList = requirementService.getAllRequirement().stream()
				.filter((requirement) ->requirement.getPrjId().equals(prjId))
				.collect(Collectors.toList());

		model.addAttribute("resultList", requirementList);

		return "requirement/requirementlist";
	}

	@GetMapping("/project/requirement/view")
	public String viewOneRequirement(
			/* @SessionAttribute , */
			@RequestParam("prjId") String prjId, @RequestParam("rqmId") String rqmId, Model model) {
		// TODO
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지
//		2. select 
		RequirementVO requirement = this.requirementService.getOneRequirement(rqmId);

		model.addAttribute("requirement", requirement);

		return "requirement/requirementview";

	}

	@GetMapping("/project/requirement/write")
	public String viewwritePage(Model model
	/* @SessionAttribute , */) {
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지
		ProjectListVO projectList = projectService.getAllProject();
		List<CommonCodeVO> scdSts = commonCodeService.getAllCommonCodeListByPId("500");
		List<CommonCodeVO> rqmSts = commonCodeService.getAllCommonCodeListByPId("600");
		
		model.addAttribute("projectList",projectList)
			.addAttribute("scdSts",scdSts)
			.addAttribute("rqmSts",rqmSts);

		return "requirement/requirementwrite";

	}
	@GetMapping("/project/requirement/modify")
	public String viewModifyPage(/* @SessionAttribute , */
			@RequestParam("prjId") String prjId, @RequestParam("rqmId") String rqmId, Model model) {
//		TODO fillter interceptor, 필수정보 입력확인, 파일 업로드 체크
		RequirementVO  requirement= this.requirementService.getOneRequirement(rqmId);
		ProjectListVO projectList = this.projectService.getAllProject();
		List<CommonCodeVO> scdSts = this.commonCodeService.getAllCommonCodeListByPId("500");
		List<CommonCodeVO> rqmSts = this.commonCodeService.getAllCommonCodeListByPId("600");
		
		model.addAttribute("requirement",requirement)
		.addAttribute("projectList",projectList)
		.addAttribute("scdSts",scdSts).addAttribute("rqmSts",rqmSts);

		return "requirement/requirementmodify";
	}

	@PostMapping("/project/requirement/write")
	public String createRequirement(/* @SessionAttribute , */
			 RequirementVO requirementVO,
			 @RequestParam MultipartFile file,
			Model model) {
		boolean isSuccess = this.requirementService.insertOneRequirement(requirementVO, null);
		
		return "redirect:/project/requirement?prjId="+requirementVO.getPrjId();			
	}

	@PostMapping("/project/requirement/modify")
	public String modifyRequirement(@RequestParam String rqmId,
			/* @SessionAttribute , */
			RequirementVO requirementVO, @RequestParam MultipartFile file) {
//		TODO fillter interceptor, 필수정보 입력확인, 파일 업로드 체크

		boolean isSuccess = this.requirementService.updateRequirement(requirementVO, file);

		return "redirect:/project/requirement?prjId="+requirementVO.getPrjId();

	}

	@GetMapping("/project/requirement/delete")
	public String deleteRequirement(
			/* @SessionAttribute , */
			@RequestParam String rqmId) {
		//TODO 사용자 체크 fillter interceptor,
		RequirementVO requirementVO=this.requirementService.getOneRequirement(rqmId);
		
		boolean isSuccess = this.requirementService.deleteOneRequirement(requirementVO);

		return "redirect:/project/requirement?prjId="+requirementVO.getPrjId();
	}


	@GetMapping("/project/requirement/delaycall")
	public String delayRequirement(/* @SessionAttribute , */ 
			@RequestParam String rqmId) {
//		2. 연기요청 처리
		RequirementVO thisRequirement = this.requirementService.getOneRequirement(rqmId);
		// setter 로 정보 변경후 업데이트
		boolean isSuccess = this.requirementService.delayRequirement(thisRequirement);

		
		return "redirect:/project/requirement?prjId="+thisRequirement.getPrjId();

	}

	@GetMapping("/project/requirement/delayaccess")
	public AjaxResponse accessDelay(
			/* @SessionAttribute , */
			@RequestParam String rqmId, @RequestParam boolean dalayApprove) {
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지

//		2. 승인인지 거절인지 확인 
//		그 값에 따라 연기 요청 처리
		boolean isSuccess = this.requirementService.updateDelayRequirement(rqmId, dalayApprove);

//		3. ajax result return 
//		AjaxResponse ajax = new AjaxResponse();
//		ajax.append("result", isSuccess);
//		return ajax;
		return null;
	}

}
