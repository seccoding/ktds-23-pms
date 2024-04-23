package com.ktdsuniversity.edu.pms.job.dao;

import com.ktdsuniversity.edu.pms.job.vo.JobVO;

public interface JobDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.job.dao.JobDao" ;
	
	public int createJob(JobVO jobVO);
	
	public int deleteJob(String jobId);
	
	public int getNameCount(String jobName);

	public JobVO getOneJob(String jobId);
	

}
