package com.ktdsuniversity.edu.pms.requirement.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

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
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

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
}
