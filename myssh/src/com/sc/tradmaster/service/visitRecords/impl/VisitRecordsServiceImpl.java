package com.sc.tradmaster.service.visitRecords.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.tagService.AppTagManagerService;
import com.sc.tradmaster.service.visitRecords.VisitRecordsService;
import com.sc.tradmaster.service.visitRecords.impl.machine.AgentDTO;
import com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO.ProjectCustomersDTO;
import com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO.VisitCustomerDTO;
import com.sc.tradmaster.service.visitRecords.impl.visitRecordDTO.VisitDTO;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.SysContent;

@Service("visitRecordsService")
public class VisitRecordsServiceImpl implements VisitRecordsService{

	@Resource(name = "baseDao")
	private BaseDao baseDao;
	
	@Resource(name = "appTagManagerService")
	private AppTagManagerService appTagManagerService;
	/**
	 * 到访客户表
	 * @throws ParseException 
	 */	
	@Override
	public List<VisitCustomerDTO> findVisitCustomer(User user, String projectId, String startTime, String endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//当前中介的user信息
		String userId = user.getUserId();
		//当前获得的project
		//当前中介的该案场的备案记录表
		String grHQL = "from GuideRecords gr where gr.userId = '" + userId + "'" + "and gr.projectId = '" + projectId + "'"
				 + " and gr.applyStatus = 1";
		
		if (startTime != null && !"".equals(startTime)) {
			grHQL += " and gr.applyTime >= '" + startTime + "'";
		}
		if (endTime != null && !"".equals(endTime)) {
			grHQL += " and gr.applyTime <= '" + endTime + "'";
		}
		
		List<GuideRecords> grList = baseDao.findByHql(grHQL);
		//new map
		List<VisitCustomerDTO> vcdtoList = new ArrayList<>();
		if (grList != null) {
			//遍历当前中介的该案场的备案记录表
			for (GuideRecords guideRecords : grList) {
				//该条备案记录的客户对应的到访记录
				String hql = "from VisitRecords vr where vr.visitNo = '" + guideRecords.getVisitNo() + "'";
				VisitRecords vr = (VisitRecords) baseDao.loadObject(hql);
				if (vr != null && !"".equals(vr)){
					VisitCustomerDTO vcdto = new VisitCustomerDTO();
					vcdto.setCustomerName(guideRecords.getCustomerName());
					vcdto.setCustomerPhone(guideRecords.getCustomerPhone());
					vcdto.setApplyTime(guideRecords.getApplyTime());
					String userHQL = "from User u where u.userId = '" + vr.getUserId() + "'";
					User u = (User) baseDao.loadObject(userHQL);
					//一条map的到访客户信息 + 置业顾问的信息
					vcdto.setArriveTime(vr.getArriveTime());
					if (u != null && !"".equals(u)){
						vcdto.setUserCaption(u.getUserCaption());
						vcdto.setUserId(u.getUserId());
					}
					vcdtoList.add(vcdto);
				}
			}
		}
		/*for (int i = 0; i < vcdtoList.size(); i++) {
			for (int j = 0; j < vcdtoList.size()-1-i; j++) {
				if (sdf.parse(vcdtoList.get(j).getArriveTime()).getTime() < sdf.parse(vcdtoList.get(j+1).getArriveTime()).getTime()){
					VisitCustomerDTO vc = vcdtoList.get(j);
					vcdtoList.set(j, vcdtoList.get(j+1));
					vcdtoList.set(j+1, vc);
				}
			}
		}*/
		comper(vcdtoList);
		
		return vcdtoList;
	}
	public void comper(List list){
		Collections.sort(list, new Comparator<VisitCustomerDTO>() {
			@Override
			public int compare(VisitCustomerDTO o1, VisitCustomerDTO o2) {
				long o1Time = DateUtil.parse(o1.getArriveTime()).getTime();
				long o2Time = DateUtil.parse(o2.getArriveTime()).getTime();
				return (int) (o2Time-o1Time);
			}
		});
	}
	/**
	 * 下载到访客户表
	 * @throws ParseException 
	 */	
	@Override
	public void findVisit(String projectId, String arriveTime,Page p) throws ParseException {
		Date date = new Date();
		String hql = "from VisitRecords vr where vr.projectId = '" + projectId + "'";
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (arriveTime != null && !"".equals(arriveTime)) {
			date = sdf.parse(arriveTime);
			//new List
			String startTime = SysContent.getStartTime2(date);
			String endTime = SysContent.getEndTime2(date);
			hql += " and vr.arriveTime >= '" + startTime + "' and vr.arriveTime <= '" + endTime + "'";
		}
		
		
		List<VisitRecords> vList = baseDao.findByHql(hql, p.getStart(), p.getLimit());
		List<VisitDTO> vdtoList = new ArrayList<>();
		for (VisitRecords vr : vList) {
			VisitDTO vdto = new VisitDTO();
			vdto.setVisitNo(vr.getVisitNo().toString());
			vdto.setUserId(vr.getUserId());
			vdto.setProjectId(vr.getProjectId());
			vdto.setVisitStatus(vr.getVisitStatus());
			vdto.setCustomerCount(vr.getCustomerCount());
			vdto.setCustomerName(vr.getCustomerName());
			vdto.setPhone(vr.getPhone());
			vdto.setRecordNo(vr.getRecordNo());
			vdto.setArriveTime(vr.getArriveTime());
			vdto.setReceptTime(vr.getReceptTime());
			vdto.setLeaveTime(vr.getLeaveTime());
			vdto.setAppointUserId(vr.getAppointUserId());
			vdto.setCustomerId(vr.getCustomerId());
			vdto.setDescription(vr.getDescription());
			vdto.setTags(vr.getTags());
			vdto.setWriteState(vr.getWriteState());
			vdtoList.add(vdto);
		}
		
		p.setTotal(vdtoList.size());
		p.setRoot(vdtoList);
		//返回当天的到访记录
	}

