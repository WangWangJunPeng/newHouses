package com.sc.tradmaster.service.house.impl;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseCollect;
import com.sc.tradmaster.bean.HouseDetails;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectBenefits;
import com.sc.tradmaster.bean.ProjectBuilding;
import com.sc.tradmaster.bean.ProjectBuildingUnit;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.ProjectPics;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.NewContractRecordsDTO;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.house.impl.dto.HouseDTO;
import com.sc.tradmaster.service.house.impl.dto.HouseExportDTO;
import com.sc.tradmaster.service.house.impl.dto.SalesHouseDTO;
import com.sc.tradmaster.service.housetype.HouseTypeService;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.ExcelHelper;
import com.sc.tradmaster.utils.JxlExcelHelper;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

import net.sf.json.JSONObject;

@Service("houseService")
public class HouseServiceImpl implements HouseService{

	@Resource(name = "projectService")
	private ProjectService projectService;
	
	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	@Override
	public void addOrUpdate(House house) {
		baseDao.saveOrUpdate(house);
		//添加房源时启用该户型，更新户型信息
		if(house.getHouseTypeId()!=null && !house.equals("")){
			HouseType houseType = (HouseType) baseDao.loadById(HouseType.class, house.getHouseTypeId());
			//更新房源信息时，如果户型信息改变，检查该户型是否被有其他房源使用，否则，将启用的户型置为no未启用状态
			String houseHql = "from House where houseTypeId = '"+house.getHouseTypeId()+"'";
			List houseList = baseDao.findByHql(houseHql);
			if(houseList.isEmpty()){
				houseType.setIsUse("no");
			}else{
				houseType.setIsUse("yes");
			}
			baseDao.saveOrUpdate(houseType);
		}
	}

	
	/**
	 * 搜索页(上下架管理搜索页)
	 * 
	 */	
	@Override
	public void findHouseByProperty4(User user, String[] selectNames, String[] selectValue, Page page) {
		
		Project pro = (Project) baseDao.loadById(Project.class, user.getParentId());
		if(pro!=null){
			String[] newPropertyName = {selectNames[0],selectNames[2],selectNames[3],selectNames[4]};
			String[] newPropertyValue = {selectValue[0],selectValue[2],selectValue[3],selectValue[4]};
			String[] orderName = { "shelvTime" };
			String[] orderType = {"DESC"};
			Integer houseStatus = 2;
			int start = 0;
			int limit = 1000;
			String projectId = user.getParentId();
			/*String houseHQL = "from House where projectId = '"+user.getParentId()+"' and houseStatus != 2 ";
		if (selectValue[0] != null && !"".equals(selectValue[0])){
			houseHQL += " and decorationStandard = '"+ selectValue[0]+ "' ";
		}
		if (selectValue[2] != null && !"".equals(selectValue[2])){
			houseHQL += " and houseStatus = '"+ selectValue[2] + "' ";
		}
		if (selectValue[3] != null && !"".equals(selectValue[3])){
			houseHQL += " and houseKind = '"+selectValue[3]+"' ";
		}
		if (selectValue[4] != null && !"".equals(selectValue[4])){
			houseHQL += " and isOpen = '"+selectValue[4]+"'";
		}*/
//		List<House> hList = baseDao.findByHql(houseHQL);
			List<House> hList = baseDao.findByPropertyToUpDown("House", newPropertyName, newPropertyValue, orderName, orderType, start, limit, houseStatus);
					
			String houseTypeHQL = null;
			if(selectValue[1]!=null && !selectValue[1].equals("")){
				houseTypeHQL = "from HouseType as model where model.projectId = '" +user.getParentId()+"' and model."+selectNames[1]+" like '%" + selectValue[1] + "%'";
			}else{
				houseTypeHQL = "from HouseType as model where model.projectId = '" +user.getParentId()+"'";
			}
			List<HouseType> htList = baseDao.findByHql(houseTypeHQL);
			
			ProjectGuide pg = (ProjectGuide) baseDao.loadById(ProjectGuide.class, user.getParentId());
			List<SalesHouseDTO> shdtoList = new ArrayList<>();
			
			if(hList!=null && hList.size()>0){
				
				for (House h : hList) {
					if (h != null && !"".equals(h)){
						if(h.getHouseTypeId()!=null && !("").equals(h.getHouseTypeId()) && htList.size()>0){
							//户型匹配
							for (HouseType ht : htList) {
								if (h.getProjectId().equals(pro.getProjectId()) && h.getHouseTypeId().equals(ht.getHouseTypeId())) {
									SalesHouseDTO shDto = new SalesHouseDTO();
									SalesHouseDTO DtoObject = shDto.getSlesHouseDto(h, pro, ht, pg);
//									ContractRecords cr = (ContractRecords) baseDao.loadObject("from ContractRecords where houseNum = '"+h.getHouseNum()+"' and recordStatus = 5");
//									if (cr != null && !"".equals(cr)){
//										DtoObject.setHouseStatus(4);
//									}
									shdtoList.add(DtoObject);
								}
							}
							//户型为空
						}else{
							if(h.getProjectId().equals(pro.getProjectId()) && !(selectValue[1]!=null && !selectValue[1].equals(""))){
								SalesHouseDTO shDto = new SalesHouseDTO();
								SalesHouseDTO DtoObject = shDto.getSlesHouseDto(h,pro,null,pg);
								shdtoList.add(DtoObject);
							}
						}
					}
				}
				
			}
			page.setTotal(shdtoList.size());
			SalesHouseDTO shDto = new SalesHouseDTO();
			List pageList = shDto.getPageList(shdtoList, page.getStart(), page.getLimit());
			page.setRoot(pageList);
		}
	}
		
	
	/**
	 * 一个案场下的所有房型(户型)
	 */	
	@Override
	public TreeSet findAllHouseType(User user, Page page) {
		String projectId = user.getParentId();
		String hql = "from HouseType ht where ht.projectId = '" + projectId +"'";
		List<HouseType> hList = baseDao.findByHql(hql);
		TreeSet ts = new TreeSet<>();
		for (HouseType ht : hList) {
			ts.add(ht.getHousType());
		}
		return ts;
	}
	
	@Override
	public void dropHouseByHouseStatus(House[] house) {
		for (int i = 0; i<house.length; i++) {
			String houseId = house[i].getHouseId();
			House h = (House) baseDao.loadById(House.class, houseId);
			h.setHouseStatus(2);
			baseDao.saveOrUpdate(h);
		}
	}

