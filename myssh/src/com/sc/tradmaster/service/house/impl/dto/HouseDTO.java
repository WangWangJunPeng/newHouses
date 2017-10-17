package com.sc.tradmaster.service.house.impl.dto;

import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectBuilding;
import com.sc.tradmaster.bean.ProjectBuildingUnit;

public class HouseDTO {
	/**
	 * 数据库房源和导入房源信息更新
	 * @param oldh
	 * @param newh
	 * @return
	 */
	public static House assignmentHouseProperty(House oldh,House newh){
		oldh.setDirect(newh.getDirect());
		oldh.setHousePropertyType(newh.getHousePropertyType());
		oldh.setPropertyYear(newh.getPropertyYear());
		oldh.setPreBuildArea(newh.getPreBuildArea());
		oldh.setBuildArea(newh.getBuildArea());
		oldh.setListPrice(newh.getListPrice());
		oldh.setInsideSpace(newh.getInsideSpace());
		oldh.setSharedArea(newh.getSharedArea());
		oldh.setFloorArea(newh.getFloorArea());
		oldh.setNovolumeArea(newh.getNovolumeArea());
		oldh.setTakeArea(newh.getTakeArea());
		oldh.setTakeAreaProportion(newh.getTakeAreaProportion());
		oldh.setPresalePermissionInfo(newh.getPresalePermissionInfo());
		oldh.setHouseTypePosition(newh.getHouseTypePosition());
		oldh.setAreaWide(newh.getAreaWide());
		oldh.setLightDirection(newh.getLightDirection());
		oldh.setMaxDeep(newh.getMaxDeep());
		oldh.setFloorHeight(newh.getFloorHeight());
		oldh.setLightTime(newh.getLightTime());
		oldh.setRenovationStandard(newh.getRenovationStandard());
		oldh.setSellType(newh.getSellType());
		return oldh;
	}
	/**
	 * 数据库楼栋和导入楼栋信息更新
	 * @param oldpb
	 * @param newpb
	 * @return
	 */
	public static ProjectBuilding assignmentProjectBuildingProperty(ProjectBuilding oldpb,ProjectBuilding newpb){
		oldpb.setBuildingDirection(newpb.getBuildingDirection());
		oldpb.setConstitute(newpb.getConstitute());
		oldpb.setIsToBasement(newpb.getIsToBasement());
		oldpb.setEmptySpace(newpb.getEmptySpace());
		oldpb.setHavaNonMotorGarage(newpb.getHavaNonMotorGarage());
		oldpb.setHavaAnnex(newpb.getHavaAnnex());
		oldpb.setBuildingPosition(newpb.getBuildingPosition());
		return oldpb;
	}
	/**
	 * 数据库单元和导入单元信息更新
	 * @param oldpbu
	 * @param newpbu
	 * @return
	 */
	public static ProjectBuildingUnit assignmentProjectBuildingUnitProperty(ProjectBuildingUnit oldpbu,ProjectBuildingUnit newpbu) {
		oldpbu.setLiftNum(newpbu.getLiftNum());
		oldpbu.setLiftConstitute(newpbu.getLiftConstitute());
		oldpbu.setUnitUser(newpbu.getUnitUser());
		oldpbu.setFloorNum(newpbu.getFloorNum());
		oldpbu.setUnitStatus(newpbu.getUnitStatus());
		oldpbu.setUnitEfficiencyRate(newpbu.getUnitEfficiencyRate());
		return oldpbu;
	}
	/**
	 * 数据库案场和导入案场信息更新
	 * @param oldp
	 * @param newp
	 * @return
	 */
	public static Project assignmentProjectProperty(Project oldp,Project newp){
		oldp.setImportanceMsg(newp.getImportanceMsg());
		oldp.setTypeid(newp.getTypeid());
		oldp.setDevelopmenStatus(newp.getDevelopmenStatus());
		oldp.setDesignCompany(newp.getDesignCompany());
		oldp.setConstructionCompany(newp.getConstructionCompany());
		oldp.setProInSystemStutas(newp.getProInSystemStutas());
		oldp.setParking(newp.getParking());
		oldp.setOwnerClubArea(newp.getOwnerClubArea());
		oldp.setOwnerGym(newp.getOwnerGym());
		oldp.setOwnerCommunityCenter(newp.getOwnerCommunityCenter());
		oldp.setOwnerSwimmingPool(newp.getOwnerSwimmingPool());
		oldp.setOwnerSchool(newp.getOwnerSchool());
		oldp.setOwnerKindergarten(newp.getOwnerKindergarten());
		oldp.setOwnerBusiness(newp.getOwnerBusiness());
		oldp.setOwnerOther(newp.getOwnerOther());
		oldp.setBusiness(newp.getBusiness());
		oldp.setEducation(newp.getEducation());
		oldp.setFinancial(newp.getFinancial());
		oldp.setTransportation(newp.getTransportation());
		oldp.setMedical(newp.getMedical());
		oldp.setOther(newp.getOther());
		return oldp;
	}
}
