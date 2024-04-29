package com.ktdsuniversity.edu.pms.job.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.job.vo.JobVO;

@Repository
public class JobDaoImpl extends SqlSessionDaoSupport implements JobDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int createJob(JobVO jobVO) {
		
		return getSqlSession().update(JobDao.NAME_SPACE + ".createJob", jobVO);
	}

	@Override
	public int deleteJob(String jobId) {
		
		return getSqlSession().update(JobDao.NAME_SPACE + ".deleteJob", jobId);
	}

	@Override
	public int getNameCount(String jobName) {
		
		return getSqlSession().update(JobDao.NAME_SPACE + ".getNameCount", jobName);
	}

	@Override
	public JobVO getOneJob(String jobId) {

		return getSqlSession().selectOne(JobDao.NAME_SPACE + ".getOneJob", jobId);
	}

	@Override
	public List<JobVO> getAllJob() {
		return getSqlSession().selectList(JobDao.NAME_SPACE + ".getAllJob");
	}



}
