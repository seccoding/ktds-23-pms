package com.ktdsuniversity.edu.pms.survey.web;

import java.util.ArrayList;
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
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionPickService;
import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionService;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyQuestionPickVO;
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

//	@GetMapping("/survey/list") public String viewSurveyListPage(SearchSurveyVO
//	searchSurveyVO, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
//	Model model) { searchSurveyVO.setEmployeeVO(employeeVO);
//	searchSurveyVO.setEmpId(employeeVO.getEmpId());
//	
//	SurveyListVO teammate =
//	this.surveyQuestionService.searchTeammate(searchSurveyVO); SurveyListVO
//	surveyListVO = this.surveyQuestionService.searchProject(searchSurveyVO);
//	
//	List<CommonCodeVO> projectCommonCodeList =
//	commonCodeService.getAllCommonCodeListByPId("400");
//	
//	List<ProjectTeammateVO> tmList =
//	this.projectService.getAllProjectTeammate().stream()
//	.filter(tm->tm.getTmId().equals(employeeVO.getEmpId()))
//	.filter(tm->tm.getRole().equals("PM")) .toList(); boolean isPM = false; if
//	(tmList.size()>0) { // PM인 경우 isPM = true; }
//	
//	List<ProjectTeammateVO> surveyYn =
//	this.projectService.getAllProjectTeammate().stream()
//	.filter(tm->tm.getTmId().equals(employeeVO.getEmpId())) .toList();
//	
//	// model.addAttribute("surveyYn", surveyYn); //
//	model.addAttribute("teammate", teammate); //
//	model.addAttribute("commonCodeList", projectCommonCodeList); //
//	model.addAttribute("surveyList", surveyListVO); //
//	model.addAttribute("SearchSurveyVO", searchSurveyVO); //
//	model.addAttribute("isPM", isPM);
//	
//	return "survey/surveylist"; 

//	@GetMapping("/survey/view")
//	public String viewCompleteSurveyPage(@RequestParam String prjId, Model model) {
//		SurveyQuestionVO surveyQuestion = this.surveyQuestionService.getOneSurveyForWrite(prjId);
//	    SurveyQuestionVO surveyQuestionVO = new SurveyQuestionVO();
//	    surveyQuestionVO.setPrjId(prjId);
//
//	    SurveyListVO questionList = this.surveyQuestionService.searchAllQuestions(surveyQuestionVO);
//	    List<SurveyQuestionPickVO> allPicks = new ArrayList<>();
//
//	    for (SurveyQuestionVO question : questionList.getQuestionList()) {
//	        SurveyQuestionPickVO surveyQuestionPickVO = new SurveyQuestionPickVO();
//	        surveyQuestionPickVO.setSrvId(question.getSrvId());
//	        SurveyListVO pickList = this.surveyQuestionPickService.searchAllPicks(surveyQuestionPickVO);
//	        allPicks.addAll(pickList.getPickList());
//	    }
//
//	    model.addAttribute("surveyQuestionVO", surveyQuestion);
//	    model.addAttribute("questionList", questionList);
//	    model.addAttribute("pickList", allPicks);
//	    return "survey/surveyview";
//	}

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
	public AjaxResponse doSurveyCreate(@PathVariable String prjId,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, SurveyQuestionVO surveyQuestionVO) {
		surveyQuestionVO.setPrjId(prjId);
		surveyQuestionVO.setCrtrId(employeeVO.getEmpId());

		boolean isSuccess = this.surveyQuestionService.createNewSurveyQuestion(surveyQuestionVO);
		return new AjaxResponse().append("result", isSuccess).append("srvId", surveyQuestionVO.getSrvId())
				.append("user", employeeVO.getEmpId());
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
	public AjaxResponse doModifySurvey(@PathVariable String srvId,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, SurveyQuestionVO surveyQuestionVO) {
		surveyQuestionVO.setSrvId(srvId);
		surveyQuestionVO.setMdfrId(employeeVO.getEmpId());

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

	@ResponseBody
	@PostMapping("/ajax/survey/delete/{srvId}")
	public AjaxResponse doDeleteSurvey(@PathVariable String srvId,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, SurveyQuestionVO surveyQuestionVO) {
		surveyQuestionVO.setSrvId(srvId);
		surveyQuestionVO.setMdfrId(employeeVO.getEmpId());

		boolean isSuccess = this.surveyQuestionService.deleteOneSurvey(surveyQuestionVO);
		return new AjaxResponse().append("result", isSuccess);
	}

	@ResponseBody
	@GetMapping("/ajax/survey/get/pick/{srvId}")
	public AjaxResponse getAllSurveyPicks(@PathVariable String srvId,
			SearchSurveyQuestionPickVO searchSurveyQuestionPickVO) {
		searchSurveyQuestionPickVO.setSrvId(srvId);
		List<SurveyQuestionPickVO> surveypickList = this.surveyQuestionPickService
				.getAllPicks(searchSurveyQuestionPickVO);

		return new AjaxResponse().append("picks", surveypickList);
	}

	@ResponseBody
	@PostMapping("/ajax/survey/answer/{srvId}")
	public AjaxResponse doCreateNewAnswer(@PathVariable String srvId,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, SurveyQuestionPickVO surveyQuestionPickVO) {
		surveyQuestionPickVO.setSrvId(srvId);
		
		boolean isSuccess = this.surveyQuestionPickService.createNewAnswer(surveyQuestionPickVO);
		return new AjaxResponse().append("result", isSuccess).append("sqpId", surveyQuestionPickVO.getSqpId());
	}

	@ResponseBody
	@PostMapping("/ajax/survey/answer/modify/{sqpId}")
	public AjaxResponse doModifyAnswers(@PathVariable String sqpId, SurveyQuestionPickVO surveyQuestionPickVO,
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		surveyQuestionPickVO.setSqpId(sqpId);
		// 수정 컬럼 삭제함
		// surveyQuestionPickVO.setMdfrId(employeeVO.getEmpId());

		boolean isSuccess = this.surveyQuestionPickService.modifyOneAnswer(surveyQuestionPickVO);

		if (!isSuccess) {
			return new AjaxResponse().append("result", isSuccess);
		}

		return new AjaxResponse().append("result", isSuccess);
	}

	@ResponseBody
	@PostMapping("/ajax/survey/answer/modify/sequence/{sqpId}")
	public AjaxResponse doModifyAnswerSequence(@PathVariable String sqpId, SurveyQuestionPickVO surveyQuestionPickVO) {
		surveyQuestionPickVO.setSqpId(sqpId);

		boolean isSuccess = this.surveyQuestionPickService.modifyOneAnswerSequence(surveyQuestionPickVO);

		return new AjaxResponse().append("result", isSuccess);
	}

	@ResponseBody
	@PostMapping("/ajax/survey/pick/delete/{sqpId}")
	public AjaxResponse doDeleteSurveyPick(@PathVariable String sqpId, SurveyQuestionPickVO surveyQuestionPickVO) {
		surveyQuestionPickVO.setSrvId(sqpId);
		// 수정컬럼 삭제
		// surveyQuestionPickVO.setMdfrId(employeeVO.getEmpId());

		boolean isSuccess = this.surveyQuestionPickService.deleteOneSurveyPick(surveyQuestionPickVO);
		return new AjaxResponse().append("result", isSuccess);
	}

}
