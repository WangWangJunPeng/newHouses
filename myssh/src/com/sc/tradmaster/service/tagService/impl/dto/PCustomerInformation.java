package com.sc.tradmaster.service.tagService.impl.dto;

import java.util.List;

import com.sc.tradmaster.bean.TagType;

/**
 * 该类封装客户基本信息
 * @author Administrator
 *
 */
public class PCustomerInformation {
	//客户编码
	private String customerId;
	//客户名字
	private String customerName;
	//案场编码
	private String projectId;
	//客户电话
	private String customerPhone;
	//所含有的标签类目
	private List<TagType> rootTagTypes;
	//状态,0:申请,1:同意,2:删除,3:否决,4:到款,5:签约,6:退款,7:撤销
	private Integer status;
	//到访次数
	private Integer comeNum;
	//第一次到访时间
	private String firstTime;
	
	private String yixiang;	
	//是否是自己的客户 1-自己的 0 -不是自己
	private String own;
	private String desc;
	private String morePhone;
	//性别,0:未知,1:male,2;famle
	private Integer sex;
	
	
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getMorePhone() {
		return morePhone;
	}
	public void setMorePhone(String morePhone) {
		this.morePhone = morePhone;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getOwn() {
		return own;
	}
	public void setOwn(String own) {
		this.own = own;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getYixiang() {
		return yixiang;
	}
	public void setYixiang(String yixiang) {
		this.yixiang = yixiang;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public List<TagType> getRootTagTypes() {
		return rootTagTypes;
	}
	public void setRootTagTypes(List<TagType> rootTagTypes) {
		this.rootTagTypes = rootTagTypes;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getComeNum() {
		return comeNum;
	}
	public void setComeNum(Integer comeNum) {
		this.comeNum = comeNum;
	}
	public String getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	
	
	
	
	
	
	

}
