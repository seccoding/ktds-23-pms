//package com.ktdsuniversity.edu.pms.beans.security.jwt;
//
//
//import java.time.Duration;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.ktdsuniversity.edu.pms.beans.SHA;
//import com.ktdsuniversity.edu.pms.member.dao.MemberDao;
//import com.ktdsuniversity.edu.pms.member.vo.MemberVO;
//import com.ktdsuniversity.edu.pms.utils.Validator;
//import com.ktdsuniversity.edu.pms.utils.Validator.Type;
//
//@RestController
//public class JwtController {
//	
//	@Autowired
//	private JsonWebTokenProvider  jsonWebTokenProvider;
//	@Autowired
//	private MemberDao memberDao;
//	
//	@PostMapping("/auth/token")
//	public ResponseEntity<Map<String, Object>> createNewJWTToken(@RequestBody MemberVO member) throws JsonProcessingException{
////		EmpId, Password validation 
//		Validator<MemberVO> validator = new Validator<>(member);
//		validator
//			.add("memId", Type.MEMID, "사원번호가 올바르지 않습니다")
//			.add("pwd", Type.PASSWORD, "비밀번호가 올바르지 않습니다")
//			.start();	
//		if(validator.hasErrors()) {
//			return ResponseEntity
//					.status(HttpStatus.FORBIDDEN)
//					.body(Map.of("message","아이디 또는 비밀번호가 올바르지 않습니다.","error",validator.getErrors()));
//		}
//		
//		String memId = member.getMemId() ;//input empId
//		String password = member.getPwd();//input password
//		
////		들어온 body 의 정보로 아이디가 검색이 안될경우 에러메세지 반환
//		MemberVO dbMember = this.memberDao.getOneMember(memId);
//		if(dbMember ==null) {/*아이디 x*/
//			return ResponseEntity
//					.status(HttpStatus.FORBIDDEN)
//					.body(Map.of("message","아이디 또는 비밀번호가 존재하지 않습니다."));
//		}
//		
////		로그인 시도횟수가 5회 이상 + 마지막 로그인 시도시간이후 60분이내인 경우 에러메세지 반환
////		if(this.loginLogService.isAccountLocked(dbMember)) {
////			return ResponseEntity
////					.status(HttpStatus.FORBIDDEN)/*403 error*/
////					.body(Map.of("message","로그인 시도횟수를 초과했습니다, 몇시간 뒤 다시 시도해주세요"));
////		}
//			
////		input password 와 DB의 slat 로 인코딩된 비밀번호를 만들어서 DB의 비밀번호와 비교 
//		String salt = dbMember.getSalt(); //DB salt
//		SHA sha = new SHA();
//		String encondingPassword = sha.getEncrypt(password, salt);
//		
////		불일치시 에러메시지 반환, 일치할경우 토큰생성 후 반환
//		if(!encondingPassword.equals(dbMember.getPwd())) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body(Map.of("message","아이디 또는 비밀번호가 존재하지 않습니다."));
//        } else {
//            String jwt = jsonWebTokenProvider.generateToken(Duration.ofMinutes(240), dbMember);
//            return ResponseEntity
//                    .status(HttpStatus.CREATED)
//                    .body(Map.of("token", jwt));
//        }
//	}
//	
//	
//}
