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

import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionService;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyReplyVO;
import com.ktdsuniversity.edu.pms.survey.vo.SearchSurveyVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class SurveyController {

	@Autowired
	private SurveyQuestionService surveyQuestionService;

	@GetMapping("/survey/list")
	public String viewSurveyListPage(Model model) {
		SurveyListVO surveyListVO = this.surveyQuestionService.getAllSurvey();
//		SurveyQuestionVO surveyQuestionVO = this
		model.addAttribute("surveyList", surveyListVO);
		return "survey/surveylist";
	}

	@GetMapping("/survey/view")
	public String viewSurveyDetailPage(@RequestParam String prjId, Model model) {
		SurveyQuestionVO surveyQuestionVO = this.surveyQuestionService.getOneSurvey(prjId);
		model.addAttribute("surveyQuestionVO", surveyQuestionVO);
		return "survey/surveyview";
	}

	@GetMapping("/survey/write")
	public String viewSurveyWritePage() {
		return "survey/surveywrite";
	}

	@GetMapping("/survey/create")
	public String viewSurveyCreatePage(@RequestParam String prjId, Model model) {
		SurveyQuestionVO surveyQuestionVO = this.surveyQuestionService.getOneSurvey(prjId);
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
	public AjaxResponse getAllSurveysForWrite(@PathVariable String prjId, SurveyQuestionVO surveyQuestionVO) {
		surveyQuestionVO.setPrjId(prjId);
		List<SurveyQuestionVO> surveyList = this.surveyQuestionService.getAllSurveysForWrite(surveyQuestionVO);
		return new AjaxResponse().append("surveys", surveyList);
	}

	@ResponseBody
	@PostMapping("/ajax/survey/write")
	public AjaxResponse doSurveyWrite(SurveyReplyVO surveyReplyVO) {

		boolean isSuccess = this.surveyQuestionService.writeNewSurvey(surveyReplyVO);
		return new AjaxResponse().append("result", isSuccess);
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
