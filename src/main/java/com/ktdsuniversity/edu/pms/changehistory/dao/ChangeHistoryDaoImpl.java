package com.ktdsuniversity.edu.pms.changehistory.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.changehistory.vo.DepartmentHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.JobHistoryVO;
import com.ktdsuniversity.edu.pms.changehistory.vo.PositionHistoryVO;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.job.vo.JobVO;

@Repository
public class ChangeHistoryDaoImpl extends SqlSessionDaoSupport implements ChangeHistoryDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertOneChangeDeptHistory(EmployeeVO employeeVO) {
		return getSqlSession().insert(ChangeHistoryDao.NAME_SPACE+".insertOneChangeDeptHistory", employeeVO);
	}

	@Override
	public String getRecentDeptHist(String empId) {
		return getSqlSession().selectOne(ChangeHistoryDao.NAME_SPACE+".getRecentDeptHist", empId);
	}

	@Override
	public List<DepartmentHistoryVO> getAllDeptHist(String empId) {
		return getSqlSession().selectList(ChangeHistoryDao.NAME_SPACE+".getAllDeptHist", empId);
	}

	@Override
	public List<JobHistoryVO> getAllJobHist(String empId) {
		return getSqlSession().selectList(ChangeHistoryDao.NAME_SPACE+".getAllJobHist", empId);
	}

	@Override
	public List<PositionHistoryVO> getUserPositionHistory(String empId) {
		return getSqlSession().selectList(ChangeHistoryDao.NAME_SPACE+".getUserPositionHistory", empId);
	}

	@Override
	public int insertOneChangeJobHistory(EmployeeVO employeeVO) {
		return getSqlSession().insert(ChangeHistoryDao.NAME_SPACE + ".insertOneChangeJobHistory", employeeVO);
	}

	@Override
	public String getRecentJobHist(String empId) {
		return getSqlSession().selectOne(ChangeHistoryDao.NAME_SPACE + ".getRecentJobHist", empId);
	}

	@Override
	public int insertOneChangePositionHistory(EmployeeVO employeeVO) {
		return getSqlSession().insert(ChangeHistoryDao.NAME_SPACE + ".insertOneChangePositionHistory", employeeVO);
	}

	@Override
	public String getRecentPositionHist(String empId) {
		return getSqlSession().selectOne(ChangeHistoryDao.NAME_SPACE + ".getRecentPositionHist", empId);
	}

	@Override
	public List<CommonCodeVO> getAllPosition() {
		return getSqlSession().selectList(ChangeHistoryDao.NAME_SPACE + ".getAllPosition");
	}

	@Override
	public List<JobHistoryVO> getUserJobHistory(String empId) {
		return getSqlSession().selectList(ChangeHistoryDao.NAME_SPACE +".getAllJobHist", empId);
	}

	@Override
	public int insertPositionHist(EmployeeVO employeeVO) {
		return getSqlSession().insert(ChangeHistoryDao.NAME_SPACE + ".insertPositionHist", employeeVO);
	}

	@Override
	public List<JobVO> getAllJob() {
		return getSqlSession().selectList(ChangeHistoryDao.NAME_SPACE + ".getAllJob");
	}


}
