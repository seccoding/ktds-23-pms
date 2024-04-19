package com.ktdsuniversity.edu.pms.job.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.job.service.JobService;
import com.ktdsuniversity.edu.pms.job.vo.JobVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;
import com.ktdsuniversity.edu.pms.utils.StringUtil;



@Controller
public class JobController {
	

	
	@Autowired
	private JobService jobService;
	
	@ResponseBody
	@PostMapping("/ajax/job/create")
	public AjaxResponse createJob(JobVO jobVO, Model model) {
		
		boolean isEmptyJobName = StringUtil.isEmpty(jobVO.getJobName());
		
		if(isEmptyJobName) {
			model.addAttribute("errorMessage", "직무 이름은 필수 입력값입니다.");
			model.addAttribute("jobVO", jobVO);
			return new AjaxResponse().append("errorMessage", model);
		}
		boolean isSuccess = this.jobService.createJob(jobVO);
		return new AjaxResponse().append("result", isSuccess).append("nextUrl", "/employee/search");
	}
	
}
