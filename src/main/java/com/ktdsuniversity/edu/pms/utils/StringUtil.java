package com.ktdsuniversity.edu.pms.utils;

import java.sql.Date;
import java.time.LocalDate;

/**
 * 유틸리티들은 추상클래스로 만들어 인스턴스화 못하게 한다.
 */
public abstract class StringUtil {

	/**
	 * 생성자를 private으로 숨겨서 익명클래스도 못만들게 막는다.
	 */
	private StringUtil() {
		
	}
	
	/**
	 * 사원번호가 숫자 7자리이거나,  "system"이 포함된 문자열인지 검사
	 * @param str 입력받은 사원번호
	 * @return 사원번호 형식이 맞다면 true, 틀리다면 false
	 */
	public final static boolean isEmpIdFormat(final String str) {
		if (isEmpty(str)) {
			return true;
		}

		String EmpIdFormat = "(\\d{7}|.*system.*)";

		return str.matches(EmpIdFormat);
	}


	/**
	 * 영문자, 숫자, 특수문자를 각각하나 이상 포함하고있는 10자리 문자인지 검사해주는 정규표현식
	 * @param str 화면에서 받은 암호
	 * @return 형식에 맞은지 여부에 따라 true false
	 */
	public final static boolean correctPasswordFormat(final String str) {
		if (isEmpty(str)) {
			return true;
		}
		
		String passwordFormat = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+|~=`{}\\[\\]:\";'<>?,./\\\\\\-])[a-zA-Z0-9!@#$%^&*()_+|~=`{}\\[\\]:\";'<>?,./\\\\\\-]{10,}$";
		
		return str.matches(passwordFormat);
	}
	
	/**
	 * 이미지 파일 형식 검사
	 */
//	public final static boolean isImageFileFormat(final String str) {
//		if (isEmpty(str)) {
//			return false;
//		}
//		
//		String ImageFileFormat = ".*\\.(jpeg|jpg|img|png)$";
//		
//		return str.matches(ImageFileFormat);
//	}
	
//	/**
//	 * 입력받은 문자가 DEPT_(숫자6자리)_(숫자6자리)인지 확인해주는 메소드
//	 * @param str 입력한 부서ID
//	 * @return 입력한 부서ID 형식이 맞다면 true, 틀리다면 false
//	 */
//	public final static boolean isDeptIdFormat(final String str) {
//		if (isEmpty(str)) {
//			return false;
//		}
//		
//		String DeptIdFormat = "^DEPT_\\d{6}_\\d{6}$";
//		
//		return str.matches(DeptIdFormat);
//	}
//	
//	/**
//	 * 입력받은 문자가 JOB_(숫자6자리)_(숫자6자리)인지 확인해주는 메소드
//	 * @param str 입력한 직무ID
//	 * @return 입력한 직무ID 형식이 맞다면 true, 틀리다면 false
//	 */
//	public final static boolean isJobIdFormat(final String str) {
//		if (isEmpty(str)) {
//			return false;
//		}
//		
//		String JobIdFormat = "^JOB_\\d{6}_\\d{6}$";
//		
//		return str.matches(JobIdFormat);
//	}
//	
//	/**
//	 * 입력받은 문자가 101~110사이의 숫자인지 확인해주는 메소드
//	 * @param str 입력한 직급ID
//	 * @return 입력한 직급ID 형식이 맞다면 true, 틀리다면 false
//	 */
//	public final static boolean isPstnIdFormat(final String str) {
//		if (isEmpty(str)) {
//			return false;
//		}
//		
//		String PstnIdFormat = "^10[1-9]$|^110$";
//		
//		return str.matches(PstnIdFormat);
//	}
	
	/**
	 * 입력받은 문자가 현재날짜(LocalDate.now())보다 작은지 검사하는 메소드 
	 * @param str 입력받은 날짜
	 * @return 현재 날짜 보다 작다면 true, 크다면 false 
	 */
	public final static boolean isBeforeLocalDateNow(final String str) {
		if (isEmpty(str)) {
			return true;
		}
		LocalDate nowLocalDate = LocalDate.now();
		
		return LocalDate.parse(str).isBefore(nowLocalDate);
	}
	
	public final static String replaceTagSymbols(final String str) {
		return nullToValue(str, "").replace(">", "&gt;")
					.replace("<", "&lt;")
					.replace(" ", "&nbsp;");
	}
	
	public final static String nullToValue(final String str, final String nullValue) {
		if (isEmpty(str)) {
			return nullValue;
		}
		
		return str;
	}
	
	public final static boolean isEmpty(final String str) {
		return str == null || str.trim().length() == 0;
	}
	
	public final static boolean isEnough(final String str, final int minLength) {
		if (isEmpty(str)) {
			return false;
		}
		
		return trim(str).length() >= minLength;
	}
	
	public final static String trim(final String str) {
		if (isEmpty(str)) {
			return null;
		}
		
		return str.trim();
	}
	
	public final static boolean isEmailFormat(final String str) {
		if (isEmpty(str)) {
			return true;
		}
		
		String emailPattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])";
		return str.matches(emailPattern);
	}
	
	public final static boolean isPhoneFormat(final String str) {
		if (isEmpty(str)) {
			return true;
		}
		
		String phoneFormat = "\\d{2,4}( |-|\\.)(\\d{4}( |-|\\.)\\d{4})";
		return str.matches(phoneFormat);
	}
	
}