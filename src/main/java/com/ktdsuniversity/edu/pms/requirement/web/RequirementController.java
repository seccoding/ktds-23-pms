package com.ktdsuniversity.edu.pms.requirement.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;
import com.ktdsuniversity.edu.pms.requirement.vo.RequirementVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class RequirementController {

	@Autowired
	private RequirementService requirementService;

//	@GetMapping("")
	public String getAllRequirement(/* @SessionAttribute , */ String prjId, Model model) {
		// TODO
//		1. fillter interceptor 
//		요청자 정보가 비로그인 유져면 out 
//		요청자 정보가 시스템관리자인지? & 팀원정보가 어떤것인지

//		2. select 
//		요구사항리스트를 가져온다 
//		1번의 정보에 따른 스트림으로 걸러내기
//		List<RequirementVO> requirementList = requirementService.getAllRequirement(prjId)
//		.stream()
//		.filter(()->
//			if()
//		).tolist();

//		3. model에 주입 
//		model.addAllAttributes(null)

//		4. go jsp fileName
		return null;

	}

//	@GetMapping("")
	public AjaxResponse getOneRequirement(/* @SessionAttribute , */ String rqmId) {

		return null;

	}

//	@PostMapping("")
	public AjaxResponse modifyRequirement(/* @SessionAttribute , */RequirementVO requirementVO) {
		return null;

	}

//	@PostMapping("")
	public AjaxResponse deleteRequirement(/* @SessionAttribute , */ RequirementVO requirementVO) {
		return null;

	}

	public AjaxResponse delayRequirement(/* @SessionAttribute , */ String rqmId) {
		return null;

	}

//	@PostMapping("")
	public AjaxResponse approveDelay(/* @SessionAttribute , */ String rqmId) {
		return null;

	}

}
