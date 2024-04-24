package com.ktdsuniversity.edu.pms.survey.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectSurveyQuestionVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.SearchProjectVO;
import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionPickService;
import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionService;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class SurveyController {

	@Autowired
	private SurveyQuestionService surveyQuestionService;
	
	@Autowired
	private SurveyQuestionPickService surveyQuestionPickService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private CommonCodeService commonCodeService;

	@GetMapping("/survey/list")
	public String viewSurveyListPage(Model model, SearchSurveyVO searchSurveyVO, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		searchSurveyVO.setEmployeeVO(employeeVO);
		
		SurveyListVO surveyListVO = this.surveyQuestionService.searchProject(searchSurveyVO);
		List<CommonCodeVO> projectCommonCodeList = commonCodeService.getAllCommonCodeListByPId("400");
		
		model.addAttribute("commonCodeList", projectCommonCodeList);
		model.addAttribute("surveyList", surveyListVO);
		model.addAttribute("SearchSurveyVO", searchSurveyVO);
		
//		SurveyListVO surveyListVO = this.surveyQuestionService.getAllSurvey();
//		model.addAttribute("surveyList", surveyListVO);

		return "survey/surveylist";
	}

	@GetMapping("/survey/write")
	public String viewSurveyWritePage(@RequestParam String prjId, Model model) {
		SurveyQuestionVO surveyQuestionVO = this.surveyQuestionService.getOneSurveyForWrite(prjId);
		List<SurveyQuestionVO> questionList = this.surveyQuestionService.getAllQuestions().getQuestionList();
		List<SurveyQuestionPickVO> pickList = this.surveyQuestionPickService.getAllPicks().getPickList();
		
		model.addAttribute("surveyQuestionVO", surveyQuestionVO);
		model.addAttribute("questionList", questionList);
		model.addAttribute("pickList", pickList);
		return "survey/surveywrite";
	}

	@GetMapping("/survey/create")
	public String viewSurveyCreatePage(@RequestParam String prjId, Model model) {
		ProjectVO projectVO = this.projectService.getOneProject(prjId);
		SurveyQuestionVO surveyQuestionVO = this.surveyQuestionService.getOneSurvey(prjId);
		model.addAttribute("projectVO", projectVO);
		model.addAttribute("surveyQuestionVO", surveyQuestionVO);
		return "survey/surveycreate";
	}

	@ResponseBody
	@GetMapping("/ajax/survey/get/{prjId}")
	public AjaxResponse getAllSurveys(@PathVariable String prjId, SearchSurveyVO searchSurveyVO) {
		searchSurveyVO.setPrjId(prjId);
		List<SurveyQuestionVO> surveyList = this.surveyQuestionService.getAllSurveys(searchSurveyVO);

		return new AjaxResponse().append("surveys", surveyList);
	}

	@ResponseBody
	@GetMapping("/ajax/survey/write/{prjId}")
	public AjaxResponse getAllQuestionsForWrite(@PathVariable String prjId, SurveyQuestionVO surveyQuestionVO) {
		surveyQuestionVO.setPrjId(prjId);
		
		List<SurveyQuestionVO> questionList = this.surveyQuestionService.getAllQuestions(surveyQuestionVO);
		
		return new AjaxResponse().append("questions", questionList);
	}
	
	@ResponseBody
	@GetMapping("/ajax/survey/write/pick/{srvId}")
	public AjaxResponse getAllPicksForWrite(@PathVariable String srvId, SurveyQuestionPickVO surveyQuestionPickVO) {
		surveyQuestionPickVO.setSrvId(srvId);
		
		List<SurveyQuestionPickVO> pickList = this.surveyQuestionPickService.getAllPicks(surveyQuestionPickVO);
		
		return new AjaxResponse().append("picks", pickList);
	}

	@ResponseBody
	@PostMapping("/ajax/survey/create/{prjId}")
	public AjaxResponse doSurveyCreate(@PathVariable String prjId, SurveyQuestionVO surveyQuestionVO) {
		surveyQuestionVO.setPrjId(prjId);

		boolean isSuccess = this.surveyQuestionService.createNewSurveyQuestion(surveyQuestionVO);
		return new AjaxResponse().append("result", isSuccess).append("srvId", surveyQuestionVO.getSrvId());
	}

	@ResponseBody
	@PostMapping("/ajax/survey/createbody/{prjId}")
	public AjaxResponse doCreateSurveyBody(@PathVariable String prjId, SurveyQuestionVO surveyQuestionVO) {
		surveyQuestionVO.setPrjId(prjId);

		boolean isSuccess = this.surveyQuestionService.createSurveyBody(surveyQuestionVO);
		return new AjaxResponse().append("result", isSuccess);
	}

	@ResponseBody
	@PostMapping("/ajax/survey/modify/{srvId}")
	public AjaxResponse doModifySurvey(@PathVariable String srvId, SurveyQuestionVO surveyQuestionVO) {
		surveyQuestionVO.setSrvId(srvId);

		boolean isSuccess = this.surveyQuestionService.modifyOneSurvey(surveyQuestionVO);
		return new AjaxResponse().append("result", isSuccess);
	}

	@ResponseBody
	@PostMapping("/ajax/survey/modify/next/{srvId}")
	public AjaxResponse doModifySurveyExceptBody(@PathVariable String srvId, SurveyQuestionVO surveyQuestionVO) {
		surveyQuestionVO.setSrvId(srvId);

		boolean isSuccess = this.surveyQuestionService.modifyOneSurveyExceptBody(surveyQuestionVO);
		return new AjaxResponse().append("result", isSuccess);
	}

}
