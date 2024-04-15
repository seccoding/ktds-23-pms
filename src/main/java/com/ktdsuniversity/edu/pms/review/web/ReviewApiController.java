package com.ktdsuniversity.edu.pms.review.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewApiController {
	
	@GetMapping("test")
	public Map<String, Object> test(){
		Map<String, Object> map = new HashMap<>();
		map.put("TEST1", "TEST1");
		System.out.println("TEST1111......");
		return map;
		
		
	}

}
