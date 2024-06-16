package com.ktdsuniversity.edu.pms.project.dao;

import com.ktdsuniversity.edu.pms.project.vo.CreateProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectStatusVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectTeammateVO;
import com.ktdsuniversity.edu.pms.project.vo.ProjectVO;
import com.ktdsuniversity.edu.pms.project.vo.SearchProjectVO;
import com.ktdsuniversity.edu.pms.survey.vo.SurveyReplyVO;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl extends SqlSessionDaoSupport implements ProjectDao {

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public List<ProjectVO> selectAllProject() {
        return getSqlSession().selectList(ProjectDao.NAME_SPACE + ".selectAllProject");
    }

    @Override
    public int selectAllProjectCount() {
        return getSqlSession().selectOne(ProjectDao.NAME_SPACE + ".selectAllProjectCount");
    }

    @Override
    public List<ProjectVO> searchBoard(SearchProjectVO searchProjectVO) {
        return getSqlSession().selectList(ProjectDao.NAME_SPACE + ".searchBoard", searchProjectVO);
    }

    @Override
    public int searchProjectCount(SearchProjectVO searchProjectVO) {
        return getSqlSession().selectOne(ProjectDao.NAME_SPACE + ".searchProjectCount", searchProjectVO);
    }

    @Override
    public ProjectVO findById(String projectId) {
        return getSqlSession().selectOne(ProjectDao.NAME_SPACE + ".findById", projectId);
    }

    @Override
    public int insertNewProject(CreateProjectVO projectVO) {
        return getSqlSession().insert(ProjectDao.NAME_SPACE + ".insertNewProject", projectVO);
    }

    @Override
    public int insertNewPm(ProjectTeammateVO pm) {
        return getSqlSession().insert(ProjectDao.NAME_SPACE + ".insertNewPm", pm);
    }

    @Override
    public int deleteById(String projectId) {
        return getSqlSession().update(ProjectDao.NAME_SPACE + ".deleteById", projectId);
    }

    @Override
    public int selectProjectTeammateCount(String projectId) {
        return getSqlSession().selectOne(ProjectDao.NAME_SPACE + ".selectProjectTeammateCount", projectId);
    }

    @Override
    public List<ProjectTeammateVO> findAllProjectTeammateByProjectId(String projectId) {
        return getSqlSession().selectList(ProjectDao.NAME_SPACE + ".findAllProjectTeammateByProjectId", projectId);
    }

    @Override
    public List<ProjectStatusVO> getProjectRequirementStatusList(String projectId) {
        return getSqlSession().selectList(ProjectDao.NAME_SPACE + ".getProjectRequirementStatusList", projectId);
    }

    @Override
    public List<ProjectStatusVO> getProjectIssueStatusList(String projectId) {
        return getSqlSession().selectList(ProjectDao.NAME_SPACE + ".getProjectIssueStatusList", projectId);
    }

    @Override
    public int updateOneProject(CreateProjectVO modifyProjectVO) {
        return getSqlSession().update(ProjectDao.NAME_SPACE + ".updateOneProject", modifyProjectVO);
    }

    @Override
    public int deletePm(String prePmEmployeeId) {
        return getSqlSession().update(ProjectDao.NAME_SPACE + ".deletePm", prePmEmployeeId);
    }

    @Override
    public ProjectTeammateVO findPmByProjectId(String prjId) {
        return getSqlSession().selectOne(ProjectDao.NAME_SPACE + ".findPmByProjectId", prjId);
    }

    @Override
    public CreateProjectVO selectDeletedPm(CreateProjectVO modifyProjectVO) {
        return getSqlSession().selectOne(ProjectDao.NAME_SPACE + ".selectDeletedPm", modifyProjectVO);
    }

    @Override
    public int restoreDeletedPm(CreateProjectVO modifyProjectVO) {
        return getSqlSession().update(ProjectDao.NAME_SPACE + ".restoreDeletedPm", modifyProjectVO);
    }

    @Override
    public int deleteManyTeammate(List<String> deleteItems) {
        return getSqlSession().update(ProjectDao.NAME_SPACE + ".deleteManyTeammate", deleteItems);
    }

    @Override
    public int deleteByTeammateId(String prjTmId) {
        return getSqlSession().update(ProjectDao.NAME_SPACE + ".deleteByTeammateId", prjTmId);
    }

    @Override
    public ProjectTeammateVO findTeammateByProjectIdAndEmployeeId(ProjectTeammateVO newProjectTeammate) {
        return getSqlSession().selectOne(ProjectDao.NAME_SPACE + ".findTeammateByProjectIdAndEmployeeId", newProjectTeammate);
    }

    @Override
    public int updateTeammateDeleteYnAndRoleByProjectTeammateId(ProjectTeammateVO originTeammate) {
        return getSqlSession().update(ProjectDao.NAME_SPACE + ".updateTeammateDeleteYnAndRoleByProjectTeammateId", originTeammate);
    }

    @Override
    public int insertNewProjectTeammate(ProjectTeammateVO newProjectTeammate) {
        return getSqlSession().insert(ProjectDao.NAME_SPACE + ".insertNewProjectTeammate", newProjectTeammate);
    }

    @Override
    public List<ProjectTeammateVO> findAllProjectTeammate() {
        return getSqlSession().selectList(ProjectDao.NAME_SPACE + ".findAllProjectTeammate");
    }

    @Override
    public List<ProjectVO> findAllProjectByEmployeeId(String empId) {
        return getSqlSession().selectList(ProjectDao.NAME_SPACE + ".findAllProjectByEmployeeId", empId);
    }

    @Override
    public int updateOneTeammateReviewStatusByProjectIdAndEmployeeId(ProjectTeammateVO projectTeammateVO) {
        return getSqlSession().update(ProjectDao.NAME_SPACE + ".updateOneTeammateReviewStatusByProjectIdAndEmployeeId", projectTeammateVO);
    }

    @Override
    public int updateOneTeammateSurveyStatusByProjectIdAndEmployeeId(ProjectTeammateVO projectTeammateVO) {
        return getSqlSession().update(ProjectDao.NAME_SPACE + ".updateOneTeammateSurveyStatusByProjectIdAndEmployeeId", projectTeammateVO);
    }

    @Override
    public int selectTeammateRolePLCountByProjectId(String prjId) {
        return getSqlSession().selectOne(ProjectDao.NAME_SPACE + ".selectTeammateRolePLCountByProjectId", prjId);
    }

    @Override
	public int updateOneProjectSurveySts(String prjId) {
		return getSqlSession().update(ProjectDao.NAME_SPACE + ".updateOneProjectSurveySts", prjId);
	}

	@Override
	public int updateOneTeammateSurveySts(SurveyReplyVO surveyReplyVO) {
		return getSqlSession().update(ProjectDao.NAME_SPACE + ".updateOneTeammateSurveySts", surveyReplyVO);
	}

	@Override
	public List<ProjectTeammateVO> getAllProjectTeammateByTmId(String empId) {
		return getSqlSession().selectList(ProjectDao.NAME_SPACE + ".getAllProjectTeammateByTmId", empId);
	}

	@Override
	public String findPmNameByPrjId(String prjId) {
		return getSqlSession().selectOne(ProjectDao.NAME_SPACE+".findPmNameByPrjId", prjId);
	}

	@Override
	public String getPrjTmId(ProjectTeammateVO projectTeammateVO) {
		return getSqlSession().selectOne(ProjectDao.NAME_SPACE+".getPrjTmId", projectTeammateVO);
	}

	@Override
	public List<ProjectVO> getAllPrjEvent(String empId) {
		return getSqlSession().selectList(ProjectDao.NAME_SPACE+".getAllPrjEvent", empId);
	}
}
