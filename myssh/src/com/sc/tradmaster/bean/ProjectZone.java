package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 案场分区
 * @author maoxy
 *
 */
@Entity
@Table(name="t_projectzone")
public class ProjectZone {
	//分区Id
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer zoneId;
	//案场Id
	private String projectId;
	//分区名称
	private String zoneName;
	//分区状态 0-删除,1-正常
	private Integer zoneStatus;
	
	public Integer getZoneStatus() {
		return zoneStatus;
	}
	public void setZoneStatus(Integer zoneStatus) {
		this.zoneStatus = zoneStatus;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
	
}
