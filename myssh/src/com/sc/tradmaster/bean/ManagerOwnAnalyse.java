package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *经理个人编辑的分析标签
 * @author wjp 2017-06-01
 *
 */
@Entity
@Table(name="t_managerownanalyse")
public class ManagerOwnAnalyse {
	
	//自增主键
	private Integer ownAnalyseId;
	//经理id
	private String managerId;
	//经理Name
	private String managerName;
	//编辑的常用标签id
	private String chartDataId;
	//标签名字
	private String buttonText;
	//添加时间
	private String addTime;
	//预留的说明字段
	private String description;
	//预留的标签字段
	private String tags;
	//删除时间
	private String deleteTime;
	//状态(0:在用,1:删除)
	private Integer ownAnalyseStatus;
	//图标
	private String iconUrl;
	
	@Id
	@GeneratedValue
	public Integer getOwnAnalyseId() {
		return ownAnalyseId;
	}
	public void setOwnAnalyseId(Integer ownAnalyseId) {
		this.ownAnalyseId = ownAnalyseId;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getChartDataId() {
		return chartDataId;
	}
	public void setChartDataId(String chartDataId) {
		this.chartDataId = chartDataId;
	}
	public String getButtonText() {
		return buttonText;
	}
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}
	public Integer getOwnAnalyseStatus() {
		return ownAnalyseStatus;
	}
	public void setOwnAnalyseStatus(Integer ownAnalyseStatus) {
		this.ownAnalyseStatus = ownAnalyseStatus;
	}
	
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	@Override
	public String toString() {
		return "ManagerOwnAnalyse [ownAnalyseId=" + ownAnalyseId + ", managerId=" + managerId + ", managerName="
				+ managerName + ", chartDataId=" + chartDataId + ", buttonText=" + buttonText + ", addTime=" + addTime
				+ ", description=" + description + ", tags=" + tags + ", deleteTime=" + deleteTime
				+ ", ownAnalyseStatus=" + ownAnalyseStatus + ", iconUrl=" + iconUrl + "]";
	}
}
