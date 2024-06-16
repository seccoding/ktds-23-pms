package com.ktdsuniversity.edu.pms.project.service;

import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectStatusVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.SearchProjectVO;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    ProjectListVO getAllProject();

    ProjectListVO searchProject(SearchProjectVO searchProjectVO);

    ProjectVO getOneProject(String projectId);

    boolean createNewProject(CreateProjectVO projectVO);

    boolean deleteOneProject(String projectId);

    int getProjectTeammateCount(String projectId);

    List<ProjectTeammateVO> getAllProjectTeammateByProjectId(String projectId);

    Map<String, List<ProjectStatusVO>> getProjectStatus(String projectId);

    boolean updateOneProject(CreateProjectVO modifyProjectVO);

    boolean deleteManyTeammate(List<String> deleteItems);

    boolean deleteOneTeammate(String prjTmId);

    boolean insertOneTeammate(ProjectTeammateVO newProjectTeammate);

    List<ProjectTeammateVO> getAllProjectTeammate();

    List<ProjectVO> getAllProjectByProjectTeammateId(String tmId);

    boolean updateReviewStatus(ProjectTeammateVO projectTeammateVO);

    boolean updateSurveyStatus(ProjectTeammateVO projectTeammateVO);

	List<ProjectTeammateVO> getAllProjectTeammateByTmId(String empId);

	String getPrjTmId(ProjectTeammateVO projectTeammateVO);

	List<ProjectVO> getAllPrjEvent(String empId);
}
