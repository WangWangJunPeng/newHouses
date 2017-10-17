package com.sc.tradmaster.service.director;

import java.util.List;
import java.util.Map;

import com.sc.tradmaster.bean.Comment;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.director.impl.dto.Order;
import com.sc.tradmaster.utils.Page;

public interface ProjectReceiveService {
	/**
	 * 获取今日接访数据
	 * @param user
	 * @return
	 */
	Map findTodayReceiveFirstPageData(User user);

	/**
	 * 顾问状态数据
	 * @param user
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findAgentStatusDataById(User user, String userId, String startDate, String endDate,String isCompare);

	/**
	 * 获取详细接访数据
	 * @param user
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map findToDayDetailedReceiveDataByTime(User user, String startDate, String endDate);

	/**
	 * 获取详细成交数据
	 * @param user
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map findDealDataByTime(User user, String startDate, String endDate);

	/**
	 * 获取今日案场顾问状态 热力图数据
	 * @param user
	 * @return
	 */
	Map findTwoDaysVisitData(User user);

	/**
	 * 获取今日案场顾问状态 接访信息
	 * @param user
	 * @param sortSign
	 * @return
	 */
	Map findToTodayAgentStatusData(User user, String sortSign);
	/************************************Web端Service***************************************/
	/**
	 * 获取项目任务完成进度
	 * @param user
	 * @param startTime
	 * @return
	 */
	Map<String, String> findProjectTaskFinishedExtent(User user, String anyDate);

	/**
	 * 获取首页三种数据
	 * @param user
	 * @param anyDate
	 * @param anyTimes
	 * @return
	 */
	Map<String, Object> findThreeTypesData(User user, String anyDate, String anyTimes);

	/**
	 * 获取首页案场门店信息
	 * @param user
	 * @param anyDate
	 * @param anyTimes
	 * @return
	 */
	List<Map<String, Object>> findProShopsData(User user, String anyDate, String anyTimes);

	/**
	 * 首页三大排行
	 * @param user
	 * @param anyDate
	 * @param anyTimes
	 * @param agentOrderSign
	 * @param shopOrderSign
	 * @return
	 */
	Map<String, Object> findThreeOrderData(User user, String anyDate, String anyTimes, String agentOrderSign,
			String shopOrderSign);

	/**
	 * 获取订单管理 全部订单数据
	 * @param user
	 * @return
	 */
	void findAllOrderData(User user ,Page page);

	/**
	 * 获取订单管理 待审核订单数据
	 * @param user
	 * @param page
	 */
	void findWaitCheckOrderData(User user, Page page);

	/**
	 * 获取订单管理 待审核订单数量
	 * @param user
	 * @return
	 */
	Map<String, Object> findWaitCheckOrderCount(User user);

	/**
	 * 获取订单管理 待付款
	 * @param user
	 * @param page
	 * @return
	 */
	void findWaitPayMoneyOrderCount(User user, Page page);

	/**
	 * 获取订单管理 待签约
	 * @param user
	 * @param page
	 */
	void findWaitSignOrderCount(User user, Page page);

	/**
	 * 获取订单管理 已签约
	 * @param user
	 * @param page
	 */
	void findAlreadySignOrderCount(User user, Page page);

	/**
	 * 获取订单管理 已撤销
	 * @param user
	 * @param page
	 */
	void findAlreadyRevokeOrderData(User user, Page page);

	/**
	 * 获取订单管理 已否决
	 * @param user
	 * @param page
	 */
	void findAlreadyRefuseOrderData(User user, Page page);

	/**
	 * 获取订单管理 订单详情
	 * @param user
	 * @param orderId
	 * @return
	 * @throws Exception 
	 */
	Map<String, Object> findOrderDeailDataById(User user, String orderId) throws Exception;
	/**
	 * 获取经理点评
	 * @param visitNo 订单no
	 * @return
	 */
	Comment findManagerCommentByVisitNo(Integer visitNo);

	/**
	 * 获取订单管理 付款待确认
	 * @param user
	 * @param page
	 */
	void findWaitPayMoneyEnterOrder(User user, Page page);

	/**
	 * 顾问状态数据
	 * @param user
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map findAgentStatusDataById(User user, String userId, String startDate, String endDate);

	
	
}
