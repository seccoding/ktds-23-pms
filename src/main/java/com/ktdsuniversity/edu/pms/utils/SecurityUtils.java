package com.ktdsuniversity.edu.pms.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.member.vo.MemberVO;

public class SecurityUtils {
   /**
    * 인증정보(JWT)로 memVO를 가져오는 함수
    * @param authentication
    * @return JWT로 얻은 MemberVO
    */
   public MemberVO getMemVoByAuthentication(Authentication authentication) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      return ((SecurityUser) userDetails).getMemberVO();
   }
}
