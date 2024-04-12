package com.ktdsuniversity.edu.pms.memo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ktdsuniversity.edu.pms.memo.service.MemoService;

@Controller
public class MemoController {
	
	@Autowired
	private MemoService memoService; 
}
