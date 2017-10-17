package com.sc.tradmaster.service.shop.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectBenefits;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.ProjectPics;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.shop.ShopService;
import com.sc.tradmaster.utils.AddressUtil;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

/**
 * 门店服务层
 * 
 * @author cdh 2017-07-05
 */
@Service(value = "shopService")
public class ShopServiceImpl implements ShopService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private BaseDao baseDao;

	@Override
	public Integer addNewShop(Shops shop) {

		if (shop == null) {
			return 0;
		}
		try {

			/* 进行门店持久化 */
			shop.setApplyTime(DateUtil.format(new Date()));
			shop.setShopStatus(0);
			String lngLat = shop.getLongitude() + "," + shop.getLatitude();
			shop.setLngLat(lngLat);
			//shop.setCity(province + "-" + market + "-" + area);
			shop.setInSystemStutas(0);
			baseDao.save(shop);

			/* 进行User的持久化处理 */
			User user = new User();
			user.setUserId(UUID.randomUUID().toString());
			user.setCreateTime(shop.getApplyTime());
			user.setParentId(shop.getShopId().toString());
			user.setPhone(shop.getPhone());
			user.setUserCaption(shop.getContactPerson());
			user.setUserStatus(0);
			String password = shop.getPhone().substring(5);
			// 进行密码的加密
			String pwMd5 = SysContent.md5(password);
			user.setPassword(pwMd5);

			baseDao.save(user);

			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}

	}

	@Override
	public Integer addShopInfo(Shops shop, MultipartFile photoPic, MultipartFile licensePic) {

		try {
			String[] strs = shop.getLngLat().split(",");
			shop.setLongitude(strs[0]);
			shop.setLatitude(strs[1]);

			if (!photoPic.isEmpty() && photoPic.getSize() > 0) {
				String photoName = photoPic.getOriginalFilename();
				if (photoName.matches("(?i).+?\\.(jpg|gif|bmp|png|jpeg)")) {
					String photoRename = SysContent.getFileRename(photoName);
					String photoSavePath = PicUploadToYun.upload(photoRename, photoPic, SmsContext.SHOP_PIC);
					if (photoSavePath != null && !photoSavePath.equals("")) {
						shop.setPhoto(photoSavePath);
					}
				}
			}

			if (!licensePic.isEmpty() && licensePic.getSize() > 0) {
				String licenseName = licensePic.getOriginalFilename();
				if (licenseName.matches("(?i).+?\\.(jpg|gif|bmp|png|jpeg)")) {
					String licensePhotoRename = SysContent.getFileRename(licensePic.getOriginalFilename());
					String licensePhotophotoSavePath = PicUploadToYun.upload(licensePhotoRename, licensePic,
							SmsContext.SHOP_PIC);
					if (licensePhotophotoSavePath != null && !licensePhotophotoSavePath.equals("")) {
						shop.setLicensePhoto(licensePhotophotoSavePath);
					}
				}
			}

			baseDao.save(shop);
		} catch (Exception e) {

			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public Integer findShopAgentIsExist(User user) {

		Shops shop = (Shops) baseDao.loadById(Shops.class, user.getParentId());
		List<User> uList = baseDao.findByHql("from User where parentId = '" + shop.getShopId() + "'");
		Integer flag = 0;
		for (User u : uList) {
			Set<Role> role = u.getRoleId();
			for (Role r : role) {
				if (r.getRoleId() == 1) {
					flag++;
				}
			}
		}

		if (flag == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public List<Map<String, Object>> findProjectListInArea(List<Map<String, String>> areas) {
		if (areas.size() == 0) {
			return null;
		}

		List<Map<String, Object>> list = new ArrayList<>();

		for (Map<String, String> area : areas) {
			// 项目数量
			Integer projectCount = 0;
			// 平均佣金
			Double commission = 0D;
			// 区域名称
			String areaName = "";
			// 平台认证数
			Integer platformNum = 0;
			// 可售房源
			Integer houseCount = 0;
			// 已售房源
			Integer buyHouseCount = 0;
			// 项目照片
			String projectPic = "";

			areaName = area.get("cityName");

			Map<String, Object> map = new HashMap<>();
			// 查出该区内的所有项目
			String projectHql = "from Project where city like '%" + area.get("cityId") + "%'";
			List<Project> projectList = baseDao.findByHql(projectHql);
			projectCount = projectList.size();
			for (Project pro : projectList) {
				// 是否入住平台
				if (pro.getProInSystemStutas() != null && !"".equals(pro.getProInSystemStutas())) {
					if (pro.getProInSystemStutas() == 1) {
						platformNum++;
					}
				}

				// 佣金
				String guideHql = "from ProjectGuide where projectId = '" + pro.getProjectId() + "'";
				ProjectGuide guide = (ProjectGuide) baseDao.loadObject(guideHql);
				if (guide != null) {
					commission += guide.getDaiKanMoney();
				}
				// 项目效果图
				String picHql = "from ProjectPics where projectId = '" + pro.getProjectId() + "'";
				List<ProjectPics> picList = baseDao.findByHql(picHql);
				if (picList.size() > 0) {

					projectPic = picList.get(0).getUrl();
				}
				// 房源
				String houseHql = "from House where projectId = '" + pro.getProjectId() + "'";
				List<House> houseList = baseDao.findByHql(houseHql);
				for (House h : houseList) {
					boolean flag = false;
					String recordHql = "from ContractRecords where projectId = '" + pro.getProjectId()
							+ "' and houseNum =" + h.getHouseNum();
					List<ContractRecords> recordList = baseDao.findByHql(recordHql);
					for (ContractRecords cr : recordList) {
						if (cr.getRecordStatus() != null && !"".equals(cr.getRecordStatus())) {
							if (cr.getRecordStatus() == 5) {
								flag = true;
							}
						}
					}

					if (flag) {
						buyHouseCount++;
					} else {
						houseCount++;
					}

				}

			}
			map.put("projectCount", projectCount);
			map.put("commission", SysContent.get2Double(commission / projectList.size()));
			map.put("areaName", areaName);
			map.put("platformNum", platformNum);
			map.put("houseCount", houseCount);
			map.put("buyHouseCount", buyHouseCount);
			map.put("projectPic", projectPic);
			map.put("areaId", area.get("cityId"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 根据省市区id获得Name
	 * 
	 * @return
	 */
	@Override
	public String findCityNameById(String shengOrShi) {
		String hql = "from CountryProvinceInfo where cityId = '" + shengOrShi + "'";
		CountryProvinceInfo cp = (CountryProvinceInfo) baseDao.loadObject(hql);
		String cityName = "";
		if (cp != null && !"".equals(cp)) {
			cityName = cp.getCityName();
		}
		return cityName;
	}

	@Override
	public void findProjectListByAddressAndTag(String maxLongitudes, String minLongitudes, String maxLatitudes,
			String minLatitudes, String[] tags, String[] houseTags, String projectId, Page page) {

		List<Map<String, Object>> list = new ArrayList<>();
		String hql = "select * from ";
		String pageHql = "";
		if (projectId == null || "".equals(projectId)) {

			if (tags != null && tags.length > 0) {
				hql += "(select * from t_projects where projectId in (select targetId from t_tagsrelation  where 1=1 ";
				for (int i = 0; i < tags.length; i++) {
					hql += "  and originalTags like '%" + tags[i] + "%'";
				}
				hql += " ))";
			} else {
				hql += " t_projects ";
			}

			hql += " p where 1=1 ";
			if (houseTags != null && houseTags.length > 0) {
				hql += " and p.projectId in (select h.projectId from t_projecthouses h where h.houseNum in (select targetId from t_tagsrelation where 1=1 ";
				for (int x = 0; x < houseTags.length; x++) {

					hql += " and originalTags like '%" + houseTags[x] + "%' ";
				}
				hql += ") and h.houseStatus = 1 and isOpen = 1 ) ";// 房源一定是要对经纪人可见，并且是上架的
			}
			hql += " and p.saleLongitude >= '" + minLongitudes + "' " + "and p.saleLongitude <= '" + maxLongitudes
					+ "' and p.saleLatitude >= '" + minLatitudes + "'" + " and p.saleLatitude <= '" + maxLatitudes
					+ "'";
			pageHql = hql;
			hql += " limit " + page.getStart() + "," + page.getLimit();
		} else {
			hql += " t_projects where projectId = '" + projectId + "'";
			pageHql = hql;
		}

		List<Project> project = baseDao.queryBySql(hql, Project.class);

		for (Project p : project) {
			int buyHouseCount = 0;
			int houseCount = 0;
			Map<String, Object> map = new HashMap<>();
			map.put("projectId", p.getProjectId());
			map.put("project", p);
			String guideHql = "from ProjectGuide where projectId = '" + p.getProjectId() + "'";
			ProjectGuide guide = (ProjectGuide) baseDao.loadObject(guideHql);
			map.put("guide", guide);
			map.put("proInfo", findOneProjectInfo(p.getProjectId()));
			String areaName = findCityNameById(p.getCity().substring(p.getCity().length() - 6, p.getCity().length()));
			map.put("areaName", areaName);
			// 房源
			String houseHql = "from House where projectId = '" + p.getProjectId() + "'";
			List<House> houseList = baseDao.findByHql(houseHql);
			for (House h : houseList) {

				boolean flag = false;
				String recordHql = "from ContractRecords where projectId = '" + p.getProjectId() + "' and houseNum ="
						+ h.getHouseNum();
				List<ContractRecords> recordList = baseDao.findByHql(recordHql);
				for (ContractRecords cr : recordList) {
					if (cr.getRecordStatus() == 5) {
						flag = true;
					}
				}

				if (flag) {
					buyHouseCount++;
				} else {
					// 可售房源必须是对经纪人可见的并且是上架的房源
					if(h.getHouseStatus() != null && !"".equals(h.getHouseStatus()) && h.getIsOpen() != null && !"".equals(h.getIsOpen())){
						if ((h.getHouseStatus() == 1 || h.getHouseStatus() == 4) && h.getIsOpen() == 1) {
							houseCount++;
						}
					}
				}

			}

			map.put("buyHouseCount", buyHouseCount);
			map.put("houseCount", houseCount);

			list.add(map);
		}
		List<Project> p = baseDao.queryBySql(pageHql, Project.class);
		Integer total = p.size();
		page.setWheres(total.toString());
		page.setTotal(total);
		page.setRoot(list);

	}

	@Override
	public Map<String, Object> findOneProjectInfo(String projectId) {

		Map<String, Object> map = new HashMap<>();

		// 图片集合
		List<String> picList = new ArrayList<>();

		String projectHql = "from Project where projectId = '" + projectId + "'";
		Project project = (Project) baseDao.loadById(Project.class, projectId);

		String picHql = "from ProjectPics where projectId = '" + projectId + "'";
		List<ProjectPics> pictureList = baseDao.findByHql(picHql);
		for (ProjectPics pics : pictureList) {
			String imgUrl = pics.getUrl();
			if (imgUrl != null && !"".equals(imgUrl)) {
				picList.add(imgUrl);
			}
		}
		map.put("proInSystemStutas", project.getProInSystemStutas());
		map.put("picList", picList);
		map.put("projectName", project.getProjectName());
		map.put("projectAddress", project.getSaleAddress());
		map.put("projectId", projectId);
		// 总户数
		map.put("unitCount", project.getUnitCount());
		// 开工时间
		map.put("startTime", project.getStartTime());
		// 项目用地面积
		map.put("deliverTime", project.getDeliverTime());
		//
		map.put("landArea", project.getLandArea());
		// 密度
		map.put("density", project.getDensity());
		// 地上建筑面积
		map.put("groundArea", project.getGroundArea());
		// 绿化率
		map.put("afforestationRatio", project.getAfforestationRatio());
		// 开发商
		map.put("developer", project.getDeveloper());
		// 底下面积
		map.put("underGroundArea", project.getUnderGroundArea());
		// 物业费
		map.put("propertyCost", project.getPropertyCost());
		// 物业管理
		map.put("manager", project.getManager());

		return map;
	}

	@Override
	public void findHouseList(String projectId, String[] houseTags, Page page, Integer status) {

		List<Map<String, Object>> list = new ArrayList<>();

		String hql = "SELECT * from ";
		String totalHql = "";
		if (houseTags.length > 0) {
			hql += " (SELECT * from t_projecthouses where houseNum in (SELECT targetId FROM t_tagsrelation where 1=1 ";
			for (int i = 0; i < houseTags.length; i++) {
				hql += " and originalTags like '%" + houseTags[i] + "%' ";
			}
			hql += " )) ";
		} else {
			hql += " t_projecthouses ";
		}

		hql += " p where 1=1 ";
		hql += " and p.projectId = '" + projectId + "'";
		if (status == 1) {
			hql += " and (p.houseStatus = 1 or p.houseStatus = 4) ";
			hql += " and p.isOpen = 1 ";
		}
		totalHql = hql;
		hql += " LIMIT " + page.getStart() + "," + page.getLimit();

		String guideHql = "from ProjectGuide where projectId = '" + projectId + "'";
		ProjectGuide guide = (ProjectGuide) baseDao.loadObject(guideHql);

		List<House> houses = baseDao.queryBySql(hql, House.class);

		for (House house : houses) {
			Map<String, Object> map = new HashMap<>();
			map.put("house", house);
			map.put("daiKanMoney", guide.getDaiKanMoney());
			boolean flag = true;
			if (status == 0) {
				String houseTypeHql = "from HouseType where houseTypeId = '" + house.getHouseTypeId() + "'";
				List<HouseType> houseTypeList = baseDao.findByHql(houseTypeHql);
				if (houseTypeList.size() == 0) {
					flag = false;
				}
			}
			// 房源类型
			HouseType houseType = null;
			if (flag) {
				houseType = (HouseType) baseDao.loadById(HouseType.class, house.getHouseTypeId());
			}
			map.put("houseType", houseType);
			Map<String, Object> proMap = findOneProjectInfo(house.getProjectId());
			map.put("projectInfo", proMap);

			String currentTime = DateUtil.format(new Date());
			// 案场优惠信息
			String benefits = "from ProjectBenefits where projectId = '" + projectId + "' and startTime <= '"
					+ currentTime + "' " + " and endTime >= '" + currentTime + "' ";
			List<ProjectBenefits> benefitList = baseDao.findByHql(benefits);

			/**
			 * 优惠金额的算法： 遍历每条优惠定义，找出所有的优惠金额和比率
			 * 定义一个优惠金额，每次遍历的优惠金额都进行叠加，优惠比率进行计算出其优惠金额叠加在优惠金额上
			 * 将所有的优惠金额算出之后，用列表价减去优惠金额得到的数值 再除去列表价 就能得到优惠金额
			 */
			// 总优惠金额
			Double ben = 0D;
			// 优惠比率
			String benLv = "0";
			for (ProjectBenefits pb : benefitList) {
				if (pb.getBenefitMoney() != null && !"".equals(pb.getBenefitMoney())) {
					ben += pb.getBenefitMoney();
				}
				if (pb.getBenefitPercent() != null && !"".equals(pb.getBenefitPercent())) {
					ben = house.getListPrice() - (pb.getBenefitPercent() * house.getListPrice());
				}
			}
			// 优惠后的价格
			Double totalY = 0D;
			if (ben != 0D && (house.getListPrice() != null && !"".equals(house.getListPrice()))) {
				// 计算所有优惠之后的价格
				totalY = house.getListPrice() - ben;
				benLv = SysContent.get2Double((totalY / house.getListPrice()));
			}
			map.put("benefits", benLv);// 优惠比率
			list.add(map);

		}
		List<House> totalList = baseDao.queryBySql(totalHql);
		Integer total = totalList.size();
		page.setWheres(total.toString());
		page.setTotal(total);
		page.setRoot(list);
	}

	@Override
	public void updateSpitelngLat() {

		String hql = "from Shops where lngLat is not null";
		List<Shops> shops = baseDao.findByHql(hql);
		for (Shops shop : shops) {
			if (!"".equals(shop.getLngLat())) {

				String[] ss = shop.getLngLat().split(",");
				if (ss.length > 1) {
					shop.setLongitude(ss[0]);
					shop.setLatitude(ss[1]);
					baseDao.saveOrUpdate(shop);
				}
			}
		}

		String proHql = "from Project where propertyAddressGis is not null";
		List<Project> proList = baseDao.findByHql(proHql);
		for (Project p : proList) {
			if (!"".equals(p.getPropertyAddressGis())) {

				String[] ss = p.getPropertyAddressGis().split(",");
				if (ss.length > 1) {
					p.setPropertyLongitude(ss[0]);
					p.setPropertyLatitude(ss[1]);
					baseDao.saveOrUpdate(p);
				}
			}
		}

		String proeHql = "from Project where saleAddressGis is not null";
		List<Project> proeList = baseDao.findByHql(proeHql);
		for (Project p : proeList) {
			if (!"".equals(p.getSaleAddressGis())) {

				String[] ss = p.getSaleAddressGis().split(",");
				if (ss.length > 1) {
					p.setSaleLongitude(ss[0]);
					p.setSaleLatitude(ss[1]);
					baseDao.saveOrUpdate(p);
				}
			}
		}

	}

	@Override
	public String findAddressByIpOrCityName(String city, HttpServletRequest request, HouseService houseService) {
		String areaId = "";

		// 如果没有选择城市列表，默认就定位当前的ip地址
		if (city == null && "".equals(city)) {
			// 获取当前ip
			String ip = AddressUtil.getIpAddr(request);
			Map<String, String> map = AddressUtil.getAddressesId(ip, "utf-8");
			areaId = map.get("areaId");
		} else {
			// 通过传递的城市名称查找id
			areaId = city;
		}
		return areaId;
	}

	@Override
	public List<Project> findProjectListForMap() {

		String proHql = "from Project where saleLongitude is not null  and saleLatitude is not null";

		List<Project> projectList = baseDao.findByHql(proHql);

		return projectList;
	}

}