	@Override
	public void findByProperty(User u,String[] propertyName, String[] propertyValue, Page page) {
		Project pro = (Project) baseDao.loadById(Project.class, u.getParentId());
		if(pro!=null){
			String[] newPropertyName = {propertyName[0],propertyName[1],propertyName[2]};
			String[] newPropertyValue = {propertyValue[0],propertyValue[1],propertyValue[2]};
			String[] orderName = { "shelvTime" };
			String[] orderType = {"DESC"};
			int start = 0;
			int limit = 1000;
			String hql = null;
			if(propertyValue[3]!=null && !propertyValue[3].equals("")){
				hql = "from HouseType as model where model.projectId = '" +u.getParentId()+"' and model."+propertyName[3]+" like '%" + propertyValue[3] + "%'";
			}else{
				hql = "from HouseType as model where model.projectId = '" +u.getParentId()+"'";
			}
			List<HouseType> htList = baseDao.findByHql(hql);
			List<House> allList = baseDao.findByProperty("House", newPropertyName, newPropertyValue, orderName, orderType, start, limit);
			List<House> list = new ArrayList<>();
			//过滤状态不是删除状态的房源信息
			for(House h : allList){
				if(h.getProjectId().equals(u.getParentId())){
					list.add(h);
				}
			}
			ProjectGuide pg = (ProjectGuide) baseDao.loadById(ProjectGuide.class, u.getParentId());
			List<SalesHouseDTO> shDtoList = new ArrayList<>();
			if(list!=null && list.size()>0){
				for(House h :list){
					if(!h.getHouseStatus().equals(2)){
						if(h.getHouseTypeId()!=null && !h.getHouseTypeId().equals("") && htList!=null && htList.size()>0){
							//户型匹配
							for (HouseType ht : htList) {
								if (h.getProjectId().equals(pro.getProjectId()) && h.getHouseTypeId().equals(ht.getHouseTypeId())) {
									SalesHouseDTO shDto = new SalesHouseDTO();
									SalesHouseDTO DtoObject = shDto.getSlesHouseDto(h, pro, ht, pg);
									shDtoList.add(DtoObject);
								}
							}
						//户型为空
						}else{
							if(h.getProjectId().equals(pro.getProjectId()) && !(propertyValue[3]!=null && !propertyValue[3].equals(""))){
								SalesHouseDTO shDto = new SalesHouseDTO();
								SalesHouseDTO DtoObject = shDto.getSlesHouseDto(h,pro,null,pg);
								shDtoList.add(DtoObject);
							}
						}
						
					}
				}
			}
			page.setTotal(shDtoList.size());
			SalesHouseDTO shDto = new SalesHouseDTO();
			List pageList = shDto.getPageList(shDtoList, page.getStart(), page.getLimit());
			page.setRoot(pageList);
		}
	}

	@Override
	public void updateUp(String[] houseNum) {
		System.out.println(houseNum);
		for (int i = 0; i<houseNum.length; i++) {
			String hql = "from House where houseNum = '" + houseNum[i] + "'";
			House h = (House) baseDao.loadObject(hql);
			if (h != null) {
				//状态改上架
				h.setHouseStatus(1);
				//上架的发布时间
				h.setShelvTime(DateTime.toString1(new Date()));
				
				baseDao.saveOrUpdate(h);
			}
		}
	}

	@Override
	public void updateDown(String[] houseNum) {
		for (int i = 0; i<houseNum.length; i++) {
			String hql = "from House where houseNum = '" + houseNum[i] + "'";
			House h = (House) baseDao.loadObject(hql);
			if ( h != null ) {
				h.setHouseStatus(0);			
				baseDao.saveOrUpdate(h);
			}
		}
	}

	@Override
	public void updateIsOpen(String[] houseNum) {
		for (int i = 0; i<houseNum.length; i++) {
			String hql = "from House where houseNum = '" + houseNum[i] + "'";
			House h = (House) baseDao.loadObject(hql);
			if (h != null ) {
				h.setIsOpen(1);			
				baseDao.saveOrUpdate(h);
			}
		}
	}

	@Override
	public void updateIsClose(String[] houseNum) {
		for (int i = 0; i<houseNum.length; i++) {
			String hql = "from House where houseNum = '" + houseNum[i] + "'";
			House h = (House) baseDao.loadObject(hql);
			if (h != null ) {
				h.setIsOpen(0);			
				baseDao.saveOrUpdate(h);
			}
		}
	}

	/**
	 * 可售房源(中介管理员)
	 * @param houses
	 * @return
	 */	
	@Override
	public List findUp(String projectId) {
		String hql="from House h where h.projectId = '" + projectId + "'" + "and (h.houseStatus = 1 or h.houseStatus = 4) and isOpen = 1";
		List<House> list = baseDao.findByHql(hql);
		List<SalesHouseDTO> shdtoList = new ArrayList<>();
		for (House house : list) {
			String houseTypeHQL = "from HouseType where houseTypeId = '" + house.getHouseTypeId() + "'";
			HouseType houseType = (HouseType) baseDao.loadObject(houseTypeHQL);
			SalesHouseDTO shdto = new SalesHouseDTO();
			shdto.setHouseId(house.getHouseId());
			shdto.setHouseNo(house.getHouseNo());
			shdto.setHouseKind(house.getHouseKind());
			shdto.setBuildingNo(house.getBuildingNo());
			if (houseType != null) {
				shdto.setHouseStyle(houseType.getHousType());
			}
			shdto.setBuildArea(house.getBuildArea());
			shdto.setUsefulArea(house.getUsefulArea());
			shdto.setListPrice(SysContent.get2Double(house.getListPrice()));
			shdto.setShopPrice(SysContent.get2Double(house.getShopPrice()));
			shdto.setRewardMoney(house.getRewardMoney());
			shdto.setShelvTime(house.getShelvTime());
			shdto.setHouseStatus(house.getHouseStatus());
			shdto.setHouseNum(house.getHouseNum());
			shdtoList.add(shdto);
		}
		return shdtoList;
	}

