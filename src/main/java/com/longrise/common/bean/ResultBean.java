package com.longrise.common.bean;

import java.util.HashMap;
import java.util.Map;

public class ResultBean {
	private Map<String, Object> result = null;

	public ResultBean() {
		result = new HashMap<String, Object>();
	};

	public ResultBean(Map<String, Object> result) {
		this.result = result;
	};

	public Map<String, Object> getResultBean() {
		return result;
	}

	public void setResultBean(Map<String, Object> result) {
		this.result = result;
	}

	public String getString(String key) {
		return (String) result.get(key);
	}

	public Float getFloat(String key) {
		return (Float) result.get(key);
	}

	public Double getDouble(String key) {
		return (Double) result.get(key);
	}

	public Integer getInteger(String key) {
		return (Integer) result.get(key);
	}

	public Boolean getBoolean(String key) {
		return (Boolean) result.get(key);
	}
}
