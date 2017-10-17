package com.sc.tradmaster.service.agent.impl.visitDTO;

public class Customer {
	
	//置业顾问id
	private String userId;
	//置业顾问name
	private String agentName;
	//案场客户id
	private String customerId;
	//客户姓名
	private String customerName;
	//首次到访时间
	private String firstVisitTime;
	//末次到访时间
	private String lastVisitTime;
	//到访次数
	private Integer visitCount;
	//状态
	private String status;
	//头像
	private String agentPhoto;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getFirstVisitTime() {
		return firstVisitTime;
	}
	public void setFirstVisitTime(String firstVisitTime) {
		this.firstVisitTime = firstVisitTime;
	}
	public String getLastVisitTime() {
		return lastVisitTime;
	}
	public void setLastVisitTime(String lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAgentPhoto() {
		return agentPhoto;
	}
	public void setAgentPhoto(String agentPhoto) {
		this.agentPhoto = agentPhoto;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", agentName=" + agentName + ", customerName=" + customerName
				+ ", firstVisitTime=" + firstVisitTime + ", lastVisitTime=" + lastVisitTime + ", visitCount="
				+ visitCount + "]";
	}
}
