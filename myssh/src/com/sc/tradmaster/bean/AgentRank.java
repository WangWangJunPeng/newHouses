package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 职业顾问排序
 * @author grl
 *
 */
@Entity
@Table(name="t_agentrank")
public class AgentRank {
	//id
	private int agentRankId;
	//案场id
	private String projectId;
	//职业顾问id
	private String userId;
	//签到id
	private Integer recordId;
	//签到时间
	private String signInTime;
	//工作状态(0:工作;1:休息)
	private Integer workingStatus;
	//签到接访状态(0:签到;1:接访;2:送别;3:签退)
	private Integer signInVisitStatus;
	//到访id
	private Integer visitNo;
	//接访时间
	private String arriveTime;
	//送别时间
	private String leaveTime;
	//是否是指定接访(0:否;1是)
	private Integer isAppointVisit;
	//排序号
	private Integer rankNumber;
	//组别
	private Integer groupNum;
	
	
	@Id
	@GeneratedValue
	public int getAgentRankId() {
		return agentRankId;
	}
	public void setAgentRankId(int agentRankId) {
		this.agentRankId = agentRankId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}
	public Integer getWorkingStatus() {
		return workingStatus;
	}
	public void setWorkingStatus(Integer workingStatus) {
		this.workingStatus = workingStatus;
	}
	public Integer getSignInVisitStatus() {
		return signInVisitStatus;
	}
	public void setSignInVisitStatus(Integer signInVisitStatus) {
		this.signInVisitStatus = signInVisitStatus;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public Integer getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
	}
	public Integer getIsAppointVisit() {
		return isAppointVisit;
	}
	public void setIsAppointVisit(Integer isAppointVisit) {
		this.isAppointVisit = isAppointVisit;
	}
	public Integer getRankNumber() {
		return rankNumber;
	}
	public void setRankNumber(Integer rankNumber) {
		this.rankNumber = rankNumber;
	}
	public Integer getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}
	
	
}
