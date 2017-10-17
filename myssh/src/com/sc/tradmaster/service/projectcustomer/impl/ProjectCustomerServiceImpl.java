package com.sc.tradmaster.service.projectcustomer.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.ShopCustomers;
import com.sc.tradmaster.bean.Tag;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.projectcustomer.ProjectCustomerService;
import com.sc.tradmaster.service.projectcustomer.impl.dto.CustomerAnalyze;
import com.sc.tradmaster.service.projectcustomer.impl.dto.CustomerManager;
import com.sc.tradmaster.service.projectcustomer.impl.dto.CustomerManagerDTO;
import com.sc.tradmaster.service.projectcustomer.impl.dto.CustomerManagerInfoDTO;
import com.sc.tradmaster.service.projectcustomer.impl.dto.Track;
import com.sc.tradmaster.service.tagService.AppTagManagerService;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.ExcelHelper;
import com.sc.tradmaster.utils.JxlExcelHelper;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.StringUtil;
import com.sc.tradmaster.utils.SysContent;

@Service("projectCustomerService")
public class ProjectCustomerServiceImpl implements ProjectCustomerService {
	
	@Resource(name="appTagManagerService")
	private AppTagManagerService appTagManagerService;
	
	@Resource(name="tagService")
	private TagService tagService;

	@Resource(name = "baseDao")
	private BaseDao baseDao;
	
	@Override
	public void findProCustomersByUser(User user,String selectValue,Page page) {
		List list = new ArrayList<>();
		String hql = "from ProjectCustomers as model where model.projectId = '"+user.getParentId()+"' ";
		if(selectValue!=null && !selectValue.equals("")){
			hql+="and model.projectCustomerName like '%" +selectValue+"%' or model.projectCustomerPhone like '%" +selectValue+"%'";
		}
		List<ProjectCustomers> pcList = baseDao.findByHql(hql,page.getStart(),page.getLimit());
		for(ProjectCustomers  pc :pcList ){
			CustomerManager cm = new CustomerManager();
			User u = null;
			if(pc.getOwnerUserId()!=null && !pc.getOwnerUserId().equals("")){
				u = (User) baseDao.loadById(User.class, pc.getOwnerUserId());
			}
			String crHql = "from ContractRecords where projectId = '"+user.getParentId()+"' and projectCustomerId = '"+pc.getProjectCustomerId()+"'";
			ContractRecords crs = (ContractRecords) baseDao.loadObject(crHql);
			CustomerManager newCm = cm.createCusMan(pc, u,crs);
			list.add(newCm);
		}
		String cHql = "select count(*) "+hql;
		int total = baseDao.countQuery(cHql);
		page.setRoot(list);
		page.setTotal(total);
	}

	@Override
	public List<Map<String, String>> findAgentsToMenu(String parentId) {
		String hql = "from User where parentId = '"+parentId+"'";
		List<User> agList = baseDao.findByHql(hql);
		List<Map<String,String>> listMap = new ArrayList<>();
		for(User ag : agList){
			Map<String,String> agMenu = new HashMap<>();
			agMenu.put("agId", ag.getUserId());
			agMenu.put("agName",ag.getUserCaption());
			listMap.add(agMenu);
		}
		return listMap;
	}

	@Override
	public void addOrUpdateProCustomerownerAgent(String[] proCursId, String agentId) {
		for(String cId : proCursId){
			ProjectCustomers pc =  (ProjectCustomers) baseDao.loadById(ProjectCustomers.class, cId);
			pc.setOwnerUserId(agentId);
			pc.setOwnerStartTime(DateTime.toString1(new Date()));
			baseDao.saveOrUpdate(pc);
		}
	}

	@Override
	public void findShopCustomersByUser(User user, String selectValue, Page page) {
		List cmList = new ArrayList<>();
		int total = 0;
		if(user!=null && user.getParentId()!=null && !user.getParentId().equals("")){
			String hql = "from ShopCustomers as model where model.shopId = " + Integer.parseInt(user.getParentId());
			if(selectValue!=null && !selectValue.equals("")){
				hql+="and model.shopCustomerName like '%" +selectValue+"%' or model.shopCustomerPhone like '%" +selectValue+"%'";
			}
			List<ShopCustomers> list = baseDao.findByHql(hql,page.getStart(),page.getLimit());
			for(ShopCustomers sc : list){
				User u = (User) baseDao.loadById(User.class, sc.getUserId());
				String cGRSHql = "select count(*) from GuideRecords where shopCustomerId = '"+sc.getShopCustomerId()+"'";
				String cDealHql = "select count(*) from GuideRecords where shopCustomerId = '"+sc.getShopCustomerId()+"' and isDeal = 1";
				int floorCounts = baseDao.countQuery(cGRSHql);//备案楼盘数
				int dealCounts = baseDao.countQuery(cDealHql);//已成交数
				CustomerManager cm = new CustomerManager();
				CustomerManager cmObj = cm.createCusManObj(sc,u);
				cmObj.setRecords(floorCounts);
				cmObj.setAlreadyDeal(dealCounts);
				cmList.add(cmObj);
			}
			String cHql = "select count(*) "+hql;
			total = baseDao.countQuery(cHql);
		}
		page.setRoot(cmList);
		page.setTotal(total);
	}
	
	@Override
	public Map<String, Object> findCustomerCAndVInfo(String phone, String projectId) {
		
		Map<String, Object> map = new HashMap<>();
		
		//获取认购记录
		String CHql = "from ContractRecords where customerPhone = '" + phone + "' and projectId ='" + projectId + "'";
		List<ContractRecords> conList = baseDao.findByHql(CHql);
		map.put("contractRecords", conList);
		
		//获取到访记录
		String VHql = "select count(*) from VisitRecords where phone ='" + phone + "'";
		Integer visitCount = baseDao.countQuery(VHql);
		map.put("visitCount", visitCount);
		
		return map;
	}
	
