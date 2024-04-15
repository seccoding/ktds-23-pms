package com.ktdsuniversity.edu.pms.login.dao;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public interface LoginLogDao {
    public String LOGIN_SPACE = "com.ktdsuniversity.edu.pms.login.dao.LoginLogDao";

    /**
     * 로그인 메서드
     * @param employeeVO
     * @return
     */
    public EmployeeVO getOneEmployeeByEmpIdAndPwd(EmployeeVO employeeVO) ;

	public EmployeeVO getOneEmpIdUseOtherPlace(EmployeeVO employeeVO);

	public void getOneEmpIdNotUseNow(EmployeeVO employeeVO);

	

	

}
