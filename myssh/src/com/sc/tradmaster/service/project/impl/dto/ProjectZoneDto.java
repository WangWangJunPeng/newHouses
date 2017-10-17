package com.sc.tradmaster.service.project.impl.dto;

import java.util.List;

import com.sc.tradmaster.bean.ProjectBuilding;

public class ProjectZoneDto {
	//分区名称
	private String zoneName;
	private Integer zoneId;
	private List<ProjectBuilding> projectBuildingList;
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	public List<ProjectBuilding> getProjectBuildingList() {
		return projectBuildingList;
	}
	public void setProjectBuildingList(List<ProjectBuilding> projectBuildingList) {
		this.projectBuildingList = projectBuildingList;
	}
	
}
