package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_projectbuilding")
public class ProjectBuilding {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//楼栋Id
	private Integer buildingId;
	//楼栋名称
	private String buildingName;
	//分区Id
	private Integer zoneId;
	//楼栋状态 0-删除,1-正常
	private Integer buildingStatus;
	//楼栋朝向  1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
	private Integer buildingDirection;
	//结构形式             框架/框剪/钢构
	private String constitute;
	//是否直连地库 0-否,1-是
	private Integer isToBasement;
	//架空层数,0表示没有架空层
	private Integer emptySpace;
	//是否含有非机动车库0-无,1-有
	private Integer havaNonMotorGarage;
	//有无裙房0-无,1-有
	private Integer havaAnnex;
	//楼栋具体位置
	private String buildingPosition;
	
	
	
	public String getBuildingPosition() {
		return buildingPosition;
	}
	public void setBuildingPosition(String buildingPosition) {
		this.buildingPosition = buildingPosition;
	}
	public Integer getIsToBasement() {
		return isToBasement;
	}
	public void setIsToBasement(Integer isToBasement) {
		this.isToBasement = isToBasement;
	}
	public Integer getEmptySpace() {
		return emptySpace;
	}
	public void setEmptySpace(Integer emptySpace) {
		this.emptySpace = emptySpace;
	}
	public Integer getHavaNonMotorGarage() {
		return havaNonMotorGarage;
	}
	public void setHavaNonMotorGarage(Integer havaNonMotorGarage) {
		this.havaNonMotorGarage = havaNonMotorGarage;
	}
	public Integer getHavaAnnex() {
		return havaAnnex;
	}
	public void setHavaAnnex(Integer havaAnnex) {
		this.havaAnnex = havaAnnex;
	}
	public String getConstitute() {
		return constitute;
	}
	public void setConstitute(String constitute) {
		this.constitute = constitute;
	}
	public Integer getBuildingDirection() {
		return buildingDirection;
	}
	public void setBuildingDirection(Integer buildingDirection) {
		this.buildingDirection = buildingDirection;
	}
	public Integer getBuildingStatus() {
		return buildingStatus;
	}
	public void setBuildingStatus(Integer buildingStatus) {
		this.buildingStatus = buildingStatus;
	}
	public Integer getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	
	
	
}
