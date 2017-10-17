package com.sc.tradmaster.service.project.impl.dto;
/**
 * 房源信息json格式
 * @author maoxy
 *
 */
public class HouseJson {
	//房号
	private String houseNo;
    //单元
    private String unit;
    //楼层
    private Integer floor;
    //户型编码
    private String houseTypeId;
    //排序号
    private Integer sortNum;
    
    
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public String getHouseTypeId() {
		return houseTypeId;
	}
	public void setHouseTypeId(String houseTypeId) {
		this.houseTypeId = houseTypeId;
	}
    

}
