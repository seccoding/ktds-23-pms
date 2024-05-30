package com.ktdsuniversity.edu.pms.beans.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.pms.beans.SHA;

/**
 * sha를 이용하여 password를 인코딩함
 */
public class SecuritySHA extends SHA implements PasswordEncoder {
	
	private String salt ;
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * 로그인 비밀번호를 암호화하는 기능
	 * @param rawPassword 암호화되지 않은 평문
	 * @return 암호화된 비밀번호
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		return super.getEncrypt(rawPassword.toString(), this.salt);
	}
	/**
	 * 로그인 비밀번화가 일치하는지 확인
	 * @param rawPassword 평문 비밀번호
	 * @param encodedPassword 암호화된 비밀번호
	 * @return 파라미터 두개의 값이 일치하는지 여부 
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String password = super.getEncrypt(rawPassword.toString(),this.salt);
		return password.equals(encodedPassword);
	}

}
