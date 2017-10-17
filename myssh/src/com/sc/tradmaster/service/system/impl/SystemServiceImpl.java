package com.sc.tradmaster.service.system.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.Advertisement;
import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.PartnerProjects;
import com.sc.tradmaster.bean.PartnerShops;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.ProjectReportRecord;
import com.sc.tradmaster.bean.ReportResult;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.project.impl.dto.RegisterProjectDTO;
import com.sc.tradmaster.service.system.SystemService;
import com.sc.tradmaster.service.system.impl.dto.AdDTO;
import com.sc.tradmaster.service.system.impl.dto.Partner;
import com.sc.tradmaster.service.system.impl.dto.ProjectOfPartner;
import com.sc.tradmaster.service.system.impl.dto.ProjectsAndShopDTO;
import com.sc.tradmaster.service.system.impl.dto.ProjectsAndUsers;
import com.sc.tradmaster.service.system.impl.dto.ShopOfPartner;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.utils.ConvertUtil;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.ExcelProjectUtils;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.SysContent;

@Service("systemService")
public class SystemServiceImpl implements SystemService {

	@Resource(name = "baseDao")
	private BaseDao baseDao;
	
	@Resource(name = "tagService")
	private TagService tagService;

	
	
	private Set<User> getUserByRole(Integer roleId) {
		// List<Role> roleList = baseDao.findByHql("from Role where id = " + roleId);
		// Role role = roleList.get(0);
		// Set<User> users = role.getUser();
		// return null;
		String sql = "SELECT * FROM t_users u, user_role ur WHERE  u.userId = ur.u_id and ur.r_id = "+roleId;
		List<User> usersList = baseDao.queryBySql(sql, User.class);
		Set<User> users = new HashSet<>();
		for(User u : usersList){
			users.add(u);
		}
		return users;
	}
	
	@Override
	public List<Map<String, String>> getSystemCount() {

		List<Map<String, String>> countList = new ArrayList<>();
		Map<String, String> countMap = new HashMap<>();
		/* 获取项目总数 */
		String projectCountHql = "select count(*) from Project";
		Integer projectCount = baseDao.countQuery(projectCountHql);
		//System.out.println(projectCount);
		/* 获取置业顾问总数 role_id=3代表职业顾问 */
		//List<Role> roleList = baseDao.findByHql("from Role where id = 3");
		//Role role = roleList.get(0);
		Set<User> users = getUserByRole(3);//role.getUser();
		// List<User> adviser = baseDao.findByHql("from User where roleId =
		// "+uSet);
		Integer adviserCount = 0; 
		for(User u : users){
			if(u.getUserStatus() == 1){
				adviserCount += 1;
			}
		}
		// System.out.println(adviserCount);
		/* 获取房源总数 */
		String houseCountHql = "select count(*) from House where houseStatus != 2 and houseStatus != 5";
		Integer houseCount = baseDao.countQuery(houseCountHql);
		/* 门店总数 */
		String shopCountHql = "select count(*) from Shops where shopStatus = 1";
		Integer shopCount = baseDao.countQuery(shopCountHql);
		/* 经纪人总数 roleId=1为中介经纪人 */
		//List<Role> agentRole = baseDao.findByHql("from Role where id= 1");
		//Role r = agentRole.get(0);
		Set<User> agents = getUserByRole(1);//r.getUser();
		Integer agentCount = 0;
		for(User u : agents){
			if(u.getUserStatus() == 1){
				agentCount += 1;
			}
		}
		/* 合伙人总数 6为合伙人 */
		//List<Role> partnerRole = baseDao.findByHql("from Role where id = 6");
		//Role p = partnerRole.get(0);
		Set<User> partners = getUserByRole(6);// p.getUser();
		Integer partnerCount = 0;
		for(User u : partners){
			if(u.getUserStatus() == 1){
				partnerCount += 1;
			}
		}
		/* 将所有的元素放入Map */
		countMap.put("projectCount", projectCount.toString());
		countMap.put("adviserCount", adviserCount.toString());
		countMap.put("houseCount", houseCount.toString());
		countMap.put("shopCount", shopCount.toString());
		countMap.put("agentCount", agentCount.toString());
		countMap.put("partnerCount", partnerCount.toString());

		/* 将Map放入LIst */
		countList.add(countMap);

		return countList;

	}

	@Override
	public Set<User> findUsersByRoleId() {
		//List<Role> roles = baseDao.findByHql("from Role where roleId=6");
		//Role role = roles.get(0);
		Set<User> users = getUserByRole(6);//role.getUser();
		return users;
	}

	@Override
	public List<Project> findPartnerOtherProjects() {
		//查询所有的项目
		List<Project> projects = baseDao.listAll("Project");
		//查询所有的合伙人项目
		List<PartnerProjects> partnersPro = baseDao.listAll("PartnerProjects");
		List<Project> otherP = new ArrayList<>();
		if(partnersPro.size()==0){
			return projects;
		}
		for(PartnerProjects pps:partnersPro){
			String partnerProjectId = pps.getProjectId();
			Iterator<Project> iterator = projects.iterator();
			while(iterator.hasNext()){
				Project next = iterator.next();
				String projectId = next.getProjectId();
				if(partnerProjectId.equals(projectId)&&pps.getRelationStatus()==1){
					iterator.remove();
				}
			}
		}
		return projects;
	}

	@Override
	public List<Shops> findPartnerOtherShops() {
		//查询所有的门店
		List<Shops> shops = baseDao.listAll("Shops");
		//查询所有的合伙人项目
		List<PartnerShops> partnersShops = baseDao.listAll("PartnerShops");
		List<Shops> otherS = new ArrayList<>();
		if(partnersShops.size()==0){
			return shops;
		}
		for(PartnerShops ps:partnersShops){
			String partnerShop = ps.getShopId();
			int partnerShop1 = Integer.valueOf(partnerShop);
			Iterator<Shops> iterator = shops.iterator();
			while(iterator.hasNext()){
				Shops next = iterator.next();
				Integer shopId = next.getShopId();
				if(partnerShop1==shopId&&ps.getRelationStatus()==1){
					iterator.remove();
				}
			}
		}
		return shops;
	
	}

	@Override
	public void addPartnerAndProjects(String userId, String[] projectIds, Integer pvalidity) {
		for(String projectId:projectIds){
			PartnerProjects pp = new PartnerProjects();
			pp.setProjectId(projectId);
			pp.setUserId(userId);
			pp.setValidity(pvalidity);
			pp.setRelationStatus(1);
			pp.setCreateTime(DateTime.toString1(new Date()));
			baseDao.saveOrUpdate(pp);
		}
	}

	@Override
	public void addPartnerAndShops(String userId, String[] shopIds, Integer svalidity) {
		for(String shopId:shopIds){
			PartnerShops ps = new PartnerShops();
			ps.setRelationStatus(1);
			ps.setShopId(shopId);
			ps.setUserId(userId);
			ps.setValidity(svalidity);
			ps.setCreateTime(DateTime.toString1(new Date()));
			baseDao.saveOrUpdate(ps);
		}
	}

