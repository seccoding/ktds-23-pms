package com.ktdsuniversity.edu.pms.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String viewMainLayoutPage() {
		return "layout/main_layout";
	}

}
