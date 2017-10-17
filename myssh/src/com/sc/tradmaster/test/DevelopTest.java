package com.sc.tradmaster.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.LogRecords;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.ProjectPics;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.controller.common.OrderWillExpiringSmsQuartz;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.dao.impl.BaseDaoImpl;
import com.sc.tradmaster.service.agent.AgentVisitRecordService;
import com.sc.tradmaster.service.agent.impl.VisitRecordServiceImpl;
import com.sc.tradmaster.service.director.ProjectReceiveService;
import com.sc.tradmaster.service.director.impl.ProjectReceiveServiceImpl;
import com.sc.tradmaster.service.director.impl.dto.AgentOrderDTO;
import com.sc.tradmaster.service.project.impl.dto.SeeBuyApplyDTO;
import com.sc.tradmaster.service.visitRecords.impl.machine.AgentDTO;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.JavaSmsApi;
import com.sc.tradmaster.utils.LogRecordsUtill;
import com.sc.tradmaster.utils.ObjClone;
import com.sc.tradmaster.utils.PropertiesUtil;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;
import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DevelopTest {

	@Resource(name = "baseDao")
	private static BaseDao baseDao;

	@Test
	public void test() {

	}

	public static void main(String[] args) throws Exception {
		
		String s = DateUtil.addDateMinut("2017-10-12 08:00:00", 2);
		System.out.println(s);
		
		
		/*VisitRecords v = new VisitRecords();
		v.setVisitNo(1);
		v.setAppointUserId("123464654");
		v.setUserId("123464654");
		v.setArriveTime("2017-10-11 15:29:00");
		System.out.println(v);
		VisitRecords newV = (VisitRecords) ObjClone.myClone(VisitRecords.class.getName(),v);
		System.out.println(newV);
		*/
		
		/*//变量
		String a = "123";
		String b = "123";
		//对象
		String a0 = new String("123");
		String b0 = new String("123");
		
		//结果输出
		System.out.println(a==b);	//变量与变量==比 true
		System.out.println(a==a0);	//变量与对象==比 false
		System.out.println(a0==b0);	//对象与对象==比 false
		System.out.println(a.equals(a0));	//变量与对象equals比 true
		System.err.println(a0.equals(b0));	//对象与对象equals比 true
		*/
		
		/*Boolean b = SysContent.addSameMouthComperWithToday("2017-10-10 08:00:00", "2017-08-10 08:00:00", 2);
		System.out.println(b);*/
		//String p = SysContent.md5("514658");
		//System.out.println(p);
		
		//Boolean s = SysContent.addSameDaysComperWithToday("2017-09-01 08:30:00", "2017-09-01 09:45:00", 20);
		//System.out.println(s);
		
		/*AgentDTO ad1 = new AgentDTO();
		ad1.setSignInTime("2017-01-02 00:00:00");
		
		AgentDTO ad2 = new AgentDTO();
		ad2.setSignInTime("2017-01-01 00:00:00");
		
		AgentDTO ad3 = new AgentDTO();
		ad3.setSignInTime("2017-01-03 00:00:00");
		List<AgentDTO> list = new ArrayList<>();
		list.add(ad1);
		list.add(ad2);
		list.add(ad3);
		for(AgentDTO adt : list){
			System.out.print(adt.getSignInTime());
			System.out.print(",");
		}
		SysContent.comper(list);
		System.out.println("-----------");
		for(AgentDTO adt : list){
			System.out.print(adt.getSignInTime());
			System.out.print(",");
		}*/
		
		/*
		 * Calendar cal = Calendar.getInstance(); Date time1 = cal.getTime();
		 * //下面的就是把当前日期加12个月 cal.add(Calendar.MONTH, 12); Date time2 =
		 * cal.getTime(); int v = time2.compareTo(time1);//time2-time1
		 * System.out.println(v);
		 */

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * String s = "2017-02-03 05:55:02"; Date d = sdf.parse(s);
		 * System.out.println(d); Calendar cal = Calendar.getInstance();
		 * cal.setTime(d); System.out.println(cal);
		 * System.out.println(cal.getTime());
		 */

		/*
		 * DecimalFormat df = new DecimalFormat("#.##"); Double d = 2.33889;
		 * System.out.println(df.format(d));
		 */

		/*
		 * BaseController bc = new BaseController(); List list = new
		 * ArrayList<>(); for(int i = 0;i<3;i++){ list.add(new
		 * SeeBuyApplyDTO()); } Map<String,Object> map = new HashMap<>();
		 * map.put("house", new House()); map.put("list",list );
		 * System.out.println(map); bc.outObjectString(map, null);
		 */

		/*
		 * Double v = (double) DateUtil.getOffsetMinute(new
		 * Date(),DateUtil.parse("2017-02-16 23:30:00")); Double d = (double)
		 * (v/1000/3600); DecimalFormat df = new DecimalFormat("#.##");
		 * System.out.println(d); System.out.println(df.format(d));
		 */

		/*
		 * Map<String,Object> map = new HashMap<>(); int houseNum = 123;
		 * map.put("houseKey", houseNum); map.put("'"+houseNum+"'", new
		 * ArrayList<>()); System.out.println(map.get(map.get("houseKey")));
		 */

		/*
		 * System.out.println("--------------------开始定时任务---------------------")
		 * ; String date = DateTime.toString1(DateUtil.parse(
		 * "2017-02-17 12:54:00")); String recordNo = "1122356"; String phone =
		 * "15639196571"; //您好！来自#userName#的购买申请，房源名称为#houseName#，已经打款，请及时确认！
		 * String content = SmsContext.PLAY_MONEY_ENTER.replace("#userName#",
		 * "testSms").replace("#houseName#", "1栋2单元201");
		 * OrderWillExpiringSmsQuartz.addJob(date, recordNo, phone, content);
		 */

		/*
		 * String maxPalyMoneyTimeStr = SysContent.addSameHours(
		 * "2017-02-17 21:30:00", 4);//最晚打款时间 String date =
		 * SysContent.addSameMinute(maxPalyMoneyTimeStr, -30);//短信发送时间
		 * System.out.println(date);
		 */

		/*
		 * Boolean v = SysContent.doubleDateComper("2017-02-17 20:00:00",
		 * "2017-02-17 21:00:00"); System.out.println(v);
		 */
		/*
		 * String s = SysContent.getEndTime2(DateUtil.parse(
		 * "2017-03-12 20:02:30")); System.out.println(s);
		 */

		/*
		 * BaseDao baseDao = new BaseDaoImpl();
		 * 
		 * VisitRecords vr = new VisitRecords();//实例化对象，属性值为null
		 * 
		 * if(vr!=null){ System.out.println("持久化前"+vr.getVisitNo());//结果为null }
		 * 
		 * baseDao.saveOrUpdate(vr);//调用数据库保存方法，id为自增，这时的vr在数据库中id是存在
		 * 
		 * if(vr!=null && !vr.getVisitNo().equals(0) && vr.getVisitNo()!=null){
		 * System.out.println("持久化后："+vr.getVisitNo());
		 * //这里的vr是持久前的对象还是持久后的对象，自增的id可以取到吗？ }
		 */
		// String str = DateTime.getAddSameWeeksNewDay("2017-011-05
		// 13:30:00",2);
		// System.out.println(str);
		/*
		 * System.out.println(SysContent.getEndTime2(new Date()));
		 * System.out.println(SysContent.getStartTime2(new Date()));
		 */

		/*
		 * TreeSet set = new TreeSet<>(); set.add("一室 一厅"); set.add("一室 一厅");
		 * set.add("一室 一厅"); set.add("一室 一厅"); set.add("两室 一厅"); BaseController
		 * bs = new BaseController(); bs.outSetString(set);
		 */

		/*
		 * String token =
		 * "123456789wqw-20170302182907886-10f0ad37efdb4c27bf677a9b11906878";
		 * String userId = token.substring(0, token.indexOf('-'));
		 * System.out.println(userId);
		 */
		//
		// String mdStr = encryptToMD5("1234564");
		// System.out.println(mdStr);

		// System.out.println(Integer.TYPE);
		//
		//
		// String mdStr = encryptToMD5("1234564");
		// System.out.println(mdStr);
		// System.out.println(Integer.TYPE);
		/*
		 * System.out.println(Integer.TYPE);
		 * 
		 * String mdStr = encryptToMD5("1234564"); System.out.println(mdStr);
		 * 
		 * String mdStr = encryptToMD5("1234564"); System.out.println(mdStr);
		 */

		/*
		 * String proPicsId = "[aaa]"; List<String> urlList = new ArrayList<>();
		 * if(proPicsId!=null && !proPicsId.equals("")){ //ProjectPics pps =
		 * (ProjectPics) baseDao.loadById(ProjectPics.class, proPicsId);
		 * //if(pps!=null){ String picUrl = proPicsId; picUrl =
		 * picUrl.substring(1, picUrl.length()-1); if(picUrl.indexOf(",")!=-1){
		 * String[] picUrlStr = picUrl.split(","); urlList =
		 * Arrays.asList(picUrlStr); }else{ urlList.add(picUrl); } //}
		 * for(String s : urlList){ System.out.println(s); } }
		 */
		// String str = " dfadf ";
		// System.out.println(str.trim());

		/*
		 * ProjectGuide pg = new ProjectGuide(); pg.setIsDaiKan(0);
		 * pg.setCustormerProtectDays("20"); //pg.setDaiKanMoney(0.3);
		 * pg.setDaiKanMoney(0.5); pg.setDescription("qq"); GuideRecords grs =
		 * new GuideRecords(); grs.setRecordNo(1); grs.setRules(pg.toString());
		 * //System.out.println(grs); //System.out.println(grs.getRules());
		 * HashMap grMap = JSON.parseObject(grs.getRules(), HashMap.class);
		 * System.out.println(grMap);
		 * System.out.println(grMap.get("daiKanMoney"));
		 */
		// {projectId:10000, isAvailable:null, validDays:10, payType:null,
		// rewardMoney:null,
		// rewardpercent:null, description:null, isDaiKan:1, isYiDi:1,
		// yiDiSalesCommission:0.0,
		// yiDiValidity:3, isFast:1, daiKanMoney:0.01, fenXiaoMoney:0.02,
		// custormerProtectDays:5}

		// 对象json转换
		
		 // ProjectGuide pg = null; 
		 /* pg.setProjectId("10000");
		  pg.setValidDays(10); 
		  pg.setIsDaiKan(1);
		  pg.setIsYiDi(1);
		  pg.setYiDiSalesCommission(0.0);
		  pg.setYiDiValidity("3");
		  pg.setIsFast(1); 
		  pg.setDaiKanMoney(0.01); 
		  pg.setFenXiaoMoney(0.02);
		  pg.setCustormerProtectDays("5"); */
		  //JSONObject json = JSONObject.fromObject(pg);
		  //System.out.println(json.toString());
		  //HashMap mapStr = JSON.parseObject(json.toString(), HashMap.class);
		  //System.out.println(json.toString());

		// 短信接口，模版测试
		/*
		 * //订购申请 提醒案场助理及时审核 public static String BUY_APPLY =
		 * "您收到一笔来自#userName#的购买申请，购买的房源名称为#houseName#，价格为#money#元，请及时审核！";
		 * //订购申请 同意订购 提醒申请人通知客户 public static String AGREE_BUY_APPLY =
		 * "您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，请及时处理！";
		 * //订购申请 拒绝订购 提醒申请人通知客户 public static String REFUSE_BUY_APPLY =
		 * "您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，原因为：#reason#";
		 * //打款确认 提醒案场助理及时确认 public static String PLAY_MONEY_ENTER =
		 * "您好！来自#userName#的购买申请，房源名称为#houseName#，已经打款，请及时确认！"; //打款即将到期提醒
		 * 提醒申请人通知客户及时大款 public static String PLAY_MONEY_WILL_OUT_TIME =
		 * "您的申请# projectName #项目，房源名称为#houseName#，客户姓名：# customerName #，已经被#state#，打款即将超时，请及时处理！"
		 * ;
		 */
		/*
		 * String str1 =
		 * "【九邑房源在线】您收到一笔来自#置业顾问/中介经纪人#的购买申请，购买的房源名称为中信，价格为10万元，请及时审核！";//#置业顾问/
		 * 中介经纪人#内容无法替换
		 * 
		 * String str2 =
		 * "【九邑房源在线】您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，请及时处理！";
		 * String text = SmsContext.AGREE_BUY_APPLY; JavaSmsApi.sendSms(str2,
		 * "15639196571"); //JavaSmsApi.sendSms(str, "15558011520");
		 * System.out.println(text);
		 */

		/*
		 * String a = DateTime.toString1(new Date()); String v =
		 * DateTime.toString1(new Date()); int d = 10; Boolean b
		 * =SysContent.addSameDaysComperWithToday(a,v,d); System.out.println(b);
		 */
		
		/*
		 * JSONObject json = JSONObject.fromObject(map);
		 * System.out.println(json); String k = json.get("key2").toString();
		 */
		// System.out.println(map);

		// List list = new ArrayList<>();
		// boolean isValue = list.isEmpty();
		// System.out.println(list);
		// System.out.println(!isValue);
		//
		// List list = new ArrayList<>();
		// boolean isValue = list.isEmpty();
		// System.out.println(list);
		// System.out.println(!isValue);
		/*
		 * List list = new ArrayList<>(); boolean isValue = list.isEmpty();
		 * System.out.println(list); System.out.println(!isValue);
		 */

		// String pw = "2059";
		// String password = SysContent.md5(pw);
		// System.out.println(password);

		/*
		 * //加密字符串 String str = SysContent.md5("123456"); String pww =
		 * SysContent.md5(str); System.out.println(pww);
		 */

		/*
		 * Double dou = 3.00; String d = SysContent.get2Double(dou);
		 * System.out.println(d);
		 */
		/*
		 * String realEnterBuyManId = "[1,2,3,4,5]"; List list =
		 * JSON.parseArray(realEnterBuyManId); System.out.println(list); for(int
		 * i=0;i<list.size();i++){ System.out.println(list.get(i)); }
		 */

		/*
		 * Boolean b = SysContent.doubleDateComper("2017-05-01 08:00:00",
		 * "2017-05-01 14:00:00"); System.out.println(b);
		 */
		
		/*Map<String,String> day = DateUtil.getPastAnyDaysOfDate(new Date(),15);
		String start = day.get("pastAnydaysStartDay");
		String end = day.get("currentDateEndDay");
		System.out.println(start);
		System.out.println(end);
		
		List list = DateUtil.getBetweenTwoDateOfEveryDay(start,end);
		System.out.println(list);*/
		/*List newList = new ArrayList<>();
		List workTime = SysContent.getWorkTime(new Date());
		newList.addAll(workTime.subList(0, workTime.size()-1));
		System.out.println(workTime);
		System.out.println(newList);*/
		
		/*String chartHql = "select count(*) from VisitRecords where projectId = '121'";
		StringBuilder sb = new StringBuilder();
		sb.append(chartHql);
				
		for(int i=0;i<3;i++){
			sb.append(" and receptTime >= "+i+" and receptTime < "+(i+1));
			String sbStr = sb.toString();
			sb.delete(sbStr.indexOf(" and"),sbStr.length());
			System.out.println(sbStr);
		}*/
		//当前日期
		/*Date date = new Date();
		//昨天日期
		Date yesterDay = DateUtil.rollDay(date, -1);
		List newYesterDayWorkTime = new ArrayList<>();
		List yesterDayWorkTime = SysContent.getWorkTime(yesterDay);
		System.out.println(yesterDayWorkTime);
		newYesterDayWorkTime.addAll(yesterDayWorkTime.subList(0, yesterDayWorkTime.size()-1));
		System.out.println(newYesterDayWorkTime);*/
		/*Map<String, String> day = DateUtil.getPastAnyDaysOfDate(new Date(),7);
		String start = day.get("pastAnydaysStartDay");
		String end = day.get("currentDateEndDay");
		Date startDate = DateUtil.parse(start, DateUtil.PATTERN_CLASSICAL_SIMPLE);
		Date endDate = DateUtil.parse(end, DateUtil.PATTERN_CLASSICAL_SIMPLE);
		List workTime = SysContent.getWorkTime(startDate,endDate);
		System.out.println(workTime);
		for(int i=0;i<workTime.size();i++){
			System.out.println(workTime.get(i));
		}*/
		
		/*Map<String, String> map = DateUtil.getPastAnyMonthOfDate(new Date(),6);
		Date start = DateUtil.parse(map.get("pastAnydaysStartDay"),DateUtil.PATTERN_CLASSICAL_SIMPLE);
		Date end = DateUtil.parse(map.get("currentDateEndDay"),DateUtil.PATTERN_CLASSICAL_SIMPLE);
		List<String> list = SysContent.getWorkTimeForMonth(start,end);
		System.out.println(list);
		String cur = null;
		for(int i=0;i<list.size();i++){
			cur = list.get(i);
			String startStr = DateUtil.format(DateUtil.getIntegralStartTime(DateUtil.parse(cur,DateUtil.PATTERN_CLASSICAL_SIMPLE)),DateUtil.PATTERN_CLASSICAL);
			String endStr = DateUtil.format(DateUtil.getMonthEndTime(DateUtil.parse(cur,DateUtil.PATTERN_CLASSICAL_SIMPLE)),DateUtil.PATTERN_CLASSICAL);
			System.out.println(startStr);
			System.out.println(endStr);
		}*/
		/* Object sum = 3.45645654E9;
		 Double dsum = Double.valueOf(sum.toString());
			DecimalFormat df = new DecimalFormat("#.##");
			df.setMinimumFractionDigits(2);
			sum = df.format(dsum);
			System.out.println(sum);*/
		/* List list = new ArrayList<>();
		 for(int i=0;i<3;i++){
			 Map m = new HashMap<>();
			 m.put("num", i);
			 m.put(i, "h"+i);
			 list.add(m);
		 }
		 //System.out.println(list);
		 String jStr = JSON.toJSONString(list);
		 //System.out.println(jStr);
		 //com.alibaba.fastjson.JSONArray jArray = JSON.parseArray(jStr);
		 //System.out.println(jArray);
		 List<Map> jList = JSON.parseObject(jStr, List.class);
		 System.out.println(jList);
		 for(Map m : jList){
			 System.out.println(m);
			 String nk = m.get("num").toString();
			 System.out.println(m.get("num"));
			 System.out.println(m.get(m.get("num").toString()));
		 }*/
		//String filePath = SysContent.getRequest().getServletContext().getRealPath("autoCreateText.txt");
		//System.out.println(filePath);
		
		
		/*List l = SysContent.getWorkTime(new Date());
		System.out.println(l);
		for(int i=0;i<l.size()-1;i++){
			System.out.println(l.get(i+1));
		}*/
		/************************************httpclit******************************/
		
		
	}

	/*public static void postFun(){
		CloseableHttpClient hc = HttpClients.createDefault();
		HttpPost hp = new HttpPost(uri);
		
	}*/
	
	//轮排算法
	public static List sort(List<AgentDTO> li){
		List results = new ArrayList<>();
        List teamA = new ArrayList<>();
        List teamB = new ArrayList<>();
        for(AgentDTO ad : li){
        	if(ad.getGroupName().equals(2)){
        		teamB.add(ad);
        	}else{
        		teamA.add(ad);
        	}
        }
        boolean lastIsTeamA = true;
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
		return results;
	}
	
	
	// 加密算法
	public static String encryptToMD5(String info) {
		byte[] digesta = null;
		try {
			// 得到一个md5的消息摘要
			MessageDigest alga = MessageDigest.getInstance("MD5");
			// 添加要进行计算摘要的信息
			alga.update(info.getBytes());
			// 得到该摘要
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将摘要转为字符串
		String rs = byte2hex(digesta);
		return rs;
	}

	public static String byte2hex(byte[] md5Bytes) {
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
}
