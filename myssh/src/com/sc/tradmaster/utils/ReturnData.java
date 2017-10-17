package com.sc.tradmaster.utils;

public class ReturnData<T> {

	//请求是否成功 0：失败  1：成功
	private Integer code;
	//返回的信息
	private String msg;
	//返回的数据
	private T data;
	
	
	
	public ReturnData(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ReturnData [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
	
	
}
