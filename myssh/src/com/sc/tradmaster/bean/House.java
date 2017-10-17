package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author wjp 2017-01-09
 */
@Entity
@Table(name = "t_projecthouses")
public class House {
    //房源编号
    private String houseId;
    //案场编号
    private String projectId;
    //房号
    private String houseNo;
    //区位名称
    private String district;
    //区位号Id
    private Integer districtId;
    //楼栋名称
    private String buildingNo;
    //楼栋号Id
    private Integer buildingId;
    //(房源类型，0:公寓、1:排屋、2:独栋、3:商住两用、4:办公室、5:酒店式公寓、6:商铺、7:车位、8:车库、9:储藏室)
    private Integer houseKind;
    //单元
    private String unit;
    //楼层
    private Integer floor;
    //朝向
    private String direct;
    //建筑面积
    private Double buildArea;
    private String buildAreaStr;
    //使用面积
    private Double usefulArea;
    private String usefulAreaStr;
    //列表价
    private Double listPrice;
    private String listPriceStr;
    //底价
    private Double minimumPrice;
    private String minimumPriceStr;
    //中介供货价
    private Double shopPrice;
    private String shopPriceStr;
    //户型编码
    private String houseTypeId;
    //户型名称
    private String houseTypeName;
    //(房源状态-0：下架；1：上架；2：删除；3：撤销；4：在售中；5：已售)
    //新改的_______________________________________0:销控,1:在售,2:删除,3:撤销,4:认购待审核5:待付款,6付款待确认,7:待签约,8已签约
    private Integer houseStatus;
    //装修标准(0：毛坯；1：普通装修；2：精装修；3：家具全配；4：家电全配)
    private Integer decorationStandard;
    //备用的说明性字段
    private String description;
    //标签字段
    private String tags;
    //户型图片URL
    private String photos;
    //发布时间
    private String shelvTime;

    private double rewardMoney;   //数据库表里没有这个字段,,,,
    //(经纪人是否可见0:不可见;1:可见)
    private Integer isOpen;
    //房子的唯一编码
    private Integer houseNum;
    //预售证信息
    private String presalePermissionInfo;
    //最佳优惠组合
    private String bestBenefitsId;
//--------------------------------------------房源生成添加字段--------------------------------------------------//
    //单元Id
    private Integer unitId;
    //排序号,用户楼盘视图显示房源
    private Integer sortNum;
    //物业类型
    //1-别墅,2-小高层,3-高层,4-超高层,5-叠排,6-多层,7-排屋,8-商业街,9-集中商业,10-酒店公寓,11-底商,12-soho,13-写字楼
    private Integer housePropertyType;
    //产权年限
    private String propertyYear;
    //产权到期时间 	2017-03-19 yyyy-MM-dd
    private String propertyExpireTime;
    //建筑面积(预测)
    private String preBuildArea;
    //套内面积
    private Double insideSpace;
    //公摊面积
    private Double sharedArea;
    //占地面积
    private Double floorArea;
    //非容积面积
    private Double novolumeArea;
    //实得面积
    private Double takeArea;
    //实得面积比   直接存%
    private String takeAreaProportion;
    //户型位置    1-边套,2-中间套
    private Integer houseTypePosition;
    //总面宽 单位米
    private Double areaWide;
    //采光面     1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer lightDirection;
    //最大进深 单位米
    private Double maxDeep;
    //层高
    private Double floorHeight;
    //日照时间 单位小时
    private Double lightTime;
    //装修标准 1-精装 2-毛坯
    private Integer renovationStandard;
    //租售类型 1-出租2-出售3-租售
    private Integer sellType;
    //所占行
    private Integer rowNum;
    //所占列
    private Integer colNum;
    //房屋用途  
    private Integer natureOfProperty;
    
    
    

	public Integer getNatureOfProperty() {
		return natureOfProperty;
	}

	public void setNatureOfProperty(Integer natureOfProperty) {
		this.natureOfProperty = natureOfProperty;
	}

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

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getHousePropertyType() {
		return housePropertyType;
	}

	public void setHousePropertyType(Integer housePropertyType) {
		this.housePropertyType = housePropertyType;
	}

	public String getPropertyYear() {
		return propertyYear;
	}

	public void setPropertyYear(String propertyYear) {
		this.propertyYear = propertyYear;
	}

	public String getPropertyExpireTime() {
		return propertyExpireTime;
	}

	public void setPropertyExpireTime(String propertyExpireTime) {
		this.propertyExpireTime = propertyExpireTime;
	}

	public String getPreBuildArea() {
		return preBuildArea;
	}

	public void setPreBuildArea(String preBuildArea) {
		this.preBuildArea = preBuildArea;
	}

	public Double getInsideSpace() {
		return insideSpace;
	}

	public void setInsideSpace(Double insideSpace) {
		this.insideSpace = insideSpace;
	}

	public Double getSharedArea() {
		return sharedArea;
	}

	public void setSharedArea(Double sharedArea) {
		this.sharedArea = sharedArea;
	}

