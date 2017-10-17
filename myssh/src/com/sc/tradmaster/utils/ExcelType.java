package com.sc.tradmaster.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanjian
 */
public enum ExcelType {
    house,houseDetails,projectBuilding,projectBuildingUnit,project;
	//导出
    public Map<String, String> enName() {
        Map map = new LinkedHashMap();
        switch (this) {
            case house:
                map.put("houseNo", "房号");
                map.put("presalePermissionInfo", "预售证号");
                map.put("district","区位号");
                map.put("buildingNo", "楼栋号");
                map.put("unit", "单元号");
                map.put("floor", "楼层");
                map.put("direct", "朝向");
                map.put("buildArea", "建筑面积");
                map.put("usefulArea", "使用面积");
                map.put("listPrice", "列表价");
                map.put("minimumPrice", "底价");
                map.put("shopPrice", "中介供货价");
                map.put("decorationStandardName", "装修标准(0：毛坯；1：普通装修；2：精装修；3：家具全配；4：家电全配)");
                map.put("houseTypeName", "户型名称");
                /*map.put("houseStatusName", "房源状态");
                map.put("shelvTime", "发布时间");
                map.put("isOpenName", "中介是否可见");
                map.put("houseNum", "房源编号");*/
                map.put("houseKindName", "房源类型(0:公寓、1:排屋、2:独栋、3:商住两用、4:办公室、5:酒店式公寓、6:商铺、7:车位、8:车库、9:储藏室)");
                return map;
            default:
                throw new RuntimeException();
        }
    }
    
