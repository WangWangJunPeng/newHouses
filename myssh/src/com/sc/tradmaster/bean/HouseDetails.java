package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 房源详情
 * @author maoxy
 *
 */
@Entity
@Table(name="t_housedetails")
public class HouseDetails {
    //房源详情Id
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer houseDetailsId;
	//房源Id
	private Integer houseNum;
    //主卧1尺寸
    private String masterBedroomSize;
    //主卧1朝向 ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer masterBedroomDirection;
    //主卧2尺寸
    private String secondMasterBedroomSize;
    //主卧2朝向  ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer secondMasterBedroomDirection;
    //次卧尺寸
    private String bedroomSize;
    //次卧朝向 ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer bedroomDirection;
    //次卧2尺寸
    private String secondBedroomSize;
    //次卧2朝向 ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer secondBedroomDirection;
    //书房尺寸
    private String schoolroomSize; 
    //书房朝向 ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer schoolroomDirection; 
    //餐厅尺寸
    private String diningRoomSize;
    //餐厅朝向 ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer diningRoomDirection;
    //客厅尺寸
    private String livingRoomSize;
    //客厅朝向 ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer livingRoomDirection;
    //卫生间1尺寸
    private String bathroomSizeOne;
    //卫生间1明暗 ---- 0-暗,1-明
    private Integer bathroomLightOne;
    //卫生间2尺寸
    private String bathroomSizeTwo;
    //卫生间2明暗 ---- 0-暗,1-明
    private Integer bathroomLightTwo;
    //卫生间3尺寸
    private String bathroomSizeThree;
    //卫生间3明暗 ---- 0-暗,1-明
    private Integer bathroomLightThree;
    //卫生间4尺寸
    private String bathroomSizeFour;
    //卫生间4明暗 ---- 0-暗,1-明
    private Integer bathroomLightFour;
    //卫生间5尺寸
    private String bathroomSizeFive;
    //卫生间5明暗 ---- 0-暗,1-明
    private Integer bathroomLightFive;
    //厨房尺寸
    private String kitchenSize; 
    //工人房尺寸
    private String maidBedroomSize;
    //工人房明暗 ----0-暗,1-明
    private Integer maidBedroomLight;
    //洗衣房尺寸
    private String laundryRoomSize;
    //阳台1尺寸
    private String balconySizeOne;
    //阳台1朝向 ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer balconyDirectionOne;
    //阳台2尺寸
    private String balconySizeTwo;
    //阳台2朝向 ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer balconyDirectionTwo;
    //阳台3尺寸
    private String balconySizeThree;
    //阳台3朝向 ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer balconyDirectionThree;
    //设备平台尺寸
    private String deviceStageSize;
    //设备平台朝向
    private Integer deviceStageDirection;
    //储藏间尺寸
    private String boxRoomSize;
    //露台1尺寸
    private String gazeboSizeOne;
    //露台1朝向---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer gazeboDirectionOne;
    //露台2尺寸
    private String gazeboSizeTwo;
    //露台2朝向---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer gazeboDirectionTwo;
    //阁楼面积
    private Double penthouseArea;
    //层高
    private Double maxPenthouseHeight;
    //入户花园面积
    private Double indoorGardenArea;
    //花园1面积
    private Double gardenAreaOne;
    //花园1朝向 ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer gardenAreaOneDirection;
    //花园2面积
    private Double gardenAreaTwo;
    //花园2朝向 ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer gardenAreaTwoDirection;
    //半地下室面积
    private Double harfBasementArea;
    //半地下室朝向 ---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer harfBasementDirection;
    //地下室1面积
    private Double basementAreaOne;
    //地下室1朝向---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer basementDirectionOne;
    //地下室1明暗----0-暗,1-明
    private Integer basementLightOne;
    //地下室2面积
    private Double basementAreaTwo;
    //地下室2朝向---- 1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北
    private Integer basementDirectionTwo;
    //地下室2明暗----0-暗,1-明
    private Integer basementLightTwo;
    //采光井/通风井 尺寸
    private String lightWellSize;
    //观景角度
    private String viewingAngle;
    
    
	public Integer getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}
	public Integer getHouseDetailsId() {
		return houseDetailsId;
	}
	public void setHouseDetailsId(Integer houseDetailsId) {
		this.houseDetailsId = houseDetailsId;
	}
	public String getMasterBedroomSize() {
		return masterBedroomSize;
	}
	public void setMasterBedroomSize(String masterBedroomSize) {
		this.masterBedroomSize = masterBedroomSize;
	}
	public Integer getMasterBedroomDirection() {
		return masterBedroomDirection;
	}
	public void setMasterBedroomDirection(Integer masterBedroomDirection) {
		this.masterBedroomDirection = masterBedroomDirection;
	}
	public String getSecondMasterBedroomSize() {
		return secondMasterBedroomSize;
	}
	public void setSecondMasterBedroomSize(String secondMasterBedroomSize) {
		this.secondMasterBedroomSize = secondMasterBedroomSize;
	}
	public Integer getSecondMasterBedroomDirection() {
		return secondMasterBedroomDirection;
	}
	public void setSecondMasterBedroomDirection(Integer secondMasterBedroomDirection) {
		this.secondMasterBedroomDirection = secondMasterBedroomDirection;
	}
	public String getBedroomSize() {
		return bedroomSize;
	}
	public void setBedroomSize(String bedroomSize) {
		this.bedroomSize = bedroomSize;
	}
	public Integer getBedroomDirection() {
		return bedroomDirection;
	}
	public void setBedroomDirection(Integer bedroomDirection) {
		this.bedroomDirection = bedroomDirection;
	}
	public String getSecondBedroomSize() {
		return secondBedroomSize;
	}
	public void setSecondBedroomSize(String secondBedroomSize) {
		this.secondBedroomSize = secondBedroomSize;
	}
	public Integer getSecondBedroomDirection() {
		return secondBedroomDirection;
	}
	public void setSecondBedroomDirection(Integer secondBedroomDirection) {
		this.secondBedroomDirection = secondBedroomDirection;
	}
	public String getSchoolroomSize() {
		return schoolroomSize;
	}
	public void setSchoolroomSize(String schoolroomSize) {
		this.schoolroomSize = schoolroomSize;
	}
	public Integer getSchoolroomDirection() {
		return schoolroomDirection;
	}
	public void setSchoolroomDirection(Integer schoolroomDirection) {
		this.schoolroomDirection = schoolroomDirection;
	}
	public String getDiningRoomSize() {
		return diningRoomSize;
	}
	public void setDiningRoomSize(String diningRoomSize) {
		this.diningRoomSize = diningRoomSize;
	}
	public Integer getDiningRoomDirection() {
		return diningRoomDirection;
	}
	public void setDiningRoomDirection(Integer diningRoomDirection) {
		this.diningRoomDirection = diningRoomDirection;
	}
	public String getLivingRoomSize() {
		return livingRoomSize;
	}
	public void setLivingRoomSize(String livingRoomSize) {
		this.livingRoomSize = livingRoomSize;
	}
	public Integer getLivingRoomDirection() {
		return livingRoomDirection;
	}
	public void setLivingRoomDirection(Integer livingRoomDirection) {
		this.livingRoomDirection = livingRoomDirection;
	}
	public String getBathroomSizeOne() {
		return bathroomSizeOne;
	}
	public void setBathroomSizeOne(String bathroomSizeOne) {
		this.bathroomSizeOne = bathroomSizeOne;
	}
	public Integer getBathroomLightOne() {
		return bathroomLightOne;
	}
	public void setBathroomLightOne(Integer bathroomLightOne) {
		this.bathroomLightOne = bathroomLightOne;
	}
	public String getBathroomSizeTwo() {
		return bathroomSizeTwo;
	}
	public void setBathroomSizeTwo(String bathroomSizeTwo) {
		this.bathroomSizeTwo = bathroomSizeTwo;
	}
	public Integer getBathroomLightTwo() {
		return bathroomLightTwo;
	}
	public void setBathroomLightTwo(Integer bathroomLightTwo) {
		this.bathroomLightTwo = bathroomLightTwo;
	}
	public String getBathroomSizeThree() {
		return bathroomSizeThree;
	}
	public void setBathroomSizeThree(String bathroomSizeThree) {
		this.bathroomSizeThree = bathroomSizeThree;
	}
	public Integer getBathroomLightThree() {
		return bathroomLightThree;
	}
	public void setBathroomLightThree(Integer bathroomLightThree) {
		this.bathroomLightThree = bathroomLightThree;
	}
	public String getBathroomSizeFour() {
		return bathroomSizeFour;
	}
	public void setBathroomSizeFour(String bathroomSizeFour) {
		this.bathroomSizeFour = bathroomSizeFour;
	}
	public Integer getBathroomLightFour() {
		return bathroomLightFour;
	}
	public void setBathroomLightFour(Integer bathroomLightFour) {
		this.bathroomLightFour = bathroomLightFour;
	}
	public String getBathroomSizeFive() {
		return bathroomSizeFive;
	}
	public void setBathroomSizeFive(String bathroomSizeFive) {
		this.bathroomSizeFive = bathroomSizeFive;
	}
	public Integer getBathroomLightFive() {
		return bathroomLightFive;
	}
	public void setBathroomLightFive(Integer bathroomLightFive) {
		this.bathroomLightFive = bathroomLightFive;
	}
	public String getKitchenSize() {
		return kitchenSize;
	}
	public void setKitchenSize(String kitchenSize) {
		this.kitchenSize = kitchenSize;
	}
	public String getMaidBedroomSize() {
		return maidBedroomSize;
	}
	public void setMaidBedroomSize(String maidBedroomSize) {
		this.maidBedroomSize = maidBedroomSize;
	}
	public Integer getMaidBedroomLight() {
		return maidBedroomLight;
	}
	public void setMaidBedroomLight(Integer maidBedroomLight) {
		this.maidBedroomLight = maidBedroomLight;
	}
	public String getLaundryRoomSize() {
		return laundryRoomSize;
	}
	public void setLaundryRoomSize(String laundryRoomSize) {
		this.laundryRoomSize = laundryRoomSize;
	}
	public String getBalconySizeOne() {
		return balconySizeOne;
	}
	public void setBalconySizeOne(String balconySizeOne) {
		this.balconySizeOne = balconySizeOne;
	}
	public Integer getBalconyDirectionOne() {
		return balconyDirectionOne;
	}
	public void setBalconyDirectionOne(Integer balconyDirectionOne) {
		this.balconyDirectionOne = balconyDirectionOne;
	}
	public String getBalconySizeTwo() {
		return balconySizeTwo;
	}
	public void setBalconySizeTwo(String balconySizeTwo) {
		this.balconySizeTwo = balconySizeTwo;
	}
	public Integer getBalconyDirectionTwo() {
		return balconyDirectionTwo;
	}
	public void setBalconyDirectionTwo(Integer balconyDirectionTwo) {
		this.balconyDirectionTwo = balconyDirectionTwo;
	}
	public String getBalconySizeThree() {
		return balconySizeThree;
	}
	public void setBalconySizeThree(String balconySizeThree) {
		this.balconySizeThree = balconySizeThree;
	}
	public Integer getBalconyDirectionThree() {
		return balconyDirectionThree;
	}
	public void setBalconyDirectionThree(Integer balconyDirectionThree) {
		this.balconyDirectionThree = balconyDirectionThree;
	}
	public String getDeviceStageSize() {
		return deviceStageSize;
	}
	public void setDeviceStageSize(String deviceStageSize) {
		this.deviceStageSize = deviceStageSize;
	}
	public Integer getDeviceStageDirection() {
		return deviceStageDirection;
	}
	public void setDeviceStageDirection(Integer deviceStageDirection) {
		this.deviceStageDirection = deviceStageDirection;
	}
	public String getBoxRoomSize() {
		return boxRoomSize;
	}
	public void setBoxRoomSize(String boxRoomSize) {
		this.boxRoomSize = boxRoomSize;
	}
	public String getGazeboSizeOne() {
		return gazeboSizeOne;
	}
	public void setGazeboSizeOne(String gazeboSizeOne) {
		this.gazeboSizeOne = gazeboSizeOne;
	}
	public Integer getGazeboDirectionOne() {
		return gazeboDirectionOne;
	}
	public void setGazeboDirectionOne(Integer gazeboDirectionOne) {
		this.gazeboDirectionOne = gazeboDirectionOne;
	}
	public String getGazeboSizeTwo() {
		return gazeboSizeTwo;
	}
	public void setGazeboSizeTwo(String gazeboSizeTwo) {
		this.gazeboSizeTwo = gazeboSizeTwo;
	}
	public Integer getGazeboDirectionTwo() {
		return gazeboDirectionTwo;
	}
	public void setGazeboDirectionTwo(Integer gazeboDirectionTwo) {
		this.gazeboDirectionTwo = gazeboDirectionTwo;
	}
	public Double getPenthouseArea() {
		return penthouseArea;
	}
	public void setPenthouseArea(Double penthouseArea) {
		this.penthouseArea = penthouseArea;
	}
	public Double getMaxPenthouseHeight() {
		return maxPenthouseHeight;
	}
	public void setMaxPenthouseHeight(Double maxPenthouseHeight) {
		this.maxPenthouseHeight = maxPenthouseHeight;
	}
	public Double getIndoorGardenArea() {
		return indoorGardenArea;
	}
	public void setIndoorGardenArea(Double indoorGardenArea) {
		this.indoorGardenArea = indoorGardenArea;
	}
	

	public Double getGardenAreaOne() {
		return gardenAreaOne;
	}
	public void setGardenAreaOne(Double gardenAreaOne) {
		this.gardenAreaOne = gardenAreaOne;
	}
	public Integer getGardenAreaOneDirection() {
		return gardenAreaOneDirection;
	}
	public void setGardenAreaOneDirection(Integer gardenAreaOneDirection) {
		this.gardenAreaOneDirection = gardenAreaOneDirection;
	}
	public Double getGardenAreaTwo() {
		return gardenAreaTwo;
	}
	public void setGardenAreaTwo(Double gardenAreaTwo) {
		this.gardenAreaTwo = gardenAreaTwo;
	}
	public Integer getGardenAreaTwoDirection() {
		return gardenAreaTwoDirection;
	}
	public void setGardenAreaTwoDirection(Integer gardenAreaTwoDirection) {
		this.gardenAreaTwoDirection = gardenAreaTwoDirection;
	}
	public Double getHarfBasementArea() {
		return harfBasementArea;
	}
	public void setHarfBasementArea(Double harfBasementArea) {
		this.harfBasementArea = harfBasementArea;
	}
	public Integer getHarfBasementDirection() {
		return harfBasementDirection;
	}
	public void setHarfBasementDirection(Integer harfBasementDirection) {
		this.harfBasementDirection = harfBasementDirection;
	}
	public Double getBasementAreaOne() {
		return basementAreaOne;
	}
	public void setBasementAreaOne(Double basementAreaOne) {
		this.basementAreaOne = basementAreaOne;
	}
	public Integer getBasementDirectionOne() {
		return basementDirectionOne;
	}
	public void setBasementDirectionOne(Integer basementDirectionOne) {
		this.basementDirectionOne = basementDirectionOne;
	}
	public Integer getBasementLightOne() {
		return basementLightOne;
	}
	public void setBasementLightOne(Integer basementLightOne) {
		this.basementLightOne = basementLightOne;
	}
	public Double getBasementAreaTwo() {
		return basementAreaTwo;
	}
	public void setBasementAreaTwo(Double basementAreaTwo) {
		this.basementAreaTwo = basementAreaTwo;
	}
	public Integer getBasementDirectionTwo() {
		return basementDirectionTwo;
	}
	public void setBasementDirectionTwo(Integer basementDirectionTwo) {
		this.basementDirectionTwo = basementDirectionTwo;
	}
	public Integer getBasementLightTwo() {
		return basementLightTwo;
	}
	public void setBasementLightTwo(Integer basementLightTwo) {
		this.basementLightTwo = basementLightTwo;
	}
	public String getLightWellSize() {
		return lightWellSize;
	}
	public void setLightWellSize(String lightWellSize) {
		this.lightWellSize = lightWellSize;
	}
	public String getViewingAngle() {
		return viewingAngle;
	}
	public void setViewingAngle(String viewingAngle) {
		this.viewingAngle = viewingAngle;
	}
    
    
    
    

}