	@Override
	public ProjectCustomers findProjectCustomersByPhone(String phone) {
		
		String hql = "select * from ProjectCustomers where phone projectCustomerPhone = '" + phone + "'";
		ProjectCustomers customer = (ProjectCustomers) baseDao.loadObject(hql);
		return customer;
	}
	
	@Override
	public List<Mydynamic> findCustomerDynamicByCustomerId(String customerId, String customerType) {
		String dynamicHql = "from Mydynamic where " + customerType + " = '" + customerId + "' order by asc";
		List<Mydynamic> list = baseDao.findByHql(dynamicHql);
		return list;
	}
	
	/**
	 * 2017-08-28 grl check
	 */
	@Override
	public Map<String, Object> addCustomerExcell(MultipartFile file, String projectId) {
		Map<String, Object> map = new HashMap<>();
		//更新的用户
		List<ProjectCustomers> updateList = new ArrayList<>();
		//新添的用户
		List<ProjectCustomers> newList = new ArrayList<>();
		Integer addFlag = 0;
		Integer updateFlag = 0;
		try {
			String sepa = File.separator;
			String realPath = SysContent.getSession().getServletContext().getRealPath("/static/upload");
			String fileRename = SysContent.getFileRename(file.getOriginalFilename());
			String fileRpn = realPath + sepa + fileRename;
			File importFile = new File(fileRpn);
			FileUtils.copyInputStreamToFile(file.getInputStream(), importFile);
			ExcelHelper exh = JxlExcelHelper.getInstance(importFile);
			String[] fieldNames = new String[]{"lastTime", "projectCustomerName", "projectCustomerPhone","morePhoneNum","createUserId"};
			List<ProjectCustomers> list = exh.readExcel(ProjectCustomers.class, fieldNames, true);
			/*如果导入的客户是同一个人那么就不重复导入 而是更新*/
			for(ProjectCustomers cus : list){
				if(!StringUtils.isEmpty(cus.getProjectCustomerName()) && !StringUtils.isEmpty(cus.getProjectCustomerPhone())){
					//首先查找数据库是否已经存在这个客户，并且最后到达案场的时间必须大于之前到达的时间
					String customerHql = "from ProjectCustomers where projectCustomerName = '" + cus.getProjectCustomerName() + "' and projectCustomerPhone = '" + cus.getProjectCustomerPhone() + "' and projectId = '" + projectId + "'";
					ProjectCustomers customer = (ProjectCustomers) baseDao.loadObject(customerHql);
					//查找当前客户的创建人
					User user = (User) baseDao.loadObject("from User where parentId = '" + projectId + "' and userCaption = '" + cus.getCreateUserId().trim()+ "'");
					if(customer != null){ // is not null prove this customer is exist
						//创建到访对象
						VisitRecords visit = new VisitRecords();
						//接访时间
						String lastTime = DateUtil.format(DateUtil.parse(cus.getLastTime(),DateUtil.PATTERN_GRACE_SIMPLE));
						DateUtil.addDateMinut(lastTime, 8);
						Date lastDate = DateUtil.parse(lastTime);
						Date oldLastTime = DateUtil.parse(customer.getLastTime());
						//if(oldLastTime.getTime() < lastDate.getTime()){
						String comeTime = DateUtil.format(DateUtil.parse(cus.getLastTime(),DateUtil.PATTERN_GRACE_SIMPLE),DateUtil.PATTERN_CLASSICAL_SIMPLE);
						//customer.setLastTime(DateUtil.addDateMinut(comeTime, 8));
						customer.setLastTime(comeTime+" 09:00:00");
						//判断客户创建时间和导入到访时间的前后，如果客户的创建时间比导入数据的到访时间大就更新客户创建时间为最早时间
						if(DateUtil.parse(customer.getCreateTime()).getTime()>DateUtil.parse(customer.getLastTime()).getTime()){
							customer.setCreateTime(customer.getLastTime());
						}
						if(cus.getMorePhoneNum()!=null && !cus.getMorePhoneNum().equals("")){
							customer.setMorePhoneNum(cus.getMorePhoneNum());
							visit.setMorePhoneNum(cus.getMorePhoneNum());
						}
						baseDao.saveOrUpdate(customer);
						//重复数据导入是防止同时间到访数据重复添加
						String havaThisTimeVsHql = "FROM VisitRecords WHERE projectId = '"+projectId+"' and userId = '"+user.getUserId()+"' and "
								+ "customerId = '"+customer.getProjectCustomerId()+"' and arriveTime = '"+customer.getLastTime()+"' and leaveTime = '"+comeTime+" 10:00:00"+"'"; 
						VisitRecords vrs = (VisitRecords) baseDao.loadObject(havaThisTimeVsHql);
						//进行到访表的操作
						if(vrs==null){
							visit.setUserId(user.getUserId());//置业顾问
							visit.setProjectId(projectId);
							visit.setCustomerName(customer.getProjectCustomerName());
							visit.setPhone(customer.getProjectCustomerPhone());
							visit.setCustomerCount(1);
							visit.setVisitStatus(3);//状态为送别
							visit.setArriveTime(comeTime+" 09:00:00");//到访时间
							visit.setReceptTime(comeTime+" 09:00:00");//接访时间
							visit.setLeaveTime(comeTime+" 10:00:00");//送别时间
							visit.setAppointUserId(user.getUserId());
							visit.setCustomerId(customer.getProjectCustomerId());
							visit.setWriteState(1);
							visit.setIsNew(false);
							baseDao.save(visit);
						}
						updateFlag += 1;
						updateList.add(customer);
					}else{// is null prove this customer is new customer
						String name = cus.getCreateUserId();
						cus.setProjectCustomerId(SysContent.uuid());
						cus.setCreateUserId(user.getUserId());
						cus.setOwnerUserId(user.getUserId());
						cus.setProjectId(projectId);
						//String comeTime = DateUtil.format(DateUtil.parse(cus.getLastTime(),DateUtil.PATTERN_GRACE_SIMPLE));
						String comeTime = DateUtil.format(DateUtil.parse(cus.getLastTime(),DateUtil.PATTERN_GRACE_SIMPLE),DateUtil.PATTERN_CLASSICAL_SIMPLE);
						cus.setCreateTime(comeTime+" 09:00:00");
						cus.setLastTime(comeTime+" 09:00:00");
						cus.setOwnerStartTime(comeTime+" 09:00:00");
						baseDao.save(cus);
						addFlag += 1;
						//进行到访表的操作
						VisitRecords visit = new VisitRecords();
						if(cus.getMorePhoneNum()!=null && !cus.getMorePhoneNum().equals("")){
							visit.setMorePhoneNum(cus.getMorePhoneNum());
						}
						visit.setUserId(user.getUserId());
						visit.setProjectId(projectId);
						visit.setVisitStatus(3);//状态为送别
						visit.setCustomerCount(1);
						visit.setCustomerName(cus.getProjectCustomerName());
						visit.setPhone(cus.getProjectCustomerPhone());
						visit.setArriveTime(comeTime+" 09:00:00");
						visit.setReceptTime(comeTime+" 09:00:00");
						visit.setLeaveTime(comeTime+" 10:00:00");
						//visit.setAppointUserId(user.getUserId());
						visit.setCustomerId(cus.getProjectCustomerId());
						visit.setWriteState(1);
						visit.setIsNew(true);
						baseDao.save(visit);
						newList.add(cus);
					}
				}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("updateCustomer", updateList);
		map.put("newCustomer", newList);
			return map;
	}

	@Override
	public Map<String,Object> searchProjectCoustomers(Integer[] coustomerStatus, Integer[] visitNum,
			String[] projectAgentId, Integer[] tagIds, String projectId, int page, int num) {
		Map<String, Object> result = new HashMap<>();
		int start = (page-1) *num;
		String finalSql = null;
		if((visitNum==null || visitNum.length==0) && (projectAgentId==null || projectAgentId.length==0) && (coustomerStatus==null || coustomerStatus.length==0)){
			//除了标签外没有搜索条件
			finalSql = "SELECT * FROM t_projectcustomers WHERE projectId = '"+projectId+"'";
		}
		StringBuilder sb = new StringBuilder("SELECT * FROM t_projectcustomers as pc WHERE pc.projectId='"+projectId+"'");
		StringBuilder sql1 = new StringBuilder();
		if(projectAgentId!=null && projectAgentId.length!=0){//含有归属顾问条件
			sql1.append("SELECT projectCustomerPhone FROM t_projectcustomers WHERE ownerUserId IN (");
			for(int i = 0;i<projectAgentId.length;i++){
				if(i!=projectAgentId.length-1){
					sql1.append("'"+projectAgentId[i]+"',");
				}else{
					sql1.append("'"+projectAgentId[i]+"')");
				}
			}
		}
		StringBuilder sql2 = new StringBuilder();
		StringBuilder sql3 = new StringBuilder();
		if(coustomerStatus!=null && coustomerStatus.length!=0){//含有客户状态条件
			StringBuilder sql6 = new StringBuilder();
			int flag = 0;
			for(int i=0;i<coustomerStatus.length;i++){
				if(coustomerStatus[i].equals(1)){//到访
					sql3.append("SELECT DISTINCT phone FROM t_visitrecords WHERE projectId='"+projectId+"'");
				}else{
					if(flag==0){
						sql2.append("SELECT DISTINCT customerPhone FROM t_contractrecords as ct WHERE ct.projectId='"+projectId+"'");
					}
					if(coustomerStatus[i].equals(2)){//认购 1,0
						if(flag==0){
							sql6.append(" (ct.recordStatus=0 OR ct.recordStatus=1)");
						}else{
							sql6.append(" OR (ct.recordStatus=0 OR ct.recordStatus=1)");
						}
						flag++;
					}
					if(coustomerStatus[i].equals(3)){//付款 4
						if(flag==0){
							sql6.append(" ct.recordStatus=4");
						}else{
							sql6.append(" OR ct.recordStatus=4");
						}
						flag++;
					}
					if(coustomerStatus[i].equals(4)){//签约 5
						if(flag==0){
							sql6.append(" ct.recordStatus=5");
						}else{
							sql6.append(" OR ct.recordStatus=5");
						}
						flag++;
						
					}
					if(coustomerStatus[i].equals(5)){ //认购否决 3
						if(flag==0){
							sql6.append(" ct.recordStatus=3");
						}else{
							sql6.append(" OR ct.recordStatus=3");
						}
						flag++;
						
					}
					if(coustomerStatus[i].equals(6)){ //退款 6
						if(flag==0){
							sql6.append(" ct.recordStatus=6");
						}else{
							sql6.append(" OR ct.recordStatus=6");
						}
						flag++;
						
					}
					if(coustomerStatus[i].equals(7)){ //撤销6
						if(flag==0){
							sql6.append(" ct.recordStatus=7");
						}else{
							sql6.append(" OR ct.recordStatus=7");
						}
						flag++;
						
					}
				}
				
			}
			if(!StringUtils.isEmpty(sql2.toString())){
				sql2.append(" AND (");
				sql2.append(sql6);
				sql2.append(")");
			}
		}
		StringBuilder sql4 = new StringBuilder();
		if(visitNum!=null && visitNum.length!=0){//含有到访次数条件
			sql4.append("SELECT phone FROM t_visitrecords");
			StringBuilder sql5 = new StringBuilder(" GROUP BY phone HAVING COUNT(*)");
			for(int i = 0;i<visitNum.length;i++){
				if(i==visitNum.length-1){
					if(visitNum[i]==3){
						sql5.append(">=");
						sql5.append(visitNum[i]);
					}else{
						sql5.append("=");
						sql5.append(visitNum[i]);
					}
				}else{
					if(visitNum[i]==3){
						sql5.append(">=");
						sql5.append(visitNum[i]+" OR COUNT(*)");
					}else{
						sql5.append("=");
						sql5.append(visitNum[i]+" OR COUNT(*)");
					}
				}
			}
			sql4.append(sql5);
		}
		if(!StringUtils.isEmpty(sql1.toString())){//归属顾问
				sb.append(" AND pc.projectCustomerPhone IN(");
				sb.append(sql1);
				sb.append(" )");
		}
		if(!StringUtils.isEmpty(sql2.toString())){//状态 
			sb.append(" AND pc.projectCustomerPhone IN(");
			sb.append(sql2);
			sb.append(" )");
		}
		if(!StringUtils.isEmpty(sql3.toString())){//到访
			sb.append(" AND pc.projectCustomerPhone IN(");
			sb.append(sql3);
			sb.append(" )");
		}
		if(!StringUtils.isEmpty(sql4.toString())){//到访次数
			sb.append(" AND pc.projectCustomerPhone IN(");
			sb.append(sql4);
			sb.append(" )");
		}
		if(StringUtils.isEmpty(finalSql)){
			finalSql = sb.toString();
		}
		System.out.println(finalSql);
		StringBuilder lastSql = null;
		StringBuilder lastSqlCount = null;
		boolean f = false;
		if(tagIds!=null && tagIds.length!=0){//有标签search条件
			f = true;
			Map<Integer,List<Integer>> map1 = new HashMap<>();
			for(Integer tagId : tagIds){
				Tag tag = (Tag)baseDao.loadObject("from Tag where tagId = "+tagId);
				Integer tagTypeId = tag.getTagTypeId();
				List<Integer> list = map1.get(tagTypeId);
				if(list==null || list.size()==0){
					list = new ArrayList<Integer>();
					map1.put(tagTypeId, list);
				}
				list.add(tagId);
			}
			String sqlCount = "SELECT count(*) FROM ("+finalSql+") AS u LEFT JOIN t_tagsrelation AS t ON t.targetId = u.projectCustomerId WHERE ";
			String sql = "SELECT u.projectCustomerId,u.projectCustomerName,u.projectCustomerPhone,u.ownerUserId,t.tags FROM ("+finalSql+") AS u LEFT JOIN t_tagsrelation AS t ON t.targetId = u.projectCustomerId WHERE ";
			lastSql = new StringBuilder(sql);
			lastSqlCount = new StringBuilder(sqlCount);
			Collection<List<Integer>> values = map1.values();
			for(List<Integer> value : values){
				for(int i = 0;i<value.size();i++){
					if(i==0){
						lastSql.append(" (t.tags LIKE '%"+value.get(i)+"%' ");
						lastSqlCount.append(" (t.tags LIKE '%"+value.get(i)+"%' ");
					}else{
						lastSql.append(" OR t.tags LIKE '%"+value.get(i)+"%' ");
						lastSqlCount.append(" OR t.tags LIKE '%"+value.get(i)+"%' ");
					}
				}
				lastSql.append(" ) AND ");
				lastSqlCount.append(" ) AND ");
			}
			lastSql = new StringBuilder(lastSql.substring(0, lastSql.lastIndexOf("AND")));
			lastSqlCount = new StringBuilder(lastSqlCount.substring(0, lastSqlCount.lastIndexOf("AND")));
		}else{
			lastSql = new StringBuilder("SELECT u.projectCustomerId,u.projectCustomerName,u.projectCustomerPhone,u.ownerUserId,t.tags FROM ("+finalSql+" ORDER BY createTime LIMIT "+start+","+num+") AS u LEFT JOIN t_tagsrelation AS t ON t.targetId = u.projectCustomerId");
			lastSqlCount = new StringBuilder("SELECT count(*) FROM ("+finalSql+") AS u LEFT JOIN t_tagsrelation AS t ON t.targetId = u.projectCustomerId");
		}
		if(f){
			lastSql.append(" ORDER BY u.createTime LIMIT "+start+","+num);
		}else{
			lastSql.append(" ORDER BY u.createTime ");
		}
		System.out.println("-------------------------"+lastSql);
		System.out.println(lastSqlCount);
		List<CustomerManagerDTO> pcs = baseDao.queryDTOBySql(lastSql.toString(), CustomerManagerDTO.class, new String[]{"projectCustomerId","projectCustomerName","projectCustomerPhone","ownerUserId","tags"}, new String[]{"String","String","String","String","String"});
		int count = baseDao.findCountBySql(lastSqlCount.toString());
		if(pcs!=null && pcs.size()>0){
			for(CustomerManagerDTO cDTO : pcs){
				//获取归属顾问
				String ownerUserId = cDTO.getOwnerUserId();
				if(StringUtils.isEmpty(ownerUserId)){
					cDTO.setOwnerUserName("未知");
				}else{
					User user = (User)baseDao.loadObject("from User where userId = '"+ownerUserId+"'");
					if(user!=null){
						cDTO.setOwnerUserName(user.getUserCaption());
					}else{
						cDTO.setOwnerUserName("未知");
					}
				}
				//获取标签的值
				List<String> tagsList = new ArrayList<>();
				String tags = cDTO.getTags();
				if(!StringUtils.isEmpty(tags)){
					String[] split = tags.split(",");
					for(int i = 0;i<split.length;i++){
						if(i==4){
							break;
						}
						Tag tag = (Tag)baseDao.loadObject("from Tag where tagId ="+split[i]);
						tagsList.add(tag.getTagName());
					}
				}
				cDTO.setTagsList(tagsList);
				//获取客户状态
				String phone = cDTO.getProjectCustomerPhone();
				List<ContractRecords> crs = baseDao.findByHql("from ContractRecords where projectId = '"+projectId+"' and customerPhone = '"+phone+"' ORDER BY applyTime DESC");
				if(crs!=null && crs.size()!=0){
					ContractRecords contractRecords = crs.get(0);
					//dto状态(1-已到访,2-认购,3-付款,4-签约,5-认购否决,6-退款,7-撤单)
					//订单状态,0:申请,1:同意,2:删除,3:否决,4:到款,5:签约,6:退款,7:撤销
					Integer recordStatus = contractRecords.getRecordStatus();
					if(recordStatus!=null){
						switch (recordStatus) {
						case 0:
							cDTO.setStatus("认购");
							break;
						case 1:
							cDTO.setStatus("认购");
							break;
						case 2:
							break;
						case 3:
							cDTO.setStatus("认购否决");
							break;
						case 4:
							cDTO.setStatus("付款");
							break;
						case 5:
							cDTO.setStatus("签约");
							break;
						case 6:
							cDTO.setStatus("退款");
							break;
						case 7:
							cDTO.setStatus("撤单");
							break;
						}
						
					}
				}
				List<VisitRecords> findByHql = baseDao.findByHql("from VisitRecords where phone = '"+phone+"' and projectId = '"+projectId+"'");
				if(findByHql!=null && findByHql.size()>0){
					if(StringUtils.isEmpty(cDTO.getStatus())){
						cDTO.setStatus("已到访");
					}
					cDTO.setVisitTimes(findByHql.size());
				}else{
					if(StringUtils.isEmpty(cDTO.getStatus())){
						cDTO.setStatus("已到访");
					}
					cDTO.setVisitTimes(0);
				}
			}
			
		}
		int pageAll = 0;
		if(count%num==0){
			pageAll = count/num;
		}else{
			pageAll = count/num + 1;
		}
		result.put("count", pageAll);
		result.put("resultList", pcs);
		return result;
	}

	@Override
	public int update_projectCustomerOwner(String original, String neo) throws Exception {
		List<ProjectCustomers> pcs = baseDao.findByHql("from ProjectCustomers where ownerUserId = '"+original+"'");
		if(pcs!=null && pcs.size()>0){
			for(ProjectCustomers pc : pcs){
				String string1 = DateTime.toString1(new Date());
				pc.setOwnerUserId(neo);
				pc.setOwnerStartTime(string1);
				try{
					baseDao.saveOrUpdate(pc);
				}catch (Exception e) {
					throw new RuntimeException("网络繁忙");
				}
			}
			return pcs.size();
		}else{
			throw new RuntimeException("该顾问名下没有客户");
		}
		
		
	}

	@Override
	public void update_projectCustomerOwner(String[] projectCustomerId, String agentId) throws Exception {
		if(projectCustomerId!=null && projectCustomerId.length>0){
			for(String s : projectCustomerId){
				ProjectCustomers pc = (ProjectCustomers)baseDao.loadObject("from ProjectCustomers where projectCustomerId = '"+s+"'");
				if(pc!=null){
					try {
						String string1 = DateTime.toString1(new Date());
						pc.setOwnerUserId(agentId);
						pc.setOwnerStartTime(string1);
						baseDao.saveOrUpdate(pc);
					} catch (Exception e) {
						throw new RuntimeException("网络异常");
					}
				}else{
					throw new RuntimeException("参数异常");
				}
			}
		}
		
	}

	@Override
	public CustomerManagerInfoDTO findOneCustomerInfo(String projectCustomerId) {
		CustomerManagerInfoDTO dto = new CustomerManagerInfoDTO();
		ProjectCustomers pc = (ProjectCustomers)baseDao.loadObject("from ProjectCustomers where projectCustomerId = '"+projectCustomerId+"'");
		if(pc!=null){
			String projectCustomerName = pc.getProjectCustomerName();
			String projectCustomerPhone = pc.getProjectCustomerPhone();
			String idCard = pc.getIdCard();
			String job = pc.getJob();
			String ownerUserId = pc.getOwnerUserId();
			Integer sex = pc.getSex();
			if(sex!=null){
				switch (sex.intValue()) {
				case 0:
					dto.setSex(0);
					break;
				case 1:
					dto.setSex(1);
					break;
				case 2:
					dto.setSex(2);
					break;
				}
			}else{
				dto.setSex(0);
			}
			dto.setProjectCustomerName(StringUtils.isEmpty(projectCustomerName)?"未知":projectCustomerName);
			dto.setProjectCustomerPhone(StringUtils.isEmpty(projectCustomerPhone)?"未知":projectCustomerPhone);
			dto.setIdCard(StringUtils.isEmpty(idCard)?"未知":idCard);
			dto.setJob(StringUtils.isEmpty(job)?"未知":job);
			dto.setOwnerUserId(StringUtils.isEmpty(ownerUserId)?"未知":ownerUserId);
			if(!StringUtils.isEmpty(ownerUserId)){
				User user =(User) baseDao.loadObject("from User where userId = '"+ownerUserId+"'");
				dto.setOwnerUserName(StringUtils.isEmpty(user.getUserCaption())?"未知":user.getUserCaption());
			}else{
				dto.setOwnerUserName("未知");
			}
			//经理点评
			String evaluate = appTagManagerService.findEvaluate(projectCustomerId);
			dto.setManagerEvaluate(StringUtils.isEmpty(evaluate)?"暂无":evaluate);
			//客户意向
			String projectId = pc.getProjectId();
			String  intention = appTagManagerService.findCustomerYiXiang(projectCustomerId, projectId);
			dto.setIntention(intention);
			//来访次数
			List<VisitRecords> findByHql = baseDao.findByHql("from VisitRecords where phone = '"+projectCustomerPhone+"' and projectId = '"+projectId+"'");
			if(findByHql!=null){
				dto.setArriveTimes(findByHql.size());
			}else{
				dto.setArriveTimes(0);
			}
			//客户标签
			Map<String, List<Tag>> map = tagService.findTagTypeAndTag2Map(projectCustomerId);
			dto.setOwnerTags(map);
			//客户轨迹
			List<Track> tracks = new ArrayList<>();
			if(!StringUtils.isEmpty(projectCustomerPhone)){
				List<VisitRecords> visit = baseDao.findByHql("from VisitRecords where phone = '"+projectCustomerPhone+"' ORDER BY arriveTime");
				if(visit!=null && visit.size()>0){
					for(int i = 0;i<visit.size();i++){
						Track t = new Track();
						t.setTime(visit.get(i).getArriveTime());
						int times = i+1;
						t.setStatus("第"+times+"次到访");
						tracks.add(t);
					}
				}
				List<ContractRecords> crs = baseDao.findByHql("from ContractRecords where projectId='"+projectId+"' and customerPhone = '"+projectCustomerPhone+"'");
				if(crs!=null && crs.size()>0){
					for(ContractRecords cr : crs){
						Integer recordStatus = cr.getRecordStatus();
						if(recordStatus!=null){
							int intValue = recordStatus.intValue();
							int x = intValue;
							if(intValue==7){
								Track t7 = new Track();
								t7.setTime(cr.getRevokeTime());
								t7.setStatus("撤单");
								tracks.add(t7);
								x = 0;
								String s1 = cr.getAuditingTime();//审核时间
								if(!StringUtils.isEmpty(s1)){
									x = cr.getRecordStatus();
								}
								String s2 = cr.getRemitConfirmTime();//到款时间
								if(!StringUtils.isEmpty(s2)){
									x = 4;
								}
								String s3 = cr.getContractConfirmTime();//签约时间
								if(!StringUtils.isEmpty(s3)){
									x = 5;
								}
								
							}
							switch (x) {
							//状态,0:申请,1:同意,2:删除,3:否决,4:到款,5:签约,6:退款,7:撤销
							case 5:
								Track t5 = new Track();
								t5.setTime(cr.getContractConfirmTime());
								t5.setStatus("签约");
								tracks.add(t5);
							case 4:
								Track t4 = new Track();
								t4.setTime(cr.getRemitConfirmTime());
								t4.setStatus("定金到款");
								tracks.add(t4);
							case 1:case 3:
								Track t1 = new Track();
								t1.setTime(cr.getAuditingTime());
								if(intValue==3){
									t1.setStatus("订单审核拒绝");
								}else{
									t1.setStatus("订单审核通过");
								}
								tracks.add(t1);
							case 0:
								Track t0 = new Track();
								t0.setTime(cr.getApplyTime());
								t0.setStatus("认购");
								tracks.add(t0);
								break;
							}
							
						}
					}
				}
			
			}
			Collections.sort(tracks);
			dto.setTracks(tracks);
			//System.out.println(tracks);
		}
		return dto;
	}

	@Override
	public CustomerAnalyze getAnalyze(String projectId) {
		CustomerAnalyze analyze = new CustomerAnalyze();
		Map<String,Integer> histogram = new HashMap<>();
		//饼图
		Map<String,String> pie = new HashMap<>();
		String sql = "SELECT phone,arriveTime FROM t_visitrecords WHERE phone IN(SELECT phone FROM t_visitrecords  WHERE phone is NOT NULL AND phone !='' AND projectId='"+projectId+"' GROUP BY phone HAVING COUNT(*)>1) ORDER BY arriveTime";
		List<VisitRecords> vrs = baseDao.queryDTOBySql(sql, VisitRecords.class, new String[]{"phone","arriveTime"}, new String[]{"String","String"});
		if(vrs!=null && vrs.size()>0){
			List<Integer> dateList = new ArrayList<>();
			Set<String> phones = new HashSet<>();
			for(VisitRecords vr : vrs){
				phones.add(vr.getPhone());
			}
			List<String> phoneList = new ArrayList<>();
			for(String s : phones){
				phoneList.add(s);
			}
			while(phoneList.size()>0){
				String string = phoneList.remove(0);
				List<String> s = new ArrayList<>();
				int i = 1;//第一次
				for(VisitRecords vr : vrs){
					String phone = vr.getPhone();
					if(phone.equals(string)){
						if(i==3){
							break;
						}else{
							Date parse = DateUtil.parse(vr.getArriveTime());
							String format = DateUtil.format(parse, "yyyyMMdd");
							s.add(format);
							i++;
						}
					}
				}
				Date date1 = DateUtil.parse(s.get(0),"yyyyMMdd");
				Date date2 = DateUtil.parse(s.get(1),"yyyyMMdd");
				int x = DateUtil.getOffsetDays(date1, date2);
//				int parseInt1 = Integer.parseInt(s.get(1));
//				int parseInt0 = Integer.parseInt(s.get(0));
//				int x = parseInt1-parseInt0;
				dateList.add(x);
				String key1 = x+"";
				if(x>29){
					key1 = "30";
				}
				String key = key1.trim();
				if(histogram.containsKey(key)){
					Integer integer = histogram.get(key);
					int a = integer.intValue()+1;
					histogram.put(key, a);
				}else{
					histogram.put(key, 1);
				}
				
				
			}
			List<Integer> histogram1 = new ArrayList<>();
			for(int i = 0;i<31;i++){
				Integer integer = histogram.get(i+"");
				if(integer==null){
					histogram1.add(0);
				}else{
					histogram1.add(integer);
				}
				
			}
			analyze.setHistogram(histogram1);
			//设置饼图
			//第一周
			int num1 = 0;
			for(int i = 0;i<7;i++){
				String key1 = i+"";
				String key = key1.trim();
				Integer integer = histogram.get(key);
				if(integer!=null){
					num1+=integer.intValue();
				}
					
			}
			pie.put(1+"", num1+"");
			//第二周
			int num2 = 0;
			for(int i = 7;i<14;i++){
				String key1 = i+"";
				String key = key1.trim();
				Integer integer = histogram.get(key);
				if(integer!=null){
					num2+=integer.intValue();
				}
			}
			pie.put(2+"", num2+"");
			//第三周
			int num3 = 0;
			for(int i = 14;i<21;i++){
				String key1 = i+"";
				String key = key1.trim();
				Integer integer = histogram.get(key);
				if(integer!=null){
					num3+=integer.intValue();
				}
			}
			pie.put(3+"", num3+"");
			//三周以后
			int num4 = 0;
			for(int i = 21;i<31;i++){
				String key1 = i+"";
				String key = key1.trim();
				Integer integer = histogram.get(key);
				if(integer!=null){
					num4+=integer.intValue();
				}
			}
			pie.put(4+"", num4+"");
			analyze.setPie(pie);
			//设置数据
			int projectCNum = baseDao.findCountBySql("SELECT COUNT(*) FROM t_projectcustomers WHERE projectId='"+projectId+"'");
			Map<String,Object> data = new HashMap<>();
			Collections.sort(dateList);
			//{onceOne90=, turnBack=0.15, oneTwo75=, mostD=0, fastest=0, mostP=42.86%, oneTwo90=, turnBackByDate=6.59, latest=508, onceOne75=}
			data.put("fastest", dateList.get(0)+"");//最快回访时间
			data.put("latest", dateList.get(dateList.size()-1)+"");//最晚回访时间
			data.put("onceOne90", "");//90% -1
			data.put("oneTwo90", "");//90% -2
			data.put("onceOne75", "");// 75% -1
			data.put("oneTwo75", "");// 75% -2
			List<String> mostDList = new ArrayList<>();
			int maxNum = -1;
			double numCount = num1+num2+num3+num4;//回访总人数
			for(String key : histogram.keySet()){
				Integer integer = histogram.get(key);
				if(integer.intValue()>maxNum){
					maxNum = integer.intValue();
				}
			}
			for(String s : histogram.keySet()){
				if(histogram.get(s).intValue()==maxNum){
					mostDList.add(s);
				}
			}
			double p = maxNum/numCount;
			p = p*100;
			String mostp = SysContent.get2Double(p);
			data.put("mostP", mostp+"%");//最多回头客的百分比
			/*for(int i = 0;i<mostDList.size();i++){
				data.put("mostD"+i, mostDList.get(i));//最多回头客的天数
			}*/
			data.put("mostD", mostDList);
			Integer integer = histogram.get("30");
			if(integer==null){
				integer = 0;
			}
			double doubleValue = integer.doubleValue();
			double pp = doubleValue/projectCNum;
			pp = pp*100;
			String turnBackByDate = SysContent.get2Double(pp);
			data.put("turnBackByDate", turnBackByDate+"%");//29天以后的回头率
			double ppp = numCount/projectCNum;
			ppp = ppp*100;
			String turnBack = SysContent.get2Double(ppp);
			data.put("turnBack", turnBack+"%");//回头率
			analyze.setData(data);
		}else{//没有到访
			List<Integer> histogram1 = new ArrayList<>(30);//柱状图
			for(int i=0;i<31;i++){
				histogram1.add(0);
			}
			Map<String,Object> data = new HashMap<>();
			analyze.setHistogram(histogram1);
			pie.put(1+"", 0+"");
			pie.put(2+"", 0+"");
			pie.put(3+"", 0+"");
			pie.put(4+"", 0+"");
			analyze.setPie(pie);
			data.put("fastest", "无");//最快回访时间
			data.put("latest", "无");//最晚回访时间
			data.put("onceOne90", "");//90% -1
			data.put("oneTwo90", "");//90% -2
			data.put("onceOne75", "");// 75% -1
			data.put("oneTwo75", "");// 75% -2
			data.put("mostP", "0%");
			List<String> mostDList = new ArrayList<>();
			mostDList.add("0");
			data.put("mostD", mostDList);
			data.put("turnBackByDate", "0%");//29天以后的回头率
			data.put("turnBack", "0%");//回头率
			analyze.setData(data);
		}
		return analyze;
	}

	@Override
	public Map<String, Object> searchProjectCoustomers(String projectId, String srarchCondition, int page, int num) {
		Map<String, Object> result = new HashMap<>();
		int start = (page-1) *num;
		StringBuilder sql = new StringBuilder();
		try {
			Long.parseLong(srarchCondition);
			//电话号码
			String sql1 = "SELECT * FROM t_projectcustomers WHERE projectId='"+projectId+"' AND projectCustomerPhone LIKE '%"+srarchCondition+"%'";
			sql.append(sql1);
		} catch (Exception e) {
			// 姓名搜索
			String sql2 = "SELECT * FROM t_projectcustomers WHERE projectId='"+projectId+"' AND projectCustomerName LIKE '%"+srarchCondition+"%'";
			sql.append(sql2);
		}
		String sqlCount = "SELECT count(*) FROM ("+sql+") AS u LEFT JOIN t_tagsrelation AS t ON t.targetId = u.projectCustomerId";
		String finalSql = "SELECT u.projectCustomerId,u.projectCustomerName,u.projectCustomerPhone,u.ownerUserId,t.tags FROM ("+sql+") AS u LEFT JOIN t_tagsrelation AS t ON t.targetId = u.projectCustomerId LIMIT "+start+","+num;
		List<CustomerManagerDTO> pcs = baseDao.queryDTOBySql(finalSql.toString(), CustomerManagerDTO.class, new String[]{"projectCustomerId","projectCustomerName","projectCustomerPhone","ownerUserId","tags"}, new String[]{"String","String","String","String","String"});
		int count = baseDao.findCountBySql(sqlCount.toString());
		if(pcs!=null && pcs.size()>0){
			for(CustomerManagerDTO cDTO : pcs){
				//获取归属顾问
				String ownerUserId = cDTO.getOwnerUserId();
				if(StringUtils.isEmpty(ownerUserId)){
					cDTO.setOwnerUserName("未知");
				}else{
					User user = (User)baseDao.loadObject("from User where userId = '"+ownerUserId+"'");
					if(user!=null){
						cDTO.setOwnerUserName(user.getUserCaption());
					}else{
						cDTO.setOwnerUserName("未知");
					}
				}
				//获取标签的值
				List<String> tagsList = new ArrayList<>();
				String tags = cDTO.getTags();
				if(!StringUtils.isEmpty(tags)){
					String[] split = tags.split(",");
					for(int i = 0;i<split.length;i++){
						if(i==4){
							break;
						}
						Tag tag = (Tag)baseDao.loadObject("from Tag where tagId ="+split[i]);
						tagsList.add(tag.getTagName());
					}
				}
				cDTO.setTagsList(tagsList);
				//获取客户状态
				String phone = cDTO.getProjectCustomerPhone();
				List<ContractRecords> crs = baseDao.findByHql("from ContractRecords where projectId = '"+projectId+"' and customerPhone = '"+phone+"' ORDER BY applyTime DESC");
				if(crs!=null && crs.size()!=0){
					ContractRecords contractRecords = crs.get(0);
					//dto状态(1-已到访,2-认购,3-付款,4-签约,5-认购否决,6-退款,7-撤单)
					//订单状态,0:申请,1:同意,2:删除,3:否决,4:到款,5:签约,6:退款,7:撤销
					Integer recordStatus = contractRecords.getRecordStatus();
					if(recordStatus!=null){
						switch (recordStatus) {
						case 0:
							cDTO.setStatus("认购");
							break;
						case 1:
							cDTO.setStatus("认购");
							break;
						case 2:
							break;
						case 3:
							cDTO.setStatus("认购否决");
							break;
						case 4:
							cDTO.setStatus("付款");
							break;
						case 5:
							cDTO.setStatus("签约");
							break;
						case 6:
							cDTO.setStatus("退款");
							break;
						case 7:
							cDTO.setStatus("撤单");
							break;
						}
						
					}
				}
				List<VisitRecords> findByHql = baseDao.findByHql("from VisitRecords where phone = '"+phone+"'");
				if(findByHql!=null && findByHql.size()>0){
					if(StringUtils.isEmpty(cDTO.getStatus())){
						cDTO.setStatus("已到访");
					}
					cDTO.setVisitTimes(findByHql.size());
				}else{
					if(StringUtils.isEmpty(cDTO.getStatus())){
						cDTO.setStatus("已到访");
					}
					cDTO.setVisitTimes(0);
				}
			}
			
		}
		int pageAll = 0;
		if(count%num==0){
			pageAll = count/num;
		}else{
			pageAll = count/num + 1;
		}
		result.put("count", pageAll);
		result.put("resultList", pcs);
		return result;
	}

	@Override
	public List<ProjectCustomers> searchProjectCustomer(String projectId, String srarchCondition) {
		List<ProjectCustomers> result = new ArrayList<>();
		try{
			Long.parseLong(srarchCondition);
			result = baseDao.findByHql("from ProjectCustomers where projectId ='"+projectId+"' and projectCustomerPhone like '%"+srarchCondition+"%'");
		}catch(Exception e){
			result = baseDao.findByHql("from ProjectCustomers where projectId ='"+projectId+"' and projectCustomerName like '%"+srarchCondition+"%'");
		}
		if(result!=null && result.size()>0){
			for(ProjectCustomers p : result){
				String ownerUserId = p.getOwnerUserId();
				if(!StringUtils.isEmpty(ownerUserId)){
					User user =(User) baseDao.loadObject("from User where userId = '"+ownerUserId+"'");
					if(user!=null){
						p.setDescription(user.getUserCaption());
					}else{
						p.setDescription("未知");
					}
				}else{
					p.setDescription("未知");
				}
			}
		}
		return result;
	}

	@Override
	public void matchPhone(String phone, String projectId) {
		ProjectCustomers pc = (ProjectCustomers)baseDao.loadObject("from ProjectCustomers where projectId = '"+projectId+"' and projectCustomerPhone = '"+phone+"'");
		if(pc == null){
			pc = (ProjectCustomers)baseDao.loadObject("from ProjectCustomers where projectId = '"+projectId+"' and morePhoneNum = '"+phone+"'");
		}
		if(pc!=null){
			throw new RuntimeException();
		}
	}

	//通过id获取案场客户,pc为null返回客户信息不为null返回更新后的客户信息
	@Override
	public ProjectCustomers findProjectCustomersById(String projectCustomerId,ProjectCustomers pc) {
		if(projectCustomerId!=null && !projectCustomerId.equals("")){
			ProjectCustomers old = (ProjectCustomers) baseDao.loadById(ProjectCustomers.class, projectCustomerId);
			if(pc!=null){
				old.setProjectCustomerName(pc.getProjectCustomerName());
				old.setDescription(pc.getDescription());
				old.setIdCard(pc.getIdCard());
				baseDao.saveOrUpdate(old);
			}
			return old;
		}else{
			return null;
		}
		
	}

	

}
