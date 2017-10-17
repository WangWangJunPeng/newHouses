package com.sc.tradmaster.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_protocol")
public class Protocol {
	//主键，自增
	private Integer id;
	//案场id(私有协议使用字段)
	private String projectId;
	//协议设置者
	private String setUserId;
	//协议设置时间
	private String setTime;
	//协议标题
	private String title;
	//协议内容
	private String content;
	//协议类别(0公共协议；1私有协议)
	private Integer protocolType;
	//协议唯一标识符
	private String uniqueSymbol;
	
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getSetUserId() {
		return setUserId;
	}
	public void setSetUserId(String setUserId) {
		this.setUserId = setUserId;
	}
	public String getSetTime() {
		return setTime;
	}
	public void setSetTime(String setTime) {
		this.setTime = setTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(columnDefinition="longtext")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getProtocolType() {
		return protocolType;
	}
	public void setProtocolType(Integer protocolType) {
		this.protocolType = protocolType;
	}
	public String getUniqueSymbol() {
		return uniqueSymbol;
	}
	public void setUniqueSymbol(String uniqueSymbol) {
		this.uniqueSymbol = uniqueSymbol;
	}
	
	
}
