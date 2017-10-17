package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 集团项目圈表
 * @author cdh  2017-10-10
 *
 */
@Entity
@Table(name="t_groupProjectCircle")
public class GroupProjectCircle {

	/**
	 * 主键 自增
	 */
	@Id
	@GeneratedValue
	private int id;
	/**
	 * 集团id
	 */
	private String groupId;
	/**
	 * 项目圈的名称
	 */
	private String circleName;
	/**
	 * 项目的id集合，使用","隔开
	 */
	private String projectIds;
	/**
	 * 圈子成员的id集合，使用","隔开
	 */
	private String userIds;
	
	/**
	 * 项目圈的状态 0:删除 1：正常
	 */
	private Integer status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	public String getProjectIds() {
		return projectIds;
	}
	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "GroupProjectCircle [id=" + id + ", groupId=" + groupId + ", circleName=" + circleName + ", projectIds="
				+ projectIds + ", userIds=" + userIds + ", status=" + status + "]";
	}
	
	
}
