package com.ktdsuniversity.edu.pms.utils;

/**
 * Spring Validator를 사용하지 않고
 * 파라미터 유효성 검사를 하기 위한 유틸리티.
 */
public abstract class ValidationUtils {

	private ValidationUtils() {
		
	}
	
	public final static boolean notEmpty(String value) {
		return ! StringUtil.isEmpty(value);
	}
	
	public final static boolean email(String value) {
		return StringUtil.isEmailFormat(value);
	}
	
	public final static boolean size(String value, int minSize) {
		return StringUtil.isEnough(value, minSize);
	}
	
	public final static boolean isEquals(String value, String otherValue) {
		if (StringUtil.isEmpty(value) || StringUtil.isEmpty(otherValue)) {
			return false;
		}
		
		return value.equals(otherValue);
	}
	
}