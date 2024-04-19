package com.ktdsuniversity.edu.pms.qna.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ktdsuniversity.edu.pms.beans.FileHandler;
import com.ktdsuniversity.edu.pms.knowledge.web.KnowledgeController;
import com.ktdsuniversity.edu.pms.project.service.ProjectService;
import com.ktdsuniversity.edu.pms.qna.service.QnaService;
import com.ktdsuniversity.edu.pms.requirement.service.RequirementService;

@Controller
public class QnaController {
	
	Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	@Autowired
	private FileHandler fileHandler;
	
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private RequirementService requirementService;

	@Autowired
	private ProjectService projectService;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
