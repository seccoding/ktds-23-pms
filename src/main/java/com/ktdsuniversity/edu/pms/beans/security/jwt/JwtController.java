package com.ktdsuniversity.edu.pms.beans.security.jwt;

import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.beans.SHA;
import com.ktdsuniversity.edu.pms.employee.dao.EmployeeDao;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

@RestController
public class JwtController {
	
	@Autowired
	private JsonWebTokenProvider  jsonWebTokenProvider;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@PostMapping("/auth/token")
	public ResponseEntity<Map<String, Object>> createNewJWTToken(@RequestBody EmployeeVO employee){
		String empId = employee.getEmpId() ;
		EmployeeVO dbEmployee = this.employeeDao.getOneEmployee(empId);
		
//		들어온 body의 정보로 아이디가 검색이 안될경우 에러메세지 반환
		if(dbEmployee ==null) { 
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)/*403 error*/
					.body(Map.of("message","아이디 또는 비밀번호가 존재하지 않습니다."));
		}
		
//		input password와 DB의 slat 로 인코딩된 비밀번호를 만들어서 DB의 비밀번호와 비교 
		String password = employee.getPwd();//input password
		String salt = dbEmployee.getSalt(); //DB salt
		SHA sha = new SHA();
		String encondingPassword = sha.getEncrypt(password, salt);
		if(encondingPassword.equals(dbEmployee.getPwd())) {/*일치할경우 토큰생성 후 반환 */
			String jwt =jsonWebTokenProvider.generateToken(Duration.ofHours(12), dbEmployee);
		
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(Map.of("token",jwt));
		}else {
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)/*403 error*/
					.body(Map.of("message","아이디 또는 비밀번호가 존재하지 않습니다."));
		}
	}
}
