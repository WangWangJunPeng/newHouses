package com.sc.tradmaster.service.project.impl.dto;
/**
 * 封装单元信息
 */
public class UnitInfo {
	//单元名称
	private String unitName;
	//单元户数
	private Integer unitUser;
	//单元排序号
	private Integer unitSortNum;
	
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getUnitUser() {
		return unitUser;
	}
	public void setUnitUser(Integer unitUser) {
		this.unitUser = unitUser;
	}
	public Integer getUnitSortNum() {
		return unitSortNum;
	}
	public void setUnitSortNum(Integer unitSortNum) {
		this.unitSortNum = unitSortNum;
	}
	
}
