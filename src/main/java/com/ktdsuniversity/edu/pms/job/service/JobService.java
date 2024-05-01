package com.ktdsuniversity.edu.pms.job.service;

import com.ktdsuniversity.edu.pms.job.vo.JobListVO;
import com.ktdsuniversity.edu.pms.job.vo.JobVO;

public interface JobService {
	
	public boolean createJob(JobVO jobVO);
	
	public boolean deleteJob(String jobId);

	public JobVO selectJob(String jobId);

	public JobListVO getAllJob();

	
	

}
