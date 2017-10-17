package com.sc.tradmaster.service.director.impl.dto;

import java.util.List;

/*
 * 数据分析类
 * @author cdh-2017-06-13
 * */
public class DataAnalysis {

	//再到访
	private long visitAgain;
	//新登，(新增客户)
	private long newCustomer;
	//新登(指)，(新增客户指定接访)
	private long newCustomerAppoint;
	//流失
	private long loseCustomer;
	//流失(指)
	private long loseCustomerAppoint;
	//数据生成时间:需求是当选择的时间区间大于三个月就每月为节点，当小于三个月就周为节点
	private String dataDate;
	
	public long getVisitAgain() {
		return visitAgain;
	}
	public void setVisitAgain(long visitAgain) {
		this.visitAgain = visitAgain;
	}
	public long getNewCustomer() {
		return newCustomer;
	}
	public void setNewCustomer(long newCustomer) {
		this.newCustomer = newCustomer;
	}
	public long getNewCustomerAppoint() {
		return newCustomerAppoint;
	}
	public void setNewCustomerAppoint(long newCustomerAppoint) {
		this.newCustomerAppoint = newCustomerAppoint;
	}
	public long getLoseCustomer() {
		return loseCustomer;
	}
	public void setLoseCustomer(long loseCustomer) {
		this.loseCustomer = loseCustomer;
	}
	public long getLoseCustomerAppoint() {
		return loseCustomerAppoint;
	}
	public void setLoseCustomerAppoint(long loseCustomerAppoint) {
		this.loseCustomerAppoint = loseCustomerAppoint;
	}
	public String getDataDate() {
		return dataDate;
	}
	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}
	
	@Override
	public String toString() {
		return "DataAnalysis [visitAgain=" + visitAgain + ", newCustomer=" + newCustomer + ", newCustomerAppoint="
				+ newCustomerAppoint + ", loseCustomer=" + loseCustomer + ", loseCustomerAppoint=" + loseCustomerAppoint
				+ ", dataDate=" + dataDate + "]";
	}
	
	
	
}
