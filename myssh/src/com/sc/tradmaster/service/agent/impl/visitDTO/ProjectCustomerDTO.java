package com.sc.tradmaster.service.agent.impl.visitDTO;

import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.User;

public class ProjectCustomerDTO {
	//案场编码
	private String projectId;
	//客户编码
	private String projectCustomerId;
	//客户名称
	private String projectCustomerName;
	//客户电话,唯一性
	private String projectCustomerPhone;
	//身份证号码
	private String idCard;
	//性别,0:未知,1:male,2;famle
	private Integer sex;
	//当前归属的职业顾问用户编码   由案场经理分配
	private String ownerUserName;
	
	
	public ProjectCustomerDTO creatyProjectCustomer(ProjectCustomers pc, User agent) {
		if(pc!=null){
			this.projectId = pc.getProjectId();
			this.projectCustomerId = pc.getProjectCustomerId();
			this.projectCustomerName = pc.getProjectCustomerName();
			this.projectCustomerPhone = pc.getProjectCustomerPhone();
			if(pc.getOwnerUserId()!=null && !pc.getOwnerUserId().equals("")){
				this.ownerUserName = agent.getUserCaption();
			}else{
				this.ownerUserName = "未知";
			}
			
		}
		return this;
	}
	
	
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectCustomerId() {
		return projectCustomerId;
	}
	public void setProjectCustomerId(String projectCustomerId) {
		this.projectCustomerId = projectCustomerId;
	}
	public String getProjectCustomerName() {
		return projectCustomerName;
	}
	public void setProjectCustomerName(String projectCustomerName) {
		this.projectCustomerName = projectCustomerName;
	}
	public String getProjectCustomerPhone() {
		return projectCustomerPhone;
	}
	public void setProjectCustomerPhone(String projectCustomerPhone) {
		this.projectCustomerPhone = projectCustomerPhone;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getOwnerUserName() {
		return ownerUserName;
	}
	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}
	
}
