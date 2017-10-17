package com.sc.tradmaster.service.director.impl.dto;
/*
 * 传送给前端的对象
 *  @author cdh-2017-06-13
 * */
public class DirectorResult<T> {

	private boolean success;
	private T data;
	private String error;
	
	public DirectorResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}

	public DirectorResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "DirectorResult [success=" + success + ", data=" + data + ", error=" + error + "]";
	}
	
	
	
	
}
