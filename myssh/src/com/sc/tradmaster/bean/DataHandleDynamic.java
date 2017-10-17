package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 数据操作记录类
 * @author cdh 2017-07-27
 *
 */
@Entity
@Table(name="t_datahandledynamic")
public class DataHandleDynamic {
	//主键，自增
	private Integer id;
	//数据操作时间
	private String handleDate;
	//数据操作状态  0：自动（定时器）  1： 手动
	private Integer handleState;
	//数据库操作人 定时器触发：admin  人工添加：userId
	private String handleUser;
	//归集的目标id（项目id）
	private String targetId;
	//归集目标的名称
	private String targetName;
	//详细信息
	private String message;
	
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}
	public Integer getHandleState() {
		return handleState;
	}
	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}
	public String getHandleUser() {
		return handleUser;
	}
	public void setHandleUser(String handleUser) {
		this.handleUser = handleUser;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "DataHandleDynamic [id=" + id + ", handleDate=" + handleDate + ", handleState=" + handleState
				+ ", handleUser=" + handleUser + ", targetId=" + targetId + ", targetName=" + targetName + ", message="
				+ message + "]";
	}
	
}
