package com.ktdsuniversity.edu.pms.requirement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
//	public AjaxResponse getAllRequirement(/*@SessionAttribute ,*/ String prjId) {
//		return null;
//		
//	}
//
//	@GetMapping("")
//	public AjaxResponse getOneRequirement(/*@SessionAttribute ,*/ String rqmId) {
//		return null;
//
//	}
//	@PostMapping("")
//	public AjaxResponse modifyRequirement(/*@SessionAttribute ,*/RequirementVO requirementVO) {
//		return null;
//
//	}
//	@PostMapping("")
//	public AjaxResponse deleteRequirement(/*@SessionAttribute ,*/ RequirementVO requirementVO) {
//		return null;
//
//	}
//	public AjaxResponse delayRequirement(/*@SessionAttribute ,*/ String rqmId) {
//		return null;
//
//	}
//	@PostMapping("")
//	public AjaxResponse approveDelay(/*@SessionAttribute ,*/ String rqmId) {
//		return null;
//
//	}

}