	/**
	 * 置业顾问到访客户表
	 */
	@Override
	public List<ProjectCustomersDTO> findSaleVisitList(User user) {
		String pcHQL = "from ProjectCustomers pc where pc.projectId = '" + user.getParentId()
		+ "' and pc.ownerUserId = '" + user.getUserId() + "' and pc.lastTime is not null  order by lastTime DESC";
		List<ProjectCustomers> pcList = baseDao.findByHql(pcHQL);
		
		List<ProjectCustomersDTO> pcdtoList = new ArrayList<>();
		
		for (ProjectCustomers pc : pcList) {
			ProjectCustomersDTO pcdto = new ProjectCustomersDTO();
			pcdto.setProjectCustomerName(pc.getProjectCustomerName());
			pcdto.setProjectCustomerPhone(pc.getProjectCustomerPhone());
			pcdto.setLastTime(pc.getLastTime());
			pcdto.setProjectCustomerId(pc.getProjectCustomerId());
			pcdto.setYixiang(appTagManagerService.findCustomerYiXiang(pc.getProjectCustomerId(), pc.getProjectId()));
		
			pcdtoList.add(pcdto);
		}
		
		return pcdtoList;
	}
	
	//通过id获取到访记录
	@Override
	public VisitRecords findVisitById(Integer visitNo) {
		return (VisitRecords) baseDao.loadById(VisitRecords.class, visitNo);
	}
	
	
	@Override
	public Map<String, Object> findOneDayVisitNum(String startTime, String endTime,String projectId) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance(); 
		Date dBegin = sdf.parse(startTime);  
	    Date dEnd = sdf.parse(endTime);  
		List<Date> lDate = findDates(dBegin, dEnd); 
		int sunday = 0;
		int sundayNum = 0;
		int monday = 0;
		int mondayNum = 0;
		int tuesday = 0;
		int tuesdayNum = 0;
		int wednesday = 0;
		int wednesdayNum = 0;
		int thursday = 0;
		int thursdayNum = 0;
		int friday = 0;
		int fridayNum = 0;
		int saturday = 0;
		int saturdayNum = 0;
		for (Date date : lDate) {
			String d = sdf2.format(date);
			Date dstart = sdf2.parse(d);
			List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where arriveTime >= '"+sdf2.format(DateUtil.getIntegralStartTime(dstart))+"' and arriveTime <= '"+ sdf2.format(DateUtil.getIntegralEndTime(dstart)) +"' and projectId='"+projectId+"' ");
	        c.setTime(date);
	        int weekday=c.get(Calendar.DAY_OF_WEEK);
	        if (weekday == 1){
	        	sundayNum += vrList.size();
	        	sunday ++;
	        }
	        if (weekday == 2){
	        	mondayNum += vrList.size();
	        	monday ++;
	        }
	        if (weekday == 3){
	        	tuesdayNum += vrList.size();
	        	tuesday ++;
	        }
	        if (weekday == 4){
	        	wednesdayNum += vrList.size();
	        	wednesday ++;
	        }
	        if (weekday == 5){
	        	thursdayNum += vrList.size();
	        	thursday ++;
	        }
	        if (weekday == 6){
	        	fridayNum += vrList.size();
	        	friday ++;
	        }
	        if (weekday == 7){
	        	saturdayNum += vrList.size();
	        	saturday ++;
	        }
		}
		map.put("周日", (double)sundayNum/(double)sunday);
		map.put("周日个数",monday);
        map.put("周一", (double)mondayNum/(double)monday);
        map.put("周一个数",monday);
        map.put("周二", (double)tuesdayNum/(double)tuesday);
        map.put("周二个数",tuesday);
        map.put("周三", (double)wednesdayNum/(double)wednesday);
        map.put("周三个数",wednesday);
        map.put("周四", (double)thursdayNum/(double)thursday);
        map.put("周四个数",thursday);
        map.put("周五", (double)fridayNum/(double)friday);
        map.put("周五个数",friday);
        map.put("周六", (double)saturdayNum/(double)saturday);
        map.put("周六个数",saturday);
        
