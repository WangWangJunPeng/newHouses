package com.sc.tradmaster.service.project.impl.dto;

import java.util.List;

import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectBenefits;
import com.sc.tradmaster.bean.ProjectBuilding;
import com.sc.tradmaster.bean.ProjectBuildingUnit;

/**
 * 一房一档房源详情
 * @author Administrator
 *
 */
public class ProjectHouseInfo {
	private Project project;
	private House house;
	private HouseType houseType;
	private ProjectBuildingUnit projectBuildingUnit;
	private ProjectBuilding projectBuilding;
	private List<ProjectBenefits> projectBenefits;
	//房源标签
	private List<String> houseTags;
	//项目标签
	private List<String> projectTags;
	//折后单价
	private String priceMin;
	//折后总价
	private String totalPriceMin;
	//单元总户数
	private Integer unitTotal;
	//单元总数
	private Integer unitNum;
	//楼栋总户数
	private Integer buildingTotal;
	//车位比 1：？
	private String parkingRate;
	//效果图集合
	private List<String> projectEffectPic;
	
	
	public List<String> getProjectEffectPic() {
		return projectEffectPic;
	}
	public void setProjectEffectPic(List<String> projectEffectPic) {
		this.projectEffectPic = projectEffectPic;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public HouseType getHouseType() {
		return houseType;
	}
	public void setHouseType(HouseType houseType) {
		this.houseType = houseType;
	}
	public ProjectBuildingUnit getProjectBuildingUnit() {
		return projectBuildingUnit;
	}
	public void setProjectBuildingUnit(ProjectBuildingUnit projectBuildingUnit) {
		this.projectBuildingUnit = projectBuildingUnit;
	}
	public ProjectBuilding getProjectBuilding() {
		return projectBuilding;
	}
	public void setProjectBuilding(ProjectBuilding projectBuilding) {
		this.projectBuilding = projectBuilding;
	}
	public List<String> getHouseTags() {
		return houseTags;
	}
	public void setHouseTags(List<String> houseTags) {
		this.houseTags = houseTags;
	}
	public List<String> getProjectTags() {
		return projectTags;
	}
	public void setProjectTags(List<String> projectTags) {
		this.projectTags = projectTags;
	}
	public String getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(String priceMin) {
		this.priceMin = priceMin;
	}
	public String getTotalPriceMin() {
		return totalPriceMin;
	}
	public void setTotalPriceMin(String totalPriceMin) {
		this.totalPriceMin = totalPriceMin;
	}
	public Integer getUnitTotal() {
		return unitTotal;
	}
	public void setUnitTotal(Integer unitTotal) {
		this.unitTotal = unitTotal;
	}
	public Integer getUnitNum() {
		return unitNum;
	}
	public void setUnitNum(Integer unitNum) {
		this.unitNum = unitNum;
	}
	public Integer getBuildingTotal() {
		return buildingTotal;
	}
	public void setBuildingTotal(Integer buildingTotal) {
		this.buildingTotal = buildingTotal;
	}
	public String getParkingRate() {
		return parkingRate;
	}
	public void setParkingRate(String parkingRate) {
		this.parkingRate = parkingRate;
	}
	public List<ProjectBenefits> getProjectBenefits() {
		return projectBenefits;
	}
	public void setProjectBenefits(List<ProjectBenefits> projectBenefits) {
		this.projectBenefits = projectBenefits;
	}


	

}