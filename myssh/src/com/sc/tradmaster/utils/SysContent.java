package com.sc.tradmaster.utils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.service.visitRecords.impl.machine.AgentDTO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class SysContent {
	
	public static ApplicationContext WEB_APP_CONTEXT = null;
	public static final String backUpPath = "D:\\tools\\mastor\\upload\\imgs";
	
	
	
	public static void backUploadPics(MultipartFile pic,String fileName) throws IOException{
		String rpn = backUpPath + "\\"+fileName;
		File file = new File(rpn);
		FileUtils.copyInputStreamToFile(pic.getInputStream(), file);
	}
	/**
	 * 获取request
	 * @return 
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
		return request;
	}
	/**
	 * 获取Session
	 * @return
	 */
	public static HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
        HttpSession session = request.getSession();  
		return session;
	}
	/**
	 * 主键生成方法
	 * @return
	 */
	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp;
		temp = str.replaceAll("-", "");
		return temp;
	}

	/**
	 * 密码加密方法
	 * @param data
	 * @return
	 */
	public static String md5(String data) {
		String _date = org.apache.commons.codec.digest.DigestUtils.md5Hex(data);
		return _date;
	}
	
	/**
	 * 上传图片路径工具
	 * @param system
	 * @return
	 */
	public static String getFilesUploadPath(String system){
		String s = File.separator;
		if(system.equals("Window")){
			return "D:/tools/mastor/uploadPic/";
		}else{
			return s+"root"+s+"develop"+s+"uploadPic"+s;
		}
	}
	
	/**
	 * 文件上传，为文件重新命名
	 **/
	public static String getFileRename(String name) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String sdfDate = sdf.format(date);
        int pos = name.lastIndexOf(".");
        String suffix = name.substring(pos);
        String rename = sdfDate+suffix;
        return rename;
    }
	
	/**
	 * 字符串解析
	 * @param rsContent
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> strToList(String rsContent) throws Exception {
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray arry = new JSONArray();
		if (rsContent != null && !rsContent.equals("")) {
			arry = JSONArray.fromObject(rsContent, jsonConfig);
		}
		List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < arry.size(); i++) {
			JSONObject jsonObject = arry.getJSONObject(i);
			Map<String, String> map = new HashMap<String, String>();
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				String value = jsonObject.get(key).toString();
				map.put(key, value);
			}
			rsList.add(map);
		}
		return rsList;
	}
	
	/**
	 * 当前时间加上n个月
	 * @param m
	 * @return
	 * @throws ParseException 
	 */
	public static Boolean addSameMouthComperWithToday(String arriveTime,String lastTime ,int m) throws ParseException{
		//到访时间  < 上次当房时间+客户保护期(在客户保护期内)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date at = sdf.parse(arriveTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(at);
		Date time1 = cal.getTime();
		String time1Str = sdf.format(time1);
		
		Calendar arrCal = Calendar.getInstance();
		Date lasTime = sdf.parse(lastTime);
		arrCal.setTime(lasTime);
		//截至保护期时间
		arrCal.add(Calendar.MONTH, m);
		Date time2 = arrCal.getTime();
		String time2Str = sdf.format(time2);
		//time2>time1 返回1 time2=time1 返回0 time2<time1 返回 -1 
		int v = time2.compareTo(time1);
		if(v >= 0){
			return true;
		}
		return false;
	} 
	
	/**
	 * 获取某个时间加上n天
	 * @param applyTime
	 * @param arriveTime
	 * @param d
	 * @return
	 * @throws ParseException
	 */
	public static Boolean addSameDaysComperWithToday(String applyTime,String arriveTime,int d) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date appTime = sdf.parse(applyTime);
		Date arrTime = sdf.parse(arriveTime);
		
		Calendar appCal = Calendar.getInstance();
		appCal.setTime(appTime);
		Calendar arrCal = Calendar.getInstance();
		arrCal.setTime(arrTime);
		Date time2 = arrCal.getTime();
		//加n天
		//appCal.add(Calendar.DATE, d);
		appCal.add(Calendar.HOUR_OF_DAY, d);
		Date time1 = appCal.getTime();
		int v = time2.compareTo(time1);
		System.out.println("a"+time1);
		System.out.println("v"+time2);
		if(v < 0){
			return true; //超时的
		}
		return false;//未超时
	}
	
	public static Boolean doubleDateComper(String dateStr1,String dateStr2) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateTime1 = sdf.parse(dateStr1);
		Date dateTIme2 = sdf.parse(dateStr2);
		//dateTIme2>dateTime1 返回1 = 返回0 < 返回-1
		//dateTIme2>=dateTime1 返回true
		int v = dateTIme2.compareTo(dateTime1);
		if(v >= 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取某个时间加上n小时
	 * @param applyTime
	 * @param arriveTime
	 * @param d
	 * @return
	 * @throws ParseException
	 */
	public static String addSameHours(String timeStr,int h) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date appTime = sdf.parse(timeStr);
		
		Calendar appCal = Calendar.getInstance();
		appCal.setTime(appTime);
		//加n小时
		appCal.add(Calendar.HOUR_OF_DAY, h);
		String newDateStr = sdf.format(appCal.getTime());
		//System.out.println(newDateStr);
		return newDateStr;
	}
	
	/**
	 * 获取某个时间加上分钟
	 * @param timeStr
	 * @param m
	 * @return
	 * @throws ParseException
	 */
	public static String addSameMinute(String timeStr,int m) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date appTime = sdf.parse(timeStr);
		
		Calendar appCal = Calendar.getInstance();
		appCal.setTime(appTime);
		//加n小时
		appCal.add(Calendar.MINUTE, m);
		String newDateStr = sdf.format(appCal.getTime());
		//System.out.println(newDateStr);
		return newDateStr;
	}
	
	/**
	 * 获取当天的起始时间
	 * @return
	 */
	public static String getStartTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return sdf.format(todayStart.getTime());
	}

	/**
	 * 获取当天的起始时间
	 * @return
	 */
	public static String getStartTime2(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(time);
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return sdf.format(todayStart.getTime());
	}
	/**
	 * 获取当天的截至时间
	 * @return
	 */
	public static String getEndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return sdf.format(todayEnd.getTime());
	} 
	
	/**
	 * 获取当天的截至时间
	 * @return
	 */
	public static String getEndTime2(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(time);
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return sdf.format(todayEnd.getTime());
	} 
	
	/**
	 * 截取Double类型小数点后两位
	 * @param a
	 * @return
	 */
	public static String get2Double(Double a){
		
		if(a != null && !a.isNaN() && !a.isInfinite()){
			DecimalFormat df=new DecimalFormat("#.##");
			df.setMinimumFractionDigits(2);
			return df.format(a).toString();  
		}else{
			return "0.00";
		}
			
	}
	
	/**
	 * 获取某日的工作时间
	 * @param date
	 * @return
	 */
	public static List getWorkTime(Date date){
		Calendar c = Calendar.getInstance();
		int h = 21; //c.get(Calendar.HOUR_OF_DAY);
		List list = new ArrayList<>();
		String dateStr = DateTime.toString(date);
		String dataTime = null;
		for(int i=7;i<=h;i++){
			String time = "";
			if(i<10){
				time = "0"+i;
			}else{
				time = String.valueOf(i);
			}
			time +=":00:00";
			dataTime = dateStr + " " + time;
			list.add(dataTime);
		}
		return list;
	}
	/**
	 * 获取startDate到endDate的日期
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List getWorkTime(Date startDate, Date endDate) {
		List list = new ArrayList<>();
		while(startDate.getTime()<=endDate.getTime()){
			Date sDate = DateUtil.getIntegralStartTime(startDate);
			String startStr = DateUtil.format(sDate,DateUtil.PATTERN_CLASSICAL);
			list.add(startStr);
			startDate = DateUtil.rollDay(startDate, 1);
		}
		return list;
	}
	/**
	 * 获取startDate到endDate的日期
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List getWorkTimeForMonth(Date startDate, Date endDate) {
		List list = new ArrayList<>();
		while(startDate.getTime()<=endDate.getTime()){
			String startStr = DateUtil.format(startDate,DateUtil.PATTERN_CLASSICAL);
			list.add(startStr);
			Date stDate = DateUtil.rollMonth(startDate, 1);
			startDate = DateUtil.getCurrentMonthStartTime(stDate);
		}
		return list;
	}

	/**
	 * 生成随机6位数字验证码
	 * @return
	 */
	public static String getVerificationCode(){
		String psd = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			psd += random.nextInt(10);
		}
		return psd;
	}
	/**
	 * 集合排序
	 */
	public static void comper(List list){
		Collections.sort(list, new Comparator<AgentDTO>() {
			@Override
			public int compare(AgentDTO o1, AgentDTO o2) {
				long o1Time = DateUtil.parse(o1.getSignInTime()).getTime();
				long o2Time = DateUtil.parse(o2.getSignInTime()).getTime();
				return (int) (o1Time-o2Time);
			}

		});
	}
	/**
	 * 小组轮排
	 * @param li
	 * @return
	 */
	public static List<AgentDTO> sort(List<AgentDTO> agentWaitList,List<AgentDTO> agentVisitedList,AgentDTO visiting,boolean flag){
		List results = new ArrayList<>();
        List teamA = new ArrayList<>();
        List teamB = new ArrayList<>();
        if(!flag){//顺排
        	results.addAll(agentWaitList);
        	results.addAll(agentVisitedList);
		} else {// 小组轮排
			for (AgentDTO ad : agentWaitList) {
				if (ad.getGroupName() != null && !ad.getGroupName().equals("") && ad.getGroupName().equals("2")) {
					teamB.add(ad);
				} else {
					teamA.add(ad);
				}
			}
			for (AgentDTO ad : agentVisitedList) {
				if (ad.getGroupName() != null && !ad.getGroupName().equals("") && ad.getGroupName().equals("2")) {
					teamB.add(ad);
				} else {
					teamA.add(ad);
				}
			}
			if(teamA!=null && !teamA.isEmpty()){
				comper(teamA);
			}
			if(teamB!=null && !teamB.isEmpty()){
				comper(teamB);
			}
			boolean lastIsTeamA = true;
			if(visiting!=null){
				if (visiting.getGroupName() != null && !visiting.getGroupName().equals("") && visiting.getGroupName().equals("2")) {
					lastIsTeamA = true;
				}else{
					lastIsTeamA = false;
				}
			}else{
				//判断第一个位置的是哪一个组的
				if(teamA.size() > 0 && teamB.size() > 0){
					AgentDTO agentA = (AgentDTO) teamA.get(0);
					AgentDTO agentB = (AgentDTO) teamB.get(0);
					if(agentA.getLeaveTime()!=null && !agentA.getLeaveTime().equals("")){
						if(agentB.getLeaveTime()==null || agentB.getLeaveTime().equals("")){
							lastIsTeamA = false;
						}else if(agentB.getLeaveTime()!=null && !agentB.getLeaveTime().equals("")){
							long timeA = DateUtil.parse(agentA.getLeaveTime()).getTime();
							long timeB = DateUtil.parse(agentB.getLeaveTime()).getTime();
							if(timeA<timeB){
								lastIsTeamA = true;
							}else{
								lastIsTeamA = false;
							}
						}
					}
				}
			}
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
		}
		return results;
	}
	
	/**
	 * 获取两个数值的百分比
	 * 
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static String getTwoNumberForValue(Integer divisor, Integer dividend) {

		if (dividend == 0) {
			return "0";
		}

		NumberFormat numberFormat = NumberFormat.getInstance();

		// 设置精确到小数点后2位

		numberFormat.setMaximumFractionDigits(2);

		String result = numberFormat.format((float) divisor / (float) dividend * 100);

		return result;

	}
}
