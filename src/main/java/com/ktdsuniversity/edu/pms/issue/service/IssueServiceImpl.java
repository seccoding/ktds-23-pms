package com.ktdsuniversity.edu.pms.issue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.pms.issue.dao.IssueDao;

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueDao issueDao;
}
