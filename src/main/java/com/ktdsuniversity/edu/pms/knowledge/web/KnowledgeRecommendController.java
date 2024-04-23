package com.ktdsuniversity.edu.pms.knowledge.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeRecommendService;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeRecommendVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class KnowledgeRecommendController {
	
	@Autowired
	private KnowledgeRecommendService knowledgeRecommendService;

	// 1사원 1추천
	@ResponseBody
	@PostMapping("/knowledge/recommend/{pPostId}")
	public AjaxResponse getRecommendKnowledge(@PathVariable String pPostId, @SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO) {
		
		KnowledgeRecommendVO knowledgeRecommendVO = new KnowledgeRecommendVO();
		
		knowledgeRecommendVO.setpPostId(pPostId);
		knowledgeRecommendVO.setCrtrId(employeeVO.getEmpId());
		
		boolean isRecommend = knowledgeRecommendService.updateRecommend(knowledgeRecommendVO);
		
		 // 해당 사원이 이미 추천을 했는지 확인
        if (isRecommend) {
            return new AjaxResponse().append("result", "추천이 완료되었습니다.").append("resultStatus", true);
        } else {
        	return new AjaxResponse().append("result", "이미 추천하셨습니다.").append("resultStatus", false);
        }
        
	}
	

}
