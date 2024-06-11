package com.ktdsuniversity.edu.pms.login.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.vo.CommuteVO;
import com.ktdsuniversity.edu.pms.login.vo.LoginLogVO;
import com.ktdsuniversity.edu.pms.login.vo.VisitedVO;

@Repository
public class LoginLogDaoImpl extends SqlSessionDaoSupport implements LoginLogDao {

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public EmployeeVO getOneEmployeeByEmpIdAndPwd(EmployeeVO employeeVO) {
        return getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".getOneEmployeeByEmpIdAndPwd", employeeVO);
    }

	@Override
	public int updateOneEmpIdUseOtherPlace(EmployeeVO employeeVO) {
		return getSqlSession().update(LoginLogDao.LOGIN_SPACE + ".updateOneEmpIdUseOtherPlace", employeeVO);
	}

	@Override
	public int updateOneEmpIdNotUseNow(EmployeeVO employeeVO) {
		return getSqlSession().update(LoginLogDao.LOGIN_SPACE + ".updateOneEmpIdNotUseNow", employeeVO);
	}


    @Override
    public int insertLoginLog(LoginLogVO loginLogVO) {
        return getSqlSession().insert(LoginLogDao.LOGIN_SPACE + ".insertLoginLog", loginLogVO);
    }

    @Override
    public EmployeeVO updateEmpLog(EmployeeVO employee) {
        return getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".updateEmpLog", employee);
    }

    @Override
    public int updateEmpLogout(String empId) {
        return getSqlSession().update(LoginLogDao.LOGIN_SPACE + ".updateEmpLogout", empId);
    }

    @Override
    public int insertCommuteIn(EmployeeVO employee) {
        return getSqlSession().insert(LoginLogDao.LOGIN_SPACE + ".insertCommuteIn", employee);
    }

    @Override
    public String selectSalt(String empId) {
        return getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".selectSalt", empId);
    }

    @Override
    public CommuteVO getCommuteDt(String empId) {
        return getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".getCommuteDt", empId);
    }

    @Override
    public int updateCommuteFnsh(EmployeeVO employee) {
        return getSqlSession().update(LoginLogDao.LOGIN_SPACE + ".updateCommuteFnsh", employee);
    }

    @Override
    public int getPwdCndt(String empId) {
        return getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".getPwdCndt", empId);
    }

    @Override
	public int updateOneEmpLgnTryPlusOne(String empId) {
		return getSqlSession().update(LoginLogDao.LOGIN_SPACE + ".updateOneEmpLgnTryPlusOne", empId);
	}

	@Override
	public int updateOneEmpLgnTryZero(String empId) {
		return getSqlSession().update(LoginLogDao.LOGIN_SPACE + ".updateOneEmpLgnTryZero", empId);
	}

	@Override
	public int getOneEmpLgnTryCount(String empId) {
		return getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".getOneEmpLgnTryCount", empId);
	}

	@Override
	public int updateOneEmpLgnTryDt(String empId) {
		return getSqlSession().update(LoginLogDao.LOGIN_SPACE + ".updateOneEmpLgnTryDt", empId);
	}

	@Override
	public int getCountPossibleLogin(String empId) {
		return getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".getCountPossibleLogin", empId);
	}

    @Override
    public List<LoginLogVO> getAllLoginLog(LoginLogVO loginLogVO) {
        return getSqlSession().selectList(LoginLogDao.LOGIN_SPACE + ".getAllLoginLog", loginLogVO);
    }

    @Override
    public List<LoginLogVO> getOneLoginLog(LoginLogVO loginLogVO) {
        return getSqlSession().selectList(LoginLogDao.LOGIN_SPACE + ".getOneLoginLog", loginLogVO);
    }

    @Override
    public List<VisitedVO> getAllVisitedLog(VisitedVO visitedVO) {
        return getSqlSession().selectList(LoginLogDao.LOGIN_SPACE + ".getAllVisitedLog", visitedVO);
    }

    @Override
    public List<VisitedVO> getOneVisitedLog(VisitedVO visitedVO) {
        return getSqlSession().selectList(LoginLogDao.LOGIN_SPACE + ".getOneVisitedLog", visitedVO);
    }

    @Override
    public int updatePwdDtThirtyDay(String empId) {
        return getSqlSession().update(LoginLogDao.LOGIN_SPACE + ".updatePwdDtThirtyDay", empId);
    }

    @Override
    public int getCommutFnshCount(String empId) {
        return getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".getCommutFnshCount", empId);
    }

	

}
