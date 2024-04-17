package com.ktdsuniversity.edu.pms.review.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.login.web.LoginController;
import com.ktdsuniversity.edu.pms.review.service.ReviewService;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@RestController
public class ReviewApiController {

	private Logger logger = LoggerFactory.getLogger(ReviewApiController.class);

	@Autowired
	private ReviewService reviewService;
	
	
	@GetMapping("test")
	public Map<String, Object> test(){
		Map<String, Object> map = new HashMap<>();
		map.put("TEST1", "TEST1");
		System.out.println("TEST1111......");
		return map;
	}
	
	
	@GetMapping("/ajax/review/viewresult/{id}/delete")
	public AjaxResponse reviewViewResultDelete(@PathVariable String id) {
		logger.debug("ID={}", id);
		return new AjaxResponse().append("result", reviewService.reviewViewResultDelete(id));
	}

}
