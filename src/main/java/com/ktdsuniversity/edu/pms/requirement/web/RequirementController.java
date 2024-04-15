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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class RequirementController {

	@Autowired
	private RequirementService requirementService;

	/**
	 * 전체 리스트를 받아온다. 계정에 정보에따라 노출되는 값이 다름
	 * 
	 * @param prjId 계정정보
	 * @param model
	 * @return
	 */
	@GetMapping("/project/{prjId}/requirement")
	public String viewAllRequirement(
			/* @SessionAttribute , */
			@PathVariable("prjId") String prjId, Model model) {
		// TODO
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지
//		2. select and filter
		List<RequirementVO> requirementList = requirementService.getAllRequirement().stream()
				.filter((requirement) -> requirement.getPrjId().equals(prjId)).collect(Collectors.toList());
//		3. model injection 
		model.addAttribute("resultList", requirementList);
//		4. go jsp fileName
		return "requirement/requirementlist";

	}

	@GetMapping("/project/{prjId}/requirement/{rqmId}")
	public String viewOneRequirement(
			/* @SessionAttribute , */
			@PathVariable("prjId") String prjId, @PathVariable("rqmId") String rqmId, Model model) {
		// TODO
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지
//		2. select 
		RequirementVO requirement = this.requirementService.getOneRequirement(rqmId);
//		3. model injection 
		model.addAttribute("requirement", requirement);
//		4. go jsp fileName
		return "requirement/requirementview";

	}

	@GetMapping("/project/requirement/write")
	public String viewwritePage(
	/* @SessionAttribute , */) {
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지

//		2. go jsp
		return "requirement/requirementwrite";

	}

	@ResponseBody
	@PostMapping("/project/{prjId}/requirement/write")
	public String createRequirement(/* @SessionAttribute , */
			@PathVariable("prjId") String prjId, RequirementVO requirementVO, @RequestParam MultipartFile file,
			Model model) {
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지

		boolean isSuccess = this.requirementService.insertOneRequirement(requirementVO, file);

//		4. isSuccess ==true -> redirect jsp
		return "redirect:/project/" + prjId;

	}

	@GetMapping("/project/{prjId}/requirement/{rqmId}/write")
	public String viewModifyPage(/* @SessionAttribute , */
			@PathVariable("prjId") String prjId, @PathVariable("rqmId") String rqmId, Model model) {
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지

//		2. select 
//		프로젝트에 요구사항이 생성되잇는지 확인하고
//		요구사항이 체크되있으면
//		요구사항을 가져온다 
//		본인이 볼수있는 요구사항인지 확인
		RequirementVO requirement = this.requirementService.getOneRequirement(rqmId);
//		3. 작성자거나, 시스템관리자라면
//		model에 주입 
		model.addAttribute("requirement", requirement);

//		4. go jsp fileName
		return "requirement/requirementwrite";

	}

//	@ResponseBody
//	@PostMapping("")
	public String modifyRequirement(
			/* @SessionAttribute , */
			@PathVariable String rqmId, RequirementVO requirementVO, @RequestParam MultipartFile file) {
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지

//		2.필수정보 입력 확인

//		3.내용 업데이트 
		boolean isSuccess = this.requirementService.updateRequirement(requirementVO, file);

//		4. isSuccess ==true -> redirect jsp
		return null;

	}

//	@ResponseBody
//	@PostMapping("")
	public String deleteRequirement(
			/* @SessionAttribute , */
			@PathVariable String rqmId) {
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지

//		2. 본인이나 관리자라면 
//		삭제코드 실행 
		boolean isSuccess = this.requirementService.deleteOneRequirement(rqmId);

//		4. isSuccess ==true -> redirect jsp
		return null;

	}

//	@ResponseBody
//	@PostMapping("")

	public AjaxResponse delayRequirement(/* @SessionAttribute , */ String rqmId) {
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지

//		2. 연기요청 처리
		RequirementVO thisRequirement = this.requirementService.getOneRequirement(rqmId);
		// setter 로 정보 변경후 업데이트
		boolean isSuccess = this.requirementService.updateRequirement(thisRequirement, null);

//		3. ajax result return 
		AjaxResponse ajax = new AjaxResponse();
		ajax.append("result", isSuccess);
		return ajax;

	}

//	@ResponseBody
//	@PostMapping("")
	public AjaxResponse accessDelay(
			/* @SessionAttribute , */
			@PathVariable String rqmId, @RequestParam boolean dalayApprove) {
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지

//		2. 승인인지 거절인지 확인 
//		그 값에 따라 연기 요청 처리
//		boolean isSuccess = this.requirementService.updateDelayRequirement(rqmId, true or false);

//		3. ajax result return 
//		AjaxResponse ajax = new AjaxResponse();
//		ajax.append("result", isSuccess);

//		return ajax;
		return null;
	}

}
