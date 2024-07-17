package com.ktdsuniversity.edu.pms.beans.security;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.pms.member.vo.MemberVO;

public class SecurityUser implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberVO memberVO;
	
	public SecurityUser (MemberVO memberVO) {
		this.memberVO= memberVO;
	}
	
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}



	
//	인가에 대한 설정
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}


	@Override
	public String getPassword() {
		return this.memberVO.getPwd();
	}

	@Override
	public String getUsername() {
		return this.memberVO.getName();
	}

//	계정만료 여부
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
//	로그인 사용자 계정의 잠금처리 여부
	@Override
	public boolean isAccountNonLocked() {
		if(!this.memberVO.getIdLockYn().equals("N")) {
			return false;
		}
		return true;
	}
	
	public String isAccountNonLockedCode(){
		if(!this.isAccountNonLocked()) {// 휴직 or 퇴직 사원인경우
			return this.memberVO.getIdLockYn();
		}
		return null;
	}
	

	
//	  해당계정 사용여부
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

}
