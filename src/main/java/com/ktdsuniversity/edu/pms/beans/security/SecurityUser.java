package com.ktdsuniversity.edu.pms.beans.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class SecurityUser implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EmployeeVO employeeVO;
	
	public SecurityUser (EmployeeVO employeeVO) {
		this.employeeVO= employeeVO;
	}
	
	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}
	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}



	/**
	 * 인가에 대한 설정
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}


	@Override
	public String getPassword() {
		return this.employeeVO.getPwd();
	}

	@Override
	public String getUsername() {
		return this.employeeVO.getEmpName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
