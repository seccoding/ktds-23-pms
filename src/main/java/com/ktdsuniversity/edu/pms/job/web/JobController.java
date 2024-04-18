package com.ktdsuniversity.edu.pms.job.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.pms.job.service.JobService;
import com.ktdsuniversity.edu.pms.job.vo.JobVO;



//@Controller
//public class JobController {
//	
//	@Autowired
//	private JobService jobService;
//	
//	@GetMapping("/job/create")
//	public String viewCreatePage() {
//		return "job/jobcreate";
//	}
//	
//	@PostMapping("/job/create")
//	public String createJob(JobVO jobVO, Model model) {
//		
//		boolean isNotEmptyJobId = 
//		
//	}
//	
//	@ResponseBody
//	@GetMapping("/ajax/job/delete")
//}