    //导入	 house,houseDetails,projectBuilding,projectBuildingUnit,project;
    public Map<String, String> zhName() {
        Map map = new LinkedHashMap();
        switch (this) {
	        case house:
	        	map.put("房源id", "houseNum");
	        	map.put("区位号", "district");
	        	map.put("楼栋号", "buildingNo");
	        	map.put("单元号", "unit");
	        	map.put("楼层号", "floor");
	        	map.put("房号", "houseNo");
	        	map.put("朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "direct");
	        	map.put("物业类型(1-别墅,2-小高层,3-高层,4-超高层,5-叠排,6-多层,7-排屋,8-商业街,9-集中商业,10-酒店公寓,11-底商,12-soho,13-写字楼)", "housePropertyType");
	        	map.put("产权年限(年)", "propertyYear");
	        	map.put("建筑面积（预测)", "preBuildArea");
	        	map.put("建筑面积（实测)", "buildArea");
	        	map.put("总价(元)", "listPrice");
	        	//map.put("物业费(元)", "buildingNo");
	        	map.put("套内面积", "insideSpace");
	        	map.put("公摊面积", "sharedArea");
	        	map.put("占地面积", "floorArea");
	        	map.put("非计容面积", "novolumeArea");
	        	map.put("实得面积", "takeArea");
	        	map.put("实得面积比", "takeAreaProportion");
	        	map.put("预售证信息", "presalePermissionInfo");
	        	map.put("户型位置(1-边套,2-中间套)", "houseTypePosition");
	        	map.put("总面宽(米)", "areaWide");
	        	map.put("采光面(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "lightDirection");
	        	map.put("最大进深(米)", "maxDeep");
	        	map.put("层高(米)", "floorHeight");
	        	map.put("日照时间(小时)", "lightTime");
	        	map.put("装修标准(1-精装 2-毛坯)", "renovationStandard");
	        	map.put("租售类型(1-出租2-出售3-租售)", "sellType");
	        	return map;
            case houseDetails: 			
            	map.put("房源id", "houseNum");
	        	/*map.put("区位号", "district");
	        	map.put("楼栋号", "buildingNo");
	        	map.put("单元号", "unit");
	        	map.put("楼层号", "floor");
	        	map.put("房号", "houseNo");*/
                map.put("主卧1尺寸(长*宽)", "masterBedroomSize");
                map.put("主卧1朝向 (1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "masterBedroomDirection");
                map.put("主卧2尺寸(长*宽)", "secondMasterBedroomSize");
                map.put("主卧2朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "secondMasterBedroomDirection");
                map.put("次卧1尺寸(长*宽)", "bedroomSize");
                map.put("次卧1朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "bedroomDirection");
                map.put("次卧2尺寸(长*宽)", "secondBedroomSize");
                map.put("次卧2朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "secondBedroomDirection");
                map.put("书房尺寸(长*宽)", "schoolroomSize");
                map.put("书房朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "schoolroomDirection");
                map.put("餐厅尺寸(长*宽)", "diningRoomSize");
                map.put("餐厅朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "diningRoomDirection");
                map.put("客厅尺寸(长*宽)", "livingRoomSize");
                map.put("客厅朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "livingRoomDirection");
                map.put("卫生间1尺寸(长*宽)", "bathroomSizeOne");
                map.put("卫生间1明暗(0-暗,1-明)", "bathroomLightOne");
                map.put("卫生间2尺寸(长*宽)", "bathroomSizeTwo");
                map.put("卫生间2明暗(0-暗,1-明)", "bathroomLightTwo");
                map.put("卫生间3尺寸(长*宽)", "bathroomSizeThree");
                map.put("卫生间3明暗(0-暗,1-明)", "bathroomLightThree");
                map.put("卫生间4尺寸(长*宽)", "bathroomSizeFour");
                map.put("卫生间4明暗(0-暗,1-明)", "bathroomLightFour");
                map.put("卫生间5尺寸(长*宽)", "bathroomSizeFive");
                map.put("卫生间5明暗(0-暗,1-明)", "bathroomLightFive");
                map.put("厨房尺寸(长*宽)", "kitchenSize");
                map.put("工人房尺寸(长*宽)", "maidBedroomSize");
                map.put("工人房明暗(0-暗,1-明)", "maidBedroomLight");
                map.put("洗衣房尺寸(长*宽)", "laundryRoomSize");
                map.put("阳台1尺寸(长*宽)", "balconySizeOne");
                map.put("阳台1朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "balconyDirectionOne");
                map.put("阳台2尺寸(长*宽)", "balconySizeTwo");
                map.put("阳台2朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "balconyDirectionTwo");
                map.put("阳台3尺寸(长*宽)", "balconySizeThree");
                map.put("阳台3朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "balconyDirectionThree");
                map.put("设备平台尺寸(长*宽)", "deviceStageSize");
                map.put("设备平台朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "deviceStageDirection");
                map.put("储藏间尺寸(长*宽)", "boxRoomSize");
                map.put("露台1尺寸(长*宽)", "gazeboSizeOne");
                map.put("露台1朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "gazeboDirectionOne");
                map.put("露台2尺寸(长*宽)", "gazeboSizeTwo");
                map.put("露台2朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "gazeboDirectionTwo");
                map.put("阁楼面积(㎡)", "penthouseArea");
                map.put("阁楼层高(米)", "maxPenthouseHeight");
                map.put("入户花园面积(㎡)", "indoorGardenArea");
                map.put("花园1面积(㎡)", "gardenAreaOne");
                map.put("花园1朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "gardenAreaOneDirection");
                map.put("花园2面积(㎡)", "gardenAreaTwo");
                map.put("花园2朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "gardenAreaTwoDirection");
                map.put("半地下室面积(㎡)", "harfBasementArea");
                map.put("半地下室朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "harfBasementDirection");
                map.put("地下室1面积(㎡)", "basementAreaOne");
                map.put("地下室1朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "basementDirectionOne");
                map.put("地下室1明暗(0-暗,1-明)", "basementLightOne");
                map.put("地下室2面积(㎡)", "basementAreaTwo");
                map.put("地下室2朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "basementDirectionTwo");
                map.put("地下室2明暗(0-暗,1-明)", "basementLightTwo");
                map.put("采光井/通风井 尺寸(长*宽)", "lightWellSize");
                map.put("观景角度(文字说明)", "viewingAngle");
                return map;
            case projectBuilding:
                map.put("楼栋id", "buildingId");
                map.put("楼栋号", "buildingName");
                map.put("楼栋朝向(1-东,2-南,3-西,4-北,5-东南,6-东北,7-西南,8-西北)", "buildingDirection");
                map.put("结构形式(框架/框剪/钢构)", "constitute");
                map.put("是否直连地库(0-否,1-是)", "isToBasement");
                map.put("架空层数(0表示没有架空层)", "emptySpace");
                map.put("是否含有非机动车库(0-无,1-有)", "havaNonMotorGarage");
                map.put("有无裙房(0-无,1-有)", "havaAnnex");
                map.put("楼栋具体位置", "buildingPosition");
                return map;
            case projectBuildingUnit:
                map.put("单元id", "unitId");
                map.put("电梯数", "liftNum");
                map.put("电梯材质", "liftConstitute");
                map.put("单元每层户数", "unitUser");
                map.put("单元楼层数", "floorNum");
                map.put("单元状态(0-删除,1-存在)", "unitStatus");
                map.put("单元得房率直接存%", "unitEfficiencyRate");
                return map;
            case project:
            	map.put("案场id", "projectId");
                map.put("重要信息(房性差异/不利因素)", "importanceMsg");
                map.put("企业性质 (1-民企,2-国企,3-上市公司)", "typeid");
                map.put("开发资质(1-1级,2-1级暂定,3-2级,4-2级暂定)", "developmenStatus");
                map.put("设计单位", "designCompany");
                map.put("施工单位", "constructionCompany");
                map.put("是否入住平台 (0-申请入住平台,1-同意入住平台,2-拒绝入住平台)", "proInSystemStutas");
                map.put("车位数", "parking");
                map.put("小区会所(自建配套)", "ownerClubArea");
                map.put("健身房(面积/品牌)(自建配套)", "ownerGym");
                map.put("社区中心(面积/品牌)(自建配套)", "ownerCommunityCenter");
                map.put("游泳池(自建配套)", "ownerSwimmingPool");
                map.put("学校(自建配套)", "ownerSchool");
                map.put("幼儿园(自建配套)", "ownerKindergarten");
                map.put("商业(自建配套)", "ownerBusiness");
                map.put("其他(自建配套)", "ownerOther");
                map.put("商业(周边配套)", "business");
                map.put("教育(周边配套)", "education");
                map.put("金融(周边配套)", "financial");
                map.put("交通(周边配套)", "transportation");
                map.put("医疗(周边配套)", "medical");
                map.put("其他(周边配套)", "other");
                return map;
            default:
                throw new RuntimeException();
        }
    }
}
