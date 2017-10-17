package com.sc.tradmaster.utils;

public class DataResult<T> {

	//是否成功
	private boolean success;
	//成功返回的数据
	private T data;
	//失败返回的错误信息
	private String error;
	
	
	
	public DataResult() {
		super();
	}

	/**
	 * 成功返回
	 * @param success
	 * @param data
	 */
	public DataResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	
	/**
	 * 失败返回
	 * @param success
	 * @param error
	 */
	public DataResult(boolean success, String error) {
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
		return "DataResult [success=" + success + ", data=" + data + ", error=" + error + "]";
	}
	
	
}
