package com.ktdsuniversity.edu.pms.beans.security.jwt;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ktdsuniversity.edu.pms.beans.SHA;
import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.login.dao.LoginLogDao;
import com.ktdsuniversity.edu.pms.login.service.LoginLogService;

@RestController
public class JwtController {
	
	@Autowired
	private JsonWebTokenProvider  jsonWebTokenProvider;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private LoginLogDao loginLogDao;
	
	@Autowired
	private LoginLogService loginLogService;
	

	
	@PostMapping("/auth/token")
	public ResponseEntity<Map<String, Object>> createNewJWTToken(@RequestBody EmployeeVO employee) throws JsonProcessingException{
		String empId = employee.getEmpId() ;//input empId
		String password = employee.getPwd();//input password
		
		
//		TODO empId, password validation 구현 필요
		
//		들어온 body 의 정보로 아이디가 검색이 안될경우 에러메세지 반환
		EmployeeVO dbEmployee = this.employeeDao.getOneEmployee(empId);
		if(dbEmployee ==null) {/*아이디 x*/
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body(Map.of("message","아이디 또는 비밀번호가 존재하지 않습니다."));
		}
		
//		로그인 시도횟수가 5회 이상 + 마지막 로그인 시도시간이후 60분이내인 경우 에러메세지 반환
		if(this.loginLogService.isAccountLocked(dbEmployee)) {
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)/*403 error*/
					.body(Map.of("message","로그인 시도횟수를 초과했습니다, 몇시간 뒤 다시 시도해주세요"));
		}
			
//		input password와 DB의 slat 로 인코딩된 비밀번호를 만들어서 DB의 비밀번호와 비교 
		String salt = dbEmployee.getSalt(); //DB salt
		SHA sha = new SHA();
		String encondingPassword = sha.getEncrypt(password, salt);
		
		
//		불일치시 에러메시지 반환, 일치할경우 토큰생성 후 반환
		if(!encondingPassword.equals(dbEmployee.getPwd())) {
//			TODO 로그인 시도횟수 1 증가(update 라 트렌젝션 필요)
			this.loginLogDao.updateOneEmpLgnTryPlusOne(empId);
			
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)/*403 error*/
					.body(Map.of("message","아이디 또는 비밀번호가 존재하지 않습니다."));
		}else {
//			TODO 로그인 시도횟수 초기화(update 라 트렌젝션 필요)
			this.loginLogDao.updateOneEmpLgnTryZero(empId);
			
			String jwt =jsonWebTokenProvider.generateToken(Duration.ofMinutes(20), dbEmployee);
			
			EmployeeVO employeeVO = this.jsonWebTokenProvider.getUserFormToken(jwt);
			SecurityUser user = new SecurityUser(employeeVO);
			
//			퇴사 및 휴직 직원 체크후 해당직원인 경우 에러메시지 반환
			if(!user.isAccountNonLocked()) {
				return ResponseEntity
						.status(HttpStatus.FORBIDDEN)/*403 error*/
						.body(Map.of("message","퇴사 혹은 휴직 처리된 직원입니다"));
			}
//			비밀변호 변경일이 90일 이상인경우 비밀번호 변경 페이지로 이동시킴
			if(!user.isCredentialsNonExpired()){
				Map<String ,Object> map = new HashMap<String, Object>();
				map.put("token", jwt);
				map.put("credentialsExpired", true);
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(map);
				
			}else {// 아무 문제 없음
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(Map.of("token",jwt));
			}
		}
	}
	
	
}
