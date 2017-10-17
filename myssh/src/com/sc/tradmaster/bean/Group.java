package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 集团表
 * @author cdh-2017-10-10
 *
 */
@Entity
@Table(name="t_group")
public class Group {

	/**
	 * 集团id 唯一主键 UUID
	 */
	@Id
	private String groupId;
	
	/**
	 * 集团名称 
	 */
	private String groupName;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 子案场的集合使用","进行隔开
	 */
	private String projectIds;
	
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getProjectIds() {
		return projectIds;
	}
	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}
	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName + ", createTime=" + createTime + ", projectIds="
				+ projectIds + "]";
	}
	
	
}
