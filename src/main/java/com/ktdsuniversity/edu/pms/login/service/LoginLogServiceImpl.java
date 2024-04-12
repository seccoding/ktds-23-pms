package com.ktdsuniversity.edu.pms.login.service;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.dao.LoginLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServiceImpl implements LoginLogService{

    /**
     * 맴버변수 loginLogDao 에 Bean Container 에 적재된 Dao 의존성 주입(할당)
     */
    @Autowired
    private LoginLogDao loginLogDao;


    @Override
    public EmployeeVO getOneEmployeeByEmpIdAndPwd(EmployeeVO employeeVO) {

        employeeVO.setPwd(employeeVO.getPwd());
        EmployeeVO employee = loginLogDao.getOneEmployeeByEmpIdAndPwd(employeeVO);

        if (employee == null) {
            // exception 패키지에서 exception 처리 해야 한다.
            throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다");
        }

        return employee;
    }
}
