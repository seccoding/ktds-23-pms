package com.ktdsuniversity.edu.pms.memo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.memo.service.MemoService;
import com.ktdsuniversity.edu.pms.memo.vo.MemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemoController {
	
	private Logger logger = LoggerFactory.getLogger(MemoController.class);
	
	@Autowired
	private MemoService memoService; 
	
	/**
	 * 보낸 쪽지리스트 보여주는 페이지 
	 */
	@GetMapping("/memo/sent")
	public String viewSentMemoListPage(Model model) {
		
		MemoListVO memoListVO =this.memoService.getSentMemoAllsearch();
		
		model.addAttribute("memoList", memoListVO );
		
		return "memo/memosent";
	}
	
	// 수정필요
	@GetMapping("/memo/storage")
	public String viewStorageMemoListPage(Model model) {
		
		MemoListVO memoListVO =this.memoService.getStorageMemoAllsearch();
		
		model.addAttribute("memoList", memoListVO );
		
		return "memo/memostorage";
	}
	
	// 수정필요
	@GetMapping("/memo/receive")
	public String viewReceiveMemoListPage(Model model) {
		
		MemoListVO memoListVO =this.memoService.getReceiveMemoAllsearch();
		
		model.addAttribute("memoList", memoListVO );
		
		return "memo/memoreceive";
	}
	
	/**
	 * 쪽지 쓰는화면 보여주기
	 */
	@GetMapping("/memo/write")
	public String viewMemoWritePage(HttpSession session) {
		return "memo/memowrite";
	}
	
	/**
	 * 쪽찌 쓰기 기능 쓰기 완료시 보낸쪽지함으로 이동
	 */
	@PostMapping("/memo/write")
	public String doBoardWrite(MemoVO memoVO,Model model) {
		
		boolean isCreateSuccess = this.memoService.writeNewMemo(memoVO);
		if(isCreateSuccess) {
			logger.info("쪽지 쓰기 성공!");
		}
		else {
			logger.info("쪽지 쓰기 실패!");			
		}

		return "redirect:/memo/memosent";
	}
	
	
	@GetMapping("/memo/view")
	public String viewBoardDetailPage(@RequestParam() String id, Model model) {
		MemoVO memoVO = this.memoService.getOneMemo(id);
	
		model.addAttribute("memoVO", memoVO);

		return "memo/memoview";
	}
	
	
}
