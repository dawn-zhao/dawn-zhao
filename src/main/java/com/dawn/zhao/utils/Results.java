package com.dawn.zhao.utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Results {
	/**
	 * 错误代码
	 */
	private String status;
	
	/**
	 * 错误描述
	 */
	private String error;
	
	/**
	 * 返回结果对象
	 */
	private Object data;
	
	public Results() {
		super();
	}
	
	public Results(String status, String error, Object data) {
		super();
		this.status = status;
		this.error = error;
		this.data = data;
	}
	
	

	public Results(String status, String error) {
		super();
		this.status = status;
		this.error = error;
		this.data = null;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
