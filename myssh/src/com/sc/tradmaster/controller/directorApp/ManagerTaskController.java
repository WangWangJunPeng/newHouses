package com.sc.tradmaster.controller.directorApp;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.hql.internal.ast.tree.FromElement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.Comment;
import com.sc.tradmaster.bean.ManaerChartData;
import com.sc.tradmaster.bean.ManagerOwnAnalyse;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.director.ProjectReceiveService;
import com.sc.tradmaster.service.tagService.AppTagManagerService;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.service.tagService.impl.dto.PCustomerInformation;
import com.sc.tradmaster.service.tagService.impl.dto.TagLib;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.service.user.impl.dto.AllCheckedCustomerDTO;
import com.sc.tradmaster.service.user.impl.dto.AnalyzeTagsDTO;
import com.sc.tradmaster.service.user.impl.dto.GrDto;
import com.sc.tradmaster.service.user.impl.dto.RankingDto;
import com.sc.tradmaster.service.user.impl.dto.VisitRecordsDTO;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.HttpClientService;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

import net.sf.json.JSONArray;

/**
 * 
 * @author wjp
 *
 */
@Controller
public class ManagerTaskController extends BaseController {
	@Resource(name = "baseDao")
	private BaseDao baseDao;
	
	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "appTagManagerService")
	private AppTagManagerService appTagManagerService;
	@Resource(name = "projectReceiveService")
	private ProjectReceiveService projectReceiveService;
	@Resource(name = "tagService")
	private TagService tagService;
	@Resource(name = "projectService")
	private ProjectService projectService;
	@Resource(name="httpClientService")
	private HttpClientService httpClientService;

	/**
	 * 登录首页
	 * 
	 * @return
	 */
	@RequestMapping("/to_director_index")
	public String toDirectorIndexPage() {
		return "app/director/index";
	}

	/**
	 * 经理分析页面...................跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_getManagerAnalysePage")
	public String toGoToManagerAnalysePage() {
		return "app/director/fenxi";
	}

	/**
	 * 经理我的页面...................跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_getManagerMyInfoPage")
	public String toGoToManagerMyInfoPage() {
		return "app/director/my";
	}

	/**
	 * 经理订单页面____________________________
	 * 
	 * @return
	 */
	@RequestMapping("/to_getManagerAllOrderPage")
	public String toGoToManagerAllOrderPage() {
		return "app/director/dingdan";
	}

	/**
	 * 经理业务页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToManagerTask")
	public String toGoToManagerTask() {
		return "app/director/task";
	}

	/**
	 * 经理任务页今日接访未登记跳转控制器
	 * 
	 */
	@RequestMapping("/to_getVisitNotRegisterList")
	public String toGoToVisitNotRegisterList() {
		return "app/director/unregistered";
	}

	/**
	 * 盘客控制器________________
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToPanKePage")
	public String toGoToPanKePage() {
		return "app/director/panke";
	}

	/**
	 * @param agentId
	 * @param sign//1,已完成,2:未完成
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/to_goToReviewedCusPage")
	public String toGoToReviewedCusPage(String agentId, String sign, Model model, String time) throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		List<VisitRecords> pros = new ArrayList<>();
		List<AllCheckedCustomerDTO> accdtoList = userService.findAllCheckedCustomer(time, user);
		for (AllCheckedCustomerDTO adto : accdtoList) {
			if (agentId.equals(adto.getAgentId())) {
				if ("1".equals(sign)) {// 已完成
					pros = adto.getHaveCheckedList();
					model.addAttribute("customerList", pros);
					return "app/director/reviewedCus2";
				}
				if ("2".equals(sign)) {// 未完成
					pros = adto.getNotCheckedList();
					model.addAttribute("customerList", pros);
					return "app/director/reviewedCus";
				}
			}
		}
		return null;
	}

	@RequestMapping("/to_goToSettingPage")
	public String toGoToSettingPage() {
		return "app/director/setting";
	}

	/**
	 * 经理任务页今日接访未登记
	 * 
	 * @param todayTime
	 * @throws ParseException
	 */
	@RequestMapping("/getVisitNotRegisterList")
	public void getVisitNotRegisterList(String time) throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		List<VisitRecordsDTO> vrdtoList = userService.findToadyVisitRecords(time, user);

		this.outListString(vrdtoList);
	}

	/**
	 * 案场今日已备案
	 * 
	 * @param todayTime
	 * @throws ParseException
	 */
	@RequestMapping("/getGuideRecordsByToday")
	public void getGuideRecordsByToday(String startTime, String endTime) throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map<String, String> map = userService.findTodayGuideRecords(startTime, endTime, user);
		this.outObjectString(map, null);
	}

	/**
	 * 经理_任务_待完成_盘客数据显示
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/getCheckCustomerByToday")
	public void getCheckCustomerByToday(String startTime, String endTime) throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map<String, Integer> map = userService.findCheckCustomerNum(startTime, endTime, user);
		this.outObjectString(map, null);
	}

	/**
	 * 经理我的_任务_备案未到访跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToGuideRecordsHaveAndNotPage")
	public String toGoToGuideRecordsHaveAndNotPage() {
		return "app/director/waichBeian";
	}

	/**
	 * 案场备案详情.....每个中介门店的备案记录
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/getGuideRecordsDetails")
	public void getGuideRecordsDetails(String startTime, String endTime) throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		List<GrDto> grdtoList = userService.findGuideRecordsDetail(startTime, endTime, user);
		this.outListString(grdtoList);
	}

	/**
	 * 我的团队排名页面控制器
	 */
	@RequestMapping("/to_goToVisitRankingPage")
	public String toGoToVisitRankingPage() {
		return "app/director/myTeam";
	}

	/**
	 * 接访排名
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/to_getVisitRanking",produces = {
	"application/json;charset=UTF-8" })
	public Object toGetVisitRanking(String startTime, String endTime) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
		/*if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
				//获取顾问信息
				//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
				//获取归集数据
				String url = SmsContext.httpClientURL+"/to_getAllVisitRecoredsRankingInfo_byOutSide";
				Map cMap = new HashMap<>();
				cMap.put("proId",user.getParentId());
				cMap.put("startDate", startTime);
				cMap.put("endDate", endTime);
				cMap.put("agentId", u.getUserId());
				String doGet = httpClientService.doPost(url, cMap);
				if(doGet!=null){
					return doGet;
				}else {
//					List<RankingDto> rkList = userService.findVisitRanking(startTime, endTime, user);
					return null;
				}
		}else {
			return null;
		}
	}
	
	
	/**
	 * 认购排名
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/to_getRengouRanking",produces = {
	"application/json;charset=UTF-8" })
	public Object toGetRengouRanking(String startTime, String endTime) throws Exception {
		// this.request.getRemoteAddr();
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
//			String todayStr = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
//			String startDayStr = DateUtil.format(DateUtil.parse(startTime), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			//如果起始时间不是当天，走归集方法，否则走原有方法；
				//获取顾问信息
				//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
				//获取归集数据
				String url = SmsContext.httpClientURL+"/to_getAllReadyDealRecoredsRankingInfo_byOutSide";
				Map cMap = new HashMap<>();
				cMap.put("proId",user.getParentId());
				cMap.put("startDate", startTime);
				cMap.put("endDate", endTime);
				cMap.put("agentId", u.getUserId());
				String doGet = httpClientService.doPost(url, cMap);
				if(doGet!=null){
					return doGet;
				}else {
//					List<RankingDto> rkList = userService.findRengouRanking(startTime, endTime, user);
					return null;
				}
		}else {
			return null;
		}
	}


	/**
	 * 签约排名
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/to_getContractRanking",produces = {
	"application/json;charset=UTF-8" })
	public Object toGetContractRanking(String startTime, String endTime) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		String userId = user.getUserId();
		//如果起始时间不为空，走归集方法，否则走原有方法
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			//获取顾问信息
			//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
			//获取归集数据
			String url = SmsContext.httpClientURL+"/to_getAllDealRecoredsRankingInfo_byOutSide";
			Map cMap = new HashMap<>();
			cMap.put("proId",user.getParentId());
			cMap.put("startDate", startTime);
			cMap.put("endDate", endTime);
			cMap.put("agentId", u.getUserId());
			String doGet = httpClientService.doPost(url, cMap);
			if(doGet!=null){
				return doGet;
			}else {
//				List<RankingDto> rkList = userService.findContractRanking(startTime, endTime, user);
				return null;
			}
		}else {
			return null;
		}
	}


	/**
	 * 成交转化率
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/to_getSlewRateRanking",produces = {
	"application/json;charset=UTF-8" })
	public Object toGetSlewRateRanking(String startTime, String endTime) throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		List<RankingDto> rkList = userService.findSlewRateRanking(startTime, endTime, user);
//		this.outListString(rkList);
		return rkList;
	}

	/**
	 * 业务报表页面跳转控制器
	 * 
	 * @return
	 */
	@RequestMapping("/to_goToMyServiceStatement")
	public String toGoToMyServiceStatement() {
		return "app/director/report";
	}

	/**
	 * 业务报表_________接访
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@RequestMapping("/to_getVisitStatement")
	public void getVisitStatement(String startTime, String endTime) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map map = new HashMap<>();
		String userId = user.getUserId();
		//如果起始时间不为空，走归集方法，否则走原有方法
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
//		if(SmsContext.toGuiJiFun && startTime!=null && !startTime.equals("") && endTime!=null && !endTime.equals("")){
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			//获取顾问信息
			//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
			//获取归集数据
			String url = SmsContext.httpClientURL+"/to_getAllVisitRecoredsInfo_byOutSide";
			Map cMap = new HashMap<>();
			cMap.put("proId",user.getParentId());
			cMap.put("startDate", startTime);
			cMap.put("endDate", endTime);
			cMap.put("agentId", u.getUserId());
			String doGet = httpClientService.doPost(url, cMap);
			map = JSON.parseObject(doGet, HashMap.class);
//		Map<String, Object> map = userService.findVisitStatement(startTime, endTime, user);
		}
		this.outObjectString(map, null);
	}
	

	/**
	 * 业务报表_________成交
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@RequestMapping("/to_getDealStatement")
	public void getDealStatement(String startTime, String endTime) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map map = new HashMap<>();
		String userId = user.getUserId();
		//如果起始时间不为空，走归集方法，否则走原有方法
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			//获取顾问信息
			//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
			//获取归集数据
			String url = SmsContext.httpClientURL+"/to_getAllDealRecoredsInfo_byOutSide";
			Map cMap = new HashMap<>();
			cMap.put("proId",user.getParentId());
			cMap.put("startDate", startTime);
			cMap.put("endDate", endTime);
			cMap.put("agentId", u.getUserId());
			String doGet = httpClientService.doPost(url, cMap);
			map = JSON.parseObject(doGet, HashMap.class);

		}
		this.outObjectString(map, null);
//		Map<String, Object> map = userService.findDealStatement(startTime, endTime, user);
	}

	
	/**
	 * 业务报表_________外场
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@RequestMapping("/to_getOutFieldStatement")
	public void getOutFieldStatement(String startTime, String endTime) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			//获取顾问信息
			//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
			//获取归集数据
			String url = SmsContext.httpClientURL+"/to_getAllOutFieldRecoredsInfo_byOutSide";
			Map cMap = new HashMap<>();
			cMap.put("proId",user.getParentId());
			cMap.put("startDate", startTime);
			cMap.put("endDate", endTime);
			cMap.put("agentId", u.getUserId());
			String doGet = httpClientService.doPost(url, cMap);
			map = JSON.parseObject(doGet, HashMap.class);

		}
		this.outObjectString(map, null);
//		Map<String, Object> map = userService.findOutFieldStatement(startTime, endTime, user);
	}

	
	
	/**
	 * 经理_任务_带盘客____________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	@RequestMapping("/to_getReadyCheckedCustomer")
	public void getReadyCheckedCustomer(String time) throws ParseException {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		List<AllCheckedCustomerDTO> accdtoList = userService.findAllCheckedCustomer(time, user);
		this.outListString(accdtoList);

	}

	/**
	 * 经理点评_________
	 * 
	 * @param projectCustomerId
	 */
	@ResponseBody
	@RequestMapping("/to_updateProjectCustomer")
	public int toUpdateProjectCustomer(Integer visitNo, String agentId, String projectCustomerId,
			String customerComment, String describe, String commitSpeed, String customerAspiration, String customerInfo,
			String isAgree) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		try {
			userService.updateProjectCustomerByManager(visitNo, user, agentId, projectCustomerId, customerComment,
					describe, commitSpeed, customerAspiration, customerInfo, isAgree);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 200;
	}

	/**
	 * 归属置业顾问___________
	 * 
	 * @param projectCustomerId
	 */
	@RequestMapping("/to_getCustomerAffiliation")
	public void getCustomerAffiliation(String projectCustomerId) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map<String, String> map = userService.findCustomerAffiliation(projectCustomerId, user);
		this.outObjectString(map, null);
	}

	/**
	 * 获取拥有的标签
	 * 
	 * @param customerId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTagUse")
	public List<TagLib> getTagUse(String customerId, Integer tagTypeId) {
		User u = (User) this.request.getSession().getAttribute("userInfo");
		String projectId = u.getParentId();
		List<TagLib> showTagLib_use = tagService.showTagLib_use(customerId, tagTypeId, projectId);
		return showTagLib_use;
	}

	/**
	 * 经理分析收藏标签____________________________________________________________________________________________________
	 */
	@RequestMapping("/to_getManagerAnalyseList")
	public void getManagerAnalyseList() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		List<ManagerOwnAnalyse> moaList = userService.findManagerOwnAnalyse(user);
		this.outListString(moaList);
	}

	/**
	 * 经理分析新增收藏标签_____________
	 * 
	 * @param categoryId
	 */
	@RequestMapping("/to_addManagerAnalyse")
	public void addManagerAnalyse(String chartDataIds, String chartDataIdStr) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		userService.updateOneManagerOwnAnalyse(user, chartDataIds);

		userService.updateManagerOwnAnalyse(user, chartDataIdStr);
	}

	/**
	 * 经理分析删除收藏标签_____________
	 * 
	 * @param categoryId
	 */
	@RequestMapping("/to_dropManagerAnalyse")
	public void dropManagerAnalyse(String chartDataIds) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		userService.updateManagerOwnAnalyse(user, chartDataIds);

	}

	/**
	 * 观察客户接待成效控制器______________________
	 * 
	 */
	@RequestMapping("/to_goToSeeCustomerReceptionPage")
	public String goToSeeCustomerReceptionPage(Model model, String chartDataId) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		ManaerChartData mcd = userService.findMCd(chartDataId);
		model.addAttribute("data", mcd);
		String address = mcd.getReturnAddress();
		return address;
	}

	/**
	 * 观察客户接待成效数据显示______________________
	 * @throws Exception 
	 * 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeCustomerReceptionInfo")
	public Object getSeeCustomerReceptionInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			//获取顾问信息
			//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
			//获取归集数据
			String url = SmsContext.httpClientURL+"/to_getSeeCustomerReceptionInfo_byOutSide";
			Map cMap = new HashMap<>();
			cMap.put("proId",user.getParentId());
			cMap.put("startDate", startTime);
			cMap.put("endDate", endTime);
			cMap.put("agentId", u.getUserId());
			String doGet = httpClientService.doPost(url, cMap);
			if(doGet!=null){
				map = JSON.parseObject(doGet, HashMap.class);
				if (chartDataId != null && !"".equals(chartDataId)) {
					ManaerChartData mcd = (ManaerChartData) baseDao
							.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
					if (mcd != null && !"".equals(mcd)) {
						map.put("descText", mcd.getDescText());
						map.put("graph_1", mcd.getChartName());
					}
				}
			}
		}
		return map;
//		return userService.findReceptionByManager(startTime, endTime, user, chartDataId);
	}

	
	
	/**
	 * 观察首访有效率______________________
	 * @throws Exception 
	 * 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeCustomerFirstVisitInfo")
	public Object getSeeCustomerFirstVisitInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			//获取顾问信息
			//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
			//获取归集数据
			String url = SmsContext.httpClientURL+"/to_getSeeCustomerFirstVisitInfo_byOutSide";
			Map cMap = new HashMap<>();
			cMap.put("proId",user.getParentId());
			cMap.put("startDate", startTime);
			cMap.put("endDate", endTime);
			cMap.put("agentId", u.getUserId());
			String doGet = httpClientService.doPost(url, cMap);
			if(doGet!=null){
				map = JSON.parseObject(doGet, HashMap.class);
				if (chartDataId != null && !"".equals(chartDataId)) {
					ManaerChartData mcd = (ManaerChartData) baseDao
							.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
					if (mcd != null && !"".equals(mcd)) {
						map.put("descText", mcd.getDescText());
						map.put("graph_1", mcd.getChartName());
					}
				}
			}
		}
		return map;
//		return userService.findReceptionByCustomerFirstVisit(startTime, endTime, user, chartDataId);
	}

	

	/**
	 * 观察新老客户通道______________________
	 * @throws Exception 
	 * 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeNewAndOldCustomerPassagewayInfo")
	public Object getSeeNewAndOldCustomerPassagewayInfo(String startTime, String endTime,
			String chartDataId) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		
		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			//获取顾问信息
			//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
			//获取归集数据
			String url = SmsContext.httpClientURL+"/to_getSeeNewAndOldCustomerPassagewayInfo_byOutSide";
			Map cMap = new HashMap<>();
			cMap.put("proId",user.getParentId());
			cMap.put("startDate", startTime);
			cMap.put("endDate", endTime);
			cMap.put("agentId", u.getUserId());
			String doGet = httpClientService.doPost(url, cMap);
			if(doGet!=null){
				map = JSON.parseObject(doGet, HashMap.class);
				if (chartDataId != null && !"".equals(chartDataId)) {
					ManaerChartData mcd = (ManaerChartData) baseDao
							.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
					if (mcd != null && !"".equals(mcd)) {
						map.put("descText", mcd.getDescText());
						map.put("graph_1", mcd.getChartName());
					}
				}
			}
		}
		return map;
//		return userService.findNewAndOldCustomerPassagewayInfo(startTime, endTime, user, chartDataId);
	}

	

	/**
	 * 观察指定接访_________________________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeAppointCustomerReceptionInfo")
	public Object getSeeAppointCustomerReceptionInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			//获取顾问信息
			//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
			//获取归集数据
			String url = SmsContext.httpClientURL+"/to_SeeAppointCustomerReceptionInfo_byOutSide";
			Map cMap = new HashMap<>();
			cMap.put("proId",user.getParentId());
			cMap.put("startDate", startTime);
			cMap.put("endDate", endTime);
			cMap.put("agentId", u.getUserId());
			String doGet = httpClientService.doPost(url, cMap);
			if(doGet!=null){
				map = JSON.parseObject(doGet, HashMap.class);
				if (chartDataId != null && !"".equals(chartDataId)) {
					ManaerChartData mcd = (ManaerChartData) baseDao
							.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
					if (mcd != null && !"".equals(mcd)) {
						map.put("descText", mcd.getDescText());
						map.put("graph_1", mcd.getChartName());
					}
				}
			}
		}
		return map;
//		return userService.findSeeAppointCustomerReceptionInfo(startTime, endTime, user, chartDataId);
	}
	

	/**
	 * 观察替接_________________________________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeReplaceCustomerReceptionInfo")
	public Object getSeeReplaceCustomerReceptionInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			//获取顾问信息
			//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
			//获取归集数据
			String url = SmsContext.httpClientURL+"/to_SeeReplaceCustomerReceptionInfo_byOutSide";
			Map cMap = new HashMap<>();
			cMap.put("proId",user.getParentId());
			cMap.put("startDate", startTime);
			cMap.put("endDate", endTime);
			cMap.put("agentId", u.getUserId());
			String doGet = httpClientService.doPost(url, cMap);
			if(doGet!=null){
				map = JSON.parseObject(doGet, HashMap.class);
				if (chartDataId != null && !"".equals(chartDataId)) {
					ManaerChartData mcd = (ManaerChartData) baseDao
							.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
					if (mcd != null && !"".equals(mcd)) {
						map.put("descText", mcd.getDescText());
						map.put("graph_1", mcd.getChartName());
					}
				}
			}
		}
		return map;
//		return userService.findSeeReplaceCustomerReceptionInfo(startTime, endTime, user, chartDataId);
	}


	/**
	 * 观察接访时长_______________________________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeCustomerReceptionTimeInfo")
	public Object getSeeCustomerReceptionTimeInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			//获取顾问信息
			//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
			//获取归集数据
			String url = SmsContext.httpClientURL+"/to_SeeCustomerReceptionTimeInfo_byOutSide";
			Map cMap = new HashMap<>();
			cMap.put("proId",user.getParentId());
			cMap.put("startDate", startTime);
			cMap.put("endDate", endTime);
			cMap.put("agentId", u.getUserId());
			String doGet = httpClientService.doPost(url, cMap);
			if(doGet!=null){
				map = JSON.parseObject(doGet, HashMap.class);
				if (chartDataId != null && !"".equals(chartDataId)) {
					ManaerChartData mcd = (ManaerChartData) baseDao
							.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
					if (mcd != null && !"".equals(mcd)) {
						map.put("descText", mcd.getDescText());
						map.put("graph_1", mcd.getChartName());
					}
				}
			}
		}
		return map;
//		return userService.findSeeCustomerReceptionTimeInfo(startTime, endTime, user, chartDataId);
	}


	/**
	 * 认购货值分析_______________________________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeCustomerContractRecordsInfo")
	public Object getSeeCustomerContractRecordsInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			//获取顾问信息
			//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
			//获取归集数据
			String url = SmsContext.httpClientURL+"/to_SeeCustomerContractRecordsInfo_byOutSide";
			Map cMap = new HashMap<>();
			cMap.put("proId",user.getParentId());
			cMap.put("startDate", startTime);
			cMap.put("endDate", endTime);
			cMap.put("agentId", u.getUserId());
			String doGet = httpClientService.doPost(url, cMap);
			if(doGet!=null){
				map = JSON.parseObject(doGet, HashMap.class);
				if (chartDataId != null && !"".equals(chartDataId)) {
					ManaerChartData mcd = (ManaerChartData) baseDao
							.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
					if (mcd != null && !"".equals(mcd)) {
						map.put("descText", mcd.getDescText());
						map.put("graph_1", mcd.getChartName());
					}
				}
			}
		}
		return map;
//		return userService.findSeeCustomerContractRecordsInfo(startTime, endTime, user, chartDataId);
	}


	/**
	 * 观察客户签约_________________________________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeCustomerHaveDealInfo")
	public Object getSeeCustomerHaveDealInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			//获取顾问信息
			//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
			//获取归集数据
			String url = SmsContext.httpClientURL+"/to_SeeCustomerHaveDealInfo_byOutSide";
			Map cMap = new HashMap<>();
			cMap.put("proId",user.getParentId());
			cMap.put("startDate", startTime);
			cMap.put("endDate", endTime);
			cMap.put("agentId", u.getUserId());
			String doGet = httpClientService.doPost(url, cMap);
			if(doGet!=null){
				map = JSON.parseObject(doGet, HashMap.class);
				if (chartDataId != null && !"".equals(chartDataId)) {
					ManaerChartData mcd = (ManaerChartData) baseDao
							.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
					if (mcd != null && !"".equals(mcd)) {
						map.put("descText", mcd.getDescText());
						map.put("graph_1", mcd.getChartName());
					}
				}
			}
		}
		return map;
//		return userService.findSeeCustomerHaveDealInfo(startTime, endTime, user, chartDataId);
	}


	/**
	 * 签约货值分析___________________________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeCustomerHaveDealMoneyInfo")
	public Object getSeeCustomerHaveDealMoneyInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			
				//获取顾问信息
				//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
				//获取归集数据
				String url = SmsContext.httpClientURL+"/to_SeeCustomerHaveDealMoneyInfo_byOutSide";
				Map cMap = new HashMap<>();
				cMap.put("proId",user.getParentId());
				cMap.put("startDate", startTime);
				cMap.put("endDate", endTime);
				cMap.put("agentId", u.getUserId());
				String doGet = httpClientService.doPost(url, cMap);
				if(doGet!=null){
					map = JSON.parseObject(doGet, HashMap.class);
					if (chartDataId != null && !"".equals(chartDataId)) {
						ManaerChartData mcd = (ManaerChartData) baseDao
								.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
						if (mcd != null && !"".equals(mcd)) {
							map.put("descText", mcd.getDescText());
							map.put("graph_1", mcd.getChartName());
						}
					}
				}
		}
		return map;
//		return userService.findSeeCustomerHaveDealMoneyInfo(startTime, endTime, user, chartDataId);
	}


	/**
	 * 成交量分析_________________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeCustomerHaveDealNumberInfo")
	public Object getSeeCustomerHaveDealNumberInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			
				//获取顾问信息
				//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
				//获取归集数据
				String url = SmsContext.httpClientURL+"/to_SeeCustomerHaveDealNumberInfoByOutSide";
				Map cMap = new HashMap<>();
				cMap.put("proId",user.getParentId());
				cMap.put("startDate", startTime);
				cMap.put("endDate", endTime);
				cMap.put("agentId", u.getUserId());
				String doGet = httpClientService.doPost(url, cMap);
				if(doGet!=null){
					map = JSON.parseObject(doGet, HashMap.class);
					if (chartDataId != null && !"".equals(chartDataId)) {
						ManaerChartData mcd = (ManaerChartData) baseDao
								.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
						if (mcd != null && !"".equals(mcd)) {
							map.put("descText", mcd.getDescText());
							map.put("graph_1", mcd.getChartName());
						}
					}
				}
		}
		return map;
//		return userService.findSeeCustomerHaveDealNumberInfo(startTime, endTime, user, chartDataId);
	}

	

	/**
	 * 销售进度观察______________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeSaleScheduleInfo")
	public Object getSeeSaleScheduleInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			
				//获取顾问信息
				//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
				//获取归集数据
				String url = SmsContext.httpClientURL+"/to_SeeSaleScheduleInfoByOutSide";
				Map cMap = new HashMap<>();
				cMap.put("proId",user.getParentId());
				cMap.put("startDate", startTime);
				cMap.put("endDate", endTime);
				cMap.put("agentId", u.getUserId());
				String doGet = httpClientService.doPost(url, cMap);
				if(doGet!=null){
					map = JSON.parseObject(doGet, HashMap.class);
					if (chartDataId != null && !"".equals(chartDataId)) {
						ManaerChartData mcd = (ManaerChartData) baseDao
								.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
						if (mcd != null && !"".equals(mcd)) {
							map.put("descText", mcd.getDescText());
							map.put("graph_1", mcd.getChartName());
						}
					}
				}
		}
		return map;
//		return userService.findSeeSaleScheduleInfo(startTime, endTime, user, chartDataId);
	}


	/**
	 * 报备到访情况_________________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeCustomerGuideRecprdsToVisitInfo")
	public Object getSeeCustomerGuideRecprdsToVisitInfo(String startTime, String endTime,
			String chartDataId) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			
				//获取顾问信息
				//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
				//获取归集数据
				String url = SmsContext.httpClientURL+"/to_SeeCustomerGuideRecprdsToVisitInfoByOutSide";
				Map cMap = new HashMap<>();
				cMap.put("proId",user.getParentId());
				cMap.put("startDate", startTime);
				cMap.put("endDate", endTime);
				cMap.put("agentId", u.getUserId());
				String doGet = httpClientService.doPost(url, cMap);
				if(doGet!=null){
					map = JSON.parseObject(doGet, HashMap.class);
					if (chartDataId != null && !"".equals(chartDataId)) {
						ManaerChartData mcd = (ManaerChartData) baseDao
								.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
						if (mcd != null && !"".equals(mcd)) {
							map.put("descText", mcd.getDescText());
							map.put("graph_1", mcd.getChartName());
						}
					}
				}
		}
		return map;
//		return userService.findSeeCustomerGuideRecprdsToVisitInfo(startTime, endTime, user, chartDataId);
	}


	/**
	 * 外场成交分析________________________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeOutCustomerDealInfo")
	public Object getSeeOutCustomerDealInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			
				//获取顾问信息
				//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
				//获取归集数据
				String url = SmsContext.httpClientURL+"/to_SeeOutCustomerDealInfoByOutSide";
				Map cMap = new HashMap<>();
				cMap.put("proId",user.getParentId());
				cMap.put("startDate", startTime);
				cMap.put("endDate", endTime);
				cMap.put("agentId", u.getUserId());
				String doGet = httpClientService.doPost(url, cMap);
				if(doGet!=null){
					map = JSON.parseObject(doGet, HashMap.class);
					if (chartDataId != null && !"".equals(chartDataId)) {
						ManaerChartData mcd = (ManaerChartData) baseDao
								.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
						if (mcd != null && !"".equals(mcd)) {
							map.put("descText", mcd.getDescText());
							map.put("graph_1", mcd.getChartName());
						}
					}
				}
		}
		return map;
//		return userService.findSeeOutCustomerDealInfo(startTime, endTime, user, chartDataId);
	}


	/**
	 * 观察老客户接访____________________________________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeOldCustomerVisitInfo")
	public Object getSeeOldCustomerVisitInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			
				//获取顾问信息
				//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
				//获取归集数据
				String url = SmsContext.httpClientURL+"/to_SeeOldCustomerVisitInfoByOutSide";
				Map cMap = new HashMap<>();
				cMap.put("proId",user.getParentId());
				cMap.put("startDate", startTime);
				cMap.put("endDate", endTime);
				cMap.put("agentId", u.getUserId());
				String doGet = httpClientService.doPost(url, cMap);
				if(doGet!=null){
					map = JSON.parseObject(doGet, HashMap.class);
					if (chartDataId != null && !"".equals(chartDataId)) {
						ManaerChartData mcd = (ManaerChartData) baseDao
								.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
						if (mcd != null && !"".equals(mcd)) {
							map.put("descText", mcd.getDescText());
							map.put("graph_1", mcd.getChartName());
						}
					}
				}
		}
		return map;
//		return userService.findSeeOldCustomerVisitInfo(startTime, endTime, user, chartDataId);
	}


	/**
	 * 观察储客分析____________________________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeReserveCustomerInfo")
	public Object getSeeReserveCustomerInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			
				//获取顾问信息
				//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
				//获取归集数据
				String url = SmsContext.httpClientURL+"/to_SeeReserveCustomerInfoByOutSide";
				Map cMap = new HashMap<>();
				cMap.put("proId",user.getParentId());
				cMap.put("startDate", startTime);
				cMap.put("endDate", endTime);
				cMap.put("agentId", u.getUserId());
				String doGet = httpClientService.doPost(url, cMap);
				if(doGet!=null){
					map = JSON.parseObject(doGet, HashMap.class);
					if (chartDataId != null && !"".equals(chartDataId)) {
						ManaerChartData mcd = (ManaerChartData) baseDao
								.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
						if (mcd != null && !"".equals(mcd)) {
							map.put("descText", mcd.getDescText());
							map.put("graph_1", mcd.getChartName());
						}
					}
				}
		}
		return map;
//		return userService.findReserveCustomerInfo(startTime, endTime, user, chartDataId);
	}


	/**
	 * 观察客户认购_____________________________________
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeAllCustomerContractRecordInfo")
	public Object getSeeAllCustomerContractRecordInfo(String startTime, String endTime, String chartDataId)
			throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		Map map = new HashMap<>();
		String userId = user.getUserId();
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime == null || startTime == ""){
			startTime = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		}
		if (endTime == null || endTime == ""){
			endTime = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		}*/
		//如果起始时间不为空，走归集方法，否则走原有方法
		if(SmsContext.toGuiJiFun){
			//如果起始时间不是当天，走归集方法，否则走原有方法；
			
				//获取顾问信息
				//Map aMap = projectReceiveService.findAgentStatusDataById(user, u.getUserId(), startTime, endTime);
				//获取归集数据
				String url = SmsContext.httpClientURL+"/to_SeeAllCustomerContractRecordInfoByOutSide";
				Map cMap = new HashMap<>();
				cMap.put("proId",user.getParentId());
				cMap.put("startDate", startTime);
				cMap.put("endDate", endTime);
				cMap.put("agentId", u.getUserId());
				String doGet = httpClientService.doPost(url, cMap);
				if(doGet!=null){
					map = JSON.parseObject(doGet, HashMap.class);
					if (chartDataId != null && !"".equals(chartDataId)) {
						ManaerChartData mcd = (ManaerChartData) baseDao
								.loadObject("from ManaerChartData where chartDataId = '" + chartDataId + "' ");
						if (mcd != null && !"".equals(mcd)) {
							map.put("descText", mcd.getDescText());
							map.put("graph_1", mcd.getChartName());
						}
					}
				}
		}
		return map;
