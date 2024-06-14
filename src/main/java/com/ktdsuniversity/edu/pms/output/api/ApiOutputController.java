package com.ktdsuniversity.edu.pms.output.api;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
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
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
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
	
	
	@GetMapping("/output/search/{prjIdValue}")
	public ApiResponse getOutputList(@PathVariable String prjIdValue,
			OutputSearchVO outputSearchVO, Authentication authentication) {
		
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
		
		outputSearchVO.setPrjId(prjIdValue);
		OutputListVO outputList = this.outputService.serarchAllOutputList(outputSearchVO);
		

		return ApiResponse.Ok(outputList.getOutputList(), 
				outputList.getListCnt(), 
				outputSearchVO.getPageCount(), 
				outputSearchVO.getPageNo() < outputSearchVO.getPageCount() - 1);
	}
	
	
	@GetMapping("/output/write")
	public ApiResponse getOutputWritePage(Authentication authentication) {
		
		// 산출물 작성과 수정에 필요한 데이터들을 불러오는 API
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		this.checkAccess(employeeVO);
		

		List<ProjectVO> projectList;
		if(employeeVO.getAdmnCode().equals("301")) {
			projectList=this.projectService.getAllProject().getProjectList();
		}else {
			 projectList = this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId());

		}
		projectList =projectList.stream().toList();
		List<CommonCodeVO> outputType = this.commonCodeService.getAllCommonCodeListByPId("1000");
		List<CommonCodeVO> prjSts = this.commonCodeService.getAllCommonCodeListByPId("400");
		
		// 모든 데이터를 하나의 응답으로 합치기
		Map <String, Object> responseData = new HashMap<>();
		responseData.put("projectList", projectList);
		responseData.put("outputType", outputType);
		responseData.put("prjSts", prjSts);
		
		return ApiResponse.Ok(responseData, responseData == null ? 0 : 1);
	}
	
	
	@PostMapping("/output/write/")
	public ApiResponse createOutput(Authentication authentication,
			@RequestParam MultipartFile file, OutputVO outputVO) {
		
		// 입력한 값들을 전송하는 API
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		this.checkAccess(employeeVO, outputVO.getPrjId());
		
		Map<String,List<String>> error = this.outputvalidator(outputVO, file);
		if(error != null) {
			return ApiResponse.Ok(error);
		}
		
		outputVO.setCrtrId(employeeVO.getEmpId());
		boolean isSuccess = this.outputService.insertOneOutput(outputVO, file);
		
		return ApiResponse.Ok(isSuccess);
	}
	
	
	@GetMapping("/output/modify/{outId}")
	public ApiResponse getOutputModifyPage(Authentication authentication, @PathVariable String outId) {
		
		// 수정 페이지에서 필요한 값들을 가져오는 API
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		this.checkAccess(employeeVO);

		List<ProjectVO> projectList;
		if(employeeVO.getAdmnCode().equals("301")) {
			projectList = this.projectService.getAllProject().getProjectList();
		}else {
			 projectList = this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId());

		}
		projectList = projectList.stream().toList();
		List<CommonCodeVO> outputType = this.commonCodeService.getAllCommonCodeListByPId("1000");
		List<CommonCodeVO> prjSts = this.commonCodeService.getAllCommonCodeListByPId("400");
		
		OutputVO output = this.outputService.getOneOutput(outId);
		
		if(this.throwUnauthorizedUser(employeeVO,output.getCrtrId())) {
			throw new AccessDeniedException();		
			}
		
		// 모든 데이터를 하나의 응답으로 합치기
		Map <String, Object> responseData = new HashMap<>();
		responseData.put("output", output);
		responseData.put("projectList", projectList);
		responseData.put("outputType", outputType);
		responseData.put("prjSts", prjSts);

		return ApiResponse.Ok(responseData, responseData == null ? 0 : 1);

	}

	@PutMapping("/output/modify/{outId}")
	public ApiResponse modifyOutput(Authentication authentication, @PathVariable String outId,
			@RequestParam(required = false) MultipartFile file, OutputVO outputVO) {
		
		// 수정한 값들을 전송하는 API
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		this.checkAccess(employeeVO, outputVO.getPrjId());

		Map<String,List<String>> error = this.outputvalidator(outputVO, file);
		if(error != null) {
			return ApiResponse.Ok(error);
		}

		boolean isSuccess = this.outputService.updateOneOutput(outputVO, file);
		
		return ApiResponse.Ok(isSuccess);
	}
	
	
	@DeleteMapping("/output/delete/{outId}")
	public ApiResponse deleteOutputment(Authentication authentication, @PathVariable String outId) {
		
		// 산출물 게시물을 삭제하는 API
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		this.checkAccess(employeeVO);
		OutputVO output = this.outputService.getOneOutput(outId);
		if(this.throwUnauthorizedUser(employeeVO,output.getCrtrId())) {
			return ApiResponse.Ok(false);
			
			}
		boolean isSuccess = this.outputService.deleteOneOutput(outId);	
		
		return ApiResponse.Ok(isSuccess);
	}
	
	
	@GetMapping("/output/downloadFile/{outId}")
	public ResponseEntity<Resource> fileDownload(Authentication authentication, @PathVariable String outId) {
		
		// 파일을 다운로드하는 API
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
		
		this.checkAccess(employeeVO);

		OutputVO Output = this.outputService.getOneOutput(outId);
		
		return this.outputService.getDownloadFile(Output);

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
	
	private Map<String , List<String>> outputvalidator (OutputVO outputVO, MultipartFile file){
		if(file !=null && ! file.isEmpty()) {
			outputVO.setOutFile(file.getOriginalFilename());
		}
		
		Validator<OutputVO> validator = new Validator<>(outputVO);
		validator
		.add("outTtl", Type.NOT_EMPTY, "제목은 필수 입력값입니다")
		.add("prjId", Type.NOT_EMPTY, "프로젝트는 필수 입력값입니다")
		.add("outType", Type.NOT_EMPTY, "산출물 종류은 필수 입력값입니다")
		.add("outVer", Type.NOT_EMPTY, "프로젝트 진행상황은 필수 입력값입니다")
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
