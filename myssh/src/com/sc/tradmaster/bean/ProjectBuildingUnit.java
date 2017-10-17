package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 单元表
 * @author Administrator
 *
 */
@Entity
@Table(name="t_projectbuildingunit")
public class ProjectBuildingUnit {
	//单元Id
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer unitId;
	//单元名称
	private String unitName;
	//楼栋Id
	private Integer buildingId;
	//电梯数
	private Integer liftNum;
	//电梯材质
	private String liftConstitute;
	//单元每层户数
	private Integer unitUser;
	//单元楼层数
	private Integer floorNum;
	//单元状态0-删除,1-存在
	private Integer unitStatus;
	//单元得房率直接存%
	private String unitEfficiencyRate;
	
	
	
	public String getUnitEfficiencyRate() {
		return unitEfficiencyRate;
	}
	public void setUnitEfficiencyRate(String unitEfficiencyRate) {
		this.unitEfficiencyRate = unitEfficiencyRate;
	}
	public Integer getUnitStatus() {
		return unitStatus;
	}
	public void setUnitStatus(Integer unitStatus) {
		this.unitStatus = unitStatus;
	}
	public Integer getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}
	public Integer getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getLiftNum() {
		return liftNum;
	}
	public void setLiftNum(Integer liftNum) {
		this.liftNum = liftNum;
	}
	public String getLiftConstitute() {
		return liftConstitute;
	}
	public void setLiftConstitute(String liftConstitute) {
		this.liftConstitute = liftConstitute;
	}
	public Integer getUnitUser() {
		return unitUser;
	}
	public void setUnitUser(Integer unitUser) {
		this.unitUser = unitUser;
	}
	
	
}
