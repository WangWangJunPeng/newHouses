package com.sc.tradmaster.service.director.impl.dto;

/**
 * 记录
 * @author Administrator
 *
 */
public class CustomerVisit {

	//客户Id
	private String customerId;
	//数量
	private Integer num;
	
	
	
	
	
	public CustomerVisit() {
		super();
	}
	
	public CustomerVisit(String customerId, Integer num) {
		super();
		this.customerId = customerId;
		this.num = num;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "CustomerVisit [customerId=" + customerId + ", num=" + num + "]";
	}
	
	
	
}