	@Override
	public List<Partner> findPartners(int page,int count) {
		List<User> usersOne = baseDao.findByHql("from User ");
		List<User> userss = new ArrayList<>();
		for(User u : usersOne){
			Set<Role> roles = u.getRoleId();
			for(Role r : roles){
				if(r.getRoleId()==6){
					userss.add(u);
				}
			}
		}
		List<User> users = new ArrayList<>();
		int start = (page-1)*count;
		int end = start+count;
		
		if(end >=userss.size()){
			end = userss.size();
		}
		for(int i = start ; i<end ; i++){
			users.add(userss.get(i));
		}
		List<Partner> partners = new ArrayList<>();
		for(User u:users){
			Integer us = u.getUserStatus();
			if(us==1){
				Partner p = new Partner();
				String pId = u.getUserId();
				p.setPartnerId(pId);
				//查询userId查询所有管辖状态的PartnerProjects
				List<PartnerProjects> PartnerProjectss = baseDao.findByHql("from PartnerProjects where userId='"+pId+"' and relationStatus=1");
				List<ProjectOfPartner> popList = new ArrayList<>();
				for(PartnerProjects pp:PartnerProjectss){
					String projectId = pp.getProjectId();
					Project project =(Project) baseDao.loadObject("from Project where projectId='"+projectId+"'");
					if (project!=null) {
						ProjectOfPartner pop = new ProjectOfPartner();
						pop.setCreateTime(pp.getCreateTime());
						pop.setProjectId(projectId);
						pop.setProjectName(project.getProjectName());
						pop.setRemoveTime(pp.getRemoveTime());
						pop.setValidity(pp.getValidity());
						popList.add(pop);
					}
				}
				p.setProjects(popList);
				List<PartnerShops>  PartnerShopss= baseDao.findByHql("from PartnerShops where userId='"+pId+"' and relationStatus=1");
				List<ShopOfPartner> sopList = new ArrayList<>();
				for(PartnerShops ps:PartnerShopss){
					String shopId = ps.getShopId();
					Shops shops =(Shops) baseDao.loadObject("from Shops where shopId="+shopId);
					if (shops!=null) {
						ShopOfPartner sop = new ShopOfPartner();
						sop.setCreateTime(ps.getCreateTime());
						sop.setRemoveTime(ps.getRemoveTime());
						sop.setShopId(ps.getShopId());
						sop.setShopName(shops.getShopName());
						sop.setValidity(ps.getValidity());
						sopList.add(sop);
					}
				}
				p.setShops(sopList);
				User user = (User)baseDao.loadObject("from User where userId='"+pId+"'");
				if (user!=null) {
					p.setPartnerName(user.getUserCaption());
					p.setPhone(user.getPhone());
					//System.out.println(p.getPartnerName());
					partners.add(p);
				}
			}
		}
		
		return partners;
	}

	@Override
	public Partner findPartnerById(String userId) {
		Partner p = new Partner();
		p.setPartnerId(userId);
		List<PartnerProjects> PartnerProjectss = baseDao.findByHql("from PartnerProjects where userId='"+userId+"' and relationStatus=1");
		List<ProjectOfPartner> popList = new ArrayList<>();
		for(PartnerProjects pp:PartnerProjectss){
			String projectId = pp.getProjectId();
			Project project =(Project) baseDao.loadObject("from Project where projectId='"+projectId+"'");
			if (project!=null) {
				ProjectOfPartner pop = new ProjectOfPartner();
				pop.setCreateTime(pp.getCreateTime());
				pop.setProjectId(projectId);
				pop.setProjectName(project.getProjectName());
				pop.setRemoveTime(pp.getRemoveTime());
				pop.setValidity(pp.getValidity());
				popList.add(pop);
			}
		}
		p.setProjects(popList);
		List<PartnerShops>  PartnerShopss= baseDao.findByHql("from PartnerShops where userId='"+userId+"' and relationStatus=1");
		List<ShopOfPartner> sopList = new ArrayList<>();
		for(PartnerShops ps:PartnerShopss){
			String shopId = ps.getShopId();
			Shops shops =(Shops) baseDao.loadObject("from Shops where shopId="+shopId);
			if (shops!=null) {
				ShopOfPartner sop = new ShopOfPartner();
				sop.setCreateTime(ps.getCreateTime());
				sop.setRemoveTime(ps.getRemoveTime());
				sop.setShopId(ps.getShopId());
				sop.setShopName(shops.getShopName());
				sop.setValidity(ps.getValidity());
				sopList.add(sop);
			}
		}
		p.setShops(sopList);
		User user = (User)baseDao.loadObject("from User where userId='"+userId+"'");
		if (user!=null) {
			p.setPartnerName(user.getUserCaption());
			p.setPhone(user.getPhone());
		}
		return p;
	}