	/**
	 * 一房一档
	 * @param houses
	 * @return
	 * @throws ParseException 
	 */	
	@Override
	public Map<String, Object> findById(Integer houseNum) throws ParseException {
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//设置价格保留两位小数
		DecimalFormat df = new DecimalFormat("#.##");
		//根据houseId查house对象
		String houseHQL = "from House where houseNum = '" + houseNum + "'";
		House house = (House) baseDao.loadObject(houseHQL);
		if (house != null && !"".equals(house)) {
			//定义一个
			Map<String, Object> map = new HashMap<>();
	  		System.out.println(house);
			//获取projectId
			String projectId = house.getProjectId();
			//根据projectId查project对象
			Project project = (Project) baseDao.loadById(Project.class, projectId);
			//查优惠信息
			List<ProjectBenefits> pbList = new ArrayList<>();
			
			//获得该房子的优惠组合id
			String bestYouHuiId = house.getBestBenefitsId();
			String nowTime = DateTime.toString1(new Date());
			double nowPrice = house.getListPrice();
			if (bestYouHuiId != null) {
				String youhui = "";
				String[] bestBenefits = bestYouHuiId.split(",");
				for (int i = 0; i < bestBenefits.length; i++) {
					String pbHQL2 = "from ProjectBenefits pb where pb.benefitId = '" + bestBenefits[i] + "' and startTime <= '"+nowTime + "'"
							+ " and endTime >= '" + nowTime + "'";
					ProjectBenefits pb = (ProjectBenefits) baseDao.loadObject(pbHQL2);
					if (pb!=null && !"".equals(pb)) {
						pbList.add(pb);
					}
				}
				if (pbList!= null && !"".equals(pbList) && pbList.size()>0) {
					for (ProjectBenefits pb : pbList) {
						if (pb.getType() == 0) {
							nowPrice = nowPrice - pb.getBenefitMoney();
						} else if (pb.getType() == 1) {
							nowPrice = nowPrice * (1-pb.getBenefitPercent()/100);
						}
						youhui += pb.getCaption()+ ",";
					}
				}
				//最高优惠价
				double zuigaoYouhui = house.getListPrice() -nowPrice;
				String zuidipriceStr = df.format(nowPrice);
				double zuidiPrice = Double.parseDouble(zuidipriceStr);
				String zuigaoYouhuiStr = df.format(zuigaoYouhui);
				double highestBenefits = Double.parseDouble(zuigaoYouhuiStr);
				double youhuilidu = (zuigaoYouhui/house.getListPrice())*100;
				String youhuiliduStr = df.format(youhuilidu);
				double lidu = Double.parseDouble(youhuiliduStr);
				//最低成交价
				map.put("zuidiPrice",nowPrice);
				//优惠力度
				map.put("lidu", lidu);
				//优惠组合
				map.put("youhui", youhui);
			}else {
				//最低成交价
				map.put("zuidiPrice",nowPrice);
				//优惠力度
				map.put("lidu", "0");
				//优惠组合
				map.put("youhui", "暂无");
				
			}
			//根据houseTypeId查houseType对象
			String houseTypeId = house.getHouseTypeId();
			String houseTypeHQL = "from HouseType where houseTypeId = '" + house.getHouseTypeId() +"'";
			HouseType ht = (HouseType) baseDao.loadObject(houseTypeHQL);
			
			map.put("houseNum", houseNum);
			map.put("projectId", project.getProjectId());
			map.put("projectName", project.getProjectName());
			String louhao = house.getBuildingNo()+"栋" + house.getUnit()+"单元"+house.getFloor()+"楼" + house.getHouseNo()+"号";
			map.put("houseNo", louhao);
			int houseKindId = house.getHouseKind();
			if (houseKindId == 0) {
				map.put("houseKind", "公寓");
			}
			if (houseKindId == 1) {
				map.put("houseKind", "排屋");
			}
			if (houseKindId == 2) {
				map.put("houseKind", "独栋");
			}
			if (houseKindId == 3) {
				map.put("houseKind", "商用两住");
			}
			if (houseKindId == 4) {
				map.put("houseKind", "办公室");
			}
			if (houseKindId == 5) {
				map.put("houseKind", "酒店式公寓");
			}
			if (houseKindId == 6) {
				map.put("houseKind", "商铺");
			}
			if (houseKindId == 7) {
				map.put("houseKind", "车位");
			}
			if (houseKindId == 8) {
				map.put("houseKind", "车库");
			}
			if (houseKindId == 9) {
				map.put("houseKind", "储藏室");
			}
//			map.put("presalePermissionURL", project.getPresalePermissionURL());
			//预售证号+图片   JSON解析字符串
//			if (house.getPresalePermissionInfo() !=null) {
//				HashMap urlMap = JSON.parseObject(house.getPresalePermissionInfo(),HashMap.class);
//				map.put("presalePermissionInfo", urlMap);
//				System.out.println("------预售证号+图片--------"+house.getPresalePermissionInfo());
//			}
			map.put("presalePermissionURL", house.getPresalePermissionInfo());
			map.put("district", house.getDistrict());
			map.put("direct", house.getDirect());
			map.put("buildArea", SysContent.get2Double(house.getBuildArea()));
			map.put("usefulArea", SysContent.get2Double(house.getUsefulArea()));
			//所有效果图
			String projectPhotoHQL = "from ProjectPics where projectId = '" +projectId+"'";
			List<ProjectPics> ppList = baseDao.findByHql(projectPhotoHQL);
			List<String> photoList = new ArrayList<>();
			//户型图添加
			photoList.add(ht.getPhotoURL());
			
			for (ProjectPics projectPics : ppList) {
				String pic = projectPics.getUrl();
				String picStr = pic.substring(1, pic.length()-1);
				String[] photoarr = picStr.split(",");
				for(int i =0;i<photoarr.length;i++){
					photoList.add(photoarr[i]);
				}
			}
			map.put("photo", photoList);
			if (ht != null) {
				//房子照片
				map.put("houseType", ht.getCaption());
			}
			int decorationStandardId = house.getDecorationStandard();
			if (decorationStandardId == 0) {
				map.put("decorationStandard", "毛坯");
			}
			if (decorationStandardId == 1) {
				map.put("decorationStandard", "普通装修");
			}
			if (decorationStandardId == 2) {
				map.put("decorationStandard", "精装修");
			}
			if (decorationStandardId == 3) {
				map.put("decorationStandard", "家具全配");
			}
			if (decorationStandardId == 4) {
				map.put("decorationStandard", "家电全配");
			}
			
			double danjiaMoney = house.getListPrice()/house.getBuildArea();
			String danjia = df.format(danjiaMoney);
			map.put("danjia", danjia);
			
			map.put("listPrice", SysContent.get2Double(house.getListPrice()));
			map.put("shopPrice", SysContent.get2Double(house.getShopPrice()));
	
			double youhuiMoney = 0; 
			double guding = 0;
			if (pbList != null) {
				map.put("youhuiList", pbList);
			}
			
			map.put("deliverStandard", project.getDeliverStandard());
			map.put("projectName", project.getProjectName());
			map.put("propertyAddress", project.getPropertyAddress());
			map.put("landArea", SysContent.get2Double(project.getLandArea())+"㎡");
			map.put("groundArea", SysContent.get2Double(project.getGroundArea())+"㎡");
			map.put("underGroundArea", SysContent.get2Double(project.getUnderGroundArea())+"㎡");
			map.put("unitCount", project.getUnitCount());
			map.put("volumeRatio", project.getVolumeRatio());
			map.put("density", project.getDensity()+"%");
			map.put("afforestationRatio", project.getAfforestationRatio()+"%");
			map.put("developer", project.getDeveloper());
			map.put("investor", project.getInvestor());
			map.put("manager", project.getManager());
			map.put("propertyCost", project.getPropertyCost());
			map.put("rightsYears", project.getRightsYears());
			map.put("startTime", project.getStartTime());
			map.put("deliverTime", project.getDeliverTime());
			return map;
		} else {
			return null;
		}
	}
	
