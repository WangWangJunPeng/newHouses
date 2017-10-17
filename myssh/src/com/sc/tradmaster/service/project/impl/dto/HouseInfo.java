package com.sc.tradmaster.service.project.impl.dto;


/**
 * 封装房源楼盘表视图单个房源 信息
 * @author maoxy
 *
 */
public class HouseInfo {
    //房号
    private String houseNo;
    //单元
    private String unit;
    //所占行
    private Integer rowNum;
    //所占列
    private Integer colNum;
    //状态
    private Integer houseStatus;
    //房子的唯一编码
    private Integer houseNum;
    //排序号
    private Integer sortNum;
    
    
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public Integer getColNum() {
		return colNum;
	}
	public void setColNum(Integer colNum) {
		this.colNum = colNum;
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
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	public Integer getHouseStatus() {
		return houseStatus;
	}
	public void setHouseStatus(Integer houseStatus) {
		this.houseStatus = houseStatus;
	}
	public Integer getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}
    
    
    
}
