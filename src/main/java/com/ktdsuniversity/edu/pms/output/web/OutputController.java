package com.ktdsuniversity.edu.pms.output.web;

import java.util.List;

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
import com.ktdsuniversity.edu.pms.output.service.OutputService;
import com.ktdsuniversity.edu.pms.output.vo.OutputListVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputSearchVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.Validator;
import com.ktdsuniversity.edu.pms.utils.Validator.Type;

import ch.qos.logback.core.filter.Filter;

@Controller
public class OutputController {
	@Autowired
	private OutputService outputService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CommonCodeService commonCodeService;

	@GetMapping("/output")
	public String viewOutputList() {
		return "redirect:output/search?prjId=";
	}

	@GetMapping("/output/search")
	public String viewOutputSearhList(@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			@RequestParam String prjId, Model model, OutputSearchVO outputSearchVO) {
		
		if(! employeeVO.getAdmnCode().equals("301") && employeeVO.getAdmnCode().equals("PM"))  {//관리자가 아닌경우
			outputSearchVO.setEmpId(employeeVO.getEmpId());
		}
		
		ProjectListVO projectList = this.projectService.getAllProject();
		projectList.setProjectList(
				projectList.getProjectList().stream().filter(project -> project.getOutYn().equals("Y")).toList());
		List<CommonCodeVO> commonCodeList = this.commonCodeService.getAllCommonCodeListByPId("1000");
		List<CommonCodeVO> verStsList = this.commonCodeService.getAllCommonCodeListByPId("400");
		OutputListVO outputList = this.outputService.serarchAllOutputList(outputSearchVO);

		model.addAttribute("outputList", outputList).addAttribute("prjId", prjId)
				.addAttribute("projectList", projectList).addAttribute("commonCodeList", commonCodeList)
				.addAttribute("outputSearchVO", outputSearchVO)
				.addAttribute("verStsList", verStsList);

		return "output/outputlist";
	}

	@GetMapping("/output/write")
	public String viewCreateOutput(Model model) {
//		TODO 파일 넣기
		ProjectListVO projectList = this.projectService.getAllProject();
		projectList.setProjectList(
				projectList.getProjectList().stream().filter((project) -> project.getOutYn().equals("Y")).toList());
		List<CommonCodeVO> outputType = this.commonCodeService.getAllCommonCodeListByPId("1000");
		List<CommonCodeVO> prjSts =this.commonCodeService.getAllCommonCodeListByPId("400");
		model.addAttribute("projectList", projectList).addAttribute("outputType", outputType)
		.addAttribute("prjSts", prjSts);
		return "output/outputwrite";
	}

	@PostMapping("/output/write")
	public String createOutput(@RequestParam MultipartFile file, OutputVO outputVO, Model model) {
		Validator<OutputVO> validator = new Validator<>(outputVO);
		validator.add("outTtl", Type.NOT_EMPTY, "제목은 필수 입력값입니다").add("outType", Type.NOT_EMPTY, "산출물 타입은 필수 입력값입니다")
				.add("prjId", Type.NOT_EMPTY, "올바르지 않은 프로젝트에서 생성했습니다.").start();

		boolean isSuccess = this.outputService.insertOneOutput(outputVO, file);

		return "redirect:/output";

	}

	@GetMapping("output/downloadFile/{outId}")
	public ResponseEntity<Resource> fileDownload(@PathVariable String outId) {

		OutputVO Output = this.outputService.getOneOutput(outId);

		return this.outputService.getDownloadFile(Output);

	}

	@GetMapping("/output/modify/{outId}")
	public String viewModifyOutputPage(@PathVariable String outId, Model model) {
		ProjectListVO projectList = this.projectService.getAllProject();
		List<CommonCodeVO> outputType = this.commonCodeService.getAllCommonCodeListByPId("1000");
		OutputVO output = this.outputService.getOneOutput(outId);

		model.addAttribute("projectList", projectList).addAttribute("outputType", outputType).addAttribute("output",
				output);

		return "/output/outputmodify";

	}

	@PostMapping("/output/modify/{outId}")
	public String ModifyOutputPage(@PathVariable String outId, @RequestParam MultipartFile file, OutputVO outputVO) {

		Validator<OutputVO> validator = new Validator<>(outputVO);
		validator.add("outTtl", Type.NOT_EMPTY, "제목은 필수 입력값입니다").add("outType", Type.NOT_EMPTY, "산출물 타입은 필수 입력값입니다")
				.add("prjId", Type.NOT_EMPTY, "올바르지 않은 프로젝트에서 생성했습니다.").start();

		boolean isSuccess = this.outputService.updateOneOutput(outputVO, file);
		return "redirect:/output";
	}

	@GetMapping("/output/delete/{outId}")
	public String deleteOutputment(@PathVariable String outId, @RequestParam String prjId) {

		boolean isSuccess = this.outputService.deleteOneOutput(outId);

		return "redirect:/output/search?prjId=" + prjId;

	}

}
