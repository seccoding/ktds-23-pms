package com.ktdsuniversity.edu.pms.output.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ktdsuniversity.edu.pms.output.service.OutputService;
import com.ktdsuniversity.edu.pms.output.vo.OutputListVO;
import com.ktdsuniversity.edu.pms.output.vo.OutputVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
@Controller
public class OutputController {
	@Autowired
	private OutputService outputService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CommonCodeService commonCodeService;
	
	@GetMapping("/output")
	public String getAllOutput(Model model) {
		OutputListVO outputList = this.outputService.getAllOutputList();
		model.addAttribute("outputList",outputList);
		
		return "output/outputlist";
	}
//	public AjaxResponse getOneOutputment() {
//		return null;
//
//	};
	@GetMapping("/output/write")
	public String viewCreateOutput(Model model) {
//		TODO 파일 넣기
		ProjectListVO projectList = this.projectService.getAllProject();
		List<CommonCodeVO> outputType = this.commonCodeService.getAllCommonCodeListByPId("1000");
		model.addAttribute("projectList",projectList)
		.addAttribute("outputType",outputType);
		
		return "output/outputwrite";
	}
	@PostMapping("/output/write")
	public String  createOutput( MultipartFile file,OutputVO outputVO, Model model){
//		TODO 파일 넣기
		boolean isSuccess = this.outputService.updateOneOutput(outputVO);
		
		return "redirect:/output";
		
	}
	
//	public AjaxResponse modifyOutputment() {
//		return null;
//
//	};
//	public AjaxResponse deleteOutputment() {
//		return null;

//	};

}
