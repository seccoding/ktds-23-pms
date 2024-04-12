package com.ktdsuniversity.edu.pms.knowledge.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.knowledge.service.KnowledgeService;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeListVO;
import com.ktdsuniversity.edu.pms.knowledge.vo.KnowledgeVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class KnowledgeController {
	
	@Autowired
	private KnowledgeService knowledgeService;
	
	/**
	 * 목록 조회
	 * @return
	 */
	@ResponseBody
	@GetMapping("/ajax/knowledge")
	public AjaxResponse viewKnowledgeListPage() {
		
		KnowledgeListVO knowledgeListVO = this.knowledgeService.getAllKnowledge();
		
		return new AjaxResponse().append("knowledge", knowledgeListVO);
	}
	
	
	/**
	 * 게시글별 상세 조회
	 * @param knowledgeId
	 * @return
	 */
	@ResponseBody
	@GetMapping("/knowledge/detail")
	public AjaxResponse viewDetailKnowledgeListPage(@RequestParam String knowledgeId, Model model) {
		
		KnowledgeVO knowledgeVO = this.knowledgeService.getOneKnowledge(knowledgeId, true);
				
		return new AjaxResponse().append("oneKnowledge", knowledgeVO);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
