package com.ktdsuniversity.edu.pms.beans.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

/**
 * spring security 로그인 처리를 할 때 
 * 사용자의 세부정보를 조회하는 기능
 * 
 * userDetailsService인터페이스 구현이 필요
 */
public class SecurityUserDetailsService implements UserDetailsService{

	private EmployeeDao employeeDao;
	
	public SecurityUserDetailsService (EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException {
		EmployeeVO employeeVO = this.employeeDao.getOneEmployee(empId);
		if(employeeVO==null) {
			throw new UsernameNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다");
		}
		return new SecurityUser(employeeVO);
	}

}