//		return userService.findAllCustomerContractRecordInfo(startTime, endTime, user, chartDataId);
	}


	/**
	 * 经理分析类目显示____________________________________
	 */
	@RequestMapping("/to_getAppSeeAnalyzeTagsInfo")
	public void getAppSeeAnalyzeTagsInfo() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		List<AnalyzeTagsDTO> atdtoList = userService.findMcdList(user);
		this.outListString(atdtoList);
	}

	// 跳到编辑页面______________________
	@RequestMapping("/to_goToEditShouCangPage")
	public String toGoToEditShouCangPage() {
		return "app/director/fenxiEditor";
	}

	/**
	 * 关联按钮集合_________________________________________
	 * 
	 * @param chartDataId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeRelationFenXiInfo")
	public List<ManaerChartData> getSeeRelationFenXiInfo(String chartDataId) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());

		return userService.findRelationFenXiInfo(chartDataId);
	}

	/**
	 * 未完成-控制器
	 * 
	 * @param projectCustomerId
	 * @param model
	 * @return
	 */
	@RequestMapping("/to_panke")
	public String toPanke(String projectCustomerId, Model model, Integer visitNo) {
		PCustomerInformation pc = appTagManagerService.findCustomersInformation(projectCustomerId, null, null);
		model.addAttribute("pc", pc);
		model.addAttribute("visitNo", visitNo);
		return "app/director/daiPanke";
	}

	/**
	 * 已完成-控制器
	 * 
	 * @param projectCustomerId
	 * @param model
	 * @return
	 */
	@RequestMapping("/to_pankefinish")
	public String toPankefinish(String projectCustomerId, Model model, Integer visitNo) {
		PCustomerInformation pc = appTagManagerService.findCustomersInformation(projectCustomerId, null, null);
		model.addAttribute("pc", pc);
		model.addAttribute("visitNo", visitNo);
		return "app/director/daiPanke2";
	}

	/**
	 * 获取订单管理 全部订单数据
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_get_all_order_data_app")
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
	 * 获取订单管理 待审核订单数据
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_wait_check_order_data_app")
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
	 * 获取订单管理 待付款
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_wait_pay_money_order_data_app")
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
	 * 获取订单管理 待签约
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_wait_sign_order_data_app")
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
	@RequestMapping("/to_already_sign_order_data_app")
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
	@RequestMapping("/to_already_revoke_order_data_app")
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
	@RequestMapping("/to_already_refuse_order_data_app")
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
	 * 获取经理点评
	 * 
	 * @return 经理点评对象
	 */
	@ResponseBody
	@RequestMapping("/getManagerComment")
	public Comment getManagerComment(Integer visitNo) {
		Comment c = projectReceiveService.findManagerCommentByVisitNo(visitNo);
		return c;
	}

	/**
	 * 订单详情控制器____________
	 * 
	 * @param orderId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/to_go_get_order_deail_data_app")
	public String toGoTOgetOrderDeailData(String orderId, Model model) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		model.addAttribute("orderId", orderId);
		return "app/director/orderStatus";
	}

	/**
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/to_get_order_deail_data_app")
	public Object getOrderDeailData(String orderId) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		Map<String, Object> map = projectReceiveService.findOrderDeailDataById(user, orderId);
		return map;
	}

	@RequestMapping("/getCookie123")
	public String getCookiessss() {
		return "app/director/getCookies";
	}

	/**
	 * 进入拒绝订单原因的界面
	 * 
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/to_refuse_page", method = RequestMethod.GET)
	public String toRefuseOrderPage(String orderId, Model model) {
		model.addAttribute("orderId", orderId);
		return "app/director/cancleOrder";
	}

	/**
	 * 拒绝订单审核
	 * 
	 * @param orderId
	 * @param reason
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/to_not_agree_order", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object notAgreeOrder(String orderId, String reason, Model model) {
		User user = (User) this.request.getSession().getAttribute("userInfo");
		Map<String, Object> map = new HashMap<>();
		try {
			projectService.updateRefuseBuyApply(user, Integer.parseInt(orderId), reason);
			map.put("result", "success");
		} catch (NumberFormatException e) {
			map.put("result", "error");
			e.printStackTrace();
		} catch (IOException e) {
			map.put("result", "error");
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 同意申请
	 * 
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/to_agree_order", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object agreeOrder(String orderId) {
		Map<String, Object> map = new HashMap<>();
		User user = (User) this.request.getSession().getAttribute("userInfo");
		try {
			projectService.updateAgreeBuyApply(user, Integer.parseInt(orderId), "同意");
			map.put("result", "success");
		} catch (NumberFormatException e) {
			map.put("result", "error");
			e.printStackTrace();
		} catch (Exception e) {
			map.put("result", "error");
			e.printStackTrace();
		}

		return map;
	}

	

	
	/** 用户退出
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/director_logout")
	public Object userLogout(){
		Map<String, String> map = new HashMap<>();
		User userInfo = (User)SysContent.getSession().getAttribute("userInfo");
		String loginSign = (String) SysContent.getSession().getAttribute("loginSign");
		if(userInfo != null && loginSign != null){
			this.request.getSession().removeAttribute("userInfo");
			this.request.getSession().removeAttribute("loginSign");
			this.request.getSession().removeAttribute("noSession");
			map.put("code", "200");
			map.put("url", "director_login");
			return map;
		}else{
			map.put("code", "202");
			map.put("url", "director_login");
			return map;
		}
	}
}