	/**
	 * 中介一个案场下房源列表(中介搜索)
	 * @param houses
	 * @return
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	@Override
	public List<SalesHouseDTO> findMidHouseByProperty(String projectId, String houseType, Double usefulArea, Integer floor) throws NumberFormatException, ParseException {
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String houseHQL = "from House h where h.projectId = '" +projectId +"' and h.houseStatus = 1 and h.isOpen = 1";
		String houseHQL = "from House h where h.projectId = '" +projectId +"' and (h.houseStatus = 1 or h.houseStatus = 4) and h.isOpen = 1";
		
		if (usefulArea != null && !"".equals(usefulArea)){
			houseHQL += " and h.usefulArea = '" + usefulArea + "'";
		}
		if (floor != null && !"".equals(floor)){
			houseHQL += " and h.floor = '" + floor + "'";
		}
		
		List<House> hList = baseDao.findByHql(houseHQL);
		
		String htHQL = "from HouseType where projectId = '"+ projectId + "' ";
		
		if (houseType != null && !"".equals(houseType)){
			htHQL += " and housType = '" + houseType + "'";
		}
		List<HouseType> htList = baseDao.findByHql(htHQL);
		List<House> hList2 = new ArrayList<>();
		for (House house : hList) {
			for (HouseType ht : htList) {
				String houseTypeId = ht.getHouseTypeId();
				if (house.getHouseTypeId().equals(houseTypeId)){
					hList2.add(house);
				}
			}
		}
		
		List<SalesHouseDTO> salesHouseList = new ArrayList<>();
		for (House h : hList2) {
			SalesHouseDTO shdto = new SalesHouseDTO();
			String houseTypeHQL = "from HouseType where houseTypeId = '"+ h.getHouseTypeId() + "'";
			HouseType ht = (HouseType) baseDao.loadObject(houseTypeHQL);
			/*String crHQL = "from ContractRecords where houseNum = '"+h.getHouseNum()+"' "
					+ " and recordStatus = 1";
			ContractRecords cr = (ContractRecords) baseDao.loadObject(crHQL);*/
			String ebHQL = "from EnterBuy where projectId = '"+projectId+"'";
			EnterBuy eb = (EnterBuy) baseDao.loadObject(ebHQL);
			if (eb != null && !"".equals(eb)){
				//是否支持认购0:不支持,1:支持
				shdto.setIsSupport(eb.getIsSupportBuy());
			}else {
				shdto.setIsSupport("0");
			}
			/*if (cr != null && !"".equals(cr)){
				String auditingTime = cr.getAuditingTime();
				String daoqiTime = SysContent.addSameHours(auditingTime, Integer.parseInt(eb.getOutOfTime()));
				//当前时间
				Date nowTime = new Date();
				Date daoqiTimeDate = sdf.parse(daoqiTime);
				long i = nowTime.getTime() - daoqiTimeDate.getTime();
				if (i<=0){
					//不支持认购
					shdto.setIsToBuyHouse(1);
				}else {
					//支持认购
					shdto.setIsToBuyHouse(0);
				}
			}else {
				//支持认购
				shdto.setIsToBuyHouse(0);
			}*/
			//projectId
			shdto.setProjectId(h.getProjectId());
			List<ContractRecords> findByHql = baseDao.findByHql("from ContractRecords where houseNum='"+h.getHouseNum()+"' and (recordStatus=0 or recordStatus=1 or recordStatus=4)");
			shdto.setDealNum(findByHql.size());
			//houseNum
			shdto.setHouseNum(h.getHouseNum());
			//房号
			shdto.setHouseNo(h.getHouseNo());
			//楼号
			shdto.setBuildingNo(h.getBuildingNo());
			//单元号
			shdto.setUnit(h.getUnit());
			//楼层
			shdto.setFloor(h.getFloor());
			//用地面积
			shdto.setUsefulArea(Double.parseDouble(SysContent.get2Double(h.getUsefulArea())));
			shdto.setBuildArea(Double.parseDouble(SysContent.get2Double(h.getBuildArea())));
			//列表价
			shdto.setListPrice(SysContent.get2Double(h.getListPrice()));
			shdto.setIsOpen(h.getIsOpen());
			if (ht != null && !"".equals(ht)){
				//房型
				shdto.setHouseStyle(ht.getHousType());
			}
			salesHouseList.add(shdto);
		}
		return salesHouseList;
	}

	/**
	 * 置业顾问房源列表(搜索)
	 * @param houses
	 * @return
	 */	
	@Override
	public List findHouses(User user, String houseType,Double usefulArea, Integer floor) throws NumberFormatException, ParseException {
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String projectId = user.getParentId();
		String houseHQL = "from House h where h.projectId = '" +projectId +"' and (h.houseStatus = 1 or h.houseStatus = 4) ";

		if (usefulArea != null && !"".equals(usefulArea)){
			houseHQL += " and h.usefulArea = '" + usefulArea + "'";
		}
		if (floor != null && !"".equals(floor)){
			houseHQL += " and h.floor = '" + floor + "'";
		}
		
		List<House> hList = baseDao.findByHql(houseHQL);
		
		
		String htHQL = "from HouseType where projectId = '"+ projectId + "' ";
		if (houseType != null && !"".equals(houseType)){
			htHQL += " and housType = '" + houseType + "'";
		}
		List<HouseType> htList = baseDao.findByHql(htHQL);
		List<House> hList2 = new ArrayList<>();
		for (House house : hList) {
			for (HouseType ht : htList) {
				String houseTypeId = ht.getHouseTypeId();
				if (house.getHouseTypeId().equals(houseTypeId)){
					hList2.add(house);
				}
			}
		}
//		List<House> hList3 = new ArrayList<>();
//		//房子已经被认购同意的不让其他人发起认购
//		for (House house : hList2) {
//			String crHQL = "from ContractRecords where houseNum = '"+house.getHouseNum()+"' "
//					+ " and recordStatus = 1";
//			ContractRecords cr = (ContractRecords) baseDao.loadObject(crHQL);
//			if (cr == null || "".equals(cr)){
//				hList3.add(house);
//			}
//		}
		List<SalesHouseDTO> salesHouseList = new ArrayList<>();
		for (House h : hList2) {
			SalesHouseDTO shdto = new SalesHouseDTO();
			
			String houseTypeHQL = "from HouseType where houseTypeId = '"+ h.getHouseTypeId() + "'";
			HouseType ht = (HouseType) baseDao.loadObject(houseTypeHQL);
			
			String crHQL = "from ContractRecords where houseNum = '"+h.getHouseNum()+"' "
					+ " and recordStatus = 1";
			ContractRecords cr = (ContractRecords) baseDao.loadObject(crHQL);
			String ebHQL = "from EnterBuy where projectId = '"+projectId+"'";
			EnterBuy eb = (EnterBuy) baseDao.loadObject(ebHQL);
			/*if (cr != null && !"".equals(cr)){
				String auditingTime = cr.getAuditingTime();
				String daoqiTime = SysContent.addSameHours(auditingTime, Integer.parseInt(eb.getOutOfTime()));
				//当前时间
				Date nowTime = new Date();
				Date daoqiTimeDate = sdf.parse(daoqiTime);
				long i = nowTime.getTime() - daoqiTimeDate.getTime();
				if (i<=0){
					//不支持认购
					shdto.setIsToBuyHouse(1);
				}else {
					//支持认购
					shdto.setIsToBuyHouse(0);
				}
			}else {
				//支持认购
				shdto.setIsToBuyHouse(0);
			}*/
			
			//projectId
			shdto.setProjectId(h.getProjectId());
			//houseNum
			shdto.setHouseNum(h.getHouseNum());
			//房号
			shdto.setHouseNo(h.getHouseNo());
			//楼号
			shdto.setBuildingNo(h.getBuildingNo());
			//单元号
			shdto.setUnit(h.getUnit());
			//楼层
			shdto.setFloor(h.getFloor());
			//用地面积
			shdto.setUsefulArea(Double.parseDouble(SysContent.get2Double(h.getUsefulArea())));
			shdto.setBuildArea(Double.parseDouble(SysContent.get2Double(h.getBuildArea())));
			//列表价
			shdto.setListPrice(SysContent.get2Double(h.getListPrice()));
			shdto.setIsOpen(h.getIsOpen());
			if (ht != null && !"".equals(ht)){
				//房型
				shdto.setHouseStyle(ht.getHousType());
			}
			salesHouseList.add(shdto);
		}
		return salesHouseList;
	}

	/**
	 * 置业顾问房源列表(所有)
	 * @param houses
	 * @return
	 */	
	@Override
	public List findAllHouses(User user) {
		String projectId = user.getParentId();
		String houseHQL = "from House h where h.projectId = '" +projectId +"' and (h.houseStatus = 1 or h.houseStatus = 4)";
		List<House> houseList = baseDao.findByHql(houseHQL);
		List salesHouseList = new ArrayList<>();
		for (House house : houseList) {
			String houseTypeHQL = "from HouseType where houseTypeId = '" + house.getHouseTypeId() + "'";
			HouseType houseType = (HouseType) baseDao.loadObject(houseTypeHQL);
			SalesHouseDTO shdto = new SalesHouseDTO();
			shdto.setHouseId(house.getHouseId());
			shdto.setHouseNo(house.getHouseNo());
			shdto.setBuildingNo(house.getBuildingNo());
			shdto.setUnit(house.getUnit());
			shdto.setFloor(house.getFloor());
			shdto.setHouseStyle(houseType.getHousType());
			shdto.setUsefulArea(house.getUsefulArea());
			shdto.setListPrice(SysContent.get2Double(house.getListPrice()));
			salesHouseList.add(shdto);
		}
		return salesHouseList;
	}


	@Override
	public House findHouseByNum(Integer hNum) {
		if(hNum!=null && !hNum.equals("")){
			House h = (House) baseDao.loadById(House.class, hNum);
			h.setBuildAreaStr(SysContent.get2Double(h.getBuildArea()));
			h.setUsefulAreaStr(SysContent.get2Double(h.getUsefulArea()));
			h.setListPriceStr(SysContent.get2Double(h.getListPrice()));
			h.setShopPriceStr(SysContent.get2Double(h.getShopPrice()));
			h.setMinimumPriceStr(SysContent.get2Double(h.getMinimumPrice()));
			return h;
		}
		return null;
	}

	@Override
	public List<Map<String, String>> findHouseTypeByProId(String parentId) {
		String hql = "from HouseType where projectId = '"+parentId+"'";
		List<HouseType> htList = baseDao.findByHql(hql);
		List<Map<String,String>> listMap = new ArrayList<>();
		for(HouseType ht : htList){
			Map<String,String> htMenu = new HashMap<>();
			htMenu.put("htId", ht.getHouseTypeId());
			htMenu.put("caption", ht.getCaption());
			listMap.add(htMenu);
		}
		return listMap;
	}

	/**
	 * 微信分享页缩小
	 * @param house
	 * 
	 */	
	@Override
	public Map<String, Object> findOneHouseLittle(User user, Integer houseNum) {
		//根据houseId查house对象
		String houseHQL = "from House where houseNum = '" + houseNum + "'";
		House house = (House) baseDao.loadObject(houseHQL);
		//定义一个map
		Map<String, Object> map = new HashMap<>();
		if ( house != null) {
			String pHQL = "from Project p where p.projectId = '" + house.getProjectId() + "'";
			Project p = (Project) baseDao.loadObject(pHQL);
			//查案场图片
			String ppHQL = "from ProjectPics p where p.projectId = '" + house.getProjectId() + "'";
			ProjectPics pp = (ProjectPics) baseDao.loadObject(pHQL);
			
			map.put("projectName", p.getProjectName());
			map.put("houseNo", house.getHouseNo());
			map.put("buildingNo", house.getBuildingNo());
			map.put("unit", house.getUnit());
			map.put("price", house.getListPrice());
			//案场图片
			//到时候需要解析
			map.put("photo", pp.getUrl());
			map.put("userId", user.getUserId());
			
		}
		return map;
	}

	
	/**
	 * 微信分享页
	 * @param house
	 * @return
	 * @throws ParseException 
	 */	
	@Override
	public Map<String, Object> findOneHouseById(User user, Integer houseNum) throws ParseException {
		//设置价格保留两位小数
		DecimalFormat df = new DecimalFormat("#.##");
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//根据houseId查house对象
		String houseHQL = "from House where houseNum = '" + houseNum + "'";
		House house = (House) baseDao.loadObject(houseHQL);
		if (house != null) {
	  		System.out.println(house);
			//获取projectId
			String projectId = house.getProjectId();
			//根据projectId查project对象
			Project project = (Project) baseDao.loadById(Project.class, projectId);
			//查优惠信息
			//根据houseTypeId查houseType对象
			String houseTypeId = house.getHouseTypeId();
			String houseTypeHQL = "from HouseType where houseTypeId = '" + house.getHouseTypeId() +"'";
			HouseType ht = (HouseType) baseDao.loadObject(houseTypeHQL);
			//定义一个map
			Map<String, Object> map = new HashMap<>();
			
			map.put("sharePerson", user.getUserCaption());
			map.put("sharePersonPhone", user.getPhone());
			map.put("shareTime", DateTime.toString1(new Date()));
			
			map.put("projectId", project.getProjectId());
			map.put("projectName", project.getProjectName());
			String louhao = house.getBuildingNo() + house.getUnit() + house.getHouseNo();
			map.put("houseNo", louhao);
			int houseKindId = house.getHouseKind();
			if (houseKindId == 0) {
				map.put("houseKind", "公寓");
			}
			if (houseKindId == 1) {
				map.put("houseKind", "排屋");
			}
			if (houseKindId == 2) {
				map.put("houseKind", "独栋");
			}
			if (houseKindId == 3) {
				map.put("houseKind", "商用两住");
			}
			if (houseKindId == 4) {
				map.put("houseKind", "办公室");
			}
			if (houseKindId == 5) {
				map.put("houseKind", "酒店式公寓");
			}
			if (houseKindId == 6) {
				map.put("houseKind", "商铺");
			}
			if (houseKindId == 7) {
				map.put("houseKind", "车位");
			}
			if (houseKindId == 8) {
				map.put("houseKind", "车库");
			}
			if (houseKindId == 9) {
				map.put("houseKind", "储藏室");
			}
//					map.put("presalePermissionURL", project.getPresalePermissionURL());
			//预售证号+图片   JSON解析字符串
//					if (house.getPresalePermissionInfo() !=null) {
//						HashMap urlMap = JSON.parseObject(house.getPresalePermissionInfo(),HashMap.class);
//						map.put("presalePermissionInfo", urlMap);
//						System.out.println("------预售证号+图片--------"+house.getPresalePermissionInfo());
//					}
			map.put("presalePermissionURL", house.getPresalePermissionInfo());
			map.put("district", house.getDistrict());
			map.put("direct", house.getDirect());
			map.put("buildArea", house.getBuildArea());
			map.put("usefulArea", house.getUsefulArea());
			//房子照片
			map.put("photos", house.getPhotos());
			
			if (ht != null) {
				map.put("houseType", ht.getCaption());
			}
			int decorationStandardId = house.getDecorationStandard();
			if (decorationStandardId == 0) {
				map.put("decorationStandard", "毛坯");
			}
			if (decorationStandardId == 1) {
				map.put("decorationStandard", "普通装修");
			}
			if (decorationStandardId == 2) {
				map.put("decorationStandard", "精装修");
			}
			if (decorationStandardId == 3) {
				map.put("decorationStandard", "家具全配");
			}
			if (decorationStandardId == 4) {
				map.put("decorationStandard", "家电全配");
			}
			double danjiaMoney = house.getListPrice()/house.getUsefulArea();
			String danjia = df.format(danjiaMoney);
			map.put("danjia", danjia);
			map.put("listPrice", house.getListPrice());
			map.put("shopPrice", house.getShopPrice());
	
			List<ProjectBenefits> pbList = new ArrayList<>();
			//获得该房子的优惠组合id
			String bestYouHuiId = house.getBestBenefitsId();
			if (bestYouHuiId != null) {
				double nowPrice = house.getListPrice();
				String youhui = "";
				String[] bestBenefits = bestYouHuiId.split(",");
				for (int i = 0; i < bestBenefits.length; i++) {
					String pbHQL2 = "from ProjectBenefits pb where pb.benefitId = '" + bestBenefits[i] + "'";
					ProjectBenefits pb = (ProjectBenefits) baseDao.loadObject(pbHQL2);
					pbList.add(pb);
					if (pb.getType() == 0) {
						nowPrice = nowPrice - pb.getBenefitMoney();
					} else if (pb.getType() == 1) {
						nowPrice = nowPrice *(1-pb.getBenefitPercent()/100);
					}
					youhui += pb.getCaption()+ ",";
				}
				double zuigaoYouhui = house.getListPrice() -nowPrice;
				String zuidipriceStr = df.format(nowPrice);
				double zuidiPrice = Double.parseDouble(zuidipriceStr);
				String zuigaoYouhuiStr = df.format(zuigaoYouhui);
				double highestBenefits = Double.parseDouble(zuigaoYouhuiStr);
				double youhuilidu = zuidiPrice/house.getListPrice();
				String youhuiliduStr = df.format(youhuilidu);
				double lidu = Double.parseDouble(youhuiliduStr);
				//最低成交价
				map.put("zuidiPrice",zuidiPrice);
				//优惠力度
				map.put("lidu", lidu);
				//优惠组合
				map.put("youhui", youhui);
			}
			
			if (pbList != null) {
				map.put("youhuiList", pbList);
			}
			map.put("deliverStandard", project.getDeliverStandard());
			map.put("projectName", project.getProjectName());
			map.put("propertyAddress", project.getPropertyAddress());
			map.put("landArea", project.getLandArea());
			map.put("groundArea", project.getGroundArea());
			map.put("underGroundArea", project.getUnderGroundArea());
			map.put("unitCount", project.getUnitCount());
			map.put("volumeRatio", project.getVolumeRatio());
			map.put("density", project.getDensity()+"%");
			map.put("afforestationRatio", project.getAfforestationRatio()+"%");
			map.put("developer", project.getDeveloper());
			map.put("investor", project.getInvestor());
			map.put("manager", project.getManager());
			map.put("propertyCost", project.getPropertyCost());
			map.put("rightsYears", project.getRightsYears());
			map.put("startTime", project.getStartTime());
			map.put("deliverTime", project.getDeliverTime());
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 房型动态菜单 (web房型)
	 * @return
	 */
	@Override
	public TreeSet<String> findHouseCaptionByProIdWeb(String projectId) {
		String hql = "from HouseType where projectId = '"+projectId+"' ";
		List<HouseType> htList = baseDao.findByHql(hql);
		TreeSet<String> ts = new TreeSet<>();
		for(HouseType ht : htList){
			String housType = ht.getHousType();
			if (housType != null && !"".equals(housType)){
				ts.add(housType);
			}
		}
		return ts;
	}
	
	/**
	 * 房型动态菜单 (房型)
	 * @return
	 */
	@Override
	public TreeSet<String> findHouseCaptionByProId(User user, String projectId) {
		//可出售房子list
		//查案场下可售房源
		String houseHQL = "from House where projectId = '"+projectId+"' and (houseStatus = 1 or houseStatus = 4) ";
		Set<Role> rs = user.getRoleId();
		if (user.getRoleId()!=null && !"".equals(user.getRoleId())){
			for (Role role : rs) {
				if (role.getRoleId() == 1 || role.getRoleId() == 2){
					houseHQL += " and isOpen = 1";
				}
			}
		}
		
		List<House> hList = baseDao.findByHql(houseHQL);
		/*List<House> hList2 = new ArrayList<>();
		//房子已经被认购同意的不让其他人发起认购
		for (House house : hList) {
			String crHQL = "from ContractRecords where houseNum = '"+house.getHouseNum()+"' "
					+ " and recordStatus = 1";
			ContractRecords cr = (ContractRecords) baseDao.loadObject(crHQL);
			if (cr == null || "".equals(cr)){
				hList2.add(house);
			}
		}*/
		
		String hql = "from HouseType where projectId = '"+projectId+"' ";
		List<HouseType> htList = baseDao.findByHql(hql);
		TreeSet<String> ts = new TreeSet<>();
		for (House house : hList) {
			for(HouseType ht : htList){
				String housType = ht.getHousType();
				if (housType != null && !"".equals(housType)){
					if (house.getHouseTypeId().equals(ht.getHouseTypeId())){
						ts.add(housType);
					}
				}
			}
		}
		return ts;
	}

	/**
	 * 面积动态菜单app
	 * @return
	 */
	@Override
	public TreeSet<Double> findHouseUsefulAreaByProId(User user,String projectId) {
		//查案场下可售房源
		String houseHQL = "from House where projectId = '"+projectId+"' and (houseStatus = 1 or houseStatus = 4) ";
		Set<Role> rs = user.getRoleId();
		if (user.getRoleId()!=null && !"".equals(user.getRoleId())){
			for (Role role : rs) {
				if (role.getRoleId() == 1 || role.getRoleId() == 2){
					houseHQL += " and isOpen = 1";
				}
			}
		}
		List<House> hList = baseDao.findByHql(houseHQL);
		TreeSet<Double> ts = new TreeSet<>();
		for (House house : hList) {
			Double usefulArea = house.getUsefulArea();
			if (usefulArea != null && !"".equals(usefulArea)){
				ts.add(usefulArea);
			}
		}
		return ts;
	}

	/**
	 *楼层动态菜单app
	 * @return
	 */
	@Override
	public TreeSet<Integer> findHouseFloorByProId(User user, String projectId) {
		//查案场下可售房源
		String houseHQL = "from House where projectId = '"+projectId+"' and (houseStatus = 1 or houseStatus = 4) ";
		Set<Role> rs = user.getRoleId();
		if (user.getRoleId()!=null && !"".equals(user.getRoleId())){
			for (Role role : rs) {
				if (role.getRoleId() == 1 || role.getRoleId() == 2){
					houseHQL += " and isOpen = 1";
				}
			}
		}
		List<House> hList = baseDao.findByHql(houseHQL);

		TreeSet<Integer> ts = new TreeSet<>();
		for (House house : hList) {
			Integer floor = house.getFloor();
			if (floor != null && !"".equals(floor)){
				ts.add(floor);
			}
		}
		return ts;
	}

	/**
	 *市区联动动态菜单
	 * @return
	 */
	@Override
	public List<Map<String, String>> findCityAreaByShi(String shengOrShi) {
		String hql = "from CountryProvinceInfo cp where cp.upCityId = '" + shengOrShi + "'";
		List<CountryProvinceInfo> cpList = baseDao.findByHql(hql);
		
		List<Map<String, String>> lmList = new ArrayList<>();
		if (cpList.size()>0) {
			for (CountryProvinceInfo countryProvinceInfo : cpList) {
				Map<String,String> cityMenu = new HashMap<>();
				cityMenu.put("upcityId", countryProvinceInfo.getUpCityId());
				cityMenu.put("upcityName", countryProvinceInfo.getUpCityName());
				cityMenu.put("cityId", countryProvinceInfo.getCityId());
				cityMenu.put("cityName", countryProvinceInfo.getCityName());
				lmList.add(cityMenu);
			}
		} else {
			String shiHQL = "from CountryProvinceInfo cp where cp.cityId = '"+shengOrShi+"'";
			CountryProvinceInfo cp = (CountryProvinceInfo) baseDao.loadObject(shiHQL);
			Map<String,String> cityMenu = new HashMap<>();
			cityMenu.put("upcityId", cp.getUpCityId());
			cityMenu.put("upcityName", cp.getUpCityName());
			cityMenu.put("cityId", cp.getCityId());
			cityMenu.put("cityName", cp.getCityName());
			lmList.add(cityMenu);
		}
		return lmList;
	}
	
	@Override
	public List<CountryProvinceInfo> findProvinceByCityNo(String cityNo) {
		String hql = "from CountryProvinceInfo cp where cp.upCityId = '" + cityNo + "'";
		List<CountryProvinceInfo> cpList = baseDao.findByHql(hql);
		
		return cpList;
	}
	

	
	/**
	 *根据省市区id获得Name
	 * @return
	 */
	@Override
	public String findCityNameById(String shengOrShi) {
		String hql = "from CountryProvinceInfo where cityId = '" + shengOrShi + "'";
		CountryProvinceInfo cp = (CountryProvinceInfo) baseDao.loadObject(hql);
		String cityName = "";
		if (cp != null && !"".equals(cp)){
			cityName = cp.getCityName();
		}
		return cityName;
	}
	
	@Override
	public String findCityIdByName(String cityName) {
		String hql = "from CountryProvinceInfo where cityName = '" + cityName + "'";
		CountryProvinceInfo cp = (CountryProvinceInfo) baseDao.loadObject(hql);
		String cityId = "";
		if(cp != null && !"".equals(cp)){
			cityId = cp.getCityId();
		}
		return cityId;
	}
	
	@Override
	public String addHouseExcel(User user, MultipartFile file) {

		Integer addFlag = 0;
		Integer updateFlag = 0;
		Integer error = 0;
		//已经卖出去的房源
		Integer dealFlag = 0;
		//在售房源
		Integer dealIngCount = 0;
		try {
			String sepa = File.separator;
			String realPath = SysContent.getSession().getServletContext().getRealPath("/static/upload");
			// 得到上传时的文件名
			String fileRename = SysContent.getFileRename(file.getOriginalFilename());
			// excel完整的路径
			String fileRpn = realPath + sepa + fileRename;
			File importFile = new File(fileRpn);
			FileUtils.copyInputStreamToFile(file.getInputStream(), importFile);
			ExcelHelper exh = JxlExcelHelper.getInstance(importFile);
			String[] fieldNames = new String[] { "houseNo", "presalePermissionInfo", "district", "buildingNo", "unit",
					"floor", "direct", "buildArea", "usefulArea", "listPrice", "minimumPrice", "shopPrice",
					"decorationStandard", "houseTypeName","houseKind"};
			List<House> importHouseList = exh.readExcel(House.class, fieldNames, true);
			/* 如果导入该项目下的重复房源将不进行添加 */
			String projectId = user.getParentId();
			for (House h : importHouseList) {
				if (h.getHouseNo() != null && !h.getHouseNo().trim().equals("")) { // 进行表格的空项判断
					if(StringUtils.isEmpty(h.getHouseTypeName()) || StringUtils.isEmpty(h.getPresalePermissionInfo())){
						error++;
						continue;
					}
					// 进行房源是否重复查询
					String houseHql = "from House where projectId = '" + projectId + "' and houseNo = '"
							+ h.getHouseNo() + "' and district = '" + h.getDistrict() + "' and buildingNo = '"
							+ h.getBuildingNo() + "' and unit = '" + h.getUnit() + "' and floor = '" + h.getFloor()
							+ "' and houseStatus != 2";
					House house = (House) baseDao.loadObject(houseHql);
					if (house != null) { // 如果数据库已经存在该房源，就更新数据
						if (house.getHouseStatus() == 5){
							dealFlag++;
						} else if(house.getHouseStatus() == 4) {
							dealIngCount++;
						} else {
							house.setHouseNo(h.getHouseNo());
							house.setPresalePermissionInfo(h.getPresalePermissionInfo());
							house.setDistrict(h.getDistrict());
							house.setBuildingNo(h.getBuildingNo());
							house.setUnit(h.getUnit());
							house.setFloor(h.getFloor());
							house.setDirect(h.getDirect());
							house.setBuildArea(h.getBuildArea());
							house.setUsefulArea(h.getUsefulArea());
							house.setListPrice(h.getListPrice());
							house.setMinimumPrice(h.getMinimumPrice());
							house.setShopPrice(h.getShopPrice());
							house.setDecorationStandard(h.getDecorationStandard());
							house.setHouseTypeName(h.getHouseTypeName());
							house.setHouseKind(h.getHouseKind());
							addOrUpdate(house);
							updateFlag += 1;
						}
					} else { // 如果不存在就添加
						String hyHql = "from HouseType where caption = '" + h.getHouseTypeName() + "' and projectId ='"+user.getParentId()+"'";
						HouseType ht = (HouseType) baseDao.loadObject(hyHql);
						h.setProjectId(user.getParentId());
						h.setHouseStatus(0);
						if (ht != null) {
							h.setHouseTypeId(ht.getHouseTypeId());
							h.setIsOpen(0);//房源默认不可见
						}
						addOrUpdate(h);
						addFlag += 1;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String info = "";
		if(addFlag > 0){
			info += "本次成功添加房源"+ addFlag + "套";
		}
		if(updateFlag > 0){
			info += " 更新房源" + updateFlag + "套";
		}
		if(error > 0){
			info += " 有 " + error + " 套房源信息错误(预售证号或房型编号)";
		}
		if(dealIngCount > 0 || dealFlag > 0){
			info += "(";
			if(dealIngCount > 0){
				info += " " + dealIngCount + "套正在出售中 ";
			}
			if(dealFlag > 0){
				info += " " + dealFlag + "套已经出售 ";
			}
			info += ",不允许更新)";
		}
		return info;

	}

	/**
	 *精选页面默认的城市
	 */
	@Override
	public Map<String, String> findLocationCity(User user,String shengOrShi,String citySigle,CitySessionDTO  csdto) {
	
		Map<String, String> map = new HashMap<>();
		if (citySigle!=null && !"".equals(citySigle)){
			String hql = "from CountryProvinceInfo where cityId = '" + shengOrShi + "'";
			CountryProvinceInfo cp = (CountryProvinceInfo) baseDao.loadObject(hql);
			map.put("cityId", cp.getCityId());
			map.put("cityName", cp.getCityName());
			csdto.setCityId(cp.getCityId());
			csdto.setCityName(cp.getCityName());
			
		} else {
			map.put("cityId", csdto.getCityId());
			map.put("cityName", csdto.getCityName());
		}
		return map;
	}

	
	/**
	 *置业顾问project的区域获得
	 */
	@Override
	public String findAgentProjectCity(User user) {
		String pHQL = "from Project where projectId = '" + user.getParentId() + "'";
		Project p = (Project) baseDao.loadObject(pHQL);
		String city = p.getCity();
		String[] cityStr = city.split("-");
		String hql = "from CountryProvinceInfo where cityId = '" + cityStr[2] + "'";
		CountryProvinceInfo cp = (CountryProvinceInfo) baseDao.loadObject(hql);
		
		String cityName = cp.getCityName();
		return cityName;
		
	}
	
	
	@Override
	//加载所有省
	public List<CountryProvinceInfo> findAllProvince() {
		List<CountryProvinceInfo> allProvince = baseDao.findByHql("from CountryProvinceInfo where cityLevel='省'");
		return allProvince;
	}
	
	@Override
	public Map<String,String> getCityByParentId(String parentId){
		if(parentId != null && !parentId.equals("")){
			Map<String, String> map = new HashMap<>();
			Integer shopId = Integer.parseInt(parentId);
			String hql = "from Shops where shopId = "+shopId;
			List<Shops> shop = baseDao.findByHql(hql);
			String cityNum = shop.get(0).getCity();
			String[] cityNumber = cityNum.split("-");
			map.put("provId", cityNumber[0]);
			map.put("cityId", cityNumber[1]);
			map.put("areaId", cityNumber[2]);
			List<CountryProvinceInfo> prov = baseDao.findByHql("from CountryProvinceInfo where cityId ="+cityNumber[0]);
			String provName = prov.get(0).getCityName();
			List<CountryProvinceInfo> city = baseDao.findByHql("from CountryProvinceInfo where cityId ="+cityNumber[1]);
			String cityName = city.get(0).getCityName();
			List<CountryProvinceInfo> area = baseDao.findByHql("from CountryProvinceInfo where cityId ="+cityNumber[2]);
			String areaName = area.get(0).getCityName();
			map.put("provName", provName);
			map.put("cityName", cityName);
			map.put("areaName", areaName);
			
			return map;
		}
		return null;
	}

	/**
	 * 房源导出
	 */
	@Override
	public List<HouseExportDTO> findThisProjectHouse(String parentId,String[] selectedHIds) {
		List houseList = new ArrayList<>();
		if(parentId!=null && !parentId.equals("")){
			//String houseHql = "from House where projectId = '"+parentId+"'";
			//List<House> list = baseDao.findByHql(houseHql);
			for(String  hId: selectedHIds){
				House h = (House) baseDao.loadById(House.class, Integer.parseInt(hId));
				HouseType ht = (HouseType) baseDao.loadById(HouseType.class, h.getHouseTypeId());
				HouseExportDTO heDto = new HouseExportDTO();
				heDto.createHouseExportDto(h);
				heDto.setHouseTypeName(ht.getCaption());
				houseList.add(heDto);
			}
		}
		return houseList;
	}


	//客户端添加房源
	@Override
	public Map addOrUpdateFromClient(House h, String houseTypeName, String preSellNum) {
		Map map = new HashMap<>();
		//户型
		String htHql = "from HouseType where 1=1 and projectId = '"+h.getProjectId()+"' caption = '"+houseTypeName+"'";
		HouseType ht = (HouseType) baseDao.loadObject(htHql);
		if(ht!=null){
			String htId = ht.getHouseTypeId();
			h.setHouseTypeId(htId);
		}else{
			ht.setHouseTypeId(SysContent.uuid());
			//户型默认未启用
			ht.setIsUse("no");
			ht.setProjectId(h.getProjectId());
			baseDao.saveOrUpdate(ht);
			h.setHouseTypeId(ht.getHouseTypeId());
		}
		//预售证
		Project pro = projectService.findProjectById(h.getProjectId());
		String url = pro.getPresalePermissionURL();
		List<Map> jList = JSON.parseObject(url, List.class);
		Boolean unPre = false;
		for(Map m : jList){
			if(m.get("imNum").equals(preSellNum)){//有预售证
				h.setPresalePermissionInfo(m.get("imNum").toString());
				break;
			}else{//没有预售证
				unPre = true;
			}
		}
		//添加预售证
		if(unPre){
			String urlStr = pro.getPresalePermissionURL();
			// 把urlStr字符串转换成listMap数据结构
			List<HashMap> listMap = new ArrayList<>();
			if (urlStr != null && !urlStr.equals("")) {
				listMap = JSON.parseArray(urlStr, HashMap.class);
			}
			// 存储预售证编号，预售证图片路径
			Map<String, String> picMap = new HashMap<String, String>();
			picMap.put("imNum", preSellNum);
			picMap.put("path", null);
			// 把数据添加到集合中
			listMap.add((HashMap) picMap);
			// 把每个map数据结构数据转成标准json格式，存入listStr集合中
			List<String> listStr = new ArrayList<>();
			for (Map m : listMap) {
				JSONObject jsonObject = JSONObject.fromObject(m);
				listStr.add(jsonObject.toString());
			}
			pro.setPresalePermissionURL(listStr.toString());
			// 更新项目对象，保存预售证信息
			baseDao.saveOrUpdate(pro);
			h.setPresalePermissionInfo(preSellNum);
		}
		baseDao.saveOrUpdate(h);
		map.put("msg", "添加成功");
		return map;
	}


	//通过房源信息获取房源
	@Override
	public House findHouseByProperty(String proId, String buildingNo, String unit, Integer floor, String houseNo) {
		String hHql = "from House where projectId = '"+proId+"' and buildingNo = '"+buildingNo+"' and unit = '"+unit+"' and "
				+ "floor = "+floor+" and houseNo = '"+houseNo+"'";
		House h = (House) baseDao.loadObject(hHql);
		return h;
	}

	//房源信息导入
	@Override
	public void addOrUpdateObj(Object obj) {
		if(obj.getClass().equals(HouseDetails.class)){
			HouseDetails hd = (HouseDetails) obj;
			baseDao.save(hd);
		}else{
			if(obj.getClass().equals(House.class)){
				House newh = (House) obj;
				if(newh!=null && newh.getHouseNum()!=null){
					House oldh = (House) baseDao.loadById(House.class, newh.getHouseNum());
					House hAllProperty = HouseDTO.assignmentHouseProperty(oldh, newh);
					baseDao.saveOrUpdate(hAllProperty);
				}
			}else if(obj.getClass().equals(ProjectBuilding.class)){
				ProjectBuilding newpb = (ProjectBuilding) obj;
				if(newpb!=null && newpb.getBuildingId()!=null){
					ProjectBuilding oldpb = (ProjectBuilding) baseDao.loadById(ProjectBuilding.class, newpb.getBuildingId());
					ProjectBuilding pbAllProperty = HouseDTO.assignmentProjectBuildingProperty(oldpb, newpb);
					baseDao.saveOrUpdate(pbAllProperty);
				}
			}else if(obj.getClass().equals(ProjectBuildingUnit.class)){
				ProjectBuildingUnit newpbu = (ProjectBuildingUnit) obj;
				if(newpbu!=null && newpbu.getUnitId()!=null){
					ProjectBuildingUnit oldpbu = (ProjectBuildingUnit) baseDao.loadById(ProjectBuildingUnit.class, newpbu.getUnitId());
					ProjectBuildingUnit pbuAllProperty = HouseDTO.assignmentProjectBuildingUnitProperty(oldpbu,newpbu);
					baseDao.saveOrUpdate(pbuAllProperty);
				}
			}else if(obj.getClass().equals(Project.class)){
				Project newp = (Project) obj;
				if(newp!=null && newp.getProjectId()!=null && !newp.getProjectId().equals("")){
					Project oldp = (Project) baseDao.loadById(Project.class, newp.getProjectId());
					Project pAllProperty = HouseDTO.assignmentProjectProperty(oldp, newp);
					baseDao.saveOrUpdate(pAllProperty);
				}
			}
		}
	}


	@Override
	public void updateOrAddCollectHouse(String userId, Integer houseNum, int flag) {
		HouseCollect hc = (HouseCollect)baseDao.loadObject("from HouseCollect where targetId = '"+userId+"'");
		if(flag==1){//添加收藏
			if(hc!=null){
				String collectHouseId = hc.getCollectHouseId();
				if(!StringUtils.isEmpty(collectHouseId)){
					String c = collectHouseId+","+houseNum;
					hc.setCollectHouseId(c);
					baseDao.saveOrUpdate(hc);
				}
			}else{
				HouseCollect h = new HouseCollect();
				h.setCollectHouseId(houseNum+"");
				h.setTargetId(userId);
				baseDao.saveOrUpdate(h);
			}
		}
		if(flag==2){//删除收藏
			if(hc!=null){
				String collectHouseId = hc.getCollectHouseId();
				if(!StringUtils.isEmpty(collectHouseId)){
					String[] split = collectHouseId.split(",");
					List<String> ids = new ArrayList<>();
					for(String s : split){
						String trim = s.trim();
						if(!trim.equals(houseNum.toString())){
							ids.add(trim);
						}
					}
					if(ids.size()>0){
						String string = ids.toString();
						String substring = string.substring(1, string.length()-1);
						hc.setCollectHouseId(substring);
					}else{
						hc.setCollectHouseId(null);
					}
					baseDao.saveOrUpdate(hc);
				}else{
					throw new RuntimeException("该收藏不存在");
				}
			}else{
				throw new RuntimeException("该收藏不存在");
			}
		}
		
	}


	@Override
	public List<SalesHouseDTO> findCollectHouse(String userId) {
		HouseCollect hc =(HouseCollect) baseDao.loadObject("from HouseCollect where targetId = '"+userId+"'");
		List<SalesHouseDTO> salesHouseList = new ArrayList<>();
		if(hc!=null){
			String collectHouseId = hc.getCollectHouseId();
			if(!StringUtils.isEmpty(collectHouseId)){
				String[] split = collectHouseId.split(",");
				List<House> houses = new ArrayList<>();
				for(String s : split){
					int id = Integer.parseInt(s.trim());
					House h =(House) baseDao.loadObject("from House where houseNum = "+id);
					houses.add(h);
				}
				for(House house : houses){
					SalesHouseDTO shdto = new SalesHouseDTO();
					String houseTypeHQL = "from HouseType where houseTypeId = '"+ house.getHouseTypeId() + "'";
					HouseType ht = (HouseType) baseDao.loadObject(houseTypeHQL);
					shdto.setProjectId(house.getProjectId());
					//houseNum
					shdto.setHouseNum(house.getHouseNum());
					//房号
					shdto.setHouseNo(house.getHouseNo());
					//楼号
					shdto.setBuildingNo(house.getBuildingNo());
					//单元号
					shdto.setUnit(house.getUnit());
					//楼层
					shdto.setFloor(house.getFloor());
					//用地面积
					shdto.setUsefulArea(Double.parseDouble(SysContent.get2Double(house.getUsefulArea())));
					shdto.setBuildArea(Double.parseDouble(SysContent.get2Double(house.getBuildArea())));
					//列表价
					shdto.setListPrice(SysContent.get2Double(house.getListPrice()));
					shdto.setIsOpen(house.getIsOpen());
					if (ht != null && !"".equals(ht)){
						//房型
						shdto.setHouseStyle(ht.getHousType());
					}
					salesHouseList.add(shdto);
				}
			}else{
				return salesHouseList;
			}
		}
		return salesHouseList;
	}

}
