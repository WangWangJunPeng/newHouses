package com.sc.tradmaster.service.director.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.Comment;
import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.SignRecords;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.director.ProjectReceiveService;
import com.sc.tradmaster.service.director.impl.dto.AgentOrderDTO;
import com.sc.tradmaster.service.director.impl.dto.Order;
import com.sc.tradmaster.service.director.impl.dto.OrderDynamic;
import com.sc.tradmaster.service.director.impl.dto.ShopsOrderDTO;
import com.sc.tradmaster.service.director.impl.dto.Times;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

import net.sf.json.JSONObject;

@Service("projectReceiveService")
public class ProjectReceiveServiceImpl implements ProjectReceiveService {
	
	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	//获取今日接访数据 经理端 app
	@Override
	public Map findTodayReceiveFirstPageData(User user) {
		Map map = new HashMap<>();
		if(user!=null){
			String toDayStartTime = SysContent.getStartTime();
			String toDayEndTime = SysContent.getEndTime();
			//今日接访
			String toDayRecListHql = "from VisitRecords where 1 = 1 "
					+ " and projectId = '"+user.getParentId()+"' and arriveTime >= '"
					+toDayStartTime+"' and arriveTime <= '"+toDayEndTime+"'";
			List<VisitRecords> list = baseDao.findByHql(toDayRecListHql);
			int counts = list.size();	
			//新客户接访
			/*String newCusVisitSql = "SELECT * FROM "
					+ "(SELECT * , COUNT(*) num FROM t_visitrecords where 1 = 1"
					+ " and projectId = '"+user.getParentId()+"' and receptTime > '"
					+toDayStartTime+"' and ( leaveTime <= '"+toDayEndTime+"' or leaveTime is null ) GROUP BY phone ) m "
					+ "WHERE m.num=1"; */
			String newCusVisitSql = "SELECT * FROM t_visitrecords where 1 = 1"
					+ " and projectId = '"+user.getParentId()+"' and arriveTime > '"
					+toDayStartTime+"' and arriveTime <= '"+toDayEndTime+"' and isNew is not false";
			List newCusVisitCountList = baseDao.queryBySql(newCusVisitSql);
			int newCusVisitCount = newCusVisitCountList.size();
			//老客户接访
			int oldCusVisitCount = counts - newCusVisitCount;
			//有效接访率
			String validVisitHql = "select count(*) from VisitRecords where 1 = 1 and writeState = 1"
					+ " and projectId = '"+user.getParentId()+"' and receptTime > '"
					+toDayStartTime+"' and ( leaveTime <= '"+toDayEndTime+"' or leaveTime is null )" ;
			int validVisitCount = baseDao.countQuery(validVisitHql);
			int validVisitRate = 0;
			if(counts>0){
				validVisitRate = (int)((double)validVisitCount / counts * 100);
			}
 			//今日下订(打款确认的订单及待签约状态)
			String overEnterBuyHql = "select count(*) from ContractRecords where 1=1 and projectId = '"+user.getParentId()+"' and recordStatus = 4"
					+ " and remitConfirmTime >= '"+toDayStartTime+"' and remitConfirmTime <= '"+toDayEndTime+"'";
			int enterPayCounts = baseDao.countQuery(overEnterBuyHql);
			//今日订单总数
			String totalOrderHql = "select count(*) from ContractRecords where 1=1 and projectId = '"+user.getParentId()+"' "
					+ " and applyTime >= '"+toDayStartTime+"' and applyTime <= '"+toDayEndTime+"'";
			int totalOrderCount = baseDao.countQuery(totalOrderHql);
			//已审核的订单
			String alreadyCheckOrderHql = "select count(*) from ContractRecords where 1=1 and projectId = '"+user.getParentId()+"' "
					+ " and auditingTime >= '"+toDayStartTime+"' and auditingTime <= '"+toDayEndTime+"'";
			int alreadyCheckOrderCount = baseDao.countQuery(alreadyCheckOrderHql);
			/*//已审核同意订单
			String alreadyCheckEnterOrderHql = "select count(*) from ContractRecords where 1=1 and recordStatus = 1"
					+ " and auditingTime >= '"+toDayStartTime+"' and auditingTime <= '"+toDayEndTime+"'";
			int alreadyCheckEnterOrderCount = baseDao.countQuery(alreadyCheckEnterOrderHql);
			//已审核否决订单
			String alreadyCheckVetoOrderHql = "select count(*) from ContractRecords where 1=1 and recordStatus = 3"
					+ " and auditingTime >= '"+toDayStartTime+"' and auditingTime <= '"+toDayEndTime+"'";
			int alreadyCheckVetoOrderCount = baseDao.countQuery(alreadyCheckVetoOrderHql);*/
			//今日签约
			String overSignHql = "select count(*) from ContractRecords where 1=1 and recordStatus = 5 and projectId = '"+user.getParentId()+"' "
					+ " and contractConfirmTime > '"+toDayStartTime+"' and contractConfirmTime <= '"+toDayEndTime+"'";
			int toDaySignCount = baseDao.countQuery(overSignHql);
			//付款待签约
			String waitSignHql = "select count(*) from ContractRecords where 1=1 and recordStatus = 4 and projectId = '"+user.getParentId()+"' "
					+ " and voucherUploadTime > '"+toDayStartTime+"' and voucherUploadTime <= '"+toDayEndTime+"'";
			int waitSignCount = baseDao.countQuery(waitSignHql);
			//放弃签约
			String finishSignHql = "select count(*) from ContractRecords where 1=1 and recordStatus = 7 and projectId = '"+user.getParentId()+"' "
					+ " and contractConfirmTime > '"+toDayStartTime+"' and contractConfirmTime <= '"+toDayEndTime+"'";
			int finishSignCount = baseDao.countQuery(finishSignHql);
			//今日签到的职业顾问
			List<String> todayWork = new ArrayList<>();
			//装配今日休息的置业顾问
			List<String> todayRest = new ArrayList<>();
				//①获取所有的案场置业信息
			String userHql = "from User where parentId = '"+user.getParentId()+"' and userStatus = 1";
			List<User> userList = baseDao.findByHql(userHql);
			for(User u : userList){
				Set<Role> rId = u.getRoleId();
				for(Role r : rId){
					if(r.getRoleId()==3){
						//查询置业在今日签到的状况（当天签到，未签退的置业顾问为工作中状态）
						String todaySignHql = "from SignRecords where userId = '"+u.getUserId()+"' "
								+ "and signInTime > '" +toDayStartTime+ "' and signInTime < '"+toDayEndTime+"'  and signOutTime is null";
						SignRecords signUser = (SignRecords) baseDao.loadObject(todaySignHql);
						if(signUser!=null){
							todayWork.add(u.getUserId());
						}else{
							todayRest.add(u.getUserId());
						}
					}
				}
			}
			
			//获取正在接访的
			List receivingList = new ArrayList<>();
			//获取正在等待的
			List waitRecList = new ArrayList<>();
			for(String uId : todayWork){
				String recHql = toDayRecListHql + " and userId = '"+uId+"' and leaveTime is null";//正在接访
				VisitRecords recingVr = (VisitRecords) baseDao.loadObject(recHql);
				if(recingVr!=null){
					receivingList.add(uId);
				}else{
					waitRecList.add(uId);
				}
			}
			//获取正在接访数
			int receivingCount = receivingList.size();
			
			//封装返回数据
			map.put("todayRecCount", counts);
			map.put("newCusVisitNum", newCusVisitCount);
			map.put("oldCusVisitNum", oldCusVisitCount);
			map.put("validVisitRate", validVisitRate+"%");
			map.put("enterBuy", enterPayCounts);
			map.put("orderTotalCount", totalOrderCount);//订单总数
			map.put("alreadyCheckOrderCount", alreadyCheckOrderCount);//已审核的订单
			//map.put("alreadyCheckEnterOrderCount", alreadyCheckEnterOrderCount);
			//map.put("alreadyCheckVetoOrderCount", alreadyCheckVetoOrderCount);
			map.put("signCount", toDaySignCount);
			map.put("waitSignCount", waitSignCount);
			map.put("finishSignCount", finishSignCount);
			map.put("recingCount", receivingCount);//正在接访
			map.put("wait", waitRecList.size());//等待接访
			map.put("rest", todayRest.size());//今日休息
			
		}
		return map;
	}

