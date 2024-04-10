package com.ktdsuniversity.edu.pms.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class AjaxResponse {

	@SerializedName("data")
	private Map<String, Object> response;

	public AjaxResponse() {
		this.response = new HashMap<>();
	}

	public AjaxResponse append(String key, Object value) {
		this.response.put(key, value);
		return this;
	}

	public Map<String, Object> getData() {
		return this.response;
	}

}