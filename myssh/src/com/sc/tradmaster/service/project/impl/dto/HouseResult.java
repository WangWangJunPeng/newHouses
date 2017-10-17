package com.sc.tradmaster.service.project.impl.dto;

import java.util.List;
import java.util.Map;
/**
 * 房源管理返回数据结构
 * @author maoxy
 *
 */
public class HouseResult {
	private List<UnitInfo> unitInfos;
	private Map<String,List<HouseInfo>> allHouse;
	
	public List<UnitInfo> getUnitInfos() {
		return unitInfos;
	}
	public void setUnitInfos(List<UnitInfo> unitInfos) {
		this.unitInfos = unitInfos;
	}
	public Map<String, List<HouseInfo>> getAllHouse() {
		return allHouse;
	}
	public void setAllHouse(Map<String, List<HouseInfo>> allHouse) {
		this.allHouse = allHouse;
	}
	

}
