package com.sc.tradmaster.service.director.impl.dto;
/**
 * 销售数据(客户到访)
 * @author cdh - 2017-06-13
 *
 */
public class SaleData {
	
	//新到访客户量
	private long newCustomerVisit;
	//新到访有效登记客户量
	private long newCustomerValidVisit;
	//回头客户量
	private long customerAgainVisit;
	//三次到访客户量
	private long customerThirdVisit;
	public long getNewCustomerVisit() {
		return newCustomerVisit;
	}
	public void setNewCustomerVisit(long newCustomerVisit) {
		this.newCustomerVisit = newCustomerVisit;
	}
	public long getNewCustomerValidVisit() {
		return newCustomerValidVisit;
	}
	public void setNewCustomerValidVisit(long newCustomerValidVisit) {
		this.newCustomerValidVisit = newCustomerValidVisit;
	}
	public long getCustomerAgainVisit() {
		return customerAgainVisit;
	}
	public void setCustomerAgainVisit(long customerAgainVisit) {
		this.customerAgainVisit = customerAgainVisit;
	}
	public long getCustomerThirdVisit() {
		return customerThirdVisit;
	}
	public void setCustomerThirdVisit(long customerThirdVisit) {
		this.customerThirdVisit = customerThirdVisit;
	}
	@Override
	public String toString() {
		return "SaleData [newCustomerVisit=" + newCustomerVisit + ", newCustomerValidVisit=" + newCustomerValidVisit
				+ ", customerAgainVisit=" + customerAgainVisit + ", customerThirdVisit=" + customerThirdVisit + "]";
	}
	
	
	
}
