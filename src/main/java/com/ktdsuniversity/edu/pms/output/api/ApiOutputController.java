package com.ktdsuniversity.edu.pms.output.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.AccessDeniedException;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.output.service.OutputService;
import com.ktdsuniversity.edu.pms.output.vo.OutputListVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputSearchVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputVO;
import com.ktdsuniversity.edu.pms.project.dao.ProjectDao;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

@RestController
@RequestMapping("/api/v1")
public class ApiOutputController {
	
	@Autowired
	private OutputService outputService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private ProjectDao projectDao;
	
	
	@GetMapping("/output/search")
	public ApiResponse getOutputList( 
			OutputSearchVO outputSearchVO, Authentication authentication) {
		
		System.out.println("######" + authentication + "#########");
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

//		this.checkAccess(employeeVO, prjId);
		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아니면
			outputSearchVO.setEmpId(employeeVO.getEmpId());
		}
		
		List<ProjectVO> projectList;
		if(employeeVO.getAdmnCode().equals("301")) {
			projectList=this.projectService.getAllProject().getProjectList();
		}else {
			 projectList = this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId());

		}
		projectList= projectList.stream().toList();
		List<CommonCodeVO> commonCodeList = this.commonCodeService.getAllCommonCodeListByPId("1000");
		List<CommonCodeVO> verStsList = this.commonCodeService.getAllCommonCodeListByPId("400");
		OutputListVO outputList = this.outputService.serarchAllOutputList(outputSearchVO);
		

		return ApiResponse.Ok(outputList.getOutputList(), 
				outputList.getListCnt(), 
				outputSearchVO.getPageCount(), 
				outputSearchVO.getPageNo() < outputSearchVO.getPageCount() - 1);
	}
	
	
	private void checkAccess(EmployeeVO employeeVO, String prjId) {
		ProjectTeammateVO pmVO = this.projectDao.findPmByProjectId(prjId);
		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아닌경우
			if (! prjId.equals("") || ! prjId.isBlank() ) {// 프로젝트 아이디가 주어진경우
				if (pmVO.getTmId().equals(employeeVO.getEmpId())) {// pm인경우
				} else {// pm이 아닌경우
					throw new PageNotFoundException();
				}
			} else {// 프로젝트 아이디가 안주어진 경우
				checkAccess(employeeVO);
			}
		}
	}

	private void checkAccess(EmployeeVO employeeVO) {
		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아닌경우
			// 프로젝트 아이디가 안주어진 경우
			List<ProjectTeammateVO> tmList = this.projectService.getAllProjectTeammate().stream()
					.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId())).filter(tm -> tm.getRole().equals("PM"))
					.toList();

			if (tmList == null || tmList.isEmpty()) {// PM을 맏은 포지션이 없다면
				throw new AccessDeniedException ();
			} else {
			} // pm을 맞은 포지션이 있다면
		}
	}
	
	private Map<String , List<String>> outputvalidator (OutputVO  outputVO , MultipartFile file){
		if(file !=null && ! file.isEmpty()) {
			outputVO.setOutFile(file.getOriginalFilename());
		}
		
		Validator<OutputVO> validator = new Validator<>(outputVO);
		validator
		.add("outTtl", Type.NOT_EMPTY, "제목은 필수 입력값입니다")
		.add("outType", Type.NOT_EMPTY, "산출물 타입은 필수 입력값입니다")
		.add("prjId", Type.NOT_EMPTY, "올바르지 않은 프로젝트에서 생성했습니다.")
		.add("outFile", Type.NOT_EMPTY, "파일은 필수입력값입니다")
		.start();
	
		
		if(validator.hasErrors()) {
			return validator.getErrors();
		}
		
		return null;
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
