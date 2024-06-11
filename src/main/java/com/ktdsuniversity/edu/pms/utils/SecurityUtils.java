package com.ktdsuniversity.edu.pms.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public class SecurityUtils {
   /**
    * 인증정보(JWT)로 empVO를 가져오는 함수
    * @param authentication
    * @return JWT로 얻은 EmployeeVO
    */
   public EmployeeVO getEmpVoByAuthentication(Authentication authentication) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      return ((SecurityUser) userDetails).getEmployeeVO();
   }
}