	//顾问状态数据
	@Override
	public Map<String,Object> findAgentStatusDataById(User user, String userId, String startDate, String endDate,String isCompare) {
		if((startDate==null || startDate.equals("")) && (endDate==null || endDate.equals(""))){
			startDate = SysContent.getStartTime2(new Date());
			endDate = SysContent.getEndTime2(new Date());
		}
		Map<String,Object> map = new HashMap<>();
		if(user!=null){
			String startStr = null;
			String eneStr = null;
			Map<String, String> dateMap = DateUtil.getPastAnyDaysOfDate(new Date(),30);
			startStr = dateMap.get("pastAnydaysStartDay");
			eneStr = dateMap.get("currentDateEndDay");
			//顾问信息
			User agent = (User) baseDao.loadById(User.class, userId);
			String status = "未知";
			//判断该顾问状态(先判断该置业是否休息，没有休息在判断是否正在接待)
			String toDayStartTime = SysContent.getStartTime();
			String toDayEndTime = SysContent.getEndTime();
			String todaySignHql = "from SignRecords where userId = '"+userId+"'"
					+ " and signInTime > '" +toDayStartTime+ "' and signInTime <= '"+toDayEndTime+"' and signOutTime is null";
			SignRecords signUser = (SignRecords) baseDao.loadObject(todaySignHql);
			if(signUser==null){
				status = "休息中";
			}else{
				String toDayRecListHql = "from VisitRecords where userId = '"+userId+"' "
						+ "and projectId = '"+user.getParentId()+"' and receptTime >= '"
						+toDayStartTime+"' and  receptTime <= '"+toDayEndTime+"' and leaveTime is null " ;
				VisitRecords rece = (VisitRecords) baseDao.loadObject(toDayRecListHql);
				if(rece!=null){
					status = "接待中";
				}else{
					status = "等待中";
				}
			}
			map.put("agentPhoto", agent.getPhoto());
			map.put("agentName", agent.getUserCaption());
			map.put("status",status);
			//到访信息,有效接访率
			String validHql = "select count(*) from VisitRecords where writeState = 1 and userId = '"+agent.getUserId()+"' and "
					+ "receptTime >= '"+startDate+"' and receptTime <= '"+endDate+"'";
			String totalHql = "select count(*) from VisitRecords where 1 = 1 and userId = '"+agent.getUserId()+"' and "
					+ "receptTime >= '"+startDate+"' and receptTime <= '"+endDate+"'";
			//新客户组数
			String newCusVisitSql = "SELECT * FROM "
					+ "(SELECT * , COUNT(*) num FROM t_visitrecords where 1 = 1 and writeState = 1"
					+ " and userId = '"+agent.getUserId()+"' and receptTime > '"
					+startDate+"' and ( leaveTime <= '"+endDate+"' or leaveTime is null ) GROUP BY phone ) m "
					+ "WHERE m.num=1";
			//老客户组数
			String oldCusVisitSql = "SELECT * FROM "
					+ "(SELECT * , COUNT(*) num FROM t_visitrecords where 1 = 1 and writeState = 1"
					+ " and userId = '"+agent.getUserId()+"' and receptTime > '"
					+startDate+"' and ( leaveTime <= '"+endDate+"' or leaveTime is null ) GROUP BY phone ) m "
					+ "WHERE m.num>1";
			double valid = baseDao.countQuery(validHql);
			double total = baseDao.countQuery(totalHql);
			String validRate = "0.00";
			List newCusList = baseDao.queryBySql(newCusVisitSql);
			List oldCusList = baseDao.queryBySql(oldCusVisitSql);
			int newCusCount = newCusList.size();
			int oldCusCount = oldCusList.size();
			if(total>0){
				validRate = SysContent.get2Double((valid/total));
			}
			//比较结果（当前大于一个月的结果为1，等于为0，小于为-1）
			int validVisitCompareValue = 0;
			int cValidVisit = 0;
			int cTotalVisit = 0;
			if(startStr != null && eneStr != null){
				//到访信息,有效接访率
				String cValidHql = "select count(*) from VisitRecords where writeState = 1 and userId = '"+agent.getUserId()+"' and "
						+ "receptTime >= '"+startStr+"' and receptTime <= '"+eneStr+"'";
				String cTotalHql = "select count(*) from VisitRecords where 1 = 1 and userId = '"+agent.getUserId()+"' and "
						+ "receptTime >= '"+startStr+"' and receptTime <= '"+eneStr+"'";
				cValidVisit = baseDao.countQuery(cValidHql);
				cTotalVisit = baseDao.countQuery(cTotalHql);
				int cValidVisitRate = 0;
				if(cTotalVisit>0){
					cValidVisitRate = cValidVisit / cTotalVisit * 100;
					if((int)(valid/total)>cValidVisitRate){
						validVisitCompareValue = 1;
					}else if(cValidVisitRate==(int)(valid/total)){
						validVisitCompareValue = 0;
					}else{
						validVisitCompareValue = -1;
					}
				}
			}
			map.put("totalVisit", (int)total);//总接访组数
			map.put("validRate", validRate);//有效接访率
			map.put("validRateCompare",validVisitCompareValue);
			map.put("valid",(int)valid);//有效接访组数
			map.put("unvalid",(int)(total - valid));//无效接访组数
			map.put("newCus", newCusCount);
			map.put("oleCus",oldCusCount);
			// 客户回头率
			// ①获取当前案场的客户
			int newAddCusCount = 0;
			int secondCount = 0;
			int secondRate = 0;
			int cSecondCount = 0;
			int cSecondRate = 0;
			String currentProCusHql = "from ProjectCustomers where projectId = '" + user.getParentId() + "' and ownerUserId = '"+userId+"'";
			List<ProjectCustomers> cusList = baseDao.findByHql(currentProCusHql);
			if (!cusList.isEmpty()) {
				for (ProjectCustomers pc : cusList) {
					String agentVisitHql = "from VisitRecords where 1=1 and customerId = '" + pc.getProjectCustomerId()
							+ "' and projectId = '" + user.getParentId() + "' " + " and receptTime >= '" + startDate
							+ "' and receptTime <= '" + endDate + "' and writeState = 1";
					List<VisitRecords> list = baseDao.findByHql(agentVisitHql);
					if (list.size() == 2) {
						secondCount++;
						VisitRecords vr = list.get(list.size()-1);//获取该到访对象
						if(vr.getIsNew()==null && vr.getIsNew().equals("") && !vr.getIsNew()){
							//oldSecondCount++;
						}
					}
					if(startStr!=null && eneStr!=null){
						String cAgentVisitHql = "from VisitRecords where 1=1 and customerId = '" + pc.getProjectCustomerId()
							+ "' and projectId = '" + user.getParentId() + "' " + " and receptTime >= '" + startStr
							+ "' and receptTime <= '" + eneStr + "' and writeState = 1";
						List<VisitRecords> cList = baseDao.findByHql(cAgentVisitHql);
						if(list.size()==2){
							cSecondCount++;
						}
					}
					if(list.size() == 1){
						newAddCusCount++;
					}
				}
			}
			if(valid>0){
				secondRate = (int) (secondCount / valid * 100);
			}
			int cSecondRateCompareValue = 0;
			if(cValidVisit>0){
				cSecondRate =  cSecondCount/ cValidVisit * 100;
				if(secondRate>cSecondRate){
					cSecondRateCompareValue = 1;
				}else if(cSecondRate == secondRate){
					cSecondRateCompareValue = 0;
				}else{
					cSecondRateCompareValue = -1;
				}
			}
			//总储客数
			map.put("totalSaveCusCount", cusList.size());
			//回头率
			map.put("secondRate", secondRate+"%");
			//回头率比较
			map.put("secondRateCompare", cSecondRateCompareValue);
			//新增客户数
			map.put("newAddCusCount", newAddCusCount);
			//新增二次来访
			map.put("secondCount", secondCount);
			//认购信息
			String getMoneyHql = "select sum(haveToPayMoney) from ContractRecords where userId = '"+userId+"'";
			String money = baseDao.findSum(getMoneyHql);
			//String getMoney = SysContent.get2Double(money);
			map.put("getMoney", money);
			//新老用户
			int newCus = 0;
			int oldCus = 0;
			String newOleUserHql = "from ContractRecords where userId = '"+userId+"'";
			List<ContractRecords> crList = baseDao.findByHql(newOleUserHql);
			for(ContractRecords cr : crList){
				if(cr.getProjectCustomerId()!=null){
					//查询是否是老客户
					String visitHql = "from VisitRecords where customerId = '"+cr.getProjectCustomerId()+"'";
					List<VisitRecords> vrs = baseDao.findByHql(visitHql);
					if(vrs.size()==1){
						newCus++;
					}else{
						oldCus++;
					}
				}else{
					newCus++;
				}
			}
			map.put("new", newCus);
			map.put("oldCus", oldCus);
			//成交数据
			String buyMoneyHql = "select sum(buyPrice) from ContractRecords where recordStatus = 5 and userId = '"+userId+"'";
			String buyTotalMoney = baseDao.findSum(buyMoneyHql);
			//String totalMoney = SysContent.get2Double(buyTotalMoney);
			map.put("money",buyTotalMoney);
			//下定数
			String areadlyPayHql = "select count(*) from ContractRecords where 1=1 and recordStatus = 4 and userId = '"+userId+"' "
					+ " and voucherUploadTime > '"+startDate+"' and voucherUploadTime<= '"+endDate+"'";
			int areadlyPayCount = baseDao.countQuery(areadlyPayHql);
			//已签约
			String orealdySign = "select count(*) from ContractRecords where recordStatus = 5 and userId = '"+userId+"' "
					+ " and contractConfirmTime > '"+startDate+"' and contractConfirmTime<= '"+endDate+"'";
			int cPaySignRate = 0;
			int paySignRateCompareValue = 0;
			if(startStr!=null && eneStr!=null){
				//下定数
				String cAreadlyPayHql = "select count(*) from ContractRecords where 1=1 and userId = '"+userId+"' "
						+ " and voucherUploadTime > '"+startStr+"' and voucherUploadTime<= '"+eneStr+"'";
				int cAreadlyPayCount = baseDao.countQuery(cAreadlyPayHql);
				//已签约
				String cOrealdySign = "select count(*) from ContractRecords where recordStatus = 5 and userId = '"+userId+"' "
						+ " and contractConfirmTime > '"+startStr+"' and contractConfirmTime<= '"+eneStr+"'";
				int cOrealdySignCount = baseDao.countQuery(cOrealdySign);
				if(cOrealdySignCount>0){
					cPaySignRate = cOrealdySignCount / cAreadlyPayCount * 100;
				}
			}
			int orealdySignCount = baseDao.countQuery(orealdySign);
			int paySignRate = 0;
			if(areadlyPayCount>0){
				paySignRate = orealdySignCount / areadlyPayCount * 100;
			}
			if(paySignRate>cPaySignRate){
				paySignRateCompareValue = 1;
			}else if(paySignRate==cPaySignRate){
				paySignRateCompareValue = 0;
			}else{
				paySignRateCompareValue = -1;
			}
			map.put("dealCount", orealdySignCount);//成交数
			map.put("paySignRate", paySignRate);//下定签约率
			map.put("paySignRateCompare", paySignRateCompareValue);//下定率比较
			map.put("areadlyPayCount", areadlyPayCount);//下定组数
			map.put("sign", orealdySignCount);//已签约数
			//等待签约
			String waitSignHql = "select count(*) from ContractRecords where recordStatus = 4 and userId = '"+userId+"'";
			int waitSignCount = baseDao.countQuery(waitSignHql);
			map.put("waitSign", waitSignCount);
			//放弃签约
			String overSignHql = "select count(*) from ContractRecords where recordStatus = 7 and userId = '"+userId+"' "
					+ " and remitConfirmTime is not null";
			int overSignCount = baseDao.countQuery(overSignHql);
			map.put("overSign", overSignCount);
			//放弃认购 	
			String overEnterBuy = "select count(*) from ContractRecords where recordStatus = 7 and userId = '"+userId+"' "
					+ " and voucherUploadTime is null";
			int overEnterBuyCount = baseDao.countQuery(overEnterBuy);
			map.put("overEnterBuy",overEnterBuyCount);
		}
		//System.out.println(map);
		return map;
	}
	
	@Override
	public Map findAgentStatusDataById(User user, String userId, String startDate, String endDate) {
		if((startDate==null || startDate.equals("")) && (endDate==null || endDate.equals(""))){
			startDate = SysContent.getStartTime2(new Date());
			endDate = SysContent.getEndTime2(new Date());
		}
		Map<String,Object> map = new HashMap<>();
		if(user!=null){
			String startStr = null;
			String eneStr = null;
			Map<String, String> dateMap = DateUtil.getPastAnyDaysOfDate(new Date(),30);
			startStr = dateMap.get("pastAnydaysStartDay");
			eneStr = dateMap.get("currentDateEndDay");
			//顾问信息
			User agent = (User) baseDao.loadById(User.class, userId);
			String status = "未知";
			//判断该顾问状态(先判断该置业是否休息，没有休息在判断是否正在接待)
			String toDayStartTime = SysContent.getStartTime();
			String toDayEndTime = SysContent.getEndTime();
			String todaySignHql = "from SignRecords where userId = '"+userId+"'"
					+ " and signInTime > '" +toDayStartTime+ "' and signInTime <= '"+toDayEndTime+"' and signOutTime is null";
			SignRecords signUser = (SignRecords) baseDao.loadObject(todaySignHql);
			if(signUser==null){
				status = "休息中";
			}else{
				String toDayRecListHql = "from VisitRecords where userId = '"+userId+"' "
						+ "and projectId = '"+user.getParentId()+"' and receptTime >= '"
						+toDayStartTime+"' and  receptTime <= '"+toDayEndTime+"' and leaveTime is null " ;
				VisitRecords rece = (VisitRecords) baseDao.loadObject(toDayRecListHql);
				if(rece!=null){
					status = "接待中";
				}else{
					status = "等待中";
				}
			}
			map.put("agentPhoto", agent.getPhoto());
			map.put("agentName", agent.getUserCaption());
			map.put("status",status);
		}
		return map;
	}

	//获取详细接访数据
	@Override
	public Map findToDayDetailedReceiveDataByTime(User user, String startDate, String endDate) {
		Map map = new HashMap<>();
		if(user!=null){
			if(startDate==null || startDate.equals("")){
				startDate = SysContent.getStartTime();
				endDate = SysContent.getEndTime();
			}
			String vistHql = "from VisitRecords where projectId = '"+user.getParentId()+"' and arriveTime >= '"+startDate+"' and arriveTime <= '"+endDate+"'";
			List<VisitRecords> vrList = baseDao.findByHql(vistHql);
			//接访数量
			int recCount = vrList.size();
			long timeDiff = 0L;
			int totalNum = 0;
			double AverageLongTime = 0.00;
			String strAverageLongTime = "0.00";
			//有效到访数
			int validReceCount = 0;
			//有效接访率
			String validRate = "0.00";
			//无效到访
			int unValidReceCount = 0;
			//老客户占比
			int oldCusCount = 0;
			String oldCusRate = "0.00";
			//总替接率
			int replaceCount = 0;
			String replaceRate = "0.00";
			for(VisitRecords vr : vrList){
				if(vr.getLeaveTime()!=null && !vr.getLeaveTime().equals("")){
					long leave = DateUtil.parse(vr.getLeaveTime()).getTime();;
					long recept = DateUtil.parse(vr.getArriveTime()).getTime();
					timeDiff += leave -recept;
					totalNum++;
				}
				if(vr.getWriteState()!=null && !vr.getWriteState().equals("") && vr.getWriteState()==1){
					validReceCount++;
				}else{
					unValidReceCount++;
				}
				//查询是否是老客户
				String visitHql = "from VisitRecords where customerId = '"+vr.getPhone()+"'";
				List<VisitRecords> vrs = baseDao.findByHql(visitHql);
				if(vrs.size()>1){
					oldCusCount++;
				}
				if(vr.getAppointUserId()!=null && !vr.getAppointUserId().equals("") 
						&& vr.getWriteState()!=null && !vr.getWriteState().equals("") && !vr.getUserId().equals(vr.getAppointUserId()) 
						&& vr.getWriteState().equals(1)){
					replaceCount++;
				}
			}
			//平均接待时间
			if(totalNum>0){
				AverageLongTime = timeDiff/totalNum/1000/60;
				strAverageLongTime = SysContent.get2Double(AverageLongTime);
				strAverageLongTime = strAverageLongTime.substring(0, strAverageLongTime.lastIndexOf('.'));
			}
			map.put("recCount", recCount);//接访总数
			map.put("averrageTime", strAverageLongTime);//平均接访时长
			map.put("valid", validReceCount);//有效到访数
			map.put("unValid", unValidReceCount);//无效到访数
			map.put("oldCustomer", oldCusCount);//老客户数
			if(recCount>0){
				 double d =  (double)validReceCount/(double)recCount*100;
				 validRate = SysContent.get2Double(d);
			}
			map.put("validRate", validRate+"%");//有效到访率
			//报备到访率
			String grTotalHql = "select count(*) from GuideRecords where 1=1 and projectId = '"+user.getParentId()+"' and applyTime > '"+startDate+"' and applyTime <= '"+endDate+"'";
			String grVisitedHql = "select count(*) from GuideRecords where 1=1 and applyStatus = 1 and projectId = '"+user.getParentId()+"' and applyTime > '"+startDate+"' and applyTime <= '"+endDate+"'";
			int grVisited = baseDao.countQuery(grVisitedHql);
			int grTotal = baseDao.countQuery(grTotalHql);
			String grVisiteRate = "0.00";
			if(grTotal>0){
				double gd = (double)grVisited/(double)grTotal*100;
				grVisiteRate = SysContent.get2Double(gd);
			}
			map.put("grVisitedRate", grVisiteRate+"%");
			if(recCount>0){
				//老客户占比
				double od = (double)oldCusCount / (double)recCount * 100;
				oldCusRate = SysContent.get2Double(od);
				//总替接率
				double rd = replaceCount/recCount * 100;
				replaceRate = SysContent.get2Double(rd); 
			}
			map.put("oldRate", oldCusRate+"%");
			map.put("replaceRate", replaceRate+"%");
			//平台导客比
			map.put("systemRate", grVisiteRate+"%");
			//客户回头率
				//①获取当前案场的客户
			int secondCount = 0;
			String secondRate = "0.00";
			String currentProCusHql = "from ProjectCustomers where projectId = '"+user.getParentId()+"'";
			List<ProjectCustomers> cusList = baseDao.findByHql(currentProCusHql);
			if(!cusList.isEmpty()){
				for(ProjectCustomers pc : cusList){
					String agentVisitHql = "from VisitRecords where 1=1 and customerId = '"+pc.getProjectCustomerId()+"' and projectId = '"+user.getParentId()+"' "
							+ " and receptTime >= '"+startDate+"' and receptTime <= '"+endDate+"' and writeState = 1";
					List list = baseDao.findByHql(agentVisitHql);
					if(list.size()==2){
						secondCount++;
					}
				}
			}
			if(recCount>0){
				double sd = (double)secondCount / (double)recCount * 100;
				secondRate = SysContent.get2Double(sd);
			}
			map.put("secondRate", secondRate+"%");
		}
		return map;
	}

