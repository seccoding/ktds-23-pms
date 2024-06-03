package com.ktdsuniversity.edu.pms.beans.security;

import java.time.LocalDate;
import java.util.Collection;

import org.apache.ibatis.annotations.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.dao.LoginLogDao;

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



	
//	인가에 대한 설정
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

//	계정만료 여부
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
//	로그인 사용자 계정의 잠금처리 여부
	@Override
	public boolean isAccountNonLocked() {
		if(!this.employeeVO.getWorkSts().equals("201")) {
			return false;
		}
		return true;
	}
	
	public String isAccountNonLockedCode(){
		if(!this.isAccountNonLocked()) {// 휴직 or 퇴직 사원인경우
			return this.employeeVO.getWorkSts();
		}
		return null;
	}
	
//	비밀번호 유효기간 만료여부
	@Override
	public boolean isCredentialsNonExpired() {
		LocalDate pwdCnDt = LocalDate.parse(this.employeeVO.getPwdCnDt());
		pwdCnDt =pwdCnDt.plusDays(90);
		LocalDate date = LocalDate.now();
		
		return date.isBefore(pwdCnDt) ;
	}
	
//	  해당계정 사용여부
	@Override
	public boolean isEnabled() {
		return true;
	}

}
