package com.sc.tradmaster.service.user.impl.dto;

import java.util.List;

import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.VisitRecords;

public class AllCheckedCustomerDTO {
	
	private String agentId;
	private String agentName;
	private String phone;
	private String photo;
	private Integer notCheckedNum;
	private Integer haveCheckedNum;
	private Integer sex;
	
	private List<VisitRecords> notCheckedList;
	private List<VisitRecords> haveCheckedList;

	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getPhoto() {
		return photo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getNotCheckedNum() {
		return notCheckedNum;
	}
	public void setNotCheckedNum(Integer notCheckedNum) {
		this.notCheckedNum = notCheckedNum;
	}
	public Integer getHaveCheckedNum() {
		return haveCheckedNum;
	}
	public void setHaveCheckedNum(Integer haveCheckedNum) {
		this.haveCheckedNum = haveCheckedNum;
	}
	public List<VisitRecords> getNotCheckedList() {
		return notCheckedList;
	}
	public void setNotCheckedList(List<VisitRecords> notCheckedList) {
		this.notCheckedList = notCheckedList;
	}
	public List<VisitRecords> getHaveCheckedList() {
		return haveCheckedList;
	}
	public void setHaveCheckedList(List<VisitRecords> haveCheckedList) {
		this.haveCheckedList = haveCheckedList;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "AllCheckedCustomerDTO [agentId=" + agentId + ", agentName=" + agentName + ", phone=" + phone
				+ ", photo=" + photo + ", notCheckedNum=" + notCheckedNum + ", haveCheckedNum=" + haveCheckedNum
				+ ", notCheckedList=" + notCheckedList + ", haveCheckedList=" + haveCheckedList + "]";
	}
}
