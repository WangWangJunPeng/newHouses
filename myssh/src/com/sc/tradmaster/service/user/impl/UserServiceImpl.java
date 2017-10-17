package com.sc.tradmaster.service.user.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sc.tradmaster.bean.AgentRank;
import com.sc.tradmaster.bean.AnalysisCategory;
import com.sc.tradmaster.bean.Comment;
import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.ContractRecordsFlowRecord;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.ManaerChartData;
import com.sc.tradmaster.bean.ManagerOwnAnalyse;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.ShopCustomers;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.TagsRelation;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.service.user.impl.dto.AllCheckedCustomerDTO;
import com.sc.tradmaster.service.user.impl.dto.AnalyzeTagsDTO;
import com.sc.tradmaster.service.user.impl.dto.CustomerInfoDB;
import com.sc.tradmaster.service.user.impl.dto.GrDto;
import com.sc.tradmaster.service.user.impl.dto.RankingDto;
import com.sc.tradmaster.service.user.impl.dto.VisitRecordsDTO;
import com.sc.tradmaster.utils.CusAccessObjectUtil;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.GetIPXY;
import com.sc.tradmaster.utils.JavaSmsApi;
import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "baseDao")
	private BaseDao baseDao;

	@Override
	public User findById(String id) {
		return (User) baseDao.loadById(User.class, id);
	}

	@Override
	public User addUserTokenAndlogin(User u) {
		if (u != null && !"".equals(u.getPassword())) {
			String paw = SysContent.md5(u.getPassword());// 登录密码加密
			String hql = "from User where phone = '" + u.getPhone() + "' and password = '" + paw
					+ "' and userStatus = 1";
			List<User> list = baseDao.findByHql(hql);
			if (list.size() > 0) {
				User user = list.get(0);
				// token生成策略
				String uuid = SysContent.uuid();
				String token = user.getUserId() + "-" + DateTime.dateForTokenToString(new Date()) + "-" + uuid;
				user.setUserToken(token);
				baseDao.saveOrUpdate(user);
				return user;
			}
		}
		return null;
	}

	@Override // u是当前登录用户，user是接收数据参数
	public Map addOrUpdateUser(User u, User user, String rightSign) {
		Map map = new HashMap<>();
		User aginUser = (User) baseDao.loadObject("from User where phone = '" + user.getPhone() + "' and userStatus !=2");
		if (u != null) {
			if (user.getUserId() == null) {
				if (aginUser != null) {
					map.put("code", 201);
					map.put("msg", "该手机号已经存在，不能重复添加");
					return map;
				}
				user.setUserId(SysContent.uuid());
				user.setCreateTime(DateTime.toString1(new Date()));
			}
			User haveUser = (User) baseDao.loadById(User.class, user.getUserId());
			if (haveUser != null) {
				Set<Role> role = haveUser.getRoleId();
				if (!role.isEmpty()) {
					for (Role haveRole : role) {
						haveUser.getRoleId().remove(haveRole);
					}
				}
			} else {
				haveUser = user;
			}

			// 获取分配的权限
			if (rightSign.equals("engineer")) {
				Role r = (Role) baseDao.loadById(Role.class, 4);
				Set<Role> roleId = new HashSet<>();
				roleId.add(r);
				haveUser.setRoleId(roleId);
			} else if (rightSign.equals("agent")) {
				Role r = (Role) baseDao.loadById(Role.class, 3);
				Set<Role> roleId = new HashSet<>();
				roleId.add(r);
				haveUser.setRoleId(roleId);
			} else if (rightSign.equals("director")) {
				Role r = (Role) baseDao.loadById(Role.class, 7);
				Set<Role> roleId = new HashSet<>();
				roleId.add(r);
				haveUser.setRoleId(roleId);
			}
			haveUser.setUserCaption(user.getUserCaption());
			haveUser.setPhone(user.getPhone());
			haveUser.setPassword(SysContent.md5(user.getPhone().substring(user.getPhone().length() - 6)));
			haveUser.setIdCard(user.getIdCard());
			haveUser.setUserStatus(1);
			haveUser.setParentId(u.getParentId());
			haveUser.setSex(user.getSex());
			// 持久化user对象
			baseDao.saveOrUpdate(haveUser);
			map.put("code", 200);
			map.put("msg", "添加成功");
		}
		return map;
	}

	@Override // u是当前登录用户，user是接收数据参数
	public void addOrUpdateMediUser(User u, User user, String rightSign) {
		if (u != null) {
			if (user.getUserId() == null) {
				user.setUserId(SysContent.uuid());
				user.setCreateTime(DateTime.toString1(new Date()));
			}
			User haveUser = (User) baseDao.loadById(User.class, user.getUserId());
			if (haveUser != null) {
				Set<Role> role = haveUser.getRoleId();
				if (!role.isEmpty()) {
					for (Role haveRole : role) {
						haveUser.getRoleId().remove(haveRole);
					}
				}
			} else {
				haveUser = user;
			}

			// 获取分配的权限
			if (rightSign.equals("medi")) {
				Role r = (Role) baseDao.loadById(Role.class, 1);
				Set<Role> roleId = new HashSet<>();
				roleId.add(r);
				haveUser.setRoleId(roleId);
			} else if (rightSign.equals("shopowner")) {
				Role r = (Role) baseDao.loadById(Role.class, 2);
				Set<Role> roleId = new HashSet<>();
				roleId.add(r);
				haveUser.setRoleId(roleId);
			}
			haveUser.setUserCaption(user.getUserCaption());
			haveUser.setPhone(user.getPhone());
			haveUser.setPassword(SysContent.md5(user.getPhone().substring(user.getPhone().length() - 6)));
			haveUser.setIdCard(user.getIdCard());
			haveUser.setUserStatus(1);
			haveUser.setParentId(u.getParentId());
			// 持久化user对象
			baseDao.saveOrUpdate(haveUser);
		}
	}

	@Override
	public void updateUserInfo(User user, String doSign) {
		if (user != null) {
			// 获取选择用户
			User u = (User) baseDao.loadById(User.class, user.getUserId());
			// 重置用户密码
			if (doSign != null && doSign.equals("reset")) {
				u.setPassword(SysContent.md5(u.getPhone().substring(u.getPhone().length() - 6)));
			} else if (doSign != null && doSign.equals("enableOrdisable")) {
				u.setUserStatus(user.getUserStatus());
			} else if (doSign != null && doSign.equals("delete")) {
				u.setUserStatus(2);
			}
			baseDao.saveOrUpdate(u);
		}
	}

	/**
	 * 中介和置业顾问app个人资料
	 * 
	 * @param u
	 */
	@Override
	public Map findUserInfo(User user) {
		Map<String, Object> map = new HashMap<>();
		map.put("photo", user.getPhoto());
		map.put("userName", user.getUserCaption());
		map.put("userPhone", user.getPhone());
		map.put("idCard", user.getIdCard());
		return map;
	}

	/**
	 * 中介和置业顾问app原来密码验证
	 * 
	 */
	@Override
	public void findOldPassword(User user, String passwwrd) {
		if (passwwrd.equals(user.getPassword())) {

		}
	}

	/**
	 * 中介和置业顾问app密码修改
	 * 
	 */
	@Override
	public void updatePassowrd(User user, String firstPassword, String secondPassword) {
		user.setPassword(SysContent.md5(firstPassword));
		baseDao.saveOrUpdate(user);
	}

	/**
	 * 中介经纪人我的页面上面信息显示
	 */
	@Override
	public Map findMidInfo(User user) {
		Map<String, Object> map = new HashMap<>();
		map.put("userCaption", user.getUserCaption());
		map.put("photo", user.getPhoto());
		int yijiesuan = 0;
		int weijiesuan = 0;
		int chengjiaoNum = 0;
		// 查出该中介的所有订购成交的订购记录表 (已结算)
		String crhql = "from ContractRecords cr where cr.userId = '" + user.getUserId()
				+ "' and cr.recordStatus = 5 and cr.shopPayConfirmTime is not null";
		List<ContractRecords> crList = baseDao.findByHql(crhql);
		for (ContractRecords contractRecords : crList) {
			// 查带看表 查分销佣金比
			if (contractRecords.getGuideId() != null && !"".equals(contractRecords.getGuideId())) {
				Integer recordNo = Integer.parseInt(contractRecords.getGuideId());
				GuideRecords gr = (GuideRecords) baseDao
						.loadObject("from GuideRecords where recordNo = '" + recordNo + "' ");
				if (gr  != null && !"".equals(gr)){
					HashMap grMap = JSON.parseObject(gr.getRules(), HashMap.class);
					if (contractRecords.getUserId().equals(gr.getUserId())) {
						// 分销
						Double fenXiaoMoney = Double.parseDouble(grMap.get("fenXiaoMoney").toString());
						yijiesuan += contractRecords.getBuyPrice() * fenXiaoMoney / 100;
						// 分销成交数量
						chengjiaoNum++;
					} else {
						Double daiKanMoney = Double.parseDouble(grMap.get("daiKanMoney").toString());
						yijiesuan += contractRecords.getBuyPrice() * daiKanMoney / 100;
						// 带看成交数量
						chengjiaoNum++;
					}
				}
			}
		}
		// //查该中介的成交的备案记录表
		// 已结算佣金
		map.put("yijiesuan", yijiesuan + "元");

		// 查出该中介的所有订购成交的订购记录表 (未结算)
		String crhql2 = "from ContractRecords cr where cr.userId = '" + user.getUserId()
				+ "' and cr.recordStatus = 5 and cr.shopPayConfirmTime is null";
		List<ContractRecords> crList2 = baseDao.findByHql(crhql2);
		if (crList2.size() > 0) {
			for (ContractRecords contractRecords : crList2) {
				if (contractRecords.getGuideId() != null && !"".equals(contractRecords.getGuideId())) {
					Integer recordNo = Integer.parseInt(contractRecords.getGuideId());
					GuideRecords gr = (GuideRecords) baseDao
							.loadObject("from GuideRecords where recordNo = '" + recordNo + "' ");
					if (gr != null && !"".equals(gr)){
						HashMap grMap = JSON.parseObject(gr.getRules(), HashMap.class);
						if (contractRecords.getUserId().equals(gr.getUserId())) {
							// 分销
							Double fenXiaoMoney = Double.parseDouble(grMap.get("fenXiaoMoney").toString());
							weijiesuan += contractRecords.getBuyPrice() * fenXiaoMoney / 100;
						} else {
							Double daiKanMoney = Double.parseDouble(grMap.get("daiKanMoney").toString());
							weijiesuan += contractRecords.getBuyPrice() * daiKanMoney / 100;
						}
					}
				}

			}
		}
		// 未结算佣金
		map.put("weijiesuan", weijiesuan + "元");

		// 成交数量
		map.put("chengjiaoNum", chengjiaoNum + "套");

		return map;
	}

	/**
	 * 中介经纪人我的页面业务统计
	 * 
	 * @throws ParseException
	 */
	@Override
	public Map findMidBusiness(User user) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 当前时间
		String nowTime = DateTime.toString1(new Date());
		// 当前月第一天
		Date monthStart = DateUtil.getMonthStartTime();
		String monthStartTime = sdf.format(monthStart);
		// 查备案成功的
		String grHQL = "from GuideRecords gr where gr.userId = '" + user.getUserId()
				+ "' and ( gr.applyStatus = 0 or gr.applyStatus = 1)";
		List<GuideRecords> grList = baseDao.findByHql(grHQL);
		int beianNum = 0;
		if (grList != null && !"".equals(grList)) {
			for (GuideRecords guideRecords : grList) {
				String applyTime = guideRecords.getApplyTime();
				double x = sdf.parse(nowTime).getTime() - sdf.parse(applyTime).getTime();
				double y = sdf.parse(monthStartTime).getTime() - sdf.parse(applyTime).getTime();
				if (x >= 0 && y <= 0) {
					beianNum++;
				}
			}
		}
		// 本月备案成功的数量
		map.put("beianNum", beianNum);

		// 查带看
		String grHQL2 = "from GuideRecords gr where gr.userId = '" + user.getUserId() + "' and gr.applyStatus = 1";
		List<GuideRecords> grList2 = baseDao.findByHql(grHQL2);
		int daikanNum = 0;
		if (grList2 != null && !"".equals(grList2)) {

			for (GuideRecords guideRecords : grList2) {
				String applyTime = guideRecords.getApplyTime();
				double x = sdf.parse(nowTime).getTime() - sdf.parse(applyTime).getTime();
				double y = sdf.parse(monthStartTime).getTime() - sdf.parse(applyTime).getTime();
				if (x >= 0 && y <= 0) {
					daikanNum++;
				}
			}
		}
		// 本月带看的数量
		map.put("daikanNum", daikanNum);

		int midfenxiaoNum = 0;
		String crhql = "from ContractRecords cr where cr.recordStatus = 5 and cr.shopPayConfirmTime is not null and cr.guideId is not null";
		List<ContractRecords> crList = baseDao.findByHql(crhql);
		if (crList.size() > 0) {
			for (ContractRecords contractRecords : crList) {
				// 查带看表 查分销佣金比
				if (contractRecords.getUserId().equals(user.getUserId())) {
					midfenxiaoNum++;
				} else {
					Integer recordNo = Integer.parseInt(contractRecords.getGuideId());
					GuideRecords gr = (GuideRecords) baseDao
							.loadObject("from GuideRecords where recordNo = '" + recordNo + "' ");
					if (gr != null && !"".equals(gr)){
						HashMap grMap = JSON.parseObject(gr.getRules(), HashMap.class);
						if (gr.getUserId().equals(user.getUserId())) {
							midfenxiaoNum++;
						}
					}
				}
			}
		}


		// 本月成交数量
		map.put("dealNum", midfenxiaoNum);

		return map;
	}

	/**
	 * 置业顾问我的页面上面的信息显示
	 */
	@Override
	public Map findSaleInfo(User user) {
		Map<String, Object> map = new HashMap<>();
		map.put("photo", user.getPhoto());
		map.put("userCaption", user.getUserCaption());
		return map;
	}

	/**
	 * 置业顾问我的页面业务统计
	 */
	@Override
	public Map findSaleBusiness(User user) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 当前时间
		String nowTime = DateTime.toString1(new Date());
		// 当前月第一天
		Date monthStart = DateUtil.getMonthStartTime();
		String monthStartTime = sdf.format(monthStart);
		String hql = "from ContractRecords cr where cr.userId = '" + user.getUserId() + "' and cr.recordStatus = 5";
		List<ContractRecords> crList = baseDao.findByHql(hql);
		// 本月成交数量
		int dealNum = 0;
		for (ContractRecords contractRecords : crList) {
			String contractConfirmTime = contractRecords.getContractConfirmTime();
			if (contractConfirmTime != null && !"".equals(contractConfirmTime)) {
				double x = sdf.parse(nowTime).getTime() - sdf.parse(contractConfirmTime).getTime();
				double y = sdf.parse(monthStartTime).getTime() - sdf.parse(contractConfirmTime).getTime();
				if (x >= 0 && y <= 0) {
					dealNum++;
				}
			}
		}
		map.put("dealNum", dealNum);
		return map;
	}

	/*
	 * 
	 * 门店注册
	 * 
	 */
	@Override
	public void addShop(Shops shop, MultipartFile photoPic, MultipartFile licensePic, String province, String market,
			String area) throws Exception {

		/* 进行shop的持久化 */
		shop.setApplyTime(DateUtil.format(new Date()));
		shop.setShopStatus(0);
		shop.setCity(province + "-" + market + "-" + area);
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
		shop.setInSystemStutas(0);
		baseDao.save(shop);

		/* 进行user的持久化处理 */
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

	}

	@Override
	public User findByUserToken(String userToken) {
		String hql = "from User where userToken = '" + userToken + "'";
		User user = (User) baseDao.loadObject(hql);
		return user;
	}

	@Override
	public boolean findExistPhoneNum(String phoneNum) {
		Integer phoneCount = 0;
		String hql = "select count(*) from User where phone = '" + phoneNum + "' and userStatus != 2";
		phoneCount = baseDao.countQuery(hql);
		if (phoneCount == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void getArray() {
		String[] str = new String[] { "1", "2", "3" };
		System.out.println(1 / 0);

	}

	private List<Map<String, Object>> getUserMap(String hql) {

		List<Map<String, Object>> listMap = new ArrayList<>();
		List<User> list = baseDao.findByHql(hql);
		boolean flag = true;
		for (User u : list) {
			// 查找当前用户的角色
			Set<Role> rSet = u.getRoleId();
			// 防止数据库乱数据
			if (rSet != null && rSet.size() > 0) {
				flag = true;
			} else {
				flag = false;
			}
			Map<String, Object> map = new HashMap<>();
			// 查找该用户所属上级
			Shops shop = null;
			if (u.getParentId() != null && !u.getParentId().equals("")) {
				Project project = (Project) baseDao.loadById(Project.class, u.getParentId());
				if (project != null) {
					map.put("contactPerson", project.getProjectName());
				} else {
					shop = (Shops) baseDao.loadById(Shops.class, Integer.parseInt(u.getParentId()));
					if (shop != null) {
						map.put("contactPerson", shop.getContactPerson());
					}
				}
			} else {
				map.put("contactPerson", "");
			}
			map.put("userCaption", u.getUserCaption());
			map.put("phone", u.getPhone());
			map.put("createTime", u.getCreateTime());
			map.put("userStatus", u.getUserStatus());
			map.put("userId", u.getUserId());
			for (Role r : rSet) {
				map.put("roleId", r.getRoleId());
			}
			if (flag) {
				listMap.add(map);
			}
		}

		return listMap;
	}

	@Override
	public List<Map<String, Object>> findAllUser(String status) {
		List<Map<String, Object>> userList = null;
		if (status.equals("")) { // 所有的用户账户，不包括申请状态和删除状态。
			String hql = "from User where userStatus != 0 and userStatus != 2";
			userList = getUserMap(hql);
		} else if (status.equals("1")) { // 正常的账号
			String hql = "from User where userStatus = 1";
			userList = getUserMap(hql);
		} else if (status.equals("2")) { // 已经被禁用的账户
			String hql = "from User where userStatus = 3";
			userList = getUserMap(hql);
		}
		return userList;
	}

	@Override
	public void updateSystemUser(User us, User user, String doSign, String role) {
		if (us != null) {
			User u = (User) baseDao.loadById(User.class, user.getUserId());
			// 重置用户密码
			if (doSign != null && doSign.equals("reset")) {
				u.setPassword(SysContent.md5(u.getPhone().substring(u.getPhone().length() - 6)));
			}
			// 更换角色
			Role roles = (Role) baseDao.loadById(Role.class, Integer.parseInt(role));
			Set<Role> rSet = new HashSet<>();
			rSet.add(roles);
			u.setRoleId(rSet);
			// change phone
			u.setPhone(user.getPhone());
			// 更换身份证号
			u.setIdCard(user.getIdCard());
			baseDao.saveOrUpdate(u);
		}
	}

	@Override
	public Boolean addOrUpdateUserPassWorld(User u, String oldPsw, String newPsw) {
		oldPsw = SysContent.md5(oldPsw.trim());
		newPsw = SysContent.md5(newPsw.trim());
		if (u != null && oldPsw != null && newPsw != null) {
			User user = (User) baseDao.loadById(User.class, u.getUserId());
			if (user.getPassword().equals(oldPsw)) {
				user.setPassword(newPsw);
				baseDao.saveOrUpdate(user);
				return true;
			}
		}
		return false;
	}

	/**
	 * 中介经纪人我的动态
	 * 
	 * @return
	 */
	@Override
	public List<Mydynamic> findMidMydynamic(User user) {
		String hql = "from Mydynamic where userId = '" + user.getUserId() + "' order by creatTime DESC";
		List<Mydynamic> mList = baseDao.findByHql(hql);

		return mList;
	}

	/**
	 * 中介经纪人我的动态改已读
	 * 
	 * @return
	 */
	@Override
	public void updateMidMydynamic(User user, Integer dynamicId) {

		String hql = "from Mydynamic where dynamicId = '" + dynamicId + "' ";
		Mydynamic mydynamic = (Mydynamic) baseDao.loadObject(hql);
		// 改已读
		mydynamic.setIsRead(1);
		mydynamic.setReadTime(DateTime.toString1(new Date()));

		baseDao.saveOrUpdate(mydynamic);
	}

	/**
	 * 中介经纪人我的动态未读数量显示
	 * 
	 * @return
	 */
	@Override
	public int toGetMydynamicNotReadNum(User user) {
		String hql = "select count(*) from Mydynamic where userId = '" + user.getUserId() + "' and isRead = 0";
		int count = baseDao.countQuery(hql);

		return count;
	}

	/**
	 * 经理app分割线__________________________________________________________________________________
	 */
	/**
	 * 经理今日接访未登记
	 * 
	 * @throws ParseException
	 */
	@Override
	public List<VisitRecordsDTO> findToadyVisitRecords(String time, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (time != null && !"".equals(time)) {
			startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(time)));
			endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(time)));
		}

		List<VisitRecords> vrList = baseDao
				.findByHql("from VisitRecords where projectId = '" + user.getParentId() + "' and arriveTime >= '"
						+ startTimeStr + "' and arriveTime <= '" + endTimeStr + "' and writeState is null");
		List<VisitRecordsDTO> vrdtoList = new ArrayList<>();
		if (vrList.size() > 0) {
			for (VisitRecords vr : vrList) {
				User agent = (User) baseDao.loadObject("from User where userId = '" + vr.getUserId() + "' ");
				VisitRecordsDTO vrdto = new VisitRecordsDTO();
				VisitRecordsDTO DtoVr = vrdto.getVisitRecordsDTO(vr, agent);
				vrdtoList.add(DtoVr);
			}
		}

		return vrdtoList;
	}

	/**
	 * 案场今日备案数量和备案为到访数量
	 * 
	 * @throws ParseException
	 */
	@Override
	public Map<String, String> findTodayGuideRecords(String startTime, String endTime, User user)
			throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Map<String, String> map = new HashMap<>();
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}

		List<GuideRecords> grList = baseDao.findByHql("from GuideRecords where applyTime >= '" + startTimeStr
				+ "' and applyTime <= '" + endTimeStr + "' and projectId = '" + user.getParentId() + "' and (applyStatus = 0 or applyStatus = 1)");
		if (grList.size() > 0) {
			map.put("haveRecords", grList.size() + "");

			// 已到访
			List<GuideRecords> grList2 = baseDao
					.findByHql("from GuideRecords where applyTime >= '" + startTimeStr + "' and applyTime <= '"
							+ endTimeStr + "' and projectId = '" + user.getParentId() + "' and visitNo is not null and (applyStatus = 0 or applyStatus = 1)");
			if (grList2.size() > 0) {
				map.put("haveVisit", grList2.size() + "");
				// 有效到访率
				map.put("percent", SysContent.get2Double((double) grList2.size() / (double) grList.size() * 100) + "%");
			} else {
				// 有效到访率
				map.put("percent", "0");
				map.put("haveVisit", "0");
			}
			List<GuideRecords> grList3 = baseDao
					.findByHql("from GuideRecords where applyTime >= '" + startTimeStr + "' and applyTime <= '"
							+ endTimeStr + "' and projectId = '" + user.getParentId() + "' and visitNo is null and (applyStatus = 0 or applyStatus = 1)");
			if (grList3.size() > 0) {
				// 未到访
				map.put("notVisit", grList3.size() + "");
			} else {
				map.put("notVisit", "0");
			}

		} else {
			map.put("haveRecords", "0");
			map.put("haveVisit", "0");
			map.put("notVisit", "0");
			map.put("percent", "0");
		}

		return map;
	}

	@Override
	public Map<String, Integer> findCheckCustomerNum(String startTime, String endTime, User user)
			throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Map<String, Integer> map = new HashMap<>();
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		int allCustomerNum = 0;
		int notCheckedNum = 0;
		int haveCheckedNum = 0;
		List<ProjectCustomers> pcList = baseDao
				.findByHql("from ProjectCustomers where projectId = '" + user.getParentId() + "' and lastTime >= '"
						+ startTimeStr + "' and lastTime <= '" + endTimeStr + "' ");
		List<VisitRecords> vrList = baseDao
				.findByHql("from VisitRecords where projectId = '" + user.getParentId() + "' and arriveTime >= '"
						+ startTimeStr + "' and arriveTime <= '" + endTimeStr + "' and writeState = 1");
		
		if (vrList.size() > 0) {
			for (VisitRecords vr : vrList) {
				allCustomerNum++;
				Comment c = (Comment) baseDao.loadObject("from Comment where visitNo = '" + vr.getVisitNo() + "' ");
				if (c != null && !"".equals(c)) {
					haveCheckedNum++;
				} else {
					notCheckedNum++;
				}
			}
			map.put("allCustomerNum", allCustomerNum);
			if (notCheckedNum > 0) {
				map.put("notCheckedNum", notCheckedNum);
			} else {
				map.put("notCheckedNum", 0);
			}
		} else {
			map.put("allCustomerNum", 0);
			map.put("notCheckedNum", 0);
		}

		return map;
	}

	@Override
	public List<GrDto> findGuideRecordsDetail(String startTime, String endTime, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		List<String> sList = new ArrayList<>();

		// 一段时间内的备案总数
		List<GuideRecords> grList = baseDao.findByHql("from GuideRecords where applyTime >= '" + startTimeStr
				+ "' and applyTime <= '" + endTimeStr + "' and projectId = '" + user.getParentId() + "' and (applyStatus = 0 or applyStatus = 1)");
		if (grList.size() > 0) {
			for (GuideRecords gr : grList) {
				sList.add(gr.getShopCustomerId());
			}
		}
		Set<Integer> inset = new HashSet<>();
		for (String string : sList) {
			ShopCustomers sc = (ShopCustomers) baseDao
					.loadObject("from ShopCustomers where shopCustomerId = '" + string + "' ");
			if (sc != null && !"".equals(sc)) {
				inset.add(sc.getShopId());
			}
		}

		List<GrDto> grdtoList = new ArrayList<>();
		for (Integer integer : inset) {
			GrDto grdto = new GrDto();
			int haveRecordsNum = 0;
			int haveVisitNum = 0;
			int notVisitNum = 0;
			List<ShopCustomers> scList = baseDao.findByHql("from ShopCustomers where shopId = '" + integer + "' ");
			for (ShopCustomers sc : scList) {
				List<GuideRecords> grList2 = baseDao.findByHql("from GuideRecords where applyTime >= '" + startTimeStr
						+ "' and applyTime <= '" + endTimeStr + "' and projectId = '" + user.getParentId()
						+ "' and shopCustomerId = '" + sc.getShopCustomerId() + "' and (applyStatus = 0 or applyStatus = 1)");
				if (grList2.size() > 0) {
					haveRecordsNum += grList2.size();
				}
				List<GuideRecords> grList3 = baseDao.findByHql("from GuideRecords where applyTime >= '" + startTimeStr
						+ "' and applyTime <= '" + endTimeStr + "' and projectId = '" + user.getParentId()
						+ "' and shopCustomerId = '" + sc.getShopCustomerId() + "' and visitNo is not null");
				if (grList3.size() > 0) {
					haveVisitNum += grList3.size();
				}
				List<GuideRecords> grList4 = baseDao.findByHql("from GuideRecords where applyTime >= '" + startTimeStr
						+ "' and applyTime <= '" + endTimeStr + "' and projectId = '" + user.getParentId()
						+ "' and shopCustomerId = '" + sc.getShopCustomerId() + "' and visitNo is null");
				if (grList4.size() > 0) {
					haveVisitNum += grList4.size();
				}
			}
			grdto.setGrAllNum(haveRecordsNum + "");
			grdto.setHaveVisitNum(haveVisitNum + "");
			grdto.setNotVisitNum(notVisitNum + "");
			Shops shops = (Shops) baseDao.loadObject("from Shops where shopId = '" + integer + "' ");
			String city = shops.getCity();
			String[] cityStr = city.split("-");
			CountryProvinceInfo cpi = (CountryProvinceInfo) baseDao
					.loadObject("from CountryProvinceInfo where cityId = '" + cityStr[1] + "' ");
			grdto.setShiqu(cpi.getCityName());
			grdto.setShopId(shops.getShopId());
			grdto.setShopName(shops.getShopName());
			grdto.setAddress(shops.getAddress());
			grdto.setPhone(shops.getPhone());

			grdtoList.add(grdto);
		}
		return grdtoList;
	}

	/**
	 * 排名
	 * 
	 * @throws ParseException
	 */
	@Override
	public List<RankingDto> findVisitRanking(String startTime, String endTime, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		List<User> uList = baseDao.findByHql("from User where parentId = '" + user.getParentId() + "' ");
		List<RankingDto> rdtoList = new ArrayList<>();
		for (User u : uList) {
			Set<Role> sr = u.getRoleId();
			for (Role role : sr) {
				if (role.getRoleId() == 3) {
					RankingDto rdto = new RankingDto();
					List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where userId = '" + u.getUserId()
							+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");
					if (vrList.size() > 0) {
						rdto.setVisitNum(vrList.size());
					} else {
						rdto.setVisitNum(0);
					}
					rdto.setPhoto(u.getPhoto());
					rdto.setUserName(u.getUserCaption());
					rdto.setUserId(u.getUserId());
					rdto.setPhone(u.getPhone());
					rdtoList.add(rdto);
				}
			}
		}
		for (int i = 0; i < rdtoList.size(); i++) {
			for (int j = 0; j < rdtoList.size() - 1 - i; j++) {
				if (rdtoList.get(j).getVisitNum() < rdtoList.get(j + 1).getVisitNum()) {
					RankingDto r = rdtoList.get(j);
					rdtoList.set(j, rdtoList.get(j + 1));
					rdtoList.set(j + 1, r);
				}

			}
		}

		return rdtoList;
	}

	@Override
	public List<RankingDto> findRengouRanking(String startTime, String endTime, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		List<User> uList = baseDao.findByHql("from User where parentId = '" + user.getParentId() + "' ");
		List<RankingDto> rdtoList = new ArrayList<>();
		for (User u : uList) {
			Set<Role> sr = u.getRoleId();
			for (Role role : sr) {
				if (role.getRoleId() == 3) {
					RankingDto rdto = new RankingDto();
					List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"
							+ u.getUserId() + "' and applyTime >= '" + startTimeStr + "' and applyTime <= '"
							+ endTimeStr + "' and (recordStatus = 4 or recordStatus = 0 or recordStatus = 1)");
					if (crList.size() > 0) {
						rdto.setRengouNum(crList.size());
					} else {
						rdto.setRengouNum(0);
					}
					rdto.setPhoto(u.getPhoto());
					rdto.setUserName(u.getUserCaption());
					rdto.setUserId(u.getUserId());
					rdto.setPhone(u.getPhone());
					rdtoList.add(rdto);
				}
			}
		}
		for (int i = 0; i < rdtoList.size(); i++) {
			for (int j = 0; j < rdtoList.size() - 1 - i; j++) {
				if (rdtoList.get(j).getRengouNum() < rdtoList.get(j + 1).getRengouNum()) {
					RankingDto r = rdtoList.get(j);
					rdtoList.set(j, rdtoList.get(j + 1));
					rdtoList.set(j + 1, r);
				}

			}
		}
		return rdtoList;
	}

	@Override
	public List<RankingDto> findContractRanking(String startTime, String endTime, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		List<User> uList = baseDao.findByHql("from User where parentId = '" + user.getParentId() + "' ");
		List<RankingDto> rdtoList = new ArrayList<>();
		for (User u : uList) {
			Set<Role> sr = u.getRoleId();
			for (Role role : sr) {
				if (role.getRoleId() == 3) {
					RankingDto rdto = new RankingDto();
					List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"
							+ u.getUserId() + "' and applyTime >= '" + startTimeStr + "' and applyTime <= '"
							+ endTimeStr + "' and (recordStatus = 5 or recordStatus = 4)");
					if (crList.size() > 0) {
						rdto.setContractNum(crList.size());
					} else {
						rdto.setContractNum(0);
					}
					rdto.setPhoto(u.getPhoto());
					rdto.setUserName(u.getUserCaption());
					rdto.setUserId(u.getUserId());
					rdto.setPhone(u.getPhone());
					rdtoList.add(rdto);
				}
			}
		}
		for (int i = 0; i < rdtoList.size(); i++) {
			for (int j = 0; j < rdtoList.size() - 1 - i; j++) {
				if (rdtoList.get(j).getContractNum() < rdtoList.get(j + 1).getContractNum()) {
					RankingDto r = rdtoList.get(j);
					rdtoList.set(j, rdtoList.get(j + 1));
					rdtoList.set(j + 1, r);
				}

			}
		}
		return rdtoList;
	}

	@Override
	public List<RankingDto> findSlewRateRanking(String startTime, String endTime, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		List<User> uList = baseDao.findByHql("from User where parentId = '" + user.getParentId() + "' ");
		List<RankingDto> rdtoList = new ArrayList<>();
		for (User u : uList) {
			Set<Role> sr = u.getRoleId();
			for (Role role : sr) {
				if (role.getRoleId() == 3) {
					RankingDto rdto = new RankingDto();
					List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where userId = '" + u.getUserId()
							+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");
					if (vrList.size() > 0) {
						rdto.setVisitNum(vrList.size());
					} else {
						rdto.setVisitNum(0);
					}

					List<ContractRecords> crList = baseDao
							.findByHql("from ContractRecords where userId = '" + u.getUserId() + "' and applyTime >= '"
									+ startTimeStr + "' and applyTime <= '" + endTimeStr + "' and recordStatus = 5 ");
					if (crList.size() > 0) {
						rdto.setContractNum(crList.size());
					} else {
						rdto.setContractNum(0);
					}
					if (vrList.size() > 0 && crList.size() > 0) {
						rdto.setSlewRate(
								SysContent.get2Double(((double) crList.size() / (double) vrList.size()) * 100));
					} else {
						rdto.setSlewRate("0");
					}
					rdto.setPhoto(u.getPhoto());
					rdto.setUserName(u.getUserCaption());
					rdto.setUserId(u.getUserId());
					rdto.setPhone(u.getPhone());
					rdtoList.add(rdto);
				}
			}
		}
		for (int i = 0; i < rdtoList.size(); i++) {
			for (int j = 0; j < rdtoList.size() - 1 - i; j++) {
				if (rdtoList.get(j).getSlewRate() != null && !"".equals(rdtoList.get(j).getSlewRate())) {
					if (Double.parseDouble(rdtoList.get(j).getSlewRate()) < Double
							.parseDouble(rdtoList.get(j + 1).getSlewRate())) {
						RankingDto r = rdtoList.get(j);
						rdtoList.set(j, rdtoList.get(j + 1));
						rdtoList.set(j + 1, r);
					}
				}
			}
		}
		return rdtoList;
	}

	@Override
	public Map<String, Object> findVisitStatement(String startTime, String endTime, User user) throws ParseException {

		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
				+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");

		// 替接接访时长
		long replaceTime = 0L;
		// 新客户接访时长
		long newVisitTime = 0L;
		// 老客户接访时长
		long oldVisitTime = 0L;
		// 平均接访时常
		long timeDiff = 0L;
		int totalNum = 0;
		double AverageLongTime = 0.00;
		String strAverageLongTime = "0.00";
		// 总接访量
		int allVisitNum = 0;
		// 有效接访量
		int effectiveNum = 0;
		// 无效接访量
		int invalidNum = 0;
		// 新客户通道接访
		int newCustomerNum = 0;
		// 老客户通道接访
		int oldCustomerNum = 0;
		// 新客户通道有效接访
		int newCustomerEffectiveNum = 0;
		// 老客户通道有效接访
		int oldCustomerEffectiveNum = 0;
		// 指定接访量
		int appointAgentNum = 0;
		// 新客户通道指定接访
		int newCustomerAppointAgentNum = 0;
		// 老客户通道指定接访
		int oldCustomerAppointAgentNum = 0;
		// 指定有效接访
		int appointAgentEffectiveNum = 0;
		// 总替接数
		int allReplaceNum = 0;
		// 按序接访替接
		int orderReplaceNum = 0;
		// 确认老客户接访次数
		int affirmOldCustomerVisitNum = 0;


		map.put("length", 29);
		// 总接访量
		map.put("name_1", "总接访数");
		map.put("name_2", "有效接访");
		map.put("name_3", "无效接访");
		map.put("name_4", "有效接访率");
		map.put("name_5", "新客户通道接访");
		map.put("name_6", "老客户通道接访");
		map.put("name_7", "新客户通道有效接访");
		map.put("name_8", "老客户通道有效接访");
		map.put("name_9", "新客户通道有效接访率");
		map.put("name_10", "老客户通道有效接访率");
		map.put("name_11", "老客户通道占比");
		map.put("name_12", "确认老客户接访次数");
		map.put("name_13", "老客户接访次数占比");
		map.put("name_14", "指定接访");
		map.put("name_15", "新客户通道指定接访");
		map.put("name_16", "老客户通道指定接访");
		map.put("name_17", "指定有效接访");
		map.put("name_18", "指定有效接访率");
		map.put("name_19", "指定接访率");
		map.put("name_20", "新客户通道指定接访率");
		map.put("name_21", "老客户通道指定接访率");
		map.put("name_22", "总替接数");
		map.put("name_23", "总替接率");
		map.put("name_24", "总接访时长");
		map.put("name_25", "新客户通道接访时长");
		map.put("name_26", "老客户通道接访时长");
		map.put("name_27", "替接接访时长");
		map.put("name_28", "每日接访平均时长");
		map.put("name_29", "每次接访平均时长");
		
		Set<String> oldSet = new HashSet<>();
		if (vrList.size() > 0) {
			for (VisitRecords visitRecords : vrList) {
				oldSet.add(visitRecords.getPhone());
				/*if (visitRecords.getPhone() != null && !"".equals(visitRecords.getPhone())){
					ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject("from ProjectCustomers where projectCustomerPhone = '"+visitRecords.getPhone()+"' and projectId = '" + user.getParentId()+"' ");
					if (pc != null && !"".equals(pc)){
						List<VisitRecords> vrList33 = baseDao.findByHql(
								"from VisitRecords where projectId = '" + user.getParentId() + "' and arriveTime < '"
										+ startTimeStr + "' and phone = '" + visitRecords.getPhone() + "' and phone is not null and phone != '' ");
						if (vrList33.size() > 0) {
							affirmOldCustomerVisitNum++;
						}else {
							List<VisitRecords> vrList333 = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId() + "' and arriveTime  >='"
									+ startTimeStr + "' and phone = '" + visitRecords.getPhone() + "' and phone is not null and arriveTime <= '" + endTimeStr + "' and phone != '' ");
							if (vrList333.size()>1){
								affirmOldCustomerVisitNum++;
							}
						}
					}
				}*/
			}
			if (oldSet.size()>0){
				for (String string : oldSet) {
						ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject("from ProjectCustomers where projectCustomerPhone = '"+string+"' and projectId = '" + user.getParentId()+"' ");
						if (pc != null && !"".equals(pc)){
							List<VisitRecords> vrList33 = baseDao.findByHql(
									"from VisitRecords where projectId = '" + user.getParentId() + "' and arriveTime < '"
											+ startTimeStr + "' and phone = '" + string + "' and phone is not null and phone != '' ");
							if (vrList33.size() > 0) {
								affirmOldCustomerVisitNum++;
							}else {
								List<VisitRecords> vrList333 = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId() + "' and arriveTime  >='"
										+ startTimeStr + "' and phone = '" + string + "' and phone is not null and arriveTime <= '" + endTimeStr + "' and phone != '' ");
								if (vrList333.size()>1){
									affirmOldCustomerVisitNum += (vrList333.size()-1);
								}
							}
					}
				}
			}
			

			map.put("data_12", affirmOldCustomerVisitNum);
			// 老客户解放次数占比

			map.put("data_13",
					SysContent.get2Double(((double) affirmOldCustomerVisitNum / (double) vrList.size()) * 100) + "%");

			map.put("data_1", vrList.size());

			for (VisitRecords vr : vrList) {
				if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {
					if (vr.getWriteState() == 1) {
						effectiveNum++;
					} else {
						invalidNum++;
					}
				} else {
					invalidNum++;
				}
				if (vr.getIsNew() != null && !"".equals(vr.getIsNew())) {
					if (vr.getIsNew()) {
						newCustomerNum++;
						if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null && !"".equals(vr.getReceptTime())) {
							long newLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
							long newRecept = DateUtil.parse(vr.getReceptTime()).getTime();
							newVisitTime += newLeave - newRecept;
						}
						if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {
							if (vr.getWriteState() == 1) {
								newCustomerEffectiveNum++;
							}
						}
					} else {
						oldCustomerNum++;
						if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null && !"".equals(vr.getReceptTime())) {
							long oldLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
							long oldRecept = DateUtil.parse(vr.getReceptTime()).getTime();
							oldVisitTime += oldLeave - oldRecept;
						}
						if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {
							if (vr.getWriteState() == 1) {
								oldCustomerEffectiveNum++;
							}
						}
					}
				}else {
					newCustomerNum++;
					if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null && !"".equals(vr.getReceptTime())) {
						long newLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
						long newRecept = DateUtil.parse(vr.getReceptTime()).getTime();
						newVisitTime += newLeave - newRecept;
					}
					if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {
						if (vr.getWriteState() == 1) {
							newCustomerEffectiveNum++;
						}
					}
				}
				if (vr.getAppointUserId() != null && !"".equals(vr.getAppointUserId())) {
					// 指定
					appointAgentNum++;
					if (vr.getIsNew() != null && !"".equals(vr.getIsNew())) {
						if (vr.getIsNew()) {
							newCustomerAppointAgentNum++;
						} else {
							oldCustomerAppointAgentNum++;
						}
					}else {
						newCustomerAppointAgentNum++;
					}
					if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {
						if (vr.getWriteState() == 1) {
							appointAgentEffectiveNum++;
						}
					}
					//替接_)_______________________________________
					if (vr.getPhone() != null && !"".equals(vr.getPhone())){
						ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(" from ProjectCustomers where projectCustomerPhone = '"+vr.getPhone()+"' and projectId = '" + user.getParentId()+"' ");
						if (pc != null && !"".equals(pc)){
							if (pc.getOwnerUserId() != null && !"".equals(pc.getOwnerUserId())){
								if (!pc.getOwnerUserId().equals(vr.getUserId())){
									allReplaceNum++;
									if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null && !"".equals(vr.getReceptTime())) {
										long replaceLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
										long replaceRecept = DateUtil.parse(vr.getReceptTime()).getTime();
										replaceTime += replaceLeave - replaceRecept;
									}
								}
							}
						}
					}
					/*if (vr.getAppointUserId().equals(vr.getUserId())) {
						// 替接
					} else {
					}*/
				} /*
					 * else { orderReplaceNum ++; }
					 */

				if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null && !"".equals(vr.getReceptTime())) {
					long leave = DateUtil.parse(vr.getLeaveTime()).getTime();
					long recept = DateUtil.parse(vr.getReceptTime()).getTime();
					timeDiff += leave - recept;
					totalNum++;
				}
			}
			// 平均接待时间

			if (totalNum > 0) {
				AverageLongTime = timeDiff / totalNum / 1000 / 60;
				strAverageLongTime = SysContent.get2Double(AverageLongTime);
				// 每次平均解放时长
				map.put("data_29", AverageLongTime);
			} else {
				map.put("data_29", 0);
			}

			map.put("data_24", timeDiff / 1000 / 60);

			map.put("data_25", newVisitTime / 1000 / 60);

			map.put("data_26", oldVisitTime / 1000 / 60 );

			map.put("data_27", replaceTime / 1000 / 60);

			int dayNum = DateUtil.getOffsetDays(sdf.parse(startTimeStr),sdf.parse(endTimeStr)) + 1;

			if (dayNum > 0) {
				map.put("data_28", timeDiff / dayNum / 1000 / 60);
			} else {
				map.put("data_28", 0);
			}

			map.put("data_2", effectiveNum);

			map.put("data_4", SysContent.get2Double(((double) effectiveNum / (double) vrList.size()) * 100) + "%");

			map.put("data_3", invalidNum);

			map.put("data_5", newCustomerNum);

			map.put("data_6", oldCustomerNum);

			map.put("data_7", newCustomerEffectiveNum);

			map.put("data_8", oldCustomerEffectiveNum);

			if (newCustomerNum > 0) {
				map.put("data_9",
						SysContent.get2Double(((double) newCustomerEffectiveNum / (double) newCustomerNum) * 100)
								+ "%");
				map.put("data_20",
						SysContent.get2Double(((double) newCustomerAppointAgentNum / (double) newCustomerNum) * 100)
								+ "%");
			} else {
				map.put("data_9", 0);
				map.put("data_20", 0);
			}

			if (oldCustomerNum > 0) {
				map.put("data_10",
						SysContent.get2Double(((double) oldCustomerEffectiveNum / (double) oldCustomerNum) * 100)
								+ "%");
				map.put("data_21",
						SysContent.get2Double(((double) oldCustomerAppointAgentNum / (double) oldCustomerNum) * 100)
								+ "%");
			} else {
				map.put("data_10", 0);
				map.put("data_21", 0);
			}

			map.put("data_11", SysContent.get2Double(((double) oldCustomerNum / (double) vrList.size()) * 100) + "%");

			map.put("data_14", appointAgentNum);

			map.put("data_15", newCustomerAppointAgentNum);

			map.put("data_16", oldCustomerAppointAgentNum);

			map.put("data_17", appointAgentEffectiveNum);

			if (appointAgentNum > 0) {
				map.put("data_18",
						SysContent.get2Double(((double) appointAgentEffectiveNum / (double) appointAgentNum) * 100)
								+ "%");
			} else {
				map.put("data_18", 0);
			}

			map.put("data_19", SysContent.get2Double(((double) appointAgentNum / (double) vrList.size()) * 100) + "%");

			map.put("data_22", allReplaceNum);

			if (allReplaceNum > 0) {
				map.put("data_23",
						SysContent.get2Double(((double) allReplaceNum / (double) vrList.size()) * 100) + "%");
			} else {
				map.put("data_23", 0);
			}

		} else {

			map.put("data_1", 0);
			map.put("data_2", 0);
			map.put("data_3", 0);
			map.put("data_4", 0);
			map.put("data_5", 0);
			map.put("data_6", 0);
			map.put("data_7", 0);
			map.put("data_8", 0);
			map.put("data_9", 0);
			map.put("data_10", 0);
			map.put("data_11", 0);
			map.put("data_12", 0);
			map.put("data_13", 0);
			map.put("data_14", 0);
			map.put("data_15", 0);
			map.put("data_16", 0);
			map.put("data_17", 0);
			map.put("data_18", 0);
			map.put("data_19", 0);
			map.put("data_20", 0);
			map.put("data_21", 0);
			map.put("data_22", 0);
			map.put("data_23", 0);
			map.put("data_24", 0);
			map.put("data_25", 0);
			map.put("data_26", 0);
			map.put("data_27", 0);
			map.put("data_28", 0);
			map.put("data_29", 0);

		}

		return map;
	}

	/**
	 * 中介经纪人分割线___________________________________________________________________________________________________________________________
	 */
	/**
	 * 中介app城市存session____________________________________________________
	 */
	@Override
	public CitySessionDTO findCityIntoSession(User user) {

		Shops shops = (Shops) baseDao.loadObject("from Shops where shopId = '" + user.getParentId() + "' ");
		String city = shops.getCity();
		String[] cityArr = city.split("-");
		CountryProvinceInfo cp = (CountryProvinceInfo) baseDao
				.loadObject("from CountryProvinceInfo where cityId = '" + cityArr[1] + "' ");
		CitySessionDTO csdto = new CitySessionDTO();
		csdto.setCityId(cp.getCityId());
		csdto.setCityName(cp.getCityName());

		return csdto;
	}

	@Override
	public CitySessionDTO findCityIntoSessionByCityId(String cityId, User user) {
		CitySessionDTO csdto = new CitySessionDTO();

		if (cityId != null && !"".equals(cityId)) {
			CountryProvinceInfo cp = (CountryProvinceInfo) baseDao
					.loadObject("from CountryProvinceInfo where cityId = '" + cityId + "' ");
			csdto.setCityId(cityId);
			csdto.setCityName(cp.getCityName());

		} else {
			Shops shops = (Shops) baseDao.loadObject("from Shops where shopId = '" + user.getParentId() + "' ");
			String city = shops.getCity();
			String[] cityArr = city.split("-");
			CountryProvinceInfo cp = (CountryProvinceInfo) baseDao
					.loadObject("from CountryProvinceInfo where cityId = '" + cityArr[1] + "' ");
			csdto.setCityId(cp.getCityId());
			csdto.setCityName(cp.getCityName());
		}
		return csdto;
	}

	@Override
	public List<AllCheckedCustomerDTO> findAllCheckedCustomer(String time, User user) throws ParseException {
		List<AllCheckedCustomerDTO> accdtoList = new ArrayList<>();

		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (time != null && !"".equals(time)) {
			startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(time)));
			endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(time)));
		}
		Set<String> set = new HashSet<String>();

		List<ProjectCustomers> pcList = baseDao
				.findByHql("from ProjectCustomers where projectId = '" + user.getParentId() + "' and lastTime >= '"
						+ startTimeStr + "' and lastTime <= '" + endTimeStr + "' ");
		if (pcList.size() > 0) {
			for (ProjectCustomers pc : pcList) {
				set.add(pc.getOwnerUserId());
			}
			for (String string : set) {
				AllCheckedCustomerDTO accdto = new AllCheckedCustomerDTO();
				User u = (User) baseDao.loadObject("from User where userId = '" + string + "' ");
				accdto.setAgentId(u.getUserId());
				accdto.setAgentName(u.getUserCaption());
				accdto.setPhone(u.getPhone());
				accdto.setPhoto(u.getPhoto());
				accdto.setSex(u.getSex());
				int havePanKeNum = 0;
				int notPanKeNum = 0;
				List<VisitRecords> pcList3 = new ArrayList<>();
				List<VisitRecords> pcList4 = new ArrayList<>();
				
				List<VisitRecords> vList = baseDao
						.findByHql("from VisitRecords where userId = '" + string + "' and arriveTime >= '"
								+ startTimeStr + "' and arriveTime <= '" + endTimeStr + "' and writeState = 1");

				for (VisitRecords vr : vList) {
					Comment c = (Comment) baseDao.loadObject("from Comment where visitNo = '" + vr.getVisitNo() + "' ");
					if (c != null && !"".equals(c)) {
						havePanKeNum++;
						pcList3.add(vr);
					} else {
						notPanKeNum++;
						pcList4.add(vr);
					}
				}
				accdto.setHaveCheckedNum(havePanKeNum);
				accdto.setHaveCheckedList(pcList3);
				accdto.setNotCheckedNum(notPanKeNum);
				accdto.setNotCheckedList(pcList4);
				accdtoList.add(accdto);
			}
		}

		return accdtoList;
	}

	@Override
	public void updateProjectCustomerByManager(Integer visitNo, User user, String agentId, String projectCustomerId,
			String customerComment, String describe, String commitSpeed, String customerAspiration, String customerInfo,
			String isAgree) throws Exception {

		Comment c = new Comment();
		c.setUserId(user.getUserId());
		c.setCustomerId(projectCustomerId);
		c.setCommentTime(DateTime.toString(new Date()));
		if (describe != null && !"".equals(describe)) {
			c.setCustomerDescribe(describe);
		}
		if (commitSpeed != null && !"".equals(commitSpeed)) {
			c.setCommitSpeed(commitSpeed);
		}
		if (isAgree != null && !"".equals(isAgree)) {
			c.setIsAgree(isAgree);
		}
		if (customerComment != null && !"".equals(customerComment)) {
			c.setCustomerComment(customerComment);
		}
		if (customerAspiration != null && !"".equals(customerAspiration)) {
			c.setCustomerAspiration(customerAspiration);
		}
		if (visitNo != null && !"".equals(visitNo)) {
			c.setVisitNo(visitNo);
		}
		// 添加客户信息
		CustomerInfoDB cInfo = new CustomerInfoDB();
		ProjectCustomers pc = (ProjectCustomers) baseDao
				.loadObject("from ProjectCustomers where projectCustomerId = '" + projectCustomerId + "'");
		cInfo.setCustomerName(pc.getProjectCustomerName());
		cInfo.setCustomerPhone(pc.getProjectCustomerPhone());
		List<VisitRecords> vrs = baseDao.findByHql("from VisitRecords where customerId='" + projectCustomerId
				+ "' and visitStatus!=2 order by arriveTime");
		// 到访次数
		cInfo.setComeNum(vrs.size());
		List<ContractRecords> crs = baseDao.findByHql(
				"from ContractRecords where projectCustomerId='" + projectCustomerId + "' order by applyTime DESC");
		// 认购状态
		if (crs.size() > 0) {
			ContractRecords contractRecords = crs.get(0);
			cInfo.setStatus(contractRecords.getRecordStatus());
		}
		// 所有标签
		TagsRelation tr = (TagsRelation) baseDao
				.loadObject("from TagsRelation where targetId = '" + projectCustomerId + "'");
		if (tr != null) {
			cInfo.setTags(tr.getTags());
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(cInfo);
		c.setCustomerInfo(json);
		baseDao.saveOrUpdate(c);
	}

	@Override
	public Map<String, String> findCustomerAffiliation(String projectCustomerId, User user) {

		Map<String, String> map = new HashMap<>();
		if (projectCustomerId != null && !"".equals(projectCustomerId)) {
			ProjectCustomers pc = (ProjectCustomers) baseDao
					.loadObject("from ProjectCustomers where projectCustomerId = '" + projectCustomerId + "' ");
			map.put("agentId", pc.getOwnerUserId());
			User u = (User) baseDao.loadObject("from User where userId = '" + pc.getOwnerUserId() + "' ");

			map.put("agentName", u.getUserCaption());
			map.put("agentPhone", u.getPhone());
			Integer sex = pc.getSex();
			String sex1 = "未知";
			if (sex != null) {
				int intValue = sex.intValue();
				if (1 == intValue) {
					sex1 = "男";
				}
				if (2 == intValue) {
					sex1 = "女";
				}
			}
			map.put("sex", sex1);
		}

		return map;
	}

	/**
	 * 业务报表_成交________
	 */
	@Override
	public Map<String, Object> findDealStatement(String startTime, String endTime, User user) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		// 认购套数
		int allContractNum = 0;
		Set<Integer> st = new HashSet<>();
		// 认购金额
		double allContractMoney = 0.00;
		// 认购到款金额
		double reachContractMoney = 0.00;
		// 认购锁定房源货值
		double lockHousePrice = 0.00;
		// 已签约数
		int haveDealNum = 0;
		// 放弃签约数
		int abandonDealNum = 0;
		// 待签约数
		int readyToDealNum = 0;
		// 已签约房源货值
		double haveDealHousePrice = 0.00;
		// 放弃签约房源货值
		double abandonDealHousePrice = 0.00;
		// 待签约房源货值
		double readyToDealHousePrice = 0.00;
		// 按揭到款套数
		int haveGetMoneyNum = 0;
		// 新增2次到访
		int newAddTwiceVisitNum = 0;
		// 客户回头数
		int customerReturnBackVisitNum = 0;
		// 储客成交人数
		int reserveCustomerToDealNum = 0;

		Set<String> ss = new HashSet<>();
		Set<String> ss2 = new HashSet<>();

		Set<String> ss3 = new HashSet<>();
		// Set<String> ss4 = new HashSet<>();

		map.put("name_1", "新增储客数");
		// 新增二次来访客户数
		map.put("name_2", "新增二次来访客户数");
		map.put("name_3", "老客户数");
		map.put("name_4", "客户回头率");
		map.put("name_5", "总认购客户数");
		map.put("name_6", "老客户认购数");
		map.put("name_7", "新客户认购数");
		map.put("name_8", "老客户认购率");
		map.put("name_9", "新客户认购率");
		map.put("name_10", "认购套数");
		map.put("name_11", "认购金额");
		map.put("name_12", "认购到款金额");
		map.put("name_13", "认购锁定房源货值");
		map.put("name_14", "已签约数");
		map.put("name_15", "放弃签约数");
		map.put("name_16", "待签约数");
		map.put("name_17", "下定数");
		map.put("name_18", "已签约房源货值");
		map.put("name_19", "放弃签约房源货值");
		map.put("name_20", "待签约房源货值");
		map.put("name_21", "来访成交比");
		map.put("name_22", "认购签约率");
		map.put("name_23", "储客成交比");
		
		map.put("length", 23);
		// 该时间段到访的人数
		List<VisitRecords> vrList22 = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
				+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");
		if (vrList22.size() > 0) {
			for (VisitRecords visitRecords : vrList22) {
				if (visitRecords.getWriteState() != null && !"".equals(visitRecords.getWriteState()) && visitRecords.getWriteState() == 1) {
					ss3.add(visitRecords.getPhone());
				}
			}
			if (ss3.size() > 0) {
				for (String string : ss3) {
					List<VisitRecords> vrList55 = baseDao.findByHql("from VisitRecords where projectId = '"
							+ user.getParentId() + "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '"
							+ endTimeStr + "' and phone = '" + string + "' ");
					if (vrList55.size() >= 2) {
						customerReturnBackVisitNum++;
					} else if (vrList55.size() == 1) {
						List<VisitRecords> vrList66 = baseDao
								.findByHql("from VisitRecords where projectId = '" + user.getParentId()
										+ "' and arriveTime < '" + startTimeStr + "' and phone = '" + string + "' ");
						if (vrList66.size() == 1) {
							customerReturnBackVisitNum++;
						}
					}
					//该客户在改时间之前的到访记录
					List<VisitRecords> vrList33 = baseDao
							.findByHql("from VisitRecords where projectId = '" + user.getParentId()
									+ "' and arriveTime < '" + startTimeStr + "' and phone = '" + string + "' ");
					
					if (vrList33.size() > 0) {
						ss.add(string);
						//如果之前是一次现在也一次为2次到访
						if (vrList33.size() == 1) {
							List<VisitRecords> vrList44 = baseDao.findByHql("from VisitRecords where projectId = '"
									+ user.getParentId() + "' and arriveTime >= '" + startTimeStr
									+ "' and arriveTime <= '" + endTimeStr + "' and phone = '" + string + "' ");
							if (vrList44.size() == 1) {
								newAddTwiceVisitNum++;
							}
						}
					} else {
						ss2.add(string);
						List<VisitRecords> vrList44 = baseDao.findByHql("from VisitRecords where projectId = '"
								+ user.getParentId() + "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '"
								+ endTimeStr + "' and phone = '" + string + "' ");
						//时间段之前没来,该时间段之内来了2次
						if (vrList44.size() == 2) {
							newAddTwiceVisitNum++;
						}
					}
				}
				List<ProjectCustomers> pcList = baseDao.findByHql("from ProjectCustomers where projectId = '"
						+ user.getParentId() + "' and lastTime <= '" + endTimeStr + "' ");

				if (pcList.size() > 0) {
					// 客户回头率
					map.put("data_4",
							SysContent.get2Double(((double) customerReturnBackVisitNum / (double) pcList.size()) * 100)
									+ "%");
				} else {
					map.put("data_4", 0);
				}
			}
			map.put("data_4", 0);
			map.put("data_2", newAddTwiceVisitNum);


			if (ss.size() > 0) {
				// 老客户数
				map.put("data_3", ss.size());
			} else {
				map.put("data_3", 0);
			}
		}
		

		List<House> hList = baseDao.findByHql("from House where projectId = '" + user.getParentId() + "' ");

		Set<String> ss10 = new HashSet<>();

		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
				+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' ");
		//总储客数_________________
		List<ProjectCustomers> pcList = baseDao.findByHql("from ProjectCustomers where createTime >= '"+startTimeStr+"' and createTime <= '"+ endTimeStr+"' and projectId = '" + user.getParentId()+"' ");
		if (pcList.size()>0){
			// 新增总储客数
			map.put("data_1", pcList.size());
		}else {
			map.put("data_1", 0);
		}
		
		
		
		if (crList.size() > 0) {
			for (ContractRecords cr : crList) {
				
				//成交数据里面是新增储客成交的_____________________________
				ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject("from ProjectCustomers where createTime >= '"+startTimeStr+"' and createTime <= '"+ endTimeStr+"' and projectCustomerPhone = '"+cr.getCustomerPhone()+"' and projectId = '" + user.getParentId()+"' ");
				if (pc != null && !"".equals(pc)){
					reserveCustomerToDealNum ++;
				}
				if (reserveCustomerToDealNum > 0){
					map.put("data_23", Double.parseDouble(
							SysContent.get2Double(((double) reserveCustomerToDealNum / (double) crList.size()) * 100)));
				}else {
					map.put("data_23", 0.00);
				}
				// 总认购客户数(去重)
				ss10.add(cr.getCustomerPhone());

				st.add(cr.getHouseNum());
				if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
					allContractMoney += cr.getBuyPrice();
				}
				if (cr.getRecordStatus() == 4) {
					readyToDealNum++;
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
						House h = (House) baseDao.loadObject("from House where houseNum = '" + cr.getHouseNum() + "' ");
						EnterBuy eb = (EnterBuy) baseDao
								.loadObject("from EnterBuy where projectId = '" + cr.getProjectId() + "' ");
						readyToDealHousePrice += cr.getBuyPrice();
					}
				}
				if (cr.getRecordStatus() == 4 || cr.getRecordStatus() == 5) {
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
						House h = (House) baseDao.loadObject("from House where houseNum = '" + cr.getHouseNum() + "' ");
						EnterBuy eb = (EnterBuy) baseDao
								.loadObject("from EnterBuy where projectId = '" + cr.getProjectId() + "' ");
						reachContractMoney += eb.getDposit();
						lockHousePrice += cr.getBuyPrice();
					}
					if (cr.getAccountStyle() == 2) {
						haveGetMoneyNum++;
					}
				}
				if (cr.getRecordStatus() == 5) {
					haveDealNum++;
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
						House h = (House) baseDao.loadObject("from House where houseNum = '" + cr.getHouseNum() + "' ");
						haveDealHousePrice += cr.getBuyPrice();
					}
				}
				if (cr.getRecordStatus() == 7) {
					abandonDealNum++;
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
						House h = (House) baseDao.loadObject("from House where houseNum = '" + cr.getHouseNum() + "' ");
						abandonDealHousePrice += cr.getBuyPrice();
					}
				}
			}

			// 老客户认购数
			int oldCustomerReadyToBuyNum = 0;
			// 新客户认购数
			int newCustomerReadyToBuyNum = 0;
			
			// 总认购客户数(去重)
			for (String string : ss10) {
				List<VisitRecords> vrList88 = baseDao
						.findByHql("from VisitRecords where projectId = '" + user.getParentId()
								+ "' and arriveTime < '" + startTimeStr + "' and phone = '" + string + "' ");
				if (vrList88.size() > 0) {
					// 老客户认购数
					List<ContractRecords> OldRecNum = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
					+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' and customerPhone = '"+ string + "' ");
					if (OldRecNum.size()>0){
						oldCustomerReadyToBuyNum += OldRecNum.size();
					}
					//之前时间段没有来过
				} else {
					List<VisitRecords> vrList8888 = baseDao
							.findByHql("from VisitRecords where projectId = '" + user.getParentId()
									+ "' and arriveTime >= '" + startTimeStr + "' and phone = '" + string + "' and arriveTime <= '"+endTimeStr+"' ");
					// 新客户认购数________改时间段内来一次
					if (vrList8888.size() == 1){
						List<ContractRecords> newRecNum = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
						+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' and customerPhone = '"+ string + "' ");
						if (newRecNum.size()>0) {
							newCustomerReadyToBuyNum += newRecNum.size();
						}
					}else {
						// 老客户认购数
						List<ContractRecords> OldRecNum2 = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
						+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' and customerPhone = '"+ string + "' ");
						if (OldRecNum2.size()>0){
							oldCustomerReadyToBuyNum += OldRecNum2.size();
						}
					}
				}
			}

			// 总认购客户数
			if (ss10.size() > 0) {

				map.put("data_5", ss10.size());
				//总认购书
				List<ContractRecords> allRecNum = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
						+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' ");
				if (oldCustomerReadyToBuyNum > 0) {
					map.put("data_6", oldCustomerReadyToBuyNum);
					map.put("data_8",
							SysContent.get2Double(((double) oldCustomerReadyToBuyNum / (double) allRecNum.size()) * 100)
									+ "%");
				} else {
					map.put("data_6", 0);
					map.put("data_8", 0);
				}
				if (newCustomerReadyToBuyNum > 0) {
					map.put("data_7", newCustomerReadyToBuyNum);
					map.put("data_9",
							SysContent.get2Double(((double) newCustomerReadyToBuyNum / (double) allRecNum.size()) * 100)
									+ "%");
				} else {
					map.put("data_7", 0);
					map.put("data_9", 0);
				}
			} else {
				map.put("data_5", 0);
				map.put("data_6", 0);
				map.put("data_8", 0);
				map.put("data_7", 0);
				map.put("data_9", 0);
			}

			if (st.size() > 0) {
				map.put("data_10", st.size());
				// 认购套数占比
				// map.put("readyToAllPercent",
				// SysContent.get2Double(((double)allContractNum/(double)st.size())*100)+"%");
			} else {
				map.put("data_10", 0);
				// map.put("readyToAllPercent", 0);
			}

			map.put("data_11", SysContent.get2Double((double) allContractMoney / 10000) + "万元");

			map.put("data_12", SysContent.get2Double((double) reachContractMoney / 10000) + "万元");

			map.put("data_13", SysContent.get2Double((double) lockHousePrice / 10000) + "万元");

			map.put("data_14", haveDealNum);

			map.put("data_15", abandonDealNum);

			map.put("data_16", readyToDealNum);
			// 下定数

			map.put("data_17", readyToDealNum);

			map.put("data_18", SysContent.get2Double((double) haveDealHousePrice / 10000) + "万元");

			map.put("data_19", SysContent.get2Double((double) abandonDealHousePrice / 10000) + "万元");

			map.put("data_20", SysContent.get2Double((double) readyToDealHousePrice / 10000) + "万元");

			String visitDeailSql = "SELECT c.recordNo,c.userId,c.houseNum,c.customerName,c.customerPhone,v.num ,c.recordStatus ,v.arriveTime"
					+ " FROM t_contractrecords c,(SELECT customerId,phone,COUNT(phone) num ,arriveTime FROM t_visitrecords "
					+ "WHERE writeState = 1 GROUP BY phone) v WHERE c.customerPhone = v.phone and c.recordStatus = 5 and c.projectId = '"
					+ user.getParentId() + "'";// 到访成交

			visitDeailSql += " and v.arriveTime >= '" + startTimeStr + "' and v.arriveTime <= '" + endTimeStr + "' "
					+ " and c.contractConfirmTime >= '" + startTimeStr + "' and c.contractConfirmTime < '" + endTimeStr
					+ "' GROUP BY (c.customerPhone)";
			List list88 = baseDao.queryBySql(visitDeailSql);

			Set<String> ss11 = new HashSet<>();

			List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
					+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");
			for (VisitRecords visitRecords : vrList) {
				ss11.add(visitRecords.getPhone());
			}

			// 首次有效到访数
			int firstEffectiveNum = 0;
			// 首次无效数
			int firstInvalidNum = 0;
			
			if (vrList.size() > 0) {
				//这段时间内是首次到访客户
				Set<String> phoneSet = new HashSet<>();
					// 总接访量
					for (VisitRecords vr : vrList) {
						if (vr.getPhone() != null && !"".equals(vr.getPhone())){
							List<VisitRecords> vrList2 = baseDao.findByHql("from VisitRecords where projectId = '"+user.getParentId()+"' "
									+ " and arriveTime < '"+startTimeStr + "' and phone = '"+vr.getPhone()+"' ");
							if (vrList2.size() < 1 || vrList2 == null){
								phoneSet.add(vr.getPhone());
							}
						}else {
							firstInvalidNum ++;
						}
					}
				// 来访成交比
				//新增储客中成交客户数/新增储客数+无效接访数
				if (list88.size() > 0) {
					map.put("data_21",
							SysContent.get2Double(((double) list88.size() / (double) (phoneSet.size()+firstInvalidNum)) * 100) + "%");
				} else {
					map.put("data_21", 0);
				}
			} else {
				map.put("data_21", 0);
			}

			if (haveDealNum > 0) {
				// 认购签约率
				map.put("data_22", SysContent.get2Double(((double) haveDealNum / (double) crList.size()) * 100) + "%");
			} else {
				map.put("data_22", 0);
			}
			
		} else {
			map.put("data_2", 0);
			map.put("data_3", 0);
			map.put("data_4", 0);
			map.put("data_5", 0);
			map.put("data_6", 0);
			map.put("data_7", 0);
			map.put("data_8", 0);
			map.put("data_9", 0);
			map.put("data_10", 0);
			map.put("data_11", 0);
			map.put("data_12", 0);
			map.put("data_13", 0);
			map.put("data_14", 0);
			map.put("data_15", 0);
			map.put("data_16", 0);
			map.put("data_17", 0);
			map.put("data_18", 0);
			map.put("data_19", 0);
			map.put("data_20", 0);
			map.put("data_21", 0);
			map.put("data_22", 0);
			map.put("data_23", 0);
		}
		return map;
	}

	@Override
	public Map<String, Object> findOutFieldStatement(String startTime, String endTime, User user)
			throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		// 报备到访数
		int grToGetVisitNum = 0;
		// 外场成交数
		int outFieldToDealNum = 0;
		// 内场成交数
		int intFiledToDealNum = 0;

		// 备案客户数
		List<GuideRecords> grList = baseDao.findByHql("from GuideRecords where applyTime >= '" + startTimeStr
				+ "' and applyTime <= '" + endTimeStr + "' and projectId = '" + user.getParentId() + "' ");
		map.put("name_1", "报备客户数");
		map.put("name_2", "报备到访客户数");
		map.put("name_3", "备案未到访客户数");
		map.put("name_4", "报备到访率");
		map.put("name_5", "外场导客占比");
		map.put("name_6", "带客成交数");
		map.put("name_7", "外场成交数");
		map.put("name_8", "外场成交占比");
		map.put("name_9", "内场成交数");
		map.put("length", 9);
		if (grList.size() > 0) {
			map.put("data_1", grList.size());
			for (GuideRecords gr : grList) {
				if (gr.getVisitNo() != null && !"".equals(gr.getVisitNo())) {
					grToGetVisitNum++;
				}
			}
			map.put("data_2", grToGetVisitNum);
			map.put("data_3", grList.size() - grToGetVisitNum);
			map.put("data_4", SysContent.get2Double(((double) grToGetVisitNum / (double) grList.size()) * 100) + "%");

			List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
					+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");

			if (vrList.size() > 0) {
				// 外场导客占比
				map.put("data_5",
						SysContent.get2Double(((double) grToGetVisitNum / (double) vrList.size()) * 100) + "%");
			} else {
				map.put("data_5", 0);
			}
			//置业顾问成交的
			List<String> sList = new ArrayList<>();
			// 带客成交数
			int guideToDealNum = 0;

			List<ContractRecords> crList = baseDao
					.findByHql("from ContractRecords where projectId = '" + user.getParentId() + "' and applyTime >= '"
							+ startTimeStr + "' and applyTime <= '" + endTimeStr + "' ");
			List<ContractRecordsFlowRecord> crfrList = baseDao.findByHql("from ContractRecordsFlowRecord where projectId = '" + user.getParentId() + "' and operateTime >= '"
					+ startTimeStr + "' and operateTime <= '" + endTimeStr + "' ");
			if (crfrList.size()>0){
				for (ContractRecordsFlowRecord cr : crfrList) {
					if (cr.getOrderSort() == 2005){
						ContractRecords crNew = (ContractRecords) baseDao.loadObject("from ContractRecords where recordNo = '"+cr.getRecordNo()+"' ");
						String newUserId = crNew.getUserId();
						User u = (User) baseDao.loadObject("from User where userId = '" + newUserId + "' ");
						Set<Role> sr = u.getRoleId();
						for (Role role : sr) {
							if (role.getRoleId() == 1 || role.getRoleId() == 2) {
								outFieldToDealNum++;
							}
							if (role.getRoleId() == 3) {
								sList.add(crNew.getCustomerPhone());
								intFiledToDealNum++;
							}
						}
					}
				}
				
				map.put("data_7", outFieldToDealNum);
				map.put("data_8", SysContent.get2Double(((double) outFieldToDealNum / (double) (outFieldToDealNum + intFiledToDealNum)) * 100) + "%");
				map.put("data_9", intFiledToDealNum);
				for (String string : sList) {
					List<GuideRecords> grList2 = baseDao.findByHql("from GuideRecords where applyTime >= '"
							+ startTimeStr + "' and applyTime <= '" + endTimeStr + "' and projectId = '"
							+ user.getParentId() + "' and customerPhone = '" + string + "' ");
					if (grList2.size() > 0) {
						guideToDealNum++;
					}
				}
				map.put("data_6", guideToDealNum);
			}else {
				map.put("data_5", 0);
				map.put("data_6", 0);
				map.put("data_7", 0);
				map.put("data_8", 0);
				map.put("data_9", 0);
			}
			
			/*if (crList.size() > 0) {
				for (ContractRecords cr : crList) {
					
					if (cr.getRecordStatus() == 5) {
						String userId = cr.getUserId();
						User u = (User) baseDao.loadObject("from User where userId = '" + cr.getUserId() + "' ");
						Set<Role> sr = u.getRoleId();
						for (Role role : sr) {
							if (role.getRoleId() == 1 || role.getRoleId() == 2) {
								outFieldToDealNum++;
							}
							if (role.getRoleId() == 3) {
								sList.add(cr.getCustomerPhone());
								intFiledToDealNum++;
							}
						}
					}
				}
				map.put("data_7", outFieldToDealNum);
				map.put("data_8", SysContent.get2Double(((double) outFieldToDealNum / (double) (outFieldToDealNum + intFiledToDealNum)) * 100) + "%");
				map.put("data_9", intFiledToDealNum);
				for (String string : sList) {
					List<GuideRecords> grList2 = baseDao.findByHql("from GuideRecords where applyTime >= '"
							+ startTimeStr + "' and applyTime <= '" + endTimeStr + "' and projectId = '"
							+ user.getParentId() + "' and customerPhone = '" + string + "' ");
					if (grList2.size() > 0) {
						guideToDealNum++;
					}
				}

				map.put("data_6", guideToDealNum);

			} else {
				map.put("data_5", 0);
				map.put("data_6", 0);
				map.put("data_7", 0);
				map.put("data_8", 0);
				map.put("data_9", 0);
			}*/

		} else {
			map.put("data_1", 0);
			map.put("data_2", 0);
			map.put("data_3", 0);
			map.put("data_4", 0);

			List<VisitRecords> vrList = baseDao
					.findByHql("from VisitRecords where projectId = '" + user.getParentId() + "' and arriveTime >= '"
							+ startTimeStr + "' and arriveTime <= '" + endTimeStr + "' and isNew is true");

			if (vrList.size() > 0) {
				// 外场导客占比
				// map.put("outFieldToVisitRate",
				// grToGetVisitNum/vrList.size());
				map.put("data_5",
						SysContent.get2Double(((double) grToGetVisitNum / (double) vrList.size()) * 100) + "%");
			} else {
				map.put("data_5", 0);
			}
			List<String> sList = new ArrayList<>();
			// 带客成交数
			int guideToDealNum = 0;

			List<ContractRecords> crList = baseDao
					.findByHql("from ContractRecords where projectId = '" + user.getParentId() + "' and applyTime >= '"
							+ startTimeStr + "' and applyTime <= '" + endTimeStr + "' ");
			if (crList.size() > 0) {
				for (ContractRecords cr : crList) {
					if (cr.getRecordStatus() == 5) {
						String userId = cr.getUserId();
						User u = (User) baseDao.loadObject("from User where userId = '" + cr.getUserId() + "' ");
						Set<Role> sr = u.getRoleId();
						for (Role role : sr) {
							if (role.getRoleId() == 1 || role.getRoleId() == 2) {
								outFieldToDealNum++;
							}
							if (role.getRoleId() == 3) {
								sList.add(cr.getCustomerPhone());
								intFiledToDealNum++;
							}
						}
					}
					// map.put("outFieldToDealNum", outFieldToDealNum);
					// map.put("outFieldToDealRate",
					// outFieldToDealNum/crList.size());

				}
				map.put("data_7", outFieldToDealNum);
				map.put("data_8",
						SysContent.get2Double(((double) outFieldToDealNum / (double) (outFieldToDealNum + intFiledToDealNum)) * 100) + "%");
				map.put("data_9", intFiledToDealNum);
				for (String string : sList) {
					List<GuideRecords> grList2 = baseDao.findByHql("from GuideRecords where applyTime >= '"
							+ startTimeStr + "' and applyTime <= '" + endTimeStr + "' and projectId = '"
							+ user.getParentId() + "' and customerPhone = '" + string + "' ");
					if (grList2.size() > 0) {
						guideToDealNum++;
					}
				}
				map.put("data_6", guideToDealNum);
			} else {
				map.put("data_5", 0);
				map.put("data_6", 0);
				map.put("data_7", 0);
				map.put("data_8", 0);
				map.put("data_9", 0);
			}
		}

		return map;
	}

	@Override
	public List<ManagerOwnAnalyse> findManagerOwnAnalyse(User user) {

		List<ManagerOwnAnalyse> moaList = baseDao.findByHql("from ManagerOwnAnalyse where managerId = '"
				+ user.getUserId() + "' and ownAnalyseStatus = 0 order by addTime DESC");

		return moaList;
	}

	@Override
	public void updateOneManagerOwnAnalyse(User user, String chartDataIds) {
		if (chartDataIds != null && !"".equals(chartDataIds)) {
			String[] categoryIdsStr = chartDataIds.split(",");
			for (String string : categoryIdsStr) {
				ManaerChartData mcd = (ManaerChartData) baseDao
						.loadObject("from ManaerChartData where chartDataId = '" + string.replace("$", "") + "' ");
				ManagerOwnAnalyse manageroa = (ManagerOwnAnalyse) baseDao
						.loadObject("from ManagerOwnAnalyse where managerId = '" + user.getUserId()
								+ "' and chartDataId = '" + string.replace("$", "") + "' ");
				if (manageroa != null && !"".equals(manageroa)) {
					manageroa.setOwnAnalyseStatus(0);
					manageroa.setAddTime(DateTime.toString1(new Date()));
					baseDao.saveOrUpdate(manageroa);
				} else {
					ManagerOwnAnalyse moa = new ManagerOwnAnalyse();
					moa.setManagerId(user.getUserId());
					moa.setManagerName(user.getUserCaption());
					moa.setChartDataId(mcd.getChartDataId());
					moa.setButtonText(mcd.getButtonText());
					moa.setAddTime(DateTime.toString1(new Date()));
					moa.setOwnAnalyseStatus(0);
					moa.setIconUrl(mcd.getIconUrl());
					baseDao.saveOrUpdate(moa);
				}
			}
		}
	}

	@Override
	public void updateManagerOwnAnalyse(User user, String chartDataIds) {
		if (chartDataIds != null && !"".equals(chartDataIds)) {
			String[] categoryIdsStr = chartDataIds.split(",");
			for (String string : categoryIdsStr) {
				String str = string.substring(1);
				ManagerOwnAnalyse moa = (ManagerOwnAnalyse) baseDao
						.loadObject("from ManagerOwnAnalyse where managerId = '" + user.getUserId()
								+ "' and chartDataId = '" + str.replace("$", "") + "' ");
				if (moa != null && !"".equals(moa)) {
					moa.setDeleteTime(DateTime.toString1(new Date()));
					moa.setOwnAnalyseStatus(1);
					baseDao.saveOrUpdate(moa);
				}
			}
		}
	}

	@Override
	public Map<String, Object> findReceptionByManager(String startTime, String endTime, User user, String chartDataId)
			throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
				+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");

		// 有效接访量
		int effectiveNum = 0;
		// 无效接访量
		int invalidNum = 0;
		// map.put("graph_2", "码表图");
		map.put("title", "观察客户接待成效");
		// map.put("name_1", "总接访数");
		map.put("name_1", "有效接访数");
		map.put("name_2", "无效接访数");
		// map.put("name_4", "有效接访率");
		map.put("length", 2);

		if (vrList.size() > 0) {
			// 总接访量
			// map.put("data_1", vrList.size());
			for (VisitRecords vr : vrList) {
				if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {
					if (vr.getWriteState() == 1) {
						effectiveNum++;
					} else {
						invalidNum++;
					}
				} else {
					invalidNum++;
				}
			}
			map.put("data_1", effectiveNum);
			map.put("data_2", invalidNum);
			// if (effectiveNum >0) {
			// map.put("data_4", effectiveNum/vrList.size());
			// }else {
			// map.put("data_4", 0);
			// }
		} else {
			map.put("data_1", 0);
			map.put("data_2", 0);
			// map.put("data_3", 0);
			// map.put("data_4", 0);
		}

		return map;
	}

	@Override
	public Map<String, Object> findNewAndOldCustomerPassagewayInfo(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
				+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");
		// 新客户通道接访
		int newCustomerNum = 0;
		// 老客户通道接访
		int oldCustomerNum = 0;
		// 新客户通道有效接访
		int newCustomerEffectiveNum = 0;
		int newCustomerWuxiaoNum = 0;
		// 老客户通道有效接访
		int oldCustomerEffectiveNum = 0;
		int oldCustomerWuxiaoNum = 0;

		map.put("title", "观察新老客户通道");
		// map.put("name_1", "新客户通道接访");
		// map.put("name_2", "老客户通道接访");
		map.put("name_1", "新客户通道无效接访");
		map.put("name_3", "新客户通道有效接访");
		map.put("name_4", "老客户通道有效接访");
		map.put("name_2", "老客户通道无效接访");
		// map.put("name_5", "新客户通道有效接访率");
		// map.put("name_6", "老客户通道有效接访率");
		// map.put("name_7", "老客户通道占比");
		map.put("length", 4);
		if (vrList.size() > 0) {
			for (VisitRecords vr : vrList) {
				if (vr.getIsNew() != null && !"".equals(vr.getIsNew())) {
					if (vr.getIsNew()) {
						//newCustomerNum++;
						if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {
							if (vr.getWriteState() == 1) {
								newCustomerEffectiveNum++;
							} else {
								newCustomerWuxiaoNum++;
							}
						}else {
							newCustomerWuxiaoNum++;
						}
					} else {
						//oldCustomerNum++;
						if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {
							if (vr.getWriteState() == 1) {
								oldCustomerEffectiveNum++;
							} else {
								oldCustomerWuxiaoNum++;
							}
						}else {
							oldCustomerWuxiaoNum++;
						}
					}
				}else {
					//newCustomerNum++;
					if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {
						if (vr.getWriteState() == 1) {
							newCustomerEffectiveNum++;
						} else {
							newCustomerWuxiaoNum++;
						}
					}
				}
			}
			map.put("data_1", newCustomerWuxiaoNum);
			map.put("data_2", oldCustomerWuxiaoNum);
			map.put("data_3", newCustomerEffectiveNum);
			map.put("data_4", oldCustomerEffectiveNum);

		} else {
			map.put("data_1", 0);
			map.put("data_2", 0);
			map.put("data_3", 0);
			map.put("data_4", 0);
			// map.put("data_5", 0);
			// map.put("data_6", 0);
			// map.put("data_7", 0);
		}

		return map;
	}

	@Override
	public Map<String, Object> findSeeAppointCustomerReceptionInfo(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
				+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");
		// 新客户通道接访
		int newCustomerNum = 0;
		// 老客户通道接访
		int oldCustomerNum = 0;
		// 指定接访量
		int appointAgentNum = 0;
		// 新客户通道指定接访
		int newCustomerAppointAgentNum = 0;
		// 老客户通道指定接访
		int oldCustomerAppointAgentNum = 0;
		// 指定有效接访
		int appointAgentEffectiveNum = 0;

		map.put("title", "观察指定接访");
		map.put("name_1", "指定接访");
		map.put("name_2", "新客户通道接访");
		map.put("name_3", "老客户通道接访");
		map.put("name_4", "指定有效接访");
		// map.put("name_5", "指定接访率");
		map.put("name_5", "新客户通道指定接访");
		map.put("name_6", "老客户通道指定接访");
		// map.put("name_8", "指定无效接访率");
		map.put("length", 6);
		if (vrList.size() > 0) {
			for (VisitRecords vr : vrList) {
				if (vr.getIsNew() != null && !"".equals(vr.getIsNew())) {
					if (vr.getIsNew()) {
						newCustomerNum++;
					} else {
						oldCustomerNum++;
					}
				}else {
					newCustomerNum++;
				}
				if (vr.getAppointUserId() != null && !"".equals(vr.getAppointUserId())) {
					// 指定
						appointAgentNum++;
						if (vr.getIsNew() != null && !"".equals(vr.getIsNew())) {
							if (vr.getIsNew()) {
								newCustomerAppointAgentNum++;
							} else {
								oldCustomerAppointAgentNum++;
							}
						}else {
							newCustomerAppointAgentNum++;
						}
						if (vr.getWriteState() != null && !"".equals(vr.getWriteState())) {
							if (vr.getWriteState() == 1) {
								appointAgentEffectiveNum++;
							}
						}
				}
			}
			map.put("data_1", appointAgentNum);
			map.put("data_2", newCustomerNum);
			map.put("data_3", oldCustomerNum);
			map.put("data_4", appointAgentEffectiveNum);
			map.put("data_5", newCustomerAppointAgentNum);
			map.put("data_6", oldCustomerAppointAgentNum);
		}

		return map;
	}

	@Override
	public Map<String, Object> findSeeReplaceCustomerReceptionInfo(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
				+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");
		// 总替接数
		int allReplaceNum = 0;
		// 按序接访替接
		//int orderReplaceNum = 0;

		map.put("title", "观察替接");
		map.put("name_1", "总替接数");
		// map.put("name_2", "总替接率");
		map.put("length", 1);

		if (vrList.size() > 0) {
			for (VisitRecords vr : vrList) {
				//替接_)_______________________________________
				if (vr.getPhone() != null && !"".equals(vr.getPhone())){
					ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(" from ProjectCustomers where projectCustomerPhone = '"+vr.getPhone()+"' and projectId = '" + user.getParentId()+"' ");
					if (pc != null && !"".equals(pc)){
						if (pc.getOwnerUserId() != null && !"".equals(pc.getOwnerUserId())){
							if (!pc.getOwnerUserId().equals(vr.getUserId())){
								allReplaceNum++;
								
							}
						}
					}
				}
				/*if (vr.getAppointUserId() != null && !"".equals(vr.getAppointUserId())) {
					// 指定
					if (!vr.getAppointUserId().equals(vr.getUserId())) {
						// 替接
						allReplaceNum++;
					}
				}*/
			}
			map.put("data_1", allReplaceNum);
			// if (allReplaceNum > 0){
			// map.put("data_2",
			// SysContent.get2Double(((double)allReplaceNum/(double)vrList.size())*100)+"%");
			// }else {
			// map.put("data_2", 0);
			// }
		} else {
			map.put("data_1", 0);
			// map.put("data_2", 0);
		}

		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerReceptionTimeInfo(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
				+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");

		// 替接接访时长
		long replaceTime = 0L;
		// 新客户接访时长
		long newVisitTime = 0L;
		// 老客户接访时长
		long oldVisitTime = 0L;
		// 平均接访时常
		long timeDiff = 0L;
		int totalNum = 0;
		double AverageLongTime = 0.00;
		String strAverageLongTime = "0.00";

		map.put("title", "观察接访时长");
		map.put("name_1", "总接访时长");
		map.put("name_2", "新客户接访时长");
		map.put("name_3", "老客户接访时长");
		map.put("name_4", "替接接访时长");
		map.put("name_5", "每次接访平均时长");
		map.put("name_6", "每日平均接访时长");
		map.put("length", 6);

		if (vrList.size() > 0) {
			for (VisitRecords vr : vrList) {
				if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null && !"".equals(vr.getReceptTime())) {
					long leave = DateUtil.parse(vr.getLeaveTime()).getTime();
					long recept = DateUtil.parse(vr.getReceptTime()).getTime();
					timeDiff += leave - recept;
					totalNum++;
				}
				if (vr.getIsNew() != null && !"".equals(vr.getIsNew())) {
					if (vr.getIsNew()) {
						if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null && !"".equals(vr.getReceptTime())) {
							long newLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
							long newRecept = DateUtil.parse(vr.getReceptTime()).getTime();
							newVisitTime += newLeave - newRecept;
						}
					} else {
						if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null && !"".equals(vr.getReceptTime())) {
							long oldLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
							long oldRecept = DateUtil.parse(vr.getReceptTime()).getTime();
							oldVisitTime += oldLeave - oldRecept;
						}
					}
				}else {
					if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null && !"".equals(vr.getReceptTime())) {
						long newLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
						long newRecept = DateUtil.parse(vr.getReceptTime()).getTime();
						newVisitTime += newLeave - newRecept;
					}
				}
				/*if (vr.getAppointUserId() != null && !"".equals(vr.getAppointUserId())) {
					// 指定
					if (!vr.getAppointUserId().equals(vr.getUserId())) {
						// 替接
						if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null && !"".equals(vr.getReceptTime())) {
							long replaceLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
							long replaceRecept = DateUtil.parse(vr.getReceptTime()).getTime();
							replaceTime += replaceLeave - replaceRecept;
						}
					}
				}*/
				//替接_)_______________________________________
				if (vr.getPhone() != null && !"".equals(vr.getPhone())){
					ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(" from ProjectCustomers where projectCustomerPhone = '"+vr.getPhone()+"' and projectId = '" + user.getParentId()+"' ");
					if (pc != null && !"".equals(pc)){
						if (pc.getOwnerUserId() != null && !"".equals(pc.getOwnerUserId())){
							if (!pc.getOwnerUserId().equals(vr.getUserId())){
								// 替接
								if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime()) && vr.getReceptTime() != null && !"".equals(vr.getReceptTime())) {
									long replaceLeave = DateUtil.parse(vr.getLeaveTime()).getTime();
									long replaceRecept = DateUtil.parse(vr.getReceptTime()).getTime();
									replaceTime += replaceLeave - replaceRecept;
								}
							}
						}
					}
				}
			}
			// 平均接待时间
			if (totalNum > 0) {
				AverageLongTime = timeDiff / totalNum / 1000 / 60;
				strAverageLongTime = SysContent.get2Double(AverageLongTime);
				// 每次平均解放时长
				map.put("data_5", AverageLongTime);
			} else {
				map.put("data_5", 0);
			}
			map.put("data_1", timeDiff / 1000 / 60);
			map.put("data_2", newVisitTime / 1000 / 60);
			map.put("data_3", oldVisitTime / 1000 / 60);
			map.put("data_4", replaceTime / 1000 / 60);
			int dayNum = DateUtil.getOffsetDays(sdf.parse(startTimeStr),sdf.parse(endTimeStr));
			if (dayNum > 0) {
				map.put("data_6", timeDiff / dayNum / 1000 / 60);
			} else {
				map.put("data_6", 0);
			}
		} else {
			map.put("data_1", 0);
			map.put("data_2", 0);
			map.put("data_3", 0);
			map.put("data_4", 0);
			map.put("data_5", 0);
			map.put("data_6", 0);
		}

		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerContractRecordsInfo(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		// 认购套数
		int allContractNum = 0;
		Set<Integer> st = new HashSet<>();
		// 认购金额
		double allContractMoney = 0.00;
		// 认购到款金额
		double reachContractMoney = 0.00;
		// 认购锁定房源货值
		double lockHousePrice = 0.00;

		map.put("title", "认购货值分析");
		map.put("name_1", "认购套数");
		map.put("name_2", "认购金额");
		map.put("name_3", "认购到款金额");
		map.put("name_4", "认购锁定房源货值");
		map.put("length", 4);

		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
				+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' ");
		if (crList.size() > 0) {
			for (ContractRecords cr : crList) {
				st.add(cr.getHouseNum());
				if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
					allContractMoney += cr.getBuyPrice();
				}
				if (cr.getRecordStatus() == 4 || cr.getRecordStatus() == 5) {
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
						EnterBuy eb = (EnterBuy) baseDao
								.loadObject("from EnterBuy where projectId = '" + cr.getProjectId() + "' ");
						reachContractMoney += eb.getDposit();
						House h = (House) baseDao.loadObject("from House where houseNum = '" + cr.getHouseNum() + "' ");
						lockHousePrice += cr.getBuyPrice();
					}
				}
			}
			if (st.size() > 0) {
				map.put("data_1", st.size());
			} else {
				map.put("data_1", 0);
			}
			map.put("data_2", Double.parseDouble(SysContent.get2Double((double) allContractMoney)));
			map.put("data_3", Double.parseDouble(SysContent.get2Double((double) reachContractMoney)));
			map.put("data_4", Double.parseDouble(SysContent.get2Double((double) lockHousePrice)));
		} else {
			map.put("data_1", 0);
			map.put("data_2", 0);
			map.put("data_3", 0);
			map.put("data_4", 0);
		}
		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerHaveDealInfo(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		// 已签约数
		int haveDealNum = 0;
		// 放弃签约数
		int abandonDealNum = 0;
		// 待签约数
		int readyToDealNum = 0;

		map.put("title", "观察客户签约");
		map.put("name_1", "已签约数");
		map.put("name_2", "放弃签约数");
		map.put("name_3", "待签约数");
		map.put("length", 3);

		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
				+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' ");
		List<ContractRecordsFlowRecord> crfrList = baseDao.findByHql("from ContractRecordsFlowRecord where projectId = '" + user.getParentId() + "' and operateTime >= '"
				+ startTimeStr + "' and operateTime <= '" + endTimeStr + "' ");
		Set<Integer> iset = new HashSet<>();
		
		if (crfrList.size() > 0) {
			for (ContractRecordsFlowRecord crfr : crfrList) {
				iset.add(crfr.getRecordNo());
				
				/*if (crfr.getOrderSort() == 2007) {
					abandonDealNum++;
				}
				if (crfr.getOrderSort() == 2004) {
					if (crfr.getOrderSort() == 2005) {
						haveDealNum++;
					}else {
						readyToDealNum++;
					}
				}*/
			}
			for (Integer integer : iset) {
				ContractRecords cr = (ContractRecords) baseDao.loadObject("from ContractRecords where recordNo = '"+integer+"' ");
				if (cr.getRecordStatus() == 7){
					abandonDealNum++;
				}else if (cr.getRecordStatus() == 5){
					haveDealNum++;
				}else if (cr.getRecordStatus() == 4){
					readyToDealNum++;
				}
			}

			map.put("data_1", haveDealNum);
			map.put("data_2", abandonDealNum);
			map.put("data_3", readyToDealNum);
		} else {
			map.put("data_1", 0);
			map.put("data_2", 0);
			map.put("data_3", 0);
		}

		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerHaveDealMoneyInfo(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		// 已签约房源货值
		double haveDealHousePrice = 0.00;
		// 放弃签约房源货值
		double abandonDealHousePrice = 0.00;
		// 待签约房源货值
		double readyToDealHousePrice = 0.00;

		map.put("title", "签约货值分析");
		map.put("name_1", "已签约房源货值");
		map.put("name_2", "放弃签约房源货值");
		map.put("name_3", "待签约房源货值");
		map.put("length", 3);

		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
				+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' ");
		if (crList.size() > 0) {
			for (ContractRecords cr : crList) {
				if (cr.getRecordStatus() == 5) {
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
						//House h = (House) baseDao.loadObject("from House where houseNum = '" + cr.getHouseNum() + "' ");
						haveDealHousePrice += cr.getBuyPrice();
					}
				}
				if (cr.getRecordStatus() == 7) {
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
						/*House h = (House) baseDao
								.loadObject("from House where houseNum = '" + cr.getHouseNum() + "' ");*/
						abandonDealHousePrice += cr.getBuyPrice();
					}
				}
				if (cr.getRecordStatus() == 4) {
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())) {
						/*House h = (House) baseDao
								.loadObject("from House where houseNum = '" + cr.getHouseNum() + "' ");*/
						readyToDealHousePrice += cr.getBuyPrice();
					}
				}
			}
			map.put("data_1", Double.parseDouble(SysContent.get2Double((double) haveDealHousePrice)));
			map.put("data_2", Double.parseDouble(SysContent.get2Double((double) abandonDealHousePrice)));
			map.put("data_3", Double.parseDouble(SysContent.get2Double((double) readyToDealHousePrice)));

		} else {
			map.put("data_1", 0);
			map.put("data_2", 0);
			map.put("data_3", 0);
		}

		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerHaveDealNumberInfo(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		// 到款数
		int readyToDealNum = 0;
		// 已签约数
		int haveDealNum = 0;
		// 总储客数
		int allReserveCustomerNum = 0;
		// 储客成交数
		int reserveCustomerToDealNum = 0;

		map.put("title", "成交量分析");
		map.put("name_1", "来访成交比");
		map.put("name_2", "储客成交比");
		map.put("name_3", "认购签约率");
		map.put("length", 3);

		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
				+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' ");
		//总储客数_________________
		List<ProjectCustomers> pcList = baseDao.findByHql("from ProjectCustomers where createTime >= '"+startTimeStr+"' and createTime <= '"+ endTimeStr+"' and projectId = '" + user.getParentId()+"' ");
		if (pcList.size()>0){
			allReserveCustomerNum = pcList.size();
		}
		List<ContractRecordsFlowRecord> crfrList = baseDao.findByHql("from ContractRecordsFlowRecord where projectId = '" + user.getParentId() + "' and operateTime >= '"
				+ startTimeStr + "' and operateTime <= '" + endTimeStr + "' ");
		
		if (crfrList.size() > 0) {
			for (ContractRecordsFlowRecord crfr : crfrList) {
				ContractRecords cr = (ContractRecords) baseDao.loadObject("from ContractRecords where recordNo = '"+crfr.getRecordNo()+"' ");
				if (crfr.getOrderSort() == 2005){
					//成交数据里面是新增储客成交的_____________________________
					ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject("from ProjectCustomers where createTime >= '"+startTimeStr+"' and createTime <= '"+ endTimeStr+"' and projectCustomerPhone = '"+cr.getCustomerPhone()+"' and projectId = '" + user.getParentId()+"' ");
					if (pc != null && !"".equals(pc)){
						reserveCustomerToDealNum ++;
					}
					haveDealNum++;
				}
				
			}
			
			if (reserveCustomerToDealNum > 0){
				map.put("data_2", Double.parseDouble(
						SysContent.get2Double(((double) reserveCustomerToDealNum / (double) allReserveCustomerNum) * 100)));
			}else {
				map.put("data_2", 0.00);
			}
			
			String visitDeailSql = "SELECT c.recordNo,c.userId,c.houseNum,c.customerName,c.customerPhone,v.num ,c.recordStatus ,v.arriveTime"
					+ " FROM t_contractrecords c,(SELECT customerId,phone,COUNT(phone) num ,arriveTime FROM t_visitrecords "
					+ "WHERE writeState = 1 GROUP BY phone) v WHERE c.customerPhone = v.phone and c.recordStatus = 5 and c.projectId = '"
					+ user.getParentId() + "'";// 到访成交

			visitDeailSql += " and v.arriveTime >= '" + startTimeStr + "' and v.arriveTime <= '" + endTimeStr + "' "
					+ " and c.contractConfirmTime >= '" + startTimeStr + "' and c.contractConfirmTime < '" + endTimeStr
					+ "' GROUP BY (c.customerPhone)";
			List list88 = baseDao.queryBySql(visitDeailSql);

			Set<String> ss11 = new HashSet<>();

			List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
					+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");
			for (VisitRecords visitRecords : vrList) {
				ss11.add(visitRecords.getPhone());
			}
			//map.put("name_1", "来访成交比");
			if (vrList.size() > 0) {
				// 来访成交比
				// map.put("data_21",
				// SysContent.get2Double(((double)(haveDealNum+readyToDealNum)/(double)vrList.size())*100)+"%");
				if (list88.size() > 0) {
					map.put("data_1", Double
							.parseDouble(SysContent.get2Double(((double) list88.size() / (double) ss11.size()) * 100)));
				} else {
					map.put("data_1", 0.00);
				}
			} else {
				map.put("data_1", 0.00);
			}
			if (haveDealNum > 0) {
				// 认购签约率
				map.put("data_3", Double
						.parseDouble(SysContent.get2Double(((double) haveDealNum / (double) crList.size()) * 100)));
			} else {
				map.put("data_3", 0.00);
			}
			
			
			
		} else {
			map.put("data_1", 0.00);
			map.put("data_2", 0.00);
			map.put("data_3", 0.00);
		}

		return map;
	}

	@Override
	public Map<String, Object> findSeeSaleScheduleInfo(String startTime, String endTime, User user, String chartDataId)
			throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		// 认购套数
		int allContractNum = 0;
		Set<Integer> st = new HashSet<>();
		// 已签约数
		int haveDealNum = 0;
		// 按揭到款套数
		int haveGetMoneyNum = 0;


		map.put("title", "销售进度观察");
		map.put("name_1", "认购套数占比");
		map.put("name_2", "签约套数占比");
		map.put("name_3", "首付套数占比");
		map.put("name_4", "按揭到款套数占比");
		map.put("length", 4);

		List<House> hList = baseDao.findByHql("from House where projectId = '" + user.getParentId() + "' ");
		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
				+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' ");
		List<ContractRecordsFlowRecord> crfrList = baseDao.findByHql("from ContractRecordsFlowRecord where projectId = '" + user.getParentId() + "' and operateTime >= '"
				+ startTimeStr + "' and operateTime <= '" + endTimeStr + "' ");
		Set<Integer> iset = new HashSet<>();
		
		if (crfrList.size() > 0) {
			for (ContractRecordsFlowRecord crfr : crfrList) {
				ContractRecords cr = (ContractRecords) baseDao.loadObject("from ContractRecords where recordNo = '"+crfr.getRecordNo()+"' ");
				iset.add(cr.getRecordNo());
				/*if (crfr.getOrderSort() == 2004) {
					if (crfr.getOrderSort() == 2005) {
						haveDealNum++;
					}else {
						if (cr.getAccountStyle() == 2) {
							haveGetMoneyNum++;
						}
					}
				}*/
			}
			for (Integer integer : iset) {
				ContractRecords cr = (ContractRecords) baseDao.loadObject("from ContractRecords where recordNo = '"+integer+"' ");
				st.add(cr.getHouseNum());
				
				if (cr.getRecordStatus() == 5) {
					haveDealNum++;
				}else if (cr.getRecordStatus() == 4){
					if (cr.getAccountStyle() == 2) {
						haveGetMoneyNum++;
					}
				}
			}
			
			if (st.size() > 0) {
				// 认购套数占比
				map.put("data_1", Double
						.parseDouble(SysContent.get2Double(((double) allContractNum / (double) st.size()) * 100)));
			} else {
				map.put("data_1", 0);
			}
			if (hList.size() > 0) {
				// 签约套数比
				map.put("data_2", Double
						.parseDouble(SysContent.get2Double(((double) haveDealNum / (double) hList.size()) * 100)));
				// 按揭到款套数比
				map.put("data_4", Double
						.parseDouble(SysContent.get2Double(((double) haveGetMoneyNum / (double) hList.size()) * 100)));
			} else {
				map.put("data_2", 0);
				map.put("data_4", 0);
			}
		} else {
			map.put("data_1", 0);
			map.put("data_2", 0);
			map.put("data_3", 0);
			map.put("data_4", 0);
		}

		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerGuideRecprdsToVisitInfo(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		// 报备客户总数
		int allGrNum = 0;
		// 报备到访数
		int grToGetVisitNum = 0;
		// 报备未到访数
		int grToGetNotVisitNum = 0;

		map.put("title", "备案到访情况");
		// map.put("name_1", "报备客户数");
		map.put("name_1", "备案到访客户数");
		map.put("name_2", "备案未到访客户数");
		// map.put("name_4", "报备到访率");
		// map.put("name_5", "外场导客占比");
		map.put("length", 2);

		List<GuideRecords> grList = baseDao.findByHql("from GuideRecords where applyTime >= '" + startTimeStr
				+ "' and applyTime <= '" + endTimeStr + "' and projectId = '" + user.getParentId() + "' and (applyStatus = 0 or applyStatus = 1)");
		if (grList.size() > 0) {
			for (GuideRecords gr : grList) {
				allGrNum++;
				if (gr.getVisitNo() != null && !"".equals(gr.getVisitNo())) {
					grToGetVisitNum++;
				} else {
					grToGetNotVisitNum++;
				}
			}
			map.put("data_1", grToGetVisitNum);
			map.put("data_2", grToGetNotVisitNum);
			// map.put("data_3", grToGetNotVisitNum);
			// map.put("data_4", grToGetVisitNum/allGrNum);
		} else {
			map.put("data_1", 0);
			map.put("data_2", 0);
			// map.put("data_3", 0);
			// map.put("data_4", 0);
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> findAllUserByPage(String status, int page, int count) {
		int startNum = (page - 1) * count;
		List<Map<String, Object>> userList = null;
		if (status.equals("")) { // 所有的用户账户，不包括申请状态和删除状态。
			String hql = "from User where userStatus != 0 and userStatus != 2";
			userList = getUserMapByPage(hql, startNum, count);
		} else if (status.equals("1")) { // 正常的账号
			String hql = "from User where userStatus = 1";
			userList = getUserMapByPage(hql, startNum, count);
		} else if (status.equals("2")) { // 已经被禁用的账户
			String hql = "from User where userStatus = 3";
			userList = getUserMapByPage(hql, startNum, count);
		}
		return userList;
	}

	@Override
	public void addManaerChartData(ManaerChartData mcd, User user, MultipartFile pic) throws Exception {

		mcd.setChartDataId(SysContent.uuid());
		if (!pic.isEmpty() && pic.getSize() > 0) {
			String rename = SysContent.getFileRename(pic.getOriginalFilename());
			// 设置凭证url
			String savePath = PicUploadToYun.upload(rename, pic, SmsContext.CR_PIC);
			mcd.setIconUrl(savePath);
		}
		baseDao.saveOrUpdate(mcd);

	}

	@Override
	public List<ManaerChartData> findManaerChartDataList(User user, String isUseful) {

		String hql = "from ManaerChartData ";
		if (isUseful != null && !"".equals(isUseful)) {
			hql += " where isUseful = '" + isUseful + "' ";
		}
		List<ManaerChartData> mcdList2 = new ArrayList<>();
		List<ManaerChartData> mcdList = baseDao.findByHql(hql);
		for (ManaerChartData md : mcdList) {
			if (md.getRelationList() != null && !"".equals(md.getRelationList())) {
				String relationListStr = "";
				String[] relationListStrs = md.getRelationList().split(",");
				for (String string : relationListStrs) {
					ManaerChartData mcd = (ManaerChartData) baseDao
							.loadObject("from ManaerChartData where chartDataId = '" + string.trim() + "' ");
					if (mcd != null && !"".equals(mcd)) {
						relationListStr += mcd.getButtonText() + ",";
					}
				}
				md.setRelationListStr(relationListStr);
			}
			mcdList2.add(md);
		}
		return mcdList2;
	}

	@Override
	public void updateManaerChartData(String chartDataId) {
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if ("0".equals(mcd.getIsUseful())) {
				mcd.setIsUseful("1");
			} else if ("1".equals(mcd.getIsUseful())) {
				mcd.setIsUseful("0");
			}
			baseDao.saveOrUpdate(mcd);
		}

	}

	@Override
	public List<Project> findProjectIdList(User user) {

		List<Project> proList = baseDao.findByHql("from Project");

		return proList;
	}

	@Override
	public void addAnalysisCategory(String categoryName, String chartDataIdArr, String projectId, String priority,
			String isMade, User user) {
		AnalysisCategory ac = new AnalysisCategory();
		if (categoryName != null && !"".equals(categoryName)) {
			ac.setCategoryName(categoryName);
		}
		if (chartDataIdArr != null && !"".equals(chartDataIdArr)) {
			ac.setHaveLabel(chartDataIdArr);
		}
		if (projectId != null && !"".equals(projectId)) {
			ac.setProjectId(projectId);
		}
		if (isMade != null && !"".equals(isMade)) {
			if ("on".equals(isMade)) {
				ac.setIsMade("1");
			} else {
				ac.setIsMade("0");
			}
		} else {
			ac.setIsMade("0");
		}
		if (priority != null && !"".equals(priority)) {
			ac.setPriority(priority);
		}
		baseDao.saveOrUpdate(ac);
	}

	private List<Map<String, Object>> getUserMapByPage(String hql, int start, int count) {

		List<Map<String, Object>> listMap = new ArrayList<>();
		List<User> list = baseDao.findByHql(hql, start, count);
		boolean flag = true;
		for (User u : list) {
			// 查找当前用户的角色
			Set<Role> rSet = u.getRoleId();
			// 防止数据库乱数据
			if (rSet != null && rSet.size() > 0) {
				flag = true;
			} else {
				flag = false;
			}
			Map<String, Object> map = new HashMap<>();
			// 查找该用户所属上级
			Shops shop = null;
			if (u.getParentId() != null && !u.getParentId().equals("")) {
				Project project = (Project) baseDao.loadById(Project.class, u.getParentId());
				if (project != null) {
					map.put("contactPerson", project.getProjectName());
				} else {
					String shopId = u.getParentId();
					if(shopId.matches("^[0-9]*$")){
						shop = (Shops) baseDao.loadById(Shops.class, Integer.parseInt(shopId));
						if (shop != null) {
							map.put("contactPerson", shop.getContactPerson());
						}
					}
				}
			} else {
				map.put("contactPerson", "");
			}
			map.put("userCaption", u.getUserCaption());
			map.put("phone", u.getPhone());
			map.put("createTime", u.getCreateTime());
			map.put("userStatus", u.getUserStatus());
			map.put("userId", u.getUserId());
			for (Role r : rSet) {
				map.put("roleId", r.getRoleId());
			}
			if (flag) {
				listMap.add(map);
			}
		}

		return listMap;
	}

	@Override
	public int findAllUserNum(String status) {
		int countQuery = 0;
		if (status.equals("")) { // 所有的用户账户，不包括申请状态和删除状态。
			String hql = "select count(*) from User where userStatus != 0 and userStatus != 2";
			countQuery = baseDao.countQuery(hql);
		} else if (status.equals("1")) { // 正常的账号
			String hql = "select count(*) from User where userStatus = 1";
			countQuery = baseDao.countQuery(hql);
		} else if (status.equals("2")) { // 已经被禁用的账户
			String hql = "select count(*) from User where userStatus = 3";
			countQuery = baseDao.countQuery(hql);
		}
		return countQuery;
	}

	@Override
	public Map<String, Object> findSeeOutCustomerDealInfo(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		// 带客成交数
		int guideToDealNum = 0;
		// 外场成交数
		int outFieldToDealNum = 0;
		// 内场成交数
		int intFiledToDealNum = 0;

		map.put("title", "外场成交分析");
		map.put("name_1", "带客成交");
		map.put("name_2", "外场成交");
		// map.put("name_3", "外场成交占比");
		map.put("name_3", "内场成交数");
		map.put("length", 3);

		List<String> sList = new ArrayList<>();

		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId = '" + user.getParentId()
				+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' ");
		List<ContractRecordsFlowRecord> crfrList = baseDao.findByHql("from ContractRecordsFlowRecord where projectId = '" + user.getParentId() + "' and operateTime >= '"
				+ startTimeStr + "' and operateTime <= '" + endTimeStr + "' ");
		if (crfrList.size()>0){
			for (ContractRecordsFlowRecord cr : crfrList) {
				if (cr.getOrderSort() == 2005){
					ContractRecords crNew = (ContractRecords) baseDao.loadObject("from ContractRecords where recordNo = '"+cr.getRecordNo()+"' ");
					String newUserId = crNew.getUserId();
					User u = (User) baseDao.loadObject("from User where userId = '" + newUserId + "' ");
					Set<Role> sr = u.getRoleId();
					for (Role role : sr) {
						if (role.getRoleId() == 1 || role.getRoleId() == 2) {
							outFieldToDealNum++;
						}
						if (role.getRoleId() == 3) {
							sList.add(crNew.getCustomerPhone());
							intFiledToDealNum++;
						}
					}
				}
			}
			
			map.put("data_2", outFieldToDealNum);
			map.put("data_3", intFiledToDealNum);
			for (String string : sList) {
				List<GuideRecords> grList2 = baseDao.findByHql("from GuideRecords where applyTime >= '"
						+ startTimeStr + "' and applyTime <= '" + endTimeStr + "' and projectId = '"
						+ user.getParentId() + "' and customerPhone = '" + string + "' ");
				if (grList2.size() > 0) {
					guideToDealNum++;
				}
			}
			map.put("data_1", guideToDealNum);
		}else {
			map.put("data_1", 0);
			map.put("data_2", 0);
			map.put("data_3", 0);
		}
		/*if (crList.size() > 0) {
			for (ContractRecords cr : crList) {
				if (cr.getRecordStatus() == 5) {
					String userId = cr.getUserId();
					User u = (User) baseDao.loadObject("from User where userId = '" + cr.getUserId() + "' ");
					Set<Role> sr = u.getRoleId();
					for (Role role : sr) {
						if (role.getRoleId() == 1 || role.getRoleId() == 2) {
							outFieldToDealNum++;
						}
						if (role.getRoleId() == 3) {
							sList.add(cr.getCustomerPhone());
							intFiledToDealNum++;
						}
					}
				}
			}
			for (String string : sList) {
				List<GuideRecords> grList2 = baseDao.findByHql("from GuideRecords where applyTime >= '" + startTimeStr
						+ "' and applyTime <= '" + endTimeStr + "' and projectId = '" + user.getParentId()
						+ "' and customerPhone = '" + string + "' ");
				if (grList2.size() > 0) {
					guideToDealNum++;
				}
			}
			map.put("data_1", guideToDealNum);
			map.put("data_2", outFieldToDealNum);
			// map.put("data_3", outFieldToDealNum/crList.size());
			map.put("data_3", intFiledToDealNum);
		} else {
			map.put("data_1", 0);
			map.put("data_2", 0);
			map.put("data_3", 0);
		}*/
		return map;
	}

	@Override
	public Map<String, Object> findSeeOldCustomerVisitInfo(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		// 确认老客户接访次数
		int affirmOldCustomerVisitNum = 0;

		map.put("title", "观察老客户接访");
		map.put("name_1", "确认老客户接访次数");
		// map.put("name_2", "老客户接访次数占比");
		map.put("length", 1);

		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
				+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' and phone != '' ");
		Set<String> oldSet = new HashSet<>();
		if (vrList.size() > 0) {
			
			for (VisitRecords visitRecords : vrList) {
				oldSet.add(visitRecords.getPhone());
				/*if (visitRecords.getPhone()!= null && !"".equals(visitRecords.getPhone())){
					
					ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject("from ProjectCustomers where projectCustomerPhone = '"+visitRecords.getPhone()+"' and projectId = '" + user.getParentId()+"' ");
					if (pc != null && !"".equals(pc)){
						List<VisitRecords> vrList33 = baseDao.findByHql(
								"from VisitRecords where projectId = '" + user.getParentId() + "' and arriveTime < '"
										+ startTimeStr + "' and phone = '" + visitRecords.getPhone() + "' and phone is not null and phone != '' ");
						if (vrList33.size() > 0) {
							affirmOldCustomerVisitNum++;
						}else {
							List<VisitRecords> vrList333 = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId() + "' and arriveTime  >='"
									+ startTimeStr + "' and phone = '" + visitRecords.getPhone() + "' and phone is not null and arriveTime <= '" + endTimeStr + "' and phone != '' ");
							if (vrList333.size()>1){
								affirmOldCustomerVisitNum++;
							}
						}
					}
				}*/
			}
			if (oldSet.size()>0){
				for (String string : oldSet) {
						ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject("from ProjectCustomers where projectCustomerPhone = '"+string+"' and projectId = '" + user.getParentId()+"' ");
						if (pc != null && !"".equals(pc)){
							List<VisitRecords> vrList33 = baseDao.findByHql(
									"from VisitRecords where projectId = '" + user.getParentId() + "' and arriveTime < '"
											+ startTimeStr + "' and phone = '" + string + "' and phone is not null and phone != '' ");
							if (vrList33.size() > 0) {
								affirmOldCustomerVisitNum++;
							}else {
								List<VisitRecords> vrList333 = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId() + "' and arriveTime  >='"
										+ startTimeStr + "' and phone = '" + string + "' and phone is not null and arriveTime <= '" + endTimeStr + "' and phone != '' ");
								if (vrList333.size()>1){
									affirmOldCustomerVisitNum += (vrList333.size()-1);
								}
							}
					}
				}
			}
			map.put("data_1", affirmOldCustomerVisitNum);
			// 老客户解放次数占比
			// map.put("data_2",
			// SysContent.get2Double(((double)affirmOldCustomerVisitNum/(double)vrList.size())*100)+"%");
		} else {
			map.put("data_1", 0);
		}

		return map;
	}

	@Override
	public Map<String, Object> findReserveCustomerInfo(String startTime, String endTime, User user, String chartDataId)
			throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		map.put("title", "观察储客分析");
		map.put("name_1", "新增储客数");
		map.put("name_2", "新增二次来访客户数");
		map.put("name_3", "老客户数");
		//map.put("name_4", "客户回头率");
		map.put("length", 3);

		// 新增2次到访
		int newAddTwiceVisitNum = 0;
		// 客户回头数
		int customerReturnBackVisitNum = 0;

		Set<String> ss = new HashSet<>();
		Set<String> ss2 = new HashSet<>();
		Set<String> ss3 = new HashSet<>();

		// 该时间段到访的人数
		List<VisitRecords> vrList22 = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
				+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");
		if (vrList22.size() > 0) {
			for (VisitRecords visitRecords : vrList22) {
				if (visitRecords.getWriteState() != null && !"".equals(visitRecords.getWriteState()) && visitRecords.getWriteState() == 1) {
					ss3.add(visitRecords.getPhone());
				}
			}
			if (ss3.size() > 0) {
				for (String string : ss3) {
					List<VisitRecords> vrList55 = baseDao.findByHql("from VisitRecords where projectId = '"
							+ user.getParentId() + "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '"
							+ endTimeStr + "' and phone = '" + string + "' ");
					if (vrList55.size() >= 2) {
						customerReturnBackVisitNum++;
					} else if (vrList55.size() == 1) {
						List<VisitRecords> vrList66 = baseDao
								.findByHql("from VisitRecords where projectId = '" + user.getParentId()
										+ "' and arriveTime < '" + startTimeStr + "' and phone = '" + string + "' ");
						if (vrList66.size() == 1) {
							customerReturnBackVisitNum++;
						}
					}

					List<VisitRecords> vrList33 = baseDao
							.findByHql("from VisitRecords where projectId = '" + user.getParentId()
									+ "' and arriveTime < '" + startTimeStr + "' and phone = '" + string + "' ");
					if (vrList33.size() > 0) {
						ss.add(string);
						if (vrList33.size() == 1) {
							List<VisitRecords> vrList44 = baseDao.findByHql("from VisitRecords where projectId = '"
									+ user.getParentId() + "' and arriveTime >= '" + startTimeStr
									+ "' and arriveTime <= '" + endTimeStr + "' and phone = '" + string + "' ");
							if (vrList44.size() == 1) {
								newAddTwiceVisitNum++;
							}
						}
					} else {
						ss2.add(string);
						List<VisitRecords> vrList44 = baseDao.findByHql("from VisitRecords where projectId = '"
								+ user.getParentId() + "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '"
								+ endTimeStr + "' and phone = '" + string + "' ");
						if (vrList44.size() == 2) {
							newAddTwiceVisitNum++;
						}
					}
				}
				List<ProjectCustomers> pcList = baseDao.findByHql("from ProjectCustomers where projectId = '"
						+ user.getParentId() + "' and lastTime <= '" + endTimeStr + "' ");
//				if (pcList.size() > 0) {
//					if (customerReturnBackVisitNum > 0) {
//						// 客户回头率
//						map.put("data_4", Double.parseDouble(SysContent
//								.get2Double(((double) customerReturnBackVisitNum / (double) pcList.size()) * 100)));
//					} else {
//						map.put("data_4", 0);
//					}
//				} else {
//					map.put("data_4", 0);
//				}
			}
			if (newAddTwiceVisitNum > 0) {
				// 新增二次来访客户数
				map.put("data_2", newAddTwiceVisitNum);
			} else {
				map.put("data_2", 0);
			}

			if (ss2.size() > 0) {
				// 新增总储客数
				map.put("data_1", ss2.size());
			} else {
				map.put("data_1", 0);
			}
			if (ss.size() > 0) {
				// 老客户数
				map.put("data_3", ss.size());
			} else {
				map.put("data_3", 0);
			}
		}


		return map;
	}

	@Override
	public List<AnalysisCategory> findAnalysisCategoryList(User user) {
		List<AnalysisCategory> acList2 = new ArrayList<>();
		List<AnalysisCategory> acList = baseDao.findByHql("from AnalysisCategory ");
		for (AnalysisCategory ac : acList) {
			if (ac.getHaveLabel() != null && !"".equals(ac.getHaveLabel())) {
				String haveLabel = "";
				String[] haveLabels = ac.getHaveLabel().split(",");
				for (String string : haveLabels) {
					ManaerChartData mcd = (ManaerChartData) baseDao
							.loadObject("from ManaerChartData where chartDataId = '" + string.trim() + "' ");
					if (mcd != null && !"".equals(mcd)) {
						haveLabel += mcd.getButtonText() + ",";
					}
				}
				ac.setHaveLabels(haveLabel);
			}
			acList2.add(ac);
		}
		return acList2;
	}

	@Override
	public Map<String, Object> findAllCustomerContractRecordInfo(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		Set<String> ss10 = new HashSet<>();

		// 老客户认购数
		int oldCustomerReadyToBuyNum = 0;
		// 新客户认购数
		int newCustomerReadyToBuyNum = 0;

		map.put("title", "观察老客户接访");
		map.put("name_1", "总认购客户数");
		map.put("name_2", "老客户认购数");
		map.put("name_3", "新客户认购数");
		map.put("length", 3);

		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId = '" + user.getParentId()
				+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' ");
		if (crList.size() > 0) {
			for (ContractRecords cr : crList) {
				ss10.add(cr.getCustomerPhone());
			}
			// 总认购客户数
			if (ss10.size() > 0) {
				for (String string : ss10) {
					List<VisitRecords> vrList88 = baseDao
							.findByHql("from VisitRecords where projectId = '" + user.getParentId()
									+ "' and arriveTime < '" + startTimeStr + "' and phone = '" + string + "' ");
					if (vrList88.size() > 0) {
						// 老客户认购数
						List<ContractRecords> OldRecNum = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
						+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' and customerPhone = '"+ string + "' ");
						if (OldRecNum.size()>0){
							oldCustomerReadyToBuyNum += OldRecNum.size();
						}
						//之前时间段没有来过
					} else {
						List<VisitRecords> vrList8888 = baseDao
								.findByHql("from VisitRecords where projectId = '" + user.getParentId()
										+ "' and arriveTime >= '" + startTimeStr + "' and phone = '" + string + "' and arriveTime <= '"+endTimeStr+"' ");
						// 新客户认购数________改时间段内来一次
						if (vrList8888.size() == 1){
							List<ContractRecords> newRecNum = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
							+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' and customerPhone = '"+ string + "' ");
							if (newRecNum.size()>0) {
								newCustomerReadyToBuyNum += newRecNum.size();
							}
						}else {
							// 老客户认购数
							List<ContractRecords> OldRecNum2 = baseDao.findByHql("from ContractRecords where projectId ='" + user.getParentId()
							+ "' and applyTime >= '" + startTimeStr + "' and applyTime <= '" + endTimeStr + "' and customerPhone = '"+ string + "' ");
							if (OldRecNum2.size()>0){
								oldCustomerReadyToBuyNum += OldRecNum2.size();
							}
						}
					}
				}
				
				if (oldCustomerReadyToBuyNum > 0) {
					map.put("data_2", oldCustomerReadyToBuyNum);
				} else {
					map.put("data_2", 0);
				}
				if (newCustomerReadyToBuyNum > 0) {
					map.put("data_3", newCustomerReadyToBuyNum);
				} else {
					map.put("data_3", 0);
				}
			} else {
				map.put("data_1", 0);
				map.put("data_2", 0);
				map.put("data_3", 0);
			}
		}

		return map;
	}

	@Override
	public void updateAnalysisCategory(Integer categoryId, String categoryName, String haveLabel, String projectId,
			String priority, String isMade) {

		AnalysisCategory ac = (AnalysisCategory) baseDao
				.loadObject("from AnalysisCategory where categoryId = '" + categoryId + "' ");
		if (ac != null && !"".equals(ac)) {
			if (categoryName != null && !"".equals(categoryName)) {
				ac.setCategoryName(categoryName);
			}
			if (haveLabel != null && !"".equals(haveLabel)) {
				ac.setHaveLabel(haveLabel);
			}
			if (projectId != null && !"".equals(projectId)) {
				ac.setProjectId(projectId);
			}
			if (isMade != null && !"".equals(isMade)) {
				if ("on".equals(isMade)) {
					ac.setIsMade("1");
				} else {
					ac.setIsMade("0");
				}
			}
			if (priority != null && !"".equals(priority)) {
				ac.setPriority(priority);
			}
			baseDao.saveOrUpdate(ac);
		}
	}

	@Override
	public void updateManaerChartData(ManaerChartData mcd, MultipartFile pic) throws Exception {

		ManaerChartData managercd = (ManaerChartData) baseDao
				.loadObject("from ManaerChartData where chartDataId = '" + mcd.getChartDataId() + "' ");
		if (managercd != null && !"".equals(managercd)) {
			if (mcd.getButtonText() != null && !"".equals(mcd.getButtonText())) {
				managercd.setButtonText(mcd.getButtonText());
			}
			if (mcd.getTitleName() != null && !"".equals(mcd.getTitleName())) {
				managercd.setTitleName(mcd.getTitleName());
			}
			if (mcd.getDescText() != null && !"".equals(mcd.getDescText())) {
				managercd.setDescText(mcd.getDescText());
			}
			if (mcd.getChartName() != null && !"".equals(mcd.getChartName())) {
				managercd.setChartName(mcd.getChartName());
			}
			if (mcd.getBackgroundMethod() != null && !"".equals(mcd.getBackgroundMethod())) {
				managercd.setBackgroundMethod(mcd.getBackgroundMethod());
			}
			// if (mcd.getRelationList() != null &&
			// !"".equals(mcd.getRelationList())) {
			managercd.setRelationList(mcd.getRelationList());
			// }
			if (mcd.getReturnAddress() != null && !"".equals(mcd.getReturnAddress())) {
				managercd.setReturnAddress(mcd.getReturnAddress());
			}
			if (mcd.getScenario() != null && !"".equals(mcd.getScenario())) {
				managercd.setScenario(mcd.getScenario());
			}
			if (mcd.getTrendPhoto() != null && !"".equals(mcd.getTrendPhoto())) {
				managercd.setTrendPhoto(mcd.getTrendPhoto());
			}
			if (mcd.getIsUseful() != null && !"".equals(mcd.getIsUseful())) {
				managercd.setIsUseful(mcd.getIsUseful());
			}
			if (!pic.isEmpty() && pic.getSize() > 0) {
				String rename = SysContent.getFileRename(pic.getOriginalFilename());
				// 设置凭证url
				String savePath = PicUploadToYun.upload(rename, pic, SmsContext.CR_PIC);
				managercd.setIconUrl(savePath);
			}
			baseDao.saveOrUpdate(managercd);
		}
	}

	@Override
	public List<AnalyzeTagsDTO> findMcdList(User user) {

		List<AnalyzeTagsDTO> atList = new ArrayList<>();
		List<AnalysisCategory> acList3 = new ArrayList<>();
		List<AnalysisCategory> acList = baseDao
				.findByHql("from AnalysisCategory where isMade = 0 order by priority DESC");
		List<AnalysisCategory> acList2 = baseDao.findByHql("from AnalysisCategory where isMade = 1 and projectId = '"
				+ user.getParentId() + "' order by priority DESC");
		if (acList2.size() > 0) {
			for (AnalysisCategory ac : acList2) {
				acList3.add(ac);
			}
		}
		if (acList.size() > 0) {
			for (AnalysisCategory analysisCategory : acList) {
				acList3.add(analysisCategory);
			}
		}
		if (acList3.size() > 0) {
			for (AnalysisCategory ac : acList3) {
				AnalyzeTagsDTO atdto = new AnalyzeTagsDTO();
				atdto.setCategoryId(ac.getCategoryId());
				atdto.setCategoryName(ac.getCategoryName());
				atdto.setProjectId(ac.getProjectId());
				if (ac.getHaveLabel() != null && ac.getHaveLabel().length() > 0) {
					String[] haveLabels = ac.getHaveLabel().split(",");
					if (haveLabels.length > 0) {
						List<ManaerChartData> mcdList = new ArrayList<>();
						for (String string : haveLabels) {
							ManaerChartData mcd = (ManaerChartData) baseDao
									.loadObject("from ManaerChartData where chartDataId = '" + string + "' ");
							if (mcd != null && !"".equals(mcd)) {
								mcdList.add(mcd);
							}
						}
						atdto.setMcdList(mcdList);
					}
				}
				atList.add(atdto);
			}
		}

		return atList;
	}

	@Override
	public ManaerChartData findMCd(String chartDataId) {

		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				return mcd;
			}
		}
		return null;
	}

	@Override
	public ManaerChartData findManaerChartById(String chartDataId) {
		ManaerChartData mcd = (ManaerChartData) baseDao
				.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
		return mcd;
	}

	@Override
	public List<ManaerChartData> findRelationFenXiInfo(String chartDataId) {

		List<ManaerChartData> mcdList = new ArrayList<>();
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd.getRelationList() != null && !"".equals(mcd.getRelationList())) {
				String[] str = mcd.getRelationList().split(",");
				for (String string : str) {
					ManaerChartData mcd2 = (ManaerChartData) baseDao
							.loadObject("from ManaerChartData where chartDataId = '" + string.trim() + "' ");
					mcdList.add(mcd2);
				}
			}
		}
		return mcdList;
	}

	@Override
	public String toSentMessageToChangePsd(User user) throws IOException {

		String verificationCode = SysContent.getVerificationCode();
		// 【九邑房源在线】正在进行修改密码操作，您的验证码是#code#，如非本人操作，请忽略此短信。
		String text = SmsContext.ChangePsd.replace("#code#", verificationCode);
		JavaSmsApi.sendSms(text, user.getPhone());

		return verificationCode;
	}

	@Override
	public Map<String, Object> updatePasswordNew(User user, String newVerification, String password,
			String truepassword, String verificationCode) {

		Map<String, Object> map = new HashMap<>();
		if (newVerification != null && !"".equals(newVerification)) {
			if (newVerification.equals(verificationCode)) {
				if (password != null && !"".equals(password) && truepassword != null && !"".equals(truepassword)) {
					if (password.equals(truepassword)) {
						user.setPassword(SysContent.md5(truepassword));
						baseDao.saveOrUpdate(user);
						map.put("returnCode", 200);
						map.put("msg", "修改成功");
					} else {
						map.put("returnCode", 201);
						map.put("msg", "新设置密码未统一");
					}
				} else {
					map.put("returnCode", 400);
					map.put("msg", "不能未空");
				}
			} else {
				map.put("returnCode", 401);
				map.put("msg", "验证码错误");
			}
		} else {
			map.put("returnCode", 400);
			map.put("msg", "不能未空");
		}
		return map;
	}

	// 获取案场助理
	@Override
	public Map findEngineerByProId(String proId) {
		Map map = new HashMap<>();
		String egSql = "SELECT * from t_users u,t_role r ,user_role ur "
				+ "WHERE u.userId = ur.u_id and r.roleId=4 and u.parentId = '" + proId + "'";
		List<User> uList = baseDao.queryBySql(egSql, User.class);
		if (!uList.isEmpty()) {
			User eg = uList.get(0);
			map.put("phone", eg.getPhone());
			map.put("name", eg.getUserCaption());
			map.put("isHave", 1);
			return map;
		} else {
			map.put("isHave", 0);
		}
		return map;
	}

	// 删除导入数据
	@Override
	public Map dropImportDataByTime(String start, String end, String proId) throws Exception {
		Map map = new HashMap();
		// ① 删除导入的置业顾问（1970-01-01 00:00:00）
		// String agentHql = "from User where createTime =
		// '"+SmsContext.SYSTEM_IMPORT_AGENT_TIME+"'";
		// List<User> agentList = baseDao.findByHql(agentHql);
		String agentSql = "SELECT * FROM t_users WHERE createTime = '" + SmsContext.SYSTEM_IMPORT_AGENT_TIME + "'";
		List<User> agentList = baseDao.queryBySql(agentSql, User.class);
		for (User u : agentList) {
			int deleteFlag = 0;
			Set<Role> rId = u.getRoleId();
			for (Role r : rId) {
				String URsql = "delete from user_role where u_id = '" + u.getUserId() + "' and r_id = '" + r.getRoleId()
						+ "'";
				deleteFlag = baseDao.excuteBySql(URsql);
			}
			if (deleteFlag > 0) {
				baseDao.delete(u);
			}
		}
		// ② 删除带看记录即这个时候新增的门店客户
		long startTime = DateUtil.parse(start).getTime();
		long endTime = DateUtil.parse(end).getTime();
		long systemTime = DateUtil.parse(SmsContext.SYSTEM_IMPORT_AGENT_TIME).getTime();
		String grHql = "from GuideRecords where applyTime >= '" + start + "' and applyTime <= '" + end + "' and projectId = '"+proId+"'";
		List<GuideRecords> grList = baseDao.findByHql(grHql);
		for (GuideRecords gr : grList) {
			// 先删除带看的客户
			ShopCustomers sc = (ShopCustomers) baseDao.loadById(ShopCustomers.class, gr.getShopCustomerId());
			if(sc!=null){
				long sct = DateUtil.parse(sc.getCreateTime()).getTime();
				if (sct == systemTime) {
					baseDao.delete(sc);
				}
			}
			baseDao.delete(gr);
		}
		// ③ 删除到访记录及这个时候新增的案场客户
		String vrHql = "from VisitRecords where arriveTime >= '" + start + "' and arriveTime <= '" + end + "' and  projectId = '"+proId+"'";
		List<VisitRecords> vrList = baseDao.findByHql(vrHql);
		for (VisitRecords vr : vrList) {
			// 先删除案场客户
			if (vr.getCustomerId() != null && !vr.getCustomerId().equals("")) {
				ProjectCustomers pc = (ProjectCustomers) baseDao.loadById(ProjectCustomers.class, vr.getCustomerId());
				if (pc != null) {
					long pct = DateUtil.parse(pc.getCreateTime()).getTime();
					if (pct >= startTime && pct <= endTime) {
						baseDao.delete(pc);
					}
				}
			}
			baseDao.delete(vr);
		}
		map.put("msg", "删除成功");
		return map;
	}

	/************************************************** 客户端数据模拟 ********************************************************/

	//添加置业顾问
	@Override
	public Map addOrUpdateUserFromClient(User user, String roleSign,String shopName) {
		//System.out.println("insert");
		//System.out.println("start"+DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss:SSS"));
		Map map = new HashMap<>();
		Set<Role> roleId = new HashSet<>();
		String shopHql = "SELECT * FROM t_shops WHERE shopName = '"+shopName+"'";
		String rsql = "SELECT * from t_role where 1=1 ";
		String sql = "SELECT * from t_users where phone = '" + user.getPhone() + "' LIMIT 1";
		User aginUser = (User) baseDao.queryObjBySql(sql, User.class);
		boolean isExist = false;
		if (aginUser != null) {// 修改
			isExist = true;
			Set<Role> role = aginUser.getRoleId();
			if (!role.isEmpty()) {
				for (Role r : role) {
					aginUser.getRoleId().remove(r);
					break;
				}
			}
		} else{ // 新增
			aginUser = new User();
			aginUser.setUserId(SysContent.uuid());
			aginUser.setCreateTime(DateTime.toString1(new Date()));
		}
		// 获取分配的权限
		if (roleSign.equals("medi")) {
			rsql += " and roleId = 1 LIMIT 1";
			Shops shop = (Shops) baseDao.queryObjBySql(shopHql, Shops.class);
			if(shop!=null){
				aginUser.setParentId(shop.getShopId().toString());
			}else{
				map.put("code", 202);
				map.put("msg", "门店不存在");
				if(isExist){
					baseDao.deleteById(User.class, aginUser.getUserId());
				}
				//System.out.println("end"+DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss:SSS"));
				return map;
			}
		}
		if (roleSign.equals("shopowner")) {
			rsql += " and roleId = 2 LIMIT 1";
			Shops shop = (Shops) baseDao.queryObjBySql(shopHql, Shops.class);
			if(shop!=null){
				aginUser.setParentId(shop.getShopId().toString());
			}else{
				map.put("code", 202);
				map.put("msg", "门店不存在");
				if(isExist){
					baseDao.deleteById(User.class, aginUser.getUserId());
				}
				//System.out.println("end"+DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss:SSS"));
				return map;
			}
		} else if (roleSign.equals("agent")) {
			rsql += " and roleId = 3 LIMIT 1";
			aginUser.setParentId(user.getParentId());
		} else if (roleSign.equals("engineer")) {
			rsql += " and roleId = 4 LIMIT 1";
			aginUser.setParentId(user.getParentId());
		} else if (roleSign.equals("director")) {
			rsql += " and roleId = 7 LIMIT 1";
			aginUser.setParentId(user.getParentId());
		}
		Role r = (Role) baseDao.queryObjBySql(rsql, Role.class);
		roleId.add(r);
		aginUser.setRoleId(roleId);
		aginUser.setUserCaption(user.getUserCaption());
		aginUser.setPhone(user.getPhone());
		aginUser.setPassword(SysContent.md5(user.getPhone().substring(user.getPhone().length() - 6)));
		aginUser.setIdCard(user.getIdCard());
		aginUser.setUserStatus(1);
		
		// 持久化user对象
		baseDao.saveOrUpdate(aginUser);
		map.put("code", 200);
		map.put("msg", "添加成功");
		//System.out.println("end"+DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss:SSS"));
		return map;
	}

	// 通过用户名获取用户
	@Override
	public User findUserByName(String shopAgentName,String agentPhone,String proId) {
		String hql = "from User where userCaption = '" + shopAgentName + "' and phone = '"+agentPhone+"'";
		if(proId!=null){
			hql += " and parentId = '"+proId+"'";
		}
		User user = (User) baseDao.loadObject(hql);
		return user;
	}

	@Override
	public String[] findjingweiduByIp(User user, HttpServletRequest request) throws Exception {

		String ip = CusAccessObjectUtil.getIpAddress(request);
		if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)){
			ip = "125.120.210.76";
		}
		// ip = "220.181.16.0";
		String[] str = (String[]) GetIPXY.getIPXY(ip);
		return str;
	}

	//通过手机号和所属案场查询用户
	@Override
	public User findUserByProperty(String agentPhone, String proId) {
		User user = (User) baseDao.loadObject("from User where phone = '" + agentPhone + "' and parentId = '"+proId+"'");
		return user;
	}

	@Override
	public void addOrUpdateUser(User user, Integer group) {
		if(user!=null && !user.equals("")){
			User u = (User) baseDao.loadById(User.class, user.getUserId());
			u.setGroupName(group.toString());
			baseDao.saveOrUpdate(u);
			//判断分组的顾问是否在签到排队列表中，若在更新其分组，并更新排队顺序
			String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			String arHql = "from AgentRank where userId = '"+user.getUserId()+"' and signInTime like '"+todayStr+"%'";
			AgentRank ar = (AgentRank) baseDao.loadObject(arHql);
			if(ar!=null){
				ar.setGroupNum(group);
				//获取排队队列中第一个排队的顾问
				String rankAllHql = "from AgentRank where 1=1 ORDER BY rankNumber ASC";
				String rankFirstHql = "from AgentRank where 1=1 and (signInVisitStatus=0 or signInVisitStatus=2) ORDER BY rankNumber ASC";
				List<AgentRank> rankAll = baseDao.findByHql(rankAllHql);
				AgentRank rankFirst = (AgentRank) baseDao.loadObject(rankFirstHql);
				List<AgentRank> results = new ArrayList<>();
		        List<AgentRank> teamA = new ArrayList<>();
		        List<AgentRank> teamB = new ArrayList<>();
				//排序重组
				for (AgentRank ad : rankAll) {
					if (ad.getGroupNum() != null && !ad.getGroupNum().equals("")
							&& ad.getGroupNum().equals(2)) {
						teamB.add(ad);
					} else {
						teamA.add(ad);
					}
				}
				//标识下一个排队的顾问是哪个组的
				boolean lastIsTeamA = false;
				if(rankFirst.getGroupNum()!=null && !rankFirst.equals("") && rankFirst.getGroupNum().equals(1)){//排位第一的顾问是那组就从该组开始（第一个的是第一组的从第一组开始排起）
					lastIsTeamA = true;
				}
				//组装排序集合
				while (teamA.size() > 0 || teamB.size() > 0) {
					if (lastIsTeamA) {
						if (teamA.size() > 0) {
							results.add(teamA.get(0));
							teamA.remove(0);
						}
					} else {
						if (teamB.size() > 0) {
							results.add(teamB.get(0));
							teamB.remove(0);
						}
					}
					lastIsTeamA = !lastIsTeamA;
				}
				//更新排序
				Integer rankNum = rankFirst.getRankNumber();
				baseDao.delete(ar);
				while(!results.isEmpty()){
					AgentRank rAr = results.get(0);
					rAr.setRankNumber(rankNum);
					baseDao.save(rAr);
					rankNum += 1;
					results.remove(0);
				}
			}
		}
	}

	@Override
	public List<User> findProjectAgentByLimit(Integer num, String projectId) {
		List<User> users = new ArrayList<>();
		if(num!=null && num.intValue()!=0){//查询指定条数
			users = baseDao.queryDTOBySql("SELECT u.userId,u.userCaption FROM t_users AS u RIGHT JOIN (SELECT * FROM user_role WHERE r_id = 3) AS r ON u.userId = r.u_id WHERE u.parentId='"+projectId+"' AND u.userStatus=1 LIMIT 0,"+num, 
					User.class,new String[]{"userId","userCaption"},new String[]{"String","String"});
		}else{
			users = baseDao.queryDTOBySql("SELECT u.userId,u.userCaption FROM t_users AS u RIGHT JOIN (SELECT * FROM user_role WHERE r_id = 3) AS r ON u.userId = r.u_id WHERE u.parentId='"+projectId+"' AND u.userStatus=1", 
					User.class,new String[]{"userId","userCaption"},new String[]{"String","String"});
		}
		return users;
	}

	@Override
	public Map<String, Object> findReceptionByCustomerFirstVisit(String startTime, String endTime, User user,
			String chartDataId) throws ParseException {
		
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)) {
			startTimeStr = startTime;
		}
		if (endTime != null && !"".equals(endTime)) {
			endTimeStr = endTime;
		}
		if (chartDataId != null && !"".equals(chartDataId)) {
			ManaerChartData mcd = (ManaerChartData) baseDao
					.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
			if (mcd != null && !"".equals(mcd)) {
				map.put("descText", mcd.getDescText());
				map.put("graph_1", mcd.getChartName());
			}
		}

		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '" + user.getParentId()
				+ "' and arriveTime >= '" + startTimeStr + "' and arriveTime <= '" + endTimeStr + "' ");

		// 首次有效到访数
		int firstEffectiveNum = 0;
		// 首次无效数
		int firstInvalidNum = 0;
		// map.put("graph_2", "码表图");
		map.put("title", "观察首访有效接访");
		// map.put("name_1", "总接访数");
		map.put("name_1", "首访有效接访数");
		map.put("name_2", "首访无效接访数");
		// map.put("name_4", "有效接访率");
		map.put("length", 2);
		
		//这段时间内是首次到访客户
		Set<String> phoneSet = new HashSet<>();
		if (vrList.size() > 0) {
			// 总接访量
			for (VisitRecords vr : vrList) {
				if (vr.getPhone() != null && !"".equals(vr.getPhone())){
					List<VisitRecords> vrList2 = baseDao.findByHql("from VisitRecords where projectId = '"+user.getParentId()+"' "
							+ " and arriveTime < '"+startTimeStr + "' and phone = '"+vr.getPhone()+"' ");
					if (vrList2.size() < 1 || vrList2 == null){
						phoneSet.add(vr.getPhone());
					}
				}else {
					firstInvalidNum ++;
				}
			}
			if (phoneSet.size()>0){
				map.put("data_1", phoneSet.size());
			}else {
				map.put("data_1", 0);
			}
			
			map.put("data_2", firstInvalidNum);
		} else {
			map.put("data_1", 0);
			map.put("data_2", 0);
		}

		return map;
	}

}