	@Override
	public void updatePartnerRel(User user, String[] projectIds, String[] shopIds) {
		String userId = user.getUserId();
		User u = (User)baseDao.loadObject("from User where userId='"+userId+"'");
		String phone = user.getPhone();
		if(!phone.equals(u.getPhone())){
			//修改电话
			u.setPhone(phone);
			String password = phone.substring(5);
			String pwd = SysContent.md5(password);
			u.setPassword(pwd);
			baseDao.saveOrUpdate(u);
		}
		List<PartnerProjects> PartnerProjectss = baseDao.findByHql("from PartnerProjects where userId='" + userId + "' and relationStatus=1");
		for (PartnerProjects pps : PartnerProjectss) {
			if (projectIds!=null) {
				boolean projectFlags = true; //默认要解除
				for (String projectId : projectIds) {
					if (pps.getProjectId().equals(projectId)) {
						projectFlags=false;//设置为不解除关系
						break;
					}
				}
				if(projectFlags){
					//设置为2：解除
					pps.setRelationStatus(2);
					pps.setRemoveTime(DateTime.toString1(new Date()));
					baseDao.saveOrUpdate(pps);
				}
			} else{
				pps.setRelationStatus(2);
				pps.setRemoveTime(DateTime.toString1(new Date()));
				baseDao.saveOrUpdate(pps);
			}
		}
		List<PartnerShops> PartnerShopss = baseDao.findByHql("from PartnerShops where userId='" + userId + "' and relationStatus=1");
		for (PartnerShops pss : PartnerShopss) {
			if (shopIds!=null) {
				boolean shopsFlags = true; //默认要解除
				for (String shopId : shopIds) {
					if (pss.getShopId().equals(shopId)) {
						shopsFlags=false;
						break;
					}

				}
				if(shopsFlags){
					//设置为2：解除
					pss.setRelationStatus(2);
					pss.setRemoveTime(DateTime.toString1(new Date()));
					baseDao.saveOrUpdate(pss);
				}
			} else{
				pss.setRelationStatus(2);
				pss.setRemoveTime(DateTime.toString1(new Date()));
				baseDao.saveOrUpdate(pss);
			}
	}
		

	}


	
	@Override
	public List<Map<String, String>> getRecordsNum(String startTime, String endTime) {

		// 到访组数
		Integer visitCount = 0;
		// 到访总人数
		Integer visiterNum = 0;
		// 登记记录
		Integer writeCount = 0;
		// 认购，申请数
		Integer applyCount = 0;
		// 认购，同意数
		Integer agreeCount = 0;
		// 认购，否决数
		Integer votedCount = 0;
		// 签约总数
		Integer writedCount = 0;
		// 到款总数
		Integer getMoneyCount = 0;
		// 中介客户贡献度
		String mediumPerc = null;
		// 中介客户成交贡献度
		String mediumTurnove = null;
		//成交总数
		Integer turnoverCount = 0;
		//中介成交数
		Integer mediumCount = 0;
		List<Map<String, String>> countList = new ArrayList<>();
		Map<String, String> countMap = new HashMap<>();
		
		
		if(endTime != null && !"".equals(endTime)){
			Date end = DateUtil.parse(endTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
			end = DateUtil.getIntegralEndTime(end);
			endTime = DateUtil.format(end);
		}
		
		if(startTime != null && !"".equals(startTime)){
			Date start = DateUtil.parse(startTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
			start = DateUtil.getIntegralStartTime(start);
			startTime = DateUtil.format(start);
		}
		
		
		
		String visitHql = "from VisitRecords where 1=1 ";
		String conHql = "from ContractRecords where 1=1 ";
		String guideHql = "from GuideRecords where (applyStatus = 0 or applyStatus = 1)";
		
		String dealHql = "from ContractRecords where recordStatus = 5 ";
		if(startTime != null && !"".equals(startTime)){
			
			visitHql += " and arriveTime >= '" + startTime + "' ";
			conHql += " and applyTime >= '" + startTime + "' ";
			guideHql += " and applyTime >= '" + startTime + "' ";
			dealHql += " and contractConfirmTime >= '" + startTime + "' ";
		}
		if(endTime != null && !"".equals(endTime)){
			
			visitHql += " and arriveTime <= '" + endTime + "' ";
			conHql += " and applyTime <= '" + endTime + "' ";
			guideHql += " and applyTime <= '" + endTime + "' ";
			dealHql += " and contractConfirmTime <= '" + endTime + "' ";
		}
		
		/**  到访 */
		List<VisitRecords> visitList = baseDao.findByHql(visitHql);
		for(VisitRecords vrs : visitList){
			//到访数
			visitCount++;
			//到访总人数
			if(vrs.getCustomerCount() != null && !"".equals(vrs.getCustomerCount())){
				visiterNum += vrs.getCustomerCount();
			}
			
			if(vrs.getWriteState() != null && !"".equals(vrs.getWriteState()) && vrs.getWriteState() == 1){
				writeCount++;
			}
		}
		
		/** 认购 */
		
		List<ContractRecords> conList = baseDao.findByHql(conHql);
		for(ContractRecords cr : conList){
			if(cr.getRecordStatus() != null && !"".equals(cr.getRecordStatus())){
				
				//申请数
				if(cr.getRecordStatus() == 0){
					applyCount++;
				}
				//同意认购数
				if(cr.getRecordStatus() != 0 && cr.getRecordStatus() != 3 && cr.getRecordStatus() != 7){
					agreeCount++;
				}
				if(cr.getRecordStatus() == 3){
					votedCount++;
				}
				if(cr.getRecordStatus() == 5){
					writedCount++;
				}
				if(cr.getRecordStatus() == 4){
					getMoneyCount++;
				}
			}
		}
		
		//带看
		List<GuideRecords> guideList = baseDao.findByHql(guideHql);
		
		if(visiterNum > 0){
			mediumPerc = getTwoNumberForValue(guideList.size(), visiterNum);
		}else{
			mediumPerc = "0";
		}
		
		//成交
		//List<Role> mediumRole = baseDao.findByHql("from Role where id = 1");
		//Role p = mediumRole.get(0);
		Set<User> mediums = getUserByRole(1);//p.getUser();
		List<ContractRecords> dealList = baseDao.findByHql(dealHql);
		for(ContractRecords crs : dealList){
			turnoverCount++;
			for(User u : mediums){
				if(crs.getUserId() != null && !"".equals(crs.getUserId()) && crs.getUserId().equals(u.getUserId())){
					mediumCount++;
				}
			}
		}
		
		//中介成交贡献度
		if(turnoverCount > 0){
			mediumTurnove = getTwoNumberForValue(mediumCount, turnoverCount);
		}else{
			mediumTurnove = "0";
		}
		

		countMap.put("visitCount", visitCount.toString());
		countMap.put("visiterNum", visiterNum.toString());
		countMap.put("writeCount", writeCount.toString());
		countMap.put("applyCount", applyCount.toString());
		countMap.put("agreeCount", agreeCount.toString());
		countMap.put("votedCount", votedCount.toString());
		countMap.put("writedCount", writedCount.toString());
		countMap.put("getMoneyCount", getMoneyCount.toString());
		countMap.put("mediumPerc", mediumPerc.toString());
		countMap.put("mediumTurnove", mediumTurnove.toString());

		countList.add(countMap);
		return countList;
	}
	
	/**
	 * 获取两个数值的百分比
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	private String getTwoNumberForValue(Integer divisor, Integer dividend) {
		
		if(dividend == 0){
			return "0";
		}

		NumberFormat numberFormat = NumberFormat.getInstance();

		// 设置精确到小数点后2位

		numberFormat.setMaximumFractionDigits(2);

		String result = numberFormat.format((float) divisor / (float) dividend * 100);
		
		return result;

	}

	@Override
	public List<Map<String, String>> getMeidNum(String startTime, String endTime) {

		// 备案总数--申请
		Integer applyCount = 0;
		// 确认总数
		Integer confirmCount = 0;
		// 备案总数--否决
		Integer vetoCount = 0;
		// 中介发起的认购总数
		Integer loanCount = 0;
		// 中介签约成功率
		String writeSuccessCount = "0.00";
		
		
		if(endTime != null && !"".equals(endTime)){
			Date end = DateUtil.parse(endTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
			end = DateUtil.getIntegralEndTime(end);
			endTime = DateUtil.format(end);
		}
		
		if(startTime != null && !"".equals(startTime)){
			Date start = DateUtil.parse(startTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
			start = DateUtil.getIntegralStartTime(start);
			startTime = DateUtil.format(start);
		}

		List<Map<String, String>> meidDataList = new ArrayList<>();
		Map<String, String> meidDataMap = new HashMap<>();
		/* ---备案总数---- */
		// 申请状态
		String applyCountHql = "select count(*) from GuideRecords where applyStatus = 0 and applyTime is not null ";
		if (startTime != null && !startTime.equals("")) {
			
			applyCountHql += " and applyTime >= '" + startTime + "' ";
		}
		if (endTime != null && !endTime.equals("")) {
			applyCountHql += " and applyTime <= '" + endTime + "'";
		}
		applyCount = baseDao.countQuery(applyCountHql);
		// 确认状态
		applyCountHql = "select count(*) from GuideRecords where applyStatus = 1 and applyTime is not null ";
		if (startTime != null && !startTime.equals("")) {
			applyCountHql += " and applyTime >= '" + startTime + "' ";
		}
		if (endTime != null && !endTime.equals("")) {
			applyCountHql += " and applyTime <= '" + endTime + "'";
		}
		confirmCount = baseDao.countQuery(applyCountHql);

		// 否决状态
		applyCountHql = "select count(*) from GuideRecords where applyStatus = 3 and applyTime is not null";
		if (startTime != null && !startTime.equals("")) {
			applyCountHql += " and applyTime >= '" + startTime + "' ";
		}
		if (endTime != null && !endTime.equals("")) {
			applyCountHql += " and applyTime <= '" + endTime + "'";
		}
		vetoCount = baseDao.countQuery(applyCountHql);

		/*---认购总数,为中介角色发起的认购----*/
		//List<Role> role = baseDao.findByHql("from Role where id = 1");
		//Role meidRole = role.get(0);
		Set<User> meidUser = getUserByRole(1);//meidRole.getUser();
		String contractHql = "from ContractRecords where recordStatus != 2 and recordStatus != 3"
				+ " and recordStatus != 7 and auditingTime is not null ";
		if (startTime != null && !startTime.equals("")) {
			contractHql += " and auditingTime >= '" + startTime + "' ";
		}
		if (endTime != null && !endTime.equals("")) {
			contractHql += " and auditingTime <= '" + endTime + "'";
		}
		List<ContractRecords> contractRecords = baseDao.findByHql(contractHql);
		if (meidUser.size() > 0) {

			for (ContractRecords crs : contractRecords) {
				for (User meid : meidUser) {
					//System.out.println(crs.getUserId() + " : " + meid.getUserId());
					if (crs.getUserId().equals(meid.getUserId())) {

						loanCount += 1;
					}
				}
			}
		}
		/*---签约成功率----*/
		// 中介发起的签约数
		String meidHql = "from ContractRecords where recordStatus = 5 and contractConfirmTime is not null";
		if (startTime != null && !startTime.equals("")) {
			meidHql += " and contractConfirmTime >= '" + startTime + "' ";
		}
		if (endTime != null && !endTime.equals("")) {
			meidHql += " and contractConfirmTime <= '" + endTime + "'";
		}
		contractRecords = baseDao.findByHql(meidHql);
		Integer successCount = 0;
		if (meidUser.size() > 0) {

			for (ContractRecords cr : contractRecords) {
				for (User u : meidUser) {
					if (cr.getUserId().equals(u.getUserId())) {
						successCount++;
					}
				}
			}
		}
		// 中介发起的到款数
		/*meidHql = "select count(*) from ContractRecords where recordStatus = 5 and isShopPayConfirm = 2";
		if (startTime != null && !startTime.equals("")) {
			meidHql += " and shopPayConfirmTime >= '" + startTime + "' ";
		}
		if (endTime != null && !endTime.equals("")) {
			meidHql += " and shopPayConfirmTime <= '" + endTime + "'";
		}
		Double getMoneyCount = (double) baseDao.countQuery(meidHql);*/
		// 签约成功率 到款数/签约数
		
		writeSuccessCount =  getTwoNumberForValue(successCount, loanCount);
		

		meidDataMap.put("applyCount", applyCount.toString());
		meidDataMap.put("confirmCount", confirmCount.toString());
		meidDataMap.put("vetoCount", vetoCount.toString());
		meidDataMap.put("loanCount", loanCount.toString());
		meidDataMap.put("writeSuccessCount", writeSuccessCount);

		meidDataList.add(meidDataMap);

		return meidDataList;
	}

	@Override
	public List<ProjectsAndUsers> findAllProjectAndUsers() {
		//查询所有的项目，获取项目ID
		List<Project> projects = baseDao.listAll("Project");
		List<ProjectsAndUsers> result = new ArrayList<>();
		for(Project p:projects){
			ProjectsAndUsers pau = new ProjectsAndUsers();
			String projectId = p.getProjectId();
			pau.setProjectId(projectId);
			pau.setProjectName(p.getProjectName());
			pau.setSaleAddress(p.getSaleAddress());
			String city = p.getCity();
			String[] split = city.split("-");
			if(split.length>0){
				String citys = "";
				for(String s : split){
					CountryProvinceInfo c = (CountryProvinceInfo)baseDao.loadObject("from CountryProvinceInfo where cityId= '"+s+"'");
					String cityName = c.getCityName();
					citys = citys + cityName+"-";
				}
				pau.setCity(citys.substring(0, citys.length()-1));
			}
			//根据projectId和用户状态查询相应的案场助理和职业顾问
			List<User> findByHql = baseDao.findByHql("from User where parentId='"+projectId+"' and userStatus=1");
			//根据roleId=4 筛选出案场助理
			for(User u:findByHql){
				Set<Role> roles = u.getRoleId();
				for(Role r:roles){
					int roleId = r.getRoleId();
					if(4==roleId){
						pau.setAssistantProject(u);
					}
				}
			}
			//根据projectId和状态为1的条件查找出对应的合伙人
			PartnerProjects pp = (PartnerProjects) baseDao.loadObject("from PartnerProjects where projectId='"+projectId+"' and relationStatus=1");
			if (pp!=null) {
				String userId = pp.getUserId();
				User partner = (User) baseDao.loadObject("from User where userId='" + userId + "'");
				pau.setPartners(partner);
			}
			result.add(pau);
		}
		return result;
	}
	
	@Override
	public void findAllProjectAndUsersToPage(Page page,String addr) {
		String proHql = "";
		if(!StringUtils.isEmpty(addr)){
			proHql = "from Project where city like '%"+addr+"%'";
		}else{
			proHql = "from Project";
		}
			
		//查询所有的项目，获取项目ID
				List<Project> projects = baseDao.findByHql(proHql,page.getStart(), page.getLimit());
				List<ProjectsAndUsers> result = new ArrayList<>();
				for(Project p:projects){
					ProjectsAndUsers pau = new ProjectsAndUsers();
					String projectId = p.getProjectId();
					pau.setProjectId(projectId);
					pau.setProjectName(p.getProjectName());
					pau.setSaleAddress(p.getSaleAddress());
					String city = p.getCity();
					String[] split = city.split("-");
					if(split.length>0){
						String citys = "";
						for(String s : split){
							CountryProvinceInfo c = (CountryProvinceInfo)baseDao.loadObject("from CountryProvinceInfo where cityId= '"+s+"'");
							String cityName = c.getCityName();
							citys = citys + cityName+"-";
						}
						pau.setCity(citys.substring(0, citys.length()-1));
					}
					//根据projectId和用户状态查询相应的案场助理和职业顾问
					List<User> findByHql = baseDao.findByHql("from User where parentId='"+projectId+"' and userStatus=1");
					//根据roleId=4 筛选出案场助理
					for(User u:findByHql){
						Set<Role> roles = u.getRoleId();
						for(Role r:roles){
							int roleId = r.getRoleId();
							if(4==roleId){
								pau.setAssistantProject(u);
							}
						}
					}
					//根据projectId和状态为1的条件查找出对应的合伙人
					PartnerProjects pp = (PartnerProjects) baseDao.loadObject("from PartnerProjects where projectId='"+projectId+"' and relationStatus=1");
					if (pp!=null) {
						String userId = pp.getUserId();
						User partner = (User) baseDao.loadObject("from User where userId='" + userId + "'");
						pau.setPartners(partner);
					}
					result.add(pau);
				}
				String cHql = "select count(*) " + proHql;
				int total = baseDao.countQuery(cHql);
				page.setRoot(result);
				page.setTotal(total);
	}

	@Override
	public boolean addPartner(User user) {
		if (user!=null) {
			String date = DateTime.toString1(new Date());
			user.setCreateTime(date);
			user.setUserId(SysContent.uuid());
			Role role = (Role) baseDao.loadById(Role.class, 6);
			Set roles = new HashSet();
			roles.add(role);
			user.setRoleId(roles);
			String password = user.getPhone().substring(5);
			String pwd = SysContent.md5(password);
			user.setPassword(pwd);
			user.setUserStatus(1);
			baseDao.saveOrUpdate(user);
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public List<AdDTO> findAdvertisementByPage(int page,int count) { 
		int startNum = (page-1)*count;
		
		String hql = "from Advertisement";
		List<Advertisement> listAll = baseDao.findByHql(hql,startNum,count);
		List<AdDTO> adDTOs = new ArrayList<>();
		for(Advertisement ad:listAll){
			AdDTO adDTO = new AdDTO();
			Project project = (Project)baseDao.loadObject("from Project where projectId='"+ad.getProjectId()+"'");
			if (project!=null) {
				adDTO.setProjectName(project.getProjectName());
			}
			adDTO.setAdId(ad.getAdId());
			if("foryou".equals(ad.getAdPosition())){
				adDTO.setAdPosition("为你推荐");
			}else if("location".equals(ad.getAdPosition())){
				adDTO.setAdPosition("本地精选");
			}else{
				adDTO.setAdPosition("其他推荐");
				
			}
			adDTO.setAdTitle(ad.getAdTitle());
			adDTO.setAdUrl(ad.getAdUrl());
			adDTO.setEndTime(ad.getEndTime());
			adDTO.setSorting(ad.getSorting());
			adDTO.setStartTime(ad.getStartTime());
			adDTO.setState(ad.getState());
			//////////////////////////////////////////////
			/////处理城市/////处理城市/////处理城市/////////////
			//////////////////////////////////////////////
			String[] citys =ad.getAdCity().split("-");
			CountryProvinceInfo city = (CountryProvinceInfo)baseDao.loadObject("from CountryProvinceInfo where cityId='"+citys[0]+"'");
			CountryProvinceInfo city1 = (CountryProvinceInfo)baseDao.loadObject("from CountryProvinceInfo where cityId='"+citys[1]+"'");
			adDTO.setProvince(city.getCityName());
			adDTO.setCity(city1.getCityName());
			adDTOs.add(adDTO);	
		}
		return adDTOs;
	}

	@Override
	public AdDTO findAdByAdId(String adId) {
		Advertisement ad = (Advertisement)baseDao.loadObject("from Advertisement where adId="+adId);
		Project project = (Project)baseDao.loadObject("from Project where projectId='"+ad.getProjectId()+"'");
		AdDTO adDTO = new AdDTO();
		if (project!=null) {
			adDTO.setProjectName(project.getProjectName());
		}
		adDTO.setAdId(ad.getAdId());
//		if("foryou".equals(ad.getAdPosition())){
//			adDTO.setAdPosition("为你推荐");
//		}else if("location".equals(ad.getAdPosition())){
//			adDTO.setAdPosition("本地精选");
//		}else{
//			adDTO.setAdPosition("其他推荐");
//		}
		adDTO.setAdPosition(ad.getAdPosition());
		adDTO.setAdTitle(ad.getAdTitle());
		adDTO.setAdUrl(ad.getAdUrl());
		adDTO.setEndTime(ad.getEndTime());
		adDTO.setSorting(ad.getSorting());
		adDTO.setStartTime(ad.getStartTime());
		adDTO.setState(ad.getState());
		adDTO.setProjectId(ad.getProjectId());
		adDTO.setProvinceAndCity(ad.getAdCity());
		String[] citys =ad.getAdCity().split("-");
		CountryProvinceInfo city = (CountryProvinceInfo)baseDao.loadObject("from CountryProvinceInfo where cityId='"+citys[0]+"'");
		CountryProvinceInfo city1 = (CountryProvinceInfo)baseDao.loadObject("from CountryProvinceInfo where cityId='"+citys[1]+"'");
		adDTO.setProvinceId(city.getCityId());
		adDTO.setProvince(city.getCityName());
		adDTO.setCityId(city1.getCityId());
		adDTO.setCity(city1.getCityName());
		return adDTO;
	}

	@Override
	public List<ProjectsAndShopDTO> findStatementOfAccount(String city, String startTime, String endTime) {
		List<ProjectsAndShopDTO> list = new ArrayList<>();
		/* 查出所有项目 */
		String projectHql = "from Project where 1=1 ";
		if (city != null && !city.equals("")) {
			projectHql += " and city = '" + city + "'";
		}
		List<Project> projectList = baseDao.findByHql(projectHql);
		for (Project pro : projectList) {
			/* 查找带看业务定义 */
			String proGuideHql = "from ProjectGuide where projectId = '" + pro.getProjectId() + "'";
			ProjectGuide projectGuide = (ProjectGuide) baseDao.loadObject(proGuideHql);
			if (projectGuide == null) // 如果没有带看业务定义就直接重新开始循环
				continue;
			Double daikanMoney = 0.00;
			Double fenxiaoMoney = 0.00;
			if (projectGuide.getDaiKanMoney() != null)
				daikanMoney = projectGuide.getDaiKanMoney();
			if (projectGuide.getDaiKanMoney() != null)
				fenxiaoMoney = projectGuide.getFenXiaoMoney();
			/* 查找项目下所有为签约状态的房源 */
			String stateHql = "from House where projectId = '" + pro.getProjectId() + "'";
			List<House> houseList = baseDao.findByHql(stateHql);
			for (House house : houseList) {
				ProjectsAndShopDTO proDTO = new ProjectsAndShopDTO();
				/* 项目名 */
				proDTO.setProjectName(pro.getProjectName()); // 项目名称
				proDTO.setProjectId(pro.getProjectId()); //项目Id
				proDTO.setHouseNum(house.getHouseNum()); // 佣金来源
				proDTO.setUnit(house.getUnit());
				proDTO.setBuildingNo(house.getBuildingNo());
				proDTO.setHouseNo(house.getHouseNo());

				/***** 认购记录 *******/
				// 房源状态必须为签约的才算
				String contractHql = "from ContractRecords where houseNum = " + house.getHouseNum()
						+ " and projectId = '" + pro.getProjectId() + "' and recordStatus = 5";
				if (startTime != null && !startTime.equals("")) {
					contractHql += " and contractConfirmTime >= '" + startTime + "'";
				}
				if (endTime != null && !endTime.equals("")) {
					contractHql += " and contractConfirmTime <= '" + endTime + "'";
				}
				ContractRecords contractRecord = (ContractRecords) baseDao.loadObject(contractHql);
				if (contractRecord == null)
					continue;
				proDTO.setApplyTime(contractRecord.getApplyTime());
				proDTO.setContractConfirmTime(contractRecord.getContractConfirmTime()); // 签约确认时间
				proDTO.setIsSystemPayConfirm(contractRecord.getIsSystemPayConfirm()); // 平台结款状态
				proDTO.setRecordStatus(contractRecord.getRecordStatus());	// (0：未结款；1已结款；2已到款)
				proDTO.setSystemPayConfirmTime(contractRecord.getSystemPayConfirmTime()); // 平台结账时间
				proDTO.setSystemReceiveConfirmTime(contractRecord.getSystemReceiveConfirmTime()); // 平台到款时间
				proDTO.setIsSystemPayConfirm(contractRecord.getIsSystemPayConfirm()); //平台结账状态 (0：未结款；1已结款；2已到款)
				/* 带看佣金和分销佣金的计算 */
				Double daikan = 0.00;
				if(contractRecord.getBuyPrice() != null && !"".equals(contractRecord.getBuyPrice())){
					daikan = Math.ceil(contractRecord.getBuyPrice() * (daikanMoney * 0.01));
				}
				Double fenxiao = 0.00;
				if(contractRecord.getBuyPrice() != null && !"".equals(contractRecord.getBuyPrice())){
					fenxiao = Math.ceil(contractRecord.getBuyPrice() * (fenxiaoMoney * 0.01));
				}
				/* 通过userId查找所属角色 */
				String userId = contractRecord.getUserId();
				User user = (User) baseDao.loadById(User.class, userId);
				Set<Role> rSet = user.getRoleId();
				Integer roleId = 0;
				for (Role role : rSet) {
					roleId = role.getRoleId();
				}
				// 如果角色为中介经纪人或者店长去查找用户数所属门店
				if (roleId == 1 || roleId == 2) {
					String shopHql = "from Shops where shopStatus != 2 and shopId = "
							+ Integer.parseInt(user.getParentId());
					Shops shop = (Shops) baseDao.loadObject(shopHql);
					if (shop == null)
						continue;
					proDTO.setAgentUserId(user.getUserCaption());	//经济人姓名
					proDTO.setShopId(shop.getShopId()); // 门店编号
					proDTO.setShopName(shop.getShopName()); // 门店名称
					/* 如果角色为中介经纪人或者店长表示房子属于分销 */
					proDTO.setMoneyType(0); // 0表示为分销
				}
				/** 查找房子属于分销还是带看 */
				/** 如果角色为置业顾问,并且在带看业务定义中有记录就说明是带看的 */
				if (roleId == 3) {
					String recordHql = "from GuideRecords where projectCustomerId = '" + contractRecord.getProjectCustomerId()
							+ "' and applyStatus = 1 ";
					List<GuideRecords> grList = baseDao.findByHql(recordHql);
					if (grList.size() > 0) {
						proDTO.setMoneyType(1); // 1 表示房子为带看的
						// 如果是带看的那么找到中介经纪人的id去找所属门店
						String userHql = "from User where userId = '" + grList.get(0).getUserId() + "'";
						User u = (User) baseDao.loadById(User.class, grList.get(0).getUserId());
						Shops s = (Shops) baseDao.loadById(Shops.class, Integer.parseInt(u.getParentId())); //中介经纪人
						proDTO.setShopName(s.getShopName()); // 门店名称
						proDTO.setDaiKanMoney(daikan); // 带看佣金
						list.add(proDTO);
					}
				}else{
					proDTO.setFenXiaoMoney(fenxiao); // 分销佣金
					list.add(proDTO);
				}

			}

		}
		return list;
	}
	
	@Override
	public boolean updateCommissionForm(Integer houseNum, Integer isConfirm, User user) {
		
		//认购记录表中修改平台收款状态
		try {
			String currentTime = DateUtil.format(new Date());
			String hql = "from ContractRecords where houseNum =" + houseNum;
			ContractRecords contractRecord = (ContractRecords) baseDao.loadObject(hql);
			if(isConfirm == 0){	//取消到款
				contractRecord.setIsSystemPayConfirm(0);
				contractRecord.setSystemReceiveConfirmUserId(null);
				contractRecord.setSystemReceiveConfirmTime(null);
				baseDao.saveOrUpdate(contractRecord);
			}else{	//确认到款
				contractRecord.setIsSystemPayConfirm(2);
				contractRecord.setSystemPayConfirmTime(currentTime);
				contractRecord.setSystemPayConfirmUserId(user.getUserId());
				contractRecord.setSystemReceiveConfirmUserId(user.getUserId());
				contractRecord.setSystemReceiveConfirmTime(currentTime);
				baseDao.saveOrUpdate(contractRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	@Override
	public List<Map<String, Object>> findShopReportForms(String city, String startTime, String endTime, int page, int count) {
		List<Map<String, Object>> list = new ArrayList<>();
		int startNum = (page-1)*count;
		/* 所有的门店 状态为正常的 */
		String shopHql = "from Shops where shopStatus = 1";
		if (city != null && !city.equals("")) {
			shopHql += "and city like '%" + city + "%'";
		}
		if (startTime != null && !startTime.equals("")) {
			shopHql += " and approveTime >= '" + startTime + "'";
		}
		if (endTime != null && !endTime.equals("")) {
			shopHql += " and approveTime <= '" + endTime + "'";
		}
		
		List<Shops> shopList = baseDao.findByHql(shopHql,startNum,count);
		
		for (Shops shop : shopList) {
			Map<String, Object> map = new HashMap<>();
			// 门店名称
			map.put("shopName", shop.getShopName());

			/** 查找该门店下的所有的user */
			String userHql = "from User where parentId = '" + shop.getShopId() + "'";
			List<User> userList = baseDao.findByHql(userHql);

			// 备案数
			Integer recordCount = 0;

			// 到访确认数
			Integer visitCount = 0;

			// 备案逾期数
			Integer overdueCount = 0;

			// 认购数
			Integer contractCount = 0;

			// 带客认购数
			Integer guideCount = 0;

			// 认购逾期数
			Integer contractOverdueCount = 0;

			// 签约数
			Integer signCount = 0;

			for (User u : userList) {

				// 将当前门店用户的所有的认购取出
				String guideRecordsHql = "from GuideRecords where userId = '" + u.getUserId() + "'";
				if (startTime != null && !startTime.equals("")) {
					guideRecordsHql += " and applyTime >= '" + startTime + "'";
				}
				if (endTime != null && !endTime.equals("")) {
					guideRecordsHql += " and applyTime <= '" + endTime + "'";
				}
				List<GuideRecords> guideRecordsList = baseDao.findByHql(guideRecordsHql);
				
				String contratRecHql = "from ContractRecords where recordStatus = 1 and userId = '" + u.getUserId()
						+ "'";
				if (startTime != null && !startTime.equals("")) {
					contratRecHql += " and applyTime >= '" + startTime + "'";
				}
				if (endTime != null && !endTime.equals("")) {
					contratRecHql += " and applyTime <= '" + endTime + "'";
				}
				
				List<ContractRecords> contratRecList = baseDao.findByHql(contratRecHql);

				for (ContractRecords crs : contratRecList) {
					// 打款超时（认购逾期）数
					if ("yes".equals(crs.getIsOut())) {
						contractOverdueCount += 1;
					}
					// 签约数
					if (crs.getRecordStatus() == 5) {
						signCount += 1;
					}

					if (crs.getRecordStatus() == 0) {
						contractCount += 1;

					}
				}

				
				for (GuideRecords grs : guideRecordsList) {
					Integer applyStatus = grs.getApplyStatus();
					// 备案数
					if (applyStatus == 0) {
						recordCount += 1;
					}
					// 到访确认数
					if (applyStatus == 1) {
						visitCount += 1;
					}
					// 备案逾期数
					if (applyStatus == 4) {
						overdueCount += 1;
					}
				}
					
					for (GuideRecords grs : guideRecordsList) {
					// 认购
					for (ContractRecords cr : contratRecList) {

						/*
						 * 该门店下的带客认购数 带看记录表中与认购表中的用户手机号码相同那么就证明该用户是带看的，
						 */
						if (cr.getRecordStatus() == 0 && cr.getCustomerPhone().equals(grs.getCustomerPhone())) {
									guideCount += 1;
						}
					}
				}
			}

			// 备案数
			map.put("recordCount", recordCount);

			// 到访确认数
			map.put("visitCount", visitCount);

			// 备案逾期数
			map.put("overdueCount", overdueCount);

			// 认购数
			map.put("contractCount", contractCount);

			// 带客认购数
			map.put("guideCount", guideCount);

			// 认购逾期数（打款超期数）
			map.put("contractOverdueCount", contractOverdueCount);

			// 签约数
			map.put("signCount", signCount);

			list.add(map);
		}

		return list;
	}
	
	@Override
	public List<Map<String, Object>> findMediReportForms(String city, String startTime, String endTime,int page,int count) {
		int startNum = (page-1)*count;
		List<Map<String, Object>> list = new ArrayList<>();
		
		//所属门店
		String shopHql = "from Shops where shopStatus = 1";
		if(city != null && !"".equals(city)){
			shopHql += "and city like '%" + city + "%'";
		}
		if (startTime != null && !startTime.equals("")) {
			shopHql += " and approveTime >= '" + startTime + "'";
		}
		if (endTime != null && !endTime.equals("")) {
			shopHql += " and approveTime <= '" + endTime + "'";
		}
		
		List<Shops> shopList = baseDao.findByHql(shopHql);
		
		for(Shops shop : shopList){
			
			/* 找出所有经纪人的角色 */
			List<User> userList2 = new ArrayList<>();
			List<User> userList = new ArrayList<>();
			String userHql = "from User where userStatus = 1 and parentId = '" + shop.getShopId() + "'";
			List<User> uList = baseDao.findByHql(userHql);
			for(User u : uList){
				Set<Role> rSet = u.getRoleId();
				for(Role role : rSet){
					if(role.getRoleId() == 1){  //角色为中介经纪人
						userList2.add(u);
					}
				}
			}
			int end = startNum+count;
			if(end>=userList2.size()){
				end = userList2.size();
			}
			for(int i = startNum;i<end;i++){
				userList.add(userList2.get(i));
			}
			for(User user : userList){
				
				//备案数
				Integer recordCount = 0;
				//到访确认数
				Integer visitCount = 0;
				//备案逾期数
				Integer overdueCount = 0;
				//认购数
				Integer contractCount = 0;
				//签约数
				Integer signCount = 0;
				//带客认购数
				Integer guideCount = 0;
				//认购逾期数
				Integer contractOverdueCount = 0;
				
				Map<String, Object> map = new HashMap<>();
				//认购记录
				String contractReacordHql = "from ContractRecords where userId = '" + user.getUserId() + "'";
				if (startTime != null && !startTime.equals("")) {
					contractReacordHql += " and applyTime >= '" + startTime + "'";
				}
				if (endTime != null && !endTime.equals("")) {
					contractReacordHql += " and applyTime <= '" + endTime + "'";
				}
				
				List<ContractRecords> contractReacordList = baseDao.findByHql(contractReacordHql);
				
				//带看备案记录
				String guideRecordHql = "from GuideRecords where userId = '" + user.getUserId() + "'";
				if (startTime != null && !startTime.equals("")) {
					guideRecordHql += " and applyTime >= '" + startTime + "'";
				}
				if (endTime != null && !endTime.equals("")) {
					guideRecordHql += " and applyTime <= '" + endTime + "'";
				}
				List<GuideRecords> guideRecordList = baseDao.findByHql(guideRecordHql);
				
				for(GuideRecords grs : guideRecordList){
					
					//备案成功数
					if(grs.getApplyStatus() == 0)
						recordCount += 1;
					//到访确认
					if(grs.getApplyStatus() == 1)
						visitCount += 1;
					//备案逾期数
					if(grs.getApplyStatus() == 4)
						overdueCount += 1;
					
				}
				
				for(ContractRecords crs : contractReacordList){
					
					//认购数
					if(crs.getRecordStatus() == 0)
						contractCount += 1;
					//签约数
					if(crs.getRecordStatus() == 5)
						signCount += 1;
					//认购逾期数
					if("yes".equals(crs.getIsOut()))
						contractOverdueCount += 1;
					
					for(GuideRecords grs : guideRecordList){
						//带客认购数
						if(crs.getRecordStatus() == 0 && grs.getCustomerPhone().equals(crs.getCustomerPhone())){
								guideCount += 1;
						}
					}
				}
				
				map.put("mediName", user.getUserCaption());
				map.put("shopName", shop.getShopName());
				map.put("recordCount", recordCount);
				map.put("visitCount", visitCount);
				map.put("overdueCount", overdueCount);
				map.put("contractCount", contractCount);
				map.put("signCount", signCount);
				map.put("guideCount", guideCount);
				map.put("contractOverdueCount", contractOverdueCount);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findProjectReportForms(String city, String startTime, String endTime,int page,int count) {
		int startNum = (page-1)*count;
		List<Map<String, Object>> list = new ArrayList<>();
		//查找所有的项目
		String projectHql = "from Project where 1=1 ";
		if(city != null && !"".equals(city)){
			projectHql += "and city like '%" + city + "%'";
		}
		if (startTime != null && !startTime.equals("")) {
			projectHql += " and startTime >= '" + startTime + "'";
		}
		if (endTime != null && !endTime.equals("")) {
			projectHql += " and startTime <= '" + endTime + "'";
		}
		
		List<Project> projectList = baseDao.findByHql(projectHql,startNum,count);
		for(Project project : projectList){
			
			Map<String, Object> map = new HashMap<>();
			//查找项目下的所有房源
			String houseHql = "select count(*) from House where projectId = '" + project.getProjectId() + "'";
			if (startTime != null && !startTime.equals("")) {
				houseHql += " and shelvTime >= '" + startTime + "'";
			}
			if (endTime != null && !endTime.equals("")) {
				houseHql += " and shelvTime <= '" + endTime + "'";
			}
			Integer houseCount = baseDao.countQuery(houseHql);
			
			//项目佣金比例
			ProjectGuide projectGuide = (ProjectGuide) baseDao.loadById(ProjectGuide.class, project.getProjectId());
			
			//分销佣金
			Double fenxiaoMoney = 0.00;
			//带看佣金
			Double daikanMoney = 0.00;
			
			if(projectGuide != null){
				
				fenxiaoMoney = projectGuide.getFenXiaoMoney();
				//带看佣金
				daikanMoney = projectGuide.getDaiKanMoney();
			}
			
			//外场成交数  ：外场成交数表示认购发起人的角色为中介
			Integer outsideCount = 0;
			//内场成交数	：内场成交数表示认购发起人是置业顾问
			Integer insideCount = 0;
			
			
			//认购状态为签约的
			String contractHql = "from ContractRecords where recordStatus = 5 and projectId = '" + project.getProjectId() + "'";
			if (startTime != null && !startTime.equals("")) {
				contractHql += " and contractConfirmTime >= '" + startTime + "'";
			}
			if (endTime != null && !endTime.equals("")) {
				contractHql += " and contractConfirmTime <= '" + endTime + "'";
			}
			List<ContractRecords> contractList = baseDao.findByHql(contractHql);
			for(ContractRecords crs : contractList){
				String userId = crs.getUserId();
				User user = (User) baseDao.loadById(User.class, userId);
				Set<Role> rSet = user.getRoleId();
				for(Role role : rSet){
					if(role.getRoleId() == 1 || role.getRoleId() == 2)
						outsideCount += 1;
					if(role.getRoleId() == 3)
						insideCount += 1;
				}
			}
			
			//平台成交率  暂时不使用
			/*Double closeRate = 0.00;
			if(houseCount != 0){
				closeRate = (contractList.size() * 1.0) / houseCount;
			}*/
			map.put("project", project.getProjectName());
			map.put("houseCount", houseCount);
			map.put("fenxiaoMoney", fenxiaoMoney);
			map.put("daikanMoney", daikanMoney);
			map.put("outsideCount", outsideCount);
			map.put("insideCount", insideCount);
//			map.put("closeRate", closeRate);
			list.add(map);
		}
		
		//将list内的平台成交率进行排序
		/*Collections.sort(list, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				
				Double closeRate1 = (Double) o1.get("closeRate");
				Double closeRate2 = (Double) o2.get("closeRate");
				 
				if(closeRate1 > closeRate2)
					return -1;
				else if(closeRate1 < closeRate2)
					return 1;
				else if(closeRate1 == closeRate2)
					return 0;
				return 0;
			}
		});*/
		
		return list;
	}

	@Override
	public int findAdTotal() {
		int countAll = baseDao.countAll("Advertisement");
		return countAll;
	}

	@Override
	public List<Project> findProByCity(String string) {
		String hql = "from Project where city REGEXP '^"+string+"'";
		List<Project> findByHql = baseDao.findByHql(hql);
		return findByHql;
	}

	@Override
	public Map<String, String> findProjectAddressById(String projectId) {
		Map<String,String> projectAddress = new HashMap<>();
		Project p = (Project)baseDao.loadObject("from Project where projectId = '"+projectId+"'");
		String city = p.getCity();
		String[] split = city.split("-");
		List<String> asList = Arrays.asList(split);
		projectAddress.put("provinceId", asList.get(0));
		CountryProvinceInfo cityName = (CountryProvinceInfo)baseDao.loadObject("from CountryProvinceInfo  where cityId = '"+asList.get(1)+"'");
		projectAddress.put("cityName",cityName.getCityName());
		//projectAddress.put("areas", asList.get(2));
		return projectAddress;
	}

	@Override
	public List<Project> findProjectByCity(String cityId) {
		String hql = "from Project where proInSystemStutas=1 and city like '%"+cityId+"%'";
		List<Project> projects = baseDao.findByHql(hql);
		return projects;
	}

	@Override
	public void updateAd(Advertisement ad) {
		Advertisement ad1 = (Advertisement)baseDao.loadObject("from Advertisement where adId= "+ad.getAdId());
		if(!StringUtils.isEmpty(ad.getAdUrl())){
			ad1.setAdUrl(ad.getAdUrl());
		}
		if(!StringUtils.isEmpty(ad.getAdCity())){
			ad1.setAdCity(ad.getAdCity());
		}
		if(!StringUtils.isEmpty(ad.getAdPosition())){
			ad1.setAdPosition(ad.getAdPosition());
		}
		if(!StringUtils.isEmpty(ad.getAdTitle())){
			ad1.setAdTitle(ad.getAdTitle());
		}
		if(!StringUtils.isEmpty(ad.getEndTime())){
			ad1.setEndTime(ad.getEndTime());
		}
		if(!StringUtils.isEmpty(ad.getProjectId())){
			ad1.setProjectId(ad.getProjectId());
		}
		if(!StringUtils.isEmpty(ad.getStartTime())){
			ad1.setStartTime(ad.getStartTime());
		}
		if(ad.getSorting()!=null){
			ad1.setSorting(ad.getSorting());
		}
		if(ad.getState()!=null){
			ad1.setState(ad.getState());
		}
		baseDao.saveOrUpdate(ad1);
	}

	@Override
	public int findPartnerNum() {
		List<User> usersOne = baseDao.findByHql("from User ");
		List<User> users = new ArrayList<>();
		for(User u : usersOne){
			Set<Role> roles = u.getRoleId();
			for(Role r : roles){
				if(r.getRoleId()==6){
					users.add(u);
				}
			}
		}
		return users.size();
	}

	@Override
	public int getProjectRepCount(String city, String startTime, String endTime) {
		String projectHql = "select count(*) from Project where 1=1 ";
		if(city != null && !"".equals(city)){
			projectHql += "and city like '%" + city + "%'";
		}
		if (startTime != null && !startTime.equals("")) {
			projectHql += " and startTime >= '" + startTime + "'";
		}
		if (endTime != null && !endTime.equals("")) {
			projectHql += " and startTime <= '" + endTime + "'";
		}
		
		int countQuery = baseDao.countQuery(projectHql);
		return countQuery;
	}

	@Override
	public int getShopRepCount(String city, String startTime, String endTime) {
		String shopHql = "select count(*) from Shops where shopStatus = 1";
		if (city != null && !city.equals("")) {
			shopHql += "and city like '%" + city + "%'";
		}
		if (startTime != null && !startTime.equals("")) {
			shopHql += " and approveTime >= '" + startTime + "'";
		}
		if (endTime != null && !endTime.equals("")) {
			shopHql += " and approveTime <= '" + endTime + "'";
		}
		
		int countQuery = baseDao.countQuery(shopHql);
		return countQuery;
	}

	@Override
	public int getMediRepCount(String city, String startTime, String endTime) {
		String shopHql = "from Shops where shopStatus = 1";
		if(city != null && !"".equals(city)){
			shopHql += "and city like '%" + city + "%'";
		}
		if (startTime != null && !startTime.equals("")) {
			shopHql += " and approveTime >= '" + startTime + "'";
		}
		if (endTime != null && !endTime.equals("")) {
			shopHql += " and approveTime <= '" + endTime + "'";
		}
		
		List<Shops> shopList = baseDao.findByHql(shopHql);
		int size = 0;
		for(Shops shop : shopList){
			
			/* 找出所有经纪人的角色 */
			List<User> userList = new ArrayList<>();
			String userHql = "from User where userStatus = 1 and parentId = '" + shop.getShopId() + "'";
			List<User> uList = baseDao.findByHql(userHql);
			for(User u : uList){
				Set<Role> rSet = u.getRoleId();
				for(Role role : rSet){
					if(role.getRoleId() == 1){  //角色为中介经纪人
						userList.add(u);
					}
				}
			}
			size = userList.size();
			
		
		}
		return size;
	}

	@Override
	public Map<String,Object> findRegisterProjectByPage(int page, int count) {
		Map<String,Object> map = new HashMap<>();
		int start = (page-1)*count;
		String agentOrderSql = "SELECT p.projectId,p.projectName,p.city,p.propertyAddress,u.userId,u.userCaption,u.phone,u.createTime FROM (SELECT * FROM t_projects WHERE proInSystemStutas = 0) p LEFT JOIN (SELECT * FROM t_users WHERE userId IN(SELECT u_id FROM user_role WHERE r_id=4)) u ON p.projectId = u.parentId LIMIT "+start+","+count;
		String[] colums = new String[]{"projectId","projectName","city","propertyAddress","userId","userCaption","phone","createTime"};
		String[] types = new String[]{"String","String","String","String","String","String","String","String"};
		List<RegisterProjectDTO> queryDTOBySql = baseDao.queryDTOBySql(agentOrderSql, RegisterProjectDTO.class, colums, types);
		for(RegisterProjectDTO p : queryDTOBySql){
			String city = p.getCity();
			String[] split = city.split("-");
			if(split.length>0){
				String citys = "";
				for(String s : split){
					CountryProvinceInfo c = (CountryProvinceInfo)baseDao.loadObject("from CountryProvinceInfo where cityId= '"+s+"'");
					String cityName = c.getCityName();
					citys = citys + cityName+"-";
				}
				p.setCity(citys.substring(0, citys.length()-1));
			}
		}
		int i = baseDao.findCountBySql("SELECT count(*) FROM (SELECT * FROM t_projects WHERE proInSystemStutas = 0) p LEFT JOIN (SELECT * FROM t_users WHERE userId IN(SELECT u_id FROM user_role WHERE r_id=4)) u ON p.projectId = u.parentId");
		//int queryBySql = baseDao.countQuery("SELECT count(p.projectId) FROM (SELECT * FROM Project WHERE proInSystemStutas = 1) p LEFT JOIN (SELECT * FROM User WHERE userId IN(SELECT u_id FROM Role WHERE r_id=4)) u ON p.projectId = u.parentId");
		int totalPage = 0;
		if(i!=0){
			if(i%count==0){
				totalPage = i/count;
			}else{
				totalPage = i/count+1;
			}
			
		}
		map.put("pageTotal", totalPage);
		map.put("dataList", queryDTOBySql);
		return map;
	}

	@Override
	public void updateProjectAndUser(String projectId, int agree) {
		
		if(agree==0 && !StringUtils.isEmpty(projectId)){//否决申请
			Project p = (Project)baseDao.loadObject("from Project where projectId = '"+projectId+"'");
			User u = (User)baseDao.loadObject("from User where parentId='"+projectId+"'");
			if(p!=null && u!=null){
				p.setProInSystemStutas(2);//项目设置为拒绝入住平台
				u.setUserStatus(3);//用户设置为冻结状态
				baseDao.saveOrUpdate(p);
				baseDao.saveOrUpdate(u);
			}else{
				throw new RuntimeException("数据异常");
			}
		}else if(agree==1 && !StringUtils.isEmpty(projectId)){//同意申请
			Project p = (Project)baseDao.loadObject("from Project where projectId = '"+projectId+"'");
			User u = (User)baseDao.loadObject("from User where parentId='"+projectId+"'");
			if(p!=null && u!=null){
				p.setProInSystemStutas(1);//项目设置为同意入住平台
				u.setUserStatus(1);//用户设置为正常状态
				baseDao.saveOrUpdate(p);
				baseDao.saveOrUpdate(u);
				// 给案场添加标准标签库
				tagService.add_copyTagLib(projectId);
			}else{
				throw new RuntimeException("数据异常");
			}
		}else{//错误请求
			throw new RuntimeException("请求参数出错");
		}
	}

	@Override
	public boolean addReportExcelToCloud(ReportResult rr) {

		OutputStream out = new ByteArrayOutputStream();
		ExcelProjectUtils eu = new ExcelProjectUtils();
		eu.exportExcel(rr, out);
		ConvertUtil cu = new ConvertUtil();
		try {
			ByteArrayInputStream byteInput = cu.parse(out);
			String rs = PicUploadToYun.uploadExcel(SysContent.getFileRename("案场数据报.xls"), byteInput);
			addReportExcelToDB(rr, rs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean addReportExcelToDB(ReportResult rr, String url) {
		
		if(StringUtils.isEmpty(url)){
			return false;
		}
		if(rr == null){
			return false;
		}
		
		ProjectReportRecord prr = new ProjectReportRecord();
		prr.setCreateTime(DateUtil.format(new Date()));
		prr.setProjectId(rr.getProjectId());
		prr.setProjectName(rr.getProjectName());
		prr.setStartTime(rr.getStartTime());
		prr.setEndTime(rr.getEndTime());
		prr.setUrl(url);
		String report = "";
		if("案场周报".equals(rr.getReportName())){
			report = "week";
		}else if("案场月报".equals(rr.getReportName())){
			report = "month";
		}else if("案场季报".equals(rr.getReportName())){
			report = "quarter";
		}else if("案场半年报".equals(rr.getReportName())){
			report = "half";
		}else if("案场年报".equals(rr.getReportName())){
			report = "year";
		}else{
			report = "other";
		}
		prr.setReportName(report);
		
		baseDao.save(prr);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectReportRecord> findDownloadReportExcel(String projectId, String reportName, String startTime, String endTime) {
		
		String hql = "from ProjectReportRecord where projectId = '" + projectId + "' ";
		if(!StringUtils.isEmpty(reportName)){
			hql += " and reportName = '" + reportName + "' ";
		}
		if(!StringUtils.isEmpty(startTime)){
			hql += " and createTime >= '" + startTime + "' "; 
		}
		if(!StringUtils.isEmpty(endTime)){
			hql += " and createTime <= '" + endTime + "' ";
		}
		
		List<ProjectReportRecord> list = baseDao.findByHql(hql);
		
		return list;
	}

	

	
}