	//获取详细成交数据
	@Override
	public Map findDealDataByTime(User user, String startDate, String endDate) {
		Map map = new HashMap<>();
		if(user!=null){
			if(startDate==null || startDate.equals("")){
				startDate = SysContent.getStartTime();
				endDate = SysContent.getEndTime();
			}
			//成交数量
			String dealHql = "select count(*) from ContractRecords where 1=1 and (recordStatus = 4 or recordStatus=5) and projectId = '"+user.getParentId()+"'";
			//今日下定
			String enterBuyHql = "select count(*) from ContractRecords where 1=1 and recordStatus = 4 and projectId = '"+user.getParentId()+"'";
			//今日签约
			String signHql = "select count(*) from ContractRecords where 1=1 and recordStatus=5 and projectId = '"+user.getParentId()+"'";
			//下定已锁定房源货值
			String lockedHouseHql = "select sum(buyPrice) from ContractRecords where 1=1 and projectId = '"+user.getParentId()+"' and recordStatus = 4 ";
			//已签约房源货值
			String signedHouseHql = "select sum(buyPrice) from ContractRecords where 1=1 and recordStatus = 5 and projectId = '"+user.getParentId()+"'";
			//平台客户认购占比(获取当前时间认购客户)
			String systemCusInOrderHql = "select count(*) from ContractRecords where 1=1 and shopCustomerId is not null and recordStatus = 1 and projectId = '"+user.getParentId()+"'";
			//平台客户签约占比
			String systemCusInSignHql = "select count(*) from ContractRecords where 1=1 and recordStatus=5 and shopCustomerId is not null and projectId = '"+user.getParentId()+"'";
			if(startDate!=null && !startDate.equals("")){
				dealHql += " and remitConfirmTime >= '"+startDate+"'";
				enterBuyHql += " and remitConfirmTime >= '"+startDate+"'";
				signHql += " and contractConfirmTime > '"+startDate+"'";
				lockedHouseHql += " and voucherUploadTime > '"+startDate+"'";
				signedHouseHql += " and contractConfirmTime > '"+startDate+"'";
				systemCusInOrderHql += " and applyTime >= '"+startDate+"'";
				systemCusInSignHql += " and voucherUploadTime > '"+startDate+"'";
			}
			if(endDate!=null && !endDate.equals("")){
				dealHql += " and remitConfirmTime <= '"+endDate+"'";
				enterBuyHql += " and remitConfirmTime <= ';"+endDate+"'";
				signHql += " and contractConfirmTime <= ';"+endDate+"'";
				lockedHouseHql += " and voucherUploadTime <= ';"+endDate+"'";
				signedHouseHql += " and contractConfirmTime <= '"+endDate+"'";
				systemCusInOrderHql += " and applyTime <= '"+endDate+"'";
				systemCusInSignHql += " and voucherUploadTime <= ';"+endDate+"'";
			}
			//成交数量
			int dealCount = baseDao.countQuery(dealHql);
			map.put("dealCount", dealCount);
			//今日下定
			int enterBuyCount = baseDao.countQuery(enterBuyHql);
			map.put("payMoney", enterBuyCount);
			//今日签约
			int signCount = baseDao.countQuery(signHql);
			map.put("signCount",signCount);
			//已锁定房源货值
			String lockedHouseValue = baseDao.findSum(lockedHouseHql);
			map.put("lockedValue", lockedHouseValue);
			//已签约房源货值
			String signedHouseValue = baseDao.findSum(signedHouseHql);
			map.put("signedHouseValue", signedHouseValue);
			//平台客户认购占比
			int systemCusCount= baseDao.countQuery(systemCusInOrderHql);
			
			double systemCusEnterBuyRate = 0;
			if(enterBuyCount>0){
				systemCusEnterBuyRate = (double)systemCusCount / (double)enterBuyCount * 100;
			}
			map.put("systemCusEnterBuyRate", SysContent.get2Double(systemCusEnterBuyRate)+"%");
			//平台客户签约占比
			double systemCusSignRate = 0;
			int systemCusInSignCount = baseDao.countQuery(systemCusInSignHql);
			if(signCount>0){
				systemCusSignRate = (double)systemCusInSignCount / (double)signCount * 100;
			}
			map.put("systemCusSignRate", SysContent.get2Double(systemCusSignRate)+"%");
		}
		return map;
	}

	//热力图俩天的数据
	@Override
	public Map findTwoDaysVisitData(User user) {
		Map map = new HashMap<>();
		if(user!=null){
			Map chartMap = new HashMap<>();
			Map oldChartMap = new HashMap<>();
			List newWorkTime = new ArrayList<>();
			List newYesterDayWorkTime = new ArrayList<>();
			//当前日期
			Date date = new Date();
			//昨天日期
			Date yesterDay = DateUtil.rollDay(date, -1);
			String start = DateUtil.format(DateUtil.getIntegralStartTime(date));
			String end = DateUtil.format(DateUtil.getIntegralEndTime(date));
			//String yesterDaystart = DateUtil.format(DateUtil.getIntegralStartTime(yesterDay));
			//String yesterDayend = DateUtil.format(DateUtil.getIntegralEndTime(yesterDay));
			List workTime = SysContent.getWorkTime(date);
			List yesterDayWorkTime = SysContent.getWorkTime(yesterDay);
			String chartHql = "select count(*) from VisitRecords where 1=1 and projectId = '"+user.getParentId()+"'";
			String chartStrHql = null;
			String oldChartStrHql = null;
			if(!workTime.isEmpty()){
				newWorkTime.addAll(workTime.subList(1, workTime.size()));
				System.out.println(newWorkTime);
				newYesterDayWorkTime.addAll(yesterDayWorkTime.subList(1, yesterDayWorkTime.size()-1));
				for(int i=0;i<workTime.size()-1;i++){
					System.out.println(workTime.get(i));
					chartStrHql = chartHql + " and arriveTime >= '"+workTime.get(0)+"' and arriveTime < '"+workTime.get(i+1)+"' "
							+ "and (leaveTime > '"+workTime.get(i)+"' and leaveTime is not null or leaveTime is null)";
					oldChartStrHql = chartHql + " and receptTime >= '"+yesterDayWorkTime.get(i)+"' and receptTime < '"+yesterDayWorkTime.get(i+1)+"'";
					int count = baseDao.countQuery(chartStrHql);
					int yesterdayCount = baseDao.countQuery(oldChartStrHql);
					chartMap.put(workTime.get(i+1), count);
					oldChartMap.put(yesterDayWorkTime.get(i), yesterdayCount);
				}
			}
			System.out.println(chartMap);
			map.put("x_axis", newWorkTime);
			map.put("yesterdayWorkTime", newYesterDayWorkTime);
			map.put("chart", chartMap);
			map.put("oldChart", oldChartMap);
		}
		return map;
	}

