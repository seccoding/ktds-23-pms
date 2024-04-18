package com.ktdsuniversity.edu.pms.job.service;

import com.ktdsuniversity.edu.pms.job.vo.JobVO;

public interface JobService {
	
	public boolean createJob(JobVO jobVO);
	
	public boolean deleteJobByName(String jobName);
	
	public boolean checkAvailableName (String jobName);
	
	

}
