package com.ktdsuniversity.edu.pms.login.dao;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
	public EmployeeVO getOneEmpIdUseOtherPlace(EmployeeVO employeeVO) {
		return getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".getOneEmpIdUseOtherPlace", employeeVO);
	}

	@Override
	public void getOneEmpIdNotUseNow(EmployeeVO employeeVO) {
		getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".getOneEmpIdNotUseNow", employeeVO);
	}

    @Override
    public void updateLoginLog(EmployeeVO employee) {
        getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".updateLoginLog", employee);
    }

    @Override
    public EmployeeVO updateEmpLog(EmployeeVO employee) {
        return getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".updateEmpLog", employee);
    }

    @Override
    public void updateEmpLogout(EmployeeVO employee) {
        getSqlSession().selectOne(LoginLogDao.LOGIN_SPACE + ".updateEmpLogout", employee);
    }


}
