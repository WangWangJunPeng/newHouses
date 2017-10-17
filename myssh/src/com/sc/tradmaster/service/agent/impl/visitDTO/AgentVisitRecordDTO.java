package com.sc.tradmaster.service.agent.impl.visitDTO;

public class AgentVisitRecordDTO {
	private String cusPhone;
	private String agentName;
	private String receptTime;
	private String cusName;
	private String visitCount;
	private String agentPhoto; 
	private Integer checkWay;
	private Integer writeState;
	private String leaveTime;
	
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getReceptTime() {
		return receptTime;
	}
	public void setReceptTime(String receptTime) {
		this.receptTime = receptTime;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(String visitCount) {
		this.visitCount = visitCount;
	}
	public String getAgentPhoto() {
		return agentPhoto;
	}
	public void setAgentPhoto(String agentPhoto) {
		this.agentPhoto = agentPhoto;
	}
	public Integer getCheckWay() {
		return checkWay;
	}
	public void setCheckWay(Integer checkWay) {
		this.checkWay = checkWay;
	}
	public Integer getWriteState() {
		return writeState;
	}
	public void setWriteState(Integer writeState) {
		this.writeState = writeState;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	
}
