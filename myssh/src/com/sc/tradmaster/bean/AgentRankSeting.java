package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_agentrankset")
public class AgentRankSeting {

	private Integer setId;
	private String projectId;
	//0:顺序排;1:轮排;
	private Integer rankValue;
	//0:全部;//1:部分(手机号码搜索的接访，排序号不变)
	private Integer rankWay;
	
	@Id
	@GeneratedValue
	public Integer getSetId() {
		return setId;
	}
	public void setSetId(Integer setId) {
		this.setId = setId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Integer getRankValue() {
		return rankValue;
	}
	public void setRankValue(Integer rankValue) {
		this.rankValue = rankValue;
	}
	public Integer getRankWay() {
		return rankWay;
	}
	public void setRankWay(Integer rankWay) {
		this.rankWay = rankWay;
	}
	
	
}