	//今日案场职业顾问的信息
	@Override
	public Map findToTodayAgentStatusData(User user, String sortSign) {
		Map map = new HashMap<>();//封装list集合
		List list = new ArrayList<>();//封装职业顾问基本接访信息map
		if(user!=null){
			//当前日期
			Date date = new Date();
			String start = DateUtil.format(DateUtil.getIntegralStartTime(date));
			String end = DateUtil.format(DateUtil.getIntegralEndTime(date));
			//今日工作
			List<User> todayWork = new ArrayList<>();
			//今日休息
			List<User> todayRest = new ArrayList<>();
			String inCurrentProjecUserHql = "FROM User WHERE parentId = '"+user.getParentId()+"' and userStatus = 1";//获取当前案场下所有
			List<User> agentList = baseDao.findByHql(inCurrentProjecUserHql);
			for(User u : agentList){
				String signAgentHql = "from SignRecords where userId = '"+u.getUserId()+"'"
						+ " and signInTime > '" +start+ "' and signInTime <= '"+end+"' and signOutTime is null";
				SignRecords signUser = (SignRecords) baseDao.loadObject(signAgentHql);
				if(signUser!=null){//今日工作
					todayWork.add(u);
				}else{//今日休息
					todayRest.add(u);
				}
			}
			//排序选择
			if(sortSign.equals("agent")){//安置业顾问排序
				//获取正在接待中的
				String visitingHql = "FROM VisitRecords WHERE leaveTime is NULL and projectId = '"+user.getParentId()+"' "
										+ " and receptTime >= '"+start+"' and receptTime <= '"+end+"'" ;
				//List<VisitRecords> visitingList = baseDao.findByHql(visitingHql);
				if(!todayWork.isEmpty()){
					for(User u : todayWork){
						Set<Role> rId = u.getRoleId();
						for(Role r : rId){
							if(r.getRoleId()==3){
								String vrHql = visitingHql + " and userId = '"+u.getUserId()+"'";
								VisitRecords vr = (VisitRecords) baseDao.loadObject(vrHql);
								if(vr!=null){
									Map aMap = new HashMap<>();
									aMap.put("userId", u.getUserId());//顾问id
									aMap.put("photo", u.getPhoto());//顾问头像
									aMap.put("name", u.getUserCaption());//顾问客户
									aMap.put("stutas", "接访中");//顾问状态
									aMap.put("comeVisitTime", vr.getArriveTime());//来访时间
									aMap.put("receptTimeLonger", "暂无");//接访时长
									aMap.put("comeCusCount", vr.getCustomerCount());//来访人数
									if(vr.getIsNew()!=null && !vr.getIsNew().equals("") && !vr.getIsNew()){
										aMap.put("cusPassageWay", "老客户通道");
									}else{
										aMap.put("cusPassageWay", "新客户通道");
									}
									if(vr.getUserId().equals(vr.getAppointUserId())){
										aMap.put("isAppoint", "是");
									}else{
										aMap.put("isAppoint", "否");
									}
									if(vr.getWriteState()!=null && !vr.getWriteState().equals("") && !vr.getWriteState().equals("0")){
										aMap.put("isWrite", "已登记");
									}else{
										aMap.put("isWrite", "未登记");
									}
									list.add(aMap);
								}else{
									Map aMap = new HashMap<>();
									aMap.put("userId", u.getUserId());//顾问id
									aMap.put("photo", u.getPhoto());//顾问头像
									aMap.put("name", u.getUserCaption());//顾问客户
									aMap.put("stutas", "空闲中");//顾问状态
									aMap.put("comeVisitTime", "暂无");//来访时间
									aMap.put("receptTimeLonger", "暂无");//接访时长
									aMap.put("comeCusCount", "暂无");
									aMap.put("cusPassageWay", "暂无");
									aMap.put("isAppoint", "暂无");
									aMap.put("isWrite", "暂无");
									list.add(aMap);
								}
							}
						}
					}
				}
				//获取休息中的顾问
				if(!todayRest.isEmpty()){
					for(User u : todayRest){
						Set<Role> rId = u.getRoleId();
						for(Role r : rId){
							if(r.getRoleId()==3){
								Map aMap = new HashMap<>();
								aMap.put("userId", u.getUserId());//顾问id
								aMap.put("photo", u.getPhoto());//顾问头像
								aMap.put("name", u.getUserCaption());//顾问客户
								aMap.put("stutas", "休息");//顾问状态
								aMap.put("comeVisitTime", "暂无");//来访时间
								aMap.put("receptTimeLonger", "暂无");//接访时长
								aMap.put("comeCusCount", 0);//来访人数
								aMap.put("cusPassageWay", "暂无");
								aMap.put("isAppoint", "否");
								aMap.put("isWrite", "暂无");
								list.add(aMap);
							}
						}
					}
				}
			}else if(sortSign.equals("timeLonger")){//按接访时长排序
				//空闲中的，按照接待时长从大到小排
				String waitVisitSql = "SELECT * FROM "
								+ "	(select *, TIMEDIFF(str_to_date(leaveTime,'%Y-%m-%d %H:%i:%s'),STR_TO_DATE(arriveTime,'%Y-%m-%d %H:%i:%s')) td "
								+ "	FROM t_visitrecords WHERE leaveTime is not NULL and projectId = '"+user.getParentId()+"' "
										+ " and receptTime >= '"+start+"' and receptTime < '"+end+"') newVisit "
								+ "	ORDER BY td DESC";
				List<VisitRecords> waitVisit = baseDao.queryBySql(waitVisitSql,VisitRecords.class);
				if(!waitVisit.isEmpty()){
					for(VisitRecords vr : waitVisit){
						User u = (User) baseDao.loadById(User.class, vr.getUserId());
						Set<Role> rId = u.getRoleId();
						for(Role r : rId){
							if(r.getRoleId()==3){
								Map aMap = new HashMap<>();
								aMap.put("userId", u.getUserId());//顾问id
								aMap.put("photo", u.getPhoto());//顾问头像
								aMap.put("name", u.getUserCaption());//顾问客户
								aMap.put("stutas", "接访完成");//顾问状态
								aMap.put("comeVisitTime", vr.getArriveTime());//来访时间
								int mm = Math.abs(DateUtil.getOffsetMinutes(DateUtil.parse(vr.getArriveTime()),
										DateUtil.parse(vr.getLeaveTime())));
								if(mm<=0){
									mm = 1;
								}
								aMap.put("receptTimeLonger", mm+"分钟");//接访时长
								if(vr.getCustomerCount()!=null && !vr.getCustomerCount().equals("")){
									aMap.put("comeCusCount", vr.getCustomerCount());
								}else{
									aMap.put("comeCusCount", 0);
								}
								
								if(vr.getIsNew()!=null && !vr.getIsNew().equals("") && !vr.getIsNew()){
									aMap.put("cusPassageWay", "老客户通道");
								}else{
									aMap.put("cusPassageWay", "新客户通道");
								}
								if(vr.getUserId().equals(vr.getAppointUserId())){
									aMap.put("isAppoint", "是");
								}else{
									aMap.put("isAppoint", "否");
								}
								if(vr.getWriteState()!=null && !vr.getWriteState().equals("") && !vr.getWriteState().equals("0")){
									aMap.put("isWrite", "已登记");
								}else{
									aMap.put("isWrite", "未登记");
								}
								list.add(aMap);
							}
						}
					}
				}
				//获取正在接待中的
				String visitingHql = "FROM VisitRecords WHERE leaveTime is NULL and projectId = '"+user.getParentId()+"' "
										+ " and receptTime >= '"+start+"' and receptTime < '"+end+"'";
				List<VisitRecords> visitingList = baseDao.findByHql(visitingHql);
				if(!visitingList.isEmpty()){
					for(VisitRecords vr : visitingList){
						User u = (User) baseDao.loadById(User.class, vr.getUserId());
						Map aMap = new HashMap<>();
						aMap.put("userId", u.getUserId());//顾问id
						aMap.put("photo", u.getPhoto());//顾问头像
						aMap.put("name", u.getUserCaption());//顾问客户
						aMap.put("stutas", "接访中");//顾问状态
						aMap.put("comeVisitTime", vr.getArriveTime());//来访时间
						aMap.put("receptTimeLonger", "暂无");//接访时长
						aMap.put("comeCusCount", vr.getCustomerCount());//来访人数
						if(vr.getIsNew()!=null && !vr.getIsNew().equals("") && !vr.getIsNew()){
							aMap.put("cusPassageWay", "老客户通道");
						}else{
							aMap.put("cusPassageWay", "新客户通道");
						}
						if(vr.getUserId().equals(vr.getAppointUserId())){
							aMap.put("isAppoint", "是");
						}else{
							aMap.put("isAppoint", "否");
						}
						if(vr.getWriteState()!=null && !vr.getWriteState().equals("") && !vr.getWriteState().equals("0")){
							aMap.put("isWrite", "已登记");
						}else{
							aMap.put("isWrite", "未登记");
						}
						list.add(aMap);
					}
				}
				//获取休息中的顾问
				for (User u : agentList) {
					Set<Role> rId = u.getRoleId();
					for (Role r : rId) {
						if (r.getRoleId() == 3) {
							String signAgentHql = "from SignRecords where userId = '" + u.getUserId() + "'"
									+ " and signInTime > '" + start + "' and ( signOutTime <= '" + end
									+ "' or signOutTime is null )";
							SignRecords signUser = (SignRecords) baseDao.loadObject(signAgentHql);
							if (signUser == null) {
								Map aMap = new HashMap<>();
								aMap.put("userId", u.getUserId());// 顾问id
								aMap.put("photo", u.getPhoto());// 顾问头像
								aMap.put("name", u.getUserCaption());// 顾问客户
								aMap.put("stutas", "休息");// 顾问状态
								aMap.put("comeVisitTime", "暂无");// 来访时间
								aMap.put("receptTimeLonger", "暂无");// 接访时长
								aMap.put("comeCusCount", 0);// 来访人数
								aMap.put("cusPassageWay", "暂无");
								aMap.put("isAppoint", "否");
								aMap.put("isWrite", "暂无");
								//list.add(aMap);
							}
						}
					}
				}
			}else if(sortSign.equals("receptTime")){//按接访时间排序
				//获取正在接待中的
				String visitingHql = "FROM VisitRecords WHERE 1=1 and projectId = '"+user.getParentId()+"' "
										+ " and receptTime >= '"+start+"' and receptTime < '"+end+"' ORDER BY arriveTime";
				List<VisitRecords> visitingList = baseDao.findByHql(visitingHql);
				if(!visitingList.isEmpty()){
					for(VisitRecords vr : visitingList){
						User u = (User) baseDao.loadById(User.class, vr.getUserId());
						Map aMap = new HashMap<>();
						aMap.put("userId", u.getUserId());//顾问id
						aMap.put("photo", u.getPhoto());//顾问头像
						aMap.put("name", u.getUserCaption());//顾问客户
						if(vr.getLeaveTime()!=null && !vr.getLeaveTime().equals("")){
							aMap.put("stutas", "接访完成");//顾问状态
							aMap.put("receptTimeLonger", DateUtil.getOffsetMinutes(DateUtil.parse(vr.getArriveTime()),
									DateUtil.parse(vr.getLeaveTime()))+"分钟");//接访时长
						}else{
							aMap.put("stutas", "接访中");//顾问状态
							aMap.put("receptTimeLonger", "暂无");//接访时长
						}
						aMap.put("comeVisitTime", vr.getArriveTime());//来访时间
						aMap.put("comeCusCount", vr.getCustomerCount());//来访人数
						if(vr.getIsNew()!=null && !vr.getIsNew().equals("") && !vr.getIsNew()){
							aMap.put("cusPassageWay", "老客户通道");
						}else{
							aMap.put("cusPassageWay", "新客户通道");
						}
						if(vr.getUserId().equals(vr.getAppointUserId())){
							aMap.put("isAppoint", "是");
						}else{
							aMap.put("isAppoint", "否");
						}
						if(vr.getWriteState()!=null && !vr.getWriteState().equals("") && !vr.getWriteState().equals("0")){
							aMap.put("isWrite", "已登记");
						}else{
							aMap.put("isWrite", "未登记");
						}
						list.add(aMap);
					}
				}
				//获取休息中的顾问
				if(!todayRest.isEmpty()){
					for(User u : todayRest){
						Set<Role> rId = u.getRoleId();
						for(Role r : rId){
							if(r.getRoleId()==3){
								Map aMap = new HashMap<>();
								aMap.put("userId", u.getUserId());//顾问id
								aMap.put("photo", u.getPhoto());//顾问头像
								aMap.put("name", u.getUserCaption());//顾问客户
								aMap.put("stutas", "休息");//顾问状态
								aMap.put("comeVisitTime", "暂无");//来访时间
								aMap.put("receptTimeLonger", "暂无");//接访时长
								aMap.put("comeCusCount", 0);//来访人数
								aMap.put("cusPassageWay", "暂无");
								aMap.put("isAppoint", "否");
								aMap.put("isWrite", "暂无");
								//list.add(aMap);
							}
						}
					}
				}
			}
			map.put("data", list);
		}
		return map;
	}

/************************************Web端Service***************************************/
	//获取项目任务完成进度
	@Override
	public Map<String, String> findProjectTaskFinishedExtent(User user, String anyDate) {
		Map map = new HashMap<>();
		if(user!=null){
			if(anyDate==null || anyDate.equals("")){
				anyDate = DateTime.toString1(new Date());
			}
			String start = SysContent.getStartTime2(DateUtil.parse(anyDate,DateUtil.PATTERN_CLASSICAL_SIMPLE));
			String end = SysContent.getEndTime2(DateUtil.parse(anyDate,DateUtil.PATTERN_CLASSICAL_SIMPLE));
			int maxNum = 100 ;
			//接访量完成度
			String visitHql = "select count(*) from VisitRecords where projectId = '"+user.getParentId()+"' and receptTime > '"+start+"' and receptTime <= '"+end+"' and writeState = 1";
			int visitCount = baseDao.countQuery(visitHql);
			int visitRate = visitCount / maxNum * 100;
			map.put("visitRate", visitRate+"%");
			//认购量完成度
			String enterBuyHql = "select count(*) from ContractRecords where projectId = '"+user.getParentId()+"' and voucherUploadTime is null and applyTime >= '"+start+"' and "
					+ " applyTime <= ';"+end+"'";
			int enterBuyCount = baseDao.countQuery(enterBuyHql);
			int enterBuyRate = enterBuyCount / maxNum * 100;
			map.put("enterBuyRate", enterBuyRate+"%");
			//签约量完成度
			String singHql = "select count(*) from ContractRecords where projectId = '"+user.getParentId()+"' and voucherUploadTime > '"+start+"' and voucherUploadTime <= '"+end+"'";
			int singCount = baseDao.countQuery(singHql);
			int singRate = singCount / maxNum * 100;
			map.put("signRate",singRate+"%");
			//售出房源货值完成度
			//新增储客完成度
			
		}
		return map;
	}

