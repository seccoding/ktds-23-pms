package com.ktdsuniversity.edu.pms.memo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.pms.memo.service.MemoService;
import com.ktdsuniversity.edu.pms.memo.vo.MemoListVO;

@Controller
public class MemoController {
	
	@Autowired
	private MemoService memoService; 
	
	/**
	 * 보낸 메세지 리스트 목록 보기
	 * @param model
	 * @return
	 */
	@GetMapping("/memo/sentlist")
	public String viewSentMemoListPage(Model model ) {
		
		MemoListVO memoListVO = this.memoService.getSentAllMemo();
		
		model.addAttribute("memoList", memoListVO);
		
		return "memo/sentlist";
	}
	
	@GetMapping("/memo/recievelist")
	public String viewReceivedMemoListPage() {
		return null;
	}
	
//	@GetMapping("/memo/")
//	public String viewArchivedMemoListPage() {
//		return null;
//	}
//	
//	@GetMapping("/memo/sentlist")
//	public String viewMemoWritePage() {
//		return null;
//	}
//	
//	@GetMapping("/memo/sentlist")
//	public String doMemoDelete() {
//		return null;
//	}
//	
//	@PostMapping("/memo/sentlist")
//	public String doMemoWrite() {
//		return null;
//	}
//
//	@PostMapping("/memo/sentlist")
//	public String doMemoSave() {
//		return null;
//	}
}
