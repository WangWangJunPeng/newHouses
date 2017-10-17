package com.sc.tradmaster.controller.directorWeb;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.Protocol;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.director.ProjectAnalyzeService;
import com.sc.tradmaster.service.director.ProjectReceiveService;
import com.sc.tradmaster.service.director.impl.dto.DataAnalysis;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.projectGuide.ProjectGuideService;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.ClientHelper;
import com.sc.tradmaster.utils.DataResult;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.HttpClientService;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.SmsContext;

/**
 * 案场经理端控制器
 * 
 * @author grl 2017-06-01
 *
 */
@Controller("directorController")
public class DirectorController extends BaseController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "projectReceiveService")
	private ProjectReceiveService projectReceiveService;

	@Resource(name = "projectAnalyzeService")
	private ProjectAnalyzeService projectAnalyzeService;

	@Resource(name = "projectService")
	private ProjectService projectService;

	@Resource(name = "httpClientService")
	private HttpClientService httpClientService;

	@Resource(name = "projectGuideService")
	private ProjectGuideService projectGuideService;

	private final String STATUS = "new";

	/**
	 * 经理段首页跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_director_page_index")
	public String toDirectorIndexPage() {
		return "directorWeb/managerHome";
	}

	/**
	 * 案场经理端侧边栏任务完成度数据
	 * 
	 * @param startTime
	 * @param endTime
	 * @param anyDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/director_frist_page_letf_data")
	public Object getFristPageOnLeftOfData(String anyDate) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		// 获取项目任务完成进度
		Map<String, String> map = projectReceiveService.findProjectTaskFinishedExtent(user, anyDate);

		return map;
	}

	/**
	 * 首页获取三类数据
	 * 
	 * @param anyDate
	 * @param anyTimes
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_three_types_data")
	public Object getThreeTypesData(String anyDate, String anyTimes) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map<String, Object> map = projectReceiveService.findThreeTypesData(user, anyDate, anyTimes);
		return map;
	}

	/**
	 * 
	 * 进入数据分析页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/to_data_analysis_page", method = RequestMethod.GET)
	public String toDataAnalysisPage() {

		return "directorWeb/dataAnalysis";
	}

	/**
	 * 进入数据报表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/to_data_statement_page", method = RequestMethod.GET)
	public String toDataStatementPage() {
		return "directorWeb/dataStatement";
	}

	/**
	 * ajax 根据时间获取接访数据--遗弃
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get_visit_data_for_chart", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Map<String, Object> getVisitData(String startTime, String endTime, String oneDay) {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		Map<String, Object> map = new HashMap<>();
		try {
			map = projectAnalyzeService.findVisitData(user, startTime, endTime, oneDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return map;
	}

	

	/**
	 * ajax获取接访数据--新版
	 * 
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */

	@SuppressWarnings("unchecked")

	@ResponseBody

	@RequestMapping(value = "/get_visit_list_for_chart", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getVisitListData(String startTime, String endTime, String oneDay) {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		if(STATUS.equals("new")){
			String url = "";
			if (!StringUtils.isEmpty(oneDay)) {
				url += "/charts/visit/" + user.getParentId() + "/" + oneDay;
			} else {
				
				startTime = DateUtil.ifNoStartTime(startTime);
				endTime = DateUtil.ifNoEndTime(endTime);
				
				url += "/charts/visit/" + user.getParentId() + "/" + startTime + "/" + endTime;
			}
			
			return getHttpClientData(url);
		}else{
			List<DataAnalysis> list = projectAnalyzeService.analysisOfData(startTime, endTime, user.getParentId(), oneDay);
			return list;
		}
	}

	/**
	 * 封装的发送httpClient请求并返回解析好的数据
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object getHttpClientData(String url) {

		String proUrl = SmsContext.httpClientURL + url;
		try {
			String result = httpClientService.doPost(proUrl, null);
			DataResult<Object> rs = JSON.parseObject(result, DataResult.class);
			if (rs.isSuccess()) {
				return rs.getData();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	/**
	 * ajax 获取储客数据
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */

	@ResponseBody

	@RequestMapping(value = "/get_memory_cus_data", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getMemoryCustomerData(String startTime, String endTime, String oneDay) {

		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		if(STATUS.equals("new")){
			String url = "";
			if (!StringUtils.isEmpty(oneDay)) {
				url += "/charts/memory/" + user.getParentId() + "/" + oneDay;
			} else {
				startTime = DateUtil.ifNoStartTime(startTime);
				endTime = DateUtil.ifNoEndTime(endTime);
				url += "/charts/memory/" + user.getParentId() + "/" + startTime + "/" + endTime;
			}
			return getHttpClientData(url);
		}else{
			return projectAnalyzeService.findMomeryCusListData(user, startTime, endTime, oneDay);
		}
	}

	/**
	 * 首页地图
	 * 
	 * @param anyDate
	 * @param anyTimes
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_pro_shops_data")
	public Object getProShopsData(String anyDate, String anyTimes) {

		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		List<Map<String, Object>> list = projectReceiveService.findProShopsData(user, anyDate, anyTimes);

		return list;
	}

	/**
	 * 首页三大排行
	 * 
	 * @param anyDate
	 * @param anyTimes
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_three_order_data")
	public Object getThreeOrderData(String anyDate, String anyTimes, String agentOrderSign, String shopOrderSign) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map<String, Object> map = projectReceiveService.findThreeOrderData(user, anyDate, anyTimes, agentOrderSign,
				shopOrderSign);
		return map;
	}

	/**
	 * 经理段订单页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_order_page")
	public String toOrderPage(String buttonSign, Model model) {
		model.addAttribute("buttonSign", buttonSign);
		return "directorWeb/orderForm";
	}

	/**
	 * 获取订单管理 全部订单数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get_all_order_data")
	public Object getAllOrderData(Integer start, Integer limit) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		projectReceiveService.findAllOrderData(user, page);
		return page;
	}

	

	/**
	 * 数据报表------接访
	 * 
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */

	@ResponseBody

	@RequestMapping(value = "get_visit_data_for_table", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getVisitDataForTable(String time, String oneDay) {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		if(STATUS.equals("new")){
			String url = "/table/visit/" + user.getParentId();
			if (!StringUtils.isEmpty(oneDay)) {
				url += "/" + oneDay;
			} else {
				if (!StringUtils.isEmpty(time)) {
					String startTime = getTimeForOrderSimple(time);
					String endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
					url += "/" + startTime + "/" + endTime;
				}
				
			}
			return getHttpClientData(url);
		}else{
			String startTime = getTimeForOrder(time);
			String endTime = DateUtil.format(new Date());
			List<Map<String, Object>> list = projectAnalyzeService.findVisitDataForTable(user, startTime, endTime, oneDay);
			return list;
		}
	}

	/**
	 * 获取订单管理 待审核订单数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/wait_check_order_data")
	public Object getWaitCheckOrderData(Integer start, Integer limit) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		projectReceiveService.findWaitCheckOrderData(user, page);
		return page;
	}

	

	/**
	 * 数据报表 -----成交
	 * 
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */

	@ResponseBody

	@RequestMapping(value = "get_deal_statement_for_table", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getDealStatementForTable(String time, String oneDay) {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		if(STATUS.equals("new")){
			
			String url = "/table/deal/" + user.getParentId();
			if (!StringUtils.isEmpty(oneDay)) {
				url += "/" + oneDay;
			} else {
				if (!StringUtils.isEmpty(time)) {
					String startTime = getTimeForOrderSimple(time);
					String endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
					url += "/" + startTime + "/" + endTime;
				}
			}
			return getHttpClientData(url);
		}else{
			String startTime = getTimeForOrder(time);
			String endTime = DateUtil.format(new Date());
			List<Map<String, Object>> list = projectAnalyzeService.findDealStatementForTable(user, startTime, endTime,
					oneDay);
			return list;
		}
	}

	/**
	 * 获取订单管理 待审核订单数量
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/wait_check_order_count")
	public Object getWaitCheckOrderCount() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map<String, Object> map = projectReceiveService.findWaitCheckOrderCount(user);
		return map;
	}


	/**
	 * 
	 * 数据报表 -----外场
	 * 
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */

	@ResponseBody

	@RequestMapping(value = "get_out_field_statment_for_table", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getOutFieldStatement(String time, String oneDay) {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		if(STATUS.equals("new")){
			
			String url = "/table/out/" + user.getParentId();
			if (!StringUtils.isEmpty(oneDay)) {
				url += "/" + oneDay;
			} else {
				if (!StringUtils.isEmpty(time)) {
					String startTime = getTimeForOrderSimple(time);
					String endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
					url += "/" + startTime + "/" + endTime;
				}
			}
			return getHttpClientData(url);
		}else{
			String startTime = getTimeForOrder(time);
			String endTime = DateUtil.format(new Date());
			List<Map<String, Object>> list = projectAnalyzeService.findOutFieldStatement(user, startTime, endTime, oneDay);
			return list;
		}
	}



	/**
	 * 获取接访的详细信息
	 * 
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */

	@ResponseBody

	@RequestMapping(value = "get_visit_data_for_label", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getVisitDataForLabel(String startTime, String endTime, String oneDay) {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		if(STATUS.equals("new")){
			
			String url = "/label/visit/" + user.getParentId();
			if (!StringUtils.isEmpty(oneDay)) {
				url += "/" + oneDay;
			} else {
				startTime = DateUtil.ifNoStartTime(startTime);
				endTime = DateUtil.ifNoEndTime(endTime);
				url += "/" + startTime + "/" + endTime;
			}
			
			return getHttpClientData(url);
		}else{
			Map<String, Object> map = projectAnalyzeService.findVisitDataForLabel(user, startTime, endTime, oneDay);
			return map;
		}
	}



	/**
	 * 获取储客详细信息
	 * 
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @return
	 */

	@ResponseBody

	@RequestMapping(value = "get_momery_customer_data_for_label", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getMomeryCustomerDataForLabel(String startTime, String endTime, String oneDay) {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		if(STATUS.equals("new")){
			
			String url = "/label/momery/" + user.getParentId();
			if (!StringUtils.isEmpty(oneDay)) {
				url += "/" + oneDay;
			} else {
				startTime = DateUtil.ifNoStartTime(startTime);
				endTime = DateUtil.ifNoEndTime(endTime);
				url += "/" + startTime + "/" + endTime;
			}
			
			return getHttpClientData(url);
		}else{
			Map<String, Object> map = projectAnalyzeService.findMomeryCustomerData(user, startTime, endTime, oneDay);
			return map;
		}
	}

	/**
	 * 获取订单管理 待付款
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/wait_pay_money_order_data")
	public Object getWaitPayMoneyOrderData(Integer start, Integer limit) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		projectReceiveService.findWaitPayMoneyOrderCount(user, page);
		return page;
	}

	/**
	 * 获取订单管理 付款待确认
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/wait_pay_money_enter_order_data")
	public Object getWaitPayMoneyEnterOrderData(Integer start, Integer limit) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		projectReceiveService.findWaitPayMoneyEnterOrder(user, page);
		return page;
	}

	/**
	 * 获取订单管理 待签约
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/wait_sign_order_data")
	public Object getWaitSignOrderData(Integer start, Integer limit) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		projectReceiveService.findWaitSignOrderCount(user, page);
		return page;
	}

	/**
	 * 获取订单管理 已签约
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/already_sign_order_data")
	public Object getAlreadySignOrderData(Integer start, Integer limit) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		projectReceiveService.findAlreadySignOrderCount(user, page);
		return page;
	}

	/**
	 * 获取订单管理 已撤销
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/already_revoke_order_data")
	public Object getAlreadyRevokeOrderData(Integer start, Integer limit) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		projectReceiveService.findAlreadyRevokeOrderData(user, page);
		return page;
	}

	/**
	 * 获取订单管理 已否决
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/already_refuse_order_data")
	public Object getAlreadyRefuseOrderData(Integer start, Integer limit) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		projectReceiveService.findAlreadyRefuseOrderData(user, page);
		return page;
	}



	/**
	 * 获取成交数据（累计，不跟时间进行互动）
	 * 
	 * @return
	 */

	@ResponseBody

	@RequestMapping(value = "/get_deal_data_for_label", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getDealDataForLabel() {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		if(STATUS.equals("new")){
			
			String url = "/label/deals/" + user.getParentId();
			return getHttpClientData(url);
		}else{
			return projectAnalyzeService.findDealDataForLabel(user);
		}

	}

	

	/**
	 * 成交客户分析
	 * 
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/get_house_buy_data_for_table", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getHouseBuyDataforTable() {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		if(STATUS.equals("new")){
			String url = "/charts/deals/" + user.getParentId();
			return getHttpClientData(url);
		}else{
			return projectAnalyzeService.findHouseBuyDataforTable(user);
		}
	}

	/**
	 * 订单详情跳转控制器，订单号
	 * 
	 * @param orderId
	 * @param buttonSign
	 * @return
	 */
	@RequestMapping("/for_order_deail_page_set_orderId")
	public ModelAndView toOrderDeailPage(String orderId, String buttonSign) {
		ModelAndView mv = new ModelAndView("directorWeb/orderDetails");
		mv.addObject("orderNo", orderId);
		mv.addObject("buttonSign", buttonSign);
		return mv;
	}

	/**
	 * 订单详情 跳转控制情 ，付款确认，签约确认
	 * 
	 * @param orderId
	 * @param buttonSign
	 * @return
	 */
	@RequestMapping("/for_order_other_deail_page_set_orderId")
	public ModelAndView toOrderOtherDeailPage(String orderId, String buttonSign, String whichButtonHidSign) {
		ModelAndView mv = new ModelAndView("directorWeb/orderOtherDetails");
		mv.addObject("orderNo", orderId);
		mv.addObject("buttonSign", buttonSign);
		mv.addObject("whichButtonHidSign", whichButtonHidSign);
		return mv;
	}

	/**
	 * 获取订单管理 订单详情
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/get_order_deail_data")
	public Object getOrderDeailData(String orderId) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map<String, Object> map = projectReceiveService.findOrderDeailDataById(user, orderId);
		return map;
	}

	/**
	 * 认购申请 审核
	 */
	@ResponseBody
	@RequestMapping("director_agree_check_buy_apply_date")
	public void agreeCheckBuyApply(Integer recordNo, String checkReson) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		try {
			projectService.updateAgreeBuyApply(u, recordNo, checkReson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 认购申请 拒绝
	 */
	@RequestMapping("director_refuse_buy_apply_date")
	public void refuseBuyApply(Integer recordNo, String reason) {
		// 获取当前登录用户对象
		User user = (User) this.request.getSession().getAttribute("userInfo");
		// 查询数据库中当前对象
		User u = userService.findById(user.getUserId());
		try {
			projectService.updateRefuseBuyApply(u, recordNo, reason);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据代码选择时间的开始时间
	 * 
	 * @param order
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	private String getTimeForOrder(String order) {

		String result = "";

		if ("week".equals(order)) {
			result = DateUtil.format(DateUtil.rollDay(new Date(), -7));
		} else if ("half_month".equals(order)) {
			result = DateUtil.format(DateUtil.rollDay(new Date(), -15));
		} else if ("one_month".equals(order)) {
			result = DateUtil.format(DateUtil.rollMonth(new Date(), -1));
		} else if ("half_year".equals(order)) {
			result = DateUtil.format(DateUtil.rollMonth(new Date(), -6));
		} else if ("one_year".equals(order)) {
			result = DateUtil.format(DateUtil.rollMonth(new Date(), -12));
		}
		return result;
	}

	/**
	 * 根据代码选择时间的开始时间
	 * 
	 * @param order
	 * @return yyyy-MM-dd
	 */
	private String getTimeForOrderSimple(String order) {

		String result = "";

		if ("week".equals(order)) {
			result = DateUtil.format(DateUtil.rollDay(new Date(), -7), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		} else if ("half_month".equals(order)) {
			result = DateUtil.format(DateUtil.rollDay(new Date(), -15), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		} else if ("one_month".equals(order)) {
			result = DateUtil.format(DateUtil.rollMonth(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		} else if ("half_year".equals(order)) {
			result = DateUtil.format(DateUtil.rollMonth(new Date(), -6), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		} else if ("one_year".equals(order)) {
			result = DateUtil.format(DateUtil.rollMonth(new Date(), -12), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		return result;
	}



	/**
	 * 获取置业顾问的接访排行
	 * 
	 * @param time
	 * @return
	 */

	@ResponseBody

	@RequestMapping(value = "/get_visit_top_and_data", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getVisitTopAndData(String time, String oneDay) {

		User user = (User) this.request.getSession().getAttribute("userInfo");
		
		if(STATUS.equals("new")){
			
			String url = "/team/visittop";
			if (!StringUtils.isEmpty(oneDay)) {
				url += "/day/" + user.getParentId() + "/" + oneDay;
			} else {
				if (!StringUtils.isEmpty(time)) {
					url += "/" + user.getParentId() + "/" + time;
				}
			}
			return getHttpClientData(url);
		}else{
			String startTime = getTimeForOrder(time);
			String endTime = DateUtil.format(new Date());
			return projectAnalyzeService.findVisitTopAndData(user, startTime, endTime, oneDay);
		}
	}

	/**
	 * 获取置业顾问的接访未登记排行
	 * 
	 * @param time
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get_visit_not_register", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public List<Map<String, Object>> getVisitNotRegister(String time, String oneDay) {
		User user = (User) this.request.getSession().getAttribute("userInfo");

		// 判断时间
		String startTime = getTimeForOrder(time);
		String endTime = DateUtil.format(new Date());
		return projectAnalyzeService.findVisitNotRegister(user, startTime, endTime, oneDay);
	}



	/**
	 * 获取案场的置业顾问的储客排行
	 * 
	 * @param time
	 * @param oneDay
	 * @return
	 */

	@ResponseBody

	@RequestMapping(value = "/get_memory_cu_top", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getMemoryCustomerTop(String time, String oneDay) {

		User user = (User) this.request.getSession().getAttribute("userInfo");
		if(STATUS.equals("new")){
			
			String url = "/team/momery";
			if (!StringUtils.isEmpty(oneDay)) {
				url += "/day/" + user.getParentId() + "/" + oneDay;
			} else {
				if (!StringUtils.isEmpty(time)) {
					url += "/" + user.getParentId() + "/" + time;
				}
			}
			return getHttpClientData(url);
		}else{
			String startTime = getTimeForOrder(time);
			String endTime = DateUtil.format(new Date());

			return projectAnalyzeService.findMemoryCuTop(user, startTime, endTime, oneDay);
		}

	}



	/**
	 * 获取置业顾问成交排行
	 * 
	 * @param time
	 * @return
	 */

	@ResponseBody

	@RequestMapping(value = "/get_deal_top_and_data", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getDealTopAndData(String time, String oneDay) {

		User user = (User) this.request.getSession().getAttribute("userInfo");
		if(STATUS.equals("new")){
			
			String url = "/team/deal";
			if (!StringUtils.isEmpty(oneDay)) {
				url += "/day/" + user.getParentId() + "/" + oneDay;
			} else {
				if (!StringUtils.isEmpty(time)) {
					url += "/" + user.getParentId() + "/" + time;
				}
			}
			return getHttpClientData(url);
		}else{
			String startTime = getTimeForOrder(time);
			String endTime = DateUtil.format(new Date());

			return projectAnalyzeService.findDealTopAndData(user, startTime, endTime, oneDay);
		}

	}

	

	/**
	 * 获取置业顾问的头像
	 * 
	 * @return
	 */

	@ResponseBody

	@RequestMapping(value = "/get_agent_img", method = RequestMethod.POST)
	public Object getAgentImg() {

		User user = (User) this.request.getSession().getAttribute("userInfo");
		if(STATUS.equals("new")){
			String url = "/team/agent/" + user.getParentId();
			return getHttpClientData(url);
		}else{
			return projectAnalyzeService.findPersonImg(user);
		}
	}

	/**
	 * 进入团队页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "to_manager_team_page", method = RequestMethod.GET)
	public String toManagerTeamPage() {
		return "directorWeb/managerTeam";
	}

	/*************************************************************** 分销开关 ***********************************************************************/
	// 分销介绍页面
	@RequestMapping("/to_fensales_introduce")
	public String toFenSalesIntroduce() {
		return "directorWeb/distributionDefault";
	}

	// 分销协议页面
	@RequestMapping("/to_fensales_deal")
	public String toFenSalesDeal() {
		return "directorWeb/distributionPact";
	}
	
	//获取协议内容
	@ResponseBody
	@RequestMapping("/get_fensales_protocol")
	public Object getProtocol(){
		Map map = new HashMap<>();
		Protocol p = projectGuideService.findProtocolByUniqueSymbol(SmsContext.protocolUniqueSymbol);
		if(p!=null){
			map.put("code", 200);
			map.put("data",p);
		}else{
			map.put("code", 201);
			map.put("data","暂无数据");
		}
		return map;
	}

	// 分销设置页面
	@RequestMapping("/to_fensales_setting")
	public String toFenSalesSetting() {
		return "directorWeb/distributionRule";
	}

	// 修改
	@RequestMapping("/to_fensales_editor")
	public String toFenSalesEditor() {
		return "redirect:/to_fensales_setting";
	}

	// 通过id回显带看定义接口 get_current_pro_guide_info
	/**
	 * 保存页面提交带看业务定义
	 * 
	 * @param pg
	 * @param isSave
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_fensales_add_updata_pro_guide")
	public Object addOrUpdataProGuide(ProjectGuide pg, String checkIsDaiKan, String checkIsYiDi, String checkIsFast,
			String isSave) {
		Map map = new HashMap<>();
		// 获取登录用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		// 封装带看对象
		pg.setIsDaiKan(Integer.valueOf(checkIsDaiKan));
		pg.setIsYiDi(Integer.valueOf(checkIsYiDi));
		pg.setIsFast(Integer.valueOf(checkIsFast));
		// 执行添加户型业务逻辑
		try {
			projectGuideService.addOrUpdate(pg, user);
			map.put("code", 200);
			map.put("url", "to_fensales_success");
			// 页面跳转
		} catch (Exception e) {
			map.put("code", 201);
			map.put("url", "to_fensales_failed");
		}
		return map;
	}

	// 保存成功页面
	@RequestMapping("/to_fensales_success")
	public String toFenSalesSuccess() {
		return "directorWeb/distributionSuccess";
	}

	// 成功页面返回按钮跳转详情页面
	@RequestMapping("/to_fensales_turn_details")
	public String toFenSalesTurnDetails() {
		return "directorWeb/distributionDone";
	}

	// 保存失败页面
	@RequestMapping("/to_fensales_failed")
	public String toFenSalesFailed() {
		return "directorWeb/distributionFail";
	}

	// 保存失败页面返回按钮跳转设置页面，重新设置
	@RequestMapping("/to_fensales_turn_setting")
	public String toFenSalesTurnSetting() {
		return "redirect:/to_fensales_setting";
	}

	// 关闭分销开关，删除案场带看定义
	@ResponseBody
	@RequestMapping("/fensales_close")
	public Object deleteProjectGuide() {
		// Map map = new HashMap<>();
		// 获取登录用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map map = projectGuideService.dropProjectGuideByUser(user);
		return map;
	}
	/************************************************************* end *************************************************************/
}
