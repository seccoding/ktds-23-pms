package com.ktdsuniversity.edu.pms.project.dao;

import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectStatusVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.SearchProjectVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

import java.util.List;

public interface ProjectDao {

    public String NAME_SPACE = "com.ktdsuniversity.edu.pms.project.dao.ProjectDao";

    List<ProjectVO> selectAllProject();

    int selectAllProjectCount();

    List<ProjectVO> searchBoard(SearchProjectVO searchProjectVO);

    int searchProjectCount(SearchProjectVO searchProjectVO);

    ProjectVO findById(String projectId);

    int insertNewProject(CreateProjectVO projectVO);

    int insertNewPm(ProjectTeammateVO pm);

    int deleteById(String projectId);

    int selectProjectTeammateCount(String projectId);

    List<ProjectTeammateVO> findAllProjectTeammateByProjectId(String projectId);

    List<ProjectStatusVO> getProjectRequirementStatusList(String projectId);

    List<ProjectStatusVO> getProjectIssueStatusList(String projectId);

    int updateOneProject(CreateProjectVO modifyProjectVO);

    int deletePm(String prePmEmployeeId);

    ProjectTeammateVO findPmByProjectId(String prjId);

    CreateProjectVO selectDeletedPm(CreateProjectVO modifyProjectVO);

    int restoreDeletedPm(CreateProjectVO modifyProjectVO);

    int deleteManyTeammate(List<String> deleteItems);

    int deleteByTeammateId(String prjTmId);

    ProjectTeammateVO findTeammateByProjectIdAndEmployeeId(ProjectTeammateVO newProjectTeammate);

    int updateTeammateDeleteYnAndRoleByProjectTeammateId(ProjectTeammateVO originTeammate);

    int insertNewProjectTeammate(ProjectTeammateVO newProjectTeammate);

    List<ProjectTeammateVO> findAllProjectTeammate();

    List<ProjectVO> findAllProjectByEmployeeId(String empId);

    int updateOneTeammateReviewStatusByProjectIdAndEmployeeId(ProjectTeammateVO projectTeammateVO);

    int updateOneTeammateSurveyStatusByProjectIdAndEmployeeId(ProjectTeammateVO projectTeammateVO);

    int selectTeammateRolePLCountByProjectId(String prjId);

	int updateOneProjectSurveySts(String prjId);

	int updateOneTeammateSurveySts(SurveyReplyVO surveyReplyVO);

	List<ProjectTeammateVO> getAllProjectTeammateByTmId(String empId);

	String findPmNameByPrjId(String prjId);

	String getPrjTmId(ProjectTeammateVO projectTeammateVO);

	List<ProjectVO> getAllPrjEvent(String empId);
}
