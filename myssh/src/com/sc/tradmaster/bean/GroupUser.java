package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 集团用户账号
 * @author cdh  2017-10-10
 *
 */
@Entity
@Table(name="t_groupUser")
public class GroupUser {
	/**
	 * 用户id uuid 唯一主键
	 */
	@Id
	private String userId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 所属项目的id
	 */
	private String parentId;
	/**
	 * 所属项目名称
	 */
	private String projectName;
	/**
	 * 手机号码 （账号）
	 */
	private String phone;
	/**
	 * 权限   0：只读   1：可操作
	 */
	private int authorization;
	/**
	 * 最后操作时间
	 */
	private String lastHandleTime;
	/**
	 * 账号的状态 0:冻结  1:正常
	 */
	private int status;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAuthorization() {
		return authorization;
	}
	public void setAuthorization(int authorization) {
		this.authorization = authorization;
	}
	public String getLastHandleTime() {
		return lastHandleTime;
	}
	public void setLastHandleTime(String lastHandleTime) {
		this.lastHandleTime = lastHandleTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "GroupUser [userId=" + userId + ", userName=" + userName + ", password=" + password + ", parentId="
				+ parentId + ", projectName=" + projectName + ", phone=" + phone + ", authorization=" + authorization
				+ ", lastHandleTime=" + lastHandleTime + ", status=" + status + "]";
	}
	
	
}
