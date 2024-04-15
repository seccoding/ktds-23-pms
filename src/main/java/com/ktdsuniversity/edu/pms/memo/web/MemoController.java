package com.ktdsuniversity.edu.pms.memo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.edu.pms.memo.service.MemoService;
import com.ktdsuniversity.edu.pms.memo.vo.MemoListVO;

@Controller
public class MemoController {
	
	@Autowired
	private MemoService memoService; 
	
	@GetMapping("/memo/sentlist")
	public String viewSentMemoListPage(Model model) {
		
		MemoListVO memoListVO =this.memoService.getSentMemoAllsearch();
		
		model.addAttribute("memoList", memoListVO );
		
		return "/memo/sentlist";
	}
}
