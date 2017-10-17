package com.sc.tradmaster.service.project.impl.dto;

//门店业务DTO类
public class ShopBusinessDTO {

	//门店客户名称
	private String shopCustomerName;
	//项目名称
	private String projectName;
	//项目id
	private String projectId;
	//备案状态 0 备案 1 已到访  2 成交
	private Integer applyStatus;
	//备案时间
	private String applyTime;
	//案场客户id
	private String projectCustomerId;
	
	public String getShopCustomerName() {
		return shopCustomerName;
	}
	public void setShopCustomerName(String shopCustomerName) {
		this.shopCustomerName = shopCustomerName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Integer getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getProjectCustomerId() {
		return projectCustomerId;
	}
	public void setProjectCustomerId(String projectCustomerId) {
		this.projectCustomerId = projectCustomerId;
	}
	@Override
	public String toString() {
		return "ShopBusinessDTO [shopCustomerName=" + shopCustomerName + ", projectName=" + projectName + ", projectId="
				+ projectId + ", applyStatus=" + applyStatus + ", applyTime=" + applyTime + ", projectCustomerId="
				+ projectCustomerId + "]";
	}
	
	
	
	
}
