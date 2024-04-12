package com.ktdsuniversity.edu.pms.project.dao;

import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;

import java.util.List;

public interface ProjectDao {

	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.project.dao.ProjectDao";

	List<ProjectVO> selectAllProject();

	int selectAllProjectCount();

	ProjectVO findById(String projectId);

	int insertNewProject(CreateProjectVO projectVO);

	int insertNewPm(ProjectTeammateVO pm);

	int deleteById(String projectId);

}
