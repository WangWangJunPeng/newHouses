package com.sc.tradmaster.service.project.impl.dto;

/**
 * 待审核项目的DTO
 * @author Administrator
 *
 */
public class RegisterProjectDTO {
	
	private String projectId;//项目ip
	private String projectName;//项目名称
	private String city;//项目所在地(省市区)
	private String propertyAddress;//物业地址
	private String userId;//用户ID
	private String userCaption;//用户姓名(联系人)
	private String phone;//联系电话
	private String createTime;//申请时间(用户创建时间)
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPropertyAddress() {
		return propertyAddress;
	}
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	public String getUserCaption() {
		return userCaption;
	}
	public void setUserCaption(String userCaption) {
		this.userCaption = userCaption;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
