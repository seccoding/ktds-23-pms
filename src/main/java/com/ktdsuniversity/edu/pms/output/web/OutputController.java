package com.ktdsuniversity.edu.pms.output.web;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

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
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

@Controller
public class OutputController {
	@Autowired
	private OutputService outputService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private ProjectDao projectDao;

	@GetMapping("/output")
	public String viewOutputList() {
		return "redirect:output/search?prjId=";
	}

	@GetMapping("/output/search")
	public String viewOutputSearhList(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			@RequestParam String prjId, Model model, OutputSearchVO outputSearchVO) {

		this.checkAccess(employeeVO, prjId);
		if (!employeeVO.getAdmnCode().equals("301")) {// 관리자가 아니면
			outputSearchVO.setEmpId(employeeVO.getEmpId());
		}
		
		List<ProjectVO> projectList;
		if(employeeVO.getAdmnCode().equals("301")) {
			projectList=this.projectService.getAllProject().getProjectList();
		}else {
			 projectList = this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId());

		}
//		projectList= projectList.stream().filter(project -> project.getOutYn().equals("Y")).toList();
		projectList= projectList.stream().toList();
		List<CommonCodeVO> commonCodeList = this.commonCodeService.getAllCommonCodeListByPId("1000");
		List<CommonCodeVO> verStsList = this.commonCodeService.getAllCommonCodeListByPId("400");
		OutputListVO outputList = this.outputService.serarchAllOutputList(outputSearchVO);

		model.addAttribute("outputList", outputList).addAttribute("prjId", prjId)
				.addAttribute("projectList", projectList).addAttribute("commonCodeList", commonCodeList)
				.addAttribute("outputSearchVO", outputSearchVO).addAttribute("verStsList", verStsList);

		return "output/outputlist";
	}

	@GetMapping("/output/write")
	public String viewCreateOutput(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, Model model) {
		this.checkAccess(employeeVO);
		

		List<ProjectVO> projectList;
		if(employeeVO.getAdmnCode().equals("301")) {
			projectList=this.projectService.getAllProject().getProjectList();
		}else {
			 projectList = this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId());

		}
//		projectList =projectList.stream().filter(project -> project.getOutYn().equals("Y")).toList();
		projectList =projectList.stream().toList();
		List<CommonCodeVO> outputType = this.commonCodeService.getAllCommonCodeListByPId("1000");
		List<CommonCodeVO> prjSts = this.commonCodeService.getAllCommonCodeListByPId("400");
		model.addAttribute("projectList", projectList).addAttribute("outputType", outputType).addAttribute("prjSts",
				prjSts);
		
		return "output/outputwrite";
	}
	@ResponseBody
	@PostMapping("/ajax/output/write")
	public AjaxResponse createOutput(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			@RequestParam MultipartFile file, OutputVO outputVO, Model model) {
		this.checkAccess(employeeVO, outputVO.getPrjId());
		
		
		
		Map<String,List<String>> error =this.outputvalidator(outputVO,file);
		if(error !=null) {
			return new AjaxResponse().append("error", error);
		}
		
		outputVO.setCrtrId(employeeVO.getEmpId());
		boolean isSuccess = this.outputService.insertOneOutput(outputVO, file);

		return new AjaxResponse().append("result", isSuccess);
		
//		return "redirect:/output";

	}

	@GetMapping("output/downloadFile/{outId}")
	public ResponseEntity<Resource> fileDownload(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			@PathVariable String outId) {
		this.checkAccess(employeeVO);

		OutputVO Output = this.outputService.getOneOutput(outId);

		return this.outputService.getDownloadFile(Output);

	}

	@GetMapping("/output/modify/{outId}")
	public String viewModifyOutputPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			@PathVariable String outId, Model model) {
		this.checkAccess(employeeVO);

		List<ProjectVO> projectList;
		if(employeeVO.getAdmnCode().equals("301")) {
			projectList=this.projectService.getAllProject().getProjectList();
		}else {
			 projectList = this.projectService.getAllProjectByProjectTeammateId(employeeVO.getEmpId());

		}
//		projectList =projectList.stream().filter(project -> project.getOutYn().equals("Y")).toList();
		projectList =projectList.stream().toList();
		List<CommonCodeVO> outputType = this.commonCodeService.getAllCommonCodeListByPId("1000");
		List<CommonCodeVO> prjSts = this.commonCodeService.getAllCommonCodeListByPId("400");
		OutputVO output = this.outputService.getOneOutput(outId);

		if(this.throwUnauthorizedUser(employeeVO,output.getCrtrId())) {
			throw new AccessDeniedException();		
			}
		model.addAttribute("projectList", projectList).addAttribute("outputType", outputType)
				.addAttribute("output", output).addAttribute("prjSts", prjSts);

		return "/output/outputmodify";

	}

	@PostMapping("/output/modify/{outId}")
	public String ModifyOutputPage(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, @PathVariable String outId,
			@RequestParam MultipartFile file, OutputVO outputVO) {
		this.checkAccess(employeeVO, outputVO.getPrjId());

		Validator<OutputVO> validator = new Validator<>(outputVO);
		validator.add("outTtl", Type.NOT_EMPTY, "제목은 필수 입력값입니다").add("outType", Type.NOT_EMPTY, "산출물 타입은 필수 입력값입니다")
				.add("prjId", Type.NOT_EMPTY, "올바르지 않은 프로젝트에서 생성했습니다.").start();

		boolean isSuccess = this.outputService.updateOneOutput(outputVO, file);
		
		return "redirect:/output";
	}
	@ResponseBody
	@PostMapping("/output/delete/{outId}")
	public AjaxResponse deleteOutputment(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, @PathVariable String outId) {
		this.checkAccess(employeeVO);
		OutputVO output = this.outputService.getOneOutput(outId);
		if(this.throwUnauthorizedUser(employeeVO,output.getCrtrId())) {
			return new AjaxResponse().append("error", "권한이 없습니다");
			
			}
		boolean isSuccess = this.outputService.deleteOneOutput(outId);	
		return new AjaxResponse()
				.append("result", isSuccess)
				.append("url", "/output/search?prjId=");
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
//				List<ProjectTeammateVO>  tmList = this.projectService.getAllProjectTeammate()
//				.stream()
//				.filter(tm -> tm.getTmId().equals(employeeVO.getEmpId()))
//				.filter(tm -> tm.getRole().equals("PM")).toList();
//				
//				if(tmList ==null || tmList.isEmpty()) {//PM을 맏은 포지션이 없다면
//					throw new PageNotFoundException();
//				}else {}//pm을 맞은 포지션이 있다면
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
