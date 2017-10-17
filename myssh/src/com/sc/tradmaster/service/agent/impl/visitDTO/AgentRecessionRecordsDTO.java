package com.sc.tradmaster.service.agent.impl.visitDTO;

public class AgentRecessionRecordsDTO {
	private Integer visitNo;
	private String userId;
	private String projectId;
	private String arriveTime;
	private String receptTime;
	private String leaveTime;
	private String name;
	private String photo;
	private String phone;
	private Integer visitCount;
	private Integer recessionTimeDiff;
	
	public Integer getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getReceptTime() {
		return receptTime;
	}
	public void setReceptTime(String receptTime) {
		this.receptTime = receptTime;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	public Integer getRecessionTimeDiff() {
		return recessionTimeDiff;
	}
	public void setRecessionTimeDiff(Integer recessionTimeDiff) {
		this.recessionTimeDiff = recessionTimeDiff;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
