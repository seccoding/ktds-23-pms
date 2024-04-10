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
	
	public final static boolean correctPasswordFormat(final String str) {
		if (isEmpty(str)) {
			return false;
		}
		
		String passwordFormat = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
		return str.matches(passwordFormat);
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