	public Double getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Double floorArea) {
		this.floorArea = floorArea;
	}


	public Double getNovolumeArea() {
		return novolumeArea;
	}

	public void setNovolumeArea(Double novolumeArea) {
		this.novolumeArea = novolumeArea;
	}

	public Double getTakeArea() {
		return takeArea;
	}

	public void setTakeArea(Double takeArea) {
		this.takeArea = takeArea;
	}

	public String getTakeAreaProportion() {
		return takeAreaProportion;
	}

	public void setTakeAreaProportion(String takeAreaProportion) {
		this.takeAreaProportion = takeAreaProportion;
	}

	public Integer getHouseTypePosition() {
		return houseTypePosition;
	}

	public void setHouseTypePosition(Integer houseTypePosition) {
		this.houseTypePosition = houseTypePosition;
	}

	public Double getAreaWide() {
		return areaWide;
	}

	public void setAreaWide(Double areaWide) {
		this.areaWide = areaWide;
	}

	public Integer getLightDirection() {
		return lightDirection;
	}

	public void setLightDirection(Integer lightDirection) {
		this.lightDirection = lightDirection;
	}

	public Double getMaxDeep() {
		return maxDeep;
	}

	public void setMaxDeep(Double maxDeep) {
		this.maxDeep = maxDeep;
	}

	public Double getFloorHeight() {
		return floorHeight;
	}

	public void setFloorHeight(Double floorHeight) {
		this.floorHeight = floorHeight;
	}

	public Double getLightTime() {
		return lightTime;
	}

	public void setLightTime(Double lightTime) {
		this.lightTime = lightTime;
	}

	public Integer getRenovationStandard() {
		return renovationStandard;
	}

	public void setRenovationStandard(Integer renovationStandard) {
		this.renovationStandard = renovationStandard;
	}

	public Integer getSellType() {
		return sellType;
	}

	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}

	public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public Integer getHouseKind() {
        return houseKind;
    }

    public void setHouseKind(Integer houseKind) {
        this.houseKind = houseKind;
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

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public Double getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(Double buildArea) {
        this.buildArea = buildArea;
    }

    public Double getUsefulArea() {
        return usefulArea;
    }

    public void setUsefulArea(Double usefulArea) {
        this.usefulArea = usefulArea;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public Double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public Double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getHouseTypeId() {
        return houseTypeId;
    }

    public void setHouseTypeId(String houseTypeId) {
        this.houseTypeId = houseTypeId;
    }

    public Integer getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(Integer houseStatus) {
        this.houseStatus = houseStatus;
    }

    public Integer getDecorationStandard() {
        return decorationStandard;
    }

    public void setDecorationStandard(Integer decorationStandard) {
        this.decorationStandard = decorationStandard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getShelvTime() {
        return shelvTime;
    }

    public void setShelvTime(String shelvTime) {
        this.shelvTime = shelvTime;
    }

    public double getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(double rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    @Id
    @GeneratedValue
    public Integer getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }

    public String getPresalePermissionInfo() {
        return presalePermissionInfo;
    }

    public void setPresalePermissionInfo(String presalePermissionInfo) {
        this.presalePermissionInfo = presalePermissionInfo;
    }

    public String getBestBenefitsId() {
        return bestBenefitsId;
    }

    public void setBestBenefitsId(String bestBenefitsId) {
        this.bestBenefitsId = bestBenefitsId;
    }

    @Transient
    public String getHouseTypeName() {
		return houseTypeName;
	}

	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
	}

	@Transient
	public String getBuildAreaStr() {
		return buildAreaStr;
	}

	public void setBuildAreaStr(String buildAreaStr) {
		this.buildAreaStr = buildAreaStr;
	}

	@Transient
	public String getUsefulAreaStr() {
		return usefulAreaStr;
	}

	public void setUsefulAreaStr(String usefulAreaStr) {
		this.usefulAreaStr = usefulAreaStr;
	}

	@Transient
	public String getListPriceStr() {
		return listPriceStr;
	}

	public void setListPriceStr(String listPriceStr) {
		this.listPriceStr = listPriceStr;
	}

	@Transient
	public String getMinimumPriceStr() {
		return minimumPriceStr;
	}

	public void setMinimumPriceStr(String minimumPriceStr) {
		this.minimumPriceStr = minimumPriceStr;
	}

	@Transient
	public String getShopPriceStr() {
		return shopPriceStr;
	}

	public void setShopPriceStr(String shopPriceStr) {
		this.shopPriceStr = shopPriceStr;
	}

	@Override
    public String toString() {
        return "House [houseId=" + houseId + ", projectId=" + projectId + ", houseNo=" + houseNo + ", district="
                + district + ", buildingNo=" + buildingNo + ", houseKind=" + houseKind + ", unit=" + unit + ", floor="
                + floor + ", direct=" + direct + ", buildArea=" + buildArea + ", usefulArea=" + usefulArea
                + ", listPrice=" + listPrice + ", minimumPrice=" + minimumPrice + ", shopPrice=" + shopPrice
                + ", houseTypeId=" + houseTypeId + ", houseStatus=" + houseStatus + ", decorationStandard="
                + decorationStandard + ", description=" + description + ", tags=" + tags + ", photos=" + photos
                + ", shelvTime=" + shelvTime + ", rewardMoney=" + rewardMoney + ", isOpen=" + isOpen + ", houseNum="
                + houseNum + ", presalePermissionInfo=" + presalePermissionInfo + ", bestBenefitsId=" + bestBenefitsId
                + "]";
    }

}
