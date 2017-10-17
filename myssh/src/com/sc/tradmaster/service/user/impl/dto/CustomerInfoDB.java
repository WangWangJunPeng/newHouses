package com.sc.tradmaster.service.user.impl.dto;

// 该类封装点评表的用户信息
/**
 * @author Administrator
 *
 */
public class CustomerInfoDB {
	
	//客户名字
	private String customerName;
	//客户电话
	private String customerPhone;
	//状态,0:申请,1:同意,2:删除,3:否决,4:到款,5:签约,6:退款,7:撤销
	private Integer status;
	//到访次数
	private Integer comeNum;
	//拥有标签
	private String tags;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getComeNum() {
		return comeNum;
	}
	public void setComeNum(Integer comeNum) {
		this.comeNum = comeNum;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	

}