		return map;
	}
	
	public static List<Date> findDates(Date dBegin, Date dEnd)  
	 {  
	  List lDate = new ArrayList();  
	  lDate.add(dBegin);  
	  Calendar calBegin = Calendar.getInstance();  
	  // 使用给定的 Date 设置此 Calendar 的时间  
	  calBegin.setTime(dBegin);  
	  Calendar calEnd = Calendar.getInstance();  
	  // 使用给定的 Date 设置此 Calendar 的时间  
	  calEnd.setTime(dEnd);  
	  // 测试此日期是否在指定日期之后  
	  while (dEnd.after(calBegin.getTime()))  
	  {  
	   // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
	   calBegin.add(Calendar.DAY_OF_MONTH, 1);  
	   lDate.add(calBegin.getTime());  
	  }  
	  return lDate;  
	 }
	@Override
	public Map<String, Object> findOneDayVisitNumTwo(String startTime, String endTime, String projectId)
			throws ParseException {
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance(); 
		Date dBegin = sdf.parse(startTime);  
	    Date dEnd = sdf.parse(endTime);  
		List<Date> lDate = findDates(dBegin, dEnd);
		
		int one = 0;
		int two = 0;
		int three = 0;
		int four = 0;
		int five = 0;
		int six = 0;
		
		for (Date date : lDate) {
			String d = sdf2.format(date);
			Date dstart = sdf2.parse(d);
			int eight1 = 8;
			String eight = DateUtil.addDateMinut(sdf2.format(DateUtil.getIntegralStartTime(dstart)), 8);
			String nine = DateUtil.addDateMinut(sdf2.format(DateUtil.getIntegralStartTime(dstart)), 10);
//			String nine = sdf2.format(DateUtil.getIntegralEndTime(sdf2.parse(time)));
			int x = 0;
			for (int i = 0; i < 6; i++) {
				List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where arriveTime >= '"+eight+"' and arriveTime <= '"+ nine +"' and projectId='"+projectId+"' ");
				if (eight1 == 8 ){
					one += vrList.size();
				}
				if (eight1 == 10 ){
					two += vrList.size();
				}
				if (eight1 == 12 ){
					three += vrList.size();
				}
				if (eight1 == 14 ){
					four += vrList.size();
				}
				if (eight1 == 16 ){
					five += vrList.size();
				}
				if (eight1 == 18 ){
					six += vrList.size();
				}
				x += 2;
				eight = DateUtil.addDateMinut(sdf2.format(DateUtil.getIntegralStartTime(dstart)), 8+x);
				nine = DateUtil.addDateMinut(sdf2.format(DateUtil.getIntegralStartTime(dstart)), 10+x);
				eight1 += 2;
			}
		}
		map.put("8-10:", (double)one/(double)lDate.size());
		map.put("10-12:", (double)two/(double)lDate.size());
		map.put("12-14:", (double)three/(double)lDate.size());
		map.put("14-16:", (double)four/(double)lDate.size());
		map.put("16-18:", (double)five/(double)lDate.size());
		map.put("18-20:", (double)six/(double)lDate.size());
		
		return map;
	}

	//更新到访记录中的客户信息
	@Override
	public void addOrUpdateVisitByVisitNo(Integer visitNo, ProjectCustomers old) {
		if(visitNo!=null && !visitNo.equals("")){
			VisitRecords vr = (VisitRecords) baseDao.loadById(VisitRecords.class, visitNo);
			vr.setCustomerName(old.getProjectCustomerName());
			vr.setDescription(old.getDescription());
			baseDao.saveOrUpdate(vr);
		}
	}  
	
	
}
