package com.sc.tradmaster.service.director;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.director.impl.dto.DataAnalysis;

public interface ProjectAnalyzeService {

	/**
	 * 获取接访数据
	 * 当startTime和endTime之间的间隔小于三个月返回的时间集合就是以周计算
	 * 当startTime和endTime之间的间隔大于三个月返回的时间集合就是以月计算
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param oneday
	 * @return
	 */
	Map<String, Object> findVisitData(User user, String startTime, String endTime, String oneDay) throws ParseException;
	
	
	/**
	 * 获取储客数据
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param oneday
	 * @return
	 */
	Map<String, Object> findMomeryCusData(User user , String startTime, String endTime, String oneDay);
	
	
	/**
	 * 获取数据报表--接访数据
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param oneday
	 * @return
	 */
	List<Map<String, Object>> findVisitDataForTable(User user, String startTime, String endTime, String oneDay);
	
	/**
	 * 
	 * 获取数据报表--成交数据
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */
	List<Map<String, Object>> findDealStatementForTable(User user, String startTime, String endTime, String oneDay);
	
	/**
	 * 获取数据报表 -- 外场数据
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */
	List<Map<String, Object>> findOutFieldStatement(User user, String startTime, String endTime, String oneDay);
	
	/**
	 * 获取接访的详细信息
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */
	Map<String, Object> findVisitDataForLabel(User user, String startTime, String endTime, String oneDay);
	
	/**
	 * 获取储客的数据
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */
	Map<String, Object> findMomeryCustomerData(User user, String startTime, String endTime, String oneDay);
	
	
	/**
	 * 获取成交数据（累计，不跟时间进行互动）
	 * @param user
	 * @return
	 */
	Map<String, Object> findDealDataForLabel(User user);
	
	/**
	 * 成交客户分析
	 * @param user
	 * @return
	 */
	Map<String,Object> findHouseBuyDataforTable(User user);
	
	/**
	 * 获取当前案场的所有的置业顾问的头像
	 * @param user
	 * @return
	 */
	List<Map<String, Object>> findPersonImg(User user);
	
	
	/**
	 * 获取置业顾问接访排行
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */
	List<Map<String, Object>> findVisitTopAndData(User user, String startTime, String endTime, String oneDay);
	
	/**
	 * 获取接访未登记排行
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */
	List<Map<String, Object>> findVisitNotRegister(User user, String startTime, String endTime, String oneDay);
	
	
	/**
	 * 获取置业顾问的储客排行
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */
	List<Map<String, Object>> findMemoryCuTop(User user, String startTime, String endTime, String oneDay);
	
	/**
	 * 获取成交排行
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Map<String, Object>> findDealTopAndData(User user, String startTime, String endTime, String oneDay);
	/**
	 * 
	 * @param startTime yyyy-MM-dd
	 * @param endTime yyyy-MM-dd
	 * @param projectId
	 * @param oneDay yyyy-MM-dd
	 * @return
	 */
	List<DataAnalysis> analysisOfData(String startTime,String endTime,String projectId,String oneDay);
	
	
		
	/**
	 * 获取储客数据-新
	 * @param user
	 * @param startTime
	 * @param endTime
	 * @param oneday
	 * @return
	 */
	Map<String, Object> findMomeryCusListData(User user , String startTime, String endTime, String oneDay);
}
