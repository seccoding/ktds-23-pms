package com.ktdsuniversity.edu.pms.survey.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.survey.service.SurveyQuestionPickService;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyQuestionPickVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class SurveyAnswerController {
	
	@Autowired
	private SurveyQuestionPickService surveyQuestionPickService;
	
	@ResponseBody
	@PostMapping("/ajax/survey/answer/{srvId}")
	public AjaxResponse doCreateNewAnswer(@PathVariable String srvId, 
										  SurveyQuestionPickVO surveyQuestionPickVO) {
		surveyQuestionPickVO.setSrvId(srvId);
		
		boolean isSuccess = this.surveyQuestionPickService.createNewAnswer(surveyQuestionPickVO);	
		return new AjaxResponse().append("result", isSuccess).append("sqpId", surveyQuestionPickVO.getSqpId());
	}
	
	@ResponseBody
	@PostMapping("/ajax/survey/answer/modify/{sqpId}")
	public AjaxResponse doModifyAnswers(@PathVariable String sqpId, SurveyQuestionPickVO surveyQuestionPickVO) {
		surveyQuestionPickVO.setSqpId(sqpId);
		
		boolean isSuccess = this.surveyQuestionPickService.modifyOneAnswer(surveyQuestionPickVO);
		
//		if (!isSuccess) {
//			return new AjaxResponse().append("result", isSuccess);		
//		}
		
		return new AjaxResponse().append("result", isSuccess);
	}

}
