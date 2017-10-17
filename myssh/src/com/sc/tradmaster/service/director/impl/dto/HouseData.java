package com.sc.tradmaster.service.director.impl.dto;

public class HouseData implements Comparable<HouseData>{

	private Integer soldNum;
	private Double buildArea;
	private String houseTypeId;
	private String houseTypeName;
	
	public Integer getSoldNum() {
		return soldNum;
	}
	public void setSoldNum(Integer soldNum) {
		this.soldNum = soldNum;
	}
	public Double getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}
	public String getHouseTypeId() {
		return houseTypeId;
	}
	public void setHouseTypeId(String houseTypeId) {
		this.houseTypeId = houseTypeId;
	}
	public String getHouseTypeName() {
		return houseTypeName;
	}
	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
	}
	
	@Override
	public String toString() {
		return "HouseData [soldNum=" + soldNum + ", buildArea=" + buildArea + ", houseTypeId=" + houseTypeId
				+ ", houseTypeName=" + houseTypeName + "]";
	}
	@Override
	public int compareTo(HouseData o) {
		
		return (int) this.buildArea.intValue() - (Integer) o.buildArea.intValue();
	}
	
	
	
}
