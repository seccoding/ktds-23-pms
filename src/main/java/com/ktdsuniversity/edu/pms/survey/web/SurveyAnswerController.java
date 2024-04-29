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

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionService;
import com.ktdsuniversity.edu.pms.survey.service.SurveyReplyService;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyListVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class SurveyAnswerController {

	@Autowired
	private SurveyReplyService surveyReplyService;
	
	@Autowired
	private SurveyQuestionService surveyQuestionService;
	
	@Autowired
	private ProjectService projectService;
	
	@ResponseBody
	@PostMapping("/ajax/survey/response/{srvId}")
	public AjaxResponse ResponseSurvey(@PathVariable String srvId, 
									   @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
									   SurveyReplyVO surveyReplyVO) {
		surveyReplyVO.setSrvId(srvId);
		surveyReplyVO.setCrtrId(employeeVO.getEmpId());
		
		boolean isSuccess = this.surveyReplyService.responseSurvey(surveyReplyVO);
		return new AjaxResponse().append("result", isSuccess).append("srvId", surveyReplyVO.getSrvId()).append("user", employeeVO.getEmpId());
	}
	
	@PostMapping("/survey/response/{prjId}")
	public String CompleteSurvey(@PathVariable String prjId, ProjectTeammateVO projectTeammateVO, 
								 @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		projectTeammateVO.setPrjId(prjId);
		projectTeammateVO.setTmId(employeeVO.getEmpId());
		this.projectService.updateSurveyStatus(projectTeammateVO);
		
		return "redirect:/survey/list";
	}
	
	@GetMapping("/survey/result")
	public String viewSurveyResultPage(@RequestParam String prjId, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO, Model model) {
		SurveyQuestionVO surveyQuestionVO = new SurveyQuestionVO();
	    surveyQuestionVO.setPrjId(prjId);
		
	    SurveyListVO questionList = this.surveyQuestionService.searchAllQuestions(surveyQuestionVO);
	    List<SurveyReplyVO> allReplies = new ArrayList<>();
	    
	    for (SurveyQuestionVO question : questionList.getQuestionList()) {
	    	SurveyReplyVO surveyReplyVO = new SurveyReplyVO();
	    	surveyReplyVO.setSrvId(question.getSrvId());
	        SurveyListVO replyList = this.surveyReplyService.getAllReplies(surveyReplyVO);
	        allReplies.addAll(replyList.getReplyList());
	    }
		
		model.addAttribute("questionList", questionList);
		model.addAttribute("replyList", allReplies);
		
		return "survey/surveyresult";
	}
}
