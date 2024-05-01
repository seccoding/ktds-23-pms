package com.ktdsuniversity.edu.pms.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.job.dao.JobDao;
import com.ktdsuniversity.edu.pms.job.vo.JobListVO;
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
	public boolean deleteJob(String jobName) {
		
		return this.jobDao.deleteJob(jobName) > 0 ;
	}

	@Override
	public JobVO selectJob(String jobId) {
		return this.jobDao.getOneJob(jobId);
	}

	@Override
	public JobListVO getAllJob() {
		List<JobVO> jobList = this.jobDao.getAllJob();
		JobListVO jobListVO = new JobListVO();
		jobListVO.setJobList(jobList);
		return jobListVO;
	}


}
