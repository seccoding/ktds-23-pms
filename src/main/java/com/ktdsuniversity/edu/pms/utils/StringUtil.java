package com.ktdsuniversity.edu.pms.utils;

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
	 * 사원번호가 숫자 10자리이거나,  "system"이 포함된 문자열인지 검사
	 * @param str 입력받은 사원번호
	 * @return 사원번호 형식이 맞다면 true, 틀리다면 false
	 */
	public final static boolean isEmpIdFormat(final String str) {
		if (isEmpty(str)) {
			return false;
		}
		
		String EmpIdFormat = "(\\d{7}|.*system.*)";
		
		return str.matches(EmpIdFormat);
	}
	
	
	public final static boolean correctPasswordFormat(final String str) {
		if (isEmpty(str)) {
			return false;
		}
		
		String passwordFormat = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
		
		return str.matches(passwordFormat);
	}
	
	/**
	 * 입력받은 문자가 DEPT_(숫자6자리)_(숫자6자리)인지 확인해주는 메소드
	 * @param str 입력한 부서ID
	 * @return 입력한 부서ID 형식이 맞다면 true, 틀리다면 false
	 */
	public final static boolean isDeptIdFormat(final String str) {
		if (isEmpty(str)) {
			return false;
		}
		
		String DeptIdFormat = "^DEPT_\\d{6}_\\d{6}$";
		
		return str.matches(DeptIdFormat);
	}
	
	/**
	 * 입력받은 문자가 JOB_(숫자6자리)_(숫자6자리)인지 확인해주는 메소드
	 * @param str 입력한 직무ID
	 * @return 입력한 직무ID 형식이 맞다면 true, 틀리다면 false
	 */
	public final static boolean isJobIdFormat(final String str) {
		if (isEmpty(str)) {
			return false;
		}
		
		String JobIdFormat = "^JOB_\\d{6}_\\d{6}$";
		
		return str.matches(JobIdFormat);
	}
	
	/**
	 * 입력받은 문자가 101~110사이의 숫자인지 확인해주는 메소드
	 * @param str 입력한 직급ID
	 * @return 입력한 직급ID 형식이 맞다면 true, 틀리다면 false
	 */
	public final static boolean isPstnIdFormat(final String str) {
		if (isEmpty(str)) {
			return false;
		}
		
		String PstnIdFormat = "^10[1-9]$|^110$";
		
		return str.matches(PstnIdFormat);
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
			return false;
		}
		
		String emailPattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])";
		return str.matches(emailPattern);
	}
	
	public final static boolean isPhoneFormat(final String str) {
		if (isEmpty(str)) {
			return false;
		}
		
		String phoneFormat = "\\d{2,4}( |-|\\.)(\\d{4}( |-|\\.)\\d{4})";
		return str.matches(phoneFormat);
	}
	
}