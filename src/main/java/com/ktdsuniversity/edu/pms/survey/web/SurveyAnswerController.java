package com.ktdsuniversity.edu.pms.survey.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		
	    int projectTeammateCount = this.projectService.getProjectTeammateCount(prjId);
	    long completedSurveyTeammateList = this.projectService.getAllProjectTeammateByProjectId(prjId).stream()
	    .filter(tm->"Y".equals(tm.getSrvYn()))
	    .count();
	    
	    SurveyListVO questionList = this.surveyQuestionService.searchAllQuestions(surveyQuestionVO);
	    List<SurveyReplyVO> allReplies = new ArrayList<>();
	    Map<String, Map<String, Integer>> responseCounts = new HashMap<>();
	    
	    for (SurveyQuestionVO question : questionList.getQuestionList()) {
	    	SurveyReplyVO surveyReplyVO = new SurveyReplyVO();
	    	surveyReplyVO.setSrvId(question.getSrvId());
	        SurveyListVO replyList = this.surveyReplyService.getAllReplies(surveyReplyVO);
	        allReplies.addAll(replyList.getReplyList());
	        
	        Map<String, Integer> replyCount = replyList.getReplyList().stream()
	        .collect(Collectors.groupingBy(
	        reply -> reply.getSrvRplCntnt().length() > 1000 ? reply.getSrvRplCntnt().substring(0, 1000) : reply.getSrvRplCntnt(),
	        Collectors.reducing(0, e -> 1, Integer::sum)
	        ));
	            
	        responseCounts.put(question.getSrvId(), replyCount);
	    }
	    
	    
		
	    model.addAttribute("srvYn", completedSurveyTeammateList);
	    model.addAttribute("teammateCount", projectTeammateCount);
		model.addAttribute("questionList", questionList);
		model.addAttribute("replyList", allReplies);
		model.addAttribute("responseCounts", responseCounts);
		
		return "survey/surveyresult";
	}
}
