package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 集团报表板块表
 * @author cdh  2017-10-10
 *
 */
@Entity
@Table(name="t_groupStatementModule")
public class GroupStatementModule {

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
	 * 板块的名称
	 */
	private String moduleName;
	/**
	 * 项目的id集合 使用","隔开 
	 */
	private String projectIds;
	
	/**
	 * 板块的状态：  0：删除  1：正常
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
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getProjectIds() {
		return projectIds;
	}
	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}
	
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "GroupStatementModule [id=" + id + ", groupId=" + groupId + ", moduleName=" + moduleName
				+ ", projectIds=" + projectIds + ", status=" + status + "]";
	}
	
}
