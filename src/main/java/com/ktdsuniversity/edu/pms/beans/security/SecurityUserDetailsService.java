package com.ktdsuniversity.edu.pms.beans.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ktdsuniversity.edu.pms.member.dao.MemberDao;
import com.ktdsuniversity.edu.pms.member.vo.MemberVO;

/**
 * spring security 로그인 처리를 할 때 
 * 사용자의 세부정보를 조회하는 기능
 * 
 * userDetailsService인터페이스 구현이 필요
 */
public class SecurityUserDetailsService implements UserDetailsService{

	private MemberDao memberDao;
	
	public SecurityUserDetailsService (MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String memId) throws UsernameNotFoundException {
		MemberVO memberVO = this.memberDao.getOneMember(memId);
		if(memberVO==null) {
			throw new UsernameNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다");
		}
		return new SecurityUser(memberVO);
	}

}
