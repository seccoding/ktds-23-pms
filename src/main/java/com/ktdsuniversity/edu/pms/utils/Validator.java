package com.ktdsuniversity.edu.pms.utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator<T> {
	
	public enum Type {
		NOT_EMPTY, SIZE, EMAIL, EQUALS, PASSWORD, MAX, MIN, EMPID, DEPTID, JOBID, PSTNID, DATE, NOW_DATE, IMAGE_FILE
	}

	private T object;
	private Map<String, Map<Type, String>> validationTypes;
	private Map<String, Map<Type, Object>> refValues;
	private Map<String, Map<Type, Object>> results;

	public Validator(T object) {
		this.object = object;
		this.validationTypes = new HashMap<>();
		this.refValues = new HashMap<>();
	}

	public Validator<T> add(String fieldName, Type type, String errorMessage) {
		return add(fieldName, type, null, errorMessage);
	}

	public Validator<T> add(String fieldName, Type type, Object refValue, String errorMessage) {
		Map<Type, String> validationType = null;
		if (!this.validationTypes.containsKey(fieldName)) {
			validationType = new HashMap<>();
			this.validationTypes.put(fieldName, validationType);
		}

		validationType = this.validationTypes.get(fieldName);
		if (!validationType.containsKey(type)) {
			validationType.put(type, errorMessage);
		}

		Map<Type, Object> refValueMap = null;
		if (!this.refValues.containsKey(fieldName)) {
			refValueMap = new HashMap<>();
			this.refValues.put(fieldName, refValueMap);
		}

		if (refValue != null) {
			refValueMap = this.refValues.get(fieldName);
			if (!refValueMap.containsKey(type)) {
				refValueMap.put(type, refValue);
			}
		}
		return this;
	}

	public void start() {
		this.results = new HashMap<>();

		this.validationTypes.forEach((key, valueMap) -> {
			String value = this.getValue(this.getField(key));
			valueMap.forEach((type, errorMessage) -> {
				boolean result = true;
				if (type == Type.NOT_EMPTY) {
					result = !StringUtil.isEmpty(value);
				} else if (type == Type.EMAIL) {
					result = StringUtil.isEmailFormat(value);
				}
				else if (type == Type.EMPID) {
					result = StringUtil.isEmpIdFormat(value);
				}
				else if (type == Type.SIZE) {
					if (this.hasRefValue(key, type)) {
						Object otherObjectValue = this.getRefValue(key, type);
						int minSize = this.toInt(otherObjectValue);
						result = StringUtil.isEnough(value, minSize);
					}
				} else if (type == Type.EQUALS) {
					if (this.hasRefValue(key, type)) {
						String otherValue = this.getRefStringValue(key, type, null);
						result = value.equals(otherValue);
					}
				} else if (type == Type.PASSWORD) {
					result = StringUtil.correctPasswordFormat(value);
				} else if (type == Type.MIN) {
					if (this.hasRefValue(key, type)) {
						int iValue = this.toInt(value);
						Object otherObjectValue = this.getRefValue(key, type);
						int minValue = this.toInt(otherObjectValue);
						result = iValue >= minValue;
					}
				} else if (type == Type.MAX) {
					if (this.hasRefValue(key, type)) {
						int iValue = this.toInt(value);
						Object otherObjectValue = this.getRefValue(key, type);
						int maxValue = this.toInt(otherObjectValue);
						result = iValue <= maxValue;
					}
				} else if (type == Type.DATE) {
					if (this.hasRefValue(key, type)) {
						String otherValue = this.getRefStringValue(key, type, null);
						if (StringUtil.isEmpty(otherValue)) {
							result = false;
						} else {
							LocalDate valueLocalDate = LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
							LocalDate refValueLocalDate = LocalDate.parse(otherValue, DateTimeFormatter.ISO_DATE);
							result = !valueLocalDate.isEqual(refValueLocalDate)
									|| !valueLocalDate.isAfter(refValueLocalDate);
						}
					}
				} else if (type == Type.NOW_DATE) {
					result = StringUtil.isBeforeLocalDateNow(value);
				} 
				else if (type == Type.IMAGE_FILE) {
					
					if (value.matches("^image\\/.*")) {
						result = true;
					} else {
						result = false;
					}
					
				}

				if (!result) {
					if (!this.results.containsKey(key)) {
						this.results.put(key, new HashMap<>());
					}

					if (!this.results.get(key).containsKey(type)) {
						this.results.get(key).put(type, errorMessage);
					}
				}
			});
		});
	}

	public boolean hasErrors() {
		return this.results.size() > 0;
	}

	public Map<String, List<String>> getErrors() {
		if (this.hasErrors()) {
			Map<String, List<String>> result = new HashMap<>();
			this.results.forEach((key, valueMap) -> {
				final List<String> messages;
				if (!result.containsKey(key)) {
					messages = new ArrayList<>();
					result.put(key, messages);
				}

				valueMap.forEach((type, errorMessage) -> {
					result.get(key).add(errorMessage.toString());
				});
			});

			return result;
		}
		return null;
	}

	private boolean hasRefValue(String key, Type type) {
		return this.refValues.containsKey(key) && this.refValues.get(key).containsKey(type);
	}

	private Object getRefValue(String key, Type type) {
		if (hasRefValue(key, type)) {
			return this.refValues.get(key).get(type);
		}

		return null;
	}

	private String getRefStringValue(String key, Type type, String defValue) {
		Object refValue = getRefValue(key, type);
		return refValue == null ? defValue : refValue.toString();
	}

	private int toInt(Object value) {
		if (value == null) {
			return 0;
		}

		if (value.toString().trim().matches("^[0-9]+$")) {
			return Integer.parseInt(value.toString().trim());
		}

		return 0;
	}

	private Field getField(String fieldName) {
		return getField(fieldName, this.object.getClass());
	}

	private Field getField(String fieldName, Class<?> cls) {
		if (cls == Object.class) {
			return null;
		}
		Field field = null;
		try {
			field = cls.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			field = getField(fieldName, cls.getSuperclass());
		}

		if (field != null) {
			field.setAccessible(true);
		}

		return field;
	}

	private String getValue(Field field) {

		if (field == null) {
			return null;
		}

		Object fieldValue = null;
		try {
			fieldValue = field.get(this.object);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
		return fieldValue == null ? null : fieldValue.toString();
	}

}