	//根据时间获取三类图标信息 经理端 web
	@Override
	public Map<String, Object> findThreeTypesData(User user, String anyDate, String anyTimes) {
		Map<String,Object> map = new HashMap<>();
		List<String> x_axis = new ArrayList<>();
		List<String> x_axis_new = new ArrayList<>();
		if(user!=null){
			//接访人数
			Map visitCountLineMap = new HashMap<>();
			String visitHql = "select count(*) from VisitRecords where 1=1 and projectId = '"+user.getParentId()+"'";
			//今日下订 
			Map enterBuyCountLineMap = new HashMap<>();
			String enterBuyHql = "select count(*) from ContractRecords where 1=1 and projectId = '"+user.getParentId()+"'";
			//今日签约
			Map signCountLineMap = new HashMap<>();
			String signHql = "select count(*) from ContractRecords where 1=1 and recordStatus = 5 and projectId = '"+user.getParentId()+"'";
			//储客人数
			Map saveCustomerLineMap = new HashMap<>();
			String saveCustomerHql = "from VisitRecords where writeState = 1 and projectId = '"+user.getParentId()+"' ";
			/******************环形图数据******************/
			//客户接访总数
			String totalVisitHql = "from VisitRecords where 1=1 and projectId = '"+user.getParentId()+"'";
			//老客户通道接访
			String oldCusVisitHql = "select count(*) from VisitRecords where 1=1 and isNew is not true and projectId = '"+user.getParentId()+"'";
			//新客户通道接访
			String newCusVisitHql = "select count(*) from VisitRecords where 1=1 and isNew is not false and projectId = '"+user.getParentId()+"'";
			//外场导客占比
			String outsideGuideVisitHql = "SELECT v.visitNo FROM t_guiderecords g,t_visitrecords v WHERE g.visitNo=v.visitNo and g.applyStatus = 1 and v.projectId = '"+user.getParentId()+"'";
			//获取当前天时间的起止时间
			String start = null;
			String end = null;
			if(anyDate!=null && !anyDate.equals("")){
				Date date = DateUtil.parse(anyDate,DateUtil.PATTERN_CLASSICAL_SIMPLE);
				//获取当前天时间的起止时间
				start = SysContent.getStartTime2(date);
				end = SysContent.getEndTime2(date);
				//获取工作时点，封装折线图X轴数据
				List workTime = SysContent.getWorkTime(date);
				x_axis.addAll(workTime);
			}/*else if(anyTimes!=null && !anyTimes.equals("")){
				Map<String,String> day = null;
				if(anyTimes.equals("oneWeek")){
					day = DateUtil.getPastAnyDaysOfDate(new Date(),7);
				}else if(anyTimes.equals("halfMonth")){
					day = DateUtil.getPastAnyDaysOfDate(new Date(),15);
				}else if(anyTimes.equals("oneMonth")){
					day = DateUtil.getPastAnyDaysOfDate(new Date(),30);
				}else if(anyTimes.equals("halfYear")){
					day = DateUtil.getPastAnyMonthOfDate(new Date(),6);
				}else if(anyTimes.equals("oneYear")){
					day = DateUtil.getPastAnyMonthOfDate(new Date(),12);
				}
				if(day!=null){
					//获取起始时间和最终时间
					start = day.get("pastAnydaysStartDay");
					end = day.get("currentDateEndDay");
					if(anyTimes.equals("halfYear") || anyTimes.equals("oneYear")){
						//获取工作时间点坐标按月
						Date startDate = DateUtil.parse(start, DateUtil.PATTERN_CLASSICAL_SIMPLE);
						Date endDate = DateUtil.parse(end, DateUtil.PATTERN_CLASSICAL_SIMPLE);
						List workTime = SysContent.getWorkTimeForMonth(startDate,endDate);
						x_axis.addAll(workTime);
					}else{
						//按天分
						Date startDate = DateUtil.parse(start, DateUtil.PATTERN_CLASSICAL_SIMPLE);
						Date endDate = DateUtil.parse(end, DateUtil.PATTERN_CLASSICAL_SIMPLE);
						List workTime = SysContent.getWorkTime(startDate,endDate);
						x_axis.addAll(workTime);
					}
				}
			}*/else{
				Date date = new Date();
				anyDate = DateUtil.format(date);
				//获取当前天时间的起止时间
				start = SysContent.getStartTime2(date);
				end = SysContent.getEndTime2(date);
				//获取工作时点，封装折线图X轴数据
				List workTime = SysContent.getWorkTime(date);
				x_axis.addAll(workTime);
			}
			//获取start到end的到访总数
			String visitCountHql = visitHql + " and arriveTime >= '"+start+"' and arriveTime <= '"+end+"'";
			int totalVisitCount = baseDao.countQuery(visitCountHql);
			map.put("totalVisitCount", totalVisitCount);
			//获取start到end认购的总数
			String enterBuyCountHql = enterBuyHql + " and recordStatus = 4 and remitConfirmTime >= '"+start+"' and remitConfirmTime <= '"+end+"'"; 
			int totalEnterBuyCount = baseDao.countQuery(enterBuyCountHql);
			map.put("totalEnterCount", totalEnterBuyCount);
			//获取start到end的签约总数 
			String signCountHql = signHql + " and contractConfirmTime > '"+start+"' and contractConfirmTime <= '"+end+"'";
			int totalSignCount = baseDao.countQuery(signCountHql);
			map.put("totalSignCount", totalSignCount);
			//获取start到end的储客总数
			String saveCustomerCountHql = saveCustomerHql + " and arriveTime >= '"+start+"' and arriveTime <= '"+end+"'";
			List<VisitRecords> totalSaveCustomerList = baseDao.findByHql(saveCustomerCountHql);
			String repeatCusHql = saveCustomerHql + " and arriveTime < '"+start+"'";
			int totalSaveCustomerCount = totalSaveCustomerList.size();
			for(VisitRecords vr : totalSaveCustomerList){
				String repeatCusByPhoneHql = repeatCusHql + " and phone = '"+vr.getPhone()+"'";
				VisitRecords oldVr = (VisitRecords) baseDao.loadObject(repeatCusByPhoneHql);
				if(oldVr!=null){
					totalSaveCustomerCount -= 1;
				}
			}
			map.put("totalSaveCustomerCount", totalSaveCustomerCount);
			/*************************环形图数据*************************/
			//有效接访
			String valideVisitHql = visitHql + "and writeState = 1 and arriveTime >= '"+start+"' and arriveTime < '"+end+"'";
			int valideVisitCount = baseDao.countQuery(valideVisitHql);
			map.put("valideVisitCount", valideVisitCount);
			//无有效接访
			map.put("unValideVisitCount", totalVisitCount - valideVisitCount);
			//老客户通道
			oldCusVisitHql += " and arriveTime >= '"+start+"' and arriveTime <= '"+end+"'";
			int oldCusVistCount = baseDao.countQuery(oldCusVisitHql);
			map.put("oldCusVistCount", oldCusVistCount);
			//新客户通道
			newCusVisitHql += " and arriveTime >= '"+start+"' and arriveTime <= '"+end+"'";
			int newCusVisitCount = baseDao.countQuery(newCusVisitHql);
			map.put("newCusVisitCount", newCusVisitCount);
			//外场导客
			outsideGuideVisitHql+= " and v.arriveTime >= '"+start+"' and v.arriveTime <= '"+end+"'";
			List outsideGuideVisitList = baseDao.queryBySql(outsideGuideVisitHql);
			int outsideGuideVisitCount = outsideGuideVisitList.size(); 
			map.put("newCusAppointVisitRate", outsideGuideVisitCount);
			//内场到访
			int inSideVisitCount = totalVisitCount - outsideGuideVisitCount;
			map.put("inSideVisitCount", inSideVisitCount);
			//折线图数据
			if(!x_axis.isEmpty()){
				String frontF = x_axis.get(0).toString();
				String front = "";
				String frontMonth = null;
				String next = null;
				String currentDate = null;
				for(int i=0;i<x_axis.size()-1;i++){
					String forVisitHql = "";
					String forEnterBuyHql = "";
					String forSignHql = "";
					String forSaveCustomerHql = "";
					Map<String,Object> visit = new HashMap<>();
					Map<String,Object> enterBuy = new HashMap<>();
					Map<String,Object> sign = new HashMap<>();
					Map<String,Object> saveCus = new HashMap<>();
					front = x_axis.get(i).toString();
					next = x_axis.get(i+1).toString();
					currentDate = x_axis.get(i+1);
					x_axis_new.add(currentDate);
					/*if(anyDate!=null && !anyDate.equals("")){
						if(i+1<x_axis.size()){
							front = x_axis.get(i).toString();
							next = x_axis.get(i+1).toString();
							currentDate = x_axis.get(i+1);
							x_axis_new.add(currentDate);
						}else{
							break;
						}
					}*/
					
					//以天为单位显示数据
					if(anyTimes!=null && !anyTimes.equals("") && 
							!anyTimes.equals("halfYear") && !anyTimes.equals("oneYear")){
						front = DateUtil.format(DateUtil.getIntegralStartTime(DateUtil.parse(currentDate,DateUtil.PATTERN_CLASSICAL_SIMPLE)),DateUtil.PATTERN_CLASSICAL);
						next = DateUtil.format(DateUtil.getIntegralEndTime(DateUtil.parse(currentDate,DateUtil.PATTERN_CLASSICAL_SIMPLE)),DateUtil.PATTERN_CLASSICAL);
						
					}else if(anyTimes!=null && !anyTimes.equals("") && 
							!anyTimes.equals("oneWeek") && !anyTimes.equals("halfMonth") && !anyTimes.equals("oneMonth")){//以月为单位显示数据
						front = DateUtil.format(
									DateUtil.getIntegralStartTime(
											DateUtil.parse(currentDate,DateUtil.PATTERN_CLASSICAL_SIMPLE)),DateUtil.PATTERN_CLASSICAL);
						next = DateUtil.format(
									DateUtil.getMonthEndTime(
											DateUtil.parse(currentDate,DateUtil.PATTERN_CLASSICAL_SIMPLE)),DateUtil.PATTERN_CLASSICAL);
						frontMonth = DateUtil.format(DateUtil.parse(front),DateUtil.PATTERN_CLASSICAL_SIMPLE);
					}
					//对应X坐标的到访数据
					forVisitHql = visitHql + " and arriveTime >= '"+frontF+"' and arriveTime < '"+next+"' and "
							+ "(leaveTime > '"+front+"' and leaveTime is not null or leaveTime is null)";
					/*forVisitHql = visitHql + " and arriveTime > '"+front+"' and "
							+ "(leaveTime < '"+next+"' or leaveTime is null)";*/
					//接访总数
					int visitCount = baseDao.countQuery(forVisitHql);
					visit.put("count", visitCount);
					//有效接访
					String vaildVisitHql = visitHql + " and writeState = 1 and arriveTime >= '"+front+"' and arriveTime < '"+next+"'";
					int vaildVisitCount = baseDao.countQuery(vaildVisitHql);
					visit.put("vaild", vaildVisitCount);
					//有效接访率
					int vaildVisitRate = 0;
					if(visitCount > 0){
						vaildVisitRate = vaildVisitCount / visitCount * 100;
					}
					visit.put("vaildRate", vaildVisitRate);
					//老客户通道接访
					String oldCusWayHql = visitHql + " and isNew is not true and arriveTime >= '"+front+"' and arriveTime < '"+next+"'";
					int oldCusWayCount = baseDao.countQuery(oldCusWayHql);
					visit.put("oldCusWayCount", oldCusWayCount);
					//接访平均时长
					String timeSumSql = "SELECT SUM(v.td) seconds FROM (select *, TIMESTAMPDIFF(SECOND,STR_TO_DATE(arriveTime,'%Y-%m-%d %H:%i:%s'),"
							+ "str_to_date(leaveTime,'%Y-%m-%d %H:%i:%s')) td FROM t_visitrecords "
							+ "WHERE leaveTime is not NULL and arriveTime >='"+front+"' and arriveTime <'"+next+"') v";
					String[] colums = {"seconds"};
					String[] types = {"Integer"};
					List<Times> timesList = baseDao.queryDTOBySql(timeSumSql, Times.class, colums, types);
					Integer seconds = 0;
					Integer averageSeconds = 0;
					if(!timesList.isEmpty()){
						for(Times t : timesList){
							seconds = t.getSeconds();
							if(seconds==null){
								seconds = 0;
							}
						}
					}
					if(visitCount > 0){					
						averageSeconds = seconds / visitCount;
					}
					visit.put("averageSeconds", averageSeconds);
					visitCountLineMap.put(next, visit);
					
					//对应X坐标的认购数据
					forEnterBuyHql = enterBuyHql + " and applyTime >= '"+front+"' and applyTime < '"+next+"'"; 
					//总认购客户数
					int enterBuyCount = baseDao.countQuery(forEnterBuyHql);
					enterBuy.put("enterBuyCount", enterBuyCount);
					//已下定
					String havePayHql = enterBuyHql + " and voucherUploadTime is not null and applyTime >= '"+front+"' and applyTime < '"+next+"'";
					int havePayCount = baseDao.countQuery(havePayHql);
					enterBuy.put("havePayCount", havePayCount);
					//锁定房源货值
					String totalBuyPriceHql = "select SUM(buyPrice) from ContractRecords where 1=1 and "
							+ "projectId = '"+user.getParentId()+"' and voucherUploadTime is not null and "
							+ "applyTime >= '"+front+"' and applyTime < '"+next+"' and voucherUploadTime is not null";
					String totalBuyPrice = baseDao.findSum(totalBuyPriceHql);
					enterBuy.put("totalBuyPrice", totalBuyPrice);
					enterBuyCountLineMap.put(next, enterBuy);
					//对应X坐标的签约数据
					forSignHql = signHql + " and voucherUploadTime >= '"+front+"' and voucherUploadTime <= '"+next+"'";
					//已签约数
					int signCount = baseDao.countQuery(forSignHql);
					sign.put("signCount", signCount);
					//已签约房源货值
					String signPriceHql = "select SUM(buyPrice) from ContractRecords where 1=1 and "
							+ "projectId = '"+user.getParentId()+"' and voucherUploadTime is not null and "
							+ "applyTime >= '"+front+"' and applyTime < '"+next+"' and recordStatus = 5";
					String signPrice = baseDao.findSum(signPriceHql);
					sign.put("signPrice", signPrice);
					//放弃签约数
					String giveUpSignHql = signHql + " and recordStatus = 7 and contractConfirmTime is not null and voucherUploadTime >= '"+front+"' and voucherUploadTime <= '"+next+"'";
					int giveUpSignCount = baseDao.countQuery(giveUpSignHql);
					sign.put("giveUpSignCount", giveUpSignCount);
					//待签约数
					String waitSignHql = signHql + " and voucherUploadTime is not null and voucherUploadTime >= '"+front+"' and voucherUploadTime <= '"+next+"'";
					int waitSignCount = baseDao.countQuery(waitSignHql);
					sign.put("waitSignCount", waitSignCount);
					signCountLineMap.put(next, sign);
					//对应X坐标的储客数据
					forSaveCustomerHql = saveCustomerHql + " and arriveTime >= '"+front+"' and arriveTime <= '"+next+"'";
					String repeatForCusHql = saveCustomerHql + " and receptTime < '"+front+"'";
					List<VisitRecords> saveList = baseDao.findByHql(forSaveCustomerHql);
					int saveCount = saveList.size();
					for(VisitRecords forVr : saveList){
						String repeatForCusByPhoneHql = repeatForCusHql + " and phone = '"+forVr.getPhone()+"'";
						VisitRecords oldFforVr = (VisitRecords) baseDao.loadObject(repeatForCusByPhoneHql);
						if(oldFforVr!=null){
							saveCount -= 1;
						}
						
					}
					//新增总储客数
					saveCus.put("count", saveCount);
					//新增二次来访客户数（已取消）
					saveCustomerLineMap.put(next, saveCus);
					
				}
			}
			map.put("x_axis", x_axis_new);
			map.put("visitLine",visitCountLineMap);
			map.put("enterBuyLine", enterBuyCountLineMap);
			map.put("signLine", signCountLineMap);
			map.put("saveCustomerLine",saveCustomerLineMap);
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> findProShopsData(User user, String anyDate, String anyTimes) {
		List list = new ArrayList<>();
		if(user!=null){
			String start = null;
			String end = null;
			if(anyDate!=null && !anyDate.equals("")){
				Date date = DateUtil.parse(anyDate,DateUtil.PATTERN_CLASSICAL_SIMPLE);
				//获取当前天时间的起止时间
				start = SysContent.getStartTime2(date);
				end = SysContent.getEndTime2(date);
			}else if(anyTimes!=null && !anyTimes.equals("")){
				Map<String,String> day = null;
				if(anyTimes.equals("oneWeek")){
					day = DateUtil.getPastAnyDaysOfDate(new Date(),7);
				}else if(anyTimes.equals("halfMonth")){
					day = DateUtil.getPastAnyDaysOfDate(new Date(),15);
				}else if(anyTimes.equals("oneMonth")){
					day = DateUtil.getPastAnyDaysOfDate(new Date(),30);
				}else if(anyTimes.equals("halfYear")){
					day = DateUtil.getPastAnyMonthOfDate(new Date(),6);
				}else if(anyTimes.equals("oneYear")){
					day = DateUtil.getPastAnyMonthOfDate(new Date(),12);
				}
				if(day!=null){
					//获取起始时间和最终时间
					start = day.get("pastAnydaysStartDay");
					end = day.get("currentDateEndDay");
					if(anyTimes.equals("halfYear") || anyTimes.equals("oneYear")){
						//获取工作时间点坐标按月
						Date startDate = DateUtil.parse(start, DateUtil.PATTERN_CLASSICAL_SIMPLE);
						Date endDate = DateUtil.parse(end, DateUtil.PATTERN_CLASSICAL_SIMPLE);
					}else{
						//按天分
						Date startDate = DateUtil.parse(start, DateUtil.PATTERN_CLASSICAL_SIMPLE);
						Date endDate = DateUtil.parse(end, DateUtil.PATTERN_CLASSICAL_SIMPLE);
					}
				}
			}else{
				Date date = new Date();
				//获取当前天时间的起止时间
				start = SysContent.getStartTime2(date);
				end = SysContent.getEndTime2(date);
			}
			//查询案场信息
			/*String shopsSql = "SELECT * FROM "
					+ "t_shops s,(SELECT u.parentId FROM t_users u ,"
					+ "(SELECT userId FROM t_guiderecords WHERE projectId = '"+user.getParentId()+"' GROUP BY userId) gr "
					+ "WHERE u.userId = gr.userId) shopId WHERE s.shopId = shopId.parentId and applyTime >= '"+start+"' and applyTime <= '"+end+"'";*/
			String shopsSql = "SELECT * FROM t_shops s,(SELECT u.parentId FROM t_users u ,(SELECT myu.userId FROM ("
					+ " (SELECT userId FROM t_guiderecords WHERE projectId = '"+user.getParentId()+"' and applyTime>= '"+start+"' and "
					+ " applyTime <= '"+end+"' GROUP BY userId) UNION ALL (SELECT c.userId FROM t_contractrecords c,"
					+ " (SELECT u.parentId,u.userId,u.userCaption,u.photo,r.r_id FROM t_users u,(SELECT u_id userId,r_id FROM user_role "
					+ " WHERE r_id = 1 or r_id=2) r WHERE u.userId = r.userId) ru WHERE ru.userId = c.userId and c.recordStatus = 0 and c.projectId='"+user.getParentId()+"' "
					+ " and c.applyTime >= '"+start+"' and c.applyTime <= '"+end+"' GROUP BY c.userId)) myu) gr WHERE "
					+ " u.userId = gr.userId) shopId WHERE s.shopId = shopId.parentId";
			List<Shops> shopsList = baseDao.queryBySql(shopsSql, Shops.class);
			for(Shops s : shopsList){
				Map map = new HashMap<>();
				map.put("name", s.getShopName());
				map.put("phone", s.getPhone());
				map.put("address", s.getAddress());
				map.put("llNum", s.getLngLat());
				//备案客户数
				String grSql = "SELECT g.recordNo FROM t_guiderecords g,(SELECT userId FROM t_users WHERE parentId = '"+s.getShopId()+"') u WHERE g.userId = u.userId"
						+ " and (g.applyStatus = 0 or g.applyStatus = 1)";
				List grList = baseDao.queryBySql(grSql);
				int grCount = grList.size();
				map.put("guideCount", grCount);
				//备案到访数
				String grVisitSql = "SELECT g.recordNo FROM t_guiderecords g,(SELECT userId FROM t_users WHERE parentId = '"+s.getShopId()+"') u WHERE g.userId = u.userId"
						+ " and g.applyStatus = 1";
				List grVisitList = baseDao.queryBySql(grVisitSql);
				int grVisitCount = grVisitList.size();
				map.put("guideVisitCount", grVisitCount);
				//备案到访率
				int grVisitRate = 0;
				if(grCount>0){
					grVisitRate = grVisitCount / grCount * 100;
				}
				map.put("guideVisitRate", grVisitRate);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> findThreeOrderData(User user, String anyDate, String anyTimes,
			String agentOrderSign, String shopOrderSign) {
		Map <String,Object> map = new HashMap<>();
		if(user!=null){
			String start = null;
			String end = null;
			if(anyDate!=null && !anyDate.equals("")){
				Date date = DateUtil.parse(anyDate,DateUtil.PATTERN_CLASSICAL_SIMPLE);
				//获取当前天时间的起止时间
				start = SysContent.getStartTime2(date);
				end = SysContent.getEndTime2(date);
			}else if(anyTimes!=null && !anyTimes.equals("")){
				Map<String,String> day = null;
				if(anyTimes.equals("oneWeek")){
					day = DateUtil.getPastAnyDaysOfDate(new Date(),7);
				}else if(anyTimes.equals("halfMonth")){
					day = DateUtil.getPastAnyDaysOfDate(new Date(),15);
				}else if(anyTimes.equals("oneMonth")){
					day = DateUtil.getPastAnyDaysOfDate(new Date(),30);
				}else if(anyTimes.equals("halfYear")){
					day = DateUtil.getPastAnyMonthOfDate(new Date(),6);
				}else if(anyTimes.equals("oneYear")){
					day = DateUtil.getPastAnyMonthOfDate(new Date(),12);
				}
				if(day!=null){
					//获取起始时间和最终时间
					start = day.get("pastAnydaysStartDay");
					end = day.get("currentDateEndDay");
					if(anyTimes.equals("halfYear") || anyTimes.equals("oneYear")){
						//获取工作时间点坐标按月
						Date startDate = DateUtil.parse(start, DateUtil.PATTERN_CLASSICAL_SIMPLE);
						Date endDate = DateUtil.parse(end, DateUtil.PATTERN_CLASSICAL_SIMPLE);
					}else{
						//按天分
						Date startDate = DateUtil.parse(start, DateUtil.PATTERN_CLASSICAL_SIMPLE);
						Date endDate = DateUtil.parse(end, DateUtil.PATTERN_CLASSICAL_SIMPLE);
					}
				}
			}else{
				Date date = new Date();
				//获取当前天时间的起止时间
				start = SysContent.getStartTime2(date);
				end = SysContent.getEndTime2(date);
			}
			//职业顾问排行
			String agentOrderSql = null;
			if(agentOrderSign.equals("visit")){
				agentOrderSql = "SELECT us.userId,us.userCaption,us.photo,COUNT(cs.userId) num from "
						+ "(SELECT u.parentId,u.userId,u.userCaption,u.photo FROM t_users u, user_role ur WHERE r_id = 3 AND u.userId=ur.u_id "
						+ "and u.parentId = '"+user.getParentId()+"') us "
						+ "LEFT JOIN t_visitrecords cs ON us.userId = cs.userId  and cs.arriveTime >= '"+start+"' and cs.arriveTime<='"+end+"'"
						+ " GROUP BY us.userId ORDER BY num DESC";
			}else if(agentOrderSign.equals("enterBuy")){//下定 recordStatus = 4 and remitConfirmTime >= '"+start+"' and remitConfirmTime <= '"+end+"'"; 
				agentOrderSql = "SELECT us.userId,us.userCaption,us.photo,COUNT(cs.userId) num from "
						+ "(SELECT u.parentId,u.userId,u.userCaption,u.photo FROM t_users u, user_role ur WHERE r_id = 3 AND u.userId=ur.u_id "
						+ "and u.parentId = '"+user.getParentId()+"') us "
						+ "LEFT JOIN t_contractrecords cs ON us.userId = cs.userId and cs.remitConfirmTime >= '"+start+"' and cs.remitConfirmTime <= '"+end+"'"
						+ " and recordStatus = 4  GROUP BY us.userId ORDER BY num DESC";
			}else if(agentOrderSign.equals("sign")){
				agentOrderSql = "SELECT us.userId,us.userCaption,us.photo,COUNT(cs.userId) num from "
						+ "(SELECT u.parentId,u.userId,u.userCaption,u.photo FROM t_users u, user_role ur WHERE r_id = 3 AND u.userId=ur.u_id "
						+ "and u.parentId = '"+user.getParentId()+"') us "
						+ "LEFT JOIN t_contractrecords cs ON us.userId = cs.userId and cs.voucherUploadTime >= '"+start+"' and "
						+ " (cs.recordStatus=5 ) and cs.voucherUploadTime <= '"+end+"' GROUP BY us.userId ORDER BY num DESC";
			}
			List<Map<String,Object>> agentInfoMapList = new ArrayList<>();
			if(agentOrderSql!=null){
				String[] colums ={"userId","userCaption","photo","num"};
				String[] types = {"String","String","String","Integer"};
				List<AgentOrderDTO> agentOrderList = baseDao.queryDTOBySql(agentOrderSql, AgentOrderDTO.class, colums, types);
				for(AgentOrderDTO adt : agentOrderList){
					JSONObject json = JSONObject.fromObject(adt);
					agentInfoMapList.add(json);
				}
			}
			map.put("angentList", agentInfoMapList);
			//门店排行
			String shopsOrderSql = null;
			if(shopOrderSign.equals("guide")){//applyStatus=1备案到访；applyStatus=0已备案
				shopsOrderSql = "SELECT s.shopId,s.shopName,guide.num FROM t_shops s, "
						+ "(SELECT agents.parentId,COUNT(gr.userId) num FROM "
						+ "(SELECT u.parentId,u.userId,ur.r_id FROM t_users u, user_role ur "
						+ "WHERE (r_id = 1 OR r_id=2) AND u.userId=ur.u_id) agents , t_guiderecords gr "
						+ "where agents.userId=gr.userId AND (gr.applyStatus=0 OR gr.applyStatus=1 ) AND gr.projectId = '"+user.getParentId()+"' "
						+ "and gr.applyTime >= '"+start+"' and "
						+ "gr.applyTime <= '"+end+"' GROUP BY agents.userId) guide "
						+ "where s.shopId = guide.parentId GROUP BY s.shopId ORDER BY guide.num DESC";
			}else if(shopOrderSign.equals("guideVisit")){//applyStatus=1备案到访
				shopsOrderSql =  "SELECT s.shopId,s.shopName,guide.num FROM t_shops s , "
						+ "(SELECT agents.parentId,COUNT(gr.userId) num FROM "
						+ "(SELECT u.parentId,u.userId,ur.r_id FROM t_users u, user_role ur "
						+ "WHERE (r_id = 1 OR r_id=2) AND u.userId=ur.u_id) agents , t_guiderecords gr "
						+ "where agents.userId=gr.userId AND gr.applyStatus=1 AND gr.projectId = '"+user.getParentId()+"' "
						+ "and gr.applyTime >= '"+start+"' and "
						+ "gr.applyTime <= '"+end+"' GROUP BY agents.userId) guide "
						+ "where s.shopId = guide.parentId GROUP BY s.shopId ORDER BY guide.num DESC";
			}
			List<Map<String,Object>> shopsInfoMapList = new ArrayList<>();
			if(shopsOrderSql!=null){
				String[] colums ={"shopId","shopName","num"};
				String[] types = {"String","String","Integer"};
				List<ShopsOrderDTO> shopsOrderList = baseDao.queryDTOBySql(shopsOrderSql, ShopsOrderDTO.class,colums,types);
				for(ShopsOrderDTO sdt : shopsOrderList){
					JSONObject json = JSONObject.fromObject(sdt);
					shopsInfoMapList.add(json);
				}
			}
			map.put("shopList", shopsInfoMapList);
			//货值金额
			//认购锁定房源货值
			String enterBuyClockHouseHql = "SELECT SUM(buyPrice) FROM ContractRecords "
					+ "WHERE recordStatus = 1 AND auditingTime >= '"+start+"' and auditingTime <= '"+end+"'"
							+ " AND projectId = '"+user.getParentId()+"'";
			String enterBuyClockHouseCount = baseDao.findSum(enterBuyClockHouseHql);
			map.put("enterBuyClockHouse", enterBuyClockHouseCount);
			//认购到款金额
			String enterBuyGetMoneyHql = "SELECT SUM(haveToPayMoney) FROM ContractRecords "
					+ "WHERE recordStatus = 4 AND remitConfirmTime >= '"+start+"' and remitConfirmTime <= '"+end+"'"
					+ " AND projectId = '"+user.getParentId()+"'";
			String enterBuyGetMoneyCount = baseDao.findSum(enterBuyGetMoneyHql);
			map.put("enterBuyGetMoney", enterBuyGetMoneyCount);
			//已签约房源货值
			String signHql = "SELECT SUM(buyPrice) FROM ContractRecords "
					+ "WHERE recordStatus = 5 AND contractConfirmTime >= '"+start+"' and contractConfirmTime <= '"+end+"'"
					+ " AND projectId = '"+user.getParentId()+"'";
			String signCount = baseDao.findSum(signHql);
			map.put("sign", signCount);
			//待签约房源货值
			String waitSignHql = "SELECT SUM(buyPrice) FROM ContractRecords "
					+ "WHERE recordStatus = 4 AND remitConfirmTime >= '"+start+"' and remitConfirmTime <= '"+end+"'"
					+ " AND projectId = '"+user.getParentId()+"'";
			String waitSignCount = baseDao.findSum(waitSignHql);
			map.put("waitSign", waitSignCount);
			//门店佣金
			String grHql = "from GuideRecords where applyStatus = 1 and isDeal = 1 and projectId = '"+user.getParentId()+"'";
			List<GuideRecords> grList = baseDao.findByHql(grHql);
			int totalMoney = 0;
			for(GuideRecords gr : grList){
				HashMap grMap = JSON.parseObject(gr.getRules(), HashMap.class);
				double fenXiao = Double.parseDouble(grMap.get("fenXiaoMoney").toString());
				double daiKan = Double.parseDouble(grMap.get("daiKanMoney").toString());
				//分销
				String orderSaleHql = "from ContractRecords where recordStatus = 5 and projectId = '"+user.getParentId()+"' and "
						+ "userId = '"+gr.getUserId()+"' and shopCustomerId = '"+gr.getShopCustomerId()+"' and projectId = '"+user.getParentId()+"'";
				List<ContractRecords> someSaleList = baseDao.findByHql(orderSaleHql);
				if(!someSaleList.isEmpty()){
					for(ContractRecords cr : someSaleList){
						totalMoney += cr.getBuyPrice() * fenXiao;
					}
				}
				//带看
				String orderGuideHql = "from ContractRecords where recordStatus = 5 and projectId = '"+user.getParentId()+"' and "
						+ "userId != '"+gr.getUserId()+"' and projectCustomerId = '"+gr.getProjectCustomerId()+"' and projectId = '"+user.getParentId()+"'";
				List<ContractRecords> someGuideList = baseDao.findByHql(orderGuideHql);
				if(!someGuideList.isEmpty()){
					for(ContractRecords cr : someGuideList){
						totalMoney += cr.getBuyPrice() * daiKan;
					}
				}
			}
			map.put("shopsMoney", totalMoney);
		}
		return map;
	}

	//全部订单
	@Override
	public void findAllOrderData(User user , Page page) {
		if(user!=null){
			String allOrderTotalSql = "SELECT c.recordNo,c.applyTime,c.buyPrice,c.recordStatus,c.voucherUploadTime,"
					+ "h.buildingNo,h.unit,h.houseNo,h.listPrice,h.houseNum,h.buildArea"
					+ " FROM t_contractrecords c,t_projecthouses h WHERE c.houseNum=h.houseNum and "
					+ "c.projectId = '"+user.getParentId()+"' ORDER BY c.applyTime DESC";
			String allOrderListSql = allOrderTotalSql + " LIMIT "+page.getStart()+","+page.getLimit();
			String[] colums = {"recordNo","applyTime","buyPrice","recordStatus","voucherUploadTime","buildingNo","unit","houseNo","listPrice","houseNum","buildArea"};
			String[] types = {"Integer","String","Double","Integer","String","String","String","String","Double","Integer","Double"};
			int total = baseDao.queryBySql(allOrderTotalSql).size();
			List<Order> orderList = baseDao.queryDTOBySql(allOrderListSql, Order.class, colums, types);
			List<Order> newList = new ArrayList<>();
			for(Order o : orderList){
				House h = (House) baseDao.loadById(House.class, o.getHouseNum());
				String houseTypeId = h.getHouseTypeId();
				if(houseTypeId!=null && !houseTypeId.equals("")){
					HouseType ht = (HouseType) baseDao.loadById(HouseType.class, houseTypeId);
					String picUrl = ht.getPhotoURL();
					if(picUrl!=null && !picUrl.equals("")){
						o.setHouseTypePic(picUrl);
					}
				}
				if(o.getRecordStatus().equals(0)){
					o.setOrderStatus("待审核");
				}else if(o.getRecordStatus().equals(1) && o.getVoucherUploadTime()!=null && !o.getVoucherUploadTime().equals("")){
					o.setOrderStatus("付款待确认");
				}else if(o.getRecordStatus().equals(1) && (o.getVoucherUploadTime()==null || !o.getVoucherUploadTime().equals(""))){
					o.setOrderStatus("待付款");
				}else if(o.getRecordStatus().equals(3)){
					o.setOrderStatus("已拒绝");
				}else if(o.getRecordStatus().equals(4)){
					o.setOrderStatus("待签约");
				}else if(o.getRecordStatus().equals(7)){
					o.setOrderStatus("已撤销");
				}else if(o.getRecordStatus().equals(5)){
					o.setOrderStatus("已签约");
				}else if(o.getRecordStatus().equals(6)){
					o.setOrderStatus("撤单已退款");
				}else{
					o.setOrderStatus("已删除");
				}
				Double unitPrice = o.getListPrice() / o.getBuildArea();
				o.setUnitPrice(SysContent.get2Double(unitPrice));
				if(o.getBuyPrice()==null || o.getBuyPrice().equals("")){
					o.setBuyPrice(0.00);
				}
				newList.add(o);
			}
			page.setTotal(total);
			page.setRoot(newList);
		}
	}

	//待审核订单
	@Override
	public void findWaitCheckOrderData(User user, Page page) {
		if(user!=null){
			String allOrderTotalSql = "SELECT c.recordNo,c.applyTime,c.buyPrice,h.buildingNo,h.unit,h.houseNo,h.listPrice ,h.houseNum ,h.buildArea"
					+ " FROM t_contractrecords c,t_projecthouses h WHERE c.houseNum=h.houseNum and c.recordStatus=0 and "
					+ " c.projectId = '"+user.getParentId()+"' ORDER BY c.applyTime DESC";
			String allOrderListSql = allOrderTotalSql + " LIMIT "+page.getStart()+","+page.getLimit();
			String[] colums = {"recordNo","applyTime","buyPrice","buildingNo","unit","houseNo","listPrice","houseNum","buildArea"};
			String[] types = {"Integer","String","Double","String","String","String","Double","Integer","Double"};
			int total = baseDao.queryBySql(allOrderTotalSql).size();
			List<Order> orderList = baseDao.queryDTOBySql(allOrderListSql, Order.class, colums, types);
			List<Order> newList = new ArrayList<>();
			for(Order o : orderList){
				House h = (House) baseDao.loadById(House.class, o.getHouseNum());
				String houseTypeId = h.getHouseTypeId();
				if(houseTypeId!=null && !houseTypeId.equals("")){
					HouseType ht = (HouseType) baseDao.loadById(HouseType.class, houseTypeId);
					String picUrl = ht.getPhotoURL();
					if(picUrl!=null && !picUrl.equals("")){
						o.setHouseTypePic(picUrl);
					}
				}
				Double unitPrice = o.getListPrice() / o.getBuildArea();
				o.setUnitPrice(SysContent.get2Double(unitPrice));
				if(o.getBuyPrice()==null || o.getBuyPrice().equals("")){
					o.setBuyPrice(0.00);
				}
				newList.add(o);
			}
			page.setTotal(total);
			page.setRoot(newList);
		}
	}

	//获取订单管理 待审核订单数量
	@Override
	public Map<String, Object> findWaitCheckOrderCount(User user) {
		Map map = new HashMap<>();
		if(user!=null){
			String allOrderTotalSql = "SELECT c.recordNo,c.applyTime,c.buyPrice,h.buildingNo,h.unit,h.houseNo,h.listPrice "
					+ "FROM t_contractrecords c,t_projecthouses h WHERE c.houseNum=h.houseNum and c.recordStatus=0 and "
					+ "c.projectId = '"+user.getParentId()+"'";
			int total = baseDao.queryBySql(allOrderTotalSql).size();
			map.put("count", total);
		}
		return map;
	}

	//获取订单管理 待付款
	@Override
	public void findWaitPayMoneyOrderCount(User user, Page page) {
		if(user!=null){
			String allOrderTotalSql = "SELECT c.recordNo,c.applyTime,c.buyPrice,h.buildingNo,h.unit,h.houseNo,h.listPrice ,h.houseNum ,h.buildArea "
					+ "FROM t_contractrecords c,t_projecthouses h WHERE c.houseNum=h.houseNum and c.recordStatus=1 and c.voucherUploadTime is null"
					+ " and c.projectId = '"+user.getParentId()+"' ORDER BY c.applyTime DESC";
			String allOrderListSql = allOrderTotalSql + " LIMIT "+page.getStart()+","+page.getLimit();
			String[] colums = {"recordNo","applyTime","buyPrice","buildingNo","unit","houseNo","listPrice","houseNum","buildArea"};
			String[] types = {"Integer","String","Double","String","String","String","Double","Integer","Double"};
			int total = baseDao.queryBySql(allOrderTotalSql).size();
			List<Order> orderList = baseDao.queryDTOBySql(allOrderListSql, Order.class, colums, types);
			List<Order> newList = new ArrayList<>();
			for(Order o : orderList){
				House h = (House) baseDao.loadById(House.class, o.getHouseNum());
				String houseTypeId = h.getHouseTypeId();
				if(houseTypeId!=null && !houseTypeId.equals("")){
					HouseType ht = (HouseType) baseDao.loadById(HouseType.class, houseTypeId);
					String picUrl = ht.getPhotoURL();
					if(picUrl!=null && !picUrl.equals("")){
						o.setHouseTypePic(picUrl);
					}
				}
				Double unitPrice = o.getListPrice() / o.getBuildArea();
				o.setUnitPrice(SysContent.get2Double(unitPrice));
				if(o.getBuyPrice()==null || o.getBuyPrice().equals("")){
					o.setBuyPrice(0.00);
				}
				newList.add(o);
			}
			page.setTotal(total);
			page.setRoot(newList);
		}
	}

	//付款待确认
	@Override
	public void findWaitPayMoneyEnterOrder(User user, Page page) {
		if(user!=null){
			String allOrderTotalSql = "SELECT c.recordNo,c.applyTime,c.buyPrice,h.buildingNo,h.unit,h.houseNo,h.listPrice ,h.houseNum ,h.buildArea "
					+ "FROM t_contractrecords c,t_projecthouses h WHERE c.houseNum=h.houseNum and c.recordStatus=1 and c.voucherUploadTime is not null"
					+ " and c.projectId = '"+user.getParentId()+"' ORDER BY c.applyTime DESC";
			String allOrderListSql = allOrderTotalSql + " LIMIT "+page.getStart()+","+page.getLimit();
			String[] colums = {"recordNo","applyTime","buyPrice","buildingNo","unit","houseNo","listPrice","houseNum","buildArea"};
			String[] types = {"Integer","String","Double","String","String","String","Double","Integer","Double"};
			int total = baseDao.queryBySql(allOrderTotalSql).size();
			List<Order> orderList = baseDao.queryDTOBySql(allOrderListSql, Order.class, colums, types);
			List<Order> newList = new ArrayList<>();
			for(Order o : orderList){
				House h = (House) baseDao.loadById(House.class, o.getHouseNum());
				String houseTypeId = h.getHouseTypeId();
				if(houseTypeId!=null && !houseTypeId.equals("")){
					HouseType ht = (HouseType) baseDao.loadById(HouseType.class, houseTypeId);
					String picUrl = ht.getPhotoURL();
					if(picUrl!=null && !picUrl.equals("")){
						o.setHouseTypePic(picUrl);
					}
				}
				Double unitPrice = o.getListPrice() / o.getBuildArea();
				o.setUnitPrice(SysContent.get2Double(unitPrice));
				if(o.getBuyPrice()==null || o.getBuyPrice().equals("")){
					o.setBuyPrice(0.00);
				}
				newList.add(o);
			}
			page.setTotal(total);
			page.setRoot(newList);
		}
	}
	
	//获取订单管理 待签约
	@Override
	public void findWaitSignOrderCount(User user, Page page) {
		if(user!=null){
			String allOrderTotalSql = "SELECT c.recordNo,c.applyTime,c.buyPrice,h.buildingNo,h.unit,h.houseNo,h.listPrice ,h.houseNum ,h.buildArea "
					+ "FROM t_contractrecords c,t_projecthouses h WHERE c.houseNum=h.houseNum and c.recordStatus=4 and c.voucherUploadTime is not null"
					+ " and c.projectId = '"+user.getParentId()+"' ORDER BY c.applyTime DESC";
			String allOrderListSql = allOrderTotalSql + " LIMIT "+page.getStart()+","+page.getLimit();
			String[] colums = {"recordNo","applyTime","buyPrice","buildingNo","unit","houseNo","listPrice","houseNum","buildArea"};
			String[] types = {"Integer","String","Double","String","String","String","Double","Integer","Double"};
			int total = baseDao.queryBySql(allOrderTotalSql).size();
			List<Order> orderList = baseDao.queryDTOBySql(allOrderListSql, Order.class, colums, types);
			List<Order> newList = new ArrayList<>();
			for(Order o : orderList){
				House h = (House) baseDao.loadById(House.class, o.getHouseNum());
				String houseTypeId = h.getHouseTypeId();
				if(houseTypeId!=null && !houseTypeId.equals("")){
					HouseType ht = (HouseType) baseDao.loadById(HouseType.class, houseTypeId);
					String picUrl = ht.getPhotoURL();
					if(picUrl!=null && !picUrl.equals("")){
						o.setHouseTypePic(picUrl);
					}
				}
				Double unitPrice = o.getListPrice() / o.getBuildArea();
				o.setUnitPrice(SysContent.get2Double(unitPrice));
				if(o.getBuyPrice()==null || o.getBuyPrice().equals("")){
					o.setBuyPrice(0.00);
				}
				newList.add(o);
			}
			page.setTotal(total);
			page.setRoot(newList);
		}
	}

	//获取订单管理 已签约
	@Override
	public void findAlreadySignOrderCount(User user, Page page) {
		if(user!=null){
			String allOrderTotalSql = "SELECT c.recordNo,c.applyTime,c.buyPrice,h.buildingNo,h.unit,h.houseNo,h.listPrice ,h.houseNum ,h.buildArea "
					+ "FROM t_contractrecords c,t_projecthouses h WHERE c.houseNum=h.houseNum and c.recordStatus=5 and c.voucherUploadTime is not null"
					+ " and c.projectId = '"+user.getParentId()+"' ORDER BY c.applyTime DESC";
			String allOrderListSql = allOrderTotalSql + " LIMIT "+page.getStart()+","+page.getLimit();
			String[] colums = {"recordNo","applyTime","buyPrice","buildingNo","unit","houseNo","listPrice","houseNum","buildArea"};
			String[] types = {"Integer","String","Double","String","String","String","Double","Integer","Double"};
			int total = baseDao.queryBySql(allOrderTotalSql).size();
			List<Order> orderList = baseDao.queryDTOBySql(allOrderListSql, Order.class, colums, types);
			List<Order> newList = new ArrayList<>();
			for(Order o : orderList){
				House h = (House) baseDao.loadById(House.class, o.getHouseNum());
				String houseTypeId = h.getHouseTypeId();
				if(houseTypeId!=null && !houseTypeId.equals("")){
					HouseType ht = (HouseType) baseDao.loadById(HouseType.class, houseTypeId);
					String picUrl = ht.getPhotoURL();
					if(picUrl!=null && !picUrl.equals("")){
						o.setHouseTypePic(picUrl);
					}
				}
				Double unitPrice = o.getListPrice() / o.getBuildArea();
				o.setUnitPrice(SysContent.get2Double(unitPrice));
				if(o.getBuyPrice()==null || o.getBuyPrice().equals("")){
					o.setBuyPrice(0.00);
				}
				newList.add(o);
			}
			page.setTotal(total);
			page.setRoot(newList);
		}
	}

	//获取订单管理 已撤销
	@Override
	public void findAlreadyRevokeOrderData(User user, Page page) {
		if(user!=null){
			String allOrderTotalSql = "SELECT c.recordNo,c.applyTime,c.buyPrice,h.buildingNo,h.unit,h.houseNo,h.listPrice ,h.houseNum ,h.buildArea "
					+ "FROM t_contractrecords c,t_projecthouses h WHERE c.houseNum=h.houseNum and c.recordStatus=7 "
					+ " and c.projectId = '"+user.getParentId()+"' ORDER BY c.applyTime DESC";
			String allOrderListSql = allOrderTotalSql + " LIMIT "+page.getStart()+","+page.getLimit();
			String[] colums = {"recordNo","applyTime","buyPrice","buildingNo","unit","houseNo","listPrice","houseNum","buildArea"};
			String[] types = {"Integer","String","Double","String","String","String","Double","Integer","Double"};
			int total = baseDao.queryBySql(allOrderTotalSql).size();
			List<Order> orderList = baseDao.queryDTOBySql(allOrderListSql, Order.class, colums, types);
			List<Order> newList = new ArrayList<>();
			for(Order o : orderList){
				House h = (House) baseDao.loadById(House.class, o.getHouseNum());
				String houseTypeId = h.getHouseTypeId();
				if(houseTypeId!=null && !houseTypeId.equals("")){
					HouseType ht = (HouseType) baseDao.loadById(HouseType.class, houseTypeId);
					String picUrl = ht.getPhotoURL();
					if(picUrl!=null && !picUrl.equals("")){
						o.setHouseTypePic(picUrl);
					}
				}
				Double unitPrice = o.getListPrice() / o.getBuildArea();
				o.setUnitPrice(SysContent.get2Double(unitPrice));
				if(o.getBuyPrice()==null || o.getBuyPrice().equals("")){
					o.setBuyPrice(0.00);
				}
				newList.add(o);
			}
			page.setTotal(total);
			page.setRoot(newList);
		}
	}

	//获取订单管理 已否决
	@Override
	public void findAlreadyRefuseOrderData(User user, Page page) {
		if(user!=null){
			String allOrderTotalSql = "SELECT c.recordNo,c.applyTime,c.buyPrice,h.buildingNo,h.unit,h.houseNo,h.listPrice ,h.houseNum ,h.buildArea "
					+ "FROM t_contractrecords c,t_projecthouses h WHERE c.houseNum=h.houseNum and c.recordStatus=3 "
					+ " and c.projectId = '"+user.getParentId()+"' ORDER BY c.applyTime DESC";
			String allOrderListSql = allOrderTotalSql + " LIMIT "+page.getStart()+","+page.getLimit();
			String[] colums = {"recordNo","applyTime","buyPrice","buildingNo","unit","houseNo","listPrice","houseNum","buildArea"};
			String[] types = {"Integer","String","Double","String","String","String","Double","Integer","Double"};
			int total = baseDao.queryBySql(allOrderTotalSql).size();
			List<Order> orderList = baseDao.queryDTOBySql(allOrderListSql, Order.class, colums, types);
			List<Order> newList = new ArrayList<>();
			for(Order o : orderList){
				House h = (House) baseDao.loadById(House.class, o.getHouseNum());
				String houseTypeId = h.getHouseTypeId();
				if(houseTypeId!=null && !houseTypeId.equals("")){
					HouseType ht = (HouseType) baseDao.loadById(HouseType.class, houseTypeId);
					String picUrl = ht.getPhotoURL();
					if(picUrl!=null && !picUrl.equals("")){
						o.setHouseTypePic(picUrl);
					}
				}
				Double unitPrice = o.getListPrice() / o.getBuildArea();
				o.setUnitPrice(SysContent.get2Double(unitPrice));
				if(o.getBuyPrice()==null || o.getBuyPrice().equals("")){
					o.setBuyPrice(0.00);
				}
				newList.add(o);
			}
			page.setTotal(total);
			page.setRoot(newList);
		}
	}

	//获取订单管理 订单详情
	@Override
	public Map<String, Object> findOrderDeailDataById(User user, String orderId) throws Exception {
		Map<String,Object> map = new HashMap<>();
		if(user!=null && orderId!=null && !orderId.equals("")){
			//保存订单号
			map.put("orderNum", orderId);
			//获取订单
			ContractRecords cr = (ContractRecords) baseDao.loadById(ContractRecords.class, Integer.parseInt(orderId));
			//获取订单提交人
			String userId = cr.getUserId();
			User orderUser = (User) baseDao.loadById(User.class, userId);
			String name = orderUser.getUserCaption();
			map.put("orderSubmitMan", name);
			//订单动态
			map.put("submitOrder", "提交订单");
			map.put("submitTime", cr.getApplyTime());
			map.put("waitCheck", "待审核");
			map.put("applyTime", cr.getApplyTime());
			if(cr.getRecordStatus()!=null && !cr.getRecordStatus().equals("") && cr.getRecordStatus()==0){
				map.put("orderNeedCheck", 0);
			}else{
				map.put("orderNeedCheck", 1);
			}
			String ebHql = "from EnterBuy where projectId = '" + cr.getProjectId() + "'";
			EnterBuy eb = (EnterBuy) baseDao.loadObject(ebHql);// 获取订购规则
			map = OrderDynamic.setOrderDynamic(cr, map,eb);//订单状态
			//客户信息
			map.put("orderNature", cr.getOrderProperty());//0自购；1代购
			map.put("enterBuyName", cr.getCustomerName());
			map.put("idCard",cr.getCustomerIDCard());
			map.put("payMode",cr.getPayStyle());//0线上，1线下
			map.put("earnest", cr.getHaveToPayMoney());
			map.put("signTerm", 1);//0未签；1已签
			//价格优惠与结算方式
			House h = (House) baseDao.loadById(House.class, cr.getHouseNum());
			map.put("price",h.getListPrice());
			map.put("discount", cr.getBenefitInfo());
			map.put("enterBuyPrice", cr.getBuyPrice());
			map.put("payStyle", cr.getPayStyle());// 0线上 1线下
			//结算方式 5一次性1公积金2商贷按揭3公积金+商贷4其他
			if(cr.getAccountStyle()!=null && !cr.getAccountStyle().equals("")){
				if(cr.getAccountStyle()==1){
					map.put("accountStyle", "公积金");
				}else if(cr.getAccountStyle()==2){
					map.put("accountStyle", "商贷按揭");
				}else if(cr.getAccountStyle()==3){
					map.put("accountStyle", "公积金+商贷");
				}else if(cr.getAccountStyle()==5){
					map.put("accountStyle", "一次性");
				}else if(cr.getAccountStyle()==4){
					map.put("accountStyle", "其他");
				}
			}else{
				map.put("accountStyle", "未选择");
			}
			//房源信息
			HouseType ht = (HouseType) baseDao.loadById(HouseType.class, h.getHouseTypeId());
			map.put("housePic",ht.getPhotoURL());
			map.put("houseTypeName", ht.getHousType());
			map.put("houseId", h.getHouseNum());
			map.put("houseName", h.getBuildingNo()+"栋"+h.getUnit()+"单元"+h.getFloor()+"楼"+h.getHouseNo());
			map.put("willSellNo", h.getPresalePermissionInfo());
			map.put("houseArea", h.getBuildArea());
			map.put("houseType", ht.getCaption());
			//付款凭证
			map.put("payMoneyPic",cr.getCredentialsPhoto());
		}
		return map;
	}

	@Override
	public Comment findManagerCommentByVisitNo(Integer visitNo) {
		Comment c = (Comment)baseDao.loadObject("from Comment where visitNo="+visitNo);
		return c;
	}

	
}
