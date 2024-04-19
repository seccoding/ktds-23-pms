package com.ktdsuniversity.edu.pms.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.job.dao.JobDao;
import com.ktdsuniversity.edu.pms.job.vo.JobVO;

@Service
public class JobServiceImpl implements JobService{

	@Autowired
	private JobDao jobDao;
	
	@Override
	public boolean createJob(JobVO jobVO) {
	
	int createSuccessCount = jobDao.createJob(jobVO);
	return createSuccessCount > 0;
	}

	@Override
	public boolean deleteJobByName(String jobName) {
		
		return this.jobDao.deleteJob(jobName) > 0 ;
	}


}
