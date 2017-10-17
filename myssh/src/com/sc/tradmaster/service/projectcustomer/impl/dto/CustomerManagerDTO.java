package com.sc.tradmaster.service.projectcustomer.impl.dto;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 该类用于封装客户管理 搜索页面的数据
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class CustomerManagerDTO {
	//客户编码
	private String projectCustomerId;
	//客户名称
	private String projectCustomerName;
	//客户电话,唯一性
	private String projectCustomerPhone;
	//当前归属的职业顾问用户编码   由案场经理分配
	private String ownerUserId;
	private String ownerUserName;
	//状态:1-已到访,2-认购,3-付款,4-签约,5-认购否决,6-退款,7-撤单
	private String status;
	//来访次数
	private Integer visitTimes;
	//标签
	private String tags;
	private List<String> tagsList;
	
	
	
	public String getOwnerUserId() {
		return ownerUserId;
	}
	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	public Integer getVisitTimes() {
		return visitTimes;
	}
	public void setVisitTimes(Integer visitTimes) {
		this.visitTimes = visitTimes;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public List<String> getTagsList() {
		return tagsList;
	}
	public void setTagsList(List<String> tagsList) {
		this.tagsList = tagsList;
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
		if(StringUtils.isEmpty(projectCustomerName)){
			this.projectCustomerName="未知";
		}else{
			this.projectCustomerName = projectCustomerName;
		}
	}
	public String getProjectCustomerPhone() {
		return projectCustomerPhone;
	}
	public void setProjectCustomerPhone(String projectCustomerPhone) {
		if(StringUtils.isEmpty(projectCustomerPhone)){
			this.projectCustomerPhone = "未知";
		}else{
			this.projectCustomerPhone = projectCustomerPhone;
		}
	}
	
	public String getOwnerUserName() {
		return ownerUserName;
	}
	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CustomerManagerDTO [projectCustomerId=" + projectCustomerId + ", projectCustomerName="
				+ projectCustomerName + ", projectCustomerPhone=" + projectCustomerPhone + ", ownerUserId="
				+ ownerUserId + ", ownerUserName=" + ownerUserName + ", status=" + status + ", visitTimes=" + visitTimes
				+ ", tags=" + tags + ", tagsList=" + tagsList + "]";
	}

	
	
	
	
	
